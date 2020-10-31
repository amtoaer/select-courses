package neu.course.list;

import neu.course.course.RequiredCourse;
import neu.course.user.*;
import java.util.Scanner;
import java.util.List;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import neu.course.course.Course;

public class Users {
    // 管理员帐号固定
    private static User admin = new Admin("admin", "amtoaer!");
    // 教师列表
    private static List<Teacher> teachers = new ArrayList<>();
    // 学生列表
    private static List<Student> students = new ArrayList<>();
    // 类内的全局输入
    private static Scanner stdIn = new Scanner(System.in);

    // 当前登陆的用户
    private static User currentUser = null;

    // 展示所有学生
    public static void showStudents() {
        System.out.printf("%8s\t%10s\t%10s\n", "学号", "姓名", "年级");
        for (Student item : students) {
            System.out.println(item.show());
        }
    }

    // 展示所有教师
    public static void showTeachers() {
        System.out.printf("%8s\t%10s\t%10s\n", "工号", "姓名", "职称");
        for (Teacher item : teachers) {
            System.out.println(item.show());
        }
    }

    // 将用户保存到文件
    public static void save() {
        try {
            var studentFile = new File("/home/amtoaer/.config/select-courses/User/student");
            var teacherFile = new File("/home/amtoaer/.config/select-courses/User/teacher");
            // 创建需要的所有父目录
            studentFile.getParentFile().mkdirs();
            // 使用FileWriter写入所有数据（文件不存在自动创建）
            var stu = new FileWriter(studentFile);
            var tea = new FileWriter(teacherFile);
            for (var item : students) {
                stu.write(item.toString());
            }
            stu.close();
            for (var item : teachers) {
                tea.write(item.toString());
            }
            tea.close();
        } catch (Exception e) {
            System.out.println("保存用户信息到文件失败");
        }
    }

    // 从文件读取用户
    public static void read() {
        try {
            var studentFile = new File("/home/amtoaer/.config/select-courses/User/student");
            var teacherFile = new File("/home/amtoaer/.config/select-courses/User/teacher");

            var stu = new Scanner(studentFile);
            var tea = new Scanner(teacherFile);
            while (stu.hasNext()) {
                int id = stu.nextInt();
                String name = stu.next();
                String pass = stu.next();
                String grade = stu.next();
                students.add(new Student(name, pass, id, grade));
            }
            stu.close();
            while (tea.hasNext()) {
                int workID = tea.nextInt();
                String name = tea.next();
                String pass = tea.next();
                String level = tea.next();
                teachers.add(new Teacher(name, pass, workID, level));
            }
            tea.close();
        } catch (Exception e) {
            System.out.println("从文件读取用户信息失败");
        }
    }

    // 添加多个教师
    public static void addTeachers() {
        int i = 1;
        String choice = "n";
        do {
            System.out.printf("请输入第%d个教师信息\n", i++);
            innerAddTeacher();
            System.out.println("是否继续？（y/n）");
            choice = stdIn.next();
        } while ("y".equals(choice));
    }

    // 添加单个教师
    public static void innerAddTeacher() {
        System.out.println("请输入工号：");
        int id = stdIn.nextInt();
        System.out.println("请输入姓名：");
        String name = stdIn.next();
        System.out.println("请输入职称：");
        String level = stdIn.next();
        teachers.add(new Teacher(name, "123456", id, level));
    }

    // 添加多个学生
    public static void addStudents() {
        int i = 1;
        String choice = "n";
        do {
            System.out.printf("请输入第%d个学生信息\n", i++);
            innerAddStudent();
            System.out.println("是否继续？（y/n）");
            choice = stdIn.next();
        } while ("y".equals(choice));
    }

    // 添加单个学生
    public static void innerAddStudent() {
        System.out.println("请输入学号：");
        int id = stdIn.nextInt();
        System.out.println("请输入姓名：");
        String name = stdIn.next();
        System.out.println("请输入年级：");
        String grade = stdIn.next();
        var student = new Student(name, "123456", id, grade);
        Courses.selectCourse(student);
        students.add(student);
    }

