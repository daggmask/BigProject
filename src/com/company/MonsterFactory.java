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
        MINOTAUR("Minotaur"),
        GOBLIN("Goblin"),
        ORC("Orc"),
        SUCCUBUS("Succubus"),
        ELEMENTAL("Elemental"),
        SKELETON("Skeleton"),
        ZOMBIE("Zombie"),
        PORCUPINE("Porcupine"),
        SLIME("Slime"),
        KAPPA("Kappa"),
        PIXIE("Pixie"),
        LIZARD_MAN("Lizard-man"),
        SPIDER("Spider"),
        COW("Cow"),
        POG("Pog"),
        FLOWER_MONSTER("Flower monster"),
        VAMPIRE("Vampire"),
        FRANKENSTEIN("Frankenstein's monster"),
        IMP("Imp"),
        FEL_HOUND("Fel hound"),
        DEMON("Demon"),
        HOBBIT("Hobbit"),
        DRAGON("Dragon"),
        DARK_ELF("Dark elf"),
        GOLEM("Golem"),
        GARGOLYE("Gargolye"),
        BEASTMAN("Beastman");


        public String monsterType;

        private MonsterType(String monsterType) {
            this.monsterType = monsterType;
        }
    }

    /**
     * Monster affix randomly selected when monster is created
     */
    enum MonsterAffix {
        FURIOUS("Furious"),
        DEFENSIVE("Defensive"),
        AGGRESSIVE("Aggressive"),
        HIDEOUS("Hideous"),
        PASSIVE("Passive"),
        LAZY("Lazy"),
        CALM("Calm"),
        WISE("Wise"),
        DEPRESSED("Depressed"),
        HANDY("Handy"),
        UGLY("Ugly"),
        MUSCULAR("Muscular"),
        WEAK("Weak"),
        BEAUTIFUL("Beautiful"),
        CUTE("Cute"),
        STUPID("Stupid"),
        HANDSOME("Handsome"),
        STUNNING("Stunning"),
        HAIRY("Hairy"),
        HAIRLESS("Hairless"),
        SPEEDY("Speedy"),
        SLOW("Slow"),
        ANGRY("Angry");

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
