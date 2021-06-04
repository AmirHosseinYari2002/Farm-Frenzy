import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameHandler {
    static private Player player;
    static Scanner scanner = new Scanner(System.in);
    public static Authentication authenticator = new Authentication();
    private static File logInstance = new File("src\\log.txt");
    public static File getInstance(){
        if (logInstance.length() == 0){
            FileManager.addToFile(logInstance,"File creation time : "+new Date().toString());
            FileManager.addToFile(logInstance,"Time of the last change in the file : "+new Date());
            FileManager.addToFile(logInstance,"---------------------------------------------------------------");
        }
        return logInstance;
    }
    public void startGame(){
        System.out.println(InputProcessor.ANSI_CYAN+"        **************************************************");
        System.out.println("        ******      "+InputProcessor.ANSI_YELLOW+"Farm Frenzy-SUT Edition"+InputProcessor.ANSI_CYAN+"       ********");
        System.out.println(InputProcessor.ANSI_CYAN+"        **************************************************\n");
        System.out.println(InputProcessor.ANSI_PURPLE+"Welcome to Farm Frenzy-SUT Edition");
        int answer = 0;
        while (answer != 1 && answer != 2){
            System.out.println(InputProcessor.ANSI_BLUE+"  1- Sign In    2- Sign up");
            System.out.print(InputProcessor.ANSI_GREEN+"Choose option (1/2): ");
            answer = scanner.nextInt();
            switch (answer) {
                case 1 -> player = authenticator.signIn();
                case 2 -> player = authenticator.signUp();
                default -> {
                    System.err.println("Invalid input...");
                    FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"Invalid input");
                }
            }
            FileManager.addToFile(GameHandler.getInstance(),"(user : "+player.getName()+" )");
        }
        System.out.println(InputProcessor.ANSI_WHITE+"-----------------------------------------------");
        startMenu();

    }
    public static void startLevel(){
        System.out.println("Player: "+InputProcessor.ANSI_CYAN+player.getName());
        System.out.println(InputProcessor.ANSI_PURPLE+"Levels: ");
        for (int i = 0; i < player.getLevel(); i++) {
            System.out.println("level "+(i+1));
        }
        System.out.print(InputProcessor.ANSI_WHITE+"Enter number of level you want to play:");
        int levelNum = scanner.nextInt();
        while (levelNum < 1 || levelNum > player.getLevel()){
            System.err.println("Sorry! you can't play this level...");
            System.out.println();
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"Select unopened level");
            System.out.print(InputProcessor.ANSI_WHITE+"Enter level number you want to play:");
            levelNum = scanner.nextInt();
        }
        FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"start level");
        System.out.println(InputProcessor.ANSI_YELLOW+"Loading Level "+levelNum);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Levels level = new Levels(levelNum);
        System.out.println(InputProcessor.ANSI_CYAN+"Level "+levelNum+" tasks:");
        for (Map.Entry<String, Integer> entry : level.tasks.entrySet()) {
            System.out.println(InputProcessor.ANSI_YELLOW+entry.getKey() + ":" + entry.getValue().toString());
        }
        System.out.println(InputProcessor.ANSI_CYAN+"--------------------------------------------------");
        Manager manager = new Manager(level,player);
        InputProcessor inputProcessor = new InputProcessor(manager);
        inputProcessor.run();
        System.out.println(InputProcessor.ANSI_GREEN+"congratulations! you finished this level.");
        FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"finished level"+levelNum);
        System.out.println("Level status: Completed - "+level.status);
        int levelPrize = switch (level.status) {
            case "golden" -> level.goldenGiftCoin;
            case "silver" -> level.silverGiftCoin;
            case "bronze" -> level.bronzeGiftCoin;
            default -> 0;
        };
        player.setCoins(player.getCoins()+levelPrize);
        player.setLevel(player.getLevel()+1);
    }
    public static void saveGame(){
        FileManager.remove(authenticator.getUsers(), player.getName());
        String input = player.getName()+","+player.getPassword()+","+player.getLevel()+","+player.getCoins();
        FileManager.addToFile(authenticator.getUsers(),input);
    }
    public static void help(){
        System.out.println(InputProcessor.ANSI_WHITE+"Game Commands:");
        System.out.println("+-----------------------------+------------------------------------------------+");
        System.out.println(InputProcessor.ANSI_PURPLE+"| 1-BUY <animal_name>         | "+InputProcessor.ANSI_YELLOW+"buying domestic animals, cat and hound         |\n" +
                InputProcessor.ANSI_PURPLE+"| 2-BUILD <workshop_name>     | "+InputProcessor.ANSI_YELLOW+"building a workshop                            |\n" +
                InputProcessor.ANSI_PURPLE+"| 3-PICKUP <x y>              | "+InputProcessor.ANSI_YELLOW+"picking up products on the ground              |\n" +
                InputProcessor.ANSI_PURPLE+"| 4-WELL                      | "+InputProcessor.ANSI_YELLOW+"Filling water bucket                           |\n" +
                InputProcessor.ANSI_PURPLE+"| 5-PLANT <x y>               | "+InputProcessor.ANSI_YELLOW+"planting grass on position x y                 |\n" +
                InputProcessor.ANSI_PURPLE+"| 6-WORK <workshop_name>      | "+InputProcessor.ANSI_YELLOW+"start working a workshop                       |\n" +
                InputProcessor.ANSI_PURPLE+"| 7-CAGE <x y>                | "+InputProcessor.ANSI_YELLOW+"trapping and putting wild animals in cage      |\n" +
                InputProcessor.ANSI_PURPLE+"| 8-TURN <n>                  | "+InputProcessor.ANSI_YELLOW+"going time forward for n time unit             |\n" +
                InputProcessor.ANSI_PURPLE+"| 9-TRUCK LOAD <item_name>    | "+InputProcessor.ANSI_YELLOW+"Loading products to the truck                  |\n" +
                InputProcessor.ANSI_PURPLE+"| 10-TRUCK UNLOAD <item_name> | "+InputProcessor.ANSI_YELLOW+"Unloading products from the truck              |\n" +
                InputProcessor.ANSI_PURPLE+"| 11-TRUCK GO                 | "+InputProcessor.ANSI_YELLOW+"The truck will start traveling                 |");
        System.out.println(InputProcessor.ANSI_WHITE+"+-----------------------------+------------------------------------------------+");
    }
    public static void startMenu(){
        int answer = 0;
        System.out.println(InputProcessor.ANSI_BLUE+"<<<<<<    Main Menu    >>>>>>>");
        System.out.println(InputProcessor.ANSI_CYAN+"  1- Levels");
        System.out.println("  2- Help");
        System.out.println("  3- Log out");
        System.out.println("  4- Exit");
        System.out.print(InputProcessor.ANSI_GREEN+"Select your desired option: ");
        answer = scanner.nextInt();
        switch (answer) {
            case 1 -> {
                startLevel();
                startMenu();
            }
            case 2 -> {
                help();
                System.out.println(InputProcessor.ANSI_WHITE + "Do you want to return? (1-yes  2-no)");
                int response = scanner.nextInt();
                while (response != 1)
                    response = scanner.nextInt();
                startMenu();
            }
            case 3 -> {
                Main.main(null);
            }
            case 4 -> {
                saveGame();
                FileManager.addToFile(GameHandler.getInstance(), new Date().toString() + " [" + Log.INFO + "] " + "save game");
                System.out.println(InputProcessor.ANSI_BLUE + "        ***********************************************");
                System.out.println("        ***   Thanks for choosing us, Good bye.     ***");
                System.out.println("        ***   Created and Designed by :             ***");
                System.out.println("        ***             Amir Hossein Yari           ***");
                System.out.println("        ***       Mohammad Hossein Shafizadegan     ***");
                System.out.println("        ***********************************************");
                FileManager.replace(GameHandler.getInstance(), "Time of the last change in the file : ", ("Time of the last change in the file : " + new Date()));
            }
        }
    }
}
