public class Buffalo extends DomesticAnimal{
    public Buffalo(int x, int y) {
        this.price = 400;
        this.ManufacturedProduct = "milk";
        this.remainingLife = 100;
        this.timeRequiredToProduct = 5;
        this.name = "Buffalo";
        this.OccupiedSpace = 4;
        this.X = x;
        this.Y = y;
    }

    @Override
    Product outProduct(TIME startDisappearTime) {
        return new Product(this.ManufacturedProduct,25,1,4,startDisappearTime,Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
    }
}
