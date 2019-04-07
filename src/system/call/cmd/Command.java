package system.call.cmd;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Command
{
	/**
	 * ������������.
	 * <ul>
	 * ����:
	 * <li>ʹ�ò���:<code>ipconfig</code>����<code>cmd</code>�е���<code>ipconfig</code>����</li>
	 * <li>ʹ�ò���:<code>"cmd /c start F:\\Program\" \"Files\" \"(x86)\\Mozilla\" \"Firefox\\firefox.exe "+"http://localhost:8080/app05a/formatDateTest.jsp";</code>
	 * ������뽫��������������JSPҳ��.</li>
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