package com.github.flowmanager;

public class Pan extends AbstractUtensil {
    
    public Pan() {
        super.nextClass = Spoon.class;
    }
    
    public String getUse() {
        return "fry";
    }

}
