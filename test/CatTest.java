import Animal.Animal;
import Animal.Cat;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CatTest {
    @Test
    void feedMoreThanHungry(){
        // Arrange
        Animal cat = new Cat("Fish", 5, "Tabby cat");

        // Act
        cat.feed(60);

        // Assert
        Assert.assertEquals(cat.getHungerLevel(), 0);
    }

    @Test
    void feedWhenAmountLessThanHunger(){
        // Arrange
        Animal cat = new Cat("Sass", 10, "Tortoiseshell cat");

        // Act
        cat.feed(30);

        // Assert
        Assert.assertEquals(cat.getHungerLevel(), 20);
    }
}
