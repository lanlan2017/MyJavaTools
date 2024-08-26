package adbs.main.ui.jpanels.act.jaskson.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;

public class JsonToFile2<T> { // 使用泛型 T 表示任意类型

    private final ObjectMapper mapper;

    public JsonToFile2() {
        this.mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT); // 格式化输出
    }

    /**
     * 将对象序列化为JSON并写入文件。
     *
     * @param obj 要序列化的对象
     * @param filePath 文件路径
     * @throws IOException 如果写入文件失败
     */
    public void toJsonFile(T obj, String filePath) throws IOException {
        mapper.writeValue(new File(filePath), obj);
    }

    /**
     * 从文件中反序列化JSON为对象。
     *
     * @param filePath 文件路径
     * @return 反序列化的对象
     * @throws IOException 如果读取文件失败
     */
    @SuppressWarnings("unchecked")
    public T fromJsonFile(String filePath) throws IOException {
        return (T) mapper.readValue(new File(filePath), (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public static void main(String[] args) {
        JsonToFile2<User2> jsonToFile2Handler = new JsonToFile2<>();

        // 创建一个对象
        User2 user = new User2("John Doe", 30);
        // 要序列化的文件
        String filePath = "user2.json";

        try {
            // 把这个对象序列化到文件中
            jsonToFile2Handler.toJsonFile(user, filePath);
            System.out.println("User object has been serialized to " + filePath);

            // 反序列化，从文件中读取对象
            User2 deserializedUser = jsonToFile2Handler.fromJsonFile(filePath);
            System.out.println("Deserialized User: " + deserializedUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//// 假设 User2 类如下定义：
//class User2 implements Serializable {
//    private String name;
//    private int age;
//
//    public User2(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    // Getters and Setters
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    @Override
//    public String toString() {
//        return "User2{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
//}
