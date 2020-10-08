package com.github.flowmanager;

public class Pot extends AbstractUtensil {
    
    public Pot() {
        super.nextClass = Pan.class;
    }
    
    public String getUse() {
        return "boil";
    }
}