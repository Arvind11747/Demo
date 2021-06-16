package com.Demo;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Calculator
public class Calculator extends Applet implements ActionListener {

    TextField valueSum = new TextField();
    TextField express= new TextField();
    //TextField temp= new TextField();

    //Arithmetic Buttons
    Button buAdd = new Button("+");
    Button buSub = new Button("-");
    Button buMul = new Button("*");
    Button buDiv = new Button("/");
    Button buRem = new Button("%");
    //Arithmetic Temp Hold
    String hold="";

    //Assignment Buttons
    Button buClear = new Button("Clear");
    Button buEq = new Button("=");

    //Operands Buttons
    Button[] buNum = new Button[10];

    //Value Variables
    Double preResD=0.0;
    Double valPre = 0.0;
    Double valFor = 0.0;
    Double valResD = 0.0;

    //Boolean Variables
    boolean lock = false;
    boolean first = true;
    //Constructor
    public Calculator() {
       setLayout( new GridLayout(7,3,5,5));
        setBackground(Color.gray);

        buNum[0] = new Button("1");
        buNum[1] = new Button("2");
        buNum[2] = new Button("3");
        buNum[3] = new Button("4");
        buNum[4] = new Button("5");
        buNum[5] = new Button("6");
        buNum[6] = new Button("7");
        buNum[7] = new Button("8");
        buNum[8] = new Button("9");
        buNum[9] = new Button("0");
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
       // add(temp);
        //temp.setText("ValPre: "+valFor+" ValFor:"+ valFor+ " preResD: "+preResD +"valResD: "+valResD);
        for (Button button : buNum) button.addActionListener(this);
        GetOpera();

    }

    @Override
    //Number Input Action Events
    public void actionPerformed(ActionEvent num) {
        try {
            Button actionSource = (Button) num.getSource();
            if(!lock&&first) {

                valueSum.setText(/*valueSum.getText()+*/actionSource.getLabel());
                valPre = ((valPre*10)+ Integer.parseInt(actionSource.getLabel()));
                valueSum.setText(Double.toString(valPre));
            }
            if(lock) {
                valueSum.setText(/*valueSum.getText()+*/actionSource.getLabel());
                valFor = ((valFor * 10)+Integer.parseInt(actionSource.getLabel()));
                valueSum.setText(Double.toString(valFor));
            }
            express.setText("Res: " + valResD);
            /*if(!hold.equals("")) {
                switch (hold) {
                    case "+":
                        preResD = valPre + valFor;
                        break;
                    case "-":
                        preResD = valPre - valFor;
                        break;
                    case "*":
                        preResD = valPre * valFor;
                        break;
                    case "/":
                        if (valFor == 0.0) throw new Exception("Cannot Divide by Zero");
                        preResD = valPre / valFor;
                        break;
                    case "%":
                        if (valFor == 0.0) throw new Exception("Cannot Divide by Zero");
                        preResD = valPre % valFor;
                        break;
                    default:
                        throw new IllegalStateException("Error No Operator Selected");
                }

                express.setText("preres:" + preResD);
                    valPre = preResD;
            }*/
        // }
        } catch (Exception exp) { valueSum.setText("Invalid Entry Num: " + exp.getMessage()); }

    }

    //Operators Input Action Events
    public void GetOpera() {
            ActionListener operaEvent = opera -> {
                Button actionSource = (Button) opera.getSource();
                     try {
                         if(!first)
                              valPre= valResD;

                        switch (actionSource.getLabel()) {
                        case "+":
                             lock=true;
                             hold="+";
                             valFor=0.0;
                             break;

                        case "-":
                            lock=true;
                            hold="-";
                            break;

                        case "*":
                            lock=true;
                            hold="*";
                            break;

                        case "/":
                            lock=true;
                            hold="/";
                            break;

                        case "%":
                            lock=true;
                            hold="%";
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
                                    if (valFor == 0.0) throw new Exception ("Cannot Divide by Zero");
                                    valResD = valPre % valFor;
                                    break;
                                default:
                                    throw new IllegalStateException("Error No Operator Selected");
                            }
                                first=false;
                           // if(valFor!=null && valPre!=null);
                                 valFor=0.0;
                                 valueSum.setText("Res: " + valResD);
                               // express.setText(Double.toString(valResD));
                            break;

                        case "Clear":
                            valueSum.setText("Cleared");
                            valPre=0.0;
                            valFor=0.0;
                            valResD=0.0;
                            preResD=0.0;
                            first=true;
                            lock=false;
                            hold="";
                            express.setText("");
                            break;
                        default:
                            throw new IllegalStateException("Error: " + actionSource.getLabel());
                    }
                      //   temp.setText("ValPre: "+valFor+" ValFor:"+ valFor+ " preResD: "+preResD +"valResD: "+valResD);
                    //if(!actionSource.getLabel().equals("=")&&!actionSource.getLabel().equals("Clear"))
                        //express.setText(express.getText()+ actionSource.getLabel());
                } catch (Exception exp) {valueSum.setText("Invalid Entry : " + exp.getMessage()); }
            };
            buAdd.addActionListener(operaEvent);
            buSub.addActionListener(operaEvent);
            buMul.addActionListener(operaEvent);
            buDiv.addActionListener(operaEvent);
            buRem.addActionListener(operaEvent);
            buEq.addActionListener(operaEvent);
            buClear.addActionListener(operaEvent);
        }
    }