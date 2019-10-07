package com.thesmeg.bots.smegsmither.config;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {
    private static final HashMap<String, HashMap<String, Integer>> smeltingRecipes;
    private static final HashMap<String, HashMap<String, Area>> smeltingLocations;

    static {
        smeltingRecipes = new HashMap<>();
        HashMap<String, Integer> bronzeBar = new HashMap<String, Integer>() {{
            put("Copper ore", 14);
            put("Tin ore", 14);
        }};
        smeltingRecipes.put("Bronze bar", bronzeBar);

        HashMap<String, Integer> silverBar = new HashMap<String, Integer>() {{
            put("Silver ore", 28);
        }};
        smeltingRecipes.put("Silver bar", silverBar);

        HashMap<String, Integer> goldAmuletU = new HashMap<String, Integer>() {{
            put("Amulet mould", 1);
            put("Gold bar", 27);
        }};
        smeltingRecipes.put("Gold amulet (u)", goldAmuletU);

        smeltingLocations = new HashMap<>();
        HashMap<String, Area> edgeville = new HashMap<String, Area>() {{
            put("Furnace", new Area.Rectangular(new Coordinate(3105, 3496, 0), new Coordinate(3109, 3501, 0)));
            put("Bank", new Area.Rectangular(new Coordinate(3092, 3491, 0), new Coordinate(3098, 3497, 0)));
        }};
        smeltingLocations.put("Edgeville", edgeville);
    }

    private Integer craftingInterfaceContainer = 446;
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

    public ArrayList<String> getAllLocationOptions() {
        ArrayList<String> smeltingLocations = new ArrayList<>();
        for (Map.Entry<String, HashMap<String, Area>> recipe : Data.smeltingLocations.entrySet()) {
            smeltingLocations.add(recipe.getKey());
        }
        return smeltingLocations;
    }

    public HashMap<String, Area> getLocation(String locationName) {
        return smeltingLocations.get(locationName);
    }

    public Area getLocationArea(String locationName, String locationAreaName) {
        HashMap<String, Area> location = getLocation(locationName);
        return location.get(locationAreaName);
    }

    public Integer getSmeltingInterfaceContainer() {
        return smeltingInterfaceContainer;
    }

    public Integer getSmeltingAnimation() {
        return smeltingAnimation;
    }

    public Integer getCraftingInterfaceContainer() {
        return craftingInterfaceContainer;
    }
}

