package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;

public class InBankArea extends BranchTask {

    private FleshCrawler fleshCrawler;

    public InBankArea(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public boolean validate() {
        Player player = Players.getLocal();
        if (player != null) {
            return fleshCrawler.bankArea.contains(player);
        }
        return false;
    }

    @Override
    public TreeTask successTask() {
        return fleshCrawler.getSupplies;
    }

    @Override
    public TreeTask failureTask() {
        return fleshCrawler.walkToBank;
    }
}
