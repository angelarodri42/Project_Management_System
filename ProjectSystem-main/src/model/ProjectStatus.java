package model;

import java.io.Serializable;

public class ProjectStatus implements Serializable {
    private boolean isOngoing;

    public ProjectStatus(boolean isOngoing) {
        this.isOngoing = isOngoing;
    }

    public boolean isOngoing() {
        return isOngoing;
    }

    public void setOngoing(boolean ongoing) {
        isOngoing = ongoing;
    }

    @Override
    public String toString() {
        return isOngoing ? "Ongoing" : "Completed";
    }
}
