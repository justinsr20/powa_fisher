package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.leaf.Fight;
import com.thesmeg.bots.fleshcrawler.leaf.Loot;

import java.util.Random;

public class LootAvailable extends BranchTask {
//    Loot loot = new Loot();
    private Random random = new Random();

    @Override
    public boolean validate() {
        if (random.nextInt(1) == 0) {
            return true;
        }
        return false;
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
