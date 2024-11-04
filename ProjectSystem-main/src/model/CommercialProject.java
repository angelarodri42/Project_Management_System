package model;

import java.util.Objects;

public class CommercialProject extends Project {
    /**
     *  Number of floors in the Commercial Project
     */
    private int numberOfFloors;
    /**
     *  Intended use of the building (e.g. office, retail, restaurant, etc.)
     */
    private String intendedUseOfTheBuilding;

    /**
     *  Type of commercial project used for XML serialization
     */
    public final String projectType = "Commercial";

    public CommercialProject() throws Exception {
        super(500000, 12, 3, false);
        this.numberOfFloors = 1;
        this.intendedUseOfTheBuilding = "Office";
    }

    public CommercialProject(int budget, int timeline, int size, int numberOfFloors, String intendedUseOfTheBuilding) throws Exception {
        super(budget, timeline, size);
        this.numberOfFloors = numberOfFloors;
        this.intendedUseOfTheBuilding = intendedUseOfTheBuilding;
    }

    public CommercialProject(int budget, int size, String intendedUseOfTheBuilding) throws Exception {
        super(budget, 18, size);
        this.numberOfFloors = 1;
        this.intendedUseOfTheBuilding = intendedUseOfTheBuilding;
    }

    public CommercialProject(int budget, int timeline, int size, boolean isOngoing, int numberOfFloors, String intendedUseOfTheBuilding) throws Exception {
        super(budget, timeline, size, isOngoing);
        this.numberOfFloors = numberOfFloors;
        this.intendedUseOfTheBuilding = intendedUseOfTheBuilding;
    }

    public CommercialProject(int budget, int timeline, boolean isOngoing, String intendedUseOfTheBuilding) throws Exception {
        super(budget, timeline, 18, isOngoing);
        this.numberOfFloors = 1;
        this.intendedUseOfTheBuilding = intendedUseOfTheBuilding;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public String getIntendedUseOfTheBuilding() {
        return intendedUseOfTheBuilding;
    }

    public void setIntendedUseOfTheBuilding(String intendedUseOfTheBuilding) {
        this.intendedUseOfTheBuilding = intendedUseOfTheBuilding;
    }

    @Override
    public void setBudget(int budget) throws Exception {
        if (budget < 500000 || budget > 2000000) {
            throw new Exception("Budget out of range. Please insert a number between 500000 and 2000000");
        }

        super.setBudget(budget);
    }


    @Override
    public void setTimeline(int timeline) throws Exception {
        if (timeline < 12 || timeline > 24) {
            throw new Exception("Timeline out or range. Please insert a number between 12 and 24");
        }

        super.setTimeline(timeline);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CommercialProject that = (CommercialProject) object;
        return numberOfFloors == that.numberOfFloors && Objects.equals(intendedUseOfTheBuilding, that.intendedUseOfTheBuilding);
    }

    public String getProjectType() {
        return projectType;
    }
}
