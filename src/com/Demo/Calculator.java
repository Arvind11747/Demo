package com.Demo;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

//Calculator
public class Calculator extends Applet implements ActionListener {

    TextField valueSum = new TextField();
    TextField express = new TextField();
    //TextField temp= new TextField();

    //Buttons
    //Arithmetic Buttons
    Button buAdd = new Button("+");
    Button buSub = new Button("-");
    Button buMul = new Button("*");
    Button buDiv = new Button("/");
    Button buRem = new Button("%");
    //Assignment Buttons
    Button buClear = new Button("Clear");
    Button buEq = new Button("=");
    //Operands Buttons
    Button[] buNum = new Button[10];

    //Value Variables
   // Double preResD = null;
    Double valPre = null;
    Double valFor = null;
    Double valResD =null;
    //Arithmetic Temp Hold
    String hold = null;

    //Boolean Variables
    boolean lock = false;
    boolean first = true;


    //Constructor
  public Calculator() { MainFrameWindows(); }

    public void MainFrameWindows() {
        resize(1000, 1000);
        setLayout(new GridLayout(7, 3, 5, 5));
        setBackground(Color.gray);

        for (int i = 0; i < buNum.length; i++)
            buNum[i] = new Button("" + i + "");

        add(express);
        add(valueSum);
        valueSum.setEditable(false);

        express.setVisible(true);
        express.setEditable(false);

        add(buEq);
        add(buAdd);
        add(buSub);
        add(buMul);
        add(buDiv);
        add(buRem);

        for (Button button : buNum) add(button);
        add(buClear);

        //Action Listeners
        for (Button button : buNum) button.addActionListener(this);
        buAdd.addActionListener(this);
        buSub.addActionListener(this);
        buMul.addActionListener(this);
        buDiv.addActionListener(this);
        buRem.addActionListener(this);
        buEq.addActionListener(this);
        buClear.addActionListener(this);

    }

    @Override
    //Input Action Events
    public void actionPerformed(ActionEvent num) {
        try {
            if (!first)
                valPre = valResD;
            Button actionSource = (Button) num.getSource();
            switch (actionSource.getLabel()) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9 ":
                case "0":
                    if (!lock && first) {
                        valueSum.setText(/*valueSum.getText()+*/actionSource.getLabel());
                        if(valPre!=null)
                        valPre = ((valPre * 10) + Integer.parseInt(actionSource.getLabel()));
                        else
                            valPre=Double.parseDouble(actionSource.getLabel());
                        valueSum.setText(Double.toString(valPre));
                    }

                    if (lock) {
                        valueSum.setText(/*valueSum.getText()+*/actionSource.getLabel());
                        if(valFor!=null)
                        valFor = ((valFor * 10) + Integer.parseInt(actionSource.getLabel()));
                        else
                            valFor=Double.parseDouble(actionSource.getLabel());
                        valueSum.setText(Double.toString(valFor));
                    }
                    // express.setText("Res: " + valResD);
                break;

                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                    if(valPre==null) throw new Exception("No Data entered");
                    lock = true;
                    hold = actionSource.getLabel();
                    valFor=0.0;
                    break;

                case "=":
                    switch (hold) {
                        case "+":
                            valResD = valPre + valFor;
                            break;
                        case "-":
                            valResD = valPre - valFor;
                            break;
                        case "*":
                            valResD = valPre * valFor;
                            break;
                        case "/":
                            if (valFor == 0.0) throw new Exception("Cannot Divide by Zero");
                            valResD = valPre / valFor;
                            break;
                        case "%":
                            if (valFor == 0.0) throw new Exception("Cannot Get Remainder for Zero Denominator");
                            valResD = valPre % valFor;
                            break;
                        default:
                            throw new IllegalStateException("No Operator Selected");
                    }
                    first = false;
                    valFor = 0.0;
                    valueSum.setText("Res: " + valResD);
                    hold=null;
                    // express.setText(Double.toString(valResD));
                    break;

                case "Clear":
                    valueSum.setText("Cleared");
                    valPre = null;
                    valFor = null;
                    valResD = null;
                   // preResD = 0.0;
                    first = true;
                    lock = false;
                    hold = null;
                    express.setText("");
                    break;
                default:
                    throw new IllegalStateException("Wrong Option: " + actionSource.getLabel());
            }

        } catch (Exception exp) {
            valueSum.setText("Error " + exp.getMessage());
        }
                      //   temp.setText("ValPre: "+valFor+" ValFor:"+ valFor+ " preResD: "+preResD +"valResD: "+valResD);
    }
}