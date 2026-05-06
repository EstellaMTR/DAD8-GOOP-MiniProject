package Animal;

import PetStore.Accessory;

public class Cat extends Animal{
    private String breed;
    private Accessory accessory;

    /**
     * Override method to make cat meow
     * @return 'meow'
     */
    @Override
    public String makeSound() {
        return super.makeSound();
    }
    /**
     * Override method to get cat type
     * @return 'cat'
     */
    @Override
    public String getAnimalType() {
        return super.getAnimalType();
    }

    //------------------Getters & Setters---------------
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }
}
