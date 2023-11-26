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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
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
    
//****
    card[] cards_array;

    public gameplayScene(Stage primaryStage) {
        super(new VBox(), 440, 350);

        Answer1.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        Answer1.setTextFill(Color.WHITE);
        Answer1.setBackground(transparentBackground);
        Answer1.setEffect(dropShadow);
       
        
        Answer2.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        Answer2.setTextFill(Color.WHITE);
        Answer2.setBackground(transparentBackground);
        Answer2.setEffect(dropShadow);
        
        
        Answer3.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        Answer3.setTextFill(Color.WHITE);
        Answer3.setBackground(transparentBackground);
        Answer3.setEffect(dropShadow);
     

        Answer4.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        Answer4.setTextFill(Color.WHITE);
        Answer4.setBackground(transparentBackground);
        Answer4.setEffect(dropShadow);
       
        
        incorrectPLabel.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        incorrectPLabel.setTextFill(Color.WHITE);
        incorrectPLabel.setBackground(orangeBackground);
        incorrectPLabel.setEffect(dropShadow);
        incorrectPLabel.setTextAlignment(TextAlignment.CENTER);
        
        
        currentScoreLabel.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        currentScoreLabel.setTextFill(Color.WHITE);
        currentScoreLabel.setBackground(transparentBackground);
        currentScoreLabel.setEffect(dropShadow);
        currentScoreLabel.setTextAlignment(TextAlignment.CENTER);
       
        
        correctPLabel.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        correctPLabel.setTextFill(Color.WHITE);
        correctPLabel.setBackground(orangeBackground);
        correctPLabel.setEffect(dropShadow);
        correctPLabel.setTextAlignment(TextAlignment.CENTER);
        
        
        nextButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        nextButton.setTextFill(Color.WHITE);
        nextButton.setBackground(orangeBackground);
        nextButton.setEffect(dropShadow);
        nextButton.setTextAlignment(TextAlignment.CENTER);
        
        
        if (gamemodeInt == 0) {
            // difficulty
            cards_array = DB_CardInteract.allCardsIncreasingDifficulty();
        }
        else if (gamemodeInt == 1) {
//score
            cards_array = DB_CardInteract.allCardsIncreasingScore(launcher.user_ID);
        }
        else if (gamemodeInt == 2) {
            cards_array = DB_CardInteract.returnAllCards();
            shuffle_array(cards_array);
        }
        else {
            System.out.println("Error: gamemodeInt not set");
        }
//random
        Label questionLabel = new Label(cards_array[i].Question_content);
        questionLabel.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        questionLabel.setTextFill(Color.WHITE);
        questionLabel.setBackground(orangeBackground);
        questionLabel.setEffect(dropShadow);
        questionLabel.setTextAlignment(TextAlignment.CENTER);
        
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
            }
            else {
                DB_PlayHistory.addHistory(launcher.user_ID, score, wins, losses);

                Stage stage = (Stage) nextButton.getScene().getWindow();
                primaryStage.setScene(postgameScene.createScene(primaryStage, score));
                primaryStage.setFullScreen(true);
                resetScore();
            };

            System.out.println(score);

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
        });

    Answer1.setText(cards_array[i].Question_answers_arr[0]);
    Answer2.setText(cards_array[i].Question_answers_arr[1]);
    Answer3.setText(cards_array[i].Question_answers_arr[2]);
    Answer4.setText(cards_array[i].Question_answers_arr[3]);
    HBox answer1 = new HBox(Answer1ckBox, Answer1);
    HBox answer2 = new HBox(Answer2ckBox, Answer2);
    HBox answer3 = new HBox(Answer3ckBox, Answer3);
    HBox answer4 = new HBox(Answer4ckBox, Answer4);
    correctP.getContent().add(correctPLabel);
    incorrectP.getContent().add(incorrectPLabel);


    VBox CenterGameLay = new VBox();
    CenterGameLay.setAlignment(Pos.CENTER);
    CenterGameLay.getChildren().addAll(questionLabel, answer1, answer2, answer3, answer4 , nextButton);

    BorderPane GameLay = new BorderPane();
    GameLay.setStyle("-fx-background-color: #FFD966;");
    GameLay.setPadding(offset);
    GameLay.setBottom(currentScoreLabel);
    GameLay.setCenter(CenterGameLay);

    VBox root = (VBox) this.getRoot();
    root.getChildren().addAll(GameLay);
    
}
    public static gameplayScene createScene(Stage primaryStage) {
    	
        
        return new gameplayScene(primaryStage);
    }

    public static int AnswerSelection(CheckBox Answer1ckBox, CheckBox Answer2ckBox, CheckBox Answer3ckBox, CheckBox Answer4ckBox) {
        // test if more than one answer is selected
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
}

