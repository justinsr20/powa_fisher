package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GetSupplies extends LeafTask {

    String foodName = "Tuna";
    Integer foodAmount = 25;

    @Override
    public void execute() {
        if (!Bank.isOpen()) {
            Bank.open();
        } else if (Bank.isOpen()) {
            Bank.depositInventory();
            if (Inventory.getQuantity(foodName) != foodAmount) {
                Bank.withdraw(foodName, foodAmount);
            }
            if (Inventory.getQuantity("Law rune") != 1) {
                Bank.withdraw("Law rune", 1);
            }
            if (Inventory.getQuantity("Fire rune") != 1) {
                Bank.withdraw("Fire rune", 1);
            }
            if (Inventory.getQuantity("Air rune") != 3) {
                Bank.withdraw("Air rune", 3);
            }
        }
        if (Inventory.isFull()) {
            Bank.close();
        }
    }
}
