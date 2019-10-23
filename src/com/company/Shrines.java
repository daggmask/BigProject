package com.company;

public enum Shrines {
    HEALING("The hero encounters a healing shrine and gets blessed by a surge of light"),
    DAMAGE("The hero encounters a damage shrine feels stronger by the presence of a god of war");

    public String shrineDescription;

    private Shrines(String shrineDescription){
        this.shrineDescription = shrineDescription;
    }
}
