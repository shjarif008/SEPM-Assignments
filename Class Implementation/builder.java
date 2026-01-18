class PizzaBuilder {
    private String topping;

    public PizzaBuilder setTopping(String topping) {
        this.topping = topping;
        return this;
    }

    public Pizza build() {
        return new Pizza(topping);
    }
}
