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
        if (bugReport == null) {
            return false;
        }
        if (!(ticketCompleted(bugReport)) && this.ticketWithinLimit(bugReport) && this.ticketSatisfyEstimate(bugReport)) {
            this.tickets[index] = bugReport;
            this.index++;
            return true;
        } else {
            return false;
        }
    }

    public Ticket[] getTickets() {
        if (this.tickets != null) {
            Ticket[] getTickets = new Ticket[this.ticketsLimit];
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
        if (userStory.getDependencies() != null && this.getTickets() == null) {
            return false;
        }

        if (userStory.getDependencies() != null && !(this.presentDependenciesCompleted(userStory))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean ticketWithinLimit(Ticket ticket) {
        return this.index <= (this.ticketsLimit - 1);
    }

    private boolean presentDependenciesCompleted(UserStory userStory) {
        boolean completed = false;
        UserStory[] dependencies = userStory.getDependencies();
        int ticketsAddedLength = 0;
        for (Ticket ticket : this.tickets) {
            if (ticket != null) {
                ticketsAddedLength++;
            }
        }

        if (userStory.getDependencies() != null) {
            for (int i = 0; i < ticketsAddedLength; i++) {
                for (int j = 0; j < dependencies.length; j++) {
                    if (this.getTickets()[i].getId() == dependencies[j].getId() && dependencies[j].isCompleted()) {
                        completed = true;
                    }
                    else{
                        completed = false;
                        break;
                    }
                }
            }
        }
        return completed;
    }
}
