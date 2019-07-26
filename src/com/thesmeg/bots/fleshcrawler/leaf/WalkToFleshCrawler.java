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

import java.util.Arrays;
import java.util.List;

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

    List<String> securityAnswers = Arrays.asList("Click here to continue",
            "No.",
            "Secure my device and reset my password.",
            "Politely tell them no and then use the 'Report Abuse' button.",
            "No, you should never buy an account.",
            "Don't give them my password.",
            "No, you should never allow anyone to level your account",
            "Only on the Old School RuneScape website.",
            "Inform Jagex by emailing reportphishing@jagex.com.",
            "Nobody.",
            "Me.",
            "The birthday of a famous person or event.",
            "No way! You'll just take my gold for your own! Reported!",
            "Don't share your information and report the player.",
            "Decline offer and report that player.",
            "No way! I'm reporting you to Jagex!",
            "Set up 2 step authentication with my email provider.",
            "Read the text and follow the advice given.",
            "Don't click any links, forward the email to reportphishing@jagex.com.",
            "Talk to any banker.",
            "Don't tell them anything and click the 'Report Abuse' button.");

//    Coordinate fleshcrawler = new Coordinate(2046, 5194, 0);


    @Override
    public void execute() {

        securityStrongholdEntrance = GameObjects.newQuery().names("Entrance").actions("Climb-down").results().nearest();
        portal = GameObjects.newQuery().names("Portal").actions("Use").results().nearest();
        ladder = GameObjects.newQuery().names("Ladder").actions("Climb-down").results().nearest();
        ricketyDoor = GameObjects.newQuery().names("Rickety door").actions("Open").results();

        if (Camera.getPitch() < 0.8) {
            Camera.concurrentlyTurnTo(1.0);
        }


        if (roomThreeDoorTwoArea.contains(Players.getLocal())) {
            if (ricketyDoor != null) {
                if (!ChatDialog.isOpen()) {
                    getLogger().info("Attempting to walk through second Rickety door");
                    ricketyDoor.nearestTo(secondRicetyDoor).click();
                }
                answerSecurityQuestion();
                return;
            }
        } else if (roomThreeArea.contains(Players.getLocal())) {
            if (ricketyDoor != null) {
                if (!ChatDialog.isOpen()) {
                    getLogger().info("Attempting to walk through first Rickety door");
                    ricketyDoor.nearestTo(firstRicetyDoor).click();
                }
                answerSecurityQuestion();
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

    private void answerSecurityQuestion() {
        if (ChatDialog.isOpen()) {
            System.out.println(ChatDialog.getTitle());
            if (!ChatDialog.hasTitle("Select an Option")) {
                ChatDialog.getContinue().select();
            }
            ChatDialog.getOptions().forEach(chatOption -> {
                System.out.println("chatOption: " + chatOption);
                securityAnswers.forEach(answer -> {
                    if (chatOption.getText().equals(answer)) {
                        System.out.println("answer: " + answer);
                        chatOption.select();
                    }
                });
            });

        }
    }
}
