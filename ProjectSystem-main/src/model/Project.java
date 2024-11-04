package model;

import java.io.Serializable;
import java.util.Objects;

public class Project implements Serializable {
    private int budget;
    private int timeline;
    private int size;
    private Customer customer;

    private ProjectStatus status;

    private int materialExpenses;
    private int manHoursUsed;

    public Project(int budget, int timeline, int size) throws Exception {
        setBudget(budget);
        setTimeline(timeline);
        setSize(size);

        status = new ProjectStatus(true);
        customer = new Customer();
        materialExpenses = 0;
        manHoursUsed = 0;
    }

    public Project(int budget, int timeline, int size, boolean isOngoing) throws Exception {
        setBudget(budget);
        setTimeline(timeline);
        setSize(size);

        status = new ProjectStatus(isOngoing);
        customer = new Customer();
        materialExpenses = 0;
        manHoursUsed = 0;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) throws Exception {
        this.budget = budget;
    }

    public int getTimeline() {
        return timeline;
    }

    public void setTimeline(int timeline) throws Exception {
        this.timeline = timeline;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isOngoing() {
        return status.isOngoing();
    }

    public String getTypeName() {
        return this.getClass().getSimpleName();
    }

    public void setOngoing(boolean isOngoing) {
        status.setOngoing(isOngoing);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getMaterialExpenses() {
        return materialExpenses;
    }

    public void setMaterialExpenses(int materialExpenses) {
        this.materialExpenses = materialExpenses;
    }

    public int getManHoursUsed() {
        return manHoursUsed;
    }

    public void setManHoursUsed(int manHoursUsed) {
        this.manHoursUsed = manHoursUsed;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Project project = (Project) object;
        return budget == project.budget && timeline == project.timeline && size == project.size && Objects.equals(status, project.status);
    }

    public String getCustomerName() {
        return customer.getName();
    }
}
