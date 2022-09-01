package tasks;

public class Task{
    protected String name;
    protected String description;
    protected Status status;
    protected int id;

    public Task(String name, String description, int id, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public Status setStatus(Status status) {
        return this.status = status;
    }

    public int getID(){
        return id;
    }

    public void setID(int newID){
        id = newID;
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
