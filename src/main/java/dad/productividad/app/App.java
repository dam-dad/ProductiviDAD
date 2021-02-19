package dad.productividad.app;

import dad.productividad.utils.Preferences;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Locale;

public class App extends Application{
	public static final String APP_NAME = "ProductiviDAD";
	public static Preferences preferences;
	private MainController controller;
	public static Stage primaryStage;

	@Override
	public void init() throws Exception {
		preferences = Preferences.load();
		Locale.setDefault(preferences.getLocale());
		super.init();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
			
		App.primaryStage = primaryStage;
		controller=new MainController();
		Scene scene=new Scene(controller.getView());

		controller.getView().setTop(controller.getTopBar());
		
		controller.getView().getScene().addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ALT) 
            	MainController.mainController.getMenuBarController().setHomeTag("Al1+1");
            	
            event.consume();
        });
		
		controller.getView().getScene().addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ALT) 
            	MainController.mainController.getMenuBarController().setHomeTag("");
            	
            event.consume(); 
        });
		
		primaryStage.setTitle("ProductiviDAD");
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED); 
		primaryStage.show();
		primaryStage.getIcons().add(new Image("/images/pdad_192px.png"));
	}
	@Override
	public void stop() throws Exception {
		super.stop();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
