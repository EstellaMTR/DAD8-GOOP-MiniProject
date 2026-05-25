import Animal.Animal;
import Animal.Cat;
import Animal.Dog;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

public class PetApp extends Application {
    private Scene scene;
    private Home home;
    private Button addPetBtn;
    private Button feedBtn;
    private Button sleepBtn;
    private Button timePassBtn;
    private PetView petView;
    private PetStats petStats;
    private PetLabel petLabel;
    private Spinner<Integer> sleepSpinner;
    private Spinner<Integer> feedSpinner;
    private Label infoIcon;

    /**
     * Method that handles everything related to the popup which allows the user to add a new pet.
     */
    public void addPetPopup(){
        Popup popup = new Popup();
        popup.setAutoHide(true);

        // Create radio buttons
        RadioButton dogButton = new RadioButton("Dog");
        RadioButton catButton = new RadioButton("Cat");

        // Create a ToggleGroup
        ToggleGroup petsGroup = new ToggleGroup();

        // Add buttons to the group
        dogButton.setToggleGroup(petsGroup);
        catButton.setToggleGroup(petsGroup);

        // Select one by default
        catButton.setSelected(true);

        // Creates three deparate textfields with labels for name, age and description
        Label nameLabel = new Label("Pet Name: ");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter the name of the cat: ");
        Label ageLabel = new Label("Pet Age: ");
        TextField ageInput = new TextField();
        ageInput.setPromptText("Enter the age of the  cat: ");
        Label descriptionLabel = new Label("Pet Description: ");
        TextField descriptionInput = new TextField();
        descriptionInput.setPromptText("Describe your cat: ");

        // Submit button for popup
        Button submitBtn = new Button("Add pet");
        // Error label for the popup
        Label errorLabel = new Label("");
        submitBtn.setOnAction(event -> {
            // When the submit button is pressed, try to pass the entered details to the 'addPet' method from Home.java
            // Hide the popup after the addPet method is called.
            // Set all buttons related to feeding the pet and making it sleep, to visible, once the pet is added.
            try{
                RadioButton selected = (RadioButton) petsGroup.getSelectedToggle();
                String name = nameInput.getText();
                int age = Integer.parseInt(ageInput.getText());
                String description = descriptionInput.getText();
                home.addPet(name, age, description, selected.getText());
                popup.hide();
                feedBtn.setVisible(true);
                sleepBtn.setVisible(true);
                sleepSpinner.setVisible(true);
                feedSpinner.setVisible(true);
                infoIcon.setVisible(true);
            } // catch any number format problems for age input - to make sure the user enters a number for age
            catch (NumberFormatException ne){
                errorLabel.setText("\"" + ageInput.getText() + "\"" + " is not a number");
                errorLabel.setStyle("-fx-text-fill: red;");
            } // Catch any other errors and display them in red
            catch (Exception e){
                errorLabel.setText(e.getMessage());
                errorLabel.setStyle("-fx-text-fill: red;");
            }
        });
        // creating the actual box for the popup
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-border-color: gray; -fx-border-width: 1;");

        // adding the elements created above for the popup, to the actual popup
        layout.getChildren().addAll(catButton, dogButton, nameLabel, nameInput, ageLabel, ageInput, descriptionLabel, descriptionInput, submitBtn, errorLabel);
        popup.getContent().add(layout);
        popup.show(scene.getWindow());
    }

    /**
     * PetView Observer class. Includes an update method for updating the view.
     * This class is responsible for displaying the image of the pet
     */
    private class PetView extends ImageView implements Observer {
        public PetView() {
        }

