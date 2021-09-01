package runable.maven;

import java.io.File;

import java.io.File;

public class SpringDependency{
    static int count = 0;

    public static void main(String[] args){
        // dependency的artifactId值
        String artifactIdInnerText = "_______ARTIFACTID_______";
        // dependency的version的标记
        String versionInnerText = "______VERSION______";
        // dependency模板
        String dependencyTemplate = "<dependency>\r\n" + "    <groupId>org.springframework</groupId>\r\n"
                + "    <artifactId>" + artifactIdInnerText + "</artifactId>\r\n" + "    <version>" + versionInnerText
                + "</version>\r\n" + "</dependency>\r\n";

        // 解压后的spring的lib目录的路径
        String path = "D:\\Desktop\\\u4E66\u7C4D\\\u968F\u4E66\u6E90\u7801\\\u5E93\u6587\u4EF6\\jar\\spring-framework-5.1.6.RELEASE\\libs";

        File file = new File(path);
        // System.out.println(file.getAbsolutePath());
        // dir表示文件的当前目录，name表示当前文件的文件名；
        String[] jars = file.list((dir,name)->{
            if(name.endsWith(".RELEASE.jar")){
                // 统计包的数量
                count++;
                return true;
            }
            return false;
        });
        // System.out.println("有"+count+"个spring*.RELEASE.jar");
        // 创建合适大小的缓存，免得扩容
        StringBuilder sb = new StringBuilder(dependencyTemplate.length() * count);
        String artifactId;
        String version;
        for(String jar:jars){
            // System.out.println(jar);
            artifactId = jar.substring(0, jar.lastIndexOf("-"));
            // System.out.println(artifactId);
            version = jar.substring(jar.lastIndexOf("-") + 1, jar.lastIndexOf(".jar"));
            // System.out.println(version);
            // System.out.println(springDependency.replace(artifactIdMatch,
            // artifactId).replace(versionMatch, version));
            sb.append(dependencyTemplate.replace(artifactIdInnerText, artifactId).replace(versionInnerText, version));
        }
        System.out.println(sb.toString());
    }
}