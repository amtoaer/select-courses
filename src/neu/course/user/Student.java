package neu.course.user;

import java.util.Scanner;

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

    @Override
    public String toString() {
        return String.format("%8d %10s %10s", this.id, this.name, this.grade);
    }

    @Override
    public boolean login() {
        System.out.println("请输入学号：");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        System.out.println("请输入密码：");
        String pass = input.next();
        input.close();
        if (id == this.id && pass.equals(this.pass)) {
            System.out.printf("%s %s，你好！", this.grade, this.name);
            return true;
        }
        return false;
    }
}
