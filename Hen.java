public class Hen extends DomesticAnimal{
    public Hen(int x, int y) {
        this.price = 100;
        this.ManufacturedProduct = "egg";
        this.remainingLife = 100;
        this.timeRequiredToProduct = 2;
        this.name = "Hen";
        this.OccupiedSpace = 1;
        this.X = x;
        this.Y = y;
    }

    @Override
    Product outProduct(TIME startDisappearTime) {
        return new Product(this.ManufacturedProduct,15,1,4,startDisappearTime,Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
    }
}
