package neu.course.course;

public class Course implements Comparable<Course> {
    // 课程编号
    protected int id;
    // 课程名称
    protected String name;
    // 课程种类（是否为选修）
    protected boolean isElective;
    // 任课教师工号
    protected int tid;
    // 选课人数
    protected int count;

    public Course(int id, String name, boolean isElective, int tid, int count) {
        this.id = id;
        this.name = name;
        this.isElective = isElective;
        this.tid = tid;
        this.count = count;
    }

    // 覆盖父类的toString方法用于保存
    @Override
    public String toString() {
        return String.format("%d %s %b %d", this.id, this.name, this.isElective, this.count);
    }

    // 用于展示（与toString的不同是设定了宽度，使用了制表符）
    public String show() {
        String type = isElective ? "选修" : "必修";
        return String.format("%-6d\t\t%-10s\t\t%-6s\t%-4d\t", this.id, this.name, type, this.count);
    }

    // 覆盖实现比较方法，用于调用List.sort()
    @Override
    public int compareTo(Course toCompare) {
        return toCompare.count - this.count;
    }

    public int getID() {
        return this.id;
    }
}