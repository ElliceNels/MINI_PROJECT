package mainPackage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;


public class signupScene extends Scene{

    //Controls defined
    Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    DropShadow dropShadow = new DropShadow();
	Insets offset = new Insets(10,10,10,10);
    Button ReturnloginButton = new Button("Back to Login Page");
    Button createAccountButton = new Button("Create Account");
    TextField newUsernameField = new TextField();
    PasswordField newPasswordField = new PasswordField();
    PasswordField confirmPasswordField = new PasswordField();
    Popup existingUserPopup = new Popup();
    Label existingUserPLabel = new Label();
    public signupScene(Stage primaryStage) {
        super(new VBox(), 450, 250);

        //Cosmetics and Action
        homeScene.buttonCosmetics(ReturnloginButton, orangeBackground, dropShadow, 15);
            ReturnloginButton.setOnAction(e -> {
                Stage stage = (Stage) ReturnloginButton.getScene().getWindow();
                stage.setScene(loginScene.createScene(stage));
            });
        homeScene.buttonCosmetics(createAccountButton, orangeBackground, dropShadow, 15);
        loginScene.textfieldCosmetics(newUsernameField, "New Username", dropShadow);
        loginScene.textfieldCosmetics(newPasswordField, "New Password", dropShadow);
        loginScene.textfieldCosmetics(confirmPasswordField, "Confirm Password", dropShadow);

        existingUserPLabel.setStyle("-fx-background-color:#D5D5D5; -fx-font-size:10;");
        existingUserPopup.getContent().add(existingUserPLabel);

        //Add layout to scene
        VBox root = (VBox) this.getRoot();
        root.getChildren().add(layoutMaker());

        SIGNUP(createAccountButton, newUsernameField, newPasswordField, confirmPasswordField, existingUserPopup, primaryStage, existingUserPLabel);
    }

    public static signupScene createScene(Stage primaryStage) {
        return new signupScene(primaryStage);
    }

    public static boolean passwordCheck(String password, Popup existingUserPopup, Stage primaryStage, Label existingUserPLabel) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean length = password.length() >= 8;
        return hasUppercase && hasLowercase && hasNumber && length;
    }

    public static void SIGNUP(Button createAccountButton, TextField newUsernameField, PasswordField newPasswordField, PasswordField confirmPasswordField, Popup existingUserPopup, Stage primaryStage, Label existingUserPLabel){
        createAccountButton.setOnAction(e -> {
            //Changes user input into String to check in db
            String user = newUsernameField.getText();
            String pass = newPasswordField.getText();
            String confirmPass = confirmPasswordField.getText();

            //tests whether the passwords are equal
            if (Objects.equals(confirmPass, pass)) {

                if (passwordCheck(pass, existingUserPopup, primaryStage, existingUserPLabel) && DB_UserInteract.insert(user, pass) ){ // new user name and password stored if it doesn't exist, and password is valid
                    System.out.println("New user created successfully");
                    Stage stage = (Stage) createAccountButton.getScene().getWindow();
                    stage.setScene(homeScene.createScene(stage));

                }else if (!passwordCheck(pass, existingUserPopup, primaryStage, existingUserPLabel)){
                    System.out.println("Password is invalid");
                    questionioScene.SuccessPopUp(existingUserPopup,primaryStage);
                    existingUserPLabel.setText("""
                            Your password must: be 8 digits long
                            have an upper and lower case letter
                            contain a number
                            """);

                }
                else {
                    System.out.println("user exists?");
                    questionioScene.SuccessPopUp(existingUserPopup,primaryStage);
                    existingUserPLabel.setText("Something has gone wrong, most likely the username already exists. Try again. ");

                }
            }else {
                System.out.println("Passwords do not match.. ");
                loginScene.colorChange(confirmPasswordField, newPasswordField);

            }
        });
    }

    public GridPane layoutMaker(){
        GridPane SignUpLay = new GridPane();
        SignUpLay.setStyle("-fx-background-color: #FFD966;");
        SignUpLay.setPadding(offset);
        SignUpLay.setVgap(10);
        SignUpLay.setHgap(5);
        SignUpLay.setAlignment(Pos.CENTER);
        //Children addition and positioning
        SignUpLay.setConstraints(ReturnloginButton, 3, 5);
        SignUpLay.setConstraints(createAccountButton, 3, 4);
        SignUpLay.setConstraints(newUsernameField, 3, 1);
        SignUpLay.setConstraints(newPasswordField, 3, 2);
        SignUpLay.setConstraints(confirmPasswordField, 3, 3);
        SignUpLay.getChildren().addAll(ReturnloginButton, createAccountButton, newUsernameField, newPasswordField, confirmPasswordField);
        SignUpLay.setPrefSize(1080, 1000);

        return SignUpLay;
    }
}