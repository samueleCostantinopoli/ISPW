package com.example.fitnesshelp.graphics_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GraphicsControllerFindAGymNearMe1 extends GraphicsControllerHomePage {

    @FXML
    private AnchorPane GymPane;

    @FXML
    private Hyperlink backHyperlink;

    @FXML
    private ImageView backImageView;

    @FXML
    private ImageView gymImageView;

    @FXML
    private ImageView gymLocationImageView;

    @FXML
    private Label gymNameLabel;

    @FXML
    void clickedOnHyperlinkBack(ActionEvent event) throws IOException {
        stageToSwitch = "findAGymNearMe";
        switchStage(event);
    }

}