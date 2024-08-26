package adbs.main.ui.jpanels.act.jaskson.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

// 使用泛型 T 表示任意类型
public class JsonToFile<T> {

    private final ObjectMapper mapper;

    public JsonToFile() {
        this.mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT); // 格式化输出
    }

    /**
     * 将对象序列化为JSON并写入文件。
     *
     * @param obj      要序列化的对象
     * @param filePath 文件路径
     * @throws IOException 如果写入文件失败
     */
    public void toJsonFile(T obj, String filePath) {
        try {
            mapper.writeValue(new File(filePath), obj);
            System.out.println("已经写入文件：" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中反序列化JSON为对象。
     *
     * @param filePath 文件路径
     * @param clazz    泛型参数的实际类型
     * @return 反序列化的对象
     * @throws IOException 如果读取文件失败
     */
    public <U extends T> U fromJsonFile(String filePath, Class<U> clazz) {
        try {
            return mapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        JsonToFile<User2> jsonToFileHandler = new JsonToFile<>();

        // 创建一个对象
        User2 user = new User2("John Doe", 30);
        // 要序列化的文件
        String filePath = "user2.json";

        // 把这个对象序列化到文件中
        jsonToFileHandler.toJsonFile(user, filePath);
        System.out.println("User object has been serialized to " + filePath);

        // 反序列化，从文件中读取对象
        User2 deserializedUser = jsonToFileHandler.fromJsonFile(filePath, User2.class);
        System.out.println("Deserialized User: " + deserializedUser);
    }
}
