import Animal.Animal;
import Animal.Cat;
import Animal.Dog;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Home extends Observable {
    /** Creating a list of type animals. List instead of ArrayList so we are not dependent on specifically implementing ArrayList */
    private final List<Animal> pets;
    /** Creating an index variable to use to get pets etc later*/
    private int currentIndex;

    /**
     * Constructs theHome class, setting pets to equal an empty ArrayList, and setting currentIndex to 0
     */
    public Home(){
        this.pets = new ArrayList<>();
        this.currentIndex = 0;
    }

    /**
     * Method to get the current pet the user has 'selected', by the index of the selected pet.
     * @return type Animal - return the animal object the user has selected (whether cat or dog)
     */
    public Animal getCurrentPet(){
        if (pets.isEmpty()){
            return null;
        }
        return pets.get(currentIndex);
    }

    /**
     * Method to get the 'next pet' in the pet array.
     * Works by incrementing the current index, until the size of the index, then resets to 0 (the first pet)
     */
    public void nextPet(){
        currentIndex += 1;
        if (currentIndex >= pets.size()){
            currentIndex = 0;
        }
        sendNotification();
    }

    /**
     * Method to get the 'previous pet' in the array.
     * Works by decrementing the current index, until the first index in the array, then resets to the last pet in the array
     */
    public void previousPet(){
        currentIndex -= 1;
        if (currentIndex < 0){
            currentIndex = pets.size() - 1;
        }
        sendNotification();
    }

    /**
     * Method to add a new pet
     * @param pet - when called, takes in a type of Animal and adds that animal to the array
     */
    public void addPet(Animal pet){
        pets.add(pet);
    }

    /**
     * Second 'addPet' function - calls the first 'addPet' function (above)
     * @param name - the name of the pet
     * @param age - the age of the pet
     * @param description - a description of the pet
     * @param petType - the type of pet (whether cat or dog, for example)
     * Takes n the above parameters and removes whitespace from name and description.
     * Checks the type of pet can creates a new pet of the designated type, with the details from the above parameters
     */
    public void addPet(String name, int age, String description, String petType){
        name = name.strip();
        description = description.strip();
        if(name.length() > 50 || name.isEmpty()){
            throw new RuntimeException("Name of pet cannot be empty or longer than 50 characters");
        } else if (age < 0 || age > 40) {
            throw new RuntimeException("Pet cannot be younger than 0 or older than 40");
        } else if (description.length() > 250) {
            throw new RuntimeException("Description cannot be longer than 250 characters");
        }

        Animal pet = null;

        switch (petType.toLowerCase()){
            case "cat": pet = new Cat(name, age, description);
            break;
            case "dog": pet = new Dog(name, age, description);
            break;
            default: throw new RuntimeException("Unrecognisable pet type");
        }
        addPet(pet);
        currentIndex = pets.size() - 1;
        sendNotification();

    }

    /**
     * Method to make the 'current pet' sleep
     * @param hours - the number of hours the user wants the pet to sleep
     */
    public void currentPetSleep(int hours){
        Animal pet = getCurrentPet();
        if (pet == null) return;
        pet.sleep(hours);
        sendNotification();
    }

    /**
     * Method to feed the 'current pet'
     * @param amount - the amount of food the user wants to feed the pet
     */
    public void currentPetFeed(int amount){
        Animal pet = getCurrentPet();
        if (pet == null) return;
        pet.feed(amount);
        sendNotification();
    }

    /**
     * The method to pass time. Calls the individual pet's designated 'timePass' function
     * This is done because of different animal's different needs; dogs, for example, will get hungrier in the same amount of time than cats will.
     */
    public void timePass(){
        for (var pet: pets){
            pet.timePass();
        }
        sendNotification();
    }

    /**
     * The method to signal changes to observers
     */
    private void sendNotification() {
        setChanged();
        notifyObservers();
    }
}
