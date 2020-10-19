package neu.course.user;

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
    public String show() {
        return String.format("%d %s %s", this.workID, this.name, this.level);
    }

    @Override
    public void showHelloMessage() {
        System.out.printf("%s %s 你好\n", this.name, this.level);
    }

    public boolean login(int workID, String pass) {
        if (this.workID == workID && this.pass.equals(pass)) {
            return true;
        }
        return false;
    }

    // TODO: 教师菜单
    @Override
    public void showMenu() {
    }
}