    // 为所有学生选上这门必修课（在添加必修课时使用）
    public static void selectCourse(RequiredCourse rc) {
        rc.select(students.size());
        for (var item : students) {
            Pairs.selectRequiredCourse(item.getID(), rc.getID());
        }
    }

    // 为所有学生删除这门课（在删除课程时使用）
    public static void removeCourse(Course c) {
        for (var item : students) {
            Pairs.removeSelectCourse(item.getID(), c.getID());
        }
    }

    // 输入学号并定位学生
    public static Student locateStudent() {
        System.out.println("请输入学号：");
        int id = stdIn.nextInt();
        return locateStudent(id);
    }

    // 通过学号参数定位学生
    public static Student locateStudent(int uid) {
        for (Student item : students) {
            if (item.getID() == uid) {
                return item;
            }
        }
        return null;
    }

    // 删除多个教师
    public static void deleteTeachers() {
        String choice = "y";
        while (choice.equals("y")) {
            Teacher t = locateTeacher();
            if (t == null) {
                System.out.println("未找到对应教师！");
            } else {
                // 删除教师讲授的所有课程和选课关系、授课关系
                Pairs.deleteTaughtCourses(t.getWorkID());
                // 移除这位教师
                teachers.remove(t);
                System.out.println("删除教师成功！");
            }
            System.out.println("是否继续？");
            choice = stdIn.next();
        }
    }

    // 删除多个学生
    public static void deleteStudents() {
        String choice = "y";
        while (choice.equals("y")) {
            Student t = locateStudent();
            if (t == null) {
                System.out.println("未找到对应学生！");
            } else {
                Pairs.deleteSelectCourses(t.getID());
                students.remove(t);
                System.out.println("删除学生成功！");
            }
            System.out.println("是否继续？");
            choice = stdIn.next();
        }
    }

    // 输入工号定位教师
    public static Teacher locateTeacher() {
        System.out.println("请输入工号：");
        int workID = stdIn.nextInt();
        return locateTeacher(workID);
    }

    // 通过工号参数定位教师
    public static Teacher locateTeacher(int tid) {
        for (Teacher item : teachers) {
            if (item.getWorkID() == tid) {
                return item;
            }
        }
        return null;
    }

    // 登陆菜单
    public static void showMenu() {
        // 读入用户、课程、选课记录
        Users.read();
        Courses.read();
        Pairs.read();
        // 输出登陆选项
        System.out.println("""
                请输入您要登陆的用户种类：
                1. 管理员
                2. 教师
                3. 学生""");
        int choice = stdIn.nextInt();
        // 将登陆成功的用户赋值给当前用户（currentUser）
        do {
            currentUser = switch (choice) {
                case 1 -> adminLogin();
                case 2 -> teacherLogin();
                case 3 -> studentLogin();
                default -> {
                    System.out.println("输入错误，请重试");
                    yield null;
                }
            };
        } while (currentUser == null);
        // 打印欢迎信息，进入用户菜单
        currentUser.showHelloMessage();
        currentUser.showMenu();
        // 保存用户、课程、选课记录
        Users.save();
        Courses.save();
        Pairs.save();
    }

    // 管理员登陆
    private static User adminLogin() {
        System.out.println("请输入管理员密码：");
        String pass = stdIn.next();
        if (admin.login(pass)) {
            return admin;
        }
        System.out.println("密码错误，请重试");
        return null;
    }

    // 教师登陆
    private static User teacherLogin() {
        User item = locateTeacher();
        if (item != null) {
            System.out.println("请输入密码：");
            String pass = stdIn.next();
            if (item.login(pass)) {
                return item;
            } else {
                System.out.println("密码错误，请重试");
                return null;
            }
        }
        return null;
    }

    // 学生登陆
    private static User studentLogin() {
        User item = locateStudent();
        if (item != null) {
            System.out.println("请输入密码：");
            String pass = stdIn.next();
            if (item.login(pass)) {
                return item;
            } else {
                System.out.println("密码错误，请重试");
                return null;
            }
        }
        return null;
    }
}
