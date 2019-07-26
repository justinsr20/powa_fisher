package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WalkToFleshCrawler extends LeafTask {


    Coordinate bottomLeftRoomOne = new Coordinate(1857, 5239, 0);
    Coordinate topRightRoomOne = new Coordinate(1865, 5245, 0);
    Area roomOneArea = new Area.Rectangular(bottomLeftRoomOne, topRightRoomOne);

    Coordinate bottomLeftRoomTwo = new Coordinate(1902, 5216, 0);
    Coordinate topRightRoomTwo = new Coordinate(1915, 5227, 0);
    Area roomTwoArea = new Area.Rectangular(bottomLeftRoomTwo, topRightRoomTwo);

    Coordinate bottomLeftRoomThree = new Coordinate(2040, 5240, 0);
    Coordinate topRightRoomThree = new Coordinate(2046, 5246, 0);
    Area roomThreeArea = new Area.Rectangular(bottomLeftRoomThree, topRightRoomThree);

    Coordinate bottomLeftRoomThreeDoorTwo = new Coordinate(2044, 5237, 0);
    Coordinate topRightRoomThreeDoorTwo = new Coordinate(2045, 5239, 0);
    Area roomThreeDoorTwoArea = new Area.Rectangular(bottomLeftRoomThreeDoorTwo, topRightRoomThreeDoorTwo);

    Coordinate securityStrongholdEntranceDestination = new Coordinate(3081, 3421, 0);
    GameObject securityStrongholdEntrance;
    GameObject portal;
    GameObject ladder;
    LocatableEntityQueryResults<GameObject> ricketyDoor;

    Coordinate firstRicetyDoor = new Coordinate(2045, 5240, 0);
    Coordinate secondRicetyDoor = new Coordinate(2045, 5237, 0);

//    Coordinate fleshcrawler = new Coordinate(2046, 5194, 0);


    @Override
    public void execute() {

        if (Camera.getPitch() < 0.8) {
            Camera.concurrentlyTurnTo(1.0);
        }

        securityStrongholdEntrance = GameObjects.newQuery().names("Entrance").actions("Climb-down").results().nearest();
        portal = GameObjects.newQuery().names("Portal").actions("Use").results().nearest();
        ladder = GameObjects.newQuery().names("Ladder").actions("Climb-down").results().nearest();
        ricketyDoor = GameObjects.newQuery().names("Rickety door").actions("Open").results();

        if (roomThreeDoorTwoArea.contains(Players.getLocal())) {
            if (ricketyDoor != null) {
                ricketyDoor.nearestTo(secondRicetyDoor).click();
                getLogger().info("Attempting to walk through second Rickety door");

                ChatDialog.getContinue().select();
                System.out.println(ChatDialog.getText());
                return;
            }
        } else if (roomThreeArea.contains(Players.getLocal())) {
            if (ricketyDoor != null) {
                ricketyDoor.nearestTo(firstRicetyDoor).click();
                getLogger().info("Attempting to walk through first Rickety door");
                return;
            }
        } else if (roomTwoArea.contains(Players.getLocal())) {
            if (ladder != null) {
                ladder.click();
                getLogger().info("Attempting to climb down ladder room two");
                return;
            }
        } else if (roomOneArea.contains(Players.getLocal())) {
            if (portal != null) {
                portal.click();
                getLogger().info("Attempting to enter portal room one");
                return;
            }
        } else if (securityStrongholdEntrance != null && securityStrongholdEntrance.isVisible()) {
            securityStrongholdEntrance.click();
            getLogger().info("Attempting to enter Stronghold of Security");
            return;
        } else {
            WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(securityStrongholdEntranceDestination);
            if (webPath != null) {
                webPath.step();
            } else {
                getLogger().warn("Could not generate webPath to Flesh Crawlers");
            }
        }
    }
}
