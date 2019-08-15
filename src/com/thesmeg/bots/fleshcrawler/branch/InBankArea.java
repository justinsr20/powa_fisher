package com.thesmeg.bots.fleshcrawler.branch;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;

public class InBankArea extends BranchTask {

    private FleshCrawler fleshCrawler;

    public InBankArea(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    private Coordinate bottomLeftBank = new Coordinate(3180, 3433, 0);
    private Coordinate topRightBank = new Coordinate(3185, 3447, 0);
    private Area bankArea = new Area.Rectangular(bottomLeftBank, topRightBank);

    @Override
    public boolean validate() {
        return bankArea.contains(Players.getLocal());
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
