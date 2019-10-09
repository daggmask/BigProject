package com.company;

public class MonsterFactory {

    enum MonsterType{
        Minotaur,
        Goblin,
        Orc,
        Succubus,
        Elemental,
        Skeleton,
        Zombie,
        Porcupine
    }

    enum MonsterAffix{
        Furious,
        Defensive,
        Calm,
        Wise,
        Handy,
        Angry,
    }
    public static MonsterType monsterType;
    public static  MonsterAffix monsterAffix;

    public static MonsterAffix getMonsterAffix() {
        return monsterAffix;
    }

    public static MonsterType getMonsterType() {
        return monsterType;
    }
}
