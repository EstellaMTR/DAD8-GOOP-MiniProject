package Animal;


public class Cat extends Animal{
    public Cat(String name, int age, String description){
        super(name, age, description);
    }

    /**
     * Override method for feed, for cats.
     * When called, if the amount input to feed the cat, will reduce the hunger below 0, then hunger set to 0.
     * @param amount - the amount the user wants to feed the cat
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
     * Override method for sleep, for cats.
     * If the tiredness of the cat, minus the hours input to sleep, will be less than 0, then set to 0.
     * If the cat sleeps less than 10 hours, then tiredness level - hours.
     * If the cat sleeps more than 10 hours, then its fully rested.
     * @param hours - the number of hours the user wants the cat to sleep.
     */
    @Override
    public void sleep(int hours) {
        int tiredLevel = getTiredLevel();
        if (tiredLevel - hours < 0){
            tiredLevel = 0;
        } else if (hours < 10) {
            tiredLevel -= hours;
        } else {
            tiredLevel = 0;
        }
        setTiredLevel(tiredLevel);
    }

    /**
     * Override method for timePass for cats.
     * When time passes, the cat's tiredness level will increase by 20.
     * When time passes, the cat's hunger level will increase by 10.
     */
    @Override
    public void timePass(){
        int tiredness = getTiredLevel() + 20;
        if (tiredness > 100){
            tiredness = 100;
        }
        setTiredLevel(tiredness);
        int hunger = getHungerLevel() + 10;
        if (hunger > 100){
            hunger = 100;
        }
        setHungerLevel(hunger);
    }

}
