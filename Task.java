public class Task {
    private String name;
    private boolean isDone;

    public Task() {
        this.name = "placeholder";
        this.isDone = false;
    }

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public String getDoneStatus() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }
}
