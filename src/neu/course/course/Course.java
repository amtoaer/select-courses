package neu.course.course;

public class Course {
    // 课程编号
    private int id;
    // 课程名称
    private String name;
    // 课程种类（是否为选修）
    private boolean isElective;

    public Course(int id, String name, boolean isElective) {
        this.id = id;
        this.name = name;
        this.isElective = isElective;
    }

    // 覆盖父类的toString方法用于输出
    @Override
    public String toString() {
        String type = this.isElective ? "选修" : "必修";
        return String.format("%8d %10s %10s", this.id, this.name, type);
    }
}