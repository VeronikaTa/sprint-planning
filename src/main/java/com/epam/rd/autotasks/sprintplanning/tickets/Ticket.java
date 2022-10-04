package com.epam.rd.autotasks.sprintplanning.tickets;

public class  Ticket {
    protected int id;
    protected String name;
    protected int estimate;
    protected boolean isCompleted = false;

    public Ticket(int id, String name, int estimate) {
            this.id = id;
            this.name = name;
            this.estimate = estimate;
    }

    public int getId() {
        if(this != null) {
            return this.id;
        }
        else{
            return 0;
        }
    }

    public String getName() {
        if(this != null) {
            return this.name;
        }
        else{
            return null;
        }
    }

    public boolean isCompleted() {
        if(this != null){
            return this.isCompleted;
        }
        else{
            return false;
        }
    }

    public void complete() {
        if(this != null) {
            this.isCompleted = true;
        }
    }

    public int getEstimate() {
        if (this != null) {
            return this.estimate;
        }
        else{
            return 0;
        }
    }
}
