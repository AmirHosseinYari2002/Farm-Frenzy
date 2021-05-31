import java.util.Random;

public class Hound extends Animal {
    Random random = new Random();
    public Hound(int x, int y) {
        this.price = 100;
        this.X = x;
        this.Y = y;
        this.name = "Hound";
    }

}
