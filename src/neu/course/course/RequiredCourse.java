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
}
