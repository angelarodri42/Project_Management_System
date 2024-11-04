package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.*;

import javafx.scene.control.TableView;

import java.util.ArrayList;

public class AddProjectController {
    private ProjectList projectList;
    private ArrayList<Project> filteredProjects;

    private String context = "CREATE";

    @FXML
    ChoiceBox<String> projectTypeChoiceBox;

    @FXML
    TabPane mainTabPane;

    @FXML
    Tab addProjectTab;

    @FXML
    Tab allProjectsTab;

    @FXML
    Tab commercialTab;

    @FXML
    Tab industrialTab;

    @FXML
    Tab residentialTab;

    @FXML
    Tab roadConstructionTab;

    @FXML
    TabPane projectSpecificDataTabPane;

    @FXML
    Button submitButton;

    @FXML
    Button cancelButton;

    @FXML
    TextField projectTitleField;

    @FXML
    TextField budgetField;

    @FXML
    TextField timelineField;

    @FXML
    TextField manHoursUsedField;

    @FXML
    TextField materialExpensesField;

    @FXML
    CheckBox isOngoingCheckBox;


    /*
    Commercial project specific fields
     */
    @FXML
    TextField commercialSizeOfBuildingField;

    @FXML
    TextField commercialNumberOfFloorsField;

    @FXML
    TextField commercialIntendedUseOfTheBuildingField;

    /*
    Residential project specific fields
     */

    @FXML
    TextField residentialSizeOfBuildingField;

    @FXML
    TextField residentialNumberOfKitchensField;

    @FXML
    TextField residentialNumberOfBathroomsField;

    @FXML
    TextField residentialNumberOfRoomsWithPlumbingField;

    @FXML
    ChoiceBox<String> residentialIsProjectNewBuildChoiceBox;

    /*
    Industrial project specific fields
     */

    @FXML
    TextField industrialSizeOfBuildingField;

    @FXML
    TextField industrialIntendedUseOfTheBuildingField;


    /*
    Road construction project specific fields
     */

    @FXML
    TextField roadConstructionRoadLengthField;

    @FXML
    TextField roadConstructionRoadWidthField;

    @FXML
    TextField roadConstructionNumberOfBridges;

    @FXML
    CheckBox roadConstructionGeoChallenges1;

    @FXML
    CheckBox roadConstructionGeoChallenges2;

    @FXML
    CheckBox roadConstructionGeoChallenges3;

    /*
    Project search tab
     */

    @FXML
    TableView<Project> projectsTableView;

    @FXML
    Button searchButton;

    @FXML
    TextField searchTextField;

    public void initialize() {
        try {
            projectList = BuildingModelManager.readProjectsFromFile();
            filteredProjects = projectList.getProjects();
        } catch (Exception e) {
            showErrorMessageBox("Error reading projects from file");
        }

        disableAndHideAllTabs();

        projectTypeChoiceBox.getItems().addAll(
                "Commercial Project",
                "Industrial Project",
                "Residential Project",
                "Road Construction Project"
        );

        residentialIsProjectNewBuildChoiceBox.getItems().addAll(
                "New Build",
                "Renovation"
        );

        isOngoingCheckBox.setVisible(false);
        cancelButton.setVisible(false);

        projectTypeChoiceBox.setOnAction(onProjectTypeChoiceBoxChange());
        submitButton.setOnAction(onSubmitButtonClicked());
        searchButton.setOnAction(onSearchButtonClicked());
        cancelButton.setOnAction(onCancelButtonClicked());
        initializeProjectSearchTab();
    }


    private void initializeProjectSearchTab() {
        projectsTableView.getColumns().addAll(
                createColumn("Project Title", "customerName"),
                createColumn("Project Type", "projectType"),
                createColumn("Budget", "budget"),
                createColumn("Timeline", "timeline"),
                createColumn("Ongoing", "ongoing")
        );

        resetProjectSearchTab();

        projectsTableView.setOnMousePressed(onProjectsTableRowDoubleClick());
    }

