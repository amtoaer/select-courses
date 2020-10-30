package neu.course.list;

import neu.course.course.OptionalCourse;
import neu.course.relation.Pair;
import java.io.FileWriter;
import java.util.List;
import java.io.File;
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
        if (Courses.locateCourse(cid) instanceof OptionalCourse) {
            OptionalCourse oc = (OptionalCourse) Courses.locateCourse(cid);
            if (oc.select()) {
                selectList.add(tmp);
                System.out.println("选课成功！");
            } else {
                System.out.println("这门课选课人数已满！");
            }
        } else {
            System.out.println("这门课是必修课！");
        }
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

    public static void showChosenStudents(int tid) {
        for (var item : teachList) {
            if (item.getFirst() == tid) {
                int cid = item.getLast();
                for (var innerItem : selectList) {
                    if (cid == innerItem.getLast()) {
                        System.out.println(Users.locateStudent(innerItem.getFirst()).show());
                    }
                }
            }
        }
    }

    public static void save() {
        try {
            File select = new File("/home/amtoaer/.config/select-courses/Relation/select");
            File teach = new File("/home/amtoaer/.config/select-courses/Relation/teach");
            select.mkdirs();
            var sel = new FileWriter(select);
            var tea = new FileWriter(teach);
            for (var item : selectList) {
                sel.write(item.toString());
            }
            sel.close();
            for (var item : teachList) {
                tea.write(item.toString());
            }
            tea.close();
        } catch (Exception e) {
            System.out.println("保存关系到文件失败");
        }
    }
}
