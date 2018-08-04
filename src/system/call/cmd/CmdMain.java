package system.call.cmd;

import java.io.IOException;

public class CmdMain {
	public static void main(String[] args) {
 
		// 执行批处理文件
		String strcmd = "cmd /c start  D:\\run.bat";
		Runtime rt = Runtime.getRuntime();
		Process ps = null;
		try {
			ps = rt.exec(strcmd);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			ps.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = ps.exitValue();
		if (i == 0) {
			System.out.println("执行完成.");
		} else {
			System.out.println("执行失败.");
		}
		ps.destroy();
		ps = null;
 
		// 批处理执行完后，根据cmd.exe进程名称
		// kill掉cmd窗口
		new CmdMain().killProcess();
 
	}
 
	public void killProcess() {
		Runtime rt = Runtime.getRuntime();
		Process p = null;
		try {
			rt.exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}