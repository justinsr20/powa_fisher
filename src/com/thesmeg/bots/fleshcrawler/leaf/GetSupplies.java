package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.CustomPlayerSense;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;

import java.util.Map;
import java.util.stream.Stream;

public class GetSupplies extends LeafTask {

    private FleshCrawler fleshCrawler;

    public GetSupplies(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public void execute() {
        if (fleshCrawler.useRange) {
            if (Inventory.contains(fleshCrawler.getAmmunitionName())) {
                Inventory.getItems(fleshCrawler.getAmmunitionName()).first().click();
                return;
            }
        }

        if (!Bank.isOpen() && !Inventory.contains(fleshCrawler.getAmmunitionName())) {
            Bank.open();
            return;
        }
        if (Bank.isOpen()) {
            // deposit if items overdrawn
            for (Map.Entry<String, Integer> item : fleshCrawler.requiredItems.entrySet()) {
                if (Inventory.getItems(item.getKey()).size() != 0 && Inventory.getQuantity(item.getKey()) != item.getValue()) {
                    getLogger().info("Depositing overdrawn item " + item.getKey());
                    if (Bank.depositInventory()) {
                        Execution.delayUntil(() -> Inventory.isEmpty(), () -> false, 50, 1000, 2000);
                        return;
                    }
                }
            }

            // deposit loot
            Stream<SpriteItem> itemsToDeposit = Inventory.getItems().stream()
                    .filter(item -> !fleshCrawler.requiredItems.keySet().contains(item.getDefinition().getName()));
            if (itemsToDeposit.count() >= 1) {
                getLogger().info("Depositing loot");
                final boolean useDepositAll = CustomPlayerSense.Key.USE_DEPOSIT_ALL.getAsBoolean();
                if (useDepositAll) {
                    if (Bank.depositInventory()) {
                        Execution.delayUntil(() -> Inventory.isEmpty(), () -> false, 50, 1000, 2000);
                    } else {
                        return;
                    }
                } else {
                    for (SpriteItem item : Inventory.getItems()) {
                        item.interact("Deposit-All");
                        Execution.delayUntil(() -> !Inventory.contains(item.getDefinition().getName()), () -> false, 50, 1000, 2000);
                        return;
                    }
                }
            }

            //withdraw items
            for (Map.Entry<String, Integer> item : fleshCrawler.requiredItems.entrySet()) {
                if (Inventory.getQuantity(item.getKey()) != item.getValue()) {
                    getLogger().info("Withdrawing " + item);
                    if (Bank.withdraw(item.getKey(), item.getValue())) {
                        Execution.delayUntil(() -> Inventory.contains(item.getKey()), () -> false, 50, 500, 1500);
                    } else {
                        return;
                    }
                }
            }
            Bank.close();
        }
    }
}
