package process.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 进程执行器.
 */
public class ProcessRunner {
    /**
     * 运行cmd命令,默认cmd的编码为gbk.
     *
     * @param commandStr cmd命令.cmd命令以空格作为分隔符,第一个参数表示要执行的程序,后面的的参数是该程序的命令行参数.
     * @return 程序的标准输出.
     */
    public String runProcess(String commandStr) {
        return runProcess(commandStr, "gbk");
    }

    /**
     * @param commandStr  cmd命令.cmd命令以空格作为分隔符,第一个参数表示要执行的程序,后面的的参数是该程序的命令行参数.
     * @param cmdEncoding cmd的编码.
     * @return 程序的标准输出.
     */
    public String runProcess(String commandStr, String cmdEncoding) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream(), cmdEncoding));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.concat("\n"));
            }
            // 等待进程运行结束.
            int exitCode = p.waitFor();
            // 如果进程的返回值不是0,则表明进程执行失败.
            if (exitCode != 0)
                // 返回null表示程序执行失败.
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 执行进程生成器中的程序和参数,并返回程序的输出.默认cmd的编码为gbk.
     *
     * @param processBuilder
     * @return 程序执行的结果字符串.
     */
    public String runProcess(ProcessBuilder processBuilder) {
        return runProcess(processBuilder, "gbk");
    }

    /**
     * 执行进程生成器中的程序和参数,并返回程序的输出.
     *
     * @param processBuilder 进程生成器.进程生成器中存放了要执行的程序,该程序的参数,该程序的工作空间等.
     * @param cmdEncoding    cmd的编码.程序将按照这个编码来读取程序的标准输出。
     * @return 程序执行的结果字符串.
     */
    public String runProcess(ProcessBuilder processBuilder, String cmdEncoding) {
        Process process = null;
        StringBuffer sb = new StringBuffer();
        try {
            process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), cmdEncoding));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            // 等待进程运行结束
            int exitCode = process.waitFor();
            // 进程结束了,关闭接收流.
            reader.close();
            // 如果线程返回值不是0则表示线程执行失败.
            if (exitCode != 0)
                return null;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
