package com.thesmeg.bots.smegsmither.leaf;

import com.runemate.game.api.script.framework.tree.LeafTask;
import com.thesmeg.bots.smegsmither.SmegSmither;

public class Smelt extends LeafTask {
    SmegSmither smegSmither;

    public Smelt(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public void execute() {
        System.out.println("implement smither");
    }
}
