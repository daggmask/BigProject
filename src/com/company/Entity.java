package com.company;

import java.io.Serializable;

/**
 * <h1>Monster base</h1>
 * Base class for monsters
 */

public abstract class Entity implements Serializable {
   private String monsterAffix;
   private String monsterType;
    public abstract void recruitedMonster();

    Entity(String monsterAffix, String monsterType){
        this.monsterAffix = monsterAffix;
        this.monsterType = monsterType;
    }

    public String getMonsterAffix() {
        return monsterAffix;
    }

    public String getMonsterType() {
        return monsterType;
    }
}

