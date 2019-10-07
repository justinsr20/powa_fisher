package com.thesmeg.bots.woadleaf;

import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.woadleaf.leaf.Buy;
import com.thesmeg.bots.woadleaf.leaf.EmptyLeaf;
import com.thesmeg.bots.woadleaf.branch.Root;


public class WoadLeaf extends TreeBot {

    private Root root = new Root(this);
    EmptyLeaf emptyLeaf = new EmptyLeaf();
    Buy buy = new Buy(this);


    @Override
    public TreeTask createRootTask() {
        return root;
    }
}
