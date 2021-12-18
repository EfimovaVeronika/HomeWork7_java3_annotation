import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TesterClass {

    public static void start(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = Class.forName(className);
        Object object = clazz.newInstance();

        List<Method> firstList = getMethodsByAnnotation(clazz, BeforeSuite.class);

        if(firstList.size() > 1) {
            throw new  RuntimeException("Методов @BeforeSuite не должно быть больше одного ");
        }

        if(firstList.size() > 0) {
            firstList.get(0).invoke(object);
        }

        Map<Integer, List<Method>> firstMap = getTestsWithPriorityByAnnotation(clazz);

        for (Map.Entry<Integer, List<Method>> entry : firstMap.entrySet()) {
            for (Method method : entry.getValue()) {
                method.invoke(object);
            }
        }

        List<Method> secondList = getMethodsByAnnotation(clazz, AfterSuite.class);


        if(secondList.size() > 1) {
            throw new  RuntimeException("Методов @AfterSuite не должно быть больше одного ");
        }

        if (secondList.size() > 0) {
            secondList.get(0).invoke(object);
        }
    }

    public static List<Method> getMethodsByAnnotation(Class clazz, Class annotation) {
        List<Method> methodsList = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.getAnnotation(annotation) != null) {
                methodsList.add(method);
            }
        }
        return methodsList;
    }

    public static Map<Integer,List<Method>> getTestsWithPriorityByAnnotation(Class clazz) {
        Map<Integer,List<Method>> treeMap = new TreeMap<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getAnnotation(Test.class) != null) {
                if(treeMap.get(method.getAnnotation(Test.class).priority()) != null) {
                   treeMap.get(method.getAnnotation(Test.class).priority()).add(method);
                }
                else {
                    List<Method> methodList = new ArrayList<>();
                    methodList.add(method);
                    treeMap.put(method.getAnnotation(Test.class).priority(), methodList);
                }
            }
        }return treeMap;
    }

}
