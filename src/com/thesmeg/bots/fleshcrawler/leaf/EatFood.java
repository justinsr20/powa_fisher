package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
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
        //When inventory item accidentally selected
        if (Inventory.getSelectedItem() != null) {
            Inventory.getSelectedItem().click();
        }
        getLogger().info("Low health, attempting to eat");
        SpriteItem foodToClick = Inventory.getItems(fleshCrawler.getFoodToEat()).first();
        final int clicks = CustomPlayerSense.Key.SPAM_CLICK_COUNT.getAsInteger();
        for (int i = 0; i < clicks && foodToClick.isValid(); i++) {
            foodToClick.click();
        }

    }
}
