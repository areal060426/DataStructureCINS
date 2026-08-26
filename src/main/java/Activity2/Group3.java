package Activity2;
//The program should create a linked list of student names.
import java.util.LinkedList;

public class Group3 {
    public static void main(String[] args) {

        LinkedList<String> students = new LinkedList<>();

        students.add("Ana");
        students.add("Ben");
        students.add("Carlo");

        students.remove(2);

        System.out.println(students);
    }
}
