package tasks;
import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private ArrayList<SubTask> listSubTasks = new ArrayList<>();
    public Epic(String name, String description, int id, Status status, ArrayList<SubTask> subTasks) {
        super(name, description, id, status);
        this.status = Status.NEW;
    }

    private Status setStatusEpic(){
        Status newStatus = null;
        int newSum = 0;
        int doneSum = 0;
        if (listSubTasks.isEmpty()){
            newStatus = Status.NEW;
        } else {
            for (SubTask subTask : listSubTasks) {
                if (Objects.equals(subTask.status, "NEW")) {
                    newSum++;
                } else if (Objects.equals(subTask.status, "DONE")) {
                    doneSum++;
                }
                if (newSum == listSubTasks.size()) {
                    newStatus = Status.NEW;
                } else if (doneSum == listSubTasks.size()) {
                    newStatus = Status.DONE;
                } else {
                    newStatus = Status.IN_PROGRESS;
                }
            }
        } return newStatus;
    }

    public void addSubTaskToEpic(SubTask subTask){
        listSubTasks.add(subTask);
    }

    public void deleteSubTaskFromEpic(SubTask subTask){
        listSubTasks.remove(subTask);
    }

    public ArrayList<SubTask> getListSubTasks() {
        return listSubTasks;
    }
    public ArrayList<SubTask>  setListSubTasks(ArrayList<SubTask> ListSubTasks){
        this.listSubTasks = ListSubTasks;
        return listSubTasks;
    }

    public void deleteAllSubTaskFromEpic(){
        listSubTasks.clear();
    }

    public void setStatus(){
        this.status = setStatusEpic();
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