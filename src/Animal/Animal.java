package Animal;

import java.util.Observable;

/**
 * Abstract base class for all animals
 */
public abstract class Animal {

    private final String name;
    private final int age;
    private final String description;

    // 0 = good, 100 = bad (for hunger/tiredness)
    private int hungerLevel;
    private int tiredLevel;
//    private int funLevel;
//    private int cleanLevel;

    public Animal(String name, int age, String description){
        this.name = name;
        this.age = age;
        this.description = description;

        hungerLevel = 50;
        tiredLevel = 50;
    }

    /**
     * Feed method for animals.
     * Reduces hunger (0 = full, 100 = starving)
     */
    public abstract void feed(int amount);

    /**
     * Sleep method for animals
     */
    public abstract void sleep(int hours);

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

//    /**
//     * Play method for animals
//     */
//    public void play() {
//        funLevel = Math.min(100, funLevel + 20);
//        tiredLevel = Math.min(100, tiredLevel + 10);
//
//        if (funLevel == 100) {
//            System.out.println(name + " is very happy!");
//        }
//    }
//
//    /**
//     * Clean method for animals
//     */
//    public void clean() {
//        cleanLevel = Math.min(100, cleanLevel + 50);
//
//        if (cleanLevel == 100) {
//            System.out.println(name + " is perfectly clean!");
//        }
//    }

//    /**
//     * Make sound method (must be implemented by subclasses)
//     */
//    public abstract String makeSound();
//
//    /**
//     * Get animal type (must be implemented by subclasses)
//     */
//    public abstract String getAnimalType();
//
//    //--------------------Getters & Setters------------------
//
//    /**
//     * Get age
//     * @return age
//     */
//    public int getAge() {
//        return age;
//    }
//
//    /**
//     * Set age
//     */
//    public void setAge(int age) {
//        if (age < 0) {
//            throw new IllegalArgumentException("Age cannot be negative");
//        }
//        this.age = age;
//    }
//
//    /**
//     * Get hunger level
//     */
//    public int getHungerLevel() {
//        return hungerLevel;
//    }
//
//    /**
//     * Set hunger level (0–100)
//     */
//    public void setHungerLevel(int hungerLevel) {
//        if (hungerLevel < 0 || hungerLevel > 100) {
//            throw new IllegalArgumentException("Hunger must be between 0 and 100");
//        }
//        this.hungerLevel = hungerLevel;
//    }
//
//    /**
//     * Get tiredness level
//     */
//    public int getTiredLevel() {
//        return tiredLevel;
//    }
//
//    /**
//     * Set tiredness level (0–100)
//     */
//    public void setTiredLevel(int tiredLevel) {
//        if (tiredLevel < 0 || tiredLevel > 100) {
//            throw new IllegalArgumentException("Tiredness must be between 0 and 100");
//        }
//        this.tiredLevel = tiredLevel;
//    }
//
//    /**
//     * Get fun level
//     */
//    public int getFunLevel() {
//        return funLevel;
//    }
//
//    /**
//     * Set fun level (0–100)
//     */
//    public void setFunLevel(int funLevel) {
//        if (funLevel < 0 || funLevel > 100) {
//            throw new IllegalArgumentException("Fun must be between 0 and 100");
//        }
//        this.funLevel = funLevel;
//    }
//
//    /**
//     * Get cleanliness level
//     */
//    public int getCleanLevel() {
//        return cleanLevel;
//    }
//
//    /**
//     * Set cleanliness level (0–100)
//     */
//    public void setCleanLevel(int cleanLevel) {
//        if (cleanLevel < 0 || cleanLevel > 100) {
//            throw new IllegalArgumentException("Cleanliness must be between 0 and 100");
//        }
//        this.cleanLevel = cleanLevel;
//    }
//
//    /**
//     * Get name
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * Set name
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /**
//     * Check if adopted
//     */
//    public boolean isAdopted() {
//        return isAdopted;
//    }
//
//    /**
//     * Set adoption status
//     */
//    public void setAdopted(boolean adopted) {
//        isAdopted = adopted;
//    }
}