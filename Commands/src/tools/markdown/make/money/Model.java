package tools.markdown.make.money;

import java.util.ArrayList;

/**
 * 模型
 */
public class Model {
    private String title;
    private ArrayList<String> tableLines;

    public Model() {
        tableLines = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getTableLines() {
        return tableLines;
    }

    @Override
    public String toString() {
        String table = "\n|软件|提现账户|金额|\n|---:|:---|:---|\n";
        StringBuilder sb = new StringBuilder();
        sb.append(table);
        tableLines.forEach(line -> {
            if(line.contains("_")){
                int splitIndex = line.lastIndexOf("_");
                // 截取出软件名称
                String software=line.substring(0, splitIndex);
                // 截取出提现账户
                String withdrawalAccount=line.substring(splitIndex+1);
                // System.out.println("software = " + software);
                // System.out.println("withdrawalAccount = " + withdrawalAccount);
                sb.append("|");
                sb.append(software);
                sb.append("|");
                sb.append(withdrawalAccount);
                sb.append("|");
                sb.append("|");
                sb.append("\n");
            } else {
                sb.append("|");
                sb.append(line);
                sb.append("|");
                sb.append("|");
                sb.append("|");
                sb.append("\n");
            }
        });

        // return "\n## " + title + "\n" + sb;
        return "\n## " + title + "\n" + sb+"\n\n\n\n\n";
    }
}
