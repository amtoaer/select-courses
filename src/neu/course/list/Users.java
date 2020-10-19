package neu.course.list;

import neu.course.user.*;
import java.util.List;
import java.util.ArrayList;

public class Users {
    // 管理员帐号固定
    private static User admin = new Admin("admin", "amtoaer!");
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

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

    // TODO: 登陆菜单
    public static void showMenu() {
    }

    // TODO: 管理员登陆
    public static void adminLogin() {
    }

    // TODO: 教师登陆
    public static void teacherLogin() {
    }

    // TODO: 学生登陆
    public static void studentLogin() {
    }

}
