package Animal;

import java.util.Observable;

/**
 * Abstract base class for all animals
 */
public abstract class Animal {

    private final String name;
    private final int age;
    private final String description;

    // 0 = full/not tired, 100 = hungry/exhausted
    private int hungerLevel;
    private int tiredLevel;

    public Animal(String name, int age, String description){
        this.name = name;
        this.age = age;
        this.description = description;

        hungerLevel = 50;
        tiredLevel = 50;
    }

    /**
     * Feed method for animals.
     * Abstract, to be overridden in each subclass.
     * Takes in 'amount' as a parameter
     */
    public abstract void feed(int amount);

    /**
     * Sleep method for animals
     * Abstract, to be overridden in each subclass.
     * Takes in 'hours' as a parameter
     */
    public abstract void sleep(int hours);

    /**
     * Method to pass time for animals.
     * Abstract, to be overridden in each subclass.
     */
    public abstract void timePass();

    // -------------------------Getters & Setters---------------------------
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    protected void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }

    public int getTiredLevel() {
        return tiredLevel;
    }

    protected void setTiredLevel(int tiredLevel) {
        this.tiredLevel = tiredLevel;
    }

}