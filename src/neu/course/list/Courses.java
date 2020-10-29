package neu.course.list;

import neu.course.course.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Courses {
    private static List<Course> list = new ArrayList<>();

    private static Scanner stdIn = new Scanner(System.in);

    // 手动输入，添加多节课
    public static void multiAdd() {
        int i = 1;
        String choice = "n";
        do {
            System.out.printf("请输入第%d门课程信息\n", i++);
            innerAddCourse(stdIn);
            System.out.println("是否继续？（y/n）");
            choice = stdIn.next();
        } while ("y".equals(choice));
    }

    // 删除多节课
    public static void multiDel() {
        int i = 1;
        String choice = "n";
        do {
            System.out.printf("正在删除第%d个课程\n", i++);
            list.remove(locateCourse());
            System.out.println("是否继续？（y/n）");
            choice = stdIn.next();
        } while ("y".equals(choice));
    }

    public static Course locateCourse() {
        System.out.println("请输入课程编号：");
        int id = stdIn.nextInt();
        return locateCourse(id);
    }

    public static Course locateCourse(int cid) {
        for (var item : list) {
            if (item.getID() == cid) {
                return item;
            }
        }
        return null;
    }

    // 格式化输出课程列表
    public static void show() {
        System.out.printf("%-6s\t%-10s\t\t%-6s\t%-6s\t%-4s\t\n", "编号", "课程", "类型", "教师", "选课人数");
        for (Course item : list) {
            System.out.println(item.show());
        }
    }

    // 格式化输出选修课列表
    public static void showSelectCourses() {
        for (Course item : list) {
            if (item instanceof OptionalCourse) {
                System.out.println(item.show());
            }
        }
    }

    // 使用选课人数对课程进行排序
    public static void sort() {
        // Course类实现了Comparable接口，直接使用sort方法进行排序
        list.sort(null);
    }

    // 内部的课程添加函数，从输入流中读取数据并添加课程
    private static void innerAddCourse(Scanner input) {
        int id = list.size() + 1;
        String name;
        boolean isElective;
        int count, maxCount, tid;
        // 从标准输入中读取时才需要输出提示信息
        if (stdIn.equals(input)) {
            System.out.println("请输入课程名：");
            name = input.next();
            System.out.println("请输入课程是否为选修（true/false）：");
            isElective = input.nextBoolean();
            System.out.println("请输入任课教师工号：");
            tid = input.nextInt();
            System.out.println("请输入选课人数：");
            count = input.nextInt();
            if (isElective) {
                System.out.println("请输入最大选课人数：");
                maxCount = input.nextInt();
                list.add(new OptionalCourse(id, name, isElective, tid, count, maxCount));
            } else {
                list.add(new Course(id, name, isElective, tid, count));
            }
        } else {
            // 否则不输出提示信息，直接进行读取
            name = input.next();
            isElective = input.nextBoolean();
            tid = input.nextInt();
            count = input.nextInt();
            if (isElective) {
                maxCount = input.nextInt();
                list.add(new OptionalCourse(id, name, isElective, tid, count, maxCount));
            } else {
                list.add(new Course(id, name, isElective, tid, count));
            }
        }
    }
}
