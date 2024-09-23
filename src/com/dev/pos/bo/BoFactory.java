package com.dev.pos.bo;

import com.dev.pos.util.Enum.BoType;

public class BoFactory {

    private static BoFactory boFactory = new BoFactory();

    private BoFactory() {}


    public static BoFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public <T> T getBo(BoType boType){
        switch(boType){
            case USER:

            default:
                return null;
        }
    }
}
