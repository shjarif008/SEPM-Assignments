class FavoritePizza {
    private static FavoritePizza instance;
    private String topping = "Cheese";

    private FavoritePizza() {}

    public static FavoritePizza getInstance() {
        if (instance == null) {
            instance = new FavoritePizza();
        }
        return instance;
    }
}
