package dad.productiviDAD.app;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import animatefx.animation.Shake;
import dad.productiviDAD.balanceManager.BalanceManagerController;
import dad.productiviDAD.dataManager.TablePages;
import dad.productiviDAD.home.HomeController;
import dad.productiviDAD.note.NotesController;
import dad.productiviDAD.page.Page;
import dad.productiviDAD.project.Project;
import dad.productiviDAD.project.ProjectManagerController;
import dad.productiviDAD.project.projectDetailController;
import dad.productiviDAD.task.RightBarController;
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
		view.setRight(rightBarController.getView());

		if (TablePages.todaysPage())
			TablePages.setID(todaysPage);
		else
			TablePages.insertPage(todaysPage);
		System.out.println(todaysPage.getId());
	} 

	public void openProject(Project project) {
		projectDetailController=new projectDetailController();
		
		new FadeIn(projectDetailController.getView()).play();
			
		view.setCenter(projectDetailController.getView());
		resetRightBar();
		
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
			resetRightBar();
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
			resetRightBar();
		}
		
	}

	@FXML
	private void onIdeasButton(ActionEvent event) {

		if (view.getCenter() == notasController.getView())
			new Shake(view.getCenter()).play();
		else {
			new FadeIn(notasController.getView()).play();
			view.setCenter(notasController.getView());
			resetRightBar();
		}
	}

	@FXML
	private void onBalanceManagerButton(ActionEvent event) {

		if (view.getCenter() == balanceManagerController.getView())
			new Shake(view.getCenter()).play();
		else {
			new FadeIn(balanceManagerController.getView()).play();
			view.setCenter(balanceManagerController.getView());
			resetRightBar();
		}
	}

	@FXML
	private void onTimePlannerButton(ActionEvent event) {

	}

	@FXML
	private void onToolsButton(ActionEvent event) {

	}

	@FXML
	private void onGithubButton(ActionEvent event) {

	}

	/**
	 * Resets right side of the view so the JFXDrawer doesn't get unreachable under
	 * the new center node
	 */
	private void resetRightBar() {
		view.setRight(null);
		view.setRight(rightBarController.getView());
	}
}