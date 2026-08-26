package Activity2;
//The stack should remove the most recently added item.
//Group 4: Vhenz Santuele, Roel Ernest Fernandes, Justine Lagrada, Daryl Paña 
//Lira Linatoc, Clare Margaret De Leon, Glayza Maxinne Dimailig, Angelica Estores
import java.util.Stack;

public class Group4 {
    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();

        stack.push("Book");
        stack.push("Notebook");
        stack.push("Pen");

        stack.pop();

        System.out.println(stack);
    }
}