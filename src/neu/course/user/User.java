package neu.course.user;

import java.util.Scanner;

// User为抽象类
public abstract class User {
    // 用户名
    protected String name;
    // 密码
    protected String pass;

    protected Scanner stdIn = new Scanner(System.in);

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    // 设置用户密码
    public void setPassword() {
        String first, last;
        do {
            System.out.println("请输入新密码（16位以内）：");
            first = stdIn.next();
            System.out.println("请再次输入密码：");
            last = stdIn.next();
        } while (!first.equals(last) || first.length() > 16);
        this.pass = first;
    }

    public void resetPassword() {
        this.pass = "123456";
    }

    // 判断登陆是否成功
    public boolean login(String pass) {
        if (pass.equals(this.pass)) {
            return true;
        }
        return false;
    }

    // 登陆
    public abstract void showHelloMessage();

    // 返回用于输出的字符串
    public abstract String show();

    // 显示特定用户级别的菜单
    public abstract void showMenu();
}
