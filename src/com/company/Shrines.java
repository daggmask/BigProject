package com.company;

public enum Shrines {
    /**
     * <h1>List of different shrines with event description</h1>
     */
    HEALING("The hero encounters a healing shrine and gets blessed by a surge of light"),
    CURSE("The hero encounters a cursed shrine and feels dread looming"),
    DAMAGE("The hero encounters a damage shrine feels stronger by the presence of a god of war");

    public String shrineDescription;

    private Shrines(String shrineDescription){
        this.shrineDescription = shrineDescription;
    }
}
