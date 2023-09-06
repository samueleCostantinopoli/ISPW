package com.example.fitnesshelp.graphics_controllers;

import com.example.fitnesshelp.application_controllers.ApplicationControllerBuyWorkoutPlan;
import com.example.fitnesshelp.bean.BeanState;
import com.example.fitnesshelp.entities.Purchase;
import com.example.fitnesshelp.entities.WorkoutPlan;
import com.example.fitnesshelp.utils.UtilityAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class GraphicsControllerBuyWorkoutPlan1 extends GraphicsControllerHomePage {

    @FXML
    public TextField CardNumberTextField;
    @FXML
    public TextField ExpiresTextField;
    @FXML
    public TextField CVVTextField;
    @FXML
    public TextField FirstNameTextField;
    @FXML
    public TextField LastNameTextField;
    @FXML
    public TextField StreetAddressTextField;
    @FXML
    public TextField ApartmentTextField;
    @FXML
    public TextField CityTextField;
    @FXML
    public TextField ZipCodeTextField;
    @FXML
    public TextField MobileTextField;
    @FXML
    public TextField EmailTextField;
    @FXML
    private ChoiceBox<String> StateChoiceBox;
    @FXML
    public CheckBox MyEmailCheckBox;

    @FXML
    private Button HyperLinkButton;

    @FXML
    CheckBox CheckCreditCard;

    @FXML
    CheckBox CheckPayPal;

    @FXML
    CheckBox CheckBitcoin;

    @FXML
    private AnchorPane WorkoutPlan1;

    @FXML
    Button BackPaymentMethodsButton;

    @FXML
    ImageView BitcoinAddress;

    private WebView webView;
    private WebEngine webEngine;
    public Purchase newPurchase;
    ApplicationControllerBuyWorkoutPlan applicationControllerBuyWorkoutPlan = new ApplicationControllerBuyWorkoutPlan(new BeanState(UtilityAccess.getState()));
    List<WorkoutPlan> workoutPlanList = applicationControllerBuyWorkoutPlan.checkWorkoutPlan();

    public GraphicsControllerBuyWorkoutPlan1() throws IOException {
    }

    void SaveWorkout(int thisWorkout) {
        WorkoutPlan workoutPlanToPurchase= workoutPlanList.get(thisWorkout);
        newPurchase = new Purchase(GenerateId(), workoutPlanToPurchase.getPrize(), TakeDate(), UtilityAccess.getUsername(), workoutPlanToPurchase);
    }

    private Date TakeDate() {
        Date currentDate = new Date();
        return currentDate;
    }

    private int GenerateId() {
        Random random = new Random();
        // Generate int random ID
        int id = random.nextInt(Integer.MAX_VALUE);
        return id;
    }

    @FXML
    void initialize() {
        CheckCreditCard.setOnAction(event -> {
            if (CheckCreditCard.isSelected()) {
                // Se CheckCreditCard è selezionato, deseleziona gli altri
                HyperLinkButton.setVisible(false);
                CheckPayPal.setSelected(false);
                CheckBitcoin.setSelected(false);
                SetCreditCard(true);
            }
        });
        CheckPayPal.setOnAction(event -> {
            if (CheckPayPal.isSelected()) {
                SetCreditCard(false);
                // Se CheckPayPal è selezionato, deseleziona gli altri
                CheckCreditCard.setSelected(false);
                CheckBitcoin.setSelected(false);
                HyperLinkButton.setText("PayPal Log In");
                HyperLinkButton.setVisible(true);
            }
        });
        CheckBitcoin.setOnAction(event -> {
            if (CheckBitcoin.isSelected()) {
                SetCreditCard(false);
                // If CheckBitcoin is selected, deselected other
                CheckCreditCard.setSelected(false);
                CheckPayPal.setSelected(false);
                HyperLinkButton.setText("Bitcoin address");
                HyperLinkButton.setVisible(true);
            }
        });
    }

    private void SetCreditCard(boolean state){
        CardNumberTextField.setVisible(state);
        ExpiresTextField.setVisible(state);
        CVVTextField.setVisible(state);
        FirstNameTextField.setVisible(state);
        LastNameTextField.setVisible(state);
        StreetAddressTextField.setVisible(state);
        ApartmentTextField.setVisible(state);
        CityTextField.setVisible(state);
        ZipCodeTextField.setVisible(state);
        MobileTextField.setVisible(state);
        EmailTextField.setVisible(state);
        StateChoiceBox.setVisible(state);
        MyEmailCheckBox.setVisible(state);
    }


    @FXML
    private void OnActionHyperLinkButton(ActionEvent event) {
        if(HyperLinkButton.getText().equals("PayPal Log In")) {
            // Carica la pagina web di PayPal nel WebView
            webView = new WebView();
            webEngine = webView.getEngine();

            // Aggiungi il WebView al contenitore nell'interfaccia utente
            WorkoutPlan1.getChildren().add(webView);
            webEngine.load("https://www.paypal.me/sCostantinopoli");
            CheckCreditCard.setVisible(false);
            CheckPayPal.setVisible(false);
            CheckBitcoin.setVisible(false);

        } else {
            BitcoinAddress.setVisible(true);
            CheckCreditCard.setVisible(false);
            CheckPayPal.setVisible(false);
            CheckBitcoin.setVisible(false);
        }
        BackPaymentMethodsButton.setVisible(true);
        BackPaymentMethodsButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
    }

    public void clickedOnPurchaseBuyWorkoutPlanButton(ActionEvent event) throws IOException {
        GraphicsControllerBuyWorkoutPlan2 graphicsControllerBuyWorkoutPlan2 = new GraphicsControllerBuyWorkoutPlan2();
        graphicsControllerBuyWorkoutPlan2.savePurchase(newPurchase);
        stageToSwitch = "/com/example/fitnesshelp/buyWorkoutPlan2";
        switchStage(event);
    }

    public void clickedOnBuyWorkoutPlanHyperlink1(ActionEvent event) throws IOException {
        stageToSwitch = "/com/example/fitnesshelp/buyWorkoutPlan";
        switchStage(event);
    }

    public void clickedOnBackPaymentMethodsButton(ActionEvent event) {
        if(HyperLinkButton.getText().equals("PayPal Log In")) {
            // Remove WebView
            WorkoutPlan1.getChildren().remove(webView);
            webEngine.getLoadWorker().cancel();
        }
        else {
            BitcoinAddress.setVisible(false);
        }
        BackPaymentMethodsButton.setVisible(false);
        CheckCreditCard.setVisible(true);
        CheckPayPal.setVisible(true);
        CheckBitcoin.setVisible(true);
    }
}

