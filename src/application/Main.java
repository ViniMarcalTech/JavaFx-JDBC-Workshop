package application;

import java.io.IOException;
import java.util.List;

import db.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.dao.AniversariantesDao;
import model.dao.impl.AniversariantesDaoJDBC;
import model.entities.Aniversariantes;

public class Main extends Application {
	
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			mainScene = new Scene(scrollPane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sample JavaFX application");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getMainScene() {
		return mainScene;
		
	}

	public static void main(String[] args) {
	
		AniversariantesDao aniv = new AniversariantesDaoJDBC( DB.getConnection());
		
		List<Aniversariantes> list = aniv.findAll();
		
		for (int i = 0; i< list.size();i++) {
			System.out.println(list.get(i));
			
		
		
			
		}
		launch(args);
	}
}
