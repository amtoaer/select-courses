package neu.course.user;

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
    }
}
