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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    String foodToEat = null;
    public boolean useRange = false;
    private String ammunitionName = null;
    private Coordinate bottomLeftFleshCrawler = new Coordinate(2035, 5185, 0);
    private Coordinate topRightFleshCrawler = new Coordinate(2046, 5194, 0);
    public Area fleshCrawlerArea = new Area.Rectangular(bottomLeftFleshCrawler, topRightFleshCrawler);
    private Coordinate bottomLeftBank = new Coordinate(3180, 3433, 0);
    private Coordinate topRightBank = new Coordinate(3185, 3447, 0);
    public Area bankArea = new Area.Rectangular(bottomLeftBank, topRightBank);
    public ArrayList<String> itemsToLoot = new ArrayList<>();
    public Map<String, Integer> requiredItems = new HashMap<String, Integer>() {{
        put("Fire rune", 1);
        put("Air rune", 3);
        put("Law rune", 1);
    }};

    private ObjectProperty<Node> botInterfaceProperty;

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

    public void setFoodToEat(String foodToEat) {
        this.foodToEat = foodToEat;
    }

    public String getFoodToEat() {
        return foodToEat;
    }

    public void setUseRange(Boolean useRange) {
        this.useRange = useRange;
    }

    public void setAmmunitionName(String ammunitionName) {
        this.ammunitionName = ammunitionName;
    }

    public String getAmmunitionName() {
        return ammunitionName;
    }
}
