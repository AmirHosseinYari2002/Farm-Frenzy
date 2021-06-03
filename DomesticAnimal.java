import java.util.Random;

public abstract class DomesticAnimal extends Animal{
    protected String ManufacturedProduct;
    protected int timeRequiredToProduct;
    protected int remainingLife;
    protected int OccupiedSpace;
    protected TIME startProduceProduct;

    abstract Product outProduct(TIME startDisappearTime);
}
