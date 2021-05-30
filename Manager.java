import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class Manager {

    private ArrayList<DomesticAnimal> domesticAnimalsList = new ArrayList<>();
    private ArrayList<WildAnimal> wildAnimalsList = new ArrayList<>();
    private ArrayList<Hound> houndsList = new ArrayList<>();
    private ArrayList<Grass> grassesList = new ArrayList<>();
    private ArrayList<Grass> removeGrassList = new ArrayList<>();
    private ArrayList<Animal> removeAnimalList = new ArrayList<>();
    private ArrayList<Product> productsList = new ArrayList<>();
    private HashMap<Product,Integer> productsInBarn = new HashMap<>();
    private ArrayList<Product> removeProductList = new ArrayList<>();
    private ArrayList<Cat> catsList = new ArrayList<>();
    private ArrayList<WorkShop> workShops = new ArrayList<>();
    private HashMap<Product,Integer> loadedProducts = new HashMap<>();
    private HashMap<Product,Integer> unLoadedProduct = new HashMap<>();


    private Grass shortestDistanceToEatGrass(int x, int y){}//TODO
    private void move(){}//TODO
    private void eatGrass(){
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            for (Grass grass : grassesList) {
                if (grass.getX() == domesticAnimal.X  &&  grass.getY() == domesticAnimal.Y  &&  domesticAnimal.remainingLife <= 50){
                    domesticAnimal.remainingLife = 100;
                    removeGrassList.add(grass);
                }
            }
        }
        for (Grass grass : removeGrassList) {
            if (grassesList.contains(grass)){
                grassesList.remove(grass);
            }
        }
    }
    private void reduceLife(){
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            domesticAnimal.remainingLife -= 10;
            if (domesticAnimal.remainingLife <= 0){
                removeAnimalList.add(domesticAnimal);
            }
        }
        for (Animal animal : removeAnimalList) {
            if (domesticAnimalsList.contains(animal)){
                domesticAnimalsList.remove(animal);
            }
        }
    }
    private void destroyDomesticAnimalAndProduct(){
        for (WildAnimal wildAnimal : wildAnimalsList) {
            for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
                if (wildAnimal.X == domesticAnimal.X  &&  wildAnimal.Y == domesticAnimal.Y){
                    removeAnimalList.add(domesticAnimal);
                }
            }
            for (Product product : productsList) {
                if (product.getX() == wildAnimal.X  &&  product.getY() == wildAnimal.Y){
                    removeProductList.add(product);
                }
            }
        }
        for (Product product : removeProductList) {
            if (productsList.contains(product))
                productsList.remove(product);
        }
        for (Animal animal : removeAnimalList) {
            if (domesticAnimalsList.contains(animal))
                domesticAnimalsList.remove(animal);
        }
    }
    private void destroyWildAnimal(){
        for (Hound hound : houndsList) {
            for (WildAnimal wildAnimal : wildAnimalsList) {
                if (hound.X == wildAnimal.X  &&  hound.Y == wildAnimal.Y){
                    removeAnimalList.add(hound);
                    removeAnimalList.add(wildAnimal);
                }
            }
        }
        for (Animal animal : removeAnimalList) {
            if (houndsList.contains(animal))
                houndsList.remove(animal);
            if (wildAnimalsList.contains(animal))
                wildAnimalsList.remove(animal);
        }

    }
    private void collectProducts(){
        for (Cat cat : catsList) {
            for (Product product : productsList) {
                if (product.getX() == cat.X  &&  product.getY() == cat.Y  &&  Barn.getInstance().getFreeSpace() >= product.getBarnSpace()){
                    productsList.remove(product);
                    if (productsInBarn.containsKey(product))
                        productsInBarn.replace(product,productsInBarn.get(product)+1);
                    else productsInBarn.put(product,1);
                        Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace()-product.getBarnSpace());
                }
            }
        }
    }
    public String buyAnimal(String name){
        switch (name){
            case "Buffalo" -> {
                if (player.coins >= 400) {
                    Buffalo buffalo = new Buffalo();
                    domesticAnimalsList.add(buffalo);
                    return buffalo.name;
                }else return "Coins";
            }
            case "Cat" -> {
                if (player.coins >= 150) {
                    Cat cat = new Cat();
                    catsList.add(cat);
                }else return "Coins";
            }
            case "Hen" -> {
                if (player.coins >= 100) {
                    Hen hen = new Hen();
                    domesticAnimalsList.add(hen);
                }else return "Coins";
            }
            case "Hound" -> {
                if (player.coins >= 100) {
                    Hound hound = new Hound();
                    houndsList.add(hound);
                }else return "Coins";
            }
            case "Turkey" -> {
                if (player.coins >= 200) {
                    Turkey turkey = new Turkey();
                    domesticAnimalsList.add(turkey);
                }else return "Coins";
            }
        }
        return "ERROR";
    }
    public void buyWorkshop(String name){
        for (WorkShop workShop : workShops) {
            if (workShop.name.equals(name)){
                System.out.println("You have bought this workshop before...");
                return;
            }
        }
        switch (name) {
            case "bakery" -> {
                if (level.coins >= Bakery.getInstance().getCost()){
                    workShops.add(Bakery.getInstance());
                    level.coins -= Bakery.getInstance().getCost();
                    System.out.println("Great! you bought a bakery." +
                            "now you can create bread from flour.");
                }
                else
                    System.out.println("Sorry! You don't have enough coins");
            }
            case "iceCreamSelling" -> {
                if (level.coins >= IceCreamSelling.getInstance().getCost()){
                    workShops.add(IceCreamSelling.getInstance());
                    level.coins -= IceCreamSelling.getInstance().getCost();
                    System.out.println("Great! you bought an ice cream selling." +
                            "now you can make ice cream with pocket milk.");
                }
                else
                    System.out.println("Sorry! You don't have enough coins");            }
            case "milkPackaging" -> {
                if (level.coins >= MilkPackaging.getInstance().getCost()){
                    workShops.add(MilkPackaging.getInstance());
                    level.coins -= MilkPackaging.getInstance().getCost();
                    System.out.println("Great! you bought a milk packaging workshop." +
                            " now you can package milk.");
                }
                else
                    System.out.println("Sorry! You don't have enough coins");            }
            case "mill" -> {
                if (level.coins >= Mill.getInstance().getCost()){
                    workShops.add(Mill.getInstance());
                    level.coins -= Mill.getInstance().getCost();
                    System.out.println("Great! you bought a mill." +
                            " now you can make flour with egg.");
                }
                else
                    System.out.println("Sorry! You don't have enough coins");            }
            case "sewing" -> {
                if (level.coins >= SewingWS.getInstance().getCost()){
                    workShops.add(SewingWS.getInstance());
                    level.coins -= SewingWS.getInstance().getCost();
                    System.out.println("Great! you bought a sewing workshop." +
                            " now you can create shirt with cloth.");
                }
                else
                    System.out.println("Sorry! You don't have enough coins");            }
            case "weaving" -> {
                if (level.coins >= WeavingWS.getInstance().getCost()){
                    workShops.add(WeavingWS.getInstance());
                    level.coins -= WeavingWS.getInstance().getCost();
                    System.out.println("Great! you bought a weaving workshop." +
                            " now you can change feather to cloth.");
                }
                else
                    System.out.println("Sorry! You don't have enough coins");            }
        }
    }
    public void upgradeWorkshop(String name){
        for (WorkShop workShop : workShops) {
            if (workShop.name.equals(name)){
                if (level.coins >= workShop.getUpgradeCost()){
                    workShop.upgrading();
                    level.coins -= workShop.getUpgradeCost();
                    System.out.println("Perfect! "+workShop.name+" upgraded to level 2.");
                    return;
                }
                System.out.println("Sorry! you don't have enough coin");
                return;
            }
        }
        System.out.println("ERROR! workshop not found!...");
    }
    public String pickupProduct(int x, int y){
        for (Product product : productsList) {
            if (product.getX() == x  &&  product.getY() == y){
                if (Barn.getInstance().getFreeSpace() >= product.getBarnSpace()) {
                    removeProductList.add(product);
                    if (productsInBarn.containsKey(product))
                        productsInBarn.replace(product,productsInBarn.get(product)+1);
                    else productsInBarn.put(product,1);
                }else return "barnSpace";
            }
        }
        for (Product product : removeProductList) {
            if (productsList.contains(product)) {
                productsList.remove(product);
                return product.getName();
            }
        }
        return "wrongLocation";
    }
    public String fillWaterBucket(){
        if (Well.getInstance().getRemainingWater() > 0)
            return "haveWater";
        else {
            Well.getInstance().setRemainingWater(5);
            return "filled";
        }
    }
    public int planting(int x, int y){
        if (x >= 1  &&  x <= 6  &&  y >= 1  &&  y <= 6){
            for (Grass grass : grassesList) {
                if (grass.getX() == x  &&  grass.getY() == y)
                    return 2;
            }
            Grass grass = new Grass(x,y);
            grassesList.add(grass);
            return 3;
        }else return 1;
    }
    public void startingWorkshop(String name){
        for (WorkShop workShop : workShops) {
            if (workShop.name.equals(name)){
                //if workshop's input product exist in the barn then
                //workShop.setStartTime(Level.time);
                System.out.println(workShop.name+" start working, your product will be ready by" +
                        workShop.productionTime.n+" TIME.");
                return;
            }
        }
    }
    public void checkWorkshops(){
        for (WorkShop workShop : workShops) {
            if (workShop.isProductReady(level.time)){
                Product product = workShop.producing();
                productsList.add(product);
                workShop.setStartTime(new TIME(0));
                System.out.println(workShop.name+" producing process finished." +
                        " your product is ready.");
            }
        }
    }
    public int cage(int x, int y){
        for (WildAnimal wildAnimal : wildAnimalsList) {
            if (wildAnimal.X == x  &&  wildAnimal.Y == y){
                wildAnimal.cageLevel++;
                if (wildAnimal.cageLevel == wildAnimal.cageLevelRequired){
                    wildAnimal.timeToStartIncarceration = level.time;
                }
                return wildAnimal.cageLevel;
            }
        }
        return -1;
    }//TODO decrease cage level
    public void goingForwardTime(int n){

    }
    public String loadingProducts(String name, int amount){
        if (checkTrip() != 0)
            return "Traveling";
        else {
            for (Map.Entry<Product, Integer> entry : productsInBarn.entrySet()) {
                if (entry.getKey().getName().equals(name)) {
                    if (entry.getValue() >= amount) {
                        if (amount * entry.getKey().getBarnSpace() >= Car.getInstance().getEmptySpace()) {
                            productsInBarn.replace(entry.getKey(), entry.getValue() - amount);
                            Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace() + amount * entry.getKey().getBarnSpace());
                            Car.getInstance().setEmptySpace(Car.getInstance().getEmptySpace() - amount * entry.getKey().getBarnSpace());
                            loadedProducts.put(entry.getKey(), amount);
                            return "loaded";
                        } else return "notEnoughSpace";
                    } else return "notEnoughProduct";
                }
            }
        }
        return "notInBarn";
    }
    public String  unLoadingProducts(String name){
        if (checkTrip() != 0)
            return "Traveling";
        else {
            for (Map.Entry<Product, Integer> entry : loadedProducts.entrySet()) {
                if (entry.getKey().getName().equals(name)) {
                    if (productsInBarn.containsKey(entry.getKey())) {
                        if (Barn.getInstance().getFreeSpace() >= entry.getValue() * entry.getKey().getBarnSpace()) {
                            productsInBarn.replace(entry.getKey(), productsInBarn.get(entry.getKey()) + entry.getValue());
                            unLoadedProduct.put(entry.getKey(), entry.getValue());
                            return "unLoaded";
                        } else return "notEnoughSpace";
                    } else {
                        if (Barn.getInstance().getFreeSpace() >= entry.getValue() * entry.getKey().getBarnSpace()) {
                            productsInBarn.put(entry.getKey(), entry.getValue());
                            unLoadedProduct.put(entry.getKey(), entry.getValue());
                            return "unLoaded";
                        } else return "notEnoughSpace";
                    }
                }
            }
            for (Map.Entry<Product, Integer> entry : unLoadedProduct.entrySet()) {
                if (loadedProducts.containsKey(entry.getKey()))
                    loadedProducts.remove(entry.getKey());
            }
        }
        return "Invalid";
    }
    public void startTrip(){
        Car.getInstance().setStartTrip(level.time);
    }
    public int checkTrip(){
        if (!Car.getInstance().IsCarBack(level.time))
            return TIME.diff(level.time,Car.getInstance().getStartTrip());
        else return 0;
    }
    public void upgradeGame(){
        eatGrass();
        reduceLife();
        destroyWildAnimal();
        destroyDomesticAnimalAndProduct();
        collectProducts();
        move();
    }
}

