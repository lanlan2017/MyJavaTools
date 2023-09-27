package adbs.main.ui.jpanels.auto;

/**
 * 屏幕坐标比例
 */
public class ScreenPositionRatio {
    /**
     * 横坐标的比例
     */
    double xRatio;
    /**
     * 纵坐标的比率
     */
    double yRatio;

    // private static ScreenPositionRatio instance = new ScreenPositionRatio();

    public ScreenPositionRatio() {
    }

    public ScreenPositionRatio(double xRatio, double yRatio) {
        this.xRatio = xRatio;
        this.yRatio = yRatio;
    }

    public double getxRatio() {
        return xRatio;
    }

    public double getyRatio() {
        return yRatio;
    }

    public void setxRatio(double xRatio) {
        this.xRatio = xRatio;
    }

    public void setyRatio(double yRatio) {
        this.yRatio = yRatio;
    }

    public void setter(double xRatio, double yRatio) {
        this.xRatio = xRatio;
        this.yRatio = yRatio;
    }

    @Override
    public String toString() {
        // return "ScreenPositionRatio{" + "xRatio=" + xRatio + ", yRatio=" + yRatio + '}';
        String newCode = "new ScreenPositionRatio (" + xRatio + "," + yRatio + ")";
        String setterCode = "";
        return newCode + setterCode;
    }
}
