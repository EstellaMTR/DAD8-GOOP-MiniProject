package Animal;


public class Dog extends Animal{
    /**
     * Calls the super class
     * @param name - the name the user inputs
     * @param age - the age the user inputs
     * @param description - the description the user inputs
     */
    public Dog(String name, int age, String description){
        super(name, age, description);
        setHungerLevel(100);
        setTiredLevel(10);
    }

    /**
     * Override method for feed, for dogs.
     * When called, if the amount input to feed the dog, will reduce the hunger below 0, then hunger set to 0.
     * @param amount - the amount the user wants to feed the dog
     */
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

    /**
     * Override method for sleep, for dogs.
     * If the tiredness of the dog, minus the hours input to sleep, will be less than 0, then set to 0.
     * If the dog sleeps less than 7 hours, then tiredness level - hours.
     * If the dog sleeps more than 7 hours, then its fully rested.
     * @param hours - the number of hours the user wants the dog to sleep.
     */
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
    /**
     * Override method for timePass for dogs.
     * When time passes, the dog's tiredness level will increase by 10.
     * When time passes, the dog's hunger level will increase by 20.
     */
    @Override
    public void timePass(){
        int tiredness = getTiredLevel() + 10;
        if (tiredness > 100){
            tiredness = 100;
        }
        setTiredLevel(tiredness);
        int hunger = getHungerLevel() + 20;
        if (hunger > 100){
            hunger = 100;
        }
        setHungerLevel(hunger);
    }
}
