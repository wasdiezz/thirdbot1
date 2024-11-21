
package org.example.db;

public class Registration {
    private int id;
    private String teamName;
    private int numberOfParticipants;
    private int selectedTask;

    public Registration(int id, String teamName, int numberOfParticipants, int selectedTask) {
        this.id = id;
        this.teamName = teamName;
        this.numberOfParticipants = numberOfParticipants;
        this.selectedTask = selectedTask;

    }

    public int getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public int getSelectedTask() {
        return selectedTask;
    }
}
