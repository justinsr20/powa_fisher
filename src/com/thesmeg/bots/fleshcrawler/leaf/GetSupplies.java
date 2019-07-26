package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GetSupplies extends LeafTask {

    String foodName = "Tuna";

    @Override
    public void execute() {
        if (!Bank.isOpen()) {
            Bank.open();
        } else if (Bank.isOpen()) {
            Bank.withdraw(foodName, 10);
            Bank.withdraw("Law rune", 1);
            Bank.withdraw("Fire rune", 1);
            Bank.withdraw("Air rune", 3);
            Bank.close();
        }
    }
}
