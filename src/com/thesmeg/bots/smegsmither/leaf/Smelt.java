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
import com.thesmeg.bots.smegsmither.playersense.CustomPlayerSense;

public class Smelt extends LeafTask {
    private SmegSmither smegSmither;

    public Smelt(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public void execute() {
//        getLogger().info("Smelt");
        final int clicks = CustomPlayerSense.Key.SPAM_CLICK_COUNT.getAsInteger();
//        Integer smeltingInterfaceContainerId = smegSmither.data.getSmeltingInterfaceContainer();
        Integer craftingInterfaceContainerId = smegSmither.data.getCraftingInterfaceContainer();
        InterfaceComponentQueryResults smeltingInterfaceComponent = Interfaces.newQuery().containers(craftingInterfaceContainerId).results();
        GameObject furnace = GameObjects.newQuery().names("Furnace").results().nearest();

        if (furnace != null) {
            if (furnace.isVisible()) {
                if (InterfaceContainers.getAt(craftingInterfaceContainerId) != null) {
                    smelt(smeltingInterfaceComponent);
                } else {
                    for (int i = 0; i < clicks; i++) {
                        furnace.interact("Smelt");
                    }
                    Execution.delayUntil(() -> InterfaceContainers.getAt(craftingInterfaceContainerId) != null, () -> false, 50, 1500, 2000);
                    smelt(smeltingInterfaceComponent);
                }
            }
        }
    }

    private void smelt(InterfaceComponentQueryResults smeltingInterfaceComponent) {
        String barToSmelt = smegSmither.settings.getBarToSmelt();
        Integer smeltingAnimationId = smegSmither.data.getSmeltingAnimation();
        final int clicks = CustomPlayerSense.Key.SPAM_CLICK_COUNT.getAsInteger();

        for (InterfaceComponent component : smeltingInterfaceComponent) {
//            String componentName = component.getName();
//            if (componentName != null) {
//                if (component.getActions().contains("All")) {
//                    if (component.click()) {
//                        return;
//                    }
//                }

            if (component.getActions() != null) {
                for (String action : component.getActions()) {
                    if (action.contains(barToSmelt)) {
                        for (int i = 0; i < clicks; i++) {
//                                component.interact("Smelt");
                            component.interact("Make " + barToSmelt);

                        }
                        Execution.delayUntil(() -> Inventory.containsOnly(barToSmelt), () -> Players.getLocal().getAnimationId() == smeltingAnimationId, 50, 1000, 2000);
                        return;
                    }
                }
            }
//                if (component.getName().equals(barToSmelt)) {
//                    for (int i = 0; i < clicks; i++) {
//                        component.interact("Smelt");
//                    }
//                    Execution.delayUntil(() -> Inventory.containsOnly(barToSmelt), () -> Players.getLocal().getAnimationId() == smeltingAnimationId, 50, 1000, 2000);
//                    return;
//                }
//            }
        }
    }
}
