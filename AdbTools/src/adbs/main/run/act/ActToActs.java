//package adbs.main.run.act;
//
//import java.util.HashSet;
//import java.util.Objects;
//
//public class ActToActs {
//    private String previous;
//    private HashSet<String> now;
//
//    public ActToActs(String previous, HashSet<String> now) {
//        this.previous = previous;
//        this.now = now;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        ActToActs actToActs = (ActToActs) o;
//        return Objects.equals(previous, actToActs.previous);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(previous);
//    }
//}
