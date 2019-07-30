package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.leaf.WalkToFleshCrawler;


public class InFleshCrawlerArea extends BranchTask {

    private Coordinate bottomLeftFleshCrawler = new Coordinate(2035, 5185, 0);
    private Coordinate topRightFleshCrawler = new Coordinate(2046, 5194, 0);
    private Area fleshCrawlerArea = new Area.Rectangular(bottomLeftFleshCrawler, topRightFleshCrawler);

    @Override
    public boolean validate() {
        return fleshCrawlerArea.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return new HaveEnoughHp();
    }

    @Override
    public TreeTask failureTask() {
        return new WalkToFleshCrawler();
    }


}
