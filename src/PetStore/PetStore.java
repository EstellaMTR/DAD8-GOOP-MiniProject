package PetStore;

import Animal.Cat;

import java.util.ArrayList;

public class PetStore {
    private String storeName;
    private ArrayList<Cat> availableCats = new ArrayList<>();
    private ArrayList<Accessory> availableAccessories = new ArrayList<>();

    public PetStore(String storeName) {
        this.storeName = storeName;
    }

    /**
     * Method for adding a cat to the store
     */
    public void addCat(){

    }
    /**
     * Method for adding an accessory to the store
     */
    public void addAccessory(Accessory accessory){

    }


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public ArrayList<Cat> getAvailableCats() {
        return availableCats;
    }

    public void setAvailableCats(ArrayList<Cat> availableCats) {
        this.availableCats = availableCats;
    }

    public ArrayList<Accessory> getAvailableAccessories() {
        return availableAccessories;
    }

    public void setAvailableAccessories(ArrayList<Accessory> availableAccessories) {
        this.availableAccessories = availableAccessories;
    }
}
