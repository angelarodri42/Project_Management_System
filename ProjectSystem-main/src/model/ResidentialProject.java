package model;

import java.util.Objects;

public class ResidentialProject extends Project {
    /**
     * Number of kitchens in the Residential Project
     */
    private int numberOfKitchens;
    /**
     * Number of bathrooms in the Residential Project
     */
    private int numberOfBathrooms;
    /**
     * Number of other rooms with plumbing excluding kitchen(s) or bathroom(s)
     */
    private int numberOfRoomsWithPlumbing;

    /**
     * Defines if residential project is NewBuild (true) or Renovation (false)
     */
    private boolean isProjectNewBuild;

    /**
     *  Type of residential project used for XML serialization
     */
    public final String projectType = "Residential";

    public ResidentialProject() throws Exception {
        super(100000, 12, 3, false);
        this.numberOfKitchens = 1;
        this.numberOfBathrooms = 1;
        this.numberOfRoomsWithPlumbing = 1;
        this.isProjectNewBuild = true;
    }

    public ResidentialProject(int budget, int timeline, int size, boolean isOngoing, int numberOfKitchens, int numberOfBathrooms, int numberOfRoomsWithPlumbing, boolean isProjectNewBuild) throws Exception {
        super(budget, timeline, size, isOngoing);
        this.numberOfKitchens = numberOfKitchens;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfRoomsWithPlumbing = numberOfRoomsWithPlumbing;
        this.isProjectNewBuild = isProjectNewBuild;
    }

    public ResidentialProject(int budget, int size, boolean isOngoing) throws Exception {
        super(budget, 9, size, isOngoing);
        this.numberOfKitchens = 1;
        this.numberOfBathrooms = 1;
        this.numberOfRoomsWithPlumbing = 1;
        this.isProjectNewBuild = true;
    }

    public ResidentialProject(int budget, int timeline, int size, int numberOfKitchens, int numberOfBathrooms, int numberOfRoomsWithPlumbing, boolean isProjectNewBuild) throws Exception {
        super(budget, timeline, size);
        this.numberOfKitchens = numberOfKitchens;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfRoomsWithPlumbing = numberOfRoomsWithPlumbing;
        this.isProjectNewBuild = isProjectNewBuild;
    }

    public ResidentialProject(int budget, int size) throws Exception {
        super(budget, 9, size);
        this.numberOfKitchens = 1;
        this.numberOfBathrooms = 1;
        this.numberOfRoomsWithPlumbing = 1;
        this.isProjectNewBuild = true;
    }

    public int getNumberOfKitchens() {
        return numberOfKitchens;
    }

    public void setNumberOfKitchens(int numberOfKitchens) {
        this.numberOfKitchens = numberOfKitchens;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public int getNumberOfRoomsWithPlumbing() {
        return numberOfRoomsWithPlumbing;
    }

    public void setNumberOfRoomsWithPlumbing(int numberOfRoomsWithPlumbing) {
        this.numberOfRoomsWithPlumbing = numberOfRoomsWithPlumbing;
    }

    public boolean isProjectNewBuild() {
        return isProjectNewBuild;
    }

    public void setProjectNewBuild(boolean projectNewBuild) {
        isProjectNewBuild = projectNewBuild;
    }

    @Override
    public void setBudget(int budget) throws Exception {
        if (budget < 100000 || budget > 500000) {
            throw new Exception("Budget out of range. Please insert a number between 100000 and 500000");
        }

        super.setBudget(budget);
    }


    @Override
    public void setTimeline(int timeline) throws Exception {
        if (timeline < 6 || timeline > 12) {
            throw new Exception("Timeline out or range. Please insert a number between 6 and 12");
        }

        super.setTimeline(timeline);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ResidentialProject that = (ResidentialProject) object;
        return numberOfKitchens == that.numberOfKitchens && numberOfBathrooms == that.numberOfBathrooms && numberOfRoomsWithPlumbing == that.numberOfRoomsWithPlumbing && isProjectNewBuild == that.isProjectNewBuild;
    }

    public String getProjectType() {
        return projectType;
    }
}
