public class Main {
    public static void main(String[] args) {
        Pizza p1 = new Pizza("Mushroom");
        Pizza p2 = new PizzaBuilder().setTopping("Pepperoni").build();
        FavoritePizza p3 = FavoritePizza.getInstance();
        PizzaRecord p4 = new PizzaRecord("Margherita");
    }
}
