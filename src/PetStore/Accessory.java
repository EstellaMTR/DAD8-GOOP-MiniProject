package PetStore;

public class Accessory {
    private String name;
    private String type;
    private String spriteFileName;
    private int price;


    //---------------------Getters & Setters------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpriteFileName() {
        return spriteFileName;
    }

    public void setSpriteFileName(String spriteFileName) {
        this.spriteFileName = spriteFileName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
