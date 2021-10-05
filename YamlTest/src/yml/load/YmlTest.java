package yml.load;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

public class YmlTest {
    public static void main(String[] args) {
        YmlTest test = new YmlTest();
        // test.testLoad();
        // test.testList();
        // test.testMap();
        // test.testFragment();

        // test.yamlToString();
        // test.mapToYmlFile();
        test.collectionToYmlFile();
        test.loadCollection();

    }

    public void testLoad() {
        String yamlStr = "key: hello yaml";
        Yaml yaml = new Yaml();
        Object ret = yaml.load(yamlStr);
        System.out.println(ret);
        System.out.println(ret.getClass().getSimpleName());
    }

    public void testList() {
        System.out.println("----------testList-----------");
        Yaml yaml = new Yaml();
        List<String> ret = (List<String>) yaml.load(this.getClass().getClassLoader().getResourceAsStream("list.yml"));
        System.out.println(ret);
        // yaml.
        System.out.println("----------testList-----------");
    }


    public void testMap() {
        System.out.println("----------testMap-----------");
        Yaml yaml = new Yaml();
        Map<String, Object> ret = (Map<String, Object>) yaml.load(this.getClass().getClassLoader().getResourceAsStream("map.yml"));
        System.out.println(ret);
        System.out.println("----------testMap-----------");
    }

    public void testFragment() {
        System.out.println("----------testFragment-----------");
        Yaml yaml = new Yaml();
        Iterable<Object> ret = yaml.loadAll(this.getClass().getClassLoader().getResourceAsStream("fragment.yml"));
        for (Object o : ret) {
            System.out.println(o);
        }
        System.out.println("----------testFragment-----------");
    }

    // @Test
    public void yamlToString() {
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("name", "Silenthand Olleander");
        data.put("race", "Human");
        data.put("traits", new String[]{"ONE_HAND", "ONE_EYE"});
        Yaml yaml = new Yaml();
        StringWriter writer = new StringWriter();
        yaml.dump(data, writer);
        String expectedYaml = "name: Silenthand Olleander\nrace: Human\ntraits: [ONE_HAND, ONE_EYE]\n";
        System.out.println(writer.toString());
        // assertEquals(expectedYaml, writer.toString());
    }

    /**
     * 测试，Map序列化成yml文件。
     */
    public void mapToYmlFile() {
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("name", "Silenthand Olleander");
        data.put("race", "Human");
        data.put("traits", new String[]{"ONE_HAND", "ONE_EYE"});
        Yaml yaml = new Yaml();
        try {
            FileWriter writer = null;
            writer = new FileWriter(new File("MapToYmlFile.yml"));
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试，Collection序列化成yml文件。
     */
    public void collectionToYmlFile() {
        // Collection<String> collection = new ArrayList<>();
        Collection<String> collection = new TreeSet<>();
        // Collection<String> collection = new HashSet<>();
        // ArrayList<String> collection = new ArrayList<>();
        collection.add("m cb css");
        collection.add("m cb html");
        collection.add("m cb sql");
        collection.add("m cb xml");

        Yaml yaml = new Yaml();
        try {
            FileWriter writer = null;
            writer = new FileWriter(new File("CollectionToYmlFile.yml"));
            yaml.dump(collection, writer);
            // yaml.dumpAs(collection, )
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<String> loadCollection() {
        Yaml yaml = new Yaml();
        Reader reader = null;
        try {
            reader = new FileReader(new File("CollectionToYmlFile.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Collection<String> collection = yaml.load(reader);
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        return collection;
    }

}