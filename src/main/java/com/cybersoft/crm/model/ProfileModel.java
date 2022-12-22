package com.cybersoft.crm.model;

public class ProfileModel {
    private int yetStartTask;
    private int doingTask;
    private int completedTask;
    private int totalTask;

    public int getYetStartTask() {
        return yetStartTask;
    }

    public void setYetStartTask(int yetStartTask) {
        this.yetStartTask = yetStartTask;
    }

    public int getDoingTask() {
        return doingTask;
    }

    public void setDoingTask(int doingTask) {
        this.doingTask = doingTask;
    }

    public int getCompletedTask() {
        return completedTask;
    }

    public void setCompletedTask(int completedTask) {
        this.completedTask = completedTask;
    }

    public int getTotalTask() {
        return totalTask;
    }

    public void setTotalTask(int totalTask) {
        this.totalTask = totalTask;
    }
}
