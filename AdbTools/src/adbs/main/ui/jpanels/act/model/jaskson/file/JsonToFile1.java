package adbs.main.ui.jpanels.act.model.jaskson.file;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Jason序列号到文件，从文件反序列化示例。
 */
public class JsonToFile1 {

    public static void toJsonFile(User2 user, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 序列化到文件中
        mapper.writeValue(new File(filePath), user);
    }

    public static User2 fromJsonFile(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), User2.class);
    }

    public static void main(String[] args) {
        // 创建一个对象
        User2 user = new User2("John Doe", 30);
        //要序列化文件
        String filePath = "user2.json";
        try {
            //把这个对象序列化到文件中
            toJsonFile(user, filePath);
            System.out.println("User object has been serialized to " + filePath);
            //反序列化，从文件中读取对象
            User2 deserializedUser = fromJsonFile(filePath);
            System.out.println("Deserialized User: " + deserializedUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


