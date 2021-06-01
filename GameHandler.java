import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameHandler {
    static private Player player;
    Scanner scanner = new Scanner(System.in);
    public static Authentication authenticator = new Authentication();
    public void startGame(){
        System.out.println("        **************************************************");
        System.out.println("        ******      Farm Frenzy-SUT Edition       ********");
        System.out.println("        **************************************************\n");
        System.out.println("Welcome to Farm Frenzy-SUT Edition");
        int answer = 0;
        while (answer != 1 && answer != 2){
            System.out.println("  1- Sign In    2- Sign up");
            System.out.println("Choose option (1/2): ");
            answer = scanner.nextInt();
            switch (answer) {
                case 1 -> player = authenticator.signIn();
                case 2 -> player = authenticator.signUp();
                default -> System.out.println("Invalid input...");
            }
        }
        while (!scanner.nextLine().equals("exit")){
            System.out.println("Player: "+player.getName());
            System.out.println("If you want to exit Enter 'exit'");
            System.out.println("Levels: ");
            for (int i = 0; i < player.getLevel(); i++) {
                System.out.println("level "+i);
            }
            System.out.println("Enter number of level you want to play:");
            int level = scanner.nextInt();
            while (level < 1 || level > player.getLevel()){
                System.out.println("Sorry! you can't play this level...");
                System.out.println("Enter level number you want to play:");
                level = scanner.nextInt();
            }
            player.setCoins(player.getCoins()+startLevel(level));
            player.setLevel(player.getLevel()+1);
        }
        saveGame();
        System.out.println("        ***********************************************");
        System.out.println("        ***   Thanks for choosing us, Good bye.     ***");
        System.out.println("        ***   Created and Designed by :             ***");
        System.out.println("        ***             Amir Hossein Yari           ***");
        System.out.println("        ***       Mohammad Hossein Shafizadegan     ***");
        System.out.println("        ***********************************************");
    }

    public static int startLevel(int levelNum){
        System.out.println("Loading Level "+levelNum);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Levels level = new Levels(levelNum);
        System.out.println("Level "+levelNum+" tasks:");
        for (Map.Entry<String, Integer> entry : level.tasks.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }
        System.out.println("--------------------------------------------------");
        Manager manager = new Manager(level,player);
        InputProcessor inputProcessor = new InputProcessor(manager);
        inputProcessor.run();
        System.out.println("congratulations! you finished this level.");
        System.out.println("Level status: Completed - "+level.status);
        int levelPrize = switch (level.status) {
            case "golden" -> level.goldenGiftCoin;
            case "silver" -> level.silverGiftCoin;
            case "bronze" -> level.bronzeGiftCoin;
            default -> 0;
        };
        return levelPrize;
    }

    public static void saveGame(){
        FileManager.remove(authenticator.getUsers(), player.getName());
        String input = player.getName()+","+player.getPassword()+","+player.getLevel()+","+player.getCoins();
        FileManager.addToFile(authenticator.getUsers(),input);
    }
}
