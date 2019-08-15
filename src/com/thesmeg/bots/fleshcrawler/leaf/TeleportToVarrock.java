package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;

public class TeleportToVarrock extends LeafTask {
    private FleshCrawler fleshCrawler;

    public TeleportToVarrock(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public void execute() {
        getLogger().info("Ran out of food, using Varrock Teleport");
        Powers.Magic.VARROCK_TELEPORT.activate();
    }
}
