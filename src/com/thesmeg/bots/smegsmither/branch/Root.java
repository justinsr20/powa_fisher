package com.thesmeg.bots.smegsmither.branch;


import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.Timer;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

public class Root extends BranchTask {
    private final double cameraPitch = Random.nextDouble(0.4, 1.0);
    private final int cameraYaw = Random.nextInt(0, 360);
    private SmegSmither smegSmither;

    public Root(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public boolean validate() {
        breakHandler();
        Player player = Players.getLocal();
        if (player != null) {
            if (player.isVisible()) {
                if (Camera.getPitch() < 0.1) {
                    Camera.concurrentlyTurnTo(cameraYaw, cameraPitch);
                }
            }
            return smegSmither.settings.getUserConfigSet() && player.isVisible();
        }
        return false;
    }

    private void breakHandler() {
        Timer t = smegSmither.settings.getTimer();
        if (!t.isRunning()) {
            if (RuneScape.isLoggedIn()) {
                if (!RuneScape.logout()) {
                    return;
                }
                GameEvents.Universal.LOGIN_HANDLER.disable();
                smegSmither.settings.setTimer(new Timer(smegSmither.settings.getRandomBreakTime()));
            } else {
                GameEvents.Universal.LOGIN_HANDLER.enable();
                smegSmither.settings.setTimer(new Timer(smegSmither.settings.getRandomPlayTime()));
            }
            smegSmither.settings.startTimer();
            getLogger().info("Timer " + smegSmither.settings.getTimer().getRemainingTimeAsString());
        }
    }

    @Override
    public TreeTask successTask() {
        return smegSmither.haveMaterials;
    }

    @Override
    public TreeTask failureTask() {
        return smegSmither.emptyLeaf;
    }


}
