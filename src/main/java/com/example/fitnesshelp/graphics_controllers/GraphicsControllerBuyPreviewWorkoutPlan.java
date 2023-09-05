package com.example.fitnesshelp.graphics_controllers;

import com.example.fitnesshelp.application_controllers.ApplicationControllerBuyWorkoutPlan;
import com.example.fitnesshelp.bean.BeanState;
import com.example.fitnesshelp.entities.Exercise;
import com.example.fitnesshelp.entities.WorkoutPlan;
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
import java.util.ArrayList;
import java.util.List;

public class GraphicsControllerBuyPreviewWorkoutPlan extends GraphicsControllerHomePage {

    @FXML
    Label insertPreview;

    @FXML
    Button priceWorkoutPlan2Button;

    ApplicationControllerBuyWorkoutPlan applicationControllerBuyWorkoutPlan = new ApplicationControllerBuyWorkoutPlan(new BeanState(UtilityAccess.getState()));
    List<Exercise> exerciseList = applicationControllerBuyWorkoutPlan.checkExercise();

    public GraphicsControllerBuyPreviewWorkoutPlan() throws IOException {
    }

    public WorkoutPlan currentWorkout;
    public void setIndex(WorkoutPlan workoutPlan) {
        priceWorkoutPlan2Button.setText(String.valueOf(workoutPlan.getPrize()));
        currentWorkout = workoutPlan;

        List<String> exercise = new ArrayList<>();
        int index = 0;
        System.out.println(exerciseList.get(index).getWorkoutPlan());
        if (!exerciseList.isEmpty()) {
            while (!exerciseList.get(index).getWorkoutPlan().getName().isEmpty()) {
                if (workoutPlan.getName().equals(exerciseList.get(index).getWorkoutPlan().getName()))
                    exercise.add(exerciseList.get(index).getExercise() + "\n");
                index++;
            }
            insertPreview.setText(exercise.toString());
        }
    }

    @FXML
    public void clickedOnButtonInfoWorkoutPlan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitnesshelp/buyPreviewWorkoutPlan.fxml"));
        Parent root = loader.load();
        GraphicsControllerBuyPreviewWorkoutPlan previewWorkoutPlanController = loader.getController();
        previewWorkoutPlanController.setIndex(currentWorkout);
        Stage home = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene primary = new Scene(root);
        home.setScene(primary);
        home.show();
    }


    @FXML
    void clickedOnButtonPageOneBuyWorkoutPlan(ActionEvent event) throws IOException {
        stageToSwitch= "/com/example/fitnesshelp/buyWorkoutPlan";
        switchStage(event);
    }


    @FXML
    void clickedOnButtonPriceWorkoutPlan(ActionEvent event) {

    }
}
