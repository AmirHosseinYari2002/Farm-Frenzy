public class Player {
    private String name;
    private String password;
    private int level;
    private int coins;

    public Player(String name, String password, int level, int coins) {
        this.name = name;
        this.level = level;
        this.coins = coins;
        this.password = password;
    }

    public int getCoins() {
        return coins;
    }
    public String getName() {
        return name;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }
    public String getPassword() {
        return password;
    }
}
