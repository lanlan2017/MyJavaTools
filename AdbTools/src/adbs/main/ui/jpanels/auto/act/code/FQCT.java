package adbs.main.ui.jpanels.auto.act.code;

import adbs.cmd.AdbCommands;
import adbs.main.ui.jpanels.auto.AdbTap;
import adbs.main.ui.jpanels.auto.CoinsType;
import adbs.main.ui.jpanels.auto.act.ActDo;
import adbs.main.ui.jpanels.auto.act.WeiZhi;
import adbs.model.Device;

public class FQCT implements CoinsType {
    ActDo actDo;
    private boolean yijingShangHua;

    public FQCT(ActDo actDo) {
        this.actDo = actDo;
    }

    private String version;

    /**
     * 番茄畅听金币自动收取功能
     *
     * @param device
     * @param packageName
     * @param actShortName
     */
    public void actFQCT(String coinType, Device device, String packageName, String actShortName) {
        System.out.println("番茄畅听");
        if (version == null) {
            version = AdbCommands.getPackageVersion(device, packageName);
        }
        switch (coinType) {
            case strFQCT:
                switch (version) {
                    case "5.1.3.32":
                        System.out.println("xxxxxxxxxxxxxx");
                        actFQCT_v_5_1_3_32(device, actShortName);
                        break;
                    case "5.0.2.32":
                        actFQCT_v_5_1_3_32(device, actShortName);
                        // actFQCT_v_5_0_2_32(device, actShortName);
                        break;
                    default:
                        System.out.println("番茄畅听v" + version + "未适配");
                        break;

                }
                break;
            default:
                actDo.stop(strFQCT);
                break;

        }

    }


    private void actFQCT_v_5_1_3_32(Device device, String actShortName) {
        if (!yijingShangHua) {
            System.out.println("开始上划");
            // 先小幅度上划6次让听书金币按钮出现
            AdbCommands.swipeBotton2TopOnRightXiaoXiaoDe(device, 5);
            yijingShangHua = true;
        }
        switch (actShortName) {
            case "com.dragon.read.pages.main.MainFragmentActivity":
                // WeiZhi tingShuBtn = new WeiZhi(915, 1517);
                // wait_tap(device, 5, tingShuBtn);
                //
                // WeiZhi closeBtn = new WeiZhi(538, 1638);
                // wait_tap(device, 5, closeBtn);
                System.out.println("主界面");
                // WeiZhi tingShuJinBi = new WeiZhi(918, 1224);
                WeiZhi tingShuJinBi = new WeiZhi(921, 1228);
                // wait_tap(device, 5, tingShuJinBi);
                AdbTap.wait_tap(device, 5, tingShuJinBi);
                // //
                wait_tap_FQCloseBtn(device);
                break;
            default:
                actDo.stop(strFQCT);
                break;
        }
    }


    private void wait_tap_FQCloseBtn(Device device) {
        WeiZhi closeBtn = new WeiZhi(538, 1638);
        // wait_tap(device, 5, closeBtn);
        AdbTap.wait_tap(device, 5, closeBtn);
    }

}
