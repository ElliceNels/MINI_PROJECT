package mainPackage;

import javafx.application.Application; //Setting up!! Make sure you dont have src folder and that when making package, you click the second option
import javafx.stage.Stage;

	//Scenes = start lowercase
	//Layouts = Start uppercase
	//Controls = start lowercase

	public class launcher extends Application {

		static String user_ID;
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			// init db
			Questions.main(args);
			launch(args);  //method in application class that sets up javafx app (setup)
		}

		@Override
		public void start(Stage window) throws Exception {
			window.setTitle("CUiz"); //Window title


			//MainQuiz layout
			window.setScene(homeScene.createScene(window));
			window.show();

		}
	}




	
	
	
//	Ellice 0-216