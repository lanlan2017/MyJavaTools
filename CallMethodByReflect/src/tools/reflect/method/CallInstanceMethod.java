package tools.reflect.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射调用方法.
 */
public class CallInstanceMethod {

    /**
     * 反射运行方法
     *
     * @param fQMethodName 全限定方法名
     * @param arg1         方法的第一个参数
     */
    public static String runFQMethodName(String fQMethodName, String arg1) {
        String className = fQMethodName.substring(0, fQMethodName.lastIndexOf("."));
        String methodName = fQMethodName.substring(fQMethodName.lastIndexOf(".") + 1);
        String result = CallInstanceMethod.runClassNameMethodName(className, methodName, arg1);
        return result;
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
        String result = CallInstanceMethod.runClassNameMethodName(className, methodName, arg1, arg2);
        // 显示运行结果
        return result;
    }

    /**
     * 通过反射调用对象的方法.
     *
     * @param className  类的全限定名.
     * @param methodName 方法名.
     */
    public static String runClassNameMethodName(String className, String methodName) {
        try {
            // 生成class对象
            Class<?> class1 = Class.forName(className);
            // 根据参数列表获取构造器
            Constructor<?> constructor = class1.getConstructor();
            // 创建对象
            Object object = constructor.newInstance();
            // 获取内中的方法
            Method method = class1.getDeclaredMethod(methodName);
            // 设置可以访问私有方法
            method.setAccessible(true);
            // 调用该方法
            String result = (String) method.invoke(object);
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过反射调用形参列表是一个String返回值是String的方法.
     *
     * @param className  全限定类名.
     * @param methodName 方法名.
     * @param arg        方法的形参.
     */
    public static String runClassNameMethodName(String className, String methodName, String arg) {

        try {
            String result;
            // 获取类
            Class<?> class1 = Class.forName(className);
            // 根据参数列表获取构造器
            Constructor<?> constructor = class1.getConstructor();
            // 创建对象
            Object object = constructor.newInstance();
            // 根据方法名和参数列表获取要调用的方法
            Method method = class1.getMethod(methodName, String.class);
            // invoke方法的第一个参数是要调用方法的拥有者,后面剩下的参数是该方法的实参列表
            result = (String) method.invoke(object, arg);
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
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
    public static String runClassNameMethodName(String className, String methodName, String arg1, String arg2) {

        try {
            String result;
            // 获取类
            Class<?> class1 = Class.forName(className);
            // 根据参数列表获取构造器
            Constructor<?> constructor = class1.getConstructor();
            // 创建对象
            Object object = constructor.newInstance();
            // 根据方法名和参数列表获取要调用的方法
            Method method = class1.getMethod(methodName, String.class, String.class);
            // invoke的调用者method是要运行的方法
            // invoke方法的第一个参数是要调用方法的拥有者,后面剩下的参数是该方法的实参列表
            result = (String) method.invoke(object, arg1, arg2);
            // 返回运行结果
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
