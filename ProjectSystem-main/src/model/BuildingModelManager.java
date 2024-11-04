package model;

import utils.MyFileHandler;

import java.io.FileNotFoundException;

public class BuildingModelManager {
    private static ProjectList projectList;

    public static ProjectList getProjectList() {
        if (projectList == null) {
            projectList = new ProjectList();
        }

        return projectList;
    }

    public static void writeProjectsToFile() {
        try {
            MyFileHandler.writeToBinaryFile("projects.bin", projectList);
            MyFileHandler.writeToXMLFile("projects.xml", projectList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ProjectList readProjectsFromFile() throws Exception {
        try {
            projectList = (ProjectList) MyFileHandler.readFromBinaryFile("projects.bin");

            if (projectList == null) {
                projectList = new ProjectList();
                return projectList;
            }
            return projectList;
        } catch (FileNotFoundException e) {
            projectList = new ProjectList();
            return projectList;
        } catch (Exception e) {
            throw e;
        }
    }
}
