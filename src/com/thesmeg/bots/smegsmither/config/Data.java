package com.thesmeg.bots.smegsmither.config;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;

import java.util.HashMap;

public class Data {
    private Area edgevilleFurnace = new Area.Rectangular(new Coordinate(3105, 3496, 0), new Coordinate(3110, 3501, 0));
    private Area edgevilleBank = new Area.Rectangular(new Coordinate(3091, 3488, 0), new Coordinate(3098, 3499, 0));
    private static final HashMap<String, HashMap<String, Integer>> smeltingRecipes;

    static {
        smeltingRecipes = new HashMap<>();
        HashMap<String, Integer> bronzeBar = new HashMap<String, Integer>() {{
            put("Copper ore", 2);
            put("Tin ore", 2);
        }};
        smeltingRecipes.put("Bronze bar", bronzeBar);
    }

    public HashMap<String, Integer> getSmeltingRecipe(String barName) {
        return smeltingRecipes.get(barName);
    }

    public Area getEdgevilleFurnace() {
        return edgevilleFurnace;
    }

    public Area getEdgevilleBank() {
        return edgevilleBank;
    }
}

