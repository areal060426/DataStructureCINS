package Activity2;
//The queue should remove the first person who entered.
import java.util.LinkedList;
import java.util.Queue;

public class Group5 {
    public static void main(String[] args) {

        Queue<String> customers = new LinkedList<>();

        customers.add("Customer A");
        customers.add("Customer B");
        customers.add("Customer C");

        customers.remove();

        System.out.println(customers);
    }
}