        /**
         * Update method for the view
         * @param o     the observable object.
         * @param arg   an argument passed to the {@code notifyObservers}
         *                 method.
         * Checks if what it is observing is an instance of the observable Home class.
         * If so, then we get the current pet.
         * If the current pet is a cat, we display the designated cat image
         * If the current pet is a dog, we display the designated dog image
         * If we cannot find the image file, then throw an error
         */
        @Override
        public void update(Observable o, Object arg){
            if (o instanceof Home){
                Animal pet = ((Home)o).getCurrentPet();
                try {
                    if (pet instanceof Cat){
                        Image pic = new Image( new FileInputStream("src/assets/Cats/drculacat.png"));
                        relocate(1010, 430);

                        this.setImage(pic);
                        this.setFitWidth(100);
                        this.setFitHeight(100);

                    } else if (pet instanceof Dog) {
                        Image pic = new Image( new FileInputStream("src/assets/Dogs/prince.png"));
                        relocate(800, 400);

                        this.setImage(pic);
                        this.setFitWidth(180);
                        this.setFitHeight(180);
                    }
                }  catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    /**
     * PetLabel class for displaying the current pet's name, age and description
     * Includes an update method for updating the view
     */
    private class PetLabel extends Text implements Observer{

        /**
         * UUUpdate method for viewing label
         * @param o     the observable object.
         * @param arg   an argument passed to the {@code notifyObservers}
         *                 method.
         * Checks if the observable object is an instance of the observable Home class
         * If so, gets the current pet
         * Then, sets the text of the label to equal the current pet's name, age and description
         */
        @Override
        public void update(Observable o, Object arg) {
            if (o instanceof Home){
                Animal pet = ((Home) o).getCurrentPet();
                if (pet == null){
                    return;
                } setText("Name: " + pet.getName() + "          Age: " + pet.getAge() + "           Description: " + pet.getDescription());
            }
        }
    }

    /**
     * Similar to PetLabel. Class for displaying the stats of the current pet.
     * Likewise includes an update method for updating the view
     */
    private class PetStats extends Text implements Observer{

        /**
         * Update method for the PetStas class
         * @param o     the observable object.
         * @param arg   an argument passed to the {@code notifyObservers}
         *                 method.
         * Checks if the observable object is an instance of the observable Home class
         * If so, gets the current pet and displays their hunger and tiredness levels
         */
        @Override
        public void update(Observable o, Object arg) {
            if (o instanceof Home){
                Animal pet = ((Home) o).getCurrentPet();
                if (pet == null){
                    return;
                } setText("Hunger Level: " + pet.getHungerLevel() + "               " + "Tired Level: " + pet.getTiredLevel());
            }
        }
    }

    /**
     * The init method for the application.
     * This is where we handle the display of everything for the application, including all things from the above classes.
     */
    @Override
    public void init() {
        /**
         * Defining variables home, petView, petStats and petLabel
         * This is to be able to use the above defined classes and their methods
         * These objects are defined local to this class, so they can only be used within the PetApp class
         */
        home = new Home();
        petView = new PetView();
        petStats = new PetStats();
        petLabel = new PetLabel();

        // Adding the PetView, PetStats and PetLabel classes as observers of Home
        home.addObserver(petView);
        home.addObserver(petStats);
        home.addObserver(petLabel);

        // Creating a new pane and assigning a background image, as well as adding petView
        // The pet is thus displayed in the main section of the page
        Pane homePane = new Pane();
        homePane.setStyle("-fx-background-image: url(assets/Scenes/Hall.png);" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center;" +
                "-fx-background-size: contain;");
        homePane.getChildren().add(petView);
        homePane.setPrefSize(1800, 600);

        // Creating a new spinner, to allow the user to choose how much to feet the pet
        // Inherently it is set to invisible, but becomes visible when the pet is added (line 91)
        feedSpinner = new Spinner<Integer>();
        feedSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0, 5));
        feedSpinner.setVisible(false);
        feedSpinner.setEditable(true);

        // Button to 'submit' the amount to feed to the pet. Similar to above, invisible at first. Becomes visible when pet added
        feedBtn = new Button("Feed");
        feedBtn.setVisible(false);
        feedBtn.setOnAction(event -> {
            home.currentPetFeed(feedSpinner.getValue());
        });

        // Creating a box to add the spinner and button to
        HBox feedBox = new HBox(0, feedSpinner, feedBtn);
        feedBox.setAlignment(Pos.CENTER);

        // Creating an information icon to explain to the user
        infoIcon = new Label("ⓘ");
        infoIcon.setFont(Font.font("System", FontWeight.BOLD, 16));
        infoIcon.setStyle("-fx-text-fill: #1565C0; -fx-cursor: hand;");

        // Adding text to the tooltip
        Tooltip tooltip = new Tooltip("Different pet types have different sleep needs. \n Sleeping more than a certain amount of hours for each type will fully rest the pet.");
        tooltip.setFont(Font.font(12));

        // Adding a delay for how quickly the text should display
        tooltip.setShowDelay(Duration.millis(100));

        // Adding the tooltip to the previously created icon
        infoIcon.setTooltip(tooltip);
        infoIcon.setVisible(false);

        // Similar to the feed spinner above. Allows the user to determine how long the pet should sleep. Invisble to begin with.
        sleepSpinner = new Spinner<Integer>();
        sleepSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 14, 0, 2));
        sleepSpinner.setVisible(false);
        sleepSpinner.setEditable(true);

