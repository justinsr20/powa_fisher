package com.thesmeg.bots.smegsmither.lib;

import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.logger.BotLogger;

public class Lib {

    public void webPathToDestination(Coordinate destination, String nameOfDestination, BotLogger botLogger) {
        //@TODO re implement once runemate is fixed
//        WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(destination);
        RegionPath regionPath = RegionPath.buildTo(destination);
        if (regionPath != null) {
            botLogger.info("Using webPath to " + nameOfDestination);
            regionPath.step();
        } else {
            botLogger.warn("Could not generate webPath to " + nameOfDestination);
        }
    }
}
