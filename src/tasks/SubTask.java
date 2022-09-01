package tasks;

public class SubTask extends Task {
    private int epicID;

    public SubTask(String name, String description, int id, Status status, int epicID) {
        super(name, description, id, status);
        this.epicID = epicID;
    }

    public void setStatus(Status status, Epic epic) {
        this.status = status;
        epic.setStatus();
    }

    public void setSubTaskEpicID(int epicID) {
        this.epicID = epicID;
    }

    public int getSubTaskEpicID() {
        return epicID;
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
