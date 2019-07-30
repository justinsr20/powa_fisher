package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.entities.GameObject;
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

    private Coordinate bottomLeftFleshCrawler = new Coordinate(2035, 5185, 0);
    private Coordinate topRightFleshCrawler = new Coordinate(2046, 5194, 0);
    private Area fleshCrawlerArea = new Area.Rectangular(bottomLeftFleshCrawler, topRightFleshCrawler);


    private Coordinate bottomLeftRoomOne = new Coordinate(1857, 5239, 0);
    private Coordinate topRightRoomOne = new Coordinate(1865, 5245, 0);
    private Area roomOneArea = new Area.Rectangular(bottomLeftRoomOne, topRightRoomOne);

    private Coordinate bottomLeftRoomTwo = new Coordinate(1902, 5216, 0);
    private Coordinate topRightRoomTwo = new Coordinate(1915, 5227, 0);
    private Area roomTwoArea = new Area.Rectangular(bottomLeftRoomTwo, topRightRoomTwo);

    private Coordinate bottomLeftRoomThree = new Coordinate(2040, 5240, 0);
    private Coordinate topRightRoomThree = new Coordinate(2046, 5246, 0);
    private Area roomThreeArea = new Area.Rectangular(bottomLeftRoomThree, topRightRoomThree);

    private Coordinate bottomLeftRoomThreeDoorTwo = new Coordinate(2044, 5237, 0);
    private Coordinate topRightRoomThreeDoorTwo = new Coordinate(2045, 5239, 0);
    private Area roomThreeDoorTwoArea = new Area.Rectangular(bottomLeftRoomThreeDoorTwo, topRightRoomThreeDoorTwo);

    private Area roomThreeDoorThreeAreaPtOne = new Area.Rectangular(new Coordinate(2037, 5209, 0), new Coordinate(2046, 5236, 0));
    private Area roomThreeDoorThreeAreaPtTwo = new Area.Rectangular(new Coordinate(2036, 5204, 0), new Coordinate(2043, 5214, 0));

    private Area roomThreeDoorFourArea = new Area.Rectangular(new Coordinate(2036, 5201, 0), new Coordinate(2037, 5203, 0));

    private Area roomThreeDoorFiveAreaPtOne = new Area.Rectangular(new Coordinate(2036, 5199, 0), new Coordinate(2040, 5200, 0));
    private Area roomThreeDoorFiveAreaPtTwo = new Area.Rectangular(new Coordinate(2040, 5198, 0), new Coordinate(2046, 5208, 0));

    private Area roomThreeDoorSixArea = new Area.Rectangular(new Coordinate(2045, 5195, 0), new Coordinate(2046, 5197, 0));


    private Coordinate securityStrongholdEntranceDestination = new Coordinate(3081, 3421, 0);
    private Coordinate fifthRicketyDoorDestination = new Coordinate(2045, 5199, 0);
    private GameObject securityStrongholdEntrance;
    private GameObject portal;
    private GameObject ladder;
    private LocatableEntityQueryResults<GameObject> ricketyDoor;

    private Coordinate firstRicketyDoor = new Coordinate(2045, 5240, 0);
    private Coordinate secondRicketyDoor = new Coordinate(2045, 5237, 0);
    private Coordinate thirdRicketyDoor = new Coordinate(2037, 5204, 0);
    private Coordinate fourthRicketyDoor = new Coordinate(2038, 5201, 0);
    private Coordinate fifthRicketyDoor = new Coordinate(2046, 5197, 0);
    private Coordinate sixthRicketyDoor = new Coordinate(2046, 5195, 0);

    private List<String> securityAnswers = Arrays.asList(
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
            "Report the incident and do not click any links.",
            "Decline the offer and report that player.",
            "Report the stream as a scam. Real Jagex streams have a 'verified' mark.");

    @Override
    public void execute() {

        securityStrongholdEntrance = GameObjects.newQuery().names("Entrance").actions("Climb-down").results().nearest();
        portal = GameObjects.newQuery().names("Portal").actions("Use").results().nearest();
        ladder = GameObjects.newQuery().names("Ladder").actions("Climb-down").results().nearest();
        ricketyDoor = GameObjects.newQuery().names("Rickety door").actions("Open").results();

        getLogger().info("Current position: " + Players.getLocal().getPosition());

        //@todo need to close warning dialogue box that comes up on first trip
        //@todo need to fix this walking its quite buggy
        if (!Players.getLocal().isMoving()) {
            if (roomThreeDoorSixArea.contains(Players.getLocal())) {
                if (ricketyDoor != null) {
                    if (!ChatDialog.isOpen()) {
                        getLogger().info("Attempting to walk through sixth Rickety door");
                        ricketyDoor.nearestTo(sixthRicketyDoor).click();
                    }
                    answerSecurityQuestion();
                    return;
                }
            } else if (roomThreeDoorFiveAreaPtOne.contains(Players.getLocal()) || roomThreeDoorFiveAreaPtTwo.contains(Players.getLocal())) {
                WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(fifthRicketyDoorDestination);
                if (webPath != null) {
                    getLogger().info("Using webpath to fifth door");
                    webPath.step();
                } else {
                    getLogger().warn("Could not generate webPath to fifth Rickety Door");
                }
                if (ricketyDoor != null) {
                    if (!ChatDialog.isOpen()) {
                        getLogger().info("Attempting to walk through fifth Rickety door");
                        ricketyDoor.nearestTo(fifthRicketyDoor).click();
                    }
                    answerSecurityQuestion();
                    return;
                }
            } else if (roomThreeDoorFourArea.contains(Players.getLocal())) {
                if (ricketyDoor != null) {
                    if (!ChatDialog.isOpen()) {
                        getLogger().info("Attempting to walk through fourth Rickety door");
                        ricketyDoor.nearestTo(fourthRicketyDoor).click();
                    }
                    answerSecurityQuestion();
                    return;
                }
            } else if (roomThreeDoorThreeAreaPtOne.contains(Players.getLocal()) || roomThreeDoorThreeAreaPtTwo.contains(Players.getLocal())) {
                WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(thirdRicketyDoor);
                if (webPath != null) {
                    webPath.step();
                } else {
                    getLogger().warn("Could not generate webPath to third Rickety Door");
                }
                if (ricketyDoor != null && ricketyDoor.nearestTo(thirdRicketyDoor).isVisible()) {
                    if (!ChatDialog.isOpen()) {
                        getLogger().info("Attempting to walk through third Rickety door");
                        ricketyDoor.nearestTo(thirdRicketyDoor).click();
                    }
                    answerSecurityQuestion();
                    return;
                }
            } else if (roomThreeDoorTwoArea.contains(Players.getLocal())) {
                if (ricketyDoor != null) {
                    if (!ChatDialog.isOpen()) {
                        getLogger().info("Attempting to walk through second Rickety door");
                        ricketyDoor.nearestTo(secondRicketyDoor).click();
                    }
                    answerSecurityQuestion();
                    return;
                }
            } else if (roomThreeArea.contains(Players.getLocal())) {
                if (ricketyDoor != null) {
                    if (!ChatDialog.isOpen()) {
                        getLogger().info("Attempting to walk through first Rickety door");
                        ricketyDoor.nearestTo(firstRicketyDoor).click();
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
                    getLogger().info("Using webpath to flesh crawlers");
                    webPath.step();
                } else {
                    getLogger().warn("Could not generate webPath to Flesh Crawlers entrance");
                }
            }
        }
    }

    //@todo this bugs sometimes as chat dialogue is not null but throws exception
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
