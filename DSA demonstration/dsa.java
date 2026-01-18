import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        LinkedList<Integer> list = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        
        int[] data = {30, 10, 20};

        for (int x : data) {
            list.add(x);
            pq.add(x);
            deque.add(x);
        }

        System.out.println("LinkedList: " + list); 
        System.out.println("PriorityQueue: " + pq.poll()); 
        System.out.println("Deque: " + deque.peekLast());

        HashMap<Integer, String> map = new HashMap<>();
        TreeMap<Integer, String> tree = new TreeMap<>();
        HashSet<Integer> set = new HashSet<>();

        map.put(3, "C"); map.put(1, "A"); map.put(2, "B");
        tree.put(3, "C"); tree.put(1, "A"); tree.put(2, "B");
        set.add(10); set.add(10); set.add(20);

        System.out.println("HashMap: " + map);
        System.out.println("TreeMap: " + tree);
        System.out.println("HashSet: " + set);
    }
}
