package com.example.fitnesshelp.controllerGrafici;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FindAGymNearMe extends HomePage{

    @FXML
    private AnchorPane Gym1Pane;

    @FXML
    private AnchorPane Gym2Pane;

    @FXML
    private AnchorPane GymPane;

    @FXML
    private Hyperlink accountHyperlink;

    @FXML
    private Label addressGym1;

    @FXML
    private Label addressGym2;

    @FXML
    private ImageView mapsImageView;

    @FXML
    private Hyperlink myTrainingDiaryHyperlink;

    @FXML
    private Hyperlink myWorkoutPlanHyperlink;

    @FXML
    private Label nameGym1;

    @FXML
    private Label nameGym2;

    @FXML
    private Label nameSurname;

    @FXML
    private Hyperlink personalTrainerHyperlink;

    @FXML
    private ImageView profileImage;

    @FXML
    private Button showAllGymButton;

    @FXML
    private Label title;

    @FXML
    private Button viewInfoGym1Button;

    @FXML
    private Button viewInfoGym2Button;

    @FXML
    void clickedOnButtonShowAllGym(ActionEvent event) {

    }

    @FXML
    void clickedOnButtonViewInfoGym1(ActionEvent event) {

    }

    @FXML
    void clickedOnButtonViewInfoGym2(ActionEvent event) {

    }
    // TODO
    public void clickedOnHyperlinkBack(ActionEvent event) {
    }

    public void clickedOnTdeeCalculatorHyperlink(ActionEvent event) throws IOException{
        stageToSwitch = "/com/example/fitnesshelp/calculateTdee1";
        switchStage(event);
    }
}
