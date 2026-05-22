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
            } // 
            catch (NumberFormatException ne){
                errorLabel.setText("\"" + ageInput.getText() + "\"" + " is not a number");
                errorLabel.setStyle("-fx-text-fill: red;");
            }
            catch (Exception e){
                errorLabel.setText(e.getMessage());
                errorLabel.setStyle("-fx-text-fill: red;");
            }
        });
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-border-color: gray; -fx-border-width: 1;");

        layout.getChildren().addAll(catButton, dogButton, nameLabel, nameInput, ageLabel, ageInput, descriptionLabel, descriptionInput, submitBtn, errorLabel);
        popup.getContent().add(layout);
        popup.show(scene.getWindow());
    }

    private class PetView extends ImageView implements Observer {
        public PetView() {
        }

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

    private class PetLabel extends Text implements Observer{

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

    private class PetStats extends Text implements Observer{

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

    @Override
    public void init() {
        home = new Home();
        petView = new PetView();
        petStats = new PetStats();
        petLabel = new PetLabel();

        home.addObserver(petView);
        home.addObserver(petStats);
        home.addObserver(petLabel);

        Pane homePane = new Pane();
        homePane.setStyle("-fx-background-image: url(assets/Scenes/Hall.png);" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center;" +
                "-fx-background-size: contain;");
        homePane.getChildren().add(petView);
        homePane.setPrefSize(1800, 600);

        feedSpinner = new Spinner<Integer>();
        feedSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0, 5));
        feedSpinner.setVisible(false);
        feedSpinner.setEditable(true);

        feedBtn = new Button("Feed");
        feedBtn.setVisible(false);
        feedBtn.setOnAction(event -> {
            home.currentPetFeed(feedSpinner.getValue());
        });

        HBox feedBox = new HBox(0, feedSpinner, feedBtn);
        feedBox.setAlignment(Pos.CENTER);

        infoIcon = new Label("ⓘ");
        infoIcon.setFont(Font.font("System", FontWeight.BOLD, 16));
        infoIcon.setStyle("-fx-text-fill: #1565C0; -fx-cursor: hand;");

        // 2. Create the hover text (Tooltip)
        Tooltip tooltip = new Tooltip("Different pet types have different sleep needs. \n Sleeping more than a certain amount of hours for each type will fully rest the pet.");
        tooltip.setFont(Font.font(12));

        // 3. Optional: Speed up how fast the text appears on hover
        tooltip.setShowDelay(Duration.millis(100));

        // 4. Attach the tooltip to the icon
        infoIcon.setTooltip(tooltip);
        infoIcon.setVisible(false);

        sleepSpinner = new Spinner<Integer>();
        sleepSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 14, 0, 2));
        sleepSpinner.setVisible(false);
        sleepSpinner.setEditable(true);

        sleepBtn = new Button("Sleep");
        sleepBtn.setVisible(false);
        sleepBtn.setOnAction(event ->{
            home.currentPetSleep(sleepSpinner.getValue());
        });
        HBox sleepBox = new HBox(0, sleepSpinner, sleepBtn, infoIcon);
        sleepBox.setAlignment(Pos.CENTER);

        timePassBtn = new Button("Pass time");
        timePassBtn.setOnAction(event -> home.timePass());

        HBox petInfoPanel = new HBox(petLabel);
        petInfoPanel.setSpacing(40);
        petInfoPanel.setPadding(new Insets(10, 0, 10, 0)); // styling
        petInfoPanel.setAlignment(Pos.CENTER);

        HBox statsPanel = new HBox(petStats);
        statsPanel.setSpacing(40);
        statsPanel.setPadding(new Insets(10, 0, 10, 0)); // styling
        statsPanel.setAlignment(Pos.CENTER);

        HBox topCtrlPanel = new HBox(petInfoPanel, statsPanel, feedBox, sleepBox);
        topCtrlPanel.setSpacing(40);
        topCtrlPanel.setPadding(new Insets(10, 0, 10, 0)); // styling
        topCtrlPanel.setAlignment(Pos.CENTER);

        addPetBtn = new Button("Adopt Pet");
        addPetBtn.setOnAction(event -> addPetPopup());

        HBox adoptPanel = new HBox(addPetBtn);
        adoptPanel.setSpacing(40);
        adoptPanel.setPadding(new Insets(10, 0, 10, 0)); // styling
        adoptPanel.setAlignment(Pos.CENTER);

        Button prevPetBtn = new Button("Previous Pet");
        prevPetBtn.setOnAction(event -> home.previousPet());
        Button nextPetBtn = new Button("Next Pet");
        nextPetBtn.setOnAction(event -> home.nextPet());

        HBox selectPetPanel = new HBox(prevPetBtn, nextPetBtn, timePassBtn);
        selectPetPanel.setSpacing(40);
        selectPetPanel.setPadding(new Insets(10, 0, 10, 0));
        selectPetPanel.setAlignment(Pos.CENTER_RIGHT);

        HBox ctrlPanel = new HBox(adoptPanel, selectPetPanel);
        ctrlPanel.setSpacing(40);
        ctrlPanel.setPadding(new Insets(10, 0, 10, 0)); // styling
        ctrlPanel.setAlignment(Pos.CENTER);

        // We create a root for the main scene as a BorderPane layout
        BorderPane root = new BorderPane();
        root.setTop(topCtrlPanel);
        root.setCenter(homePane);
        root.setBottom(ctrlPanel);

        // We crete the main 600x600 scene
        scene = new Scene(root, 1800, 800);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pet");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        //  Lunch the standalone application
        launch(args);
    }
}

