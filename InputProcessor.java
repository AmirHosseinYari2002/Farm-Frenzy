import java.util.Scanner;

public class InputProcessor {
    private Manager manager;
    private Scanner scanner = new Scanner(System.in);
    public InputProcessor(Manager manager) {
        this.manager = manager;
    }


    private void processBuyAnimal(String[] split){
        String manageError = manager.buyAnimal(split[1]);
        if (manageError.equals("Coins")){
            System.err.println("Sorry! You don't have enough coins");
        }else if (manageError.equals("ERROR")){
            System.err.println("Invalid Input!");
        }else System.out.println(ANSI_PURPLE+"The purchase was successful.\nYou bought "+manageError);
    }
    private void processPickupProduct(String[] split){
        String manageError = manager.pickupProduct(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
        if (manageError.equals("wrongLocation")){
            System.err.println("ERROR! The selected location is incorrect.");
        }else if (manageError.equals("barnSpace")){
            System.err.println("You do not have enough space in the Barn !");
        }else System.out.println(ANSI_YELLOW+manageError+" was transferred to Barn");
    }
    private void processFillWaterBucket(){
        String manageError = manager.fillWaterBucket();
        if (manageError.equals("haveWater"))
            System.err.println("The bucket still has water. So you can not take water from the well");
        else if (manageError.equals("filled"))
            System.out.println(ANSI_GREEN+"The bucket of water was filled");
    }
    private void processPlanting(String[] split){
        if (manager.planting(Integer.parseInt(split[1]),Integer.parseInt(split[2])) == 1)
            System.err.println("Invalid Input");
        else if (manager.planting(Integer.parseInt(split[1]),Integer.parseInt(split[2])) == 2)
            System.err.println("There is grass in these location !");
        else if (manager.planting(Integer.parseInt(split[1]),Integer.parseInt(split[2])) == 3)
            System.out.println(ANSI_CYAN+"Grass was planted");
    }
    private void processStartingWorkshop(String[] split){}
    private void processCage(String[] split){}
    private void processGoingForwardTime(String[] split){}
    private void processLoadingProducts(String[] split){}
    private void processUnloadingProduct(String[] split){}
    private void processStartTrip(){}

    public void run(){
        String input;
        boolean logout = false;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("exit")){
            while (!input.equalsIgnoreCase("logout")){
                if (input.matches("^(?i)buy\\s+(\\w+)\\s*$")){
                    processBuyAnimal(input.split("\\s+"));
                }else if (input.matches("^(?i)pickup\\s+(\\d\\s+\\d)\\s*$")){
                    processPickupProduct(input.split("\\s+"));
                }else if (input.matches("^(?i)well\\s*$")){
                    processFillWaterBucket();
                }else if (input.matches("^(?i)plant\\s+(\\d\\s+\\d)\\s*$")){
                    processPlanting(input.split("\\s+"));
                }else if (input.matches("^(?i)work\\s+(\\w+)\\s*$")){
                    processStartingWorkshop(input.split("\\s+"));
                }else if (input.matches("^(?i)cage\\s+(\\d\\s+\\d)\\s*$")){
                    processCage(input.split("\\s+"));
                }else if (input.matches("^(?i)turn\\s+(\\d+)\\s*$")){
                    processGoingForwardTime(input.split("\\s+"));
                }else if (input.matches("^(?i)(truck\\s+load)\\s+(\\w+)\\s*$")){
                    processLoadingProducts(input.split("\\s+"));
                }else if (input.matches("^(?i)(truck\\s+unload)\\s+(\\w+)\\s*$")){
                    processUnloadingProduct(input.split("\\s+"));
                }else if (input.matches("^(?i)(truck\\s+go)\\s*$")){
                    processStartTrip();
                }else System.err.println("Invalid Input !");
            }
        }
    }

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
}
