package com.company;

import java.io.Serializable;

public class Equipment implements Serializable {
    private String gear;

    Equipment(String gear){
        this.gear = gear;
    }

    public String getGear() {
        return gear;
    }

    public String toString(){
        return "items: " + gear;
    }
}
