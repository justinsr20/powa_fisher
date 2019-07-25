package com.thesmeg.bots.outlaw.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GetSupplies extends LeafTask {

    String foodName = "Pike";

    @Override
    public void execute() {
        if (!Bank.isOpen()) {
            Bank.open();
        } else if (Bank.isOpen()) {
            Bank.withdraw(foodName, 28);
            Bank.close();
        }
    }
}
