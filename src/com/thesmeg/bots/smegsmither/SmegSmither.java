package com.thesmeg.bots.smegsmither;

import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.smegsmither.branch.AtBank;
import com.thesmeg.bots.smegsmither.branch.AtFurnace;
import com.thesmeg.bots.smegsmither.branch.HaveMaterials;
import com.thesmeg.bots.smegsmither.branch.Root;
import com.thesmeg.bots.smegsmither.config.Data;
import com.thesmeg.bots.smegsmither.config.Settings;
import com.thesmeg.bots.smegsmither.leaf.*;
import com.thesmeg.bots.smegsmither.lib.Lib;
import com.thesmeg.bots.smegsmither.playersense.CustomPlayerSense;
import com.thesmeg.bots.smegsmither.ui.SmegSmitherController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class SmegSmither extends TreeBot implements EmbeddableUI {
    private ObjectProperty<Node> botInterfaceProperty;

    private SmegSmitherController smegSmitherController = new SmegSmitherController(this);
    private Root root = new Root(this);
    public HaveMaterials haveMaterials = new HaveMaterials(this);
    public AtFurnace atFurnace = new AtFurnace(this);
    public AtBank atBank = new AtBank(this);
    public RunToFurnace runToFurnace = new RunToFurnace(this);
    public RunToBank runToBank = new RunToBank(this);
    public Smelt smelt = new Smelt(this);
    public WithdrawMaterials withdrawMaterials = new WithdrawMaterials(this);
    public EmptyLeaf emptyLeaf = new EmptyLeaf();
    public Settings settings = new Settings();
    public Data data = new Data();
    public Lib lib = new Lib();

    public SmegSmither() {
        setEmbeddableUI(this);
    }

    @Override
    public void onStart(String... strings) {
        CustomPlayerSense.initializeKeys();
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(smegSmitherController);
        if (botInterfaceProperty == null) {
            try {
                Node node = fxmlLoader.load(Resources.getAsStream("com/thesmeg/bots/smegsmither/ui/smethSmitherOptions.fxml"));
                botInterfaceProperty = new SimpleObjectProperty<>(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return botInterfaceProperty;
    }

    @Override
    public TreeTask createRootTask() {
        return root;
    }
}
