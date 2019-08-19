package com.thesmeg.bots.fleshcrawler.ui;

import com.thesmeg.bots.fleshcrawler.FleshCrawler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FleshCrawlerController implements Initializable {

    private FleshCrawler fleshCrawler;

    public FleshCrawlerController(FleshCrawler fleshCrawler) {
        this.fleshCrawler = fleshCrawler;
    }

    @FXML
    private TextField foodName;

    @FXML
    private Button btnStart;

    @FXML
    private
    CheckBox useRange;

    @FXML
    private TextField ammunitionName;

    @FXML
    GridPane lootGridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStart.setOnAction(getbtnStartAction());
        useRange.setOnAction(getcheckBoxAction());
    }

    private EventHandler<ActionEvent> getbtnStartAction() {
        return event -> {
            this.fleshCrawler.setFoodToEat(foodName.getText());
            this.fleshCrawler.requiredItems.put(foodName.getText(), 25);
            this.fleshCrawler.setUseRange(useRange.isSelected());
            this.fleshCrawler.setAmmunitionName(ammunitionName.getText());
            this.fleshCrawler.itemsToLoot.clear();

            ObservableList<Node> children = lootGridPane.getChildren();
            for (Node child : children) {
                if (child instanceof CheckBox) {
                    CheckBox checkBoxChild = (CheckBox) child;
                    if (checkBoxChild.isSelected()) {
                        fleshCrawler.itemsToLoot.add(checkBoxChild.getText());
                    }
                }
            }
            if (useRange.isSelected()) {
                fleshCrawler.itemsToLoot.add(ammunitionName.getText());
            }
        };
    }

    private EventHandler<ActionEvent> getcheckBoxAction() {
        return event -> {
            if (!useRange.isSelected()) {
                ammunitionName.setText(null);
            }
            ammunitionName.visibleProperty().bind(useRange.selectedProperty());
        };
    }
}
