package adbs.cmd;

import adbs.main.AdbTools;
import adbs.model.Device;

public class Teststetset {
    public static void main(String[] args) {
        Device device = AdbTools.getInstance().getDevice();
        String productModel = device.getProductModel();
        System.out.println("productModel = " + productModel);
        String netHostName = device.getNetHostName();
        System.out.println("netHostName = " + netHostName);

    }
}
