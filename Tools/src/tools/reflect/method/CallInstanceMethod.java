package tools.reflect.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        return CallInstanceMethod.runClassMethod(getClassName(fQMethodName), getMethodName(fQMethodName));
    }

    /**
     * 反射运行方法
     *
     * @param fQMethodName 全限定方法名
     * @param arg1         方法的第一个参数
     */
    public static String runFQMethodName(String fQMethodName, String arg1) {
        return CallInstanceMethod.runClassMethod(getClassName(fQMethodName), getMethodName(fQMethodName), arg1);
    }


    /**
     * 执行需要两个参数的方法名
     *
     * @param fQMethodName 方法的全限定名
     * @param arg1         方法需要的第1个参数
     * @param arg2         方法需要的第2个参数
     */
    public static String runFQMethodName(String fQMethodName, String arg1, String arg2) {
        // 从全限定方法名中取出全限定类名
        String className = getClassName(fQMethodName);
        // 从全限定方法名中取出方法名
        String methodName = getMethodName(fQMethodName);
        // 执行方法，传入两个参数
        // 显示运行结果
        return CallInstanceMethod.runClassMethod(className, methodName, arg1, arg2);
    }


    /**
     * 执行需要三个字符串参数的方法
     *
     * @param fqMethodName 要执行的方法的全限定方法名
     * @param arg1         要执行的方法所需的第1个字符串参数
     * @param arg2         要执行的方法所需的第2个字符串参数
     * @param arg3         要执行的方法所需的第3个字符串参数
     * @return 方法执行返回的字符串
     */
    public static String runFqMethodName(String fqMethodName, String arg1, String arg2, String arg3) {
        return runClassMethod(getClassName(fqMethodName), getMethodName(fqMethodName), arg1, arg2, arg3);
    }

    /**
     * 从全限定方法名中取出全限定类名
     *
     * @param fqMethodName 全限定方法名
     * @return 全限定类名
     */
    private static String getClassName(String fqMethodName) {
        return fqMethodName.substring(0, fqMethodName.lastIndexOf("."));
    }

    /**
     * 从全限定方法名中取出方法名
     *
     * @param fqMethodName 全限定方法名
     * @return 方法名
     */
    private static String getMethodName(String fqMethodName) {
        return fqMethodName.substring(fqMethodName.lastIndexOf(".") + 1);
    }

    /**
     * 通过反射调用对象的无参方法.
     *
     * @param className  类的全限定名.
     * @param methodName 方法名.
     */
    private static String runClassMethod(String className, String methodName) {
        try {
            // 生成class对象
            Class<?> class1 = Class.forName(className);
            // 从map中获取对象，或者创建对象
            Object object = ObjectMap.get(class1);
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
    private static String runClassMethod(String className, String methodName, String arg) {
        try {
            String result;
            // 根据全限定方法名字创建Class对象
            Class<?> class1 = Class.forName(className);
            // 从Map中获取对象或者创建对象
            Object object = ObjectMap.get(class1);

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
     * 执行需要两个字符串作为参数的方法
     *
     * @param className  要执行的方法所在的类的全限定类名
     * @param methodName 要执行的方法名
     * @param arg1       要执行的方法所需要的第1个参数
     * @param arg2       要执行的方法所需要的第2个参数
     */
    private static String runClassMethod(String className, String methodName, String arg1, String arg2) {
        try {
            String result;
            // 获取类
            Class<?> class1 = Class.forName(className);
            // 创建全限定类名对应的对象，或者从Map中获取
            Object object = ObjectMap.get(class1);

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

    /**
     * 执行需要三个字符串作为参数的方法
     *
     * @param className  方法所在的类的全限定类名
     * @param methodName 要执行的方法
     * @param arg1       要执行的方法所需的第1个参数
     * @param arg2       要执行的方法所需的第2个参数
     * @param arg3       要执行的方法所需的第3个参数
     * @return 方法返回的字符串。
     */
    private static String runClassMethod(String className, String methodName, String arg1, String arg2, String arg3) {
        String result = null;
        // 获取类
        Class<?> class1 = null;
        try {
            class1 = Class.forName(className);
            // 创建全限定类名对应的对象，或者从Map中获取
            Object object = ObjectMap.get(class1);

            // 根据方法名和参数列表获取要调用的方法
            Method method = class1.getMethod(methodName, String.class, String.class, String.class);
            // invoke的调用者method是要运行的方法
            // invoke方法的第一个参数是要调用方法的拥有者,后面剩下的参数是该方法的实参列表
            result = (String) method.invoke(object, arg1, arg2, arg3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
}
