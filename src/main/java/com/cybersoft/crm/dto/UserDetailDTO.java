package com.cybersoft.crm.dto;

public class UserDetailDTO {
    private String fullname;
    private String email;
    private float yetRate;
    private float doingRate;
    private float completedRate;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getYetRate() {
        return yetRate;
    }

    public void setYetRate(float yetRate) {
        this.yetRate = yetRate;
    }

    public float getDoingRate() {
        return doingRate;
    }

    public void setDoingRate(float doingRate) {
        this.doingRate = doingRate;
    }

    public float getCompletedRate() {
        return completedRate;
    }

    public void setCompletedRate(float completedRate) {
        this.completedRate = completedRate;
    }
}
