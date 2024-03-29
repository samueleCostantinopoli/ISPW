package com.example.fitnesshelp.graphics_controllers;

import com.example.fitnesshelp.entities.State;
import com.example.fitnesshelp.utils.UtilityAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class GraphicsControllerCalculateTdee1 extends GraphicsControllerHomePage{

    @FXML
    private Button myTdeeButton;

    @FXML
    private Button startButton;

    @FXML
    private Label errorLoginMessageLabel;

    @FXML
    void clickedOnButtonStart(ActionEvent event) throws  IOException{
        // check if the user is logged in
        if(UtilityAccess.getState() == State.LOGGED_IN) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitnesshelp/calculateTdee2.fxml"));
            Parent root = loader.load();
            GraphicsControllerCalculateTdee2 graphicsControllerCalculateTdee2 = loader.getController();
            graphicsControllerCalculateTdee2.disableButton();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
            startButton.setDisable(true);
        } else {
            errorLoginMessageLabel.setOpacity(1);
        }
    }

    @FXML
    void clickedOnButtonMyTdee(ActionEvent event) throws IOException, SQLException {
        // this method shows all tdee that user has saved
        if(UtilityAccess.getState() == State.LOGGED_IN) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitnesshelp/calculateTdee0.fxml"));
            Parent root = loader.load();
            GraphicsControllerCalculateTdee0 graphicsControllerCalculateTdee0 = loader.getController();
            graphicsControllerCalculateTdee0.initialize();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
            myTdeeButton.setDisable(true);
        } else {
            errorLoginMessageLabel.setOpacity(1);
        }
    }
}
