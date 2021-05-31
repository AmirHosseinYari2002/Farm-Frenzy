import java.util.Random;

public class Bear extends WildAnimal{
    public Bear(int x, int y) {
        this.cageLevel = 0;
        this.price = 400;
        this.X = x;
        this.Y = y;
        this.cageLevelRequired = 4;
        this.OccupiedSpace = 15;
        this.name = "Bear";
    }
}
