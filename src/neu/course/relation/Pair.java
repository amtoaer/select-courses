package neu.course.relation;

// 用于存放关系（中间表的一行）
// 对于学生选课，first->学号，last->课程号
// 对于教师教课，first->教师号，last->课程号
public class Pair {
    int first;
    int last;

    public Pair(int first, int last) {
        this.first = first;
        this.last = last;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    // 用于输出到文件
    @Override
    public String toString() {
        return String.format("%d %d\n", this.first, this.last);
    }

    // 用于判断是否已经选修过这门课
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair tmp = (Pair) obj;
            if (this.first == tmp.first && this.last == tmp.last) {
                return true;
            }
            return false;
        }
        return false;
    }
}