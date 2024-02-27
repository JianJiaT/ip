package logic;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Storage {
    public static final int TASK_TYPE_INDEX = 0;
    public static final int TASK_DONE_STATUS_INDEX = 3;
    public static final int TASK_NAME_INDEX = 6;
    public static final int COMMA_AND_SPACE_LENGTH = 2;
    private File dataFile;

    private TaskManager taskManager;

    public Storage(String filePath) throws IOException {
        if (new File("./data").mkdir()) {
            System.out.println("data folder created");
        } else {
            System.out.println("data folder found");
        }
        try {
            if (new File(filePath).createNewFile()) {
                System.out.println("dor.txt created");
            } else {
                System.out.println("dor.txt found");
            }
        } catch (IOException e) {
            throw new IOException();
        }
        this.dataFile = new File(filePath);
        this.taskManager = new TaskManager();
    }

    public TaskManager loadDataFromTextFile() throws FileNotFoundException {
        Scanner s = null;
        try {
            s = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Could not load dor.txt!");
            throw new FileNotFoundException();
        }
        while (s.hasNext()) {
            String data = s.nextLine();
            String taskType = String.valueOf(data.charAt(TASK_TYPE_INDEX));
            boolean taskIsDone = processTaskDoneStatus(data);
            switch (taskType) {
            case "T":
                loadToDo(data, taskIsDone);
                break;
            case "D":
                loadDeadline(data, taskIsDone);
                break;
            case "E":
                loadEvent(data, taskIsDone);
                break;
            }
        }
        System.out.println("Successfully loaded data!");
        System.out.println("Current number of tasks: " + taskManager.numOfTasks);
        return taskManager;
    }

    private boolean processTaskDoneStatus(String data) {
        boolean taskDoneStatus;
        if (String.valueOf(data.charAt(TASK_DONE_STATUS_INDEX)).equals("1")){
            taskDoneStatus = true;
        } else {
            taskDoneStatus = false;
        }
        return taskDoneStatus;
    }

    private void loadToDo(String data, boolean taskIsDone) {
        String taskName = data.substring(TASK_NAME_INDEX);
        taskManager.tasks.add(new ToDo(taskName, taskIsDone));
        taskManager.numOfTasks++;
    }

    private void loadDeadline(String data, boolean taskIsDone) {
        int commaAfterTaskNameIndex = data.indexOf(',', TASK_NAME_INDEX);
        String taskName = data.substring(TASK_NAME_INDEX, commaAfterTaskNameIndex);
        String byWhen = data.substring(commaAfterTaskNameIndex + COMMA_AND_SPACE_LENGTH);
        taskManager.tasks.add(new Deadline(taskName, taskIsDone, byWhen));
        taskManager.numOfTasks++;
    }

    private void loadEvent(String data, boolean taskIsDone) {
        int commaAfterTaskNameIndex = data.indexOf(',', TASK_NAME_INDEX);
        String taskName = data.substring(TASK_NAME_INDEX, commaAfterTaskNameIndex);
        int commaAfterFromDateTimeIndex = data.indexOf(",", commaAfterTaskNameIndex + COMMA_AND_SPACE_LENGTH);
        String fromWhen = data.substring(commaAfterTaskNameIndex + COMMA_AND_SPACE_LENGTH, commaAfterFromDateTimeIndex);
        String toWhen = data.substring(commaAfterFromDateTimeIndex + COMMA_AND_SPACE_LENGTH);
        taskManager.tasks.add(new Event(taskName, taskIsDone, fromWhen, toWhen));
        taskManager.numOfTasks++;
    }

    public void saveDataToTextFile() throws IOException {
        FileWriter fw = new FileWriter(dataFile);
        for (int i = 0; i < taskManager.numOfTasks; i++) {
            Task currTask = taskManager.tasks.get(i);
            String data = processData(currTask);
            fw.write(data + System.lineSeparator());
        }
        fw.close();
    }

    private String processData(Task currTask) {
        String data;
        String doneStatusOneAndZero;
        if (currTask.getDoneStatus().equals("X")) {
            doneStatusOneAndZero = "1";
        } else {
            doneStatusOneAndZero = "0";
        }
        data = currTask.getType() + ", " + doneStatusOneAndZero + ", " + currTask.getName();
        if (currTask.getType().equals("D")) {
            data = data + ", " + ((Deadline) currTask).getBy();
        } else if (currTask.getType().equals("E")) {
            data = data + ", " + ((Event) currTask).getFrom() + ", " + ((Event) currTask).getTo();
        }
        return data;
    }
}
