package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;
import com.thesmeg.bots.fleshcrawler.playersense.CustomPlayerSense;

public class EatFood extends LeafTask {
    private FleshCrawler fleshCrawler;

    public EatFood(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public void execute() {
        Player player = Players.getLocal();
        SpriteItem foodToClick = Inventory.getItems(fleshCrawler.getFoodToEat()).first();
        final int clicks = CustomPlayerSense.Key.SPAM_CLICK_COUNT.getAsInteger();
        String ammunitionName = fleshCrawler.getAmmunitionName();

        if (fleshCrawler.useRange) {
            if (Equipment.getItems(ammunitionName).isEmpty()) {
                if (Inventory.contains(ammunitionName)) {
                    SpriteItem ammunitionItem = Inventory.getItems(ammunitionName).first();
                    if (ammunitionItem != null) {
                        ammunitionItem.click();
                    }
                } else {
                    Environment.getBot().stop("Ran out of arrows");
                }
            }
        }

        //When inventory item accidentally selected
        if (Inventory.getSelectedItem() != null) {
            Inventory.getSelectedItem().click();
        }
        if (foodToClick != null) {
            if (player != null && player.getTarget() == null) {
                getLogger().info("Low health, attempting to eat");
                for (int i = 0; i < clicks && foodToClick.isValid(); i++) {
                    foodToClick.click();
                }
            } else if (Health.getCurrentPercent() < 35) {
                getLogger().info("Close to dying, attempting to eat!");
                for (int i = 0; i < clicks && foodToClick.isValid(); i++) {
                    foodToClick.click();
                }
            }
        }
    }
}
