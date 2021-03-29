package runable.wifi;

/**
 * ��ȡwindows���ԣ����ӹ���WiFi������
 * ע��,�˴�����Ҫ��cmd�����У���ide�����п��ܲ�����Ч����
 * �������cmd,Ȼ��javac���뱾���룬Ȼ����ʹ��java�������С�
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GetWiFiPassWord {
    /**
     * @param commandStr cmd ����̨����
     * @return �ÿ���̨����commandStr���еĽ��
     */
    public static String exeCmd(String commandStr) {
        String result = null;
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            // System.out.println(sb.toString());
            result = sb.toString();
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

        return result;
    }

    /**
     * @throws FileNotFoundException
     */
    public static void printWiFiPassWord(String result)
            throws FileNotFoundException {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(result);
        String line;
        String wifi;
        String passworld;
        while ((line = scanner.nextLine()) != null) {
            // SSID ���� :��Hello��
            if (line.contains("SSID ����")) {
                wifi = line.substring(line.lastIndexOf("��") + 1,
                        line.length() - 1);
                System.out.println("����:" + wifi.trim());// trim()ȥ������Ŀհ׷�
            }
            // �ؼ����� : *********
            else if (line.contains("�ؼ�����")) {
                passworld = line.substring(line.lastIndexOf(":") + 1);
                System.out.println("����:" + passworld.trim());// trim()ȥ������Ŀհ׷�
            }
        }
    }

    public static String getWiFiMap(String result) throws FileNotFoundException {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(result);
        String line;
        String wifi;
        String passworld;
        StringBuilder buff = new StringBuilder();
        HashMap<String, String> WiFiMap = new HashMap<String, String>();
        try {
            /*
             * �ӿ� WLAN �ϵ������ļ� Ŷ: --->WiFi����Ŷ,λ��"�ӿ� WLAN �ϵ������ļ�"��仰��ð��֮��
             */
            // ����仰˵������������
            String WiFiNameLineFlag = "�ӿ� WLAN �ϵ������ļ�";
            // ����java.util.NoSuchElementException
            while ((line = scanner.nextLine()) != null) {
                // SSID ���� :��Hello��

                if (line.contains(WiFiNameLineFlag)) {
                    wifi = line.substring(
                            line.lastIndexOf(WiFiNameLineFlag)
                                    + WiFiNameLineFlag.length(),
                            line.lastIndexOf(":"));
                    // System.out.print("����:"+wifi.trim());//trim()ȥ������Ŀհ׷�
                    buff.append("����:" + wifi.trim() + "|");
                }
                // �ؼ����� : *********
                if (line.contains("�ؼ�����")) {
                    passworld = line.substring(line.lastIndexOf(":") + 1);
                    // System.out.println("|����:"+passworld.trim());//trim()ȥ������Ŀհ׷�
                    buff.append("����:" + passworld.trim());
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return buff.toString();
    }

    /**
     * ��ȡ���ӹ���WiFi�������б�
     *
     * @return �������ӹ���WiFi�����б�
     */
    public static ArrayList<String> getWiFiNameList() {
        String allWiFiName = "netsh wlan show profiles";
        String cmdResult = GetWiFiPassWord.exeCmd(allWiFiName);
        Scanner scanner = new Scanner(cmdResult);// ɨ����
        ArrayList<String> WiFiNameList = new ArrayList<String>();
        String line = null;
        try {
            // ���׳��쳣 java.util.NoSuchElementException:
            while ((line = scanner.nextLine()) != null) {
                // System.out.println(line);
                if (line.contains(":")) {
                    String name = line.substring(line.lastIndexOf(":") + 1)
                            .trim();
                    // :����û�����ֵı�ʾ��ֻ�Ǹ�ð�ţ�����������Ҫ��WiFi��
                    if (!name.equals(""))
                        WiFiNameList.add(name);
                }
            }
        } catch (Exception e) {
            // ��������������Ϊ���ó�����������ȥ
            // TODO: handle exception
        }
        return WiFiNameList;
    }

    /**
     * cmd��ѯname��Ӧ��WiFi���������ļ���������cmdִ�еĽ��
     *
     * @param name
     * @return
     */
    public static String getPassWordByName(String name) {
        String commandStr = "netsh wlan show profile name=" + name
                + " key=clear";
        String result = GetWiFiPassWord.exeCmd(commandStr);
        return result;
    }

    public static void main(String[] args)
            throws FileNotFoundException, InterruptedException {
        // �����±�׼�����
        PrintStream out = System.out;
        System.out.println("����رյ�ǰ����");
        System.out.println("��������WiFi�����ļ�...");
        String outFile = "����������WiFi����.txt";
        StringBuffer sb = new StringBuffer();
        String line;
        // ��ȡWiFi���б�
        ArrayList<String> WiFiNameList = getWiFiNameList();
        for (String string : WiFiNameList) {
            // ����ÿ��WiFi�б��е�WiFi���ƣ���ȡWiFi������
            line = getWiFiMap(getPassWordByName(string));
            System.out.println(line);
            sb.append(line).append("\n");
        }


        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File("����������WiFi����.txt")));) {

            writer.write(sb.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // �ָ���ԭ���ı�׼�����
        System.out.println("������WiFi�����ļ�,·��:.\\����������WiFi����.txt");
    }

}
