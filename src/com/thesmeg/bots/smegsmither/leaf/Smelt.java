package com.thesmeg.bots.smegsmither.leaf;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceContainers;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.queries.results.InterfaceComponentQueryResults;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

public class Smelt extends LeafTask {
    SmegSmither smegSmither;

    public Smelt(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public void execute() {
//        getLogger().info("Smelt");
        String barToSmelt = smegSmither.settings.getBarToSmelt();
        Area edgevilleFurnace = smegSmither.data.getEdgevilleFurnace();
        Integer smeltingInterfaceContainer = smegSmither.data.getSmeltingInterfaceContainer();
        Integer smeltingAnimation = smegSmither.data.getSmeltingAnimation();
        GameObject furnace = GameObjects.newQuery().names("Furnace").within(edgevilleFurnace).results().first();
        InterfaceComponentQueryResults smeltingInterface = Interfaces.newQuery().containers(smeltingInterfaceContainer).results();
        try {
            if (furnace.isVisible()) {
                if (furnace.interact("Smelt")) {
                    Execution.delayUntil(() -> InterfaceContainers.getAt(smeltingInterfaceContainer) != null, () -> false, 50, 1500, 2000);
                    for (InterfaceComponent component : smeltingInterface) {
                        if (component.getName() != null) {
                            if (component.getActions().contains("All")) {
                                if (component.click()) {
                                    return;
                                }
                            }
                            if (component.getName().equals(barToSmelt)) {
                                if (component.interact("Smelt")) {
                                    Execution.delayUntil(() -> Inventory.containsOnly(barToSmelt), () -> Players.getLocal().getAnimationId() == smeltingAnimation, 50, 1000, 2000);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException ignored) {
            ignored.printStackTrace();
        }
    }
}
