package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;

public class Sprint {

    private final int timeCapacity;
    private final int ticketsLimit;
    private Ticket[] tickets;
    private int index;
    public Sprint(int capacity, int ticketsLimit) {
        if (capacity != 0 && ticketsLimit != 0) {
            this.timeCapacity = capacity;
            this.ticketsLimit = ticketsLimit;
            this.tickets = new Ticket[this.ticketsLimit];
            this.index = 0;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean addUserStory(UserStory userStory) {
        if(userStory == null){
            return false;
        }
        if (!(ticketCompleted(userStory)) && this.ticketWithinLimit(userStory) && this.ticketSatisfyEstimate(userStory) && dependenciesSatisfyConditions(userStory)) {
            this.tickets[index] = userStory;
            this.index++;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean addBug(Bug bugReport) {
        if(bugReport == null){
            return false;
        }
        if (!ticketCompleted(bugReport) && this.ticketWithinLimit(bugReport) && this.ticketSatisfyEstimate(bugReport)) {
            this.tickets[index] = bugReport;
            this.index++;
            return true;
        } else {
            return false;
        }
    }

    public Ticket[] getTickets() {
        if (this.tickets != null) {
            int ticketsAddedLength = 0;
            for (Ticket ticket : this.tickets) {
                if (ticket != null) {
                    ticketsAddedLength++;
                }
            }
            Ticket[] getTickets = new Ticket[ticketsAddedLength];
            for (int i = 0; i < getTickets.length; i++) {
                if (this.tickets[i] != null) {
                    getTickets[i] = this.tickets[i];
                }
            }
            return getTickets;
        } else {
            return null;
        }
    }

    public int getTotalEstimate() {
        int totalEstimate = 0;
        for (int i = 0; i < this.ticketsLimit; i++) {
            if (this.tickets[i] != null) {
                totalEstimate += this.tickets[i].getEstimate();
            }
        }
        return totalEstimate;
    }

    private static boolean ticketCompleted(Ticket ticket) {
        return ticket.isCompleted();
    }

    private boolean ticketSatisfyEstimate(Ticket ticket) {
        return (this.getTotalEstimate() + ticket.getEstimate()) <= this.timeCapacity;
    }

    private boolean dependenciesSatisfyConditions(UserStory userStory) {
        if(userStory.getDependencies() == null){
            return true;
        }
        if (this.getTickets() == null) {
            return false;
        }

        if (this.uncompletedDependenciesPresent(userStory)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean ticketWithinLimit(Ticket ticket) {
        return this.index <= (this.ticketsLimit - 1);
    }

    private boolean uncompletedDependenciesPresent(UserStory userStory) {
        boolean present = false;
        UserStory[] dependencies = userStory.getDependencies();

        if (userStory.getDependencies() != null) {
            for (int i = 0; i < dependencies.length; i++) {
                if(!dependencies[i].isCompleted()){
                    for(int j = 0; j < this.getTickets().length; j++){
                        if(dependencies[i].getId() == this.getTickets()[j].getId()){
                            present = true;
                        }
                        else{
                            present = false;
                        }
                    }
                }
            }
        }
        return present;
    }

}
