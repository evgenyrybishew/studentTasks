package ru.edu.java.tasks;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReflectionsImp implements Reflections {


    @Override
    public Object getFieldValueByName(Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException, NullPointerException {

        if (object == null || fieldName == null)
            throw new NullPointerException();

        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    @Override
    public Set<String> getProtectedMethodNames(Class clazz) throws NullPointerException {
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

        if (clazz.getSuperclass() != null)
            result.addAll(getAllImplementedMethodsWithSupers(clazz.getSuperclass()));
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
        Class[] interfaces = clazz.getInterfaces();
        for (Class c : interfaces)
            result.add(c);
        return result;

    }

    @Override
    public List<Class> getThrownExceptions(Method method) {
        Class[] ex = method.getExceptionTypes();
        List<Class> result = new ArrayList<>();

        for (Class c : ex)
            result.add(c);
        return result;
    }

    @Override
    public String getFooFunctionResultForDefaultConstructedClass()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class clazz = SecretClass.class;

        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object sectetClassInstance = constructor.newInstance();
        Method methodFoo = clazz.getDeclaredMethod("foo");
        methodFoo.setAccessible(true);

        return (String) methodFoo.invoke(sectetClassInstance);

    }

    @Override
    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = SecretClass.class;

        Constructor constructor = clazz.getDeclaredConstructor(constructorParameter.getClass());
        constructor.setAccessible(true);
        Object sectetClassInstance = constructor.newInstance(constructorParameter);
        Method methodFoo = clazz.getDeclaredMethod("foo", string.getClass(), integers.getClass());
        methodFoo.setAccessible(true);

        return (String) methodFoo.invoke(sectetClassInstance, string, integers);
    }
}

