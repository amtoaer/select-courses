package neu.course.user;

import java.util.Scanner;

// User为抽象类
public abstract class User {
    // 用户名
    protected String name;
    // 密码
    protected String pass;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    // 设置用户密码
    public void setPassword() {
        Scanner input = new Scanner(System.in);
        String first, last;
        do {
            System.out.println("请输入新密码（16位以内）：");
            first = input.next();
            System.out.println("请再次输入密码：");
            last = input.next();
        } while (!first.equals(last) || first.length() > 16);
        input.close();
        this.pass = first;
    }

    // 登陆
    public abstract boolean login();
}
