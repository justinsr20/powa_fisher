package com.thesmeg.bots.fleshcrawler.ui;

import com.thesmeg.bots.fleshcrawler.FleshCrawler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnStart.setOnAction(getbtnStartAction());
    }

    private EventHandler<ActionEvent> getbtnStartAction() {
        return event -> {
            System.out.println("start button");
            this.fleshCrawler.setFoodToEat(foodName.getText());
        };
    }
}
