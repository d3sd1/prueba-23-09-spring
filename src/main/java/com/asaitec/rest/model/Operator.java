package com.asaitec.rest.model;

public class Operator {
    private long id;
    private String name;
    private OperatorStatus status;

    public Operator(String name, OperatorStatus status) {
        this.id = 1;
        this.name = name;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OperatorStatus getStatus() {
        return status;
    }

    public void setStatus(OperatorStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
