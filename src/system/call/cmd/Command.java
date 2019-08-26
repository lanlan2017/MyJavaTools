package system.call.cmd;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Command
{
	/**
	 * 调用其他程序.
	 * <ul>
	 * 例如:
	 * <li>使用参数:<code>ipconfig</code>将在<code>cmd</code>中调用<code>ipconfig</code>命令</li>
	 * <li>使用参数:<code>"cmd /c start F:\\Program\" \"Files\" \"(x86)\\Mozilla\" \"Firefox\\firefox.exe "+"http://localhost:8080/app05a/formatDateTest.jsp";</code>
	 * 这个代码将启动火狐浏览器打卡JSP页面.</li>
	 * </ul>
	 * 
	 * @param commandStr
	 * @return
	 */
	public static String exeCmd(String commandStr)
	{
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			Process p = Runtime.getRuntime().exec(commandStr);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	public static void main(String[] args)
	{
		// String cmdStr = "ipconfig";
		String cmdStr = "cmd /c start F:\\Program\" \"Files\" \"(x86)\\Mozilla\" \"Firefox\\firefox.exe "
			+ "http://localhost:8080/app05a/formatDateTest.jsp";
		System.out.println(exeCmd(cmdStr));
	}
}