package com.Demo;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import  java.awt.event.ActionListener;
public class Applet_Demo extends Applet implements ActionListener{

    TextField valueOne  = new TextField();
    TextField valueTwo  = new TextField();
    TextField valueSum  = new TextField();

    Button buttonOne = new Button("Calculate");

    public Applet_Demo(){
        Label valOneLbl = new Label("Value 1: ");
        Label valTwoLbl = new Label("Value 2: ");
        Label valSumLbl = new Label("Result : ");

        setLayout(new GridLayout(4,3));
        setBackground(Color.gray);

        add(valOneLbl);
        add(valueOne);

        add(valTwoLbl);
        add(valueTwo);

        add(valSumLbl);
        add(valueSum);

        add(buttonOne);
        buttonOne.addActionListener(this);//Sub
    }

    @Override
    public void actionPerformed(ActionEvent press) { //Pub

        try{
        Double valOneD = Double.parseDouble(valueOne.getText());
        Double valTwoD = Double.parseDouble(valueTwo.getText());

            double valSumD= valOneD+valTwoD;

            valueSum.setText("Result:"+ valSumD);

        }
        catch (Exception Exp)
        {
            String msg=Exp.getMessage();
            String upper= msg.toUpperCase();
            valueSum.setText("Invild Entry: "+ upper);
        }

    }
}
