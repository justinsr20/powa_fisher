package com.thesmeg.bots.fleshcrawler;

import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class FleshCrawler extends TreeBot {
    @Override
    public TreeTask createRootTask() {
        return new IsLoggedIn();
    }
}
