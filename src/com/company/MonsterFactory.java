package com.company;

/**
 * <h1>Factory</h1>
 * Factory class used to create random monsters to create dynamic when monster is created
 */

public class MonsterFactory {

    /**
     * Monster types randomly selected when monster is created
     */
    enum MonsterType {
        Minotaur("Minotaur"),
        Goblin("Goblin"),
        Orc("Orc"),
        Succubus("Succubus"),
        Elemental("Elemental"),
        Skeleton("Skeleton"),
        Zombie("Zombie"),
        Porcupine("Porcupine"),
        Slime("Slime"),
        Beastman("Beastman");


        public String monsterType;

        private MonsterType(String monsterType) {
            this.monsterType = monsterType;
        }
    }

    /**
     * Monster affix randomly selected when monster is created
     */
    enum MonsterAffix {
        Furious("Furious"),
        Defensive("Defensive"),
        Calm("Calm"),
        Wise("Wise"),
        Handy("Handy"),
        Ugly("Ugly"),
        Strong("Strong"),
        Weak("Weak"),
        Angry("Angry");

        public String monsterAffix;

        private MonsterAffix(String monsterAffix) {
            this.monsterAffix = monsterAffix;
        }
    }

    public static MonsterType monsterType;
    public static MonsterAffix monsterAffix;

    public static MonsterAffix getMonsterAffix() {
        return monsterAffix;
    }

    public static MonsterType getMonsterType() {
        return monsterType;
    }
}
