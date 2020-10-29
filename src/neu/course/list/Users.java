package neu.course.list;

import neu.course.user.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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
            innerAddTeacher();
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
        teachers.add(new Teacher(name, "123456", id, grade));
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

    // 登陆菜单
    public static void showMenu() {
        System.out.println("""
                请输入您要登陆的用户种类：
                1. 管理员
                2. 教师
                3. 学生""");
        do {
            int choice = stdIn.nextInt();
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
