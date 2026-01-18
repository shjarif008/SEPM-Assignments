import java.util.*;

public class EmployeeDept {
    public static void main(String[] args) {
        HashMap<Integer, String> employees = new HashMap<>();
        employees.put(5001, "Engineering");
        employees.put(5002, "Marketing");
        employees.put(5003, "Human Resources");

        System.out.println("Employee 5001 Dept: " + employees.get(5001));
    }
}
