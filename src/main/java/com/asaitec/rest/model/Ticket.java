package com.asaitec.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private long ticketId;
    private long assignedOperatorId;
    private String description;
    private List<Comment> comments = new ArrayList<>();
    private TicketStatus status = TicketStatus.OPEN;

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public long getAssignedOperatorId() {
        return assignedOperatorId;
    }

    public void setAssignedOperatorId(long assignedOperatorId) {
        this.assignedOperatorId = assignedOperatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public void addComments(List<Comment> comments) {
        this.comments.addAll(comments);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", assignedOperatorId=" + assignedOperatorId +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                '}';
    }
}
