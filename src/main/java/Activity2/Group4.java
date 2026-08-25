package Activity2;
//The stack should remove the most recently added item.
import java.util.Stack;

public class Group4 {
    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();

        stack.push("Book");
        stack.push("Notebook");
        stack.push("Pen");

        stack.remove(0);

        System.out.println(stack);
    }
}