package yml.load;

import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        // test.testLoad();
        // test.testType();
        // test.test2();
        test.test3();
    }

    public void testLoad() {
        String yamlStr = "key: hello yaml";
        Yaml yaml = new Yaml();
        Object ret = yaml.load(yamlStr);
        System.out.println(ret);
        System.out.println(ret.getClass().getSimpleName());
    }

    public void testType() {
        Yaml yaml = new Yaml();
        List<String> ret = (List<String>) yaml.load(this.getClass().getClassLoader().getResourceAsStream("list.yml"));
        System.out.println(ret);
    }

    public void test2() {
        Yaml yaml = new Yaml();
        Map<String, Object> ret = (Map<String, Object>) yaml.load(
                this.getClass().getClassLoader().getResourceAsStream("map.yml"));
        System.out.println(ret);
    }
    public void test3() {
        Yaml yaml = new Yaml();
        Iterable<Object> ret = yaml.loadAll(this.getClass().getClassLoader()
                .getResourceAsStream("test.yml"));
        for (Object o : ret) {
            System.out.println(o);
        }
    }




}