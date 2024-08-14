package adbs.main.run.act;

import java.util.Objects;

/**
 * 从一个Activity进入另一个Activity
 * enterFromOneActivityToAnother
 * FromActivityToActivity
 * Activity到Activity
 */
public class ActToAct {
    private String previous;
    private String now;

    public ActToAct(String previous, String now) {
        this.previous = previous;
        this.now = now;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ActToAct actToAct = (ActToAct) o;
        return Objects.equals(previous, actToAct.previous) && Objects.equals(now, actToAct.now);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previous, now);
    }
}
