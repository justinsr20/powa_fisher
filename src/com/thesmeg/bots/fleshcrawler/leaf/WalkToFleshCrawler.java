package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceContainer;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceContainers;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.fleshcrawler.FleshCrawler;
import com.thesmeg.bots.fleshcrawler.playersense.CustomPlayerSense;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WalkToFleshCrawler extends LeafTask {

    private FleshCrawler fleshCrawler;

    public WalkToFleshCrawler(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    private Coordinate securityStrongholdEntrance = new Coordinate(3081, 3420, 0);
    private Coordinate firstPortal = new Coordinate(1863, 5238, 0);
    private Coordinate firstLadder = new Coordinate(1902, 5222, 0);
    private Coordinate firstRicketyDoor = new Coordinate(2045, 5240, 0);
    private Coordinate secondRicketyDoor = new Coordinate(2044, 5237, 0);
    private Coordinate thirdRicketyDoor = new Coordinate(2037, 5204, 0);
    private Coordinate fourthRicketyDoor = new Coordinate(2036, 5201, 0);
    private Coordinate fifthRicketyDoor = new Coordinate(2046, 5198, 0);
    private Coordinate sixthRicketyDoor = new Coordinate(2045, 5195, 0);

    private Map<Coordinate, List<String>> pathToFleshCrawlers = new HashMap<Coordinate, List<String>>() {
        {
            put(sixthRicketyDoor, Arrays.asList("Rickety door", "Open"));
            put(fifthRicketyDoor, Arrays.asList("Rickety door", "Open"));
            put(fourthRicketyDoor, Arrays.asList("Rickety door", "Open"));
            put(thirdRicketyDoor, Arrays.asList("Rickety door", "Open"));
            put(secondRicketyDoor, Arrays.asList("Rickety door", "Open"));
            put(firstRicketyDoor, Arrays.asList("Rickety door", "Open"));
            put(firstLadder, Arrays.asList("Ladder", "Climb-down"));
            put(firstPortal, Arrays.asList("Portal", "Use"));
            put(securityStrongholdEntrance, Arrays.asList("Entrance", "Climb-down"));
        }
    };

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
            "Through account config on oldschool.runescape.com.",
            "Don't give out your password to anyone. Not even close friends.",
            "No, you should never allow anyone to level your account.",
            "Report the incident and do not click any links.",
            "Decline the offer and report that player.",
            "Report the stream as a scam. Real Jagex streams have a 'verified' mark.",
            "Use the Account Recovery System.",
            "Don't type in my password backwards and report the player.",
            "Report the player for phishing.",
            "Through account settings on oldschool.runescape.com.");

    private final int executionDelayMin = CustomPlayerSense.Key.EXECUTION_DELAY_MIN.getAsInteger();
    private final int executionDelayMax = CustomPlayerSense.Key.EXECUTION_DELAY_MAX.getAsInteger();

    @Override
    public void execute() {
        warningInterface();
        boolean walkingToEntrance = true;
        for (Map.Entry<Coordinate, List<String>> destination : pathToFleshCrawlers.entrySet()) {
            if (destination.getKey().isReachable()) {
                if (destination.getKey().isVisible()) {
                    answerSecurityQuestion();
                    if (!Players.getLocal().isMoving() && Players.getLocal().getAnimationId() != 4283 && Players.getLocal().getAnimationId() != 4282 && !ChatDialog.isOpen()) {
                        GameObjects.newQuery().names(destination.getValue().get(0)).results().nearestTo(destination.getKey().randomize(1, 0)).interact(destination.getValue().get(1));
                        Execution.delay(executionDelayMin, executionDelayMax);
                    }
                } else if (!destination.getKey().isVisible()) {
                    webPathToDestination(destination.getKey(), destination.getValue().get(0));
                }
                walkingToEntrance = false;
                break;
            }
        }
        if (walkingToEntrance) {
            webPathToDestination(securityStrongholdEntrance, "Security Stronghold entrance");
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
                        Execution.delay(executionDelayMin, executionDelayMax);
                        chatOption.select();
                    }
                });
            });
        }
    }

    private void webPathToDestination(Coordinate destination, String nameOfDestination) {
        WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(destination);
        if (webPath != null) {
            getLogger().info("Using webPath to " + nameOfDestination);
            webPath.step();
        } else {
            getLogger().warn("Could not generate webPath to " + nameOfDestination);
        }
    }

    private void warningInterface() {
        InterfaceContainer warningInterface = InterfaceContainers.getAt(579);
        if (warningInterface != null) {
            if (warningInterface.getComponent(20).isVisible()) {
                getLogger().info(warningInterface.getComponent(20).click());
                Execution.delay(executionDelayMin, executionDelayMax);
            }
            if (warningInterface.getComponent(17).isVisible()) {
                warningInterface.getComponent(17).click();
            }
        }
    }
}
