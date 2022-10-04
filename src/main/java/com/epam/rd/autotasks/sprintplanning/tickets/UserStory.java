package com.epam.rd.autotasks.sprintplanning.tickets;

public class UserStory extends Ticket {
    private UserStory[] dependencies;

    public UserStory(int id, String name, int estimate){
       super(id, name, estimate);
    }

    public UserStory(int id, String name, int estimate, UserStory... dependsOn) {
        super(id, name, estimate);
        if(dependsOn != null){
            this.dependencies = new UserStory[dependsOn.length];
            for(int i = 0; i < dependencies.length; i ++){
                this.dependencies[i] = dependsOn[i];
            }
        }

    }

    @Override
    public void complete() {
        if (dependenciesCompleted()) {
            this.isCompleted = true;
        }
    }

    public UserStory[] getDependencies() {
        if(this.dependencies != null) {
            UserStory[] getDependencies = new UserStory[this.dependencies.length];
            for (int i = 0; i < this.dependencies.length; i++) {
                getDependencies[i] = this.dependencies[i];
            }
            return getDependencies;
        }
        else{
            return null;
        }
    }

    @Override
    public String toString() {
        return "[US " + this.getId() +
                "] " + this.getName();
    }

    private boolean dependenciesCompleted(){
        boolean dependenciesCompleted = false;
        if(this.getDependencies() == null){
            dependenciesCompleted = true;
        }
        else{
            UserStory[] dependencies = this.getDependencies();
            for(UserStory dependency : dependencies){
                if(dependency.isCompleted()){
                    dependenciesCompleted = true;
                }
                else{
                    dependenciesCompleted = false;
                }
            }
        }
        return  dependenciesCompleted;
    }
}
