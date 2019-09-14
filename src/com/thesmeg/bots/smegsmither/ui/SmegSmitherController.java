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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SmegSmitherController implements Initializable {
    private SmegSmither smegSmither;
    @FXML
    private ComboBox<String> comboBoxChooseBar = new ComboBox<>();
    @FXML
    private ComboBox<String> comboBoxChooseLocation = new ComboBox<>();
    @FXML
    private Button btnSetConfig;

    public SmegSmitherController(SmegSmither smegSmither) {
        this.smegSmither = smegSmither;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> smeltingOptions = smegSmither.data.getAllSmeltingOptions();
        comboBoxChooseBar.setItems(FXCollections.observableArrayList(smeltingOptions));
        comboBoxChooseBar.setOnAction(comboBoxChooseBarAction());

        ArrayList<String> locationOptions = smegSmither.data.getAllLocationOptions();
        comboBoxChooseLocation.setItems(FXCollections.observableArrayList(locationOptions));
        comboBoxChooseLocation.setOnAction(comboBoxChooseLocationAction());

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

    private EventHandler<ActionEvent> comboBoxChooseLocationAction() {
        return event -> {
            smegSmither.settings.setLocationToSmelt(comboBoxChooseLocation.getSelectionModel().getSelectedItem());
        };
    }
}
