package neu.course.user;

import neu.course.list.Courses;

public class Admin extends User {
    public Admin(String name, String pass) {
        super(name, pass);
    }

    @Override
    public void showHelloMessage() {
        System.out.printf("管理员%s，你好\n", this.name);
    }

    // 管理员只有一个，没有展示的需要，因此此处为空函数
    @Override
    public String show() {
        return "";
    }

    // TODO: 管理员菜单
    @Override
    public void showMenu() {
        while (true) {
            System.out.println("""
                    1. 添加课程
                    2. 删除课程
                    3. 按照选课人数排序
                    4. 显示课程清单
                    5. 修改授课老师
                    6. 显示学生列表
                    7. 显示教师列表
                    8. 恢复学生初始密码
                    9. 恢复教师初始密码
                    10. 添加老师
                    11. 添加学生
                    12. 删除老师
                    13. 删除学生""");
            int choice = stdIn.nextInt();
            switch (choice) {
                case 1 -> Courses.multiAdd();
                case 2 -> Courses.multiDel();
                case 3 -> Courses.sort();
                case 4 -> Courses.show();
            }
        }
    }
}
