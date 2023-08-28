package adbs.main.run;

import adbs.cmd.CmdRun;

import java.util.*;

/**
 * 电池信息类，封装"adb shell dumpsys battery"命令的执行结果
 */
public class BatteryModel {

    // Current Battery Service state:
    //   AC powered: true
    //   USB powered: false
    //   Wireless powered: false
    //   Max charging current: 0
    //   Max charging voltage: 0
    //   Charge counter: 220
    //   status: 2
    //   health: 2
    //   present: true
    //   level: 15
    //   scale: 100
    //   voltage: 4359
    //   temperature: 340
    //   technology: Li-poly
    private static String[] flag = {"Current Battery Service state:", "AC powered:", "USB powered:", "Wireless powered:", "Max charging current:", "Max charging voltage:", "Charge counter:", "status:", "health:", "present:", "level:", "scale:", "voltage:", "temperature:", "technology:"};
    // private static ArrayList<String> mameList = new ArrayList<>(flag.length);
    //
    // static {
    //     Collections.addAll(mameList, flag);
    // }

    private String serial;
    private boolean isAcPowered;
    private boolean isUSBPowered;
    private boolean isWirelessPowered;
    private int maxChargingCurrent;
    private int maxChargingVoltage;
    private int chargeCounter;
    private int status;
    private int health;
    private boolean present;
    private int level;
    private int scale;
    private int voltage;
    private int temperature;
    private String technology;


    public static void main(String[] args) {
        // String serial = "75aed56d";
        // String serial = "192.168.0.102:5555";
        String serial = "192.168.0.113:5561";
        // String serial = "U8ENW18119024027";
        BatteryModel model = new BatteryModel(serial);
        System.out.println("model = " + model);
        if (model.needAcPower()) {
            System.out.println("请使用充电头充电");
        }
    }

    /**
     * 判断设备是否需要使用充电头充电。
     *
     * @return 如果没有使用充电头充电，并且电量小于百分之30，则返回true，否则返回false
     */
    public boolean needAcPower() {
        // 更新电池信息
        // update();
        return !isAcPowered && level < 30;
        // return !isAcPowered && level <= 100;
    }

    /**
     * 判断是否需要使用USB充电。
     *
     * @return 如果没有使用充电头充电，也没有用使用USB充电，并且电池电量小于50%，
     * 则需要使用USB进行充电，返回true。
     */
    public boolean isNeedUSBPower() {
        // System.out.println("isUSBPowered = " + isUSBPowered);
        return !isAcPowered && !isUSBPowered && level < 75;
    }

    /**
     * 判断是否需要停止使用USB充电。
     *
     * @return 如果没有AC充电，并且正在USB充电，并且电量又大于80%，则返回true，表示应该停止USB充电。
     */
    public boolean isStopUSBPower() {
        // System.out.println("isUSBPowered = " + isUSBPowered);
        return !isAcPowered && isUSBPowered && level > 95;
    }


    public BatteryModel(String serial) {
        // String serial = AdbTools.getInstance().getDevice().getId();
        // String serial = "75aed56d";
        this.serial = serial;
        // update();
        // return loopTimes;
    }

    /**
     * 查询电池信息。
     */
    public void update() {
        // 拼接命令
        String code = "adb -s " + serial + " shell dumpsys battery";
        // 执行命令
        String run = CmdRun.run(code);
        // System.out.println("run = " + run);
        // System.out.println("----------------------------");
        // 创建一个和标记数组长度一样的列表
        ArrayList<String> mameList = new ArrayList<>(flag.length);
        // 把标记字符串数组复制到列表中
        Collections.addAll(mameList, flag);


        // 从list中取出信息开始的标记字符串
        String startFlag = mameList.get(0);
        // 移除标记字符串，后续不需要使用了
        mameList.remove(0);

        // 遍历命令的结果
        Scanner scanner = new Scanner(run);
        int flagIndex = 0;
        boolean start = false;

        // long loopTimes = 0;
        while (scanner.hasNext()) {
            // loopTimes++;
            String line = scanner.nextLine();
            // 如果遇到空行，则跳过
            if ("".equals(line)) {
                continue;
            }
            // System.out.println("line =|" + line + "|");
            // if (!start && line.contains(flag[0])) {
            // 如果假的，则执行后面的判断
            if (!start && line.contains(startFlag)) {
                // 我们需要的信息开始了
                start = true;
                // System.out.println("--------------------");
                continue;
            }
            // 电池信息开始后
            if (start) {
                // 获取电池信息名称的迭代器
                Iterator iterator = mameList.iterator();
                while (iterator.hasNext()) {
                    // 获取当前
                    String name = (String) iterator.next();
                    // 如果这行命令输出有这个名称
                    if (line.contains(name)) {
                        // 从集合中删除这个key
                        // System.out.println("包含：" + name);
                        // 从这行命令中取出名称对应的值
                        String value = line.substring(line.indexOf(name) + name.length()).trim();
                        // System.out.println("value = |" + value + "|");
                        // model.setter(name, value);
                        // 根据名称给成员变量赋值
                        setter(name, value);
                        // 这个名称的值已经设置到对象中，后续不需要再次设置了，把这个名称从列表中删除掉
                        iterator.remove();
                        // 找到匹配项了，结束查找
                        break;
                    }
                    // else {
                    //     System.out.println("不包含：" + name);
                    // }
                }

            }
        }
    }

