package tasks;

public class Deadline extends Task {
    private String by;

    public Deadline(String by) {
        super();
        this.by = by;
    }

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, boolean isDone, String by) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + this.by + ")");
    }
}
