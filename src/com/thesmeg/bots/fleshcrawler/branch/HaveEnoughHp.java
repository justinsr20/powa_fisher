package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.Random;

public class HaveEnoughHp extends BranchTask {

    private Random random = new Random();

    @Override
    public boolean validate() {

        int randomHpCheck = random.nextInt(40) + 30;
        if (Health.getCurrentPercent() > randomHpCheck) {
            return true;
        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        return new LootAvailable();
    }

    @Override
    public TreeTask failureTask() {
        return new HaveFood();
    }


}
