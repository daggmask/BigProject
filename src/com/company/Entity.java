package com.company;

import java.io.Serializable;

public abstract class Entity implements Serializable {
   private String monsterAffix;
   private String monsterName;
    public abstract void recruitedMonster();

    Entity(MonsterFactory.MonsterAffix monsterAffix, MonsterFactory.MonsterType monsterName){
        this.monsterAffix = monsterAffix.toString();
        this.monsterName = monsterName.toString();
    }

    public String getMonsterAffix() {
        return monsterAffix;
    }

    public String getMonsterName() {
        return monsterName;
    }
}

