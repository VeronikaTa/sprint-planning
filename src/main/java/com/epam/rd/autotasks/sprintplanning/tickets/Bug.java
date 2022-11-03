package com.epam.training.student_veronika_tarasova.sprint_planning.src.main.java.com.epam.rd.autotasks.sprintplanning.tickets;

public class Bug extends Ticket {
    public UserStory userStory;

    public static Bug createBug(int id, String name, int estimate, UserStory userStory) {
         if(userStory.isCompleted() && userStory != null){
            Bug bug = new Bug(id, name, estimate, userStory);
            return bug;
        }
        else{
            return null;
        }
    }

    private Bug(int id, String name, int estimate, UserStory userStory) {
        super(id, name, estimate);
        this.userStory = new UserStory(userStory.getId(), userStory.getName(), userStory.getEstimate());
    }

    @Override
    public String toString() {
        return "[Bug " + this.getId() + "] " + this.userStory.getName() + ": " + this.getName();
    }
}
