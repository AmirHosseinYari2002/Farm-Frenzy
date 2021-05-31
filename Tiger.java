import java.util.Random;

public class Tiger extends WildAnimal{
    public Tiger(int x, int y) {
        this.cageLevel = 0;
        this.price = 500;
        this.X = x;
        this.Y = y;
        this.cageLevelRequired = 4;
        this.OccupiedSpace = 15;
        this.name = "Tiger";
    }

}
