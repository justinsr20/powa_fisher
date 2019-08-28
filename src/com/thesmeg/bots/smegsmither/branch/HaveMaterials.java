package com.thesmeg.bots.smegsmither.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

import java.util.HashMap;

public class HaveMaterials extends BranchTask {
    private SmegSmither smegSmither;

    public HaveMaterials(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public boolean validate() {
        String barToSmelt = smegSmither.settings.getBarToSmelt();
        HashMap<String, Integer> requiredOres = smegSmither.data.getSmeltingRecipe(barToSmelt);
        for (HashMap.Entry<String, Integer> ore : requiredOres.entrySet()) {
            String oreName = ore.getKey();
            Integer oreAmount = ore.getValue();
            if (Inventory.getQuantity(oreName) <= oreAmount) {
                return false;
            }
        }
        return true;
    }

    @Override
    public TreeTask successTask() {
        return smegSmither.atFurnace;
    }

    @Override
    public TreeTask failureTask() {
        return smegSmither.atBank;
    }
}
