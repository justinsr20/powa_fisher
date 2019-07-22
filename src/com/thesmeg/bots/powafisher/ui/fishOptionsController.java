package com.thesmeg.bots.powafisher.ui;

import com.thesmeg.bots.powafisher.branch.Fish;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class fishOptionsController implements Initializable {

    @FXML
    private ComboBox<String> selectFish;

    @FXML
    private Button btnStart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectFish.getItems().addAll("Shrimp/Anchovies", "Sardines/Herring", "Trout/Salmon", "Pike",
                "Tuna/Swordfish", "Lobster");
        btnStart.setOnAction((getbtnStartAction()));
    }

    private EventHandler<ActionEvent> getbtnStartAction() {
        return event -> {
            Fish.selectedFish = selectFish.getSelectionModel().getSelectedItem();
        };
    }


}
