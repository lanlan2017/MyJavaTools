package adbs.main.ui.jpanels.time.listener;

public class QuJian {
    private int start;
    private int end;

    public QuJian(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        // return "QuJian{" + "start=" + start + ", end=" + end + '}';
        return start + "," + end;
    }
}
