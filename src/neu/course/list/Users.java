package neu.course.list;

import neu.course.course.RequiredCourse;
import neu.course.user.*;
import java.util.Scanner;
import java.util.List;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;

public class Users {
    // 管理员帐号固定
    private static User admin = new Admin("admin", "amtoaer!");
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Scanner stdIn = new Scanner(System.in);

    private static User currentUser = null;

    public static void showStudents() {
        System.out.printf("%8s\t%10s\t%10s\n", "学号", "姓名", "年级");
        for (Student item : students) {
            System.out.println(item.show());
        }
    }

    public static void showTeachers() {
        System.out.printf("%8s\t%10s\t%10s\n", "工号", "姓名", "职称");
        for (Teacher item : teachers) {
            System.out.println(item.show());
        }
    }

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
            e.printStackTrace();
        }
    }

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

    public static void innerAddTeacher() {
        System.out.println("请输入工号：");
        int id = stdIn.nextInt();
        System.out.println("请输入姓名：");
        String name = stdIn.next();
        System.out.println("请输入职称：");
        String level = stdIn.next();
        teachers.add(new Teacher(name, "123456", id, level));
    }

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

    public static void innerAddStudent() {
        System.out.println("请输入学号：");
        int id = stdIn.nextInt();
        System.out.println("请输入姓名：");
        String name = stdIn.next();
        System.out.println("请输入年级：");
        String grade = stdIn.next();
        students.add(new Student(name, "123456", id, grade));
    }

    // 为所有学生选上这门必修课
    public static void selectCourse(RequiredCourse rc) {
        rc.select(students.size());
        for (var item : students) {
            Pairs.selectRequiredCourse(item.getID(), rc.getID());
        }
    }

    public static User locateStudent() {
        System.out.println("请输入学号：");
        int id = stdIn.nextInt();
        for (Student item : students) {
            if (item.getID() == id) {
                return item;
            }
        }
        System.out.println("学号不存在，请重新输入");
        return null;
    }

    public static User locateStudent(int uid) {
        for (Student item : students) {
            if (item.getID() == uid) {
                return item;
            }
        }
        return null;
    }

    public static void deleteTeachers() {
        String choice = "y";
        while (choice.equals("y")) {
            User t = locateTeacher();
            teachers.remove(t);
            System.out.println("是否继续？");
            choice = stdIn.next();
        }
    }

    public static void deleteStudents() {
        String choice = "y";
        while (choice.equals("y")) {
            User t = locateTeacher();
            teachers.remove(t);
            System.out.println("是否继续？");
            choice = stdIn.next();
        }
    }

    public static User locateTeacher() {
        System.out.println("请输入工号：");
        int workID = stdIn.nextInt();
        for (Teacher item : teachers) {
            if (item.getWorkID() == workID) {
                return item;
            }
        }
        System.out.println("工号不存在，请重新输入");
        return null;
    }

    public static User locateTeacher(int tid) {
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
        System.out.println("""
                请输入您要登陆的用户种类：
                1. 管理员
                2. 教师
                3. 学生""");
        int choice = stdIn.nextInt();
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
            admin.showHelloMessage();
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
