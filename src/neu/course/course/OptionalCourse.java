package neu.course.course;

public class OptionalCourse extends Course {
    // 最大选课人数
    private int maxStudentCount;

    public OptionalCourse(int id, String name, boolean isElective, int tid, int count, int maxStudentCount) {
        super(id, name, isElective, tid, count);
        this.maxStudentCount = maxStudentCount;
    }

    // 尝试选课，在内部进行选课人数是否达到上限的检验，如果选课成功返回true，否则返回false
    public boolean select() {
        if (this.maxStudentCount > this.count) {
            this.count++;
            // 选课成功
            return true;
        }
        // 选课失败
        return false;
    }

    @Override
    public String show() {
        super.show();
        return super.show() + String.format("%-4d", this.maxStudentCount);
    }
}
