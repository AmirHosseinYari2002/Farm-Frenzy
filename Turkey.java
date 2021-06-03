public class Turkey extends DomesticAnimal{
    public Turkey(int x, int y) {
        this.price = 200;
        this.ManufacturedProduct = "feather";
        this.remainingLife = 100;
        this.timeRequiredToProduct = 3;
        this.name = "Turkey";
        this.OccupiedSpace = 2;
        this.X = x;
        this.Y = y;
    }

    @Override
    Product outProduct(TIME startDisappearTime) {
        return new Product(this.ManufacturedProduct,20,1,4,startDisappearTime,Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
    }
}
