import java.util.*;

public class PQStructures {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        PriorityQueue<Integer> stack = new PriorityQueue<>(Collections.reverseOrder());

        int[] vals = {10, 20, 30};
        for (int v : vals) {
            queue.add(v);
            stack.add(v);
        }

        System.out.println("PQ Queue: " + queue.poll());
        System.out.println("PQ Stack: " + stack.poll());
    }
}
