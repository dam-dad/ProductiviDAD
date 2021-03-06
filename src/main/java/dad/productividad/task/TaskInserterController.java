package dad.productividad.task;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableTasks;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

/**
 * TaskInserter view controller
 */
public class TaskInserterController extends HBox implements Initializable {
    /**
     * Title textfield
     */
    @FXML
    private JFXTextField titleTF;
    /**
     * Title
     */
    private StringProperty title = new SimpleStringProperty();

    /**
     * TaskInserterController constructor
     */
    public TaskInserterController() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskInserter.fxml"));
            loader.setResources(ResourceBundle.getBundle("i18n/strings"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * TaskInserter view initialization
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        titleTF.textProperty().bindBidirectional(title);
    }

    /**
     * Enter key action
     *
     * @param event
     */
    @FXML
    private void onEnter(ActionEvent event) {
        insertTask();
    }

    /**
     * Insert Button action
     *
     * @param event
     */
    @FXML
    private void onInsertTaskButton(ActionEvent event) {
        insertTask();
    }

    /**
     * Inserts a task into the database, set that task into the right
     * side of mainView and reset the focusProperty
     */
    private void insertTask() {

        if (title.get() != null) {
            if (!title.get().isEmpty()) {
                Task task = new Task();
                task.setTitle(title.get());
                task.setCreationDate(LocalDate.now());
                task.setStatus(StatusType.TODO);
                TableTasks.insert(task);
                MainController.mainController.updateTaskWrapper();
                MainController.mainController.setTaskOnRightSide(task);
                title.set("");
                requestFocus();
            }
        }
    }
}
