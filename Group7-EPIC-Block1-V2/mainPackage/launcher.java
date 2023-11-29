package mainPackage;

import javafx.application.Application;
import javafx.stage.Stage;

	public class launcher extends Application {
		static String user_ID;

		public static void main(String[] args) {
			//DB and JavaFX Setup
			Questions.main(args);
			launch(args);
		}

		@Override
		public void start(Stage window) throws Exception {
			//Window creation
			window.setTitle("CUiz");
			window.setScene(homeScene.createScene(window));
			window.show();

		}
	}




	
	
	
//	Ellice 0-216