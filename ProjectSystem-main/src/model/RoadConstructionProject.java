package model;

import java.util.ArrayList;
import java.util.Objects;

public class RoadConstructionProject extends Project {
    /**
     * Length of the road
     */
    private int lengthOfTheRoad;

    /**
     * Width of the road
     */
    private int widthOfTheRoad;
    /**
     * Number of bridges in the Road construction project
     */
    private int numberOfBridges;
    /**
     * Number of either environmental or geographical challenges in Road construction project (e.g. steep hills, wetlands, cliffs, etc.)
     */
    ArrayList<String> challenges;

    /**
     *  Type of road construction project used for XML serialization
     */
    public final String projectType = "RoadConstruction";

    public RoadConstructionProject() throws Exception {
        super(1000000, 12, 3, false);
        this.lengthOfTheRoad = 1;
        this.widthOfTheRoad = 1;
        this.numberOfBridges = 1;
        this.challenges = new ArrayList<>();
    }

    public RoadConstructionProject(int budget, int timeline, int lengthOfTheRoad, int widthOfTheRoad, int numberOfBridges, ArrayList<String> challenges) throws Exception {
        super(budget, timeline, 0);
        this.lengthOfTheRoad = lengthOfTheRoad;
        this.widthOfTheRoad = widthOfTheRoad;
        this.numberOfBridges = numberOfBridges;
        this.challenges = challenges;
    }

    public RoadConstructionProject(int budget, int timeline, int lengthOfTheRoad, int widthOfTheRoad) throws Exception {
        super(budget, timeline, 0);
        this.lengthOfTheRoad = lengthOfTheRoad;
        this.widthOfTheRoad = widthOfTheRoad;
        this.numberOfBridges = 0;
        this.challenges = new ArrayList<>();
    }

    public RoadConstructionProject(int budget, int timeline, boolean isOngoing, int lengthOfTheRoad, int widthOfTheRoad, int numberOfBridges, ArrayList<String> challenges) throws Exception {
        super(budget, timeline, 0, isOngoing);
        this.lengthOfTheRoad = lengthOfTheRoad;
        this.widthOfTheRoad = widthOfTheRoad;
        this.numberOfBridges = numberOfBridges;
        this.challenges = challenges;
    }

    public RoadConstructionProject(int budget, int timeline, boolean isOngoing, int lengthOfTheRoad, int widthOfTheRoad) throws Exception {
        super(budget, timeline, 0, isOngoing);
        this.lengthOfTheRoad = lengthOfTheRoad;
        this.widthOfTheRoad = widthOfTheRoad;
        this.numberOfBridges = 0;
        this.challenges = new ArrayList<>();
    }

    public int getLengthOfTheRoad() {
        return lengthOfTheRoad;
    }

    public void setLengthOfTheRoad(int lengthOfTheRoad) {
        this.lengthOfTheRoad = lengthOfTheRoad;
    }

    public int getWidthOfTheRoad() {
        return widthOfTheRoad;
    }

    public void setWidthOfTheRoad(int widthOfTheRoad) {
        this.widthOfTheRoad = widthOfTheRoad;
    }

    public int getNumberOfBridges() {
        return numberOfBridges;
    }

    public void setNumberOfBridges(int numberOfBridges) {
        this.numberOfBridges = numberOfBridges;
    }

    public ArrayList<String> getChallenges() {
        return challenges;
    }

    public void setChallenges(ArrayList<String> challenges) {
        this.challenges = challenges;
    }

    public void addChallenge(String challenge) {
        this.challenges.add(challenge);
    }

    public void removeChallenge(String challenge) {
        this.challenges.remove(challenge);
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
        RoadConstructionProject that = (RoadConstructionProject) object;
        return lengthOfTheRoad == that.lengthOfTheRoad && widthOfTheRoad == that.widthOfTheRoad && numberOfBridges == that.numberOfBridges && Objects.equals(challenges, that.challenges);
    }

    public String getProjectType() {
        return projectType;
    }
}

