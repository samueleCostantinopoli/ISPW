package com.example.fitnesshelp.state;

import com.example.fitnesshelp.entities.Gender;
import com.example.fitnesshelp.entities.Questionnaire;

public class BulkingState implements TdeeState{
    // state bulking
    private double kcalBulk;
    private double mbBulk;
    private double actBulk;

    @Override
    public double calculateTdee(Questionnaire questionnaire){
        if(questionnaire.getGender() == Gender.MALE){
            mbBulk = 88.362 + (13.397*questionnaire.getWeight()) + (4.799*questionnaire.getHeight()) - (5.677* questionnaire.getAge());
        } else {
            mbBulk = 447.593 + (9.247*questionnaire.getWeight()) + (3.098*questionnaire.getHeight()) - (4.330* questionnaire.getAge());
        }

        actBulk = switch (questionnaire.getActivity()){
            case SEDENTARY -> 1.05;
            case LIGHT_EXERCISE -> 1.2;
            case MODERATE_EXERCISE -> 1.35;
            case HEAVY_EXERCISE -> 1.5;
            case ATHLETE -> 1.65;
        };

        kcalBulk = mbBulk*actBulk + mbBulk*0.1 + 500;

        return kcalBulk;
    }
}
