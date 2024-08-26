package adbs.main.ui.jpanels.act.jaskson.file;

import java.io.Serializable;

public class User2 implements Serializable {
    private String name;
    private int age;

    //jackson需要一个默认的公共无参构造用于反序列化
    public User2() {
    }

    //    如果不提供默认的公共无参构造函数，可以使用 @JsonCreator 注解来指定使用带参数的构造函数用来反序列化。
    //    @JsonProperty 注解用于指定JSON属性与Java对象属性之间的映射关系。它可以应用于构造函数参数、方法参数、字段或getter方法上
    //    当构造函数的参数名称与JSON属性名称不同时，你需要使用 @JsonProperty 注解来明确指定映射关系。
    //
    //    @JsonCreator
    //    public User2(@JsonProperty("name") String name, @JsonProperty("age") int age) {
    //        this.name = name;
    //        this.age = age;
    //    }


    public User2(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
