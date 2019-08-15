package com.thesmeg.bots.fleshcrawler;

import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.thesmeg.bots.fleshcrawler.branch.*;
import com.thesmeg.bots.fleshcrawler.leaf.*;
import com.thesmeg.bots.fleshcrawler.ui.FleshCrawlerController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FleshCrawler extends TreeBot implements EmbeddableUI {

    public HaveEnoughHp haveEnoughHp = new HaveEnoughHp(this);
    public HaveFood haveFood = new HaveFood(this);
    HaveSupplies haveSupplies = new HaveSupplies(this);
    public InBankArea inBankArea = new InBankArea(this);
    public InFleshCrawlerArea inFleshCrawlerArea = new InFleshCrawlerArea(this);
    public LootAvailable lootAvailable = new LootAvailable(this);

    public EatFood eatFood = new EatFood(this);
    public Fight fight = new Fight(this);
    public GetSupplies getSupplies = new GetSupplies(this);
    public Loot loot = new Loot(this);
    public TeleportToVarrock teleportToVarrock = new TeleportToVarrock(this);
    WaitUntilLoggedIn waitUntilLoggedIn = new WaitUntilLoggedIn(this);
    public WalkToBank walkToBank = new WalkToBank(this);
    public WalkToFleshCrawler walkToFleshCrawler = new WalkToFleshCrawler(this);


    private ObjectProperty<Node> botInterfaceProperty;

    public String foodToEat = null;
    private Coordinate bottomLeftFleshCrawler = new Coordinate(2035, 5185, 0);
    private Coordinate topRightFleshCrawler = new Coordinate(2046, 5194, 0);
    public Area fleshCrawlerArea = new Area.Rectangular(bottomLeftFleshCrawler, topRightFleshCrawler);

    public List<String> itemsToLoot = Arrays.asList("Shield left half", "Dragon spear", "Rune spear", "Tooth half of key",
            "Loop half of key", "Uncut diamond", "Uncut ruby", "Rune javelin", "Uncut emerald",
            "Nature talisman", "Uncut sapphire", "Iron ore", "Coins", "Body rune", "Iron arrow");

    public FleshCrawler() {
        setEmbeddableUI(this);
    }

    @Override
    public TreeTask createRootTask() {
        return new IsLoggedIn(this);
    }

    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new FleshCrawlerController(this));

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

    public void setFoodToEat(String foodName) {
        foodToEat = foodName;
    }
}
