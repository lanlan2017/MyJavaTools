package tools.reflect.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * 对象缓存池
 */
public class ObjectMap {
    /**
     * 对象缓存，已经创建过的对象将会保存在这里。
     */
    private static final HashMap<String, Object> objectMap = new HashMap<>();
    /**
     * 从Map中获取对象，如果没有，则创建一个
     *
     * @param class_ 要获取的对象的Class对象
     * @return Class对象对应的实例对象。
     */
    public static <T> T  getObjectFromMap(Class<T> class_){
        String className = class_.getName();
        Object object=null;
        // 如果Map中有这个对象了
        if (objectMap.containsKey(className)) {
            System.out.println("从HashMap中获取要运行的对象");
            object = objectMap.get(className);
        }
        // 如果Map中没有这个对象
        else {
            System.out.println("通过反射创建对象");
            // 获取类的无参构造器
            Constructor<?> constructor;
            try {
                constructor = class_.getConstructor();
                // 通过构造器创建对象
                object = constructor.newInstance();
                // 把这个对象放到Map中
                objectMap.put(className, object);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return (T) object;
    }

    // public static <T> T getObjectFromMap(String className){
    //     // String className = class1.getName();
    //     Object object=null;
    //     // 如果Map中有这个对象了
    //     if (objectMap.containsKey(className)) {
    //         System.out.println("从HashMap中获取要运行的对象");
    //         object = objectMap.get(className);
    //     }
    //     // 如果Map中没有这个对象
    //     else {
    //         System.out.println("通过反射创建对象");
    //         // 获取类的无参构造器
    //         Constructor<?> constructor;
    //         try {
    //             Class<?> class1=Class.forName(className);
    //             constructor = class1.getConstructor();
    //             // 通过构造器创建对象
    //             object = constructor.newInstance();
    //             // 把这个对象放到Map中
    //             objectMap.put(className, object);
    //         } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //     return (T) object;
    // }
}
