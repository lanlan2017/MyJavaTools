package adbs.main.run.act.model;

public class FrameTitle {
    private volatile String deviceName;
    private volatile int batteryLevel;
    private volatile String appName;

    private static final FrameTitle frameTitle = new FrameTitle("设备名", 0, "应用名");

    public FrameTitle(String deviceName, int batteryLevel, String appName) {
        this.deviceName = deviceName;
        this.batteryLevel = batteryLevel;
        this.appName = appName;
    }

    public static FrameTitle getFrameTitle() {
        return frameTitle;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return "" + deviceName + "," + batteryLevel + "%|" + appName;
    }
}
