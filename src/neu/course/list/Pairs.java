package neu.course.list;

import neu.course.relation.Pair;
import java.util.List;
import java.util.ArrayList;

// 用于存放全部的选课关系（类似数据库中间表）
public class Pairs {
    private static List<Pair> selectList = new ArrayList<>();
    private static List<Pair> teachList = new ArrayList<>();

    public static void selectCourse(int uid, int cid) {
        selectList.add(new Pair(uid, cid));
    }

    public static void teachCourse(int tid, int cid) {
        teachList.add(new Pair(tid, cid));
    }

}
