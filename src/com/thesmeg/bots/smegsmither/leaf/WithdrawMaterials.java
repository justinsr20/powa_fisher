package com.thesmeg.bots.smegsmither.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

import java.util.HashMap;

public class WithdrawMaterials extends LeafTask {
    private SmegSmither smegSmither;

    public WithdrawMaterials(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public void execute() {
//        getLogger().info("WithdrawMaterials");
        if (!Bank.isOpen()) {
            Bank.open();
        }

        if (Bank.isOpen() && !Inventory.isEmpty()) {
            Bank.depositInventory();
            Execution.delayUntil(() -> Inventory.isEmpty(), () -> false, 50, 1000, 2000);
        }

        if (Bank.isOpen() && Inventory.isEmpty()) {
            String barToSmelt = smegSmither.settings.getBarToSmelt();
            HashMap<String, Integer> requiredOres = smegSmither.data.getSmeltingRecipe(barToSmelt);
            for (HashMap.Entry<String, Integer> ore : requiredOres.entrySet()) {
                String oreName = ore.getKey();
                Integer oreAmount = ore.getValue();
                if (Bank.withdraw(oreName, oreAmount)) {
                    Execution.delayUntil(() -> Inventory.contains(oreName), () -> false, 50, 1000, 2000);
                } else {
                    return;
                }
            }
        }

        if (Bank.isOpen()) {
            Bank.close();
            Execution.delayUntil(() -> !Bank.isOpen(), () -> false, 50, 1000, 2000);
        }
    }
}
