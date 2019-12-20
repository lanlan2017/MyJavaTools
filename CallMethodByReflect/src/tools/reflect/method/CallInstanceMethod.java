package tools.reflect.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射调用方法.
 */
public class CallInstanceMethod {
    /**
     * 通过反射调用对象的方法.
     *
     * @param className  类的全限定名.
     * @param methodName 方法名.
     */
    public static void noArgMethod(String className, String methodName) {

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
            method.invoke(object);
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
    }

    /**
     * 通过反射调用形参列表是一个String返回值是String的方法.
     *
     * @param className  全限定类名.
     * @param methodName 方法名.
     * @param arg        方法的形参.
     */
    public static String oneArgMethod(String className, String methodName, String arg) {

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
     * @param className  类名
     * @param methodName 方法名.
     * @param arg1       参数1
     * @param arg2       参数2
     */
    public static String twoArgMethod(String className, String methodName, String arg1, String arg2) {

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
            // invoke方法的第一个参数是要调用方法的拥有者,后面剩下的参数是该方法的实参列表
            result = (String) method.invoke(object, arg1, arg2);
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
