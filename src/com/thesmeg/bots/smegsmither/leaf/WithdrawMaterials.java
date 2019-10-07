package com.thesmeg.bots.smegsmither.leaf;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

import java.util.HashMap;
import java.util.Set;

public class WithdrawMaterials extends LeafTask {
    private SmegSmither smegSmither;

    public WithdrawMaterials(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public void execute() {
//        getLogger().info("WithdrawMaterials");
        String itemToSmelt = smegSmither.settings.getBarToSmelt();
        int amountToSmelt = Inventory.getQuantity(itemToSmelt);

        if (!Bank.isOpen()) {
            if (Bank.open()) {
                Execution.delayUntil(() -> Bank.isOpen(), () -> false, 50, 1000, 2000);
            } else {
                return;
            }
        }

        if (Bank.isOpen()) {
            if (Inventory.contains(itemToSmelt)) {
                if (Bank.deposit(itemToSmelt, amountToSmelt)) {
                    Execution.delayUntil(() -> !Inventory.contains(itemToSmelt), () -> false, 50, 1000, 2000);
                } else {
                    return;
                }
            }

            HashMap<String, Integer> requiredOres = smegSmither.data.getSmeltingRecipe(itemToSmelt);
            for (HashMap.Entry<String, Integer> ore : requiredOres.entrySet()) {
                String oreName = ore.getKey();
                Integer oreAmount = ore.getValue();
                if (Inventory.getQuantity(oreName) != 0 && Inventory.getQuantity(oreName) != oreAmount) {
                    if (Bank.depositInventory()) {
                        Execution.delayUntil(() -> Inventory.isEmpty(), () -> false, 50, 1000, 2000);
                    } else {
                        return;
                    }
                } else if (Inventory.getQuantity(oreName) == 0) {
                    if (Bank.getQuantity(oreName) < oreAmount) {
                        Environment.getBot().stop("Not enough " + oreName);
                    }
                    if (Bank.withdraw(oreName, oreAmount)) {
                        Execution.delayUntil(() -> Inventory.contains(oreName), () -> false, 50, 1000, 2000);
                    } else {
                        return;
                    }
                }
            }

            Set<String> oreNames = smegSmither.data.getSmeltingRecipe(itemToSmelt).keySet();
            for (SpriteItem inventoryItem : Inventory.getItems()) {
                String itemName = inventoryItem.getDefinition().getName();
                if (!oreNames.contains(itemName)) {
                    if (Bank.depositInventory()) {
                        Execution.delayUntil(() -> Inventory.isEmpty(), () -> false, 50, 1000, 2000);
                        return;
                    } else {
                        return;
                    }
                }
            }
        }

        if (Bank.isOpen()) {
            Bank.close();
            Execution.delayUntil(() -> !Bank.isOpen(), () -> false, 50, 1000, 2000);
        }
    }
}
