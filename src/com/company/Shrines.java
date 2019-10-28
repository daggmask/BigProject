package com.company;

/**
 * <h1>Different shrines with event description</h1>
 */

public enum Shrines {
    /**
     * Healing shrine, heals hero
     */
    HEALING("The hero encounters a healing shrine and gets blessed by a surge of light"),
    /**
     * Curse shrine, lowers hero damage output
     */
    CURSE("The hero encounters a cursed shrine and feels dread looming"),
    /**
     * Damage shrine, increases hero damage output
     */
    DAMAGE("The hero encounters a damage shrine feels stronger by the presence of a god of war");

    public String shrineDescription;

    private Shrines(String shrineDescription){
        this.shrineDescription = shrineDescription;
    }
}
