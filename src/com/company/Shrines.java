package com.company;

/**
 * <h1>Different shrines with event description</h1>
 */

public enum Shrines {
    /**
     * Description used for print upon shrine encountered
     */
    HEALING("The hero encounters a healing shrine and gets blessed by a surge of light"),
    CURSE("The hero encounters a cursed shrine and feels dread looming"),
    DAMAGE("The hero encounters a damage shrine feels stronger by the presence of a god of war");

    public String shrineDescription;

    private Shrines(String shrineDescription){
        this.shrineDescription = shrineDescription;
    }
}
