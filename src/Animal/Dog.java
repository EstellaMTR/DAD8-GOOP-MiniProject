package Animal;


public class Dog extends Animal{
    public Dog(String name, int age, String description){
        super(name, age, description);
    }

    @Override
    public void feed(int amount) {
        int hungerLevel = getHungerLevel();
        if (hungerLevel - amount < 0){
            hungerLevel = 0;
        } else{
            hungerLevel -= amount;
        }
        setHungerLevel(hungerLevel);
    }

    @Override
    public void sleep(int hours) {
        int tiredLevel = getTiredLevel();
        if (tiredLevel - hours < 0){
            tiredLevel = 0;
        } else if (hours < 7) {
            tiredLevel -= hours;
        } else {
            tiredLevel = 0;
        }
        setTiredLevel(tiredLevel);
    }

}
