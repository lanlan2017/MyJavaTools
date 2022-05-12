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
        String table="\n|软件|金额|提现账户|\n|---:|:---|:---|\n";
        StringBuilder sb=new StringBuilder();
        sb.append(table);
        tableLines.forEach(line->{
            sb.append("|");
            sb.append(line);
            sb.append("|");
            sb.append("|");
            sb.append("|");
            sb.append("\n");

        });

        // return "\n## " + title + "\n" + "lines:{" + tableLines + '}';
        return "\n## " + title + "\n" + sb.toString();
    }
}
