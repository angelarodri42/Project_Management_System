package model;

import java.util.Objects;

public class IndustrialProject  extends Project{
    /**
     * Type of industrial facility being constructed (e.g. manufacturing plant, warehouse, distribution center).
     */
    private String type;

    /**
     *  Type of industrial project used for XML serialization
     */
    public final String projectType = "Industrial";

    public IndustrialProject() throws Exception {
        super(1000000, 12, 3, false);
        this.type = "Manufacturing plant";
    }

    public IndustrialProject(int budget, int timeline, int size, String type) throws Exception {
        super(budget, timeline, size);
        setType(type);
    }

    public IndustrialProject(int budget, int size, String type) throws Exception {
        super(budget, 30, size);
        setType(type);
    }

    public IndustrialProject(int budget, int timeline, int size, boolean isOngoing, String type) throws Exception {
        super(budget, timeline, size, isOngoing);
        setType(type);

    }

    public IndustrialProject(int budget, int size, boolean isOngoing, String type) throws Exception {
        super(budget, 30, size, isOngoing);
        setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setBudget(int budget) throws Exception {
        if (budget < 1000000 || budget > 5000000) {
            throw new Exception("Budget out of range. Please insert a number between 1000000 and 5000000");
        }

        super.setBudget(budget);
    }


    @Override
    public void setTimeline(int timeline) throws Exception {
        if (timeline < 12 || timeline > 36) {
            throw new Exception("Timeline out or range. Please insert a number between 12 and 36");
        }

        super.setTimeline(timeline);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        IndustrialProject that = (IndustrialProject) object;
        return Objects.equals(type, that.type);
    }

    public String getProjectType() {
        return projectType;
    }
}
