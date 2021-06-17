package com.Demo;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

//Calculator
public class Calculator extends Applet implements ActionListener {
    TextField valueSum = new TextField();
    TextField express = new TextField();
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
    Double valPre = null;
    Double valFor = null;
    Double valResD =null;
    //Arithmetic Temp Hold
    String hold = null;
    //Boolean Variables
    boolean lock = false;
    boolean eqLoc = false;
    boolean opLoc = false;
    static Dialog dialog;

    public void PopError(String catchError) {
        Frame frame= new Frame();
        dialog= new Dialog(frame, "Calculator Error", true);
        dialog.setLayout(new GridLayout());
        Button ok=new Button("OK");
        ok.setSize(20, 10);
        ok.addActionListener(err -> Calculator.dialog.setVisible(false));
        dialog.add(new Label(catchError));
        dialog.add(ok);
        dialog.setSize(200,100);
        dialog.setVisible(true);
    }
    //Constructor
  public Calculator() { MainFrameWindows(); }
    public void MainFrameWindows() {
        setLayout(new GridLayout(7, 3, 5, 5));
        setBackground(Color.gray);

        for (int i = 0; i < buNum.length; i++)  buNum[i] = new Button("" + i + "");

        add(express);
        express.setVisible(false);
        express.setEditable(false);

        add(valueSum);
        valueSum.setEditable(false);

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
            Button actionSource = (Button) num.getSource();
            switch (actionSource.getLabel()) {
                case "1":case "2":case "3":
                case "4":case "5":case "6":
                case "7":case "8":case "9 ":
                         case "0":
                    if (!lock) {
                        if(valPre!=null) valPre = ((valPre * 10) + Integer.parseInt(actionSource.getLabel()));
                        else valPre=Double.parseDouble(actionSource.getLabel());
                    }
                    if (lock) {
                        if(valFor!=null) valFor = ((valFor * 10) + Integer.parseInt(actionSource.getLabel()));
                        else valFor=Double.parseDouble(actionSource.getLabel());
                    }
                    if(eqLoc&&hold==null) { valFor=null; break; }
                    else valueSum.setText(valueSum.getText()+actionSource.getLabel());
                break;

                case "+":case "-":case "*":case "/": case "%":
                    if(valPre==null) throw new Exception("No Data entered");
                      if(opLoc) break;
                      else valueSum.setText(valueSum.getText()+actionSource.getLabel());
                    hold = actionSource.getLabel();
                    valFor=null;
                    opLoc=true;
                    lock = true;
                break;

                case "=":
                    if(valFor==null){ valueSum.setText(Double.toString(valResD)); break; }
                    CalRes();
                    valueSum.setText(valPre+"" + hold +""+valFor+"="+valResD);
                    valFor = null;
                    valPre = valResD;
                    hold=null;
                    eqLoc= true;
                    opLoc=false;
                break;

                case "Clear":
                    valueSum.setText(null);
                    valPre = null;
                    valFor = null;
                    valResD =null;
                    hold = null;
                    lock = false;
                    eqLoc = false;
                    opLoc = false;
                    break;
                default: throw new IllegalStateException("Wrong Option: " + actionSource.getLabel());
            }
        } catch (Exception exp) { PopError("Error: "+ exp.getMessage()); }
    }
    public void CalRes() throws Exception{
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
            default: throw new IllegalStateException("No Operator Selected");
        }
    }
}