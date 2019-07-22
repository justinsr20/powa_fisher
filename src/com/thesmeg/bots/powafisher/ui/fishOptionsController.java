package com.thesmeg.bots.powafisher.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class fishOptionsController implements Initializable {

    @FXML
    private ComboBox<String> selectFish;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectFish.getItems().addAll("Shrimp/Anchovies", "Trout/Salmon", "Pike", "Tuna/Swordfish", "Lobster");
    }
}
