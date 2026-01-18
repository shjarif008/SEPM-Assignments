import java.util.*;

public class KthSmallest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(7, 10, 4, 3, 20, 15));
        int k = 3;
        Collections.sort(list);
        System.out.println(k + "rd smallest: " + list.get(k - 1));
    }
}
