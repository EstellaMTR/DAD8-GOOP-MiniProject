import Animal.Animal;
import Animal.Cat;
import Animal.Dog;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest {
    @Test
    void addPetIfNameLongerThan50Chars(){
        // Arrange
        Home home = new Home();

        // Act
        RuntimeException exception = null;
        try{
            home.addPet("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyui", 5, "cattt", "cat");
            Assert.fail("Method didnt throw a runtime exception");
        } catch (RuntimeException e){
            exception = e;
        }

        // Assert
        Assert.assertNotNull(exception);
        Assert.assertTrue(exception.getMessage().contains("50"));
    }

    @Test
    void addPetIfAgeGreaterThan40(){
        // Arrange
        Home home = new Home();

        // Act
        RuntimeException exception = null;
        try{
            home.addPet("moonlight", 45, "sheepdog", "dog");
            Assert.fail("Method didnt throw a runtime exception");
        } catch (RuntimeException e){
            exception = e;
        }

        // Assert
        Assert.assertNotNull(exception);
        Assert.assertTrue(exception.getMessage().contains("older"));
    }

    @Test
    void addPetIfDescriptionGreaterThan250Chars(){
        // Arrange
        Home home = new Home();

        // Act
        RuntimeException exception = null;
        try{
            home.addPet("prince", 15, "dogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdogdo\n", "dog");
            Assert.fail("Method didnt throw a runtime exception");
        } catch (RuntimeException e){
            exception = e;
        }

        // Assert
        Assert.assertNotNull(exception);
        Assert.assertTrue(exception.getMessage().contains("250"));
    }

    @Test
    void makeSureCatIsAdded(){
        // Arrange
        Home home = new Home();

        // Act
        home.addPet("Sass", 10, "tortoiseshell cat", "cat");
        Animal pet = home.getCurrentPet();

        // Assert
        Assert.assertNotNull(pet);
        Assert.assertEquals(pet.getName(), "Sass");
        Assert.assertEquals(pet.getAge(), 10);
        Assert.assertEquals(pet.getDescription(), "tortoiseshell cat");
        Assert.assertTrue(pet instanceof Cat);
    }

    @Test
    void makeSureDogIsAdded(){
        // Arrange
        Home home = new Home();

        // Act
        home.addPet("Prince", 15, "rescue", "dog");
        Animal pet = home.getCurrentPet();

        // Assert
        Assert.assertNotNull(pet);
        Assert.assertEquals(pet.getName(), "Prince");
        Assert.assertEquals(pet.getAge(), 15);
        Assert.assertEquals(pet.getDescription(), "rescue");
        Assert.assertTrue(pet instanceof Dog);
    }

    @Test
    void unrecognisablePetType(){
        // Arrange
        Home home = new Home();

        // Act
        RuntimeException exception = null;
        try{
            home.addPet("Shaun", 2, "fluffy", "sheep");
            Assert.fail("Method didnt throw a runtime exception");
        } catch (RuntimeException e){
            exception = e;
        }

        // Assert
        Assert.assertNotNull(exception);
        Assert.assertTrue(exception.getMessage().contains("Unrecognisable"));
    }
}
