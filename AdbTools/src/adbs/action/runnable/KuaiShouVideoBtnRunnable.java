package adbs.action.runnable;

import adbs.action.model.InOutputModel;
import adbs.action.runnable.abs.PyImgFinderCloseRunnable;

public class KuaiShouVideoBtnRunnable extends PyImgFinderCloseRunnable {

    public KuaiShouVideoBtnRunnable(InOutputModel inOutputModel) {
        setInOutputModel(inOutputModel);
    }

    @Override
    protected void setMsg() {
        msg = "快手刷视频线程";
    }

    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\KuaiShou\\Video\\video.py";
    }
}
