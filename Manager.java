//import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

public class Manager {
    Levels level;
    Player player;

    static Random random = new Random();
    private ArrayList<DomesticAnimal> domesticAnimalsList = new ArrayList<>();
    private ArrayList<WildAnimal> wildAnimalsList = new ArrayList<>();
    private ArrayList<Hound> houndsList = new ArrayList<>();
    private ArrayList<Grass> grassesList = new ArrayList<>();
    private ArrayList<Grass> removeGrassList = new ArrayList<>();
    private ArrayList<Animal> removeAnimalList = new ArrayList<>();
    private ArrayList<Product> productsList = new ArrayList<>();
    private HashMap<Product,Integer> productsInBarn = new HashMap<>();
    private HashMap<Animal,Integer> animalInBarn = new HashMap<>();
    private ArrayList<Product> removeProductList = new ArrayList<>();
    private ArrayList<Cat> catsList = new ArrayList<>();
    private ArrayList<WorkShop> workShops = new ArrayList<>();
    private HashMap<Product,Integer> loadedProducts = new HashMap<>();
    private HashMap<Product,Integer> unLoadedProduct = new HashMap<>();

    public ArrayList<DomesticAnimal> getDomesticAnimalsList() {
        return domesticAnimalsList;
    }
    public ArrayList<WildAnimal> getWildAnimalsList() {
        return wildAnimalsList;
    }
    public ArrayList<Hound> getHoundsList() {
        return houndsList;
    }
    public ArrayList<Cat> getCatsList() {
        return catsList;
    }
    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public Manager(Levels level, Player player) {
        this.level = level;
        this.player = player;
    }

