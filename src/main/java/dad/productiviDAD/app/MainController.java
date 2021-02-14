package dad.productiviDAD.app;

import animatefx.animation.FadeIn;
import animatefx.animation.Shake;
import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.dlsc.preferencesfx.model.Setting;
import dad.productiviDAD.balanceManager.BalanceManagerController;
import dad.productiviDAD.dataManager.TablePages;
import dad.productiviDAD.home.HomeController;
import dad.productiviDAD.note.NotesController;
import dad.productiviDAD.page.Page;
import dad.productiviDAD.project.Project;
import dad.productiviDAD.project.ProjectManagerController;
import dad.productiviDAD.project.projectDetailController;
import dad.productiviDAD.task.RightBarController;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController implements Initializable {
	static Page todaysPage = new Page();
	// View

	public static Page getTodaysPage() {
		return todaysPage;
	}

	@FXML
	private BorderPane view;

	@FXML
	private AnchorPane centerPane;

	@FXML
	private GridPane topBar;

	@FXML
	private VBox center;

	@FXML
	private ListView<String> listView;
	
	// Controllers
	private RightBarController rightBarController;

	private ProjectManagerController projectManagerController;
	private NotesController notasController;
	private BalanceManagerController balanceManagerController;
	private projectDetailController projectDetailController;	
	private HomeController homeController;	 
	
	public static MainController mainController;
 
	public MainController() {
		MainController.mainController = this;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
  
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		
		todaysPage.setDate(LocalDate.now());
 
		rightBarController = new RightBarController();

		projectManagerController = new ProjectManagerController();
		notasController = new NotesController();
		balanceManagerController = new BalanceManagerController();
		homeController=new HomeController();
		
		view.setCenter(homeController.getView()); 
		//TODO Modificar todo el tema de las task
//		view.setRight(rightBarController.getView());

		if (TablePages.todaysPage())
			TablePages.setID(todaysPage);
		else
			TablePages.insertPage(todaysPage);
		System.out.println(todaysPage.getId());
	} 

	public void openProject(Project project, String styleSheetPath) {
		projectDetailController=new projectDetailController();
		projectDetailController.setProject(project);
		
		new FadeIn(projectDetailController.getView()).play();
			
		view.setCenter(projectDetailController.getView());
		
	}

	public BorderPane getView() {
		return this.view;
	}

	public GridPane getTopBar() {
		return this.topBar;
	}

	// TopBar
	@FXML
	private void onCloseWindow(ActionEvent event) {
		Stage stage=(Stage)view.getScene().getWindow();
    	stage.close();
	}

	@FXML
	private void onMaximizeButton(ActionEvent event) {
		Stage stage = (Stage) view.getScene().getWindow();
		stage.setMaximized(!stage.isMaximized());
	}

	@FXML
	private void onMinimizeWindow(ActionEvent event) {
		Stage stage = (Stage) view.getScene().getWindow();
		stage.setIconified(true);
	}

	// LeftMenuBar

	@FXML
	private void onHomeButton(ActionEvent event) {

		if (view.getCenter() == homeController.getView()) 
			new Shake(view.getCenter()).play();
		else {
			new FadeIn(homeController.getView()).play();
			
			view.setCenter(homeController.getView());
		}
	}

	@FXML
	private void onCalendarButton(ActionEvent event) {

	}

	@FXML
	private void onEntryReaderButton(ActionEvent event) {

	}

	@FXML
	private void onProyectManagerButton(ActionEvent event) {

		if(view.getCenter()==projectManagerController.getView())
			new Shake(view.getCenter()).play();
		else {
			new FadeIn(projectManagerController.getView()).play();
			view.setCenter(projectManagerController.getView());
		}
		
	}

	@FXML
	private void onIdeasButton(ActionEvent event) {

		if (view.getCenter() == notasController.getView())
			new Shake(view.getCenter()).play();
		else {
			new FadeIn(notasController.getView()).play();
			view.setCenter(notasController.getView());
		}
	}

	@FXML
	private void onBalanceManagerButton(ActionEvent event) {

		if (view.getCenter() == balanceManagerController.getView())
			new Shake(view.getCenter()).play();
		else {
			new FadeIn(balanceManagerController.getView()).play();
			view.setCenter(balanceManagerController.getView());
		}
	}

	@FXML
	private void onTimePlannerButton(ActionEvent event) {

	}

	@FXML
	private void onToolsButton(ActionEvent event) {
		
		StringProperty stringProperty = new SimpleStringProperty("String");
		BooleanProperty booleanProperty = new SimpleBooleanProperty(true);
		IntegerProperty integerProperty = new SimpleIntegerProperty(12);
		DoubleProperty doubleProperty = new SimpleDoubleProperty(6.5);

		PreferencesFx preferencesFx = PreferencesFx.of(App.class, // Save class (will be used to reference saved values of
																	// Settings to)
				Category.of("Category title 1", Setting.of("Setting title 1", stringProperty), // creates a group
																								// automatically
						Setting.of("Setting title 2", booleanProperty) // which contains both settings
				), Category.of("Category title 2").expand() // Expand the parent category in the tree-view
						.subCategories( // adds a subcategory to "Category title 2"
								Category.of("Category title 3",
										Group.of("Group title 1", Setting.of("Setting title 3", integerProperty)), Group.of( // group
																																// without
																																// title
												Setting.of("Setting title 3", doubleProperty)))));
		preferencesFx.dialogIcon(App.primaryStage.getIcons().get(0));
		preferencesFx.show(true);
	}

	@FXML
	private void onGithubButton(ActionEvent event) {
		//this is using an awt component lol
		try {
		    Desktop.getDesktop().browse(new URL("https://github.com/dam-dad/ProductiviDAD").toURI());
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
	}
}
