package com.company;

import java.io.Serializable;

public abstract class Entity implements Serializable {
   private String monsterAffix;
   private String monsterName;
    public abstract void recruitedMonster();
    Entity(String monsterAffix, String monsterName){
        this.monsterAffix = monsterAffix;
        this.monsterName = monsterName;
    }

    public String getMonsterAffix() {
        return monsterAffix;
    }

    public String getMonsterName() {
        return monsterName;
    }
}

