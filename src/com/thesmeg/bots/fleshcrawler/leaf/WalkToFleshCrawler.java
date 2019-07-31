package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.Arrays;
import java.util.List;

public class WalkToFleshCrawler extends LeafTask {

    private Coordinate securityStrongholdEntrance = new Coordinate(3081, 3420, 0);
    private Coordinate firstPortal = new Coordinate(1863, 5238, 0);
    private Coordinate firstLadder = new Coordinate(1902, 5222, 0);
    private Coordinate firstRicketyDoor = new Coordinate(2045, 5240, 0); //@todo randomize these to be either door
    private Coordinate secondRicketyDoor = new Coordinate(2044, 5237, 0);
    private Coordinate thirdRicketyDoor = new Coordinate(2037, 5204, 0);
    private Coordinate fourthRicketyDoor = new Coordinate(2036, 5201, 0);
    private Coordinate fifthRicketyDoor = new Coordinate(2046, 5198, 0);
    private Coordinate sixthRicketyDoor = new Coordinate(2045, 5195, 0);

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
            "Report the stream as a scam. Real Jagex streams have a 'verified' mark.",
            "Use the Account Recovery System.",
            "Don't type in my password backwards and report the player.");

    @Override
    public void execute() {
        //@todo need to close warning dialogue box that comes up on first trip
        if (sixthRicketyDoor.isReachable()) {
            if (sixthRicketyDoor.isVisible()) {
                if (!Players.getLocal().isMoving()) {
                    answerSecurityQuestion();
                    if (!ChatDialog.isOpen()) {
                        GameObjects.newQuery().names("Rickety door").results().nearestTo(sixthRicketyDoor).click();
                    }

                }
                return;
            } else {
                webPathToDestination(sixthRicketyDoor, "Security Stronghold sixth Rickety Door");
            }
        } else if (fifthRicketyDoor.isReachable()) {
            if (fifthRicketyDoor.isVisible()) {
                if (!Players.getLocal().isMoving()) {
                    answerSecurityQuestion();
                    if (!ChatDialog.isOpen()) {
                        GameObjects.newQuery().names("Rickety door").results().nearestTo(fifthRicketyDoor).click();
                    }
                }
                return;
            } else {
                webPathToDestination(fifthRicketyDoor, "Security Stronghold fifth Rickety Door");
            }
        } else if (fourthRicketyDoor.isReachable()) {
            if (fourthRicketyDoor.isVisible()) {
                if (!Players.getLocal().isMoving()) {
                    answerSecurityQuestion();
                    if (!ChatDialog.isOpen()) {
                        GameObjects.newQuery().names("Rickety door").results().nearestTo(fourthRicketyDoor).click();
                    }
                }
                return;
            } else {
                webPathToDestination(fourthRicketyDoor, "Security Stronghold fourth Rickety Door");
            }
        } else if (thirdRicketyDoor.isReachable()) {
            if (thirdRicketyDoor.isVisible()) {
                if (!Players.getLocal().isMoving()) {
                    answerSecurityQuestion();
                    if (!ChatDialog.isOpen()) {
                        GameObjects.newQuery().names("Rickety door").results().nearestTo(thirdRicketyDoor).click();
                    }
                }
                return;
            } else {
                webPathToDestination(thirdRicketyDoor, "Security Stronghold third Rickety Door");
            }
        } else if (secondRicketyDoor.isReachable()) {
            if (secondRicketyDoor.isVisible()) {
                if (!Players.getLocal().isMoving()) {
                    answerSecurityQuestion();
                    if (!ChatDialog.isOpen()) {
                        GameObjects.newQuery().names("Rickety door").results().nearestTo(secondRicketyDoor).click();
                    }
                }
                return;
            } else {
                webPathToDestination(secondRicketyDoor, "Security Stronghold second Rickety Door");
            }
        } else if (firstRicketyDoor.isReachable()) {
            if (firstRicketyDoor.isVisible()) {
                if (!Players.getLocal().isMoving()) {
                    answerSecurityQuestion();
                    if (!ChatDialog.isOpen()) {
                        GameObjects.newQuery().names("Rickety door").results().nearestTo(firstRicketyDoor).click();
                    }
                }
                return;
            } else {
                webPathToDestination(firstRicketyDoor, "Security Stronghold first Rickety Door");
            }
        } else if (firstLadder.isReachable()) {
            if (firstLadder.isVisible()) {
                if (!Players.getLocal().isMoving()) {
                    firstLadder.click();
                }
                return;
            } else {
                webPathToDestination(firstLadder, "Security Stronghold second room ladder");
            }
        } else if (firstPortal.isReachable()) {
            if (firstPortal.isVisible()) {
                if (!Players.getLocal().isMoving()) {
                    firstPortal.click();
                }
                return;
            } else {
                webPathToDestination(firstPortal, "Security Stronghold first room portal");
            }
        } else if (securityStrongholdEntrance.isVisible()) {
            if (!Players.getLocal().isMoving()) {
                securityStrongholdEntrance.click();
            }
            return;
        } else {
            webPathToDestination(securityStrongholdEntrance, "Security Stronghold entrance");
        }
    }


    private void answerSecurityQuestion() {
        try {
            if (ChatDialog.isOpen()) {
                if (!ChatDialog.hasTitle("Select an Option")) {
                    ChatDialog.getContinue().select();
                }
                ChatDialog.getOptions().forEach(chatOption -> {
                    System.out.println("chatOption: " + chatOption.getText());
                    securityAnswers.forEach(answer -> {
                        if (chatOption.getText().equals(answer)) {
                            //@todo this bugs sometimes as chat dialogue is not null but throws exception
                            Execution.delay(0, 2000);
                            chatOption.select();
                        }
                    });
                });
            }
        } catch (NullPointerException e) {
            getLogger().warn("Answering security questions threw a null pointer");
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
}
