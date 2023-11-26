package mainPackage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.CheckBox;

import java.util.concurrent.atomic.AtomicInteger;

public class mainPlayGui extends Application {

	Scene mainQuiz;
	Scene playModes;
	Scene stats;
	Scene questionIO;
	Scene game;
	Scene postGame;
	Scene home;
	static Stage stage;


	int i = 0;
	int answer = 0;
	public static void main(String[] args) {
		launch(args);  //method in application class that sets up javafx app (setup)

	}


	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("The CUiz"); //Window title
		String user_ID = "Sean";
		card[] cards_array = DB_CardInteract.allCardsIncreasingDifficulty();
		AtomicInteger score = new AtomicInteger();
		AtomicInteger wins = new AtomicInteger();
		AtomicInteger losses = new AtomicInteger();
		//Controls
		//------------------------------------------------------------------------
			//mainQuiz controls
		Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
		Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
		
		DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(8); // Set the radius of the shadow
        dropShadow.setColor(Color.BLACK);
		
		Button playButton = new Button("-Play-");
		playButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 40));
		playButton.setTextFill(Color.WHITE);
		playButton.setBackground(transparentBackground);
		playButton.setEffect(dropShadow);
		playButton.setTextAlignment(TextAlignment.CENTER);
        playButton.setOnAction(e -> stage.setScene(playModes));
        
        Button statsButton = new Button("Play statistics");
        statsButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        statsButton.setTextFill(Color.WHITE);
        statsButton.setBackground(transparentBackground);
        statsButton.setEffect(dropShadow);
        playButton.setTextAlignment(TextAlignment.CENTER);
        statsButton.setOnAction(e -> stage.setScene(stats));
        
		
        Button questionIOButton = new Button("Input your own questions");
        questionIOButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        questionIOButton.setTextFill(Color.WHITE);
        questionIOButton.setBackground(transparentBackground);
        questionIOButton.setEffect(dropShadow);
        playButton.setTextAlignment(TextAlignment.CENTER);
        questionIOButton.setOnAction(e -> stage.setScene(questionIO));
        
        Button logoutButton = new Button("Log out");
        logoutButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        logoutButton.setTextFill(Color.WHITE);
        logoutButton.setBackground(transparentBackground);
        logoutButton.setEffect(dropShadow);
        logoutButton.setTextAlignment(TextAlignment.CENTER);
        logoutButton.setOnAction(e -> stage.setScene(home));//********************When linked, should go to logon page
        
        Label currentUserLabel = new Label("Currently Logged in: " + user_ID ); 
        currentUserLabel.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        currentUserLabel.setTextFill(Color.WHITE);
        
        Label trademarkLabel = new Label("Made by Jack Casey and Ellice Nelson\r\n"
        		+ "“CUiz” is a registered trademark ™\r\n");
        trademarkLabel.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        trademarkLabel.setTextFill(Color.WHITE);
        
        Image CUizLogo = new Image("CUiz Logo.png");
        ImageView CUizView = new ImageView();
        CUizView.setFitWidth(440); 
        CUizView.setFitHeight(250); 
		CUizView.setImage(CUizLogo);
		
		Image CUizPenguin = new Image("CUizPenguin.png");
		ImageView CUizPenguinView = new ImageView();
		CUizPenguinView.setFitWidth(75); 
		CUizPenguinView.setFitHeight(75); 
		CUizPenguinView.setImage(CUizPenguin);
		
		//-------------------------------------------------
			//playModes controls
		Button incDifficultyButton = new Button("Increasing\n" 
				+ "Difficulty");
		incDifficultyButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
		incDifficultyButton.setTextFill(Color.WHITE);
		incDifficultyButton.setBackground(transparentBackground);
		incDifficultyButton.setEffect(dropShadow);
		incDifficultyButton.setTextAlignment(TextAlignment.CENTER);
		incDifficultyButton.setOnAction(e -> {stage.setScene(game);
			//increasingDifficulty_play(user_ID);
		});
		
		Button incScoreButton = new Button("Increasing\n" 
				+ "Score");
		incScoreButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
		incScoreButton.setTextFill(Color.WHITE);
		incScoreButton.setBackground(transparentBackground);
		incScoreButton.setEffect(dropShadow);
		incScoreButton.setTextAlignment(TextAlignment.CENTER);
		incScoreButton.setOnAction(e -> {stage.setScene(game);
			//increasingScore_play(user_ID);  uncomment once connected
		});
		
		Button randomPlayButton = new Button("Random\n" 
				+ "Play");
		randomPlayButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
		randomPlayButton.setTextFill(Color.WHITE);
		randomPlayButton.setBackground(transparentBackground);
		randomPlayButton.setEffect(dropShadow);
		randomPlayButton.setTextAlignment(TextAlignment.CENTER);
		randomPlayButton.setOnAction(e -> {stage.setScene(game);
			//random_play(user_ID); uncomment once connected
		});
		
        ImageView CUizView1 = new ImageView();
        CUizView1.setFitWidth(50); 
        CUizView1.setFitHeight(25);
		CUizView1.setImage(CUizLogo);
		
		ImageView CUizPenguinView1 = new ImageView();
		CUizPenguinView1.setFitWidth(75); 
		CUizPenguinView1.setFitHeight(75);
		CUizPenguinView1.setImage(CUizPenguin);
		
		Label currentUserLabel1 = new Label("Currently Logged in: " + user_ID ); 
        currentUserLabel1.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 10));
        currentUserLabel1.setTextFill(Color.WHITE);
		
		Label chooseModeLabel= new Label("Choose a Game Mode");
		chooseModeLabel.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 40));
		chooseModeLabel.setTextFill(Color.WHITE);
		chooseModeLabel.setBackground(orangeBackground);
		chooseModeLabel.setEffect(dropShadow);
		chooseModeLabel.setTextAlignment(TextAlignment.CENTER);
		
		//-----------------------------------------------------------
			//questionIO controls
		TextField questionInTextfield= new TextField();
		questionInTextfield.setPromptText("Input Question");
		
		TextField rightAnswerInTextfield= new TextField();
		rightAnswerInTextfield.setPromptText("Input Correct Answer");
		
		TextField wrongAnswer1InTextfield= new TextField();
		wrongAnswer1InTextfield.setPromptText("Input Wrong Answer 1");
		
		TextField wrongAnswer2InTextfield= new TextField();
		wrongAnswer2InTextfield.setPromptText("Input Wrong Answer 2");

		TextField wrongAnswer3InTextfield= new TextField();
		wrongAnswer3InTextfield.setPromptText("Input Wrong Answer 3");

		Button returnMainButton = new Button("Return to mainPackage.DB_Handler.Main Menu");
		returnMainButton.setOnAction(e -> stage.setScene(mainQuiz));
		
		Button doneButton = new Button("Done");//******************************
		doneButton.setOnAction(e -> {
			// generate string USR+random number
			// add mainPackage.DB_Handler.card to database
			int rand = (int)(Math.random() * 500)+1;
			// string
			String card_id= "usr" + rand;

			card user_manual = new card(card_id, questionInTextfield.getText(), rightAnswerInTextfield.getText() + ";" + wrongAnswer1InTextfield.getText() + ";" + wrongAnswer2InTextfield.getText() + ";" + wrongAnswer3InTextfield.getText(), 0, 0, 0);
			DB_CardInteract.addCardToDB(user_manual);
			Popup successfulQAddp = new Popup();
			Label successfulQAddpLabel = new Label("Question Successfully added");
			successfulQAddp.getContent().add(successfulQAddpLabel);
			SuccessPopUp(successfulQAddp, stage);
		});
		
		
        ImageView CUizView2 = new ImageView();
		CUizView2.setImage(CUizLogo);
		
		//------------------------------------------------------
			//game controls
		Label questionLabel = new Label(cards_array[i].Question_content);
		
		Label currentScoreLabel = new Label("Current Score:" + score); 
		
		Label Answer1 = new Label("Ans1");
		Answer1.setText(cards_array[i].Question_answers_arr[0]);
        
		Label Answer2 = new Label("Ans2");
		Answer2.setText(cards_array[i].Question_answers_arr[1]);
		
		Label Answer3 = new Label("Ans3");
		Answer3.setText(cards_array[i].Question_answers_arr[2]);
		
		Label Answer4 = new Label("Ans4");
		 Answer4.setText(cards_array[i].Question_answers_arr[3]);
		
		Popup incorrectP = new Popup();
			Label incorrectPLabel = new Label("Incorrect");
			incorrectP.getContent().add(incorrectPLabel);
			
		Popup correctP = new Popup();
			Label correctPLabel = new Label("Correct");
			correctP.getContent().add(correctPLabel);
			
		 

		CheckBox Answer1ckBox = new CheckBox();
		
		CheckBox Answer2ckBox = new CheckBox();
		
		CheckBox Answer3ckBox = new CheckBox();
		
		CheckBox Answer4ckBox = new CheckBox();

		Button nextButton = new Button("Next");
		nextButton.setOnAction(e -> {

			if (AnswerSelection(Answer1ckBox, Answer2ckBox, Answer3ckBox, Answer4ckBox) == cards_array[i].Question_correct_answer){
				// if correct, add 1 to score, and add win to scores_db and change score_db by 1
				DB_ScoreInteract.addWin(user_ID, cards_array[i].card_id);
				score.getAndIncrement(); // local score += 1
				wins.getAndIncrement(); // local wins += 1
				CorrectPopUp(correctP, stage);

			}else {
				DB_ScoreInteract.addLoss(user_ID, cards_array[i].card_id);
				score.getAndDecrement(); // local score -= 1
				losses.getAndIncrement(); // local losses += 1
				IncorrectPopUp(incorrectP, stage);
			}
			if (i < cards_array.length - 1) {
				i++;
			}else {
				stage.setScene(postGame);
			}

			System.out.println(score);

			questionLabel.setText(cards_array[i].Question_content);
			Answer1.setText(cards_array[i].Question_answers_arr[0]);
			Answer2.setText(cards_array[i].Question_answers_arr[1]);
			Answer3.setText(cards_array[i].Question_answers_arr[2]);
			Answer4.setText(cards_array[i].Question_answers_arr[3]);
			currentScoreLabel.setText("Current Score:" + score);

		});

		//postGame contols
		Label gameOverLabel = new Label("Game Over");
		
		Label scoreLabel = new Label("Your total score is:");
		
		Label scoreDisplayLabel = new Label(score + "/18"); 
		
		Button viewStatsButton = new Button("View mainPackage.Statistics");
		viewStatsButton.setOnAction(e -> stage.setScene(stats));
		
		Button returnToMainButton = new Button("Return to mainPackage.DB_Handler.Main Menu");
		returnToMainButton.setOnAction(e -> stage.setScene(mainQuiz));
		
        Insets offset = new Insets(10,10,10,10);
		
		//Layouts
		//---------------------------------------------------------------------------
			//mainQuiz layout
		GridPane CenterMainQuizLay = new GridPane();
		CenterMainQuizLay.setPadding(offset);
		CenterMainQuizLay.setVgap(10);
		CenterMainQuizLay.setHgap(5);
		CenterMainQuizLay.setAlignment(Pos.CENTER);
		CenterMainQuizLay.setHalignment(playButton, HPos.CENTER);
		CenterMainQuizLay.setConstraints(playButton, 1, 0);
		CenterMainQuizLay.setHalignment(statsButton, HPos.CENTER);
		CenterMainQuizLay.setConstraints(statsButton, 1, 2);
		CenterMainQuizLay.setHalignment(questionIOButton, HPos.CENTER);
		CenterMainQuizLay.setConstraints(questionIOButton, 1, 3);
		CenterMainQuizLay.setHalignment(logoutButton, HPos.CENTER);
		CenterMainQuizLay.setConstraints(logoutButton, 1, 4);
		
		CenterMainQuizLay.getChildren().addAll(playButton, statsButton, questionIOButton, logoutButton);

		
		GridPane BottomMainQuizLay = new GridPane();
		BottomMainQuizLay.setVgap(10);
		BottomMainQuizLay.setHgap(5);
		BottomMainQuizLay.setAlignment(Pos.CENTER);
		BottomMainQuizLay.setConstraints(CUizPenguinView, 0, 0);
		BottomMainQuizLay.setConstraints(currentUserLabel, 1, 0);
		BottomMainQuizLay.setConstraints(trademarkLabel, 220, 0);
		BottomMainQuizLay.getChildren().addAll(CUizPenguinView, currentUserLabel,trademarkLabel);

		
		BorderPane MainQuizLay = new BorderPane();
		MainQuizLay.setStyle("-fx-background-color: #FFD966;");
		MainQuizLay.setPadding(offset);
		MainQuizLay.setTop(CUizView);
		MainQuizLay.setAlignment(CUizView, Pos.CENTER);
		MainQuizLay.setBottom(BottomMainQuizLay);
		MainQuizLay.setCenter(CenterMainQuizLay);
		
		mainQuiz = new Scene(MainQuizLay);
		
			//PlayModesLay
		
		GridPane BottomPlayModesLay = new GridPane();
		BottomPlayModesLay.setVgap(10);
		BottomPlayModesLay.setHgap(5);
		BottomPlayModesLay.setAlignment(Pos.CENTER);
		BottomPlayModesLay.setConstraints(CUizPenguinView1, 0, 0);
		BottomPlayModesLay.setConstraints(currentUserLabel1, 1, 0);
		BottomPlayModesLay.getChildren().addAll(CUizPenguinView1, currentUserLabel1);
		
		GridPane CenterPlayModesLay = new GridPane();
		CenterPlayModesLay.setPadding(offset);  
		CenterPlayModesLay.setVgap(10);
		CenterPlayModesLay.setHgap(5);
		CenterPlayModesLay.setAlignment(Pos.CENTER);
		CenterPlayModesLay.setConstraints(incDifficultyButton, 0, 5);
		CenterPlayModesLay.setHalignment(incDifficultyButton, HPos.CENTER);
		CenterPlayModesLay.setConstraints(incScoreButton, 10, 5);
		CenterPlayModesLay.setHalignment(incScoreButton, HPos.CENTER);
		CenterPlayModesLay.setConstraints(randomPlayButton, 5, 5);
		CenterPlayModesLay.setHalignment(randomPlayButton, HPos.CENTER);
		CenterPlayModesLay.getChildren().addAll(incDifficultyButton, incScoreButton, randomPlayButton);
		
		GridPane TopPlayModesLay = new GridPane();
		TopPlayModesLay.setVgap(10);
		TopPlayModesLay.setHgap(5);
		TopPlayModesLay.setAlignment(Pos.CENTER);
		TopPlayModesLay.setConstraints(CUizView1, 2, 1);
		TopPlayModesLay.setConstraints(chooseModeLabel, 0, 8);
		TopPlayModesLay.getChildren().addAll(CUizView1, chooseModeLabel);
		
		
		BorderPane PlayModesLay = new BorderPane();
		PlayModesLay.setStyle("-fx-background-color: #FFD966;");
		PlayModesLay.setPadding(offset);
		PlayModesLay.setTop(TopPlayModesLay);
		PlayModesLay.setBottom(BottomPlayModesLay);
		PlayModesLay.setCenter(CenterPlayModesLay);
		
		playModes = new Scene(PlayModesLay);
		
			//QuestionIO Layout
		VBox CenterQuestionIOLay = new VBox();
		CenterQuestionIOLay.setAlignment(Pos.CENTER);
		CenterQuestionIOLay.getChildren().addAll(questionInTextfield, rightAnswerInTextfield, wrongAnswer1InTextfield, wrongAnswer2InTextfield, wrongAnswer3InTextfield, doneButton);

		
		BorderPane QuestionIOLay = new BorderPane();
		QuestionIOLay.setStyle("-fx-background-color: #FFD966;");
		QuestionIOLay.setPadding(offset);
		QuestionIOLay.setTop(CUizView2);
		QuestionIOLay.setCenter(CenterQuestionIOLay);
		QuestionIOLay.setBottom(returnMainButton);
		
		questionIO = new Scene(QuestionIOLay);
		
			//Game Layout
		HBox answer1 = new HBox(Answer1ckBox, Answer1);
        HBox answer2 = new HBox(Answer2ckBox, Answer2);
        HBox answer3 = new HBox(Answer3ckBox, Answer3);
        HBox answer4 = new HBox(Answer4ckBox, Answer4);

		
		VBox CenterGameLay = new VBox();
		CenterGameLay.setAlignment(Pos.CENTER);
		CenterGameLay.getChildren().addAll(questionLabel, answer1, answer2, answer3, answer4 , nextButton);
		
		BorderPane GameLay = new BorderPane();
		GameLay.setStyle("-fx-background-color: #FFD966;");
		GameLay.setPadding(offset);
		GameLay.setBottom(currentScoreLabel);
		GameLay.setCenter(CenterGameLay);
		
		game = new Scene(GameLay);
		
			//postGame Layout
		VBox PostGameLay = new VBox();
		PostGameLay.setAlignment(Pos.CENTER);
		PostGameLay.getChildren().addAll(gameOverLabel, scoreLabel, scoreDisplayLabel, returnToMainButton, viewStatsButton );
		
		postGame = new Scene(PostGameLay);

		stage.show();
		stage.setScene(mainQuiz);
		
	}
	
	public static void SuccessPopUp(Popup successfulQAddp, Stage stage) {	
		Timeline popupTimeline = new Timeline( //To store keyframes
	            new KeyFrame(Duration.seconds(0), e -> { 
	            	successfulQAddp.show(stage);
	            }),
	            new KeyFrame(Duration.seconds(3), e -> {
	            	successfulQAddp.hide();
	            }));
		popupTimeline.setCycleCount(1); //How many times it plays the sequence
		popupTimeline.playFromStart();
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


    public static void CorrectPopUp(Popup correctP, Stage stage) {
		Timeline popupTimeline = new Timeline( //To store keyframes
	            new KeyFrame(Duration.seconds(0), e -> { 
	            	correctP.show(stage);
	            }),
	            new KeyFrame(Duration.seconds(3), e -> {
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
	            new KeyFrame(Duration.seconds(3), e -> {
	            	incorrectP.hide();
	            }));
		popupTimeline.setCycleCount(1); //How many times it plays the sequence
		popupTimeline.playFromStart();
	}
}
