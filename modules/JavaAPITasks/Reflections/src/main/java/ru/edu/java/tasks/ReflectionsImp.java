package ru.edu.java.tasks;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReflectionsImp implements Reflections {
    //TODO: ✔ привеси реализацию методов к заданному интерфейсу
    @Override
    public Object getFieldValueByName(Object object, String fieldName)
            throws NoSuchFieldException {

        if (object == null || fieldName == null)
            throw new NullPointerException();

        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new NoSuchFieldException(e.getMessage());
        }
    }

    @Override
    public Set<String> getProtectedMethodNames(Class clazz) {
        if (clazz == null)
            throw new NullPointerException();
        Set<String> result = new HashSet<>();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method m : methods)
            if (Modifier.isProtected(m.getModifiers()))
                result.add(m.getName());
        return result;

    }

    @Override
    public Set<Method> getAllImplementedMethodsWithSupers(Class clazz) {
        if (clazz == null)
            throw new NullPointerException();
        Set<Method> result = new HashSet<>();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method m : methods)
            result.add(m);

        Class superClass = clazz.getSuperclass();
        if (superClass != null)
            result.addAll(getAllImplementedMethodsWithSupers(superClass));
        return result;
    }

    @Override
    public List<Class> getExtendsHierarchy(Class clazz) {

        if (clazz == null)
            throw new NullPointerException();
        List<Class> classes = new ArrayList<>();

        if (clazz.getSuperclass() != null) {
            classes.add(clazz.getSuperclass());
            classes.addAll(getExtendsHierarchy(clazz.getSuperclass()));
        }
        return classes;

    }

    @Override
    public Set<Class> getImplementedInterfaces(Class clazz) {
        Set<Class> result = new HashSet<>();
        for (Class c : clazz.getInterfaces())
            result.add(c);
        return result;
    }

    @Override
    public List<Class> getThrownExceptions(Method method) {
        List<Class> result = new ArrayList<>();

        for (Class c : method.getExceptionTypes())
            result.add(c);
        return result;
    }

    @Override
    public String getFooFunctionResultForDefaultConstructedClass(){
        Class clazz = SecretClass.class;
        try{
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object sectetClassInstance = constructor.newInstance();
            Method methodFoo = clazz.getDeclaredMethod("foo");
            methodFoo.setAccessible(true);
            return (String) methodFoo.invoke(sectetClassInstance);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers){
        Class clazz = SecretClass.class;

        try {
            Constructor constructor = clazz.getDeclaredConstructor(constructorParameter.getClass());
            constructor.setAccessible(true);
            Object sectetClassInstance = constructor.newInstance(constructorParameter);
            Method methodFoo = clazz.getDeclaredMethod("foo", string.getClass(), integers.getClass());
            methodFoo.setAccessible(true);

            return (String) methodFoo.invoke(sectetClassInstance, string, integers);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

