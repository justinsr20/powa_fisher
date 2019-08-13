package com.thesmeg.bots.fleshcrawler;

import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.ui.FleshCrawlerController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class FleshCrawler extends TreeBot implements EmbeddableUI {

    public FleshCrawler() {
        setEmbeddableUI(this);
    }

    private ObjectProperty<Node> botInterfaceProperty;

    @Override
    public TreeTask createRootTask() {
        return new IsLoggedIn();
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new FleshCrawlerController());

        if (botInterfaceProperty == null) {
            try {
                Node node = loader.load(Resources.getAsStream("com/thesmeg/bots/fleshcrawler/ui/FleshCrawlerOptions.fxml"));
                botInterfaceProperty = new SimpleObjectProperty<>(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return botInterfaceProperty;
    }
}
