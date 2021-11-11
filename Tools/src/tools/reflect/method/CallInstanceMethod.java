package tools.reflect.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 通过反射调用对象的方法
 */
public class CallInstanceMethod {
    // /**
    //  * 对象缓存，已经创建过的对象将会保存在这里。
    //  */
    // private static HashMap<String, Object> objectMap = new HashMap<>();

    /**
     * 反射运行方法
     *
     * @param fQMethodName 全限定方法名
     */
    public static String runFQMethodName(String fQMethodName) {
        String className = fQMethodName.substring(0, fQMethodName.lastIndexOf("."));
        String methodName = fQMethodName.substring(fQMethodName.lastIndexOf(".") + 1);
        return CallInstanceMethod.runClassMethod(className, methodName);
    }

    /**
     * 反射运行方法
     *
     * @param fQMethodName 全限定方法名
     * @param arg1         方法的第一个参数
     */
    public static String runFQMethodName(String fQMethodName, String arg1) {
        // 从全限定方法名中取出全限定类名
        String className = fQMethodName.substring(0, fQMethodName.lastIndexOf("."));
        // 从全限定方法名中取出方法名
        String methodName = fQMethodName.substring(fQMethodName.lastIndexOf(".") + 1);
        // 根据全限定类名，方法命令，参数
        return CallInstanceMethod.runClassMethod(className, methodName, arg1);
    }

    /**
     * 反射执行有两个参数的方法
     *
     * @param fQMethodName 方法的全限定名
     * @param arg1         方法的第1个参数
     * @param arg2         方法的第2个参数
     */
    public static String runFQMethodName(String fQMethodName, String arg1, String arg2) {
        // 从全限定方法名中取出全限定类名
        String className = fQMethodName.substring(0, fQMethodName.lastIndexOf("."));
        // 从全限定方法名中取出方法名
        String methodName = fQMethodName.substring(fQMethodName.lastIndexOf(".") + 1);
        // 执行方法，传入两个参数
        // 显示运行结果
        return CallInstanceMethod.runClassMethod(className, methodName, arg1, arg2);
    }

    /**
     * 通过反射调用对象的无参方法.
     *
     * @param className  类的全限定名.
     * @param methodName 方法名.
     */
    public static String runClassMethod(String className, String methodName) {
        try {
            // 生成class对象
            Class<?> class1 = Class.forName(className);
            // 从map中获取对象，或者创建对象
            Object object = ObjectMap.getObjectFromMap(class1);
            // 获取内中的方法
            Method method = class1.getDeclaredMethod(methodName);
            // 设置可以访问私有方法
            method.setAccessible(true);
            // 调用该方法
            return (String) method.invoke(object);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过反射调用形参列表是一个String，返回值是String的方法.
     *
     * @param className  全限定类名.
     * @param methodName 方法名.
     * @param arg        方法的形参.
     */
    public static String runClassMethod(String className, String methodName, String arg) {

        try {
            String result;
            // 根据全限定方法名字创建Class对象
            Class<?> class1 = Class.forName(className);
            // 从Map中获取对象或者创建对象
            Object object = ObjectMap.getObjectFromMap(class1);

            // 根据方法名和参数列表获取要类的方法
            Method method = class1.getMethod(methodName, String.class);
            // 执行该方法,并获得返回值
            // invoke方法的第一个参数是要调用方法的拥有者,后面剩下的参数是该方法的实参列表
            result = (String) method.invoke(object, arg);
            return result;
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过反射执行两个参数的方法。
     *
     * @param className  全限定类名
     * @param methodName 方法名
     * @param arg1       参数1
     * @param arg2       参数2
     */
    public static String runClassMethod(String className, String methodName, String arg1, String arg2) {

        try {
            String result;
            // 获取类
            Class<?> class1 = Class.forName(className);
            // 创建全限定类名对应的对象，或者从Map中获取
            Object object = ObjectMap.getObjectFromMap(class1);

            // 根据方法名和参数列表获取要调用的方法
            Method method = class1.getMethod(methodName, String.class, String.class);
            // invoke的调用者method是要运行的方法
            // invoke方法的第一个参数是要调用方法的拥有者,后面剩下的参数是该方法的实参列表
            result = (String) method.invoke(object, arg1, arg2);
            // 返回运行结果
            return result;
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    // /**
    //  *
    //  * 从Map中获取对象，如果没有，则创建一个
    //  *
    //  * @param class1 要获取的对象的Class对象
    //  * @return Class对象对应的实例对象。
    //  */
    // private static Object getObjectFromMap(Class<?> class1){
    //     String className = class1.getName();
    //     Object object=null;
    //     // 如果Map中有这个对象了
    //     if (objectMap.containsKey(className)) {
    //         // System.out.println("从HashMap中获取要运行的对象");
    //         object = objectMap.get(className);
    //     }
    //     // 如果Map中没有这个对象
    //     else {
    //         // System.out.println("通过反射创建对象");
    //         // 获取类的无参构造器
    //         Constructor<?> constructor = null;
    //         try {
    //             constructor = class1.getConstructor();
    //             // 通过构造器创建对象
    //             object = constructor.newInstance();
    //             // 把这个对象放到Map中
    //             objectMap.put(className, object);
    //         } catch (NoSuchMethodException e) {
    //             e.printStackTrace();
    //         } catch (InvocationTargetException e) {
    //             e.printStackTrace();
    //         } catch (InstantiationException e) {
    //             e.printStackTrace();
    //         } catch (IllegalAccessException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //     return object;
    // }
}
