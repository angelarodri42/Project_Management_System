package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectList implements Serializable {
    ArrayList<Project> projects;

    public ProjectList(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ProjectList() {
        this.projects = new ArrayList<>();
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }
    public ArrayList<Project> getProjects() {
        return this.projects;
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
    }

    public int getSize() {
        return this.projects.size();
    }

    public Project getProject(int index) {
        return this.projects.get(index);
    }

    public ArrayList<Project> search(String query) {
        ArrayList<Project> results = new ArrayList<>();
        for (Project project : this.projects) {
            if (
                    project.getCustomer().getName().toLowerCase().contains(query.toLowerCase())
                    || project.getTypeName().toLowerCase().contains(query.toLowerCase())
                    || String.valueOf(project.getBudget()).toLowerCase().contains(query.toLowerCase())
                    || String.valueOf(project.getTimeline()).toLowerCase().contains(query.toLowerCase())
                    || String.valueOf(project.isOngoing()).toLowerCase().contains(query.toLowerCase())
            ) {
                results.add(project);
            }
        }
        return results;
    }
}
