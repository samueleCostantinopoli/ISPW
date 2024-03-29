package com.example.fitnesshelp.cli_graphics_controller;

import com.example.fitnesshelp.application_controllers.ApplicationControllerCalculateTdee;
import com.example.fitnesshelp.bean.*;
import com.example.fitnesshelp.entities.Activity;
import com.example.fitnesshelp.entities.Gender;
import com.example.fitnesshelp.exception.TdeeRemoveException;
import com.example.fitnesshelp.utils.Printer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class GraphicsControllerCliCalculateTdee2 {

    private BeanGender beanGender;
    private BeanHeight beanHeight;
    private BeanAge beanAge;
    private BeanWeight beanWeight;
    private BeanActivity beanActivity;
    private BeanTarget beanTarget;

    public void showQuestionnaire() throws IOException, SQLException, TdeeRemoveException {
        Printer.print("-------------------QUESTIONNAIRE PAGE--------------------\n");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String mainTarget = "maintenance";
        String cutTarget = "cutting";
        String bulkTarget = "bulking";

        // these methods take the answer of the user
        selectGender(bufferedReader);

        selectAge(bufferedReader);

        selectWeight(bufferedReader);

        selectHeight(bufferedReader);

        selectActivityLevel(bufferedReader);

        selectTarget(bufferedReader, mainTarget, cutTarget, bulkTarget);

        // at this point of methods I can call application controller to calculate tdee
        ApplicationControllerCalculateTdee applicationControllerCalculateTdee = new ApplicationControllerCalculateTdee(beanAge, beanHeight, beanWeight, beanGender, beanActivity, beanTarget);
        // now I can create the questionnaire with my answers to be sent to the tdee calculator via the application controller
        double kcal = applicationControllerCalculateTdee.calculateTdee();
        // finally call tha graphics controller that will show the result
        GraphicsControllerCliCalculateTdee3 graphicsControllerCliCalculateTdee3 = new GraphicsControllerCliCalculateTdee3();
        graphicsControllerCliCalculateTdee3.showResult(kcal, beanTarget.getTarget());
    }

    private void selectGender(BufferedReader bufferedReader) throws IOException {
        String gender = "";
        Gender genderSelected;
        while (!gender.equals("male") && !gender.equals("female")) {
            Printer.print("Select your gender: \n");
            Printer.print("1: Male\n");
            Printer.print("2: Female\n");
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
                default -> Printer.print("Invalid choice. Please select 1 for Male or 2 for Female.\n");
            }
        }
    }

    private void selectAge(BufferedReader bufferedReader) throws IOException {
        int age = 0;
        while (age <= 0) {
            Printer.print("Enter your age: ");
            String ageInput = bufferedReader.readLine();
            if (!isNumeric(ageInput)){
                Printer.print("Please, digit a number for age");
            } else {
                beanAge = new BeanAge(Integer.parseInt(ageInput));
                String ageRange = beanAge.verifyAge(Integer.parseInt(ageInput));
                if (isNumeric(ageInput) && ageRange == null) {
                    age = Integer.parseInt(ageInput);
                } else if (isNumeric(ageInput) && ageRange != null) {
                    Printer.print("Invalid input. Please enter a valid age.");
                }
            }
        }
    }

    private void selectWeight(BufferedReader bufferedReader) throws IOException {
        float weight = 0.0f;
        while (weight <= 0) {
            Printer.print("Enter your weight (in kg): ");
            String weightInput = bufferedReader.readLine();
            if (!isNumeric(weightInput)){
                Printer.print("Please, digit a number for weight");
            } else {
                beanWeight = new BeanWeight(Float.parseFloat(weightInput));
                String weightRange = beanWeight.verifyWeight(Float.parseFloat(weightInput));
                if (isNumeric(weightInput) && weightRange == null) {
                    weight = Float.parseFloat(weightInput);
                } else {
                    Printer.print("Invalid input. Please enter a valid weight.");
                }
            }
        }
    }

    private void selectHeight(BufferedReader bufferedReader) throws IOException {
        float height = 0.0f;
        while (height <= 0) {
            Printer.print("Enter your height (in cm): ");
            String heightInput = bufferedReader.readLine();
            if (!isNumeric(heightInput)){
                Printer.print("Please, digit a number for height");
            } else {
                beanHeight = new BeanHeight(Float.parseFloat(heightInput));
                String heightRange = beanHeight.verifyHeight(Float.parseFloat(heightInput));
                if (isNumeric(heightInput) && heightRange == null) {
                    height = Float.parseFloat(heightInput);
                } else {
                    Printer.print("Invalid input. Please enter a valid height.");
                }
            }
        }
    }

    private void selectActivityLevel(BufferedReader bufferedReader) throws IOException {
        int activityLevel = 0;
        Activity activitySelected;
        while (activityLevel < 1 || activityLevel > 5) {
            Printer.print("Select your activity level: ");
            Printer.print("1: Sedentary (office job)");
            Printer.print("2: Light exercise (1-2 days/week)");
            Printer.print("3: Moderate exercise (3-5 days/week)");
            Printer.print("4: Heavy exercise (6-7 days/week)");
            Printer.print("5: Athlete (2 x day)");
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
                Printer.print("Invalid choice. Please select a number from 1 to 5.");
            }
        }
    }

    private void selectTarget(BufferedReader bufferedReader, String mainTarget, String cutTarget, String bulkTarget) throws IOException {
        String target = "";
        while (!target.equals(mainTarget) && !target.equals(cutTarget) && !target.equals(bulkTarget)) {
            Printer.print("Select your target: ");
            Printer.print("1: Maintenance (maintain current weight)");
            Printer.print("2: Cutting (lose weight)");
            Printer.print("3: Bulking (gain weight/muscle)");
            String choice = bufferedReader.readLine();
            switch (choice) {
                case "1" -> {
                    beanTarget = new BeanTarget(mainTarget);
                    target = mainTarget;
                }
                case "2" -> {
                    beanTarget = new BeanTarget(cutTarget);
                    target = cutTarget;
                }
                case "3" -> {
                    beanTarget = new BeanTarget(bulkTarget);
                    target = bulkTarget;
                }
                default ->
                        Printer.print("Invalid choice. Please select 1 for Maintenance, 2 for Cutting, or 3 for Bulking.");
            }
        }
    }

    private boolean isNumeric(String str) {
        // this method verify if the age, weight and height are numeric
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
