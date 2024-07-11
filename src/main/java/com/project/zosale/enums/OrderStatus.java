package com.project.zosale.enums;

public enum OrderStatus {
    COMPLETED("COMPLETED"),
    PENDING("PENDING"),
    CANCELLED("CANCELLED");

    public  String status;

    OrderStatus(String status){
        this.status = status;
    }
}
