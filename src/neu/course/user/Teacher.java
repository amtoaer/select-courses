package neu.course.user;

import java.util.Scanner;

// 教师类继承用户类
public class Teacher extends User {
    // 工号
    private int workID;
    // 职称
    private String level;

    public Teacher(String name, String pass, int workID, String level) {
        super(name, pass);
        this.workID = workID;
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format("%8d %10s %10s", this.workID, this.name, this.level);
    }

    @Override
    public boolean login() {
        System.out.println("请输入工号：");
        Scanner input = new Scanner(System.in);
        int workID = input.nextInt();
        System.out.println("请输入密码：");
        String pass = input.next();
        input.close();
        if (workID == this.workID && pass.equals(this.pass)) {
            return true;
        }
        return false;
    }
}
