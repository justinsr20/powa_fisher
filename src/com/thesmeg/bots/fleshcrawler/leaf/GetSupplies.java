package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;
import com.thesmeg.bots.fleshcrawler.playersense.CustomPlayerSense;

import java.util.Map;
import java.util.stream.Stream;

public class GetSupplies extends LeafTask {

    private final int executionDelayMin = CustomPlayerSense.Key.EXECUTION_DELAY_MIN.getAsInteger();
    private final int executionDelayMax = CustomPlayerSense.Key.EXECUTION_DELAY_MAX.getAsInteger();
    private FleshCrawler fleshCrawler;

    public GetSupplies(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public void execute() {
        String ammunitionName = fleshCrawler.getAmmunitionName();
        if (fleshCrawler.useRange) {
            if (Inventory.contains(ammunitionName)) {
                SpriteItem ammunition = Inventory.getItems(fleshCrawler.getAmmunitionName()).first();
                if (ammunition != null) {
                    if (ammunition.click()) {
                        Execution.delayUntil(() -> !Inventory.contains(fleshCrawler.getAmmunitionName()), () -> false, 50, executionDelayMin, executionDelayMax);
                    }
                } else {
                    return;
                }
            }
        }

        if (!Bank.isOpen() && !Inventory.contains(fleshCrawler.getAmmunitionName())) {
            if (Bank.open()) {
                Execution.delayUntil(() -> Bank.isOpen(), () -> false, 50, executionDelayMin, executionDelayMax);
            } else {
                return;
            }
        }

        if (Bank.isOpen()) {
            // deposit if items overdrawn
            for (Map.Entry<String, Integer> item : fleshCrawler.requiredItems.entrySet()) {
                if (Inventory.getItems(item.getKey()).size() != 0 && Inventory.getQuantity(item.getKey()) != item.getValue()) {
                    getLogger().info("Depositing overdrawn item " + item.getKey());
                    if (Bank.depositInventory()) {
                        Execution.delayUntil(() -> Inventory.isEmpty(), () -> false, 50, executionDelayMin, executionDelayMax);
                    } else {
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
                        Execution.delayUntil(() -> Inventory.isEmpty(), () -> false, 50, executionDelayMin, executionDelayMax);
                    } else {
                        return;
                    }
                } else {
                    for (SpriteItem item : Inventory.getItems()) {
                        ItemDefinition itemDefinition = item.getDefinition();
                        if (itemDefinition != null) {
                            if (item.interact("Deposit-All")) {
                                Execution.delayUntil(() -> !Inventory.contains(itemDefinition.getName()), () -> false, 50, executionDelayMin, executionDelayMax);
                            } else {
                                return;
                            }
                        }
                    }
                }
            }

            //withdraw items
            for (Map.Entry<String, Integer> item : fleshCrawler.requiredItems.entrySet()) {
                if (Inventory.getQuantity(item.getKey()) != item.getValue()) {
                    getLogger().info("Withdrawing " + item);
                    if (!Bank.contains(item.getKey())) {
                        Environment.getBot().stop("Ran out of " + item.getKey());
                    }
                    if (Bank.withdraw(item.getKey(), item.getValue())) {
                        Execution.delayUntil(() -> Inventory.contains(item.getKey()), () -> false, 50, executionDelayMin, executionDelayMax);
                    } else {
                        return;
                    }
                }
            }
            if (Bank.open()) {
                Bank.close();
            }
        }
    }
}
