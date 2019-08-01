package com.thesmeg.bots.getanimations;

import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.LoopingBot;

public class GetAnimations extends LoopingBot {
    @Override
    public void onLoop() {
        try {
            getLogger().info("NPC-> Animation " + Players.getLocal().getTarget().getAnimationId());
//            getLogger().info("Player-> Animation " + Players.getLocal().getAnimationId());
        } catch (NullPointerException e) {

        }
    }
}
