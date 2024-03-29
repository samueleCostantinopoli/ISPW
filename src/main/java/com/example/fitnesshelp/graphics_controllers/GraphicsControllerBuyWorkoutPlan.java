package com.example.fitnesshelp.graphics_controllers;

import com.example.fitnesshelp.application_controllers.ApplicationControllerBuyWorkoutPlan;
import com.example.fitnesshelp.bean.BeanState;
import com.example.fitnesshelp.entities.State;
import com.example.fitnesshelp.entities.WorkoutPlan;
import com.example.fitnesshelp.utils.UtilityAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GraphicsControllerBuyWorkoutPlan extends GraphicsControllerHomePage implements Initializable {
    @FXML
    private Label errorLoginMessageLabel;
    @FXML
    private Button errorLoginMessageButton;
    @FXML
    private VBox anchorPaneContainer;

    static final String COLORBOX = "-fx-background-color: #231717;";

    public GraphicsControllerBuyWorkoutPlan() throws IOException, SQLException {
        // Only for exception
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int numberOfAnchorPanes;
        numberOfAnchorPanes = viewWorkoutPlan();

        List<AnchorPane> anchorPanes = createAnchorPanes(numberOfAnchorPanes, listWorkoutsForSale);
        anchorPaneContainer.getChildren().addAll(anchorPanes);
    }
    BeanState beanState = new BeanState(UtilityAccess.getState());
    ApplicationControllerBuyWorkoutPlan applicationControllerBuyWorkoutPlan = new ApplicationControllerBuyWorkoutPlan(beanState);
    List<WorkoutPlan> listWorkouts = applicationControllerBuyWorkoutPlan.checkWorkoutPlan();
    List<WorkoutPlan> listWorkoutsForSale = new ArrayList<>();
    private int viewWorkoutPlan() {
        // Read value from the file system
        int sizeOfAnchorPane = 0;
        for (WorkoutPlan listWorkout : listWorkouts) {
            if (listWorkout.getPrize() != -10)
                sizeOfAnchorPane++;
            listWorkoutsForSale.add(listWorkout);
        }
        return sizeOfAnchorPane;
    }

    @FXML
    void clickedOnButtonInfoWorkoutPlan(ActionEvent event, int currentWorkout) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitnesshelp/buyInfoWorkoutPlan.fxml"));
        Parent root = loader.load();
        GraphicsControllerBuyInfoWorkoutPlan infoController = loader.getController();
        infoController.getIndexWorkout(listWorkouts.get(currentWorkout));
        Stage home = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene primary = new Scene(root);
        home.setScene(primary);
        home.show();
    }


    @FXML
    void clickedOnButtonPreviewWorkoutPlan(ActionEvent event, int currentWorkout) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitnesshelp/buyPreviewWorkoutPlan.fxml"));
        Parent root = loader.load();
        GraphicsControllerBuyPreviewWorkoutPlan previewWorkoutPlanController = loader.getController();
        previewWorkoutPlanController.getIndexWorkout(listWorkouts.get(currentWorkout));
        Stage home = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene primary = new Scene(root);
        home.setScene(primary);
        home.show();
    }

    @FXML
    void clickedOnButtonPriceWorkoutPlan(ActionEvent event,int currentWorkout) throws IOException {
        if(UtilityAccess.getState() == State.LOGGED_IN) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitnesshelp/buyWorkoutPlanSetPayments.fxml"));
            Parent root = loader.load();
            GraphicsControllerBuyWorkoutPlanSetPayments infoController1 = loader.getController();
            infoController1.checkInformation(listWorkouts.get(currentWorkout));
            Stage home = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene primary = new Scene(root);
            home.setScene(primary);
            home.show();
        } else {
            errorLoginMessageLabel.setOpacity(1);
            errorLoginMessageButton.setOpacity(1);
        }
    }



    @FXML
    void clickedOnBuyWorkoutPlanHyperlink(ActionEvent event) throws IOException {
        setStageToSwitch("/com/example/fitnesshelp/buyWorkoutPlan");
        switchStage(event);
    }

    @FXML
    void clickedOnBuyWorkoutPlanHyperlink1(ActionEvent event) throws IOException {
        setStageToSwitch("/com/example/fitnesshelp/buyWorkoutPlan");
        switchStage(event);
    }


    @FXML
    private List<AnchorPane> createAnchorPanes(int numberOfAnchorPanes, List<WorkoutPlan> workoutPlanList) {
        List<AnchorPane> anchorPanes = new ArrayList<>();
        for (int index = 0; index < numberOfAnchorPanes; index++) {
            AnchorPane anchorPaneView = new AnchorPane();
            anchorPaneView.setPrefHeight(115.0);
            anchorPaneView.setPrefWidth(585.0);
            anchorPaneView.setStyle("-fx-background-color: #dcdcdc;");
            int numberOfWorkout = index;
            Button priceButton = new Button(workoutPlanList.get(index).getPrize() + "€");
            priceButton.setLayoutX(526.0);
            priceButton.setLayoutY(72.0);
            priceButton.setOnAction(event -> {
                try {
                    clickedOnButtonPriceWorkoutPlan(event, numberOfWorkout);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            priceButton.setStyle(COLORBOX);
            priceButton.setTextFill(Color.WHITE);
            priceButton.setFont(new Font(14.0));

            Button previewButton = new Button("preview");
            previewButton.setLayoutX(449.0);
            previewButton.setLayoutY(72.0);
            previewButton.setOnAction(event -> {
                try {
                    clickedOnButtonPreviewWorkoutPlan(event, numberOfWorkout);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            previewButton.setStyle(COLORBOX);
            previewButton.setTextFill(Color.WHITE);
            previewButton.setFont(new Font(14.0));

            Button infoButton = new Button("info");
            infoButton.setLayoutX(385.0);
            infoButton.setLayoutY(72.0);
            infoButton.setOnAction(event -> {
                try {
                    clickedOnButtonInfoWorkoutPlan(event, numberOfWorkout);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            infoButton.setStyle(COLORBOX);
            infoButton.setTextFill(Color.WHITE);
            infoButton.setFont(new Font(14.0));

            AnchorPane dataPane = new AnchorPane();
            dataPane.setPrefHeight(40.0);
            dataPane.setPrefWidth(585.0);
            dataPane.setStyle("-fx-background-color: #464646;");

            Label nameLabel = new Label(workoutPlanList.get(index).getName());
            nameLabel.setLayoutX(6.0);
            nameLabel.setLayoutY(11.0);
            nameLabel.setTextFill(Color.WHITE);
            nameLabel.setFont(new Font(14.0));
            dataPane.getChildren().add(nameLabel);
            anchorPaneView.getChildren().addAll(priceButton, previewButton, infoButton, dataPane);

            anchorPanes.add(anchorPaneView);
        }
        return anchorPanes;
    }
}
