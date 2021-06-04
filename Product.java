public class Product {
    private String name;
    private int sellingPrice;
    private int barnSpace;
    private int disappearTime;
    private TIME startDisappearTime;
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
    public void setX(int x) {
        X = x;
    }
    public void setY(int y) {
        Y = y;
    }
    public int getSellingPrice() {
        return sellingPrice;
    }
    public int getDisappearTime() {
        return disappearTime;
    }
    public TIME getStartDisappearTime() {
        return startDisappearTime;
    }

    public void setStartDisappearTime(TIME startDisappearTime) {
        this.startDisappearTime = startDisappearTime;
    }

    public Product(String name, int sellingPrice, int barnSpace, int disappearTime, TIME startDisappearTime, int X, int Y) {
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.barnSpace = barnSpace;
        this.disappearTime = disappearTime;
        this.X = X;
        this.Y = Y;
        this.startDisappearTime = startDisappearTime;
    }

    public Product(Product product) {
        this.name = product.name;
        this.sellingPrice = product.sellingPrice;
        this.startDisappearTime = new TIME(product.startDisappearTime);
        this.disappearTime = product.disappearTime;
        this.barnSpace = product.barnSpace;
        this.X = product.X;
        this.Y = product.Y;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Product))
            return false;
        Product product = (Product) o;
        return product.name.equals(this.name);
    }
}
