import java.util.*;

public class LinkedListEqual {
    public static void main(String[] args) {
        LinkedList<String> list1 = new LinkedList<>(Arrays.asList("A", "B", "C"));
        LinkedList<String> list2 = new LinkedList<>(Arrays.asList("A", "B", "C"));

        boolean isEqual = list1.equals(list2);
        System.out.println("Lists are equal: " + isEqual);
    }
}
