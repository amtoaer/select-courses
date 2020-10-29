package neu.course.user;

import java.util.Scanner;

import neu.course.list.Courses;
import neu.course.list.Pairs;

// 学生类继承用户类
public class Student extends User {
    // 学号
    private int id;
    // 年级
    private String grade;

    public Student(String name, String pass, int id, String grade) {
        super(name, pass);
        this.id = id;
        this.grade = grade;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s", this.id, this.name, this.grade);
    }

    @Override
    public String show() {
        return String.format("%8d %10s %10s", this.id, this.name, this.grade);
    }

    @Override
    public void showHelloMessage() {
        System.out.printf("%s %s 你好\n", this.grade, this.name);
    }

    // TODO: 学生菜单
    @Override
    public void showMenu() {
        while (true) {
            System.out.println("""
                    1. 修改登陆密码
                    2. 查看所上课程
                    3. 选修课选课
                    4. 退出""");
            int choice = stdIn.nextInt();
            switch (choice) {
                case 1 -> this.resetPassword();
                case 2 -> {
                    Pairs.showSelectedCourses(this.id);
                }
                case 3 -> {
                    Courses.showSelectCourses();
                    System.out.println("请输入课程编号：");
                    int cid = stdIn.nextInt();
                    Pairs.selectCourse(this.id, cid);
                }
                case 4 -> {
                    return;
                }
            }
        }

    }
}
