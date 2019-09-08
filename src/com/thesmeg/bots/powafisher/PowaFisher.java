package com.thesmeg.bots.powafisher;

import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.powafisher.ui.fishOptionsController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class PowaFisher extends TreeBot implements EmbeddableUI {

    private ObjectProperty<Node> botInterfaceProperty;

    public PowaFisher() {
        setEmbeddableUI(this);
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new fishOptionsController());

        if (botInterfaceProperty == null) {
            try {
                Node node = loader.load(Resources.getAsStream("com/thesmeg/bots/powafisher/ui/fishOptions.fxml"));
                botInterfaceProperty = new SimpleObjectProperty<>(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return botInterfaceProperty;
    }

    @Override
    public TreeTask createRootTask() {
        return new IsLoggedIn();
    }
}
