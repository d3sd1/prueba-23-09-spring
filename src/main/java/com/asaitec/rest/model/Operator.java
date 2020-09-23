package com.asaitec.rest.model;

public class Operator {
    private String name;
    private OperatorStatus status;

    public Operator(String name, OperatorStatus status) {
        this.name = name;
        this.status = status;
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
}
