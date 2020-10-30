package neu.course.list;

import neu.course.course.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import neu.course.user.Student;

public class Courses {
    // 选修课、必修课列表
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

    // 输入课程编号并定位到课程
    public static Course locateCourse() {
        System.out.println("请输入课程编号：");
        int id = stdIn.nextInt();
        return locateCourse(id);
    }

    // 通过参数中的课程编号定位到课程
    public static Course locateCourse(int cid) {
        for (var item : list) {
            if (item.getID() == cid) {
                return item;
            }
        }
        return null;
    }

    // 分别显示必修课和选修课
    public static void show() {
        showRequiredCourses();
        showOptionalCourses();
    }

    // 为某位同学选上所有的必修课（在添加新学生时使用）
    public static void selectCourse(Student s) {
        for (var item : list) {
            if (item instanceof RequiredCourse) {
                // 添加选课关系
                Pairs.selectCourse(s.getID(), item.getID());
            }
        }
    }

    // 格式化输出必修课列表
    public static void showRequiredCourses() {
        System.out.println("必修课");
        System.out.printf("%-6s%-10s%-6s%-4s%-4s%-4s\n", "编号", "课程", "类型", "教师工号", "选课人数", "课程学分");
        for (Course item : list) {
            if (item instanceof RequiredCourse) {
                System.out.println(item.show());
            }
        }
    }

    // 格式化输出选修课列表
    public static void showOptionalCourses() {
        System.out.println("选修课");
        System.out.printf("%-6s%-10s%-6s%-4s%-4s%-4s\n", "编号", "课程", "类型", "教师工号", "选课人数", "最大选课人数");
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

    // 内部的课程添加函数，从Scanner中读取数据并添加课程
    private static void innerAddCourse(Scanner input) {
        String name;
        boolean isElective;
        int count, maxCount, tid, score, id;
        // 从标准输入中读取时才需要输出提示信息
        if (stdIn.equals(input)) {
            System.out.println("请输入课程编号：");
            id = input.nextInt();
            System.out.println("请输入课程名：");
            name = input.next();
            System.out.println("请输入课程是否为选修（true/false）：");
            isElective = input.nextBoolean();
            while (true) {
                System.out.println("请输入任课教师工号：");
                tid = input.nextInt();
                if (Users.locateTeacher(tid) == null) {
                    System.out.println("任课教师不存在，请重新输入");
                } else {
                    break;
                }
            }
            if (isElective) {
                System.out.println("请输入最大选课人数：");
                maxCount = input.nextInt();
                // 添加授课关系
                Pairs.teachCourse(tid, id);
                // 对于选修课，选课人数应初始化为0
                list.add(new OptionalCourse(id, name, isElective, tid, 0, maxCount));
            } else {
                System.out.println("请输入必修课学分：");
                score = input.nextInt();
                // 添加授课关系
                Pairs.teachCourse(tid, id);
                // 选课人数初始化为0
                RequiredCourse rc = new RequiredCourse(id, name, isElective, tid, 0, score);
                // 为所有同学选上这门必修课
                Users.selectCourse(rc);
                list.add(rc);
            }
        } else {
            // 否则不输出提示信息，直接进行读取
            id = input.nextInt();
            name = input.next();
            isElective = input.nextBoolean();
            tid = input.nextInt();
            count = input.nextInt();
            if (isElective) {
                maxCount = input.nextInt();
                list.add(new OptionalCourse(id, name, isElective, tid, count, maxCount));
            } else {
                score = input.nextInt();
                list.add(new RequiredCourse(id, name, isElective, tid, count, score));
            }
        }
    }

    // 课程列表保存
    public static void save() {
        try {
            File course = new File("/home/amtoaer/.config/select-courses/Courses/course");
            // 创建缺失的父路径
            course.getParentFile().mkdirs();
            var writer = new FileWriter(course);
            for (var item : list) {
                writer.write(item.toString());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("保存课程到文件失败");
        }
    }

    // 课程列表读取
    public static void read() {
        try {
            File course = new File("/home/amtoaer/.config/select-courses/Courses/course");
            Scanner input = new Scanner(course);
            while (input.hasNext()) {
                innerAddCourse(input);
            }
            input.close();
        } catch (Exception e) {
            System.out.println("从文件读取课程失败");
        }
    }
}
