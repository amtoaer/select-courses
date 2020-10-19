package neu.course.course;

public class OptionalCourse extends Course {
    private int maxStudentCount;

    public OptionalCourse(int id, String name, boolean isElective, String teacher, int count, int maxStudentCount) {
        super(id, name, isElective, teacher, count);
        this.maxStudentCount = maxStudentCount;
    }

    @Override
    public String show() {
        super.show();
        return super.show() + String.format("%-4d", this.maxStudentCount);
    }
}
