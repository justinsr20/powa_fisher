package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.leaf.Fight;
import com.thesmeg.bots.fleshcrawler.leaf.Loot;

public class LootAvailable extends BranchTask {

    @Override
    public boolean validate() {
        return !GroundItems.getLoadedWithin(Area.circular(Players.getLocal().getPosition(), 2)).isEmpty();
    }

    @Override
    public TreeTask successTask() {
        return new Loot();
    }

    @Override
    public TreeTask failureTask() {
        return new Fight();
    }
}
