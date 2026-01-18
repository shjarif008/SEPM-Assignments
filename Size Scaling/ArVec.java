import java.util.ArrayList;
import java.util.Vector;
import java.lang.reflect.Field;

public class GrowthProof {
    public static void main(String[] args) throws Exception {
        
        ArrayList<String> al = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) al.add("A");
        
        System.out.println("ArrayList initial: " + getCap(al));
        al.add("Grow");
        System.out.println("ArrayList after growth: " + getCap(al));

        System.out.println("---");

        Vector<String> v = new Vector<>(10);
        for (int i = 0; i < 10; i++) v.add("V");
        
        System.out.println("Vector initial: " + v.capacity());
        v.add("Grow");
        System.out.println("Vector after growth: " + v.capacity());
    }

    static int getCap(ArrayList<?> l) throws Exception {
        Field f = ArrayList.class.getDeclaredField("elementData");
        f.setAccessible(true);
        return ((Object[]) f.get(l)).length;
    }
}
