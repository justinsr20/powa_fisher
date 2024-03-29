package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;
import com.thesmeg.bots.fleshcrawler.playersense.CustomPlayerSense;

public class HaveEnoughHp extends BranchTask {
    private FleshCrawler fleshCrawler;

    public HaveEnoughHp(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public boolean validate() {
        final int minHp = CustomPlayerSense.Key.MIN_HP.getAsInteger();
        int randomHpCheck = Random.nextInt(minHp, 80);

        return Health.getCurrentPercent() > randomHpCheck;
    }

    @Override
    public TreeTask successTask() {
        return fleshCrawler.lootAvailable;
    }

    @Override
    public TreeTask failureTask() {
        return fleshCrawler.haveFood;
    }


}
