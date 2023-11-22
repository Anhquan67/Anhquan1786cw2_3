package com.example.exercise_three_cw2.model;

public class AvatarModel {

    private Integer avatar;
    private Boolean isSelected;

    public AvatarModel(Integer avatar, Boolean isSelected) {
        this.avatar = avatar;
        this.isSelected = isSelected;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
