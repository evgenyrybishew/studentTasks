package ru.edu.java.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ReflectionsTest {
    Reflections ref = new ReflectionsImp();

    @Test
    public void getFieldValueByNameTest() throws Exception {

        Reflections.SecretClass clazz = new Reflections.SecretClass("test");
        Object obj = ref.getFieldValueByName(clazz, "text");
        Assert.assertTrue(obj.toString().equals("test"));
    }

    @Test
    public void getFieldValueByNameTestNoSuchFieldException() throws Exception {
        Reflections.SecretClass clazz = new Reflections.SecretClass("test");
        boolean exception = false;
        try {
            ref.getFieldValueByName(clazz, "field");
        } catch (NoSuchFieldException e) {
            exception = true;
        } finally {
            Assert.assertTrue(exception);
        }
    }

    @Test
    public void getFieldValueByNameTestNullPointerException() throws Exception {
        boolean exception = false;
        try {
            ref.getFieldValueByName(null, "field");
        } catch (NullPointerException e) {
            exception = true;
        } finally {
            Assert.assertTrue(exception);
        }

    }

    @Test
    public void getProtectedMethodNamesTest() throws Exception {
        Set<String> methods = new HashSet<>();
        methods.add("printSomeThing");
        methods.add("sumSomething");
        methods.add("multSomething");
        Assert.assertEquals(methods, ref.getProtectedMethodNames(ClassForTest.class));
    }


    private String readFile(String path) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(path));
        String source = "";
        for (String str : lines)
            source += str + " ";
        return source;
    }

    private boolean matchStringAndSet(String str, Collection<String> set) {
        String[] array = str.split(" ");
        if (array.length == set.size()) {
            for (String s : array)
                if (!set.contains(s))
                    return false;
            return true;
        }
        return false;
    }


    @Test
    public void getAllImplementedMethodsWithSupersTest() throws Exception {
        Set<Method> methodSet = ref.getAllImplementedMethodsWithSupers(ClassForTest.class);
        ArrayList<String> methodSetConvert = new ArrayList<>();
        for (Method m : methodSet)
            methodSetConvert.add(m.getName());

        String result = readFile("modules/JavaAPITasks/Reflections/src/test/resource/methods.txt");
        Assert.assertTrue(matchStringAndSet(result, methodSetConvert));
    }




    @Test
    public void getExtendsHierarchyTest() throws Exception {
        List<Class> list = ref.getExtendsHierarchy(ClassForTest.class);
        List<Class> result = new ArrayList<>();

        result.add(Reflections.SecretClass.class);
        result.add(Object.class);

        Assert.assertEquals(list, result);
    }

    @Test
    public void getImplementedInterfacesTest() throws Exception {

        Set<Class> list = ref.getImplementedInterfaces(ClassForTest.class);
        ArrayList<String> interfacesSetConvert = new ArrayList<>();
        for (Class c : list)
            interfacesSetConvert.add(c.getSimpleName());
        String result = readFile("modules/JavaAPITasks/Reflections/src/test/resource/interfaces.txt");
        Assert.assertTrue(matchStringAndSet(result, interfacesSetConvert));
    }

    @Test
    public void getThrownExceptionsTest() throws Exception {

        Method[] methods = ClassForTest.class.getDeclaredMethods();
        ArrayList<Class> ex = new ArrayList<>();
        ArrayList<String> exSetConvert = new ArrayList<>();

        for (Method m : methods)
            ex.addAll(ref.getThrownExceptions(m));

        for (Class c : ex)
            exSetConvert.add(c.getSimpleName());

        String result = readFile("modules/JavaAPITasks/Reflections/src/test/resource/exception.txt");
        Assert.assertTrue(matchStringAndSet(result, exSetConvert));

    }

    @Test
    public void getFooFunctionResultForDefaultConstructedClassTest() throws Exception {
        Assert.assertTrue(ref.getFooFunctionResultForDefaultConstructedClass().equals("abraKadabra"));
    }

    @Test
    public void getFooFunctionResultForClassTest() throws Exception {
        String result = ref.getFooFunctionResultForClass("constructor parameter", "method parametr", 1, 2, 4);
        Assert.assertTrue(result.equals("method parametr - > 7"));
    }
}