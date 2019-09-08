package com.thesmeg.bots.smegsmither.config;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {
    private static final HashMap<String, HashMap<String, Integer>> smeltingRecipes;

    static {
        smeltingRecipes = new HashMap<>();
        HashMap<String, Integer> bronzeBar = new HashMap<String, Integer>() {{
            put("Copper ore", 14);
            put("Tin ore", 14);
        }};
        smeltingRecipes.put("Bronze bar", bronzeBar);
    }

    private Area edgevilleFurnace = new Area.Rectangular(new Coordinate(3105, 3496, 0), new Coordinate(3109, 3501, 0));
    private Area edgevilleBank = new Area.Rectangular(new Coordinate(3092, 3491, 0), new Coordinate(3098, 3497, 0));
    private Integer smeltingInterfaceContainer = 270;
    private Integer smeltingAnimation = 899;

    public ArrayList<String> getAllSmeltingOptions() {
        ArrayList<String> smeltingOptions = new ArrayList<>();
        for (Map.Entry<String, HashMap<String, Integer>> recipe : smeltingRecipes.entrySet()) {
            smeltingOptions.add(recipe.getKey());
        }
        return smeltingOptions;
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

    public Integer getSmeltingInterfaceContainer() {
        return smeltingInterfaceContainer;
    }

    public Integer getSmeltingAnimation() {
        return smeltingAnimation;
    }
}

