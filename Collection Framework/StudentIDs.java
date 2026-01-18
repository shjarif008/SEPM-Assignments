import java.util.*;

public class StudentIDs {
    public static void main(String[] args) {
        TreeMap<Integer, String> students = new TreeMap<>();
        students.put(101, "Alice - CS");
        students.put(105, "Bob - Math");
        students.put(102, "Charlie - Bio");

        for (var entry : students.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " Details: " + entry.getValue());
        }
    }
}
