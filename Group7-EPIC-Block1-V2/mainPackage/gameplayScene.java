package mainPackage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class gameplayScene extends Scene {
    Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
    DropShadow dropShadow = new DropShadow();
    static int gamemodeInt;
    Label Answer1 = new Label("Ans1");
    Label Answer2 = new Label("Ans2");
    Label Answer3 = new Label("Ans3");
    Label Answer4 = new Label("Ans4");
    Popup incorrectP = new Popup();
    Label incorrectPLabel = new Label("Incorrect");
    Popup correctP = new Popup();
    Label correctPLabel = new Label("Correct");
    Insets offset = new Insets(10,10,10,10);
    CheckBox Answer1ckBox = new CheckBox();
    CheckBox Answer2ckBox = new CheckBox();
    CheckBox Answer3ckBox = new CheckBox();
    CheckBox Answer4ckBox = new CheckBox();
    static int score;
    static int wins;
    static int losses;
    static int i = 0;
    Label currentScoreLabel = new Label("Current Score:" + score);
    Button nextButton = new Button("Next");
    HBox answer1 = new HBox(Answer1ckBox, Answer1);
    HBox answer2 = new HBox(Answer2ckBox, Answer2);
    HBox answer3 = new HBox(Answer3ckBox, Answer3);
    HBox answer4 = new HBox(Answer4ckBox, Answer4);

    public gameplayScene(Stage primaryStage) {
        super(new VBox(), 440, 350);

        homeScene.labelCosmetics(Answer1, 20, transparentBackground, dropShadow);

        homeScene.labelCosmetics(Answer2, 20, transparentBackground, dropShadow);

        homeScene.labelCosmetics(Answer3, 20, transparentBackground, dropShadow);

        homeScene.labelCosmetics(Answer4, 20, transparentBackground, dropShadow);

        homeScene.labelCosmetics(incorrectPLabel, 20, orangeBackground, dropShadow);
        incorrectP.getContent().add(incorrectPLabel);

        homeScene.labelCosmetics(currentScoreLabel, 20, transparentBackground, dropShadow);

        homeScene.labelCosmetics(correctPLabel, 20, orangeBackground, dropShadow);
        correctP.getContent().add(correctPLabel);

        homeScene.buttonCosmetics(nextButton, orangeBackground, dropShadow, 20);

        card[] cards_array = setGamemode();

        Label questionLabel = new Label(cards_array[i].Question_content);
        homeScene.labelCosmetics(questionLabel, 20, orangeBackground, dropShadow);

        setAnswers(questionLabel, cards_array);
        nextButtonAction(primaryStage, questionLabel, cards_array);

    VBox root = (VBox) this.getRoot();
    root.getChildren().addAll(layoutMaker(questionLabel));

}
    public static gameplayScene createScene(Stage primaryStage) {
        return new gameplayScene(primaryStage);
    }

    public card[] setGamemode(){
        card[] cards_array;
        if (gamemodeInt == 0) {
            // difficulty
            cards_array = DB_CardInteract.allCardsIncreasingDifficulty();
        }
        else if (gamemodeInt == 1) {
            //score
            cards_array = DB_CardInteract.allCardsIncreasingScore(launcher.user_ID);
        }
        else if (gamemodeInt == 2) {
            //random
            cards_array = DB_CardInteract.returnAllCards();
            shuffle_array(cards_array);
        }
        else {
            System.out.println("Error: gamemodeInt not set");
            cards_array = null;
        }
        return cards_array;
    }

    public void nextButtonAction(Stage primaryStage, Label questionLabel, card[] cards_array){
        nextButton.setOnAction(e -> {
            Answer3ckBox.setVisible(true);
            Answer4ckBox.setVisible(true);
            if (AnswerSelection(Answer1ckBox, Answer2ckBox, Answer3ckBox, Answer4ckBox) == cards_array[i].Question_correct_answer){
                // if correct, add 1 to score, and add win to scores_db and change score_db by 1
                DB_ScoreInteract.addWin(launcher.user_ID, cards_array[i].card_id);
                score += 1; // local score += 1
                wins += 1; // local wins += 1
                CorrectPopUp(correctP, primaryStage);

            }else {
                DB_ScoreInteract.addLoss(launcher.user_ID, cards_array[i].card_id);
                score -= 1; // local score -= 1
                losses += 1; // local losses += 1
                IncorrectPopUp(incorrectP, primaryStage);
            }

            if (i < cards_array.length - 1) {
                System.out.println("i = " + i);
                System.out.println("cards_array.length = " + cards_array.length);
                i++;
            } else {
                DB_PlayHistory.addHistory(launcher.user_ID, score, wins, losses);
                Stage stage = (Stage) nextButton.getScene().getWindow();
                stage.setScene(postgameScene.createScene(primaryStage, score));
                primaryStage.setFullScreen(true);
                resetScore();
            }
            System.out.println(score);
            setAnswers(questionLabel, cards_array);
        });
    }

    public void setAnswers(Label questionLabel, card[] cards_array){
        questionLabel.setText(cards_array[i].Question_content);
        currentScoreLabel.setText("Current Score:" + score);
        //** if array length
        if (cards_array[i].Question_answers_arr.length == 4) {
            Answer1.setText(cards_array[i].Question_answers_arr[0]);
            Answer2.setText(cards_array[i].Question_answers_arr[1]);
            Answer3.setText(cards_array[i].Question_answers_arr[2]);
            Answer4.setText(cards_array[i].Question_answers_arr[3]);


        }else if (cards_array[i].Question_answers_arr.length == 3) {
            Answer1.setText(cards_array[i].Question_answers_arr[0]);
            Answer2.setText(cards_array[i].Question_answers_arr[1]);
            Answer3.setText(cards_array[i].Question_answers_arr[2]);
            Answer4.setText(" ");
            Answer4ckBox.setVisible(false);
        }else{
            Answer1.setText(cards_array[i].Question_answers_arr[0]);
            Answer2.setText(cards_array[i].Question_answers_arr[1]);
            Answer3.setText(" ");
            Answer4.setText(" ");
            Answer3ckBox.setVisible(false);
            Answer4ckBox.setVisible(false);
        }
    }

    public static int AnswerSelection(CheckBox Answer1ckBox, CheckBox Answer2ckBox, CheckBox Answer3ckBox, CheckBox Answer4ckBox) {
        // test if more than one answer is selected (else)
        int total = 0;
        int user_answer = 9;
        if (Answer1ckBox.isSelected()) {
            user_answer = 0;
            total += 1;
        }
        if(Answer2ckBox.isSelected()){
            user_answer = 1;
            total += 1;
        }
        if(Answer3ckBox.isSelected()) {
            user_answer = 2;
            total += 1;
        }
        if(Answer4ckBox.isSelected()) {
            user_answer = 3;
            total += 1;
        }
        if (total == 1) {
            return user_answer;
        }
        return 9;
    }

    public static void shuffle_array(card[] cards_array){ //https://www.digitalocean.com/community/tutorials/shuffle-array-java#2-shuffle-array-using-random-class
        // shuffle array
        for (int i = 0; i < cards_array.length; i++) {
            int randomIndexToSwap = (int) (Math.random() * cards_array.length);
            card temp = cards_array[randomIndexToSwap];
            cards_array[randomIndexToSwap] = cards_array[i];
            cards_array[i] = temp;
        }
    }

    public static void CorrectPopUp(Popup correctP, Stage stage) {
        Timeline popupTimeline = new Timeline( //To store keyframes
                new KeyFrame(Duration.seconds(0), e -> {
                    correctP.show(stage);
                }),
                new KeyFrame(Duration.seconds(2), e -> {
                    correctP.hide();
                }));
        popupTimeline.setCycleCount(1); //How many times it plays the sequence
        popupTimeline.playFromStart();
    }

    public static void IncorrectPopUp(Popup incorrectP, Stage stage) {
        Timeline popupTimeline = new Timeline( //To store keyframes
                new KeyFrame(Duration.seconds(0), e -> {
                    incorrectP.show(stage);
                }),
                new KeyFrame(Duration.seconds(2), e -> {
                    incorrectP.hide();
                }));
        popupTimeline.setCycleCount(1); //How many times it plays the sequence
        popupTimeline.playFromStart();
    }

    public static void resetScore() {
        score = 0;
        wins = 0;
        losses = 0;
        i = 0;
    }

    public VBox centerLayout(Label questionLabel){
        VBox CenterGameLay = new VBox(10);
        CenterGameLay.setAlignment(Pos.CENTER);
        CenterGameLay.getChildren().addAll(questionLabel, answer1, answer2, answer3, answer4 , nextButton);
        CenterGameLay.setPrefSize(1080,900);

        return CenterGameLay;
    }

    public BorderPane layoutMaker(Label questionLabel){
        BorderPane GameLay = new BorderPane();
        GameLay.setStyle("-fx-background-color: #FFD966;");
        GameLay.setPadding(offset);
        GameLay.setBottom(currentScoreLabel);
        GameLay.setPrefSize(1080,900);
        GameLay.setCenter(centerLayout(questionLabel));

        return GameLay;
    }
}

