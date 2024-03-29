package com.example.fitnesshelp.application_controllers;

import com.example.fitnesshelp.bean.*;
import com.example.fitnesshelp.dao.DaoEntity;
import com.example.fitnesshelp.entities.*;
import com.example.fitnesshelp.factory.FactoryDao;
import com.example.fitnesshelp.factory.TypeOfEntity;
import com.example.fitnesshelp.factory.TypeOfPersistence;
import com.example.fitnesshelp.system_actor.TdeeCalculator;
import com.example.fitnesshelp.utils.UtilityAccess;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ApplicationControllerCalculateTdee {

    private int age;
    private float height;
    private float weight;
    private Gender gender;
    private Activity activity;
    private String target;

    public ApplicationControllerCalculateTdee(BeanAge beanAge, BeanHeight beanHeight, BeanWeight beanWeight, BeanGender beanGender, BeanActivity beanActivity, BeanTarget beanTarget){
        this.age = beanAge.getAge();
        this.height = beanHeight.getHeight();
        this.weight = beanWeight.getWeight();
        this.gender = beanGender.getGender();
        this.activity = beanActivity.getActivity();
        this.target = beanTarget.getTarget();
    }

    public ApplicationControllerCalculateTdee() {
    }

    public double calculateTdee(){
        // call system actor and request tdee
        Questionnaire questionnaire = new Questionnaire(gender, age, weight, height, activity);
        double kcal;
        TdeeCalculator tdeeCalculator = new TdeeCalculator();
        kcal = tdeeCalculator.calculateTdee(questionnaire, target);
        return kcal;
    }

    @SuppressWarnings("unchecked")
    public List<Tdee> requestTdeeList() throws SQLException, IOException {
        // this method take all tdee that user has saved
        FactoryDao factoryDao = new FactoryDao();
        return factoryDao.useDao(TypeOfPersistence.JDBC, TypeOfEntity.TDEE).showData(UtilityAccess.getUsername());
    }

    public List<Macro> requestMacroList(int kcal){
        // this method request at tdee calculator to calculate the partition of macro based on kcal
        List<Macro> macroList;
        TdeeCalculator tdeeCalculator = new TdeeCalculator();
        macroList = tdeeCalculator.calculateMacro(kcal);
        return macroList;
    }

    @SuppressWarnings("unchecked")
    public void saveTdee(TypeOfPersistence typeOfPersistence, Tdee tdee) throws SQLException, IOException {
        // this method save the tdee passed in db/fs
        FactoryDao factoryDao = new FactoryDao();
        DaoEntity<Tdee> daoEntity = factoryDao.useDao(typeOfPersistence, TypeOfEntity.TDEE);
        daoEntity.saveData(tdee);
    }

    @SuppressWarnings("unchecked")
    public void removeTdee(Tdee tdee) throws SQLException, IOException {
        // this method remove a tdee selected from db
        FactoryDao factoryDao = new FactoryDao();
        DaoEntity<Tdee> daoEntity = factoryDao.useDao(TypeOfPersistence.JDBC, TypeOfEntity.TDEE);
        daoEntity.removeData(tdee);
    }
}
