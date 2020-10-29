package neu.course.list;

import neu.course.relation.Pair;
import java.util.List;
import java.util.ArrayList;

// 用于存放全部的选课关系（类似数据库中间表）
public class Pairs {
    private static List<Pair> selectList = new ArrayList<>();
    private static List<Pair> teachList = new ArrayList<>();

    public static void selectCourse(int uid, int cid) {
        var tmp = new Pair(uid, cid);
        if (selectList.contains(tmp)) {
            System.out.println("你已经选修过这门课！");
            return;
        }
        selectList.add(tmp);
    }

    public static void teachCourse(int tid, int cid) {
        teachList.add(new Pair(tid, cid));
    }

    public static void showSelectedCourses(int uid) {
        for (var item : selectList) {
            if (item.getFirst() == uid) {
                System.out.println(Courses.locateCourse(item.getLast()).show());
            }
        }
    }

    public static void showTaughtCourses(int tid) {
        for (var item : teachList) {
            if (item.getFirst() == tid) {
                System.out.println(Courses.locateCourse(item.getLast()).show());
            }
        }
    }

    public static void showChosenStudents(int cid) {
        for (var item : selectList) {
            if (item.getLast() == cid) {
                System.out.println(Users.locateStudent(item.getFirst()).show());
            }
        }
    }

}
