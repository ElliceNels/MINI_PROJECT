package mainPackage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class loginScene extends Scene{
    Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    DropShadow dropShadow = new DropShadow();
	Insets offset = new Insets(10,10,10,10);
    Button confirmButton = new Button("Confirm");
    Button ReturnsignUpButton = new Button("Back to Sign Up");
    TextField usernameField = new TextField();
    PasswordField passwordField = new PasswordField();

    public loginScene(Stage ignoredPrimaryStage) {
        super(new VBox(), 450, 250);

        //Cosmetics and Actions
        homeScene.buttonCosmetics(confirmButton, orangeBackground, dropShadow, 15);

        homeScene.buttonCosmetics(ReturnsignUpButton, orangeBackground, dropShadow, 15);
            ReturnsignUpButton.setOnAction(e -> {
                Stage stage = (Stage) ReturnsignUpButton.getScene().getWindow();
                stage.setScene(signupScene.createScene(stage));
            });

        textfieldCosmetics(usernameField, "Username", dropShadow);

        textfieldCosmetics(passwordField, "Password", dropShadow);

        LOGIN(confirmButton, usernameField, passwordField);

        //Add layout to scene
        VBox root = (VBox) this.getRoot();
        root.getChildren().addAll(layoutMaker());
    }

    public static loginScene createScene(Stage primaryStage) {
        return new loginScene(primaryStage);
    }

    public static void colorChange(TextField usernameField, PasswordField passwordField) {
        //To store keyframes
        Timeline colorChangeTimeline = new Timeline(

                //Change the text field's background color for 1 seconds
                new KeyFrame(Duration.seconds(0), e -> {
                    usernameField.setStyle("-fx-background-color: red;");
                    passwordField.setStyle("-fx-background-color: red;");

                }),
                //Revert the color after 3 seconds
                new KeyFrame(Duration.seconds(1), e -> {
                    usernameField.setStyle("");
                    passwordField.setStyle("");
                }));

        //How many times it plays the sequence
        colorChangeTimeline.setCycleCount(1);
        //Play sequence
        colorChangeTimeline.playFromStart();

    }

    public static void textfieldCosmetics(TextField Field, String promptText, DropShadow dropShadow){
        Field.setPromptText(promptText);
        Field.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 14));
        Field.setEffect(dropShadow);
        Field.setAlignment(Pos.CENTER);
    }

    public void LOGIN(Button confirmButton, TextField usernameField, PasswordField passwordField){
        confirmButton.setOnAction(e -> {
            //Changes user input into String to check in db
            String user = usernameField.getText();
            String pass = passwordField.getText();

            //Checks whether user name and password exists
            if (!DB_UserInteract.loginCheck(user, pass)) {

                //If it doesnt exist then go red
                colorChange(usernameField, passwordField);
                System.out.print("Username or password does not match. Try again");
            }else {
                //If it exists then login in
                System.out.print("Successful login");
                launcher.user_ID = user;
                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.setScene(mainmenuScene.createScene(stage));
                stage.setFullScreen(true);
            }
        });
    }

    public GridPane layoutMaker(){
        GridPane LoginLay = new GridPane();
        LoginLay.setStyle("-fx-background-color: #FFD966;");
        LoginLay.setPadding(offset);
        LoginLay.setVgap(10);
        LoginLay.setHgap(5);
        LoginLay.setAlignment(Pos.CENTER);
        //Children addition and positioning
        LoginLay.setConstraints(ReturnsignUpButton, 2 , 4);
        LoginLay.setConstraints(usernameField, 2, 1);
        LoginLay.setConstraints(passwordField, 2, 2);
        LoginLay.setConstraints(confirmButton, 2, 3);
        LoginLay.getChildren().addAll(ReturnsignUpButton, usernameField, passwordField, confirmButton);
        LoginLay.setPrefSize(1080,1000);

        return LoginLay;
    }
}
