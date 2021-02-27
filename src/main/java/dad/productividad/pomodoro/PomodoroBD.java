package dad.productividad.pomodoro;

import javafx.beans.property.*;

public class PomodoroBD {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty typeTask = new SimpleStringProperty();
    private IntegerProperty timeSpent = new SimpleIntegerProperty();

    public PomodoroBD() {
    }

    public PomodoroBD(String typeTask, Integer timeSpent) {
        this.typeTask.set(typeTask);
        this.timeSpent.set(timeSpent);
    }

    /**
     * @return IntegerProperty of id
     */
    public final IntegerProperty idProperty() {
        return this.id;
    }

    /**
     * @return int of id
     */
    public final int getId() {
        return this.idProperty().get();
    }

    /**
     * @return StringProperty of typeTask
     */
    public final StringProperty typeTaskProperty() {
        return this.typeTask;
    }

    /**
     * @return String of typeTask
     */
    public final String getTypeTask() {
        return this.typeTaskProperty().get();
    }

    /**
     * Sets a new Type Task
     *
     * @param typeTask
     */
    public final void setTypeTask(final String typeTask) {
        this.typeTaskProperty().set(typeTask);
    }

    /**
     * @return IntegerProperty of timeSpent
     */
    public final IntegerProperty timeSpentProperty() {
        return this.timeSpent;
    }

    /**
     * @return int of timeSpent
     */
    public final int getTimeSpent() {
        return this.timeSpentProperty().get();
    }

    /**
     * Sets a new timeSpent
     *
     * @param timeSpent
     */
    public final void setTimeSpent(final int timeSpent) {
        this.timeSpentProperty().set(timeSpent);
    }
}
