package tasks;

public class SubTask extends Task {
    protected int epicID;

    public SubTask(String name, String description, int id, String status, int epicID) {
        super(name, description, id, status);
        this.epicID = epicID;
    }

    public void setStatus(String status, Epic epic) {
        this.status = status;
        epic.setStatus();
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "epicID=" + epicID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                '}';
    }
}