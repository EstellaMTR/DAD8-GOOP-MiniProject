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
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

public class PetApp extends Application {
    private Scene scene;
    private Home home;
    private Button addPetBtn;
    private PetView petView;

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

        Label nameLabel = new Label("Pet Name: ");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter the name of the cat: ");
        Label ageLabel = new Label("Pet Age: ");
        TextField ageInput = new TextField();
        ageInput.setPromptText("Enter the age of the  cat: ");
        Label descriptionLabel = new Label("Pet Description: ");
        TextField descriptionInput = new TextField();
        descriptionInput.setPromptText("Describe your cat: ");
//        TODO: Handle errors
        Button submitBtn = new Button("Add pet");
        Label errorLabel = new Label("");
        submitBtn.setOnAction(event -> {
            // TODO: make so not only cats when adopting
            try{
                RadioButton selected = (RadioButton) petsGroup.getSelectedToggle();
                String name = nameInput.getText();
                int age = Integer.parseInt(ageInput.getText());
                String description = descriptionInput.getText();
                home.addPet(name, age, description, selected.getText());
                popup.hide();
            } catch (NumberFormatException ne){
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
//            try {
//                Image pic = new Image( new FileInputStream("src/assets/Cats/drculacat.png"));
//                relocate(500, 500);
//
//                this.setImage(pic);
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
        }

        @Override
        public void update(Observable o, Object arg){
            if (o instanceof Home){
                Animal pet = ((Home)o).getCurrentPet();
                try {
                    if (pet instanceof Cat){
                        Image pic = new Image( new FileInputStream("src/assets/Cats/drculacat.png"));
                        relocate(500, 500);

                        this.setImage(pic);
                    } else if (pet instanceof Dog) {
                        Image pic = new Image( new FileInputStream("src/assets/Dogs/Dogs.png"));
                        relocate(500, 500);

                        this.setImage(pic);
                    }
                }  catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    @Override
    public void init() {
        home = new Home();
        petView = new PetView();

        home.addObserver(petView);

        Pane homePane = new Pane();
        homePane.setStyle("-fx-background-image: url(assets/Scenes/Hall.png);" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center;" +
                "-fx-background-size: contain;");
        homePane.getChildren().add(petView);
        homePane.setPrefSize(1800, 600);
//        homePane.getChildren().add(duckView);

//
//        Text label = new Text("Select a strategy:");
//
//        HBox strategyBar = new HBox(label, rndSwim, diagSwim, stillSwim);
//        strategyBar.setAlignment(Pos.BASELINE_CENTER);
//        strategyBar.setSpacing(5);
//
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


        HBox selectPetPanel = new HBox(prevPetBtn, nextPetBtn);
        selectPetPanel.setSpacing(40);
        selectPetPanel.setPadding(new Insets(10, 0, 10, 0));
        selectPetPanel.setAlignment(Pos.CENTER_RIGHT);

        HBox ctrlPanel = new HBox(adoptPanel, selectPetPanel);
        ctrlPanel.setSpacing(40);
        ctrlPanel.setPadding(new Insets(10, 0, 10, 0)); // styling
        ctrlPanel.setAlignment(Pos.CENTER);

        // We create a root for the main scene as a BorderPane layout
        BorderPane root = new BorderPane();
        root.setCenter(homePane);
        root.setBottom(ctrlPanel);
//        root.setTop(coords);

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

