package tasks;

public class Task{
    protected String name;
    protected String description;
    protected String status;
    protected int id;

    public Task(String name, String description, int id, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public String setStatus(String status) {
        return this.status = status;
    }

    public int getID(){
        return id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
