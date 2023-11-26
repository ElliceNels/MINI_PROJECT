package mainPackage;

import javafx.application.Application; //Setting up!! Make sure you dont have src folder and that when making package, you click the second option
import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
	import javafx.scene.control.Label;
	import javafx.geometry.Insets;
	import javafx.geometry.Pos; 
	import javafx.scene.control.TextField;
	import javafx.scene.control.PasswordField;


	public class gui extends Application {
		Scene home, login, signUp; // All scenes
		
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			        launch(args);  //method in application class that sets up javafx app (setup)
			    }

			    @Override
			    public void start(Stage window) throws Exception {
			        window.setTitle("The Quiz Logon"); //Window title
			        //String user = "";
			        //String pass = "";
			    //Labels and buttons creation  
	//------------------------------------------------------------------------------------//	        
			        Label Intro = new Label("Welcome to the quiz! Please Login or Sign up");
			        
			        TextField usernameField = new TextField();
			        usernameField.setPromptText("Username");
			        
			        TextField newUsernameField = new TextField();
			        newUsernameField.setPromptText("New username");
			        
			        PasswordField passwordField = new PasswordField();
			        passwordField.setPromptText("Password");
			        
			        PasswordField newPasswordField = new PasswordField();
			        newPasswordField.setPromptText("New password");
			        
			        PasswordField confirmPasswordField = new PasswordField();
			        confirmPasswordField.setPromptText("Confirm password");
			        
			        Button confirmButton = new Button("Confirm");
			        confirmButton.setOnAction(e -> {
			        	String user = usernameField.getText();
			        	String pass = passwordField.getText();
			        		
						while (DB_UserInteract.loginCheck(user, pass) == false) { //loop to allow user to attempt login again after inserting incorrect details
							if (DB_UserInteract.loginCheck(user, pass)) { //Checks whether user name and password exists
								new TextField("Successful login");
							}else {
								new TextField("Username or password does not match. Try again"); //loop continues
							}
						}
			        });
			        
			        Button createAccountButton = new Button("Create Account");
			        //createAccountButton.setOnAction(e -> pass = passwordField);
			       
			        Button loginButton = new Button("Login");
			        loginButton.setOnAction(e -> window.setScene(login));
			        
			        Button signUpButton = new Button("Sign Up");
			        signUpButton.setOnAction(e -> window.setScene(signUp));
			        
			        Button ReturnloginButton = new Button("Back to Login Page");
			        ReturnloginButton.setOnAction(e -> window.setScene(login));
			        
			        Button ReturnsignUpButton = new Button("Back to Sign Up");
			        ReturnsignUpButton.setOnAction(e -> window.setScene(signUp));
			        //You cannot use the same button on different scenes//
	//------------------------------------------------------------------------------------//	        
			        Insets Offset = new Insets(10,10,10,10);
			        
			     //home layout
			        GridPane homeLay = new GridPane();
			        //General layout settings
			        homeLay.setPadding(Offset);  
			        homeLay.setVgap(10);
			        homeLay.setHgap(5);
			        homeLay.setAlignment(Pos.CENTER);
			        //Children addition and positioning
			        homeLay.setConstraints(Intro, 1, 0);
			        homeLay.setConstraints(loginButton, 0, 2);
			        homeLay.setConstraints(signUpButton, 2, 2);
			        homeLay.getChildren().addAll(Intro, loginButton, signUpButton);
			      
			        home = new Scene(homeLay, 440, 250);//Homepage setup
			     
			     //Login layout
			        GridPane LoginLay = new GridPane();
			        //General layout settings
			        LoginLay.setPadding(Offset);  
			        LoginLay.setVgap(10);
			        LoginLay.setHgap(5);
			        LoginLay.setAlignment(Pos.CENTER);
			        //Children addition and positioning
			        LoginLay.setConstraints(ReturnsignUpButton, 2 , 4);
			        LoginLay.setConstraints(usernameField, 2, 1);
			        LoginLay.setConstraints(passwordField, 2, 2);
			        LoginLay.setConstraints(confirmButton, 2, 3);
			        LoginLay.getChildren().addAll(ReturnsignUpButton, usernameField, passwordField, confirmButton);
			        
			        login = new Scene(LoginLay, 450, 250);
			   
			      //SignUp layout
			        GridPane SignUpLay = new GridPane();
			        //General layout settings
			        SignUpLay.setPadding(Offset);  
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
			        
			        signUp = new Scene(SignUpLay, 450, 250);
	//------------------------------------------------------------------------------------//	        
  
			        
			    //Show the GUI
			        window.setScene(home);
			        window.show();
			       
			    }
	
	}
	/* 
	- Stage = entire window
	- Scene = everything inside window
	- Button = an object
	- buttonName.setText("text on button"
	.setOnAction(this) = envokes handle method
	- StackPane = object, simple layout
	- Scene = object, parameters, first one is layout name and next two is size
	.setScene(sceneName) = primary stage command
	.show() = primary stage command, displays scene
	implements EventHandler<ActionEvent> = tells it what to do when an event happens
	event.getSource() = checks what the source of the event was 
	OR 
	.setOnAction(e -> THING TO DO) = e replaces the handle method, lambda
Label = text
	*/
