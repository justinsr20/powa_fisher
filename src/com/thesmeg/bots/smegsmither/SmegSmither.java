package com.thesmeg.bots.smegsmither;

import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.smegsmither.branch.HaveMaterials;
import com.thesmeg.bots.smegsmither.branch.Root;
import com.thesmeg.bots.smegsmither.config.Data;
import com.thesmeg.bots.smegsmither.config.Settings;
import com.thesmeg.bots.smegsmither.leaf.EmptyLeaf;
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
    public EmptyLeaf emptyLeaf = new EmptyLeaf();
    public Settings settings = new Settings();
    public Data data = new Data();

    public SmegSmither() {
        setEmbeddableUI(this);
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
