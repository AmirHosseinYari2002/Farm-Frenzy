import java.util.Date;
import java.util.Map;
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
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"don't have enough coins to buy animal");
        }else if (manageError.equals("ERROR")){
            System.err.println("Invalid Input!");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"Invalid animal name entered");
        }else {
            System.out.println(ANSI_PURPLE + "The purchase was successful.\nYou bought " + manageError);
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"purchase animal was successful");
        }
    }
    private void processPickupProduct(String[] split){
        String manageError = manager.pickupProduct(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
        if (manageError.equals("wrongLocation")){
            System.err.println("ERROR! The selected location is incorrect.");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"wrong  location was selected for pickup product");
        }else if (manageError.equals("barnSpace")){
            System.err.println("You do not have enough space in the Barn !");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"Barn is full and product did not picked up");
        }else{
            System.out.println(ANSI_YELLOW+manageError+" was transferred to Barn");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"product picked up");
        }
    }
    private void processFillWaterBucket(){
        String manageError = manager.fillWaterBucket();
        if (manageError.equals("haveWater")) {
            System.err.println("The bucket still has water. So you can not take water from the well");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"The bucket still has water");
        }
        else if (manageError.equals("filled")) {
            System.out.println(ANSI_GREEN + "The bucket of water was filled");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"The bucket of water was filled");
        }
    }
    private void processPlanting(String[] split){
        int manageError = manager.planting(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
        if (manageError == 1) {
            System.err.println("Invalid Input");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"Wrong location for planting");
        }else if (manageError == 2) {
            System.err.println("There is grass in these location !");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"There is grass in these location");
        }else if (manageError == 3) {
            System.out.println(ANSI_CYAN + "Grass was planted");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Grass was planted");
        }else if (manageError == 4){
            System.err.println("Bucket does not have a  water!");
        }
    }
    private void processStartingWorkshop(String[] split){
        String manageError = manager.startingWorkshop(split[1]);
        if (manageError.equals("a")){
            System.err.println("You do not have this workshop on your farm !");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"do not have this workshop for starting workshop");
        }else if (manageError.equals("b")){
            System.err.println("You do not have the raw materials to produce the product !");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"do not have the raw materials to produce the product");
        }else {
            System.out.println(ANSI_CYAN + manageError.substring(0, manageError.length() - 2) + " start working, your product will be ready by" + manageError.substring(manageError.length() - 2) + " TIME.");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Workshop start working");
        }
    }
    private void processCage(String[] split){
        int manageError = manager.cage(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
        if (manageError == -1) {
            System.err.println("There is no wild animal in this place !");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"The cage order location is incorrect");
        }else {
            System.out.println(ANSI_GREEN + "Cage level increased\nnew cage level : " + manageError + ANSI_YELLOW + "\nWARNING! You must use the cage command in the next time units to be completely imprisoned, otherwise the level of the cage will decrease.");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Cage level increased");
        }
    }
    private void processGoingForwardTime(String[] split){
        for (int i = 0; i < Integer.parseInt(split[1]); i++) {
            updateGame();
            showInformation();
        }
        FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Time go forward"+split[1]+" unit");
    }
    private void processLoadingProducts(String[] split){
        String manageError = manager.loadingProducts(split[2],Integer.parseInt(split[3]));
        if (manageError.equals("loaded")) {
            System.out.println(ANSI_BLUE + "Loaded successfully");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Loaded successfully");
        }else if (manageError.equals("notEnoughSpace")) {
            System.err.println("Product space is more than car empty space !");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"not enough space to load in car");
        }else if (manageError.equals("notEnoughProduct")){
            System.err.println("This amount of product is not available in Barn !");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"not enough product in Barn");
        }else if (manageError.equals("notInBarn")) {
            System.err.println("This product is not in Barn !");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+" product is not in Barn");
        }
        else if (manageError.equals("Traveling")) {
            System.err.println("The car is transporting products to the city.\n" + ANSI_BLUE + "Car returns " + (Car.getInstance().getTransferTime() - manager.checkTrip()) + " time unit");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"The car is traveling");
        }
    }
    private void processUnloadingProduct(String[] split){
        String manageError = manager.unLoadingProducts(split[2]);
        if (manageError.equals("unLoaded")) {
            System.out.println(ANSI_YELLOW + "Unloaded successfully");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Unloaded successfully");
        }else if (manageError.equals("notEnoughSpace")) {
            System.err.println("The Barn does not have enough empty space So you can not unloaded this product.");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"not enough space in Barn");
        }else if (manageError.equals("Invalid")) {
            System.err.println("This product has not been loaded before...");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"product has not been loaded");
        }else if (manageError.equals("Traveling")) {
            System.err.println("The car is transporting products to the city.\n" + ANSI_BLUE + "Car returns " + (Car.getInstance().getTransferTime() - manager.checkTrip()) + " time unit\"");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"The car is traveling");
        }
    }
    private void processStartTrip(){
        manager.startTrip();
        System.out.println(ANSI_CYAN+"The car started transporting products to the city.");
        FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"The car started transporting");
    }
    private void processBuildWorkshop(String[] split){
        String manageError = manager.buyWorkshop(split[1]);
        if (manageError.equals("have")){
            System.err.println("You have bought this workshop before...");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"have bought this workshop");
        }else if (manageError.equals("coins")){
            System.err.println("Sorry! You don't have enough coins");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"don't have enough coins");
        }else if (manageError.equals("Invalid")){
            System.err.println("Invalid Input !");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"Wrong name selected");
        }else {
            String[] word = manageError.split("\\s+");
            System.out.println(ANSI_BLUE + "Great! you bought a " + word[0] + "\nnow you can create "+word[2]+" from "+word[1]);
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"you bought "+word[0]);
        }
    }
    private void processUpgradeWorkshop(String[] split){
        String manageError = manager.upgradeWorkshop(split[2]);
        if (manageError.equals("error")){
            System.err.println("ERROR! workshop not found!...");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"workshop not found!");
        }else if (manageError.equals("coins")){
            System.err.println("Sorry! you don't have enough coin");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"don't have enough coin to upgrade workshop");
        }else {
            System.out.println(ANSI_PURPLE + "Perfect! " + manageError + " upgraded to level 2.");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+manageError + " upgraded");
        }
    }
    private void updateGame(){
        manager.level.time.n++;
        manager.move();
        manager.eatGrass();
        manager.reduceLife();
        String checkWorkshop = manager.checkWorkshops();
        if (!checkWorkshop.equals("notReady")) {
            System.out.println(ANSI_GREEN + checkWorkshop + " producing process finished." + " your product is ready.");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"The product of "+checkWorkshop+" is ready");
        }
        manager.collectProducts();
        manager.destroyDomesticAnimalAndProduct();
        manager.destroyWildAnimal();
        manager.disappearProduct();
        manager.appearWildAnimal();
        manager.isAnimalManufacturedProduct();
        int sellProducts = manager.sellProducts();
        if (sellProducts != -1 && sellProducts != 0)
            System.out.println(ANSI_GREEN+"Products loaded to the truck sold for "+sellProducts);
        String task = manager.checkTasks();
        if (task.equals("coins")){
            System.out.println(ANSI_CYAN+"Good! You complete a task. Your coins reach the desired amount.");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"coins reach the desired amount");
        }else if (!task.equals("null")){
            System.out.println(ANSI_CYAN+"Good! You complete a task. Your "+task+" reach the desired amount.");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+task+" reach the desired amount.");
        }
    }
    private void showInformation(){
        System.out.println(ANSI_BLUE+"Time : "+ manager.level.time.n);
        System.out.println(ANSI_BLUE+"Your Coins : "+manager.player.getCoins());
        System.out.println(ANSI_CYAN+"               Animals\n"+"+++++++++++++++++++++++++++++++++++++++++");
        for (DomesticAnimal domesticAnimal : manager.getDomesticAnimalsList()) {
            System.out.println("|"+domesticAnimal.name+manager.space(10,domesticAnimal.name)+"|"+domesticAnimal.remainingLife+manager.space(5,"10")+"|"+domesticAnimal.X+manager.space(5,"1")+"|"+domesticAnimal.Y+manager.space(5,"1"));
        }
        for (WildAnimal wildAnimal : manager.getWildAnimalsList()) {
            System.out.println("|"+wildAnimal.name+manager.space(10,wildAnimal.name)+"|"+(wildAnimal.cageLevelRequired-wildAnimal.cageLevel)+manager.space(5,"1")+"|"+wildAnimal.X+manager.space(5,"1")+"|"+wildAnimal.Y+manager.space(5,"1"));
        }
        for (Cat cat : manager.getCatsList()) {
            System.out.println("|"+cat.name+manager.space(10,cat.name)+"|"+cat.X+manager.space(5,"1")+"|"+cat.Y+manager.space(5,"1"));
        }
        for (Hound hound : manager.getHoundsList()) {
            System.out.println("|"+hound.name+manager.space(10,hound.name)+"|"+hound.X+manager.space(5,"1")+"|"+hound.Y+manager.space(5,"1"));
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println(ANSI_BLUE+"               Products In Map\n"+"+++++++++++++++++++++++++++++++++++++++++");
        for (Product product : manager.getProductsList()){
            System.out.println("|"+product.getName()+manager.space(10,product.getName())+"|"+product.getX()+manager.space(5,"1")+"|"+product.getY()+manager.space(5,"1"));
        }System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println(ANSI_PURPLE+"               Products In Barn\n"+"+++++++++++++++++++++++++++++++++++++++++");
        for (Map.Entry<Product,Integer> entry : manager.getProductsInBarn().entrySet()) {
            System.out.println("|"+entry.getKey().getName()+manager.space(10,entry.getKey().getName())+"|"+entry.getKey().getX()+manager.space(5,"1")+"|"+entry.getKey().getY()+manager.space(5,"1"));
        }System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println(ANSI_YELLOW+"Tasks -> ");
        for (Map.Entry<String,Integer> entry : manager.level.tasks.entrySet()){
            System.out.println(entry.getKey()+" : "+(manager.level.basicTasks.get(entry.getKey())-entry.getValue())+"/"+manager.level.basicTasks.get(entry.getKey()));
        }
        FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Show information of map");
    }

    public void run(){
        while (!manager.isLevelCompleted()) {
            String input = scanner.nextLine();
            if (input.matches("^(?i)buy\\s+(\\w+)\\s*$")) {
                processBuyAnimal(input.split("\\s+"));
            } else if (input.matches("^(?i)pickup\\s+(\\d\\s+\\d)\\s*$")) {
                processPickupProduct(input.split("\\s+"));
            } else if (input.matches("^(?i)well\\s*$")) {
                processFillWaterBucket();
            } else if (input.matches("^(?i)plant\\s+(\\d\\s+\\d)\\s*$")) {
                processPlanting(input.split("\\s+"));
            } else if (input.matches("^(?i)work\\s+(\\w+)\\s*$")) {
                processStartingWorkshop(input.split("\\s+"));
            } else if (input.matches("^(?i)cage\\s+(\\d\\s+\\d)\\s*$")) {
                processCage(input.split("\\s+"));
            } else if (input.matches("^(?i)turn\\s+(\\d+)\\s*$")) {
                processGoingForwardTime(input.split("\\s+"));
            } else if (input.matches("^(?i)(truck\\s+load)\\s+(\\w+)\\s+(\\d+)\\s*$")) {
                processLoadingProducts(input.split("\\s+"));
            } else if (input.matches("^(?i)(truck\\s+unload)\\s+(\\w+)\\s*$")) {
                processUnloadingProduct(input.split("\\s+"));
            } else if (input.matches("^(?i)(truck\\s+go)\\s*$")) {
                processStartTrip();
            } else if (input.matches("^(?i)(Build)\\s+(\\w+)\\s*$")) {
                processBuildWorkshop(input.split("\\s+"));
            } else if (input.matches("^(?i)(Upgrade\\s+Workshop)\\s+(\\w+)\\s*$")) {
                processUpgradeWorkshop(input.split("\\s+"));
            } else if (input.matches("(?i)(inquiry)")) {
                showInformation();
            } else System.err.println("Invalid Input !");
        }
    }

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";
}
