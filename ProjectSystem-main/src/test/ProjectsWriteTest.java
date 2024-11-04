package test;

import model.*;

public class ProjectsWriteTest {
    public static void main(String[] args) {
        try {
            CommercialProject project = new CommercialProject();
            RoadConstructionProject project2 = new RoadConstructionProject();
            IndustrialProject project3 = new IndustrialProject();
            ResidentialProject project4 = new ResidentialProject();
            CommercialProject project5 = new CommercialProject();
            RoadConstructionProject project6 = new RoadConstructionProject();
            IndustrialProject project7 = new IndustrialProject();
            ResidentialProject project8 = new ResidentialProject();
            CommercialProject project9 = new CommercialProject();
            RoadConstructionProject project10 = new RoadConstructionProject();
            IndustrialProject project11 = new IndustrialProject();
            ResidentialProject project12 = new ResidentialProject();
            CommercialProject project13 = new CommercialProject();
            RoadConstructionProject project14 = new RoadConstructionProject();
            IndustrialProject project15 = new IndustrialProject();
            ResidentialProject project16 = new ResidentialProject();
            CommercialProject project17 = new CommercialProject();
            RoadConstructionProject project18 = new RoadConstructionProject();
            IndustrialProject project19 = new IndustrialProject();
            ResidentialProject project20 = new ResidentialProject();
            CommercialProject project21 = new CommercialProject();
            RoadConstructionProject project22 = new RoadConstructionProject();
            IndustrialProject project23 = new IndustrialProject();
            ResidentialProject project24 = new ResidentialProject();
            CommercialProject project25 = new CommercialProject();

            project2.setOngoing(true);
            project5.setOngoing(true);
            project6.setOngoing(true);
            project9.setOngoing(true);
            project12.setOngoing(true);

            ProjectList projectList = BuildingModelManager.getProjectList();
            projectList.addProject(project);
            projectList.addProject(project2);
            projectList.addProject(project3);
            projectList.addProject(project4);
            projectList.addProject(project5);
            projectList.addProject(project6);
            projectList.addProject(project7);
            projectList.addProject(project8);
            projectList.addProject(project9);
            projectList.addProject(project10);
            projectList.addProject(project11);
            projectList.addProject(project12);
            projectList.addProject(project13);
            projectList.addProject(project14);
            projectList.addProject(project15);
            projectList.addProject(project16);
            projectList.addProject(project17);
            projectList.addProject(project18);
            projectList.addProject(project19);
            projectList.addProject(project20);
            projectList.addProject(project21);
            projectList.addProject(project22);
            projectList.addProject(project23);
            projectList.addProject(project24);
            projectList.addProject(project25);


            for (int i = 0; i < projectList.getSize(); i++) {
                projectList.getProject(i).getCustomer().setName("Customer " + (i + 1));
                projectList.getProject(i).getCustomer().setPhoneNumber("Phone Number " + (i + 1));
                projectList.getProject(i).getCustomer().setEmail("Email " + (i + 1));
            }

            BuildingModelManager.writeProjectsToFile();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
