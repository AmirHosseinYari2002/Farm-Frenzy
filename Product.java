public class Product {
    private String name;
    private int sellingPrice;
    private int barnSpace;
    private TIME disappearTime;
    private int X;
    private int Y;

    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public int getBarnSpace() {
        return barnSpace;
    }
    public String getName() {
        return name;
    }

    public Product(String name, int sellingPrice, int barnSpace, TIME disappearTime, int X, int Y) {
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.barnSpace = barnSpace;
        this.disappearTime = disappearTime;
        this.X = X;
        this.Y = Y;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Product))
            return false;
        Product product = (Product) o;
        return product.name.equals(this.name);
    }
}
