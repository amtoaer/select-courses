package neu.course.course;

import java.util.Scanner;

import neu.course.list.Pairs;
import neu.course.list.Users;

public abstract class Course implements Comparable<Course> {
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
        return String.format("%d %s %b %d %d", this.id, this.name, this.isElective, this.tid, this.count);
    }

    // 用于展示（与toString的不同是设定了宽度，使用了制表符）
    public String show() {
        String type = isElective ? "选修" : "必修";
        return String.format("%-6d%-10s%-6s%-4d%-4d", this.id, this.name, type, this.tid, this.count);
    }

    // 覆盖实现比较方法，用于调用List.sort()
    @Override
    public int compareTo(Course toCompare) {
        return toCompare.count - this.count;
    }

    public int getID() {
        return this.id;
    }

    // 选课方法
    public abstract boolean select();

    // 修改授课教师
    public void changeTeacher() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入教师ID:");
        int tid = input.nextInt();
        input.close();
        if (Users.locateTeacher(tid) != null) {
            Pairs.removeTeachCourse(this.tid, this.id);
            Pairs.teachCourse(tid, this.id);
            this.tid = tid;
        } else {
            System.out.println("教师ID不存在！");
        }
    }
}