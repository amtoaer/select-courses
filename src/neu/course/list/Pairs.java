package neu.course.list;

import neu.course.course.OptionalCourse;
import neu.course.relation.Pair;
import java.io.FileWriter;
import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

// 用于存放全部的选课关系（类似数据库中间表）
public class Pairs {
    // 学生选课
    private static List<Pair> selectList = new ArrayList<>();
    // 老师教课
    private static List<Pair> teachList = new ArrayList<>();

    // 用于选修课的选课
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

    // 用于必修课的选课
    public static void selectRequiredCourse(int uid, int cid) {
        selectList.add(new Pair(uid, cid));
    }

    // 添加教师授课的关系
    public static void teachCourse(int tid, int cid) {
        teachList.add(new Pair(tid, cid));
    }

    // 移除教师授课的关系（在修改授课教师、删除教师、删除课程时使用）
    public static void removeTeachCourse(int tid, int cid) {
        teachList.remove(new Pair(tid, cid));
    }

    // 移除教师对某个课程授课的关系
    public static void removeTeachCourse(int cid) {
        teachList.removeIf(i -> i.getLast() == cid);
    }

    // 移除学生选课的关系（在删除学生、删除课程时使用）
    public static void removeSelectCourse(int uid, int cid) {
        selectList.remove(new Pair(uid, cid));
    }

    // 展示学生选修的所有课程
    public static void showSelectedCourses(int uid) {
        System.out.printf("%-6s%-20s%-6s%-15s%-8s%-8s\n", "编号", "课程", "类型", "教师工号", "选课人数", "课程学分/最大选课人数");
        for (var item : selectList) {
            if (item.getFirst() == uid) {
                System.out.println(Courses.locateCourse(item.getLast()).show());
            }
        }
    }

    // 展示教师教授的所有课程
    public static void showTaughtCourses(int tid) {
        System.out.printf("%-6s%-20s%-6s%-15s%-8s%-8s\n", "编号", "课程", "类型", "教师工号", "选课人数", "课程学分/最大选课人数");
        for (var item : teachList) {
            if (item.getFirst() == tid) {
                System.out.println(Courses.locateCourse(item.getLast()).show());
            }
        }
    }

    // 删除教师教授的所有课程和授课关系
    public static void deleteTaughtCourses(int tid) {
        for (var item : teachList) {
            if (item.getFirst() == tid) {
                // 删除课程和学生对这门课的选课记录
                Courses.deleteCourse(Courses.locateCourse(item.getLast()));
            }
        }
        // 删除教课关系
        selectList.removeIf(i -> i.getFirst() == tid);
    }

    // 删除学生的所有选课记录并撤销选课（删除学生时使用）
    public static void deleteSelectCourses(int uid) {
        for (var item : selectList) {
            if (item.getFirst() == uid) {
                Courses.locateCourse(item.getLast()).unselect();
            }
        }
        selectList.removeIf(i -> i.getFirst() == uid);
    }

    // 展示选修某门课的学生列表
    public static void showChosenStudents(int tid) {
        for (var item : teachList) {
            if (item.getFirst() == tid) {
                int cid = item.getLast();
                System.out.println(Courses.locateCourse(cid).getName());
                for (var innerItem : selectList) {
                    if (cid == innerItem.getLast()) {
                        System.out.println(Users.locateStudent(innerItem.getFirst()).show());
                    }
                }
            }
        }
    }

    // 将关系保存到文件
    public static void save() {
        try {
            File select = new File("/home/amtoaer/.config/select-courses/Relation/select");
            File teach = new File("/home/amtoaer/.config/select-courses/Relation/teach");
            select.getParentFile().mkdirs();
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

    // 从文件读取关系
    public static void read() {
        try {
            File select = new File("/home/amtoaer/.config/select-courses/Relation/select");
            File teach = new File("/home/amtoaer/.config/select-courses/Relation/teach");
            var sel = new Scanner(select);
            var tea = new Scanner(teach);
            while (sel.hasNext()) {
                int first = sel.nextInt();
                int last = sel.nextInt();
                selectList.add(new Pair(first, last));
            }
            sel.close();
            while (tea.hasNext()) {
                int first = tea.nextInt();
                int last = tea.nextInt();
                teachList.add(new Pair(first, last));
            }
            tea.close();
        } catch (Exception e) {
            System.out.println("从文件读取关系失败");
        }
    }
}
