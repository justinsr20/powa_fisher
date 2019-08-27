package com.thesmeg.bots.smegsmither.config;

import java.util.HashMap;

public class Data {
    private static final HashMap<String, HashMap<String, Integer>> smeltingRecipes;

    static {
        smeltingRecipes = new HashMap<>();
        HashMap<String, Integer> bronzeBar = new HashMap<String, Integer>() {{
            put("Iron ore", 2);
            put("Tin ore", 2);
        }};
        smeltingRecipes.put("Bronze bar", bronzeBar);
    }

    public HashMap getSmeltingRecipes() {
        return smeltingRecipes;
    }
    public HashMap<String, Integer> getSmeltingRecipe(String barName){
        return smeltingRecipes.get(barName);
    }
}

