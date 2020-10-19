package neu.course.course;

public class Course implements Comparable<Course> {
    // 课程编号
    protected int id;
    // 课程名称
    protected String name;
    // 课程种类（是否为选修）
    protected boolean isElective;
    // 任课教师
    protected String teacher;
    // 选课人数
    protected int count;

    public Course(int id, String name, boolean isElective, String teacher, int count) {
        this.id = id;
        this.name = name;
        this.isElective = isElective;
        this.teacher = teacher;
        this.count = count;
    }

    // 覆盖父类的toString方法用于保存
    @Override
    public String toString() {
        return String.format("%d %s %b %s %d", this.id, this.name, this.isElective, this.teacher, this.count);
    }

    public String show() {
        String type = isElective ? "选修" : "必修";
        return String.format("%-6d\t\t%-10s\t%-6s\t%-6s\t%-4d\t", this.id, this.name, type, this.teacher, this.count);
    }

    // 覆盖实现比较方法
    @Override
    public int compareTo(Course toCompare) {
        return toCompare.count - this.count;
    }

    public String getTeacherName() {
        return this.teacher;
    }
}