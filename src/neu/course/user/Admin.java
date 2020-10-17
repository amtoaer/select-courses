package neu.course.user;

import java.util.Scanner;

public class Admin extends User {
    public Admin(String name, String pass) {
        super(name, pass);
    }

    @Override
    public boolean login() {
        System.out.println("请输入管理员密码：");
        Scanner input = new Scanner(System.in);
        String pass = input.next();
        input.close();
        if (pass.equals(this.pass)) {
            return true;
        }
        return false;
    }
}
