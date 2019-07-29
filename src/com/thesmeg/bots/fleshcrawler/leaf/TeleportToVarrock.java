package com.thesmeg.bots.fleshcrawler.leaf;

import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class TeleportToVarrock extends LeafTask {
    @Override
    public void execute() {
        Powers.Magic.VARROCK_TELEPORT.activate();
    }
}
