//import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.*;
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
    private ArrayList<Animal> animalsCalculatedInTask = new ArrayList<>();
    private HashSet<Product> productsCalculatedInTask = new HashSet<>();

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
    public HashMap<Product, Integer> getProductsInBarn() {
        return productsInBarn;
    }
    public ArrayList<Grass> getGrassesList() {
        return grassesList;
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
            if (grassesList.size() > 0) {
                if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getX() > domesticAnimal.X) {
                    domesticAnimal.X++;
                } else if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getX() < domesticAnimal.X) {
                    domesticAnimal.X--;
                } else {
                    if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getY() > domesticAnimal.Y) {
                        domesticAnimal.Y++;
                    } else if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getY() < domesticAnimal.Y) {
                        domesticAnimal.Y--;
                    }
                }
            }else {
                int a = 1;
                if (random.nextInt(2) == 0){
                    if (domesticAnimal.X == 6){
                        a = -a;
                    }
                    domesticAnimal.X += a;
                }else {
                    if (domesticAnimal.Y == 6){
                        a = -a;
                    }
                    domesticAnimal.Y += a;
                }
            }
        }
        for (Cat cat : catsList) {
            if (productsList.size() > 0) {
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
            }else {
                int a = 1;
                if (random.nextInt(2) == 0){
                    if (cat.X == 6){
                        a = -a;
                    }
                    cat.X += a;
                }else {
                    if (cat.Y == 6){
                        a = -a;
                    }
                    cat.Y += a;
                }
            }
        }
        for (Hound hound : houndsList) {
            int a = 1;
            if (random.nextInt(2) == 0){
                if (hound.X == 6){
                    a = -a;
                }
                hound.X += a;
            }else {
                if (hound.Y == 6){
                    a = -a;
                }
                hound.Y += a;
            }
        }
        for (WildAnimal wildAnimal : wildAnimalsList) {
            int a = 1;
            if (random.nextInt(2) == 0){
                if (wildAnimal.X == 6){
                    a = -a;
                }
                wildAnimal.X += a;
            }else {
                if (wildAnimal.Y == 6){
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
                    removeProductList.add(product);
                    productsInBarn.put(product,1);
                    Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace()-product.getBarnSpace());
                }
            }
        }
        int i = 1;
        for (Product product : removeProductList) {
            if (productsList.contains(product)) {
                productsList.remove(product);
                if (i == removeProductList.size()){
                    removeProductList.clear();
                    return;
                }
                i++;
            }
        }
    }
    public String buyAnimal(String name){
        switch (name){
            case "Buffalo" -> {
                if (player.getCoins() >= 400) {
                    Buffalo buffalo = new Buffalo(random.nextInt(6)+1,random.nextInt(6)+1);
                    player.setCoins(player.getCoins()-buffalo.price);
                    domesticAnimalsList.add(buffalo);
                    buffalo.startProduceProduct = new TIME(level.time.n);
                    return buffalo.name;
                }else return "Coins";
            }
            case "Cat" -> {
                if (player.getCoins() >= 150) {
                    Cat cat = new Cat(random.nextInt(6)+1,random.nextInt(6)+1);
                    player.setCoins(player.getCoins()-cat.price);
                    catsList.add(cat);
                    return cat.name;
                }else return "Coins";
            }
            case "Hen" -> {
                if (player.getCoins() >= 100) {
                    Hen hen = new Hen(random.nextInt(6)+1,random.nextInt(6)+1);
                    player.setCoins(player.getCoins()-hen.price);
                    domesticAnimalsList.add(hen);
                    hen.startProduceProduct = new TIME(level.time.n);
                    return hen.name;
                }else return "Coins";
            }
            case "Hound" -> {
                if (player.getCoins() >= 100) {
                    Hound hound = new Hound(random.nextInt(6)+1,random.nextInt(6)+1);
                    player.setCoins(player.getCoins()-hound.price);
                    houndsList.add(hound);
                    return hound.name;
                }else return "Coins";
            }
            case "Turkey" -> {
                if (player.getCoins() >= 200) {
                    Turkey turkey = new Turkey(random.nextInt(6)+1,random.nextInt(6)+1);
                    player.setCoins(player.getCoins()-turkey.price);
                    domesticAnimalsList.add(turkey);
                    turkey.startProduceProduct = new TIME(level.time.n);
                    return turkey.name;
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
                if (player.getCoins() >= Bakery.getInstance().getCost()){
                    workShops.add(Bakery.getInstance());
                    player.setCoins(player.getCoins()-Bakery.getInstance().getCost());
                    stringBuilder.append("bakery").append(" ").append(Bakery.getInstance().input.getName()).append(" ").append(Bakery.getInstance().output.getName());
                }
                else
                    return "coins";
            }
            case "iceCreamSelling" -> {
                if (player.getCoins() >= IceCreamSelling.getInstance().getCost()){
                    workShops.add(IceCreamSelling.getInstance());
                    player.setCoins(player.getCoins()-IceCreamSelling.getInstance().getCost());
                    stringBuilder.append("iceCreamSelling").append(" ").append(IceCreamSelling.getInstance().input.getName()).append(" ").append(IceCreamSelling.getInstance().output.getName());
                }
                else
                    return "coins";
            }
            case "milkPackaging" -> {
                if (player.getCoins() >= MilkPackaging.getInstance().getCost()){
                    workShops.add(MilkPackaging.getInstance());
                    player.setCoins(player.getCoins()-MilkPackaging.getInstance().getCost());
                    stringBuilder.append("milkPacking").append(" ").append(MilkPackaging.getInstance().input.getName()).append(" ").append(MilkPackaging.getInstance().output.getName());
                }
                else
                    return "coins";
            }
            case "mill" -> {
                if (player.getCoins() >= Mill.getInstance().getCost()){
                    workShops.add(Mill.getInstance());
                    player.setCoins(player.getCoins()-Mill.getInstance().getCost());
                    stringBuilder.append("mill").append(" ").append(Mill.getInstance().input.getName()).append(" ").append(Mill.getInstance().output.getName());
                }
                else
                    return "coins";
            }
            case "sewing" -> {
                if (player.getCoins() >= SewingWS.getInstance().getCost()){
                    workShops.add(SewingWS.getInstance());
                    player.setCoins(player.getCoins()-SewingWS.getInstance().getCost());
                    stringBuilder.append("sewing").append(" ").append(SewingWS.getInstance().input.getName()).append(" ").append(SewingWS.getInstance().output.getName());
                }
                else
                    return "coins";
            }
            case "weaving" -> {
                if (player.getCoins() >= WeavingWS.getInstance().getCost()){
                    workShops.add(WeavingWS.getInstance());
                    player.setCoins(player.getCoins()-WeavingWS.getInstance().getCost());
                    stringBuilder.append("weaving").append(" ").append(WeavingWS.getInstance().input.getName()).append(" ").append(WeavingWS.getInstance().output.getName());
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
                    productsInBarn.put(product,1);
                    Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace()-product.getBarnSpace());
                }else return "barnSpace";
            }
        }
        int i = 0;
        String transfer = "";
        for (Product product : removeProductList) {
            if (productsList.contains(product)) {
                productsList.remove(product);
                transfer += product.getName()+",";
                i++;
                if (i == removeProductList.size()){
                    removeProductList.clear();
                    return transfer;
                }
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
            if (Well.getInstance().getRemainingWater() > 0) {
                Grass grass = new Grass(x, y);
                grassesList.add(grass);
                Well.getInstance().setRemainingWater(Well.getInstance().getRemainingWater()-1);
                return 3;
            }else return 4;
        }else return 1;
    }
    public String startingWorkshop(String name){
        for (WorkShop workShop : workShops) {
            if (workShop.name.equals(name)){
                Product[] product = new Product[workShop.level];
                int i = 0;
                for (Map.Entry<Product, Integer> entry : productsInBarn.entrySet()){
                    if (entry.getKey().getName().equals(workShop.input.getName()) && i < 2){
                        product[i] = entry.getKey();
                        i++;
                    }
                }
                if (product[0] == null){
                    System.out.println("ERROR");
                    return "b";
                }
                if (workShop.level == 1){
                    productsInBarn.remove(product[0]);
                    workShop.setStartTime(level.time);
                    return workShop.name.concat(String.valueOf(workShop.productionTime));
                }
                if (product[1] != null){
                    productsInBarn.remove(product[0]);
                    productsInBarn.remove(product[1]);
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
                for (int i = 0; i < workShop.level; i++) {
                    Product product = workShop.producing();
                    productsList.add(product);
                    product.setStartDisappearTime(level.time);
                }
                workShop.setStartTime(new TIME(0));
                return workShop.name;
            }else if (workShop.startTime == null)
                return "does not work";
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
                wildAnimal.decreaseCageLevel = true;
                wildAnimal.useCageOrder = true;
                return wildAnimal.cageLevel;
            }
        }
        return -1;
    }
    public String loadingProducts(String name, int amount){
        if (checkTrip() != 0)
            return "Traveling";
        else {
            int count = 0;
            for (Map.Entry<Product, Integer> entry : productsInBarn.entrySet()){
                if (entry.getKey().getName().equals(name)){
                    count++;
                }
            }
            if (count == 0)
                return "notInBarn";
            if (amount > count)
                return "notEnoughSpace";
            Product[] inTruck = new Product[amount];
            int i = 0;
            for (Map.Entry<Product, Integer> entry : productsInBarn.entrySet()){
                if (entry.getKey().getName().equals(name) && i < amount){
                    inTruck[i] = entry.getKey();
                    i++;
                }
            }
            for (Product product : inTruck) {
                productsInBarn.remove(product);
                loadedProducts.put(product, 1);
            }
            Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace() + amount * inTruck[0].getBarnSpace());
            Car.getInstance().setEmptySpace(Car.getInstance().getEmptySpace() - amount * inTruck[0].getBarnSpace());
        }
        return "loaded";
    }
    public String  unLoadingProducts(String name){
        if (checkTrip() != 0)
            return "Traveling";
        else {
            int i = 0;
            Product[] inTruck = new Product[loadedProducts.size()];
            for (Map.Entry<Product, Integer> entry : loadedProducts.entrySet()){
                if (entry.getKey().getName().equals(name)){
                    inTruck[i] = entry.getKey();
                    i++;
                }
            }
            int spaceNeeded = 0;
            for (int j = 0; j < i; j++) {
                spaceNeeded += inTruck[j].getBarnSpace();
            }
            if (spaceNeeded > Barn.getInstance().getFreeSpace())
                return "notEnoughSpace";
            for (Product product : inTruck) {
                loadedProducts.remove(product);
                productsInBarn.put(product, 1);
            }
            Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace() - spaceNeeded);
            Car.getInstance().setEmptySpace(Car.getInstance().getEmptySpace() + spaceNeeded);
        }
        return "unLoaded";
    }
    public void startTrip(){
        Car.getInstance().setStartTrip(new TIME(level.time.n));
    }
    public int checkTrip(){
        if (Car.getInstance().getStartTrip() == null)
            return 0;
        if (!Car.getInstance().IsCarBack(level.time))
            return TIME.diff(level.time,Car.getInstance().getStartTrip());
        else return 0;
    }
    public int sellProducts(){
        if (Car.getInstance().getStartTrip() == null)
            return -1;
        if (Car.getInstance().IsCarBack(level.time)){
            int sellPrice = 0;
            for (Map.Entry<Product, Integer> entry : loadedProducts.entrySet()){
                sellPrice += entry.getKey().getSellingPrice();
            }
            player.setCoins(player.getCoins()+sellPrice);
            loadedProducts.clear();
            return sellPrice;
        }
        return -1;
    }
    public String checkTasks(){
        if (level.tasks.containsKey("coins")){
            if (level.coins >= level.tasks.get("coins")){
                level.tasks.remove("coins");
                return "coins";
            }
        }
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (level.tasks.containsKey(domesticAnimal.name)  &&  !animalsCalculatedInTask.contains(domesticAnimal)) {
                animalsCalculatedInTask.add(domesticAnimal);
                int animalNum = level.tasks.get(domesticAnimal.name) - 1;
                if (animalNum == 0) {
                    level.tasks.remove(domesticAnimal.name);
                    return domesticAnimal.name;
                }
                level.tasks.replace(domesticAnimal.name, animalNum);
            }
        }
        for (Map.Entry<Product,Integer> entry : productsInBarn.entrySet()) {
            if (level.tasks.containsKey(entry.getKey().getName()) && !productsCalculatedInTask.contains(entry.getKey())) {
                productsCalculatedInTask.add(entry.getKey());
                int productNum = level.tasks.get(entry.getKey().getName()) - 1;
                if (productNum == 0) {
                    level.tasks.remove(entry.getKey().getName());
                    return entry.getKey().getName();
                }
                level.tasks.replace(entry.getKey().getName(), productNum);
            }
        }
        return "null";
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
    public void isAnimalManufacturedProduct(){
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (level.time.n-domesticAnimal.startProduceProduct.n == domesticAnimal.timeRequiredToProduct){
                Product product = domesticAnimal.outProduct(level.time);
                domesticAnimal.startProduceProduct = new TIME(level.time.n);
                product.setStartDisappearTime(new TIME(level.time));
                productsList.add(product);
            }
        }
    }
    public void disappearProduct(){
        for (Product product : productsList) {
            if (TIME.diff(product.getStartDisappearTime(),level.time) == product.getDisappearTime()){
                removeProductList.add(product);
            }
        }
        for (Product product : removeProductList) {
            productsList.remove(product);
        }
        removeProductList.clear();
    }
    public void appearWildAnimal(){
        for (Map.Entry<String,Integer> entry : level.wildAnimals.entrySet()){
            if (entry.getValue() == level.time.n){
                switch (entry.getKey()){
                    case "Tiger" ->{
                        Tiger tiger = new Tiger(random.nextInt(6)+1,random.nextInt(6)+1);
                        wildAnimalsList.add(tiger);
                    }
                    case "Lion" ->{
                        Lion lion = new Lion(random.nextInt(6)+1,random.nextInt(6)+1);
                        wildAnimalsList.add(lion);
                    }
                    case "Bear" ->{
                        Bear bear = new Bear(random.nextInt(6)+1,random.nextInt(6)+1);
                        wildAnimalsList.add(bear);
                    }
                }
            }
        }
    }
    public void decreaseCageLevel(){
        for (WildAnimal wildAnimal : wildAnimalsList) {
            if (wildAnimal.useCageOrder){
                if (!wildAnimal.decreaseCageLevel){
                    wildAnimal.cageLevel--;
                }
            }
            wildAnimal.decreaseCageLevel = false;
        }
        for (Animal animal : removeAnimalList) {
            wildAnimalsList.remove(animal);
        }
    }
    public String space(int n,String word){
        StringBuilder stringBuilder =new StringBuilder("");
        for (int i = 0; i < n-word.length(); i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}