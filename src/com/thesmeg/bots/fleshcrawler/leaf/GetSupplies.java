package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetSupplies extends LeafTask {

    private FleshCrawler fleshCrawler;

    public GetSupplies(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    public final Map<String, Integer> requiredItems() {
        return Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>("Pike", 25),
                new AbstractMap.SimpleEntry<>("Fire rune", 1),
                new AbstractMap.SimpleEntry<>("Air rune", 3),
                new AbstractMap.SimpleEntry<>("Law rune", 1)
        ).collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));
    }

    Boolean useRanged = true;

    @Override
    public void execute() {
        if (useRanged) {
            if (Inventory.contains("Iron arrow")) {
                Inventory.getItems("Iron arrow").first().click();
            }
        }

        if (!Bank.isOpen() && !Inventory.contains("Iron arrow")) {
            Bank.open();
        }
        if (Bank.isOpen()) {
            // deposit loot
            SpriteItem deposit = Inventory.getItems().stream()
                    .filter(item -> !requiredItems().keySet().contains(item.getDefinition().getName())).findFirst().orElse(null);
            getLogger().info("deposit " + deposit);
            if (deposit != null) {
                getLogger().info("Depositing loot");
                Bank.depositInventory();
            }
            //withdraw items
            requiredItems().forEach((item, itemAmount) -> {
                if (Inventory.getQuantity(item) != itemAmount) {
                    getLogger().info("Withdrawing " + item);
                    Bank.withdraw(item, itemAmount);
                    Execution.delayUntil(() -> Inventory.contains(item), () -> false, 50, 500, 1500);
                }
            });
            //@todo not make this a for loop as Bank.depositInventory gets called each loop
            // deposit if items overdrawn
            requiredItems().forEach((item, itemAmount) -> {
                if (Inventory.getItems(item).size() != 0 && Inventory.getQuantity(item) != itemAmount) {
                    getLogger().info("Depositing overdrawn item " + item);
                    Bank.depositInventory();
                    Execution.delayUntil(() -> Inventory.isEmpty(), () -> false, 50, 1000, 2000);
                }
            });
        }
        if (Inventory.isFull()) {
            Bank.close();
        }
    }
}
