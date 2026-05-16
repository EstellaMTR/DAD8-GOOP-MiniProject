import Animal.Animal;
import Animal.Cat;
import Animal.Dog;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Home extends Observable {
    private final List<Animal> pets;
    private int currentIndex;
    public Home(){
        this.pets = new ArrayList<>();
        this.currentIndex = 0;
    }

    public Animal getCurrentPet(){
        if (pets.isEmpty()){
            return null;
        }
        return pets.get(currentIndex);
    }

    public void nextPet(){
        currentIndex += 1;
        if (currentIndex >= pets.size()){
            currentIndex = 0;
        }
        sendNotification();
    }

    public void previousPet(){
        currentIndex -= 1;
        if (currentIndex < 0){
            currentIndex = pets.size() - 1;
        }
        sendNotification();
    }

    public Animal getPet(int index){
        if(index >= pets.size()){
            throw new RuntimeException("No pets at index " + index);
        }
        return pets.get(index);
    }

    public int numberOfPets(){
        return pets.size();
    }

    public void addPet(Animal pet){
        pets.add(pet);
    }

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

    private void sendNotification() {
        setChanged();
        notifyObservers();
    }
}
