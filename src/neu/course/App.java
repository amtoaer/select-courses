package neu.course;

import neu.course.list.Courses;

public class App {
    public static void main(String[] args) throws Exception {
        Courses.addCourses();
        System.out.println("排序前：");
        Courses.showCourses();
        System.out.println("排序后：");
        Courses.sortCourses();
        Courses.showCourses();
    }
}
