package com.thesmeg.bots.powafisher.branch;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.powafisher.leaf.DropFish;
import com.thesmeg.bots.powafisher.leaf.Logout;

/**
 * NOTES:
 */
public class Fish extends BranchTask {

    public static String selectedFish;
    Player player;
    Npc fishingSpot;
    private DropFish dropFish = new DropFish();
    private Logout logout = new Logout();
    private String fishingAction;
    private String fishingSpotType;

    @Override
    public boolean validate() {
        if (selectedFish == null) {
            return true;
        } else if (selectedFish.equals("Shrimp/Anchovies")) {
            fishingAction = "Net";
            fishingSpotType = "Fishing spot";
        } else if (selectedFish.equals("Sardines/Herring")) {
            fishingAction = "Bait";
            fishingSpotType = "Fishing spot";
        } else if (selectedFish.equals("Trout/Salmon")) {
            fishingAction = "Lure";
            fishingSpotType = "Rod Fishing spot";
        } else if (selectedFish.equals("Pike")) {
            fishingAction = "Bait";
            fishingSpotType = "Rod Fishing spot";
        } else if (selectedFish.equals("Tuna/Swordfish")) {
            fishingAction = "Harpoon";
            fishingSpotType = "Fishing spot";
        } else if (selectedFish.equals("Lobster")) {
            fishingAction = "Cage";
            fishingSpotType = "Fishing spot";
        }

        player = Players.getLocal();
        fishingSpot = Npcs.newQuery().names(fishingSpotType).actions(fishingAction).results().nearest();

        if (fishingSpot != null) {
            //Found bug when fish is selected it tries to use it on fishing spot
            if (Inventory.getSelectedItem() != null) {
                Inventory.getSelectedItem().click();
            }
            if (fishingSpot.interact(fishingAction)) {
                Execution.delayUntil(() -> Inventory.isFull(), () -> player.getAnimationId() != -1, 50, 1000, 2000);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public TreeTask successTask() {
        return dropFish;
    }

    @Override
    public TreeTask failureTask() {
        return logout;
    }
}
