package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetSupplies extends LeafTask {

    private static Map<String, Integer> requiredItems() {
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
        if (!Bank.isOpen()) {
            Bank.open();
        }
        //@todo need to cater for when to many of required item withdrawn
        if (Bank.isOpen()) {
            for (SpriteItem item : Inventory.getItems().asList()) {
                getLogger().info(item);
                getLogger().info(requiredItems().keySet().contains(item.getDefinition().getName()));
                if (!requiredItems().keySet().contains(item.getDefinition().getName())) {
                    Bank.depositInventory();
                }
            }
            requiredItems().forEach((item, itemAmount) -> {
                if (Inventory.getQuantity(item) != itemAmount) {
                    Bank.withdraw(item, itemAmount);
                }
            });
        }
        if (Inventory.isFull()) {
            Bank.close();
        }
    }
}
