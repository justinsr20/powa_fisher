package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;

public class WalkToBank extends LeafTask {
    private FleshCrawler fleshCrawler;
    private Coordinate destination = new Coordinate(3183, 3437, 0);

    public WalkToBank(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @Override
    public void execute() {
        //Bugs out when spell is selected and tries to teleport
        if (Magic.getSelected() != null) {
            Magic.getSelected().deactivate();
        }
        WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(destination.randomize(3, 3));
        if (webPath != null) {
            getLogger().info("Using webPath to Varrock Bank");
            webPath.step();
        } else {
            getLogger().warn("Could not generate webPath to bank");
        }
    }
}
