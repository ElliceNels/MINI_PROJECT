package mainPackage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;


public class questionioScene extends Scene {
    //Controls defined
    static Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    static Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    static DropShadow dropShadow = new DropShadow();
    Image CUizLogo = new Image(getClass().getResourceAsStream("CUiz Logo.png"));
    Insets offset = new Insets(10,10,10,10);
    Button doneButton = new Button("Done");
    ImageView CUizView = new ImageView();
    ImageView CUizView2 = new ImageView();
    TextField questionInTextfield= new TextField();
    TextField rightAnswerInTextfield= new TextField();
    TextField wrongAnswer1InTextfield= new TextField();
    TextField wrongAnswer2InTextfield= new TextField();
    TextField wrongAnswer3InTextfield= new TextField();
    Button returnMainButton = new Button("Return to Main Menu");

    public questionioScene(Stage primaryStage) {
        super(new VBox(), 440, 350);

        //Cosmetics and Actions
        CUizView.setFitWidth(440);
        CUizView.setFitHeight(250);
        CUizView.setImage(CUizLogo);

        CUizView2.setImage(CUizLogo);

        loginScene.textfieldCosmetics(questionInTextfield,"Input Question", dropShadow);

        loginScene.textfieldCosmetics(rightAnswerInTextfield, "Input Correct Answer", dropShadow);

        loginScene.textfieldCosmetics(wrongAnswer1InTextfield, "Input Wrong Answer 1", dropShadow);

        loginScene.textfieldCosmetics(wrongAnswer2InTextfield, "Input Wrong Answer 2", dropShadow);

        loginScene.textfieldCosmetics(wrongAnswer3InTextfield, "Input Wrong Answer 3", dropShadow);

        homeScene.buttonCosmetics(returnMainButton, orangeBackground, dropShadow, 18);
        returnMainButton.setOnAction(e -> {
            Stage stage = (Stage) returnMainButton.getScene().getWindow();
            primaryStage.setScene(mainmenuScene.createScene(stage));
        });

        homeScene.buttonCosmetics(doneButton, transparentBackground, dropShadow,18);
        doneButton.setOnAction(e -> storeNewQuestion(questionInTextfield, rightAnswerInTextfield, wrongAnswer1InTextfield, wrongAnswer2InTextfield, wrongAnswer3InTextfield, primaryStage));

        // Add layout to the scene
        VBox root = (VBox) this.getRoot();
        root.getChildren().addAll(layoutMaker());

    }
    public VBox centerLayout(){
        VBox CenterQuestionIOLay = new VBox(50);
        CenterQuestionIOLay.setAlignment(Pos.CENTER);
        CenterQuestionIOLay.getChildren().addAll(questionInTextfield, rightAnswerInTextfield, wrongAnswer1InTextfield, wrongAnswer2InTextfield, wrongAnswer3InTextfield, doneButton);

        return CenterQuestionIOLay;
    }

    public BorderPane layoutMaker(){
        BorderPane QuestionIOLay = new BorderPane();
        QuestionIOLay.setStyle("-fx-background-color: #FFD966;");
        QuestionIOLay.setPadding(offset);
        QuestionIOLay.setTop(CUizView2);
        QuestionIOLay.setAlignment(CUizView2, Pos.CENTER);
        QuestionIOLay.setCenter(centerLayout());
        QuestionIOLay.setBottom(returnMainButton);
        QuestionIOLay.setPrefSize(1080, 1000);

        return QuestionIOLay;
    }
    public static questionioScene createScene(Stage primaryStage) {
        return new questionioScene(primaryStage);
    }

    public static void SuccessPopUp(Popup popup, Stage stage) {
        Timeline popupTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> {
                    popup.show(stage);
                }),
                new KeyFrame(Duration.seconds(3), e -> {
                    popup.hide();
                }));
        popupTimeline.setCycleCount(1);
        popupTimeline.playFromStart();
    }

    public static void storeNewQuestion(TextField questionInTextfield, TextField rightAnswerInTextfield, TextField wrongAnswer1InTextfield, TextField wrongAnswer2InTextfield, TextField wrongAnswer3InTextfield, Stage primaryStage){

        //generate string USR+random number
        int rand = (int)(Math.random() * 500)+1;
        String card_id= "usr" + rand;

        //add card to database
        card user_manual = new card(card_id, questionInTextfield.getText(), rightAnswerInTextfield.getText() + ";" + wrongAnswer1InTextfield.getText() + ";" + wrongAnswer2InTextfield.getText() + ";" + wrongAnswer3InTextfield.getText(), 0, 0, 0);
        DB_CardInteract.addCardToDB(user_manual);
        Popup successfulQAddp = new Popup();
        Label successfulQAddpLabel = new Label("Question Successfully added");
        successfulQAddp.getContent().add(successfulQAddpLabel);
        SuccessPopUp(successfulQAddp, primaryStage);
    }
}


