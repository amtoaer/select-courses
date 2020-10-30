package neu.course.user;

import neu.course.list.Pairs;

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
    public String show() {
        return String.format("%8d %10s %10s", this.workID, this.name, this.level);
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s", this.workID, this.name, this.pass, this.level);
    }

    @Override
    public void showHelloMessage() {
        System.out.printf("%s %s 你好\n", this.name, this.level);
    }

    // 获取教师工号
    public int getWorkID() {
        return workID;
    }

    @Override
    public void showMenu() {
        while (true) {
            System.out.println("""
                    1. 修改登陆密码
                    2. 查看教授课程
                    3. 查看所授课程的学生列表
                    4. 退出""");
            int choice = stdIn.nextInt();
            switch (choice) {
                case 1 -> this.setPassword();
                case 2 -> {
                    Pairs.showTaughtCourses(this.workID);
                }
                case 3 -> {
                    Pairs.showChosenStudents(this.workID);
                }
                case 4 -> {
                    return;
                }
            }
        }
    }
}
