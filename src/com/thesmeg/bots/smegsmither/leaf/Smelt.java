package com.thesmeg.bots.smegsmither.leaf;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceContainers;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.InterfaceComponentQueryResults;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

public class Smelt extends LeafTask {
    private SmegSmither smegSmither;

    public Smelt(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public void execute() {
//        getLogger().info("Smelt");
        Integer smeltingInterfaceContainerId = smegSmither.data.getSmeltingInterfaceContainer();
        InterfaceComponentQueryResults smeltingInterfaceComponent = Interfaces.newQuery().containers(smeltingInterfaceContainerId).results();
        GameObject furnace = GameObjects.newQuery().names("Furnace").results().nearest();

        try {
            if (furnace.isVisible()) {
                if (InterfaceContainers.getAt(smeltingInterfaceContainerId) != null) {
                    smelt(smeltingInterfaceComponent);
                } else if (furnace.interact("Smelt")) {
                    Execution.delayUntil(() -> InterfaceContainers.getAt(smeltingInterfaceContainerId) != null, () -> false, 50, 1500, 2000);
                    smelt(smeltingInterfaceComponent);
                }
            }
        } catch (NullPointerException ignored) {
            ignored.printStackTrace();
        }
    }

    private void smelt(InterfaceComponentQueryResults smeltingInterfaceComponent) {
        String barToSmelt = smegSmither.settings.getBarToSmelt();
        Integer smeltingAnimationId = smegSmither.data.getSmeltingAnimation();

        for (InterfaceComponent component : smeltingInterfaceComponent) {
            if (component.getName() != null) {
                if (component.getActions().contains("All")) {
                    if (component.click()) {
                        return;
                    }
                }
                if (component.getName().equals(barToSmelt)) {
                    if (component.interact("Smelt")) {
                        Execution.delayUntil(() -> Inventory.containsOnly(barToSmelt), () -> Players.getLocal().getAnimationId() == smeltingAnimationId, 50, 1000, 2000);
                        return;
                    }
                }
            }
        }
    }
}