    public boolean isAcPowered() {
        return isAcPowered;
    }

    public boolean isUSBPowered() {
        return isUSBPowered;
    }

    public boolean isWirelessPowered() {
        return isWirelessPowered;
    }

    public int getMaxChargingCurrent() {
        return maxChargingCurrent;
    }

    public int getMaxChargingVoltage() {
        return maxChargingVoltage;
    }

    public int getChargeCounter() {
        return chargeCounter;
    }

    public int getStatus() {
        return status;
    }

    public int getHealth() {
        return health;
    }

    public boolean isPresent() {
        return present;
    }

    public int getLevel() {
        return level;
    }

    public int getScale() {
        return scale;
    }

    public int getVoltage() {
        return voltage;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getTechnology() {
        return technology;
    }

    public void setUSBPowered(boolean USBPowered) {
        isUSBPowered = USBPowered;
    }

    /**
     * 设置值
     *
     * @param name
     * @param value
     */
    private void setter(String name, String value) {
        switch (name) {
            case "AC powered:":
                isAcPowered = Boolean.parseBoolean(value);
                break;
            case "USB powered:":
                isUSBPowered = Boolean.parseBoolean(value);
                break;
            case "Wireless powered:":
                isWirelessPowered = Boolean.parseBoolean(value);
                break;
            case "Max charging current:":
                maxChargingCurrent = Integer.parseInt(value);
                break;
            case "Max charging voltage:":
                maxChargingVoltage = Integer.parseInt(value);
                break;
            case "Charge counter:":
                chargeCounter = Integer.parseInt(value);
                break;
            case "status:":
                status = Integer.parseInt(value);
                break;
            case "health:":
                health = Integer.parseInt(value);
                break;
            case "present:":
                present = Boolean.parseBoolean(value);
                break;
            case "level:":
                level = Integer.parseInt(value);
                break;
            case "scale:":
                scale = Integer.parseInt(value);
                break;
            case "voltage:":
                voltage = Integer.parseInt(value);
                break;
            case "temperature:":
                temperature = Integer.parseInt(value);
                break;
            case "technology:":
                technology = value;
                break;
        }
    }

    // @Override
    // public String toString() {
    //     return "BatteryLeveModel{" + "isAcPowered=" + isAcPowered + ", isUSBPowered=" + isUSBPowered + ", isWirelessPowered=" + isWirelessPowered + ", maxChargingCurrent=" + maxChargingCurrent + ", maxChargingVoltage=" + maxChargingVoltage + ", chargeCounter=" + chargeCounter + ", status=" + status + ", health=" + health + ", present=" + present + ", level=" + level + ", scale=" + scale + ", voltage=" + voltage + ", temperature=" + temperature + ", technology='" + technology + '\'' + '}';
    // }

    @Override
    public String toString() {
        // return "\nCurrent Battery Service state:\n" + "isAcPowered=" + isAcPowered + "\n isUSBPowered=" + isUSBPowered + "\n isWirelessPowered=" + isWirelessPowered + "\n maxChargingCurrent=" + maxChargingCurrent + "\n maxChargingVoltage=" + maxChargingVoltage + "\n chargeCounter=" + chargeCounter + "\n status=" + status + "\n health=" + health + "\n present=" + present + "\n level=" + level + "\n scale=" + scale + "\n voltage=" + voltage + "\n temperature=" + temperature + "\n technology='" + technology + '\'' + '}';
        return "\nCurrent Battery Service state:\n" + "AC powered: " + isAcPowered + "\nUSB powered: " + isUSBPowered + "\nWireless powered: " + isWirelessPowered + "\nMax charging current: " + maxChargingCurrent + "\nMax charging voltage: " + maxChargingVoltage + "\nCharge counter: " + chargeCounter + "\nstatus: " + status + "\nhealth: " + health + "\npresent: " + present + "\nlevel: " + level + "\nscale: " + scale + "\nvoltage: " + voltage + "\ntemperature: " + temperature + "\ntechnology: " + technology + "\n";
    }
}
