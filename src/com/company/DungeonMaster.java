package com.company;

public class DungeonMaster extends Entity {


    DungeonMaster(String monsterAffix, String monsterName) {
        super ( monsterAffix, monsterName );
        recruitedMonster ();
    }

    public void recruitedMonster() {
        System.out.println ("Welcome Dungeon master: " + getMonsterAffix() + " " + getMonsterName());
    }
}
