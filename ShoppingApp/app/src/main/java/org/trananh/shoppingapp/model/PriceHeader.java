package org.trananh.shoppingapp.model;

import java.io.Serializable;
import java.sql.Date;

public class PriceHeader implements Serializable {
    private String id;
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean status;
    private User createUser;

    public PriceHeader(String id) {
        this.id = id;
    }

    public PriceHeader() {
    }

    public PriceHeader(String id, String description, Date startDate, Date endDate, boolean status, User createUser) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createUser = createUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return "PriceHeader{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", createUser=" + createUser +
                '}';
    }
}
