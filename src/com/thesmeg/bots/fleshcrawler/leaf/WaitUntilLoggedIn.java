package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;

public class WaitUntilLoggedIn extends LeafTask {

    private FleshCrawler fleshCrawler;

    public WaitUntilLoggedIn(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public void execute() {

    }
}
