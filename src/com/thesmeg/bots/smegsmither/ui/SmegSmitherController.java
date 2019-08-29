package com.thesmeg.bots.smegsmither.ui;

import com.thesmeg.bots.smegsmither.SmegSmither;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SmegSmitherController implements Initializable {
    private SmegSmither smegSmither;

    public SmegSmitherController(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @FXML
    private ComboBox<String> comboBoxChooseBar = new ComboBox<>();
    @FXML
    private Button btnSetConfig;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxChooseBar.setItems(FXCollections.observableArrayList("Bronze bar", "Iron bar"));
        comboBoxChooseBar.setOnAction(comboBoxChooseBarAction());
        btnSetConfig.setOnAction(btnSetConfigAction());
    }

    private EventHandler<ActionEvent> btnSetConfigAction() {
        return event -> {
            smegSmither.settings.setUserConfigSet(true);
        };
    }

    private EventHandler<ActionEvent> comboBoxChooseBarAction() {
        return event -> {
            smegSmither.settings.setBarToSmelt(comboBoxChooseBar.getSelectionModel().getSelectedItem());
        };
    }
}