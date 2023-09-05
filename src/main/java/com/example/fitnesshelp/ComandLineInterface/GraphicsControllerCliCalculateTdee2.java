package com.example.fitnesshelp.ComandLineInterface;

import com.example.fitnesshelp.application_controllers.ApplicationControllerCalculateTdee;
import com.example.fitnesshelp.bean.*;
import com.example.fitnesshelp.entities.Activity;
import com.example.fitnesshelp.entities.Gender;
import com.example.fitnesshelp.entities.Questionnaire;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GraphicsControllerCliCalculateTdee2 {

    private BeanGender beanGender;
    private BeanHeight beanHeight;
    private BeanAge beanAge;
    private BeanWeight beanWeight;
    private BeanActivity beanActivity;
    private BeanTarget beanTarget;

    public void showQuestionnaire() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // gender choice
        String gender = "";
        Gender genderSelected = null;
        while (!gender.equals("male") && !gender.equals("female")) {
            System.out.println("Select your gender: \n");
            System.out.println("1: Male\n");
            System.out.println("2: Female\n");
            String choice = bufferedReader.readLine();
            switch (choice) {
                case "1" -> {
                    genderSelected = Gender.MALE;
                    beanGender = new BeanGender(genderSelected);
                    gender = "male";
                }
                case "2" -> {
                    genderSelected = Gender.FEMALE;
                    beanGender = new BeanGender(genderSelected);
                    gender = "female";
                }
                default -> System.out.println("Invalid choice. Please select 1 for Male or 2 for Female.\n");
            }
        }

        // age
        int age = 0;
        while (age <= 0) {
            System.out.println("Enter your age: ");
            String ageInput = bufferedReader.readLine();
            beanAge = new BeanAge(Integer.parseInt(ageInput));
            String ageRange = beanAge.verifyAge(Integer.parseInt(ageInput));
            if (isNumeric(ageInput) && ageRange == null) {
                age = Integer.parseInt(ageInput);
            } else {
                System.out.println("Invalid input. Please enter a valid age.");
            }
        }

        // weight
        float weight = 0.0f;
        while (weight <= 0) {
            System.out.println("Enter your weight (in kg): ");
            String weightInput = bufferedReader.readLine();
            beanWeight = new BeanWeight(Float.parseFloat(weightInput));
            String weightRange = beanWeight.verifyWeight(Float.parseFloat(weightInput));
            if (isNumeric(weightInput) && weightRange == null) {
                weight = Float.parseFloat(weightInput);
            } else {
                System.out.println("Invalid input. Please enter a valid weight.");
            }
        }

        // height
        float height = 0.0f;
        while (height <= 0) {
            System.out.println("Enter your height (in cm): ");
            String heightInput = bufferedReader.readLine();
            beanHeight = new BeanHeight(Float.parseFloat(heightInput));
            String heightRange = beanHeight.verifyHeight(Float.parseFloat(heightInput));
            if (isNumeric(heightInput) && heightRange == null) {
                height = Float.parseFloat(heightInput);
            } else {
                System.out.println("Invalid input. Please enter a valid height.");
            }
        }

        // activity selection
        int activityLevel = 0;
        Activity activitySelected = null;
        while (activityLevel < 1 || activityLevel > 5) {
            System.out.println("Select your activity level: ");
            System.out.println("1: Sedentary (office job)");
            System.out.println("2: Light exercise (1-2 days/week)");
            System.out.println("3: Moderate exercise (3-5 days/week)");
            System.out.println("4: Heavy exercise (6-7 days/week)");
            System.out.println("5: Athlete (2 x day)");
            String choice = bufferedReader.readLine();
            try {
                activitySelected = switch (Integer.parseInt(choice)){
                    case 1 -> Activity.SEDENTARY;
                    case 2 -> Activity.LIGHT_EXERCISE;
                    case 3 -> Activity.MODERATE_EXERCISE;
                    case 4 -> Activity.HEAVY_EXERCISE;
                    case 5 -> Activity.ATHLETE;
                    default -> null;
                };
                beanActivity = new BeanActivity(activitySelected);
                activityLevel = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please select a number from 1 to 5.");
            }
        }

        // target choice
        String target = "";
        while (!target.equals("maintenance") && !target.equals("cutting") && !target.equals("bulking")) {
            System.out.println("Select your target: ");
            System.out.println("1: Maintenance (maintain current weight)");
            System.out.println("2: Cutting (lose weight)");
            System.out.println("3: Bulking (gain weight/muscle)");
            String choice = bufferedReader.readLine();
            switch (choice) {
                case "1" -> {
                    beanTarget = new BeanTarget("maintenance");
                    target = "maintenance";
                }
                case "2" -> {
                    beanTarget = new BeanTarget("cutting");
                    target = "cutting";
                }
                case "3" -> {
                    beanTarget = new BeanTarget("bulking");
                    target = "bulking";
                }
                default ->
                        System.out.println("Invalid choice. Please select 1 for Maintenance, 2 for Cutting, or 3 for Bulking.");
            }
        }

        // at this point of methods I can call application controller to calculate tdee
        ApplicationControllerCalculateTdee applicationControllerCalculateTdee = new ApplicationControllerCalculateTdee(beanAge, beanHeight, beanWeight, beanGender, beanActivity, beanTarget);
        // now I can create the questionnaire with my answers to be sent to the tdee calculator via the application controller
        Questionnaire questionnaire = new Questionnaire(genderSelected, age, weight, height, activitySelected);
        double kcal = applicationControllerCalculateTdee.calculateTdee(questionnaire, target);
        // finally call tha graphics controller that will show the result
        GraphicsControllerCliCalculateTdee3 graphicsControllerCliCalculateTdee3 = new GraphicsControllerCliCalculateTdee3();
        graphicsControllerCliCalculateTdee3.showResult(kcal);
    }

    private boolean isNumeric(String str) {
        // this method verify if the age, weight and height are numeric
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}