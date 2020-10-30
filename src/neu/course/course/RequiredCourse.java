package neu.course.course;

public class RequiredCourse extends Course {
    // 学分属性
    private int score;

    public RequiredCourse(int id, String name, boolean isElective, int tid, int count, int score) {
        super(id, name, isElective, tid, count);
        this.score = score;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %d\n", this.score);
    }

    // 返回用于展示的格式化后的字符串
    @Override
    public String show() {
        return super.show() + String.format("%-4d", this.score);
    }

    // 必修课选课只需要给选课人数+1即可
    @Override
    public boolean select() {
        this.count++;
        return true;
    }

    // 在添加课程时使用，为所有的学生选上这门课
    public boolean select(int studentCount) {
        this.count += studentCount;
        return true;
    }
}
