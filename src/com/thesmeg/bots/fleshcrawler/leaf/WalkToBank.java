package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WalkToBank extends LeafTask {

    private Coordinate destination = new Coordinate(3185, 3436, 0);

    @Override
    public void execute() {
        //Bugs out when spell is selected and tries to teleport
        if (Magic.getSelected() != null) {
            Magic.getSelected().deactivate();
        }
        WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(destination);
        if (webPath != null) {
            webPath.step();
        } else {
            getLogger().warn("Could not generate webPath to bank");
        }
    }
}