        // A button to 'submit'/confirm how long the user wants the pet to sleep for.
        sleepBtn = new Button("Sleep");
        sleepBtn.setVisible(false);
        sleepBtn.setOnAction(event ->{
            home.currentPetSleep(sleepSpinner.getValue());
        });

        // Creating a new HBox to add the spinner, button and information icon to
        HBox sleepBox = new HBox(0, sleepSpinner, sleepBtn, infoIcon);
        sleepBox.setAlignment(Pos.CENTER);

        // Creating a button to allow the user to pass time. When pressed, this makes the pet become hungrier and more tired.
        timePassBtn = new Button("Pass time");
        timePassBtn.setOnAction(event -> home.timePass());

        // Creating a new hbox and adding the petLabel to it
        HBox petInfoPanel = new HBox(petLabel);
        petInfoPanel.setSpacing(40);
        petInfoPanel.setPadding(new Insets(10, 0, 10, 0)); // styling
        petInfoPanel.setAlignment(Pos.CENTER);

        // Creating a new hbox and adding the petStats to it
        HBox statsPanel = new HBox(petStats);
        statsPanel.setSpacing(40);
        statsPanel.setPadding(new Insets(10, 0, 10, 0)); // styling
        statsPanel.setAlignment(Pos.CENTER);

        // Creating a new hbox as the overall control panel at the top of the app, and adding the petInfoPanel, statsPanel, feedBox and sleepBox to it.
        HBox topCtrlPanel = new HBox(petInfoPanel, statsPanel, feedBox, sleepBox);
        topCtrlPanel.setSpacing(40);
        topCtrlPanel.setPadding(new Insets(10, 0, 10, 0)); // styling
        topCtrlPanel.setAlignment(Pos.CENTER);

        // Creating the button to allow the user to 'adopt' a pet (adding a pet)
        // Calls the addPetPopup method above
        addPetBtn = new Button("Adopt Pet");
        addPetBtn.setOnAction(event -> addPetPopup());

        // Creatign a hbox panel for adopting/adding a pet. Just has the add pet button.
        HBox adoptPanel = new HBox(addPetBtn);
        adoptPanel.setSpacing(40);
        adoptPanel.setPadding(new Insets(10, 0, 10, 0)); // styling
        adoptPanel.setAlignment(Pos.CENTER);

        // Button for getting the previous pet
        Button prevPetBtn = new Button("Previous Pet");
        prevPetBtn.setOnAction(event -> home.previousPet());
        // Buutton for getting the next pet
        Button nextPetBtn = new Button("Next Pet");
        nextPetBtn.setOnAction(event -> home.nextPet());

        // Panel which displays the buttons for getting next and previous pet, as well as the button to pass time
        HBox selectPetPanel = new HBox(prevPetBtn, nextPetBtn, timePassBtn);
        selectPetPanel.setSpacing(40);
        selectPetPanel.setPadding(new Insets(10, 0, 10, 0));
        selectPetPanel.setAlignment(Pos.CENTER_RIGHT);

        // Overall hbox for displaying the adopt and select pet panels
        HBox ctrlPanel = new HBox(adoptPanel, selectPetPanel);
        ctrlPanel.setSpacing(40);
        ctrlPanel.setPadding(new Insets(10, 0, 10, 0)); // styling
        ctrlPanel.setAlignment(Pos.CENTER);

        // Creating a root for the main scene as a BorderPane layout
        // Set the top to be the topCtrlPanel, the center to be homePane, and bottom to be ctrlanel
        BorderPane root = new BorderPane();
        root.setTop(topCtrlPanel);
        root.setCenter(homePane);
        root.setBottom(ctrlPanel);

        // Creating the main 1800 x 800 scene
        scene = new Scene(root, 1800, 800);

    }

    /**
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Not resizeable
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pet");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Method to launch the application
     * @param args
     */
    public static void main(String[] args) {
        //  Launch the standalone application
        launch(args);
    }
}

