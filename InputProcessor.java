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
    private void processStartingWorkshop(String[] split){
        String manageError = manager.startingWorkshop(split[1]);
        if (manageError.equals("a")){
            System.err.println("You do not have this workshop on your farm !");
        }else if (manageError.equals("b")){
            System.err.println("You do not have the raw materials to produce the product !");
        }else
            System.out.println(ANSI_CYAN+manageError.substring(0,manageError.length()-2) + " start working, your product will be ready by" + manageError.substring(manageError.length()-2) + " TIME.");
    }
    private void processCage(String[] split){
        int manageError = manager.cage(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
        if (manageError == -1)
            System.err.println("There is no wild animal in this place !");
        else
            System.out.println(ANSI_GREEN+"Cage level increased\nnew cage level : "+manageError+ANSI_YELLOW+"\nWARNING! You must use the cage command in the next time units to be completely imprisoned, otherwise the level of the cage will decrease.");
    }
    private void processGoingForwardTime(String[] split){}
    private void processLoadingProducts(String[] split){
        String manageError = manager.loadingProducts(split[2],Integer.parseInt(split[3]));
        if (manageError.equals("loaded"))
            System.out.println(ANSI_BLUE+"Loaded successfully");
        else if (manageError.equals("notEnoughSpace"))
            System.err.println("Product space is more than car empty space !");
        else if (manageError.equals("notEnoughProduct"))
            System.err.println("This amount of product is not available in Barn !");
        else if (manageError.equals("notInBarn"))
            System.err.println("This product is not in Barn !");
        else if (manageError.equals("Traveling"))
            System.err.println("The car is transporting products to the city.\n"+ANSI_BLUE+"Car returns "+ (Car.getInstance().getTransferTime()-manager.checkTrip())+" time unit");
    }
    private void processUnloadingProduct(String[] split){
        String manageError = manager.unLoadingProducts(split[2]);
        if (manageError.equals("unLoaded"))
            System.out.println(ANSI_YELLOW+"Unloaded successfully");
        else if (manageError.equals("notEnoughSpace"))
            System.err.println("The Barn does not have enough empty space So you can not unloaded this product.");
        else if (manageError.equals("Invalid"))
            System.err.println("This product has not been loaded before...");
        else if (manageError.equals("Traveling"))
            System.err.println("The car is transporting products to the city.\n"+ANSI_BLUE+"Car returns "+ (Car.getInstance().getTransferTime()-manager.checkTrip())+" time unit\"");
    }
    private void processStartTrip(){
        manager.startTrip();
        System.out.println(ANSI_CYAN+"The car started transporting products to the city.");
    }
    private void processBuildWorkshop(String[] split){
        String manageError = manager.buyWorkshop(split[1]);
        if (manageError.equals("have")){
            System.err.println("You have bought this workshop before...");
        }else if (manageError.equals("coins")){
            System.err.println("Sorry! You don't have enough coins");
        }else if (manageError.equals("Invalid")){
            System.err.println("Invalid Input !");
        }else {
            String[] word = manageError.split("\\s+");
            System.out.println(ANSI_BLUE + "Great! you bought a " + manageError + "\nnow you can create "+word[1]+" from "+word[0]);
        }
    }
    private void processUpgradeWorkshop(String[] split){
        String manageError = manager.upgradeWorkshop(split[2]);
        if (manageError.equals("error")){
            System.err.println("ERROR! workshop not found!...");
        }else if (manageError.equals("coins")){
            System.err.println("Sorry! you don't have enough coin");
        }else
            System.out.println(ANSI_PURPLE+"Perfect! "+manageError+" upgraded to level 2.");
    }

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
                }else if (input.matches("^(?i)(truck\\s+load)\\s+(\\w+)\\s+(\\d+)\\s*$")){
                    processLoadingProducts(input.split("\\s+"));
                }else if (input.matches("^(?i)(truck\\s+unload)\\s+(\\w+)\\s*$")){
                    processUnloadingProduct(input.split("\\s+"));
                }else if (input.matches("^(?i)(truck\\s+go)\\s*$")){
                    processStartTrip();
                }else if (input.matches("^(?i)(Build)\\s+(\\w+)\\s*$")){
                    processBuildWorkshop(input.split("\\s+"));
                }else if (input.matches("^(?i)(Upgrade\\s+Workshop)\\s+(\\w+)\\s*$")){
                    processUpgradeWorkshop(input.split("\\s+"));
                }else System.err.println("Invalid Input !");
                manager.upgradeGame();
            }
        }
    }

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
}