    private Grass shortestDistanceToEatGrass(int x, int y){
        double  shortestDistance = 1000;
        Grass nearestGrass = new Grass();
        for (Grass grass : grassesList) {
            if (Math.sqrt((grass.getX()-x)*(grass.getX()-x) + (grass.getY()-y)*(grass.getY()-y)) < shortestDistance){
                shortestDistance = Math.sqrt((grass.getX()-x)*(grass.getX()-x) + (grass.getY()-y)*(grass.getY()-y));
                nearestGrass.setX(grass.getX());
                nearestGrass.setY(grass.getY());
            }
        }
        return nearestGrass;
    }
    private Product shortestDistanceToCollectProduct(int x, int y){
        double  shortestDistance = 1000;
        Product nearestProduct = new Product();
        for (Product product : productsList) {
            if (Math.sqrt((product.getX()-x)*(product.getX()-x) + (product.getY()-y)*(product.getY()-y)) < shortestDistance){
                shortestDistance = Math.sqrt((product.getX()-x)*(product.getX()-x) + (product.getY()-y)*(product.getY()-y));
                nearestProduct.setX(product.getX());
                nearestProduct.setY(product.getY());
            }
        }
        return nearestProduct;
    }
    public void move(){
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getX() != domesticAnimal.X  &&  shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getY() != domesticAnimal.Y) {
                if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getX() > domesticAnimal.X) {
                    domesticAnimal.X++;
                } else if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getX() < domesticAnimal.X) {
                    domesticAnimal.X--;
                } else {
                    if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getY() > domesticAnimal.Y) {
                        domesticAnimal.Y++;
                    } else if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getY() < domesticAnimal.Y) {
                        domesticAnimal.X--;
                    }
                }
            }
        }
        for (Cat cat : catsList) {
            if (shortestDistanceToCollectProduct(cat.X,cat.Y).getX() != cat.X  &&  shortestDistanceToCollectProduct(cat.X,cat.Y).getY() != cat.Y) {
                if (shortestDistanceToCollectProduct(cat.X, cat.Y).getX() > cat.X) {
                    cat.X++;
                } else if (shortestDistanceToCollectProduct(cat.X, cat.Y).getX() < cat.X) {
                    cat.X--;
                } else {
                    if (shortestDistanceToCollectProduct(cat.X, cat.Y).getY() > cat.Y) {
                        cat.Y++;
                    } else if (shortestDistanceToCollectProduct(cat.X, cat.Y).getY() < cat.Y) {
                        cat.Y--;
                    }
                }
            }
        }
        for (Hound hound : houndsList) {
            int a = 1;
            if (random.nextInt(2) == 0){
                if (hound.X == 6  ||  hound.X == 1){
                    a = -a;
                }
                hound.X += a;
            }else {
                if (hound.Y == 6  ||  hound.Y == 1){
                    a = -a;
                }
                hound.Y += a;
            }
        }
        for (WildAnimal wildAnimal : wildAnimalsList) {
            int a = 1;
            if (random.nextInt(2) == 0){
                if (wildAnimal.X == 6  ||  wildAnimal.X == 1){
                    a = -a;
                }
                wildAnimal.X += a;
            }else {
                if (wildAnimal.Y == 6  ||  wildAnimal.Y == 1){
                    a = -a;
                }
                wildAnimal.Y += a;
            }
        }
    }
    public void eatGrass(){
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
    public void reduceLife(){
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
    public void destroyDomesticAnimalAndProduct(){
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
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (domesticAnimal.remainingLife == 0)
                removeAnimalList.add(domesticAnimal);
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
    public void destroyWildAnimal(){
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
    public void collectProducts(){
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
                if (player.getCoins() >= 400) {
                    Buffalo buffalo = new Buffalo(random.nextInt(6)+1,random.nextInt(6)+1);
                    domesticAnimalsList.add(buffalo);
                    return buffalo.name;
                }else return "Coins";
            }
            case "Cat" -> {
                if (player.getCoins() >= 150) {
                    Cat cat = new Cat(random.nextInt(6)+1,random.nextInt(6)+1);
                    catsList.add(cat);
                }else return "Coins";
            }
            case "Hen" -> {
                if (player.getCoins() >= 100) {
                    Hen hen = new Hen(random.nextInt(6)+1,random.nextInt(6)+1);
                    domesticAnimalsList.add(hen);
                }else return "Coins";
            }
            case "Hound" -> {
                if (player.getCoins() >= 100) {
                    Hound hound = new Hound(random.nextInt(6)+1,random.nextInt(6)+1);
                    houndsList.add(hound);
                }else return "Coins";
            }
            case "Turkey" -> {
                if (player.getCoins() >= 200) {
                    Turkey turkey = new Turkey(random.nextInt(6)+1,random.nextInt(6)+1);
                    domesticAnimalsList.add(turkey);
                }else return "Coins";
            }
        }
        return "ERROR";
    }
    public String buyWorkshop(String name){
        for (WorkShop workShop : workShops) {
            if (workShop.name.equals(name)){
                return "have";
            }
        }
        StringBuilder stringBuilder = new StringBuilder("");
        switch (name) {
            case "bakery" -> {
                if (level.coins >= Bakery.getInstance().getCost()){
                    workShops.add(Bakery.getInstance());
                    level.coins -= Bakery.getInstance().getCost();
                    stringBuilder.append(Bakery.getInstance().input).append(" ").append(Bakery.getInstance().output);
                }
                else
                    return "coins";
            }
            case "iceCreamSelling" -> {
                if (level.coins >= IceCreamSelling.getInstance().getCost()){
                    workShops.add(IceCreamSelling.getInstance());
                    level.coins -= IceCreamSelling.getInstance().getCost();
                    stringBuilder.append(IceCreamSelling.getInstance().input).append(" ").append(IceCreamSelling.getInstance().output);
                }
                else
                    return "coins";
            }
            case "milkPackaging" -> {
                if (level.coins >= MilkPackaging.getInstance().getCost()){
                    workShops.add(MilkPackaging.getInstance());
                    level.coins -= MilkPackaging.getInstance().getCost();
                    stringBuilder.append(MilkPackaging.getInstance().input).append(" ").append(MilkPackaging.getInstance().output);
                }
                else
                    return "coins";
            }
            case "mill" -> {
                if (level.coins >= Mill.getInstance().getCost()){
                    workShops.add(Mill.getInstance());
                    level.coins -= Mill.getInstance().getCost();
                    stringBuilder.append(Mill.getInstance().input).append(" ").append(Mill.getInstance().output);
                }
                else
                    return "coins";
            }
            case "sewing" -> {
                if (level.coins >= SewingWS.getInstance().getCost()){
                    workShops.add(SewingWS.getInstance());
                    level.coins -= SewingWS.getInstance().getCost();
                    stringBuilder.append(SewingWS.getInstance().input).append(" ").append(SewingWS.getInstance().output);
                }
                else
                    return "coins";
            }
            case "weaving" -> {
                if (level.coins >= WeavingWS.getInstance().getCost()){
                    workShops.add(WeavingWS.getInstance());
                    level.coins -= WeavingWS.getInstance().getCost();
                    stringBuilder.append(WeavingWS.getInstance().input).append(" ").append(WeavingWS.getInstance().output);
                }
                else
                    return "coins";
            }
        }
        if (stringBuilder.length() != 0)
            return stringBuilder.toString();
        else return "Invalid";
    }
    public String upgradeWorkshop(String name){
        for (WorkShop workShop : workShops) {
            if (workShop.name.equals(name)){
                if (level.coins >= workShop.getUpgradeCost()){
                    workShop.upgrading();
                    level.coins -= workShop.getUpgradeCost();
                    return workShop.name;
                }
                return "coins";
            }
        }
        return "error";
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
    public String startingWorkshop(String name){
        for (WorkShop workShop : workShops) {
            if (workShop.name.equals(name)){
                if (productsInBarn.get(workShop.input) > workShop.level) {
                    if (productsInBarn.get(workShop.input)-workShop.level == 0) {
                        productsInBarn.remove(workShop.input);
                    }else productsInBarn.replace(workShop.input,productsInBarn.get(workShop.input)-workShop.level);
                    workShop.setStartTime(level.time);
                    return workShop.name.concat(String.valueOf(workShop.productionTime));
                }
                return "b";
            }
        }
        return "a";
    }
    public String checkWorkshops(){
        for (WorkShop workShop : workShops) {
            if (workShop.isProductReady(level.time)){
                Product product = workShop.producing();
                productsList.add(product);
                workShop.setStartTime(new TIME(0));
                return workShop.name;
            }
        }
        return "notReady";
    }
    public int cage(int x, int y){
        for (WildAnimal wildAnimal : wildAnimalsList) {
            if (wildAnimal.X == x  &&  wildAnimal.Y == y){
                wildAnimal.cageLevel++;
                if (wildAnimal.cageLevel == wildAnimal.cageLevelRequired){
                    if (Barn.getInstance().getFreeSpace() >= wildAnimal.OccupiedSpace){
                        animalInBarn.put(wildAnimal,1);
                        Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace() - wildAnimal.OccupiedSpace);
                    }
                    removeAnimalList.add(wildAnimal);
                }
                return wildAnimal.cageLevel;
            }
        }
        for (Animal animal : removeAnimalList) {
            if (wildAnimalsList.contains(animal)){
                wildAnimalsList.remove(animal);
            }
        }
        return -1;
    }//TODO
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
    public String checkTasks(){
        if (level.tasks.containsKey("Coin")){
            if (level.coins == level.tasks.get("Coin")){
                level.tasks.remove("Coin");
                return "coins";
            }
        }
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (level.tasks.containsKey(domesticAnimal.name)){
                int animalNum = level.tasks.get(domesticAnimal.name) - 1;
                if (animalNum == 0) {
                    level.tasks.remove(domesticAnimal.name);
                    return domesticAnimal.name;
                }
                level.tasks.replace(domesticAnimal.name,animalNum);
            }
        }
        for (Map.Entry<Product,Integer> entry : productsInBarn.entrySet()){
            if (level.tasks.containsKey(entry.getKey().getName())){
                int productNum = level.tasks.get(entry.getKey().getName())-1;
                if (productNum == 0) {
                    level.tasks.remove(entry.getKey().getName());
                    return entry.getKey().getName();
                }
                level.tasks.replace(entry.getKey().getName(),productNum);
            }
        }
        return null;
    }
    public boolean isLevelCompleted(){
        if (level.tasks.isEmpty()){
            level.finishTime = new TIME(level.time);
            if (level.finishTime.n <= level.goldenFinishTime.n){
                level.status = "Golden";
            }
            else if (level.finishTime.n <= level.silverFinishTime.n){
                level.status = "Silver";
            }
            else{
                level.status = "Bronze";
            }
            return true;
        }
        return false;
    }
    public String space(int n,String word){
        StringBuilder stringBuilder =new StringBuilder("");
        for (int i = 0; i < n-word.length(); i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}