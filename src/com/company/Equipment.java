package com.company;

import java.io.Serializable;

/**
 * <h1>Equipment base</h1>
 * Base for equipment
 */

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
