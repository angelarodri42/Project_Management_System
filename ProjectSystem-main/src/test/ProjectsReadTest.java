package test;

import model.BuildingModelManager;
import model.ProjectList;

public class ProjectsReadTest {
    public static void main(String[] args) {
        ProjectList projectList = null;

        try {
            projectList = BuildingModelManager.readProjectsFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < projectList.getSize(); i++) {
            System.out.println(projectList.getProject(i));
        }
    }
}
