package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;


public class InFleshCrawlerArea extends BranchTask {

    private FleshCrawler fleshCrawler;

    public InFleshCrawlerArea(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }


    @Override
    public boolean validate() {
        return fleshCrawler.fleshCrawlerArea.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return fleshCrawler.haveEnoughHp;
    }

    @Override
    public TreeTask failureTask() {
        return fleshCrawler.walkToFleshCrawler;
    }


}
