package tools.markdown.make.money;

import tools.file.Files;
import tools.format.date.DateFormatters;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;

public class MakeMoney {
    public static void main(String[] args) {
        System.out.println(new MakeMoney().todaySStatistics());
    }

    /**
     * 生成今日统计信息。
     */
    public String todaySStatistics() {
        File taskFile = new File("G:\\Blog\\blogRoot\\source\\todo\\任务.md");
        // 读取文件内容
        String taskFileStr = Files.readFile(taskFile);
        StringBuilder sb = new StringBuilder();
        // 生成一级标题
        sb.append(heading1());
        sb.append(heading2(taskFileStr));
        return sb.toString();
    }

    /**
     * 生成一级标题。
     * 格式为今日的日期yyyy-MM-dd
     *
     * @return 一级标题。
     */
    private String heading1() {
        return "# " + DateFormatters.yyyyMMdd.format(new Date()) + "\n";
    }

    /**
     * 生成二级标题和表格
     *
     * @param taskFileStr 任务文件内容
     */
    private static String heading2(String taskFileStr) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new StringReader(taskFileStr));
        Model model = null;
        ArrayList<Model> models = new ArrayList<>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                // 如果这行是一级标题
                if (line.matches("^# ([^#]+)$")) {
                    // System.out.println("table start：" + line.replaceAll("^# ([^#]+)$", "$1"));
                    // 创建一个新的model
                    model = new Model();
                    models.add(model);
                    model.setTitle(line.replaceAll("^# ([^#]+)$", "$1"));

                }
                // 如果表格开始了，并且是二级标题
                if (line.matches("^## ([^#]+)$")) {
                    // System.out.println(line.replaceAll("^## ([^#]+)$", "$1"));
                    // 取出二级标题的内容
                    String h2 = line.replaceAll("^## ([^#]+)$", "$1");
                    model.getTableLines().add(h2);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(models.size());

        models.forEach(m -> {
            if (m.getTableLines().size() > 0) {
                // System.out.println(m);
                sb.append(m.toString());
            }
        });
        // return sb.toString();
        return sb.toString()+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    }
}
