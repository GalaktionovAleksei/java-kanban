package tasks;
import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    protected ArrayList<SubTask> listSubTasks = new ArrayList<>();
    public Epic(String name, String description, int id, String status, ArrayList<SubTask> subTasks) {
        super(name, description, id, status);
        this.status = "NEW";
    }

    private String setStatusEpic(){
        String newStatus = null;
        int newNum = 0;
        int progressNum = 0;
        int doneNum = 0;
        if (listSubTasks.isEmpty()){
            newStatus = "NEW";
        } else {
            for (SubTask subTask : listSubTasks) {
                if (Objects.equals(subTask.status, "NEW")) {
                    newNum++;
                } else if (Objects.equals(subTask.status, "IN_PROGRESS")) {
                    progressNum++;
                } else if (Objects.equals(subTask.status, "DONE")) {
                    doneNum++;
                }
                if (newNum == 0 && progressNum == 00) {
                    newStatus = "DONE";
                } else if (progressNum == 0 && doneNum == 0) {
                    newStatus = "NEW";
                } else {
                    newStatus = "IN_PROGRESS";
                }
            }
        } return newStatus;
    }

    public ArrayList <SubTask> getListSubTasks(){
        ArrayList <SubTask> newListSubTasks = listSubTasks;
        return newListSubTasks;
    }

    protected String setStatus(){
        return this.status = setStatusEpic();
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                " listSubTasks.size()=" + listSubTasks.size() +
                ", id=" + id +
                '}';
    }
}