package neu.course.user;

public class Admin extends User {
    public Admin(String name, String pass) {
        super(name, pass);
    }

    @Override
    public void showHelloMessage() {
        System.out.printf("管理员%s，你好\n", this.name);
    }

    // 管理员只有一个，没有展示的需要，因此此处为空函数
    @Override
    public String show() {
        return "";
    }

    // TODO: 管理员菜单
    @Override
    public void showMenu() {
    }
}