    private EventHandler<MouseEvent> onProjectsTableRowDoubleClick() {
        return event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Project project = projectsTableView.getSelectionModel().getSelectedItem();
                if (project == null) {
                    return;
                }

                context = "EDIT";

                projectTitleField.setText(project.getCustomer().getName());
                budgetField.setText(String.valueOf(project.getBudget()));
                timelineField.setText(String.valueOf(project.getTimeline()));
                manHoursUsedField.setText(String.valueOf(project.getManHoursUsed()));
                materialExpensesField.setText(String.valueOf(project.getMaterialExpenses()));
                isOngoingCheckBox.setSelected(project.isOngoing());

                if (project instanceof CommercialProject) {
                    projectTypeChoiceBox.getSelectionModel().select("Commercial Project");
                    commercialSizeOfBuildingField.setText(String.valueOf((project).getSize()));
                    commercialNumberOfFloorsField.setText(String.valueOf(((CommercialProject) project).getNumberOfFloors()));
                    commercialIntendedUseOfTheBuildingField.setText(((CommercialProject) project).getIntendedUseOfTheBuilding());

                } else if (project instanceof IndustrialProject) {
                    projectTypeChoiceBox.getSelectionModel().select("Industrial Project");
                    industrialSizeOfBuildingField.setText(String.valueOf((project).getSize()));
                    industrialIntendedUseOfTheBuildingField.setText(((IndustrialProject)project).getType());

                } else if (project instanceof ResidentialProject) {
                    projectTypeChoiceBox.getSelectionModel().select("Residential Project");
                    residentialSizeOfBuildingField.setText(String.valueOf((project).getSize()));
                    residentialNumberOfKitchensField.setText(String.valueOf(((ResidentialProject) project).getNumberOfKitchens()));
                    residentialNumberOfBathroomsField.setText(String.valueOf(((ResidentialProject) project).getNumberOfBathrooms()));
                    residentialNumberOfRoomsWithPlumbingField.setText(String.valueOf(((ResidentialProject) project).getNumberOfRoomsWithPlumbing()));
                    residentialIsProjectNewBuildChoiceBox.getSelectionModel().select(((ResidentialProject) project).isProjectNewBuild() ? "New Build" : "Renovation");

                } else if (project instanceof RoadConstructionProject) {
                    projectTypeChoiceBox.getSelectionModel().select("Road Construction Project");
                    roadConstructionRoadLengthField.setText(String.valueOf(((RoadConstructionProject) project).getLengthOfTheRoad()));
                    roadConstructionRoadWidthField.setText(String.valueOf(((RoadConstructionProject) project).getWidthOfTheRoad()));
                    roadConstructionNumberOfBridges.setText(String.valueOf(((RoadConstructionProject) project).getNumberOfBridges()));

                    ArrayList<String> challenges = ((RoadConstructionProject) project).getChallenges();
                    roadConstructionGeoChallenges1.setSelected(challenges.contains(roadConstructionGeoChallenges1.getText()));
                    roadConstructionGeoChallenges2.setSelected(challenges.contains(roadConstructionGeoChallenges2.getText()));
                    roadConstructionGeoChallenges3.setSelected(challenges.contains(roadConstructionGeoChallenges3.getText()));
                }

                mainTabPane.getSelectionModel().select(addProjectTab);
                addProjectTab.setText("Edit Project");
                isOngoingCheckBox.setVisible(true);
                cancelButton.setVisible(true);
            }
        };
    }

    private void resetProjectSearchTab() {
        projectsTableView.getItems().clear();
        projectsTableView.getItems().addAll(filteredProjects);
    }

    private EventHandler<ActionEvent> onSearchButtonClicked() {
        return event -> {
            String query = searchTextField.getText();

            if (query.isEmpty()) {
                try {
                    filteredProjects = projectList.getProjects();
                    resetProjectSearchTab();
                } catch (Exception e) {
                    showErrorMessageBox("Error reading projects from file");
                }
                return;
            }
            filteredProjects = projectList.search(query);
            resetProjectSearchTab();
        };
    }

    private EventHandler<ActionEvent> onCancelButtonClicked() {
        return event -> {
            addProjectTab.setText("Add Project");
            context = "CREATE";
            isOngoingCheckBox.setVisible(false);
            cancelButton.setVisible(false);
            clearAllFields();
            mainTabPane.getSelectionModel().select(allProjectsTab);
        };
    }

    private <S, T> TableColumn<S, T> createColumn(String columnName, String propertyName) {
        TableColumn<S, T> column = new TableColumn<>(columnName);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }

    private EventHandler<ActionEvent> onProjectTypeChoiceBoxChange() {
        return event -> {
            String selectedProjectType = projectTypeChoiceBox.getSelectionModel().getSelectedItem();
            switch (selectedProjectType) {
                case "Commercial Project":
                    disableAndHideAllTabs();
                    commercialTab.setDisable(false);
                    projectSpecificDataTabPane.getTabs().add(commercialTab);
                    projectSpecificDataTabPane.getSelectionModel().select(commercialTab);
                    break;
                case "Industrial Project":
                    disableAndHideAllTabs();
                    industrialTab.setDisable(false);
                    projectSpecificDataTabPane.getTabs().add(industrialTab);
                    projectSpecificDataTabPane.getSelectionModel().select(industrialTab);
                    break;
                case "Residential Project":
                    disableAndHideAllTabs();
                    residentialTab.setDisable(false);
                    projectSpecificDataTabPane.getTabs().add(residentialTab);
                    projectSpecificDataTabPane.getSelectionModel().select(residentialTab);
                    break;
                case "Road Construction Project":
                    disableAndHideAllTabs();
                    roadConstructionTab.setDisable(false);
                    projectSpecificDataTabPane.getTabs().add(roadConstructionTab);
                    projectSpecificDataTabPane.getSelectionModel().select(roadConstructionTab);
                    break;
            }
        };
    }

    private EventHandler<ActionEvent> onSubmitButtonClicked() {
        return event -> {
            String currentProjectTypeStripped = getCurrentProjectTypeStripped();

            if (currentProjectTypeStripped.isEmpty()) {
                return;
            }

            boolean isProjectValid;

            if (context.equals("CREATE")) {
                Object project;
                try {
                    project = Class.forName("model." + currentProjectTypeStripped).newInstance();
                } catch (ClassNotFoundException e) {
                    showErrorMessageBox("Class not found");
                    return;
                } catch (IllegalAccessException e) {
                    showErrorMessageBox("Illegal access");
                    return;
                } catch (InstantiationException e) {
                    showErrorMessageBox("Instantiation exception");
                    return;
                }

                isProjectValid = validateAndSaveProject(project, true);
            } else {
                Project project = projectsTableView.getSelectionModel().getSelectedItem();
                if (project == null) {
                    showErrorMessageBox("Please select a project to edit");
                    return;
                }

                isProjectValid = validateAndSaveProject(project, false);

                if (isProjectValid) {
                    addProjectTab.setText("Add Project");
                    context = "CREATE";
                    isOngoingCheckBox.setVisible(false);
                    cancelButton.setVisible(false);
                }
            }

            if (isProjectValid) {
                mainTabPane.getSelectionModel().select(allProjectsTab);
                clearAllFields();
            }
        };
    }

    private void clearAllFields()
    {
        projectTitleField.clear();
        budgetField.clear();
        timelineField.clear();
        manHoursUsedField.clear();
        materialExpensesField.clear();
        commercialSizeOfBuildingField.clear();
        commercialNumberOfFloorsField.clear();
        commercialIntendedUseOfTheBuildingField.clear();
        residentialSizeOfBuildingField.clear();
        residentialNumberOfKitchensField.clear();
        residentialNumberOfBathroomsField.clear();
        residentialNumberOfRoomsWithPlumbingField.clear();
        residentialIsProjectNewBuildChoiceBox.getSelectionModel().clearSelection();
        industrialSizeOfBuildingField.clear();
        industrialIntendedUseOfTheBuildingField.clear();
        roadConstructionRoadLengthField.clear();
        roadConstructionRoadWidthField.clear();
        roadConstructionNumberOfBridges.clear();
        roadConstructionGeoChallenges1.setSelected(false);
        roadConstructionGeoChallenges2.setSelected(false);
        roadConstructionGeoChallenges3.setSelected(false);
    }

    private boolean validateAndSaveProject(Object project, boolean isNew) {
        return switch (getCurrentProjectTypeStripped()) {
            case "CommercialProject" -> fillCommercialProjectWithDataAndSave(project, isNew);
            case "IndustrialProject" -> fillIndustrialProjectWithDataAndSave(project, isNew);
            case "ResidentialProject" -> fillResidentialProjectWithDataAndSave(project, isNew);
            case "RoadConstructionProject" -> fillRoadConstructionProjectWithDataAndSave(project, isNew);
            default -> false;
        };
    }

    private boolean fillCommercialProjectWithDataAndSave(Object project, boolean isNew) {
        CommercialProject commercialProject = (CommercialProject) project;

        try {
            commercialProject.getCustomer().setName(projectTitleField.getText());
            commercialProject.setBudget(Integer.parseInt(budgetField.getText()));
            commercialProject.setTimeline(Integer.parseInt(timelineField.getText()));
            commercialProject.setSize(Integer.parseInt(commercialSizeOfBuildingField.getText()));
            commercialProject.setNumberOfFloors(Integer.parseInt(commercialNumberOfFloorsField.getText()));
            commercialProject.setIntendedUseOfTheBuilding(commercialIntendedUseOfTheBuildingField.getText());
            commercialProject.setManHoursUsed(Integer.parseInt(manHoursUsedField.getText()));
            commercialProject.setMaterialExpenses(Integer.parseInt(materialExpensesField.getText()));
        } catch (Exception e) {
            showErrorMessageBox(e.getMessage());
            return false;
        }

        if (isNew) {
            projectList.addProject(commercialProject);
        } else {
            commercialProject.setOngoing(isOngoingCheckBox.isSelected());
        }

        BuildingModelManager.writeProjectsToFile();
        showSuccessMessageBox(isNew ? "Project successfully added" : "Project successfully edited");
        resetProjectSearchTab();

        return true;
    }

    private boolean fillResidentialProjectWithDataAndSave(Object project, boolean isNew) {
        ResidentialProject residentialProject = (ResidentialProject) project;

        try {
            residentialProject.getCustomer().setName(projectTitleField.getText());
            residentialProject.setBudget(Integer.parseInt(budgetField.getText()));
            residentialProject.setTimeline(Integer.parseInt(timelineField.getText()));
            residentialProject.setSize(Integer.parseInt(residentialSizeOfBuildingField.getText()));
            residentialProject.setNumberOfKitchens(Integer.parseInt(residentialNumberOfKitchensField.getText()));
            residentialProject.setNumberOfBathrooms(Integer.parseInt(residentialNumberOfBathroomsField.getText()));
            residentialProject.setNumberOfRoomsWithPlumbing(Integer.parseInt(residentialNumberOfRoomsWithPlumbingField.getText()));
            residentialProject.setManHoursUsed(Integer.parseInt(manHoursUsedField.getText()));
            residentialProject.setMaterialExpenses(Integer.parseInt(materialExpensesField.getText()));

            boolean isProjectNewBuild = residentialIsProjectNewBuildChoiceBox.getSelectionModel().getSelectedItem().equals("New Build");
            residentialProject.setProjectNewBuild(isProjectNewBuild);
        }
        catch (Exception e) {
            showErrorMessageBox(e.getMessage());
            return false;
        }

        if (isNew) {
            projectList.addProject(residentialProject);
        } else {
            residentialProject.setOngoing(isOngoingCheckBox.isSelected());
        }

        BuildingModelManager.writeProjectsToFile();
        showSuccessMessageBox(isNew ? "Project successfully added" : "Project successfully edited");
        resetProjectSearchTab();

        return true;
    }

    private boolean fillIndustrialProjectWithDataAndSave(Object project, boolean isNew) {
        IndustrialProject industrialProject = (IndustrialProject) project;

        try {
            industrialProject.getCustomer().setName(projectTitleField.getText());
            industrialProject.setBudget(Integer.parseInt(budgetField.getText()));
            industrialProject.setTimeline(Integer.parseInt(timelineField.getText()));
            industrialProject.setSize(Integer.parseInt(industrialSizeOfBuildingField.getText()));
            industrialProject.setType(industrialIntendedUseOfTheBuildingField.getText());
            industrialProject.setManHoursUsed(Integer.parseInt(manHoursUsedField.getText()));
            industrialProject.setMaterialExpenses(Integer.parseInt(materialExpensesField.getText()));
        } catch (Exception e) {
            showErrorMessageBox(e.getMessage());
            return false;
        }

        if (isNew) {
            projectList.addProject(industrialProject);
        } else {
            industrialProject.setOngoing(isOngoingCheckBox.isSelected());
        }

        BuildingModelManager.writeProjectsToFile();
        showSuccessMessageBox(isNew ? "Project successfully added" : "Project successfully edited");
        resetProjectSearchTab();

        return true;
    }

    private boolean fillRoadConstructionProjectWithDataAndSave(Object project, boolean isNew) {
        RoadConstructionProject roadConstructionProject = (RoadConstructionProject) project;

        try {
            roadConstructionProject.getCustomer().setName(projectTitleField.getText());
            roadConstructionProject.setBudget(Integer.parseInt(budgetField.getText()));
            roadConstructionProject.setTimeline(Integer.parseInt(timelineField.getText()));
            roadConstructionProject.setLengthOfTheRoad(Integer.parseInt(roadConstructionRoadLengthField.getText()));
            roadConstructionProject.setWidthOfTheRoad(Integer.parseInt(roadConstructionRoadWidthField.getText()));
            roadConstructionProject.setNumberOfBridges(Integer.parseInt(roadConstructionNumberOfBridges.getText()));
            roadConstructionProject.setManHoursUsed(Integer.parseInt(manHoursUsedField.getText()));
            roadConstructionProject.setMaterialExpenses(Integer.parseInt(materialExpensesField.getText()));

            ArrayList<String> challenges = new ArrayList<>();
            if (roadConstructionGeoChallenges1.isSelected()) {
                challenges.add(roadConstructionGeoChallenges1.getText());
            }

            if (roadConstructionGeoChallenges2.isSelected()) {
                challenges.add(roadConstructionGeoChallenges2.getText());
            }

            if (roadConstructionGeoChallenges3.isSelected()) {
                challenges.add(roadConstructionGeoChallenges3.getText());
            }

            roadConstructionProject.setChallenges(challenges);

        } catch (Exception e) {
            showErrorMessageBox(e.getMessage());
            return false;
        }

        if (isNew) {
            projectList.addProject(roadConstructionProject);
        } else {
            roadConstructionProject.setOngoing(isOngoingCheckBox.isSelected());
        }

        BuildingModelManager.writeProjectsToFile();
        showSuccessMessageBox(isNew ? "Project successfully added" : "Project successfully edited");
        resetProjectSearchTab();

        return true;
    }

    private String getCurrentProjectTypeStripped() {
        String currentProjectType = getCurrentProjectType();

        if (currentProjectType == null) {
            showErrorMessageBox("Please select a project type");
            return "";
        }

        return currentProjectType.replaceAll("\\s+", "");
    }

    private String getCurrentProjectType() {
        return projectTypeChoiceBox.getSelectionModel().getSelectedItem();
    }

    private void showErrorMessageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessMessageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void disableAndHideAllTabs() {
        commercialTab.setDisable(true);
        industrialTab.setDisable(true);
        residentialTab.setDisable(true);
        roadConstructionTab.setDisable(true);
        projectSpecificDataTabPane.getTabs().remove(commercialTab);
        projectSpecificDataTabPane.getTabs().remove(industrialTab);
        projectSpecificDataTabPane.getTabs().remove(residentialTab);
        projectSpecificDataTabPane.getTabs().remove(roadConstructionTab);
    }
}
