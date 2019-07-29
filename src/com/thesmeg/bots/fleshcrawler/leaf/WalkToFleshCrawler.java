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

    Coordinate bottomLeftFleshCrawler = new Coordinate(2035, 5185, 0);
    Coordinate topRightFleshCrawler = new Coordinate(2046, 5194, 0);
    Area fleshCrawlerArea = new Area.Rectangular(bottomLeftFleshCrawler, topRightFleshCrawler);


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

    Area roomThreeDoorThreeAreaPtOne = new Area.Rectangular(new Coordinate(2037, 5209, 0), new Coordinate(2046, 5236, 0));
    Area roomThreeDoorThreeAreaPtTwo = new Area.Rectangular(new Coordinate(2036, 5204, 0), new Coordinate(2043, 5214, 0));

    Area roomThreeDoorFourArea = new Area.Rectangular(new Coordinate(2036, 5201, 0), new Coordinate(2037, 5203, 0));

    Area roomThreeDoorFiveAreaPtOne = new Area.Rectangular(new Coordinate(2036, 5199, 0), new Coordinate(2040, 5200, 0));
    Area roomThreeDoorFiveAreaPtTwo = new Area.Rectangular(new Coordinate(2040, 5200, 0), new Coordinate(2046, 5208, 0));

    Area roomThreeDoorSixArea = new Area.Rectangular(new Coordinate(2045, 5195, 0), new Coordinate(2046, 5197, 0));


    Coordinate securityStrongholdEntranceDestination = new Coordinate(3081, 3421, 0);
    GameObject securityStrongholdEntrance;
    GameObject portal;
    GameObject ladder;
    LocatableEntityQueryResults<GameObject> ricketyDoor;

    //@todo fix speling
    Coordinate firstRicetyDoor = new Coordinate(2045, 5240, 0);
    Coordinate secondRicetyDoor = new Coordinate(2045, 5237, 0);
    Coordinate thirdRicetyDoor = new Coordinate(2037, 5204, 0);
    Coordinate fourthRicetyDoor = new Coordinate(2038, 5201, 0);
    Coordinate fifthRicetyDoor = new Coordinate(2046, 5197, 0);
    Coordinate sixthRicetyDoor = new Coordinate(2046, 5195, 0);

    List<String> securityAnswers = Arrays.asList(
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
            "Don't tell them anything and click the 'Report Abuse' button.",
            "Do not visit the website and report the player who messaged you.",
            "Virus scan my device then change my password.",
            "Using the Account Recovery System.",
            "Don't give them the information and send an 'Abuse report'.",
            "Authenticator and two-step login on my registered email.",
            "Through account settings on oldschool.runescape.com.",
            "Don't give out your password to anyone. Not even close friends.",
            "No, you should never allow anyone to level your account.",
            "Report the incident and do not click any links.");

    @Override
    public void execute() {

        securityStrongholdEntrance = GameObjects.newQuery().names("Entrance").actions("Climb-down").results().nearest();
        portal = GameObjects.newQuery().names("Portal").actions("Use").results().nearest();
        ladder = GameObjects.newQuery().names("Ladder").actions("Climb-down").results().nearest();
        ricketyDoor = GameObjects.newQuery().names("Rickety door").actions("Open").results();

        if (roomThreeDoorSixArea.contains(Players.getLocal())) {
            if (ricketyDoor != null) {
                if (!ChatDialog.isOpen()) {
                    getLogger().info("Attempting to walk through sixth Rickety door");
                    ricketyDoor.nearestTo(sixthRicetyDoor).click();
                }
                answerSecurityQuestion();
                return;
            }
        } else if (roomThreeDoorFiveAreaPtOne.contains(Players.getLocal()) || roomThreeDoorFiveAreaPtTwo.contains(Players.getLocal())) {
            WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(fifthRicetyDoor);
            if (webPath != null) {
                webPath.step();
            } else {
                getLogger().warn("Could not generate webPath to fifth Rickety Door");
            }
            if (ricketyDoor != null) {
                if (!ChatDialog.isOpen()) {
                    getLogger().info("Attempting to walk through fifth Rickety door");
                    ricketyDoor.nearestTo(fifthRicetyDoor).click();
                }
                answerSecurityQuestion();
                return;
            }
        } else if (roomThreeDoorFourArea.contains(Players.getLocal())) {
            if (ricketyDoor != null) {
                if (!ChatDialog.isOpen()) {
                    getLogger().info("Attempting to walk through fourth Rickety door");
                    ricketyDoor.nearestTo(fourthRicetyDoor).click();
                }
                answerSecurityQuestion();
                return;
            }
        } else if (roomThreeDoorThreeAreaPtOne.contains(Players.getLocal()) || roomThreeDoorThreeAreaPtTwo.contains(Players.getLocal())) {
            WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(thirdRicetyDoor);
            if (webPath != null) {
                webPath.step();
            } else {
                getLogger().warn("Could not generate webPath to third Rickety Door");
            }
            if (ricketyDoor != null && ricketyDoor.nearestTo(thirdRicetyDoor).isVisible()) {
                if (!ChatDialog.isOpen()) {
                    getLogger().info("Attempting to walk through third Rickety door");
                    ricketyDoor.nearestTo(thirdRicetyDoor).click();
                }
                answerSecurityQuestion();
                return;
            }
        } else if (roomThreeDoorTwoArea.contains(Players.getLocal())) {
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
                getLogger().info("Attempting to climb down ladder room two");
                ladder.click();
                return;
            }
        } else if (roomOneArea.contains(Players.getLocal())) {
            if (portal != null) {
                getLogger().info("Attempting to enter portal room one");
                portal.click();
                return;
            }
        } else if (securityStrongholdEntrance != null && securityStrongholdEntrance.isVisible()) {
            getLogger().info("Attempting to enter Stronghold of Security");
            securityStrongholdEntrance.click();
            return;
        } else if (!fleshCrawlerArea.contains(Players.getLocal())) {
            WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(securityStrongholdEntranceDestination);
            if (webPath != null) {
                webPath.step();
            } else {
                getLogger().warn("Could not generate webPath to Flesh Crawlers entrance");
            }
        }
    }

    private void answerSecurityQuestion() {
        if (ChatDialog.isOpen()) {
            if (!ChatDialog.hasTitle("Select an Option")) {
                ChatDialog.getContinue().select();
            }
            ChatDialog.getOptions().forEach(chatOption -> {
                System.out.println("chatOption: " + chatOption.getText());
                securityAnswers.forEach(answer -> {
                    if (chatOption.getText().equals(answer)) {
                        chatOption.select();
                    }
                });
            });

        }
    }
}
