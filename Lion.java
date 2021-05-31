import java.util.Random;

public class Lion extends WildAnimal{
    public Lion(int x, int y) {
        this.cageLevel = 0;
        this.price = 300;
        this.X = x;
        this.Y = y;
        this.cageLevelRequired = 3;
        this.OccupiedSpace = 15;
        this.name = "Lion";
    }
}
