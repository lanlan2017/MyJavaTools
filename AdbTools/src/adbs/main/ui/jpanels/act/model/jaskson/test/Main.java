package adbs.main.ui.jpanels.act.model.jaskson.test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        try {
            User1 user1 = new User1("John Doe", 30);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(user1);
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

