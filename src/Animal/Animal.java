package Animal;

public abstract class Animal {

    private String name;

    private int age;

    private boolean isAdopted;

    private int hungerLevel;

    private int tiredLevel;

    private int funLevel;

    private int cleanLevel;


    /**
     * Feed method for animals.
     * 0 = Full
     * 100 = Starving
     */
    public void feed(){
        try{
            hungerLevel -= 20;
            if (hungerLevel < 0){
                throw new IllegalStateException("Hunger cannot go below 0");
            }
        } catch (Exception e){

        }

    }
    /**
     * Sleep method for animals
     */
    public void sleep(){

    }  /**
     * Play method for animals
     */
    public void play(){

    }
    /**
     * Clean method for animals
     */
    public void clean(){

    }
    /**
     * Make sound method for animals
     */
    public String makeSound(){

    }
    /**
     * Get animal type method for animals
     */
    public String getAnimalType(){

    }

    //--------------------Getters & Setters------------------

    /**
     * Get age
     * @return age
     */
    public int getAge() {
        return age;
    }
    /**
     * Set age
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * Get hunger level
     * @return hunger level
     */
    public int getHungerLevel() {
        return hungerLevel;
    }
    /**
     * Set hunger level
     */
    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }
    /**
     * Get tiredness level
     * @return tiredness level
     */
    public int getTiredLevel() {
        return tiredLevel;
    }
    /**
     * Set tiredness level
     */
    public void setTiredLevel(int tiredLevel) {
        this.tiredLevel = tiredLevel;
    }
    /**
     * Get fun level
     * @return fun level
     */
    public int getFunLevel() {
        return funLevel;
    }
    /**
     * Set fun level
     */
    public void setFunLevel(int funLevel) {
        this.funLevel = funLevel;
    }
    /**
     * Get cleanliness
     * @return cleanliness
     */
    public int getCleanLevel() {
        return cleanLevel;
    }
    /**
     * Set cleanliness
     */
    public void setCleanLevel(int cleanLevel) {
        this.cleanLevel = cleanLevel;
    }
    /**
     * Get name
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Set name
     */
    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdopted() {
        return isAdopted;
    }

    public void setAdopted(boolean adopted) {
        isAdopted = adopted;
    }
}
