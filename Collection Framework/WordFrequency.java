import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String text = "hello world hello java world hello";
        String[] words = text.split(" ");
        TreeMap<String, Integer> map = new TreeMap<>();

        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        System.out.println(map);
    }
}
