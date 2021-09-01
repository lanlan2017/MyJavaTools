package runable.maven;

public class JarName {
    public static void main(String[] args) {
        String jarPath = "/Login6SsmMaven/src/main/webapp/WEB-INF/lib/c3p0-0.9.5.5";
        System.out.println(artifactIdAndVersion(jarPath));
    }

    /**
     * 获取一个jar包的名称和版本
     *
     * @param jarPath jar包的路径,相对路径，或绝对路径
     * @return 两行字符串，第一行是jar包的名称，第二行是jar包的版本。
     */
    private static String artifactIdAndVersion(String jarPath) {
        String result = "输入错误，请输入jar包的相对路径或绝对路径(Windows格式)。";
        if (jarPath.endsWith(".jar")) {
            String jarName = jarPath.substring(jarPath.lastIndexOf("/") + 1);
            String artifactId = jarName.substring(0, jarName.lastIndexOf("-"));
            String version = jarName.substring(jarName.indexOf("-") + 1, jarName.lastIndexOf(".jar"));
            System.out.println(jarName);
            System.out.println(artifactId);
            System.out.println(version);
            result = artifactId + "\n" + version;
        }
        return result;
    }
}
