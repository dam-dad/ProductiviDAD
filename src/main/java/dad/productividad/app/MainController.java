package dad.productividad.app;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import dad.productividad.balanceManager.BalanceManagerController;
import dad.productividad.dataManager.TablePages;
import dad.productividad.home.HomeController;
import dad.productividad.menuBar.MenuBarController;
import dad.productividad.note.NotesController;
import dad.productividad.page.Page;
import dad.productividad.pomodoro.PomodoroController;
import dad.productividad.pomodoro.PomodoroPopUpController;
import dad.productividad.project.Project;
import dad.productividad.project.ProjectDetailController;
import dad.productividad.project.ProjectManagerController;
import dad.productividad.settings.SettingsController;
import dad.productividad.task.Task;
import dad.productividad.task.TaskDetailController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Main controller class of the program
 */
public class MainController implements Initializable { 
    /**
     * Main view
     */
    @FXML
    private BorderPane view;
    /**
     * Center pane of main view
     */
    @FXML
    private AnchorPane centerPane;
    /**
     * Top bar of main view 
     */
    @FXML 
    private GridPane topBar;
    /** 
     * Center of main view
     */
    @FXML
    private VBox center;
    /**
     * ListView
     */
    @FXML
    private ListView<String> listView;
    /**
     * Close, maximize and minimize buttons
     */
    @FXML
    private Button closeButton, maximizeButton, minimizeButton;
    /**
     * Todays page
     */
    static Page todaysPage = new Page();
    /**
     * Home shortcut
     */
    private final KeyCombination homeShortcut = new KeyCodeCombination(KeyCode.DIGIT1, KeyCombination.ALT_DOWN);
    /**
     * Project Manager shortcut
     */
    private final KeyCombination projectManagerShortcut = new KeyCodeCombination(KeyCode.DIGIT2, KeyCombination.ALT_DOWN);
    /**
     * Notes shortcut
     */
    private final KeyCombination notesShortcut = new KeyCodeCombination(KeyCode.DIGIT3, KeyCombination.ALT_DOWN);
    /**
     * Balance shortcut
     */
    private final KeyCombination balanceShortcut = new KeyCodeCombination(KeyCode.DIGIT4, KeyCombination.ALT_DOWN);
    /**
     * Pomodoro shortcut
     */
    private final KeyCombination pomodoroShortcut = new KeyCodeCombination(KeyCode.DIGIT5, KeyCombination.ALT_DOWN);
    /**
     * Settings shortcut
     */
    private final KeyCombination settingsShortcut = new KeyCodeCombination(KeyCode.DIGIT6, KeyCombination.ALT_DOWN);
    /**
     * Project Manager controller
     */
    private ProjectManagerController projectManagerController;
    /**
     * Notes controller
     */
    private NotesController notesController;
    /**
     * Balance Manager controller
     */
    private BalanceManagerController balanceManagerController;
    /**
     * Project Detail Controller
     */
    private ProjectDetailController projectDetailController;
    /**
     * Home Controller
     */
    private HomeController homeController;
    /**
     * Pomodoro Controller
     */
    private PomodoroController pomodoroController;
    /**
     * Settings Controller
     */
    private SettingsController settingsController;
    /**
     * Menu Bar controller
     */
    private MenuBarController menuBarController;
    /**
     * Main controller
     */
    public static MainController mainController;

    
    /**
     * Class constructor.
     */
    public MainController() {
        MainController.mainController = this;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            loader.setResources(ResourceBundle.getBundle("i18n/strings", Locale.getDefault()));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * View initialize method.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        homeController = new HomeController();
        view.setCenter(homeController.getView());
        projectManagerController = new ProjectManagerController();
        notesController = new NotesController();
        balanceManagerController = new BalanceManagerController();
        pomodoroController = new PomodoroController();
        settingsController = new SettingsController(); 
        menuBarController = new MenuBarController();
//        pomodoPopUpController = new PomodoroPopUpController();
        view.setLeft(menuBarController.getView());
//        view.setBottom(pomodoPopUpController.getView());
    	
        view.getStylesheets().add(getClass().getResource(App.preferences.getTheme()).toExternalForm());

        view.centerProperty().addListener((o, ov, nv) -> {
            if (nv != null) { 
                if (view.getRight() != null)
                    view.setRight(null);
            }
        });

        view.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ALT)
                    MainController.mainController.getMenuBarController().showTagShortcut();
                if (homeShortcut.match(e))
                    menuBarController.onHomeManagerSection();
                if (projectManagerShortcut.match(e)) 
                    menuBarController.onProjectManagerSection();
                if (balanceShortcut.match(e))
                    menuBarController.onBalanceManagerSection();
                if (notesShortcut.match(e))
                    menuBarController.onNotesManagerSection();
                if (pomodoroShortcut.match(e))
                    menuBarController.onPomodoroManagerSection();
                if (settingsShortcut.match(e))
                    menuBarController.onSettingsManagerSection();
            }
        });

        view.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ALT)
                    MainController.mainController.getMenuBarController().hideTagShortcut();

            }
        });


        projectManagerController = new ProjectManagerController();
        notesController = new NotesController();
        balanceManagerController = new BalanceManagerController();
        homeController = new HomeController();
        pomodoroController = new PomodoroController();
        settingsController = new SettingsController(); 
        menuBarController = new MenuBarController();
        view.setLeft(menuBarController.getView());
        view.setCenter(homeController.getView());

        

        todaysPage.setDate(LocalDate.now());
        TablePages.insertPage(todaysPage);
    }

    /**
     *
     * @return todaysPage Page of today
     */
    public static Page getTodaysPage() { 
        return todaysPage;
    }

    /**
     * Set the center of view with projectDetailController.getView().
     * The received project and stylesheet are assigned to the projectDetailController
     *
     * @param project Project
     * @param styleSheetPath String
     */
    public void openProject(Project project, String styleSheetPath) {
        projectDetailController = new ProjectDetailController();
        projectDetailController.setProject(project);

        new FadeIn(projectDetailController.getView()).play();

        view.setCenter(projectDetailController.getView());
    }

    /**
     * Set the right zone of view with taskDetailController.getView(),
     * with a certain Task.
     *
     * @param task The task to be set
     */
    public void setTaskOnRightSide(Task task) {

        view.setRight(null);
        TaskDetailController taskDetailController = new TaskDetailController();
        taskDetailController.setTask(task);
        view.setRight(taskDetailController.getView());
    }

    /**
     * Updates the right zone of view,
     * with a concrete Task.
     *
     * @param task Task
     */
    public void updateRightSide(Task task) {

        if (view.getRight() != null) {
            TaskDetailController taskDetailController = new TaskDetailController();
            taskDetailController.setTask(task);

            view.setRight(null);
            view.setRight(taskDetailController.getView());
        }
    }

    /**
     * Clear the right side of view.
     */
    public void setRightSideNull() {
        view.setRight(null);
    }

    /**
     * Updates the tasks, when adding one.
     */
    public void updateTaskWrapper() {
        homeController.insertTaskFromDB();
    }

    /**
     * @return The view
     */
    public BorderPane getView() {
        return this.view;
    }

    /**
     * @return The top bar
     */
    public GridPane getTopBar() {
        return this.topBar;
    }


    /**
     * Init actions of fxBorderlessScene
     */
    public void initActions() {
        App.borderLessScene.setMoveControl(getTopBar());
        App.borderLessScene.removeDefaultCSS();
        closeButton.setOnAction(a -> App.primaryStage.close());
        minimizeButton.setOnAction(a -> App.primaryStage.setIconified(true));
        maximizeButton.setOnAction(a -> App.borderLessScene.maximizeStage());
    }

    /**
     * Change the View theme.
     */
    public void changeTheme() {
        view.getStylesheets().clear();
        view.getStylesheets().add(getClass().getResource(App.preferences.getTheme()).toExternalForm());

    }

    /**
     * @return project controller
     */
    public ProjectManagerController getProjectManagerController() {
        return projectManagerController;
    }

    /**
     * Set a new project controller.
     *
     * @param projectManagerController
     */
    public void setProjectManagerController(ProjectManagerController projectManagerController) {
        this.projectManagerController = projectManagerController;
    }

    /**
     * @return notes controller
     */
    public NotesController getNotesController() {
        return notesController;
    }

    /**
     * Set a new notes controller
     *
     * @param notesController
     */
    public void setNotesController(NotesController notesController) {
        this.notesController = notesController;
    }

    /**
     * @return balance manager controller
     */
    public BalanceManagerController getBalanceManagerController() {
        return balanceManagerController;
    }

    /**
     * Set a new balance manager controller
     *
     * @param balanceManagerController
     */
    public void setBalanceManagerController(BalanceManagerController balanceManagerController) {
        this.balanceManagerController = balanceManagerController;
    }

    /**
     * @return project detail controller
     */
    public ProjectDetailController getProjectDetailController() {
        return projectDetailController;
    }

    /**
     * Set a new project detail controller
     *
     * @param projectDetailController
     */
    public void setProjectDetailController(ProjectDetailController projectDetailController) {
        this.projectDetailController = projectDetailController;
    }

    /**
     * @return Home controller
     */
    public HomeController getHomeController() {
        return homeController;
    }

    /**
     * Set a new home controller
     *
     * @param homeController
     */
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    /**
     * @return Pomodoro controller
     */
    public PomodoroController getPomodoroController() {
        return pomodoroController;
    }

    /**
     * Set a new pomodoro controller
     *
     * @param pomodoroController
     */
    public void setPomodoroController(PomodoroController pomodoroController) {
        this.pomodoroController = pomodoroController;
    }

    /**
     * @return Settings controller
     */
    public SettingsController getSettingsController() {
        return settingsController;
    }

    /**
     * Set a new settings controller
     *
     * @param settingsController
     */
    public void setSettingsController(SettingsController settingsController) {
        this.settingsController = settingsController;
    }

    /**
     * @return Menu Bar controller
     */
    public MenuBarController getMenuBarController() {
        return menuBarController;
    }

    /**
     * Set a new Menu Bar controller
     *
     * @param menuBarController
     */
    public void setMenuBarController(MenuBarController menuBarController) {
        this.menuBarController = menuBarController;
    }

    /**
     * @return close button
     */
    public Button getCloseButton() {
        return closeButton;
    }

    /**
     * Set a new close button
     *
     * @param closeButton
     */
    public void setCloseButton(Button closeButton) {
        this.closeButton = closeButton;
    }

    /**
     * @return maximize button
     */
    public Button getMaximizeButton() {
        return maximizeButton;
    }

    /**
     * Set a new maximize button
     *
     * @param maximizeButton
     */
    public void setMaximizeButton(Button maximizeButton) {
        this.maximizeButton = maximizeButton;
    }

    /**
     * @return minimize button
     */
    public Button getMinimizeButton() {
        return minimizeButton;
    }

    /**
     * Set a new minimize button
     *
     * @param minimizeButton
     */
    public void setMinimizeButton(Button minimizeButton) {
        this.minimizeButton = minimizeButton;
    }

}
