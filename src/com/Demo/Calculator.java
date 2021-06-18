package com.Demo;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.stream.IntStream;

//Calculator
public class Calculator extends Applet implements ActionListener {
    //Text Fields
    private TextField valueSum;

    //Buttons
    //Arithmetic Buttons
    private Button buAdd;
    private Button buSub;
    private Button buMul;
    private Button buDiv;
    private Button buRem;
    //Assignment Buttons
    private Button buClear;
    private Button buEq;
    //Operands Buttons
    private Button[] buNum;

    //Font
    private Font buFon;

    //Value Variables
    private Double valPre;
    private Double valFor;
    private Double valResD;
    private int count;

    //Strings
    private String hold;
    private String errHold;

    //Boolean Variables
    private boolean lock;
    private boolean eqLoc;
    private boolean opLoc;
    static Dialog dialog;

    @Override
    public void init() {
        //Text Fields
        valueSum = new TextField();
        //Buttons
        //Arithmetic Buttons
        buAdd = new Button("+");
        buSub = new Button("-");
        buMul = new Button("*");
        buDiv = new Button("/");
        buRem = new Button("%");

        //Assignment Buttons
        buClear = new Button("Clear");
        buEq = new Button("=");
        //Operands Buttons
        buNum = new Button[10];
        buNum[0] = new Button("0");
        IntStream.range(1, buNum.length).forEach(i -> buNum[i] = new Button("" + i + ""));

        //Font
        buFon = new Font("Arial", Font.PLAIN, 25);

        //Value Variables
        valPre = null;
        valFor = null;
        valResD = null;
        count = 0;

        //Strings
        hold = null;
        errHold=null;

        //Boolean Variables
        lock = false;
        eqLoc = false;
        opLoc = false;

        super.init();
    }

    @Override
    public void start() {
        setLayout(new GridLayout(7,3,3,3));
        setSize(500, 800);
        setBackground(Color.gray);

   /* }

    @Override
    public void paint(Graphics g){        super.paint(g);}*/

        valueSum.setFont(buFon);
        /*valueSum.setSize(500,70);
        valueSum.setLocation(0,0);*/
        //Arrays.stream(buNum).forEach(value -> value.setFont(buFon));
        for (Button value : buNum) {
            value.setSize(70, 70);
            value.setFont(buFon);
        }
       /* buNum[1].setLocation(0,75);
        buNum[2].setLocation(75,75);
        buNum[3].setLocation(150,75);
        buNum[4].setLocation(0,150);
        buNum[5].setLocation(75,150);
        buNum[6].setLocation(150,150);
        buNum[7].setLocation(0,225);
        buNum[8].setLocation(75,225);
        buNum[9].setLocation(150,225);*/
        buAdd.setFont(buFon);
        buSub.setFont(buFon);
        buMul.setFont(buFon);
        buDiv.setFont(buFon);
        buRem.setFont(buFon);
        buClear.setFont(buFon);
        buEq.setFont(buFon);

        //Add Buttons
        add(valueSum);
        valueSum.setEditable(false);
        add(buAdd);
        add(buSub);
        add(buMul);
        add(buDiv);
        add(buRem);
        IntStream.range(1, buNum.length).forEach((i -> add(buNum[i])));
        add(buClear);
        add(buNum[0]);
        add(buEq);

        //Action Listeners
        Arrays.stream(buNum).forEach(button -> button.addActionListener(this));
        buAdd.addActionListener(this);
        buSub.addActionListener(this);
        buMul.addActionListener(this);
        buDiv.addActionListener(this);
        buRem.addActionListener(this);
        buEq.addActionListener(this);
        buClear.addActionListener(this);
        super.start();
    }
        @Override
        //Input Action Events
        public void actionPerformed (ActionEvent num){
            try {
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
                    case "9":
                    case "0":
                        if (!lock) {
                            if (valPre != null) valPre = ((valPre * 10) + Integer.parseInt(actionSource.getLabel()));
                            else valPre = Double.parseDouble(actionSource.getLabel());
                        }
                        if (lock) {
                            if (valFor != null) valFor = ((valFor * 10) + Integer.parseInt(actionSource.getLabel()));
                            else valFor = Double.parseDouble(actionSource.getLabel());
                        }
                        if (eqLoc && hold == null) {
                            valFor = null;
                            break;
                        } else {
                            valueSum.setText(valueSum.getText() + actionSource.getLabel());
                            errHold=valueSum.getText();
                        }
                        break;

                    case "+":
                    case "-":
                    case "*":
                    case "/":
                    case "%":
                        if (valPre == null) throw new Exception("No Data entered");
                        if (count == 5) throw new Exception("Enter Data First");

                            if (opLoc) {
                                count++;
                                break;
                            } else valueSum.setText(valueSum.getText() + actionSource.getLabel());
                        hold = actionSource.getLabel();
                        valFor = null;
                        opLoc = true;
                        lock = true;

                        break;

                    case "=":

                        if (valFor == null) {
                            if (valResD != null)
                                valueSum.setText(Double.toString(valResD));
                            break;
                        }

                        CalRes();

                        valueSum.setText(valPre + "" + hold + "" + valFor + "=" + valResD);
                        valFor = null;
                        valPre = valResD;
                        count=0;
                        hold = null;
                        eqLoc = true;
                        opLoc = false;
                        errHold=null;
                        break;

                    case "Clear":
                        Clear();
                        break;
                    default:
                        throw new IllegalStateException("Illegal Event: " + actionSource.getLabel());
                }
            } catch (Exception exp) {
                PopError("Error: " + exp.getMessage());
            }
        }

        public void Clear () {
            valueSum.setText(null);
            valPre = null;
            valFor = null;
            valResD = null;
            hold = null;
            lock = false;
            eqLoc = false;
            opLoc = false;
            errHold=null;
            count=0;
        }
        public void CalRes () throws Exception {
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
                    if (valFor == 0.0) throw new Exception("Cannot divide by zero");
                    valResD = valPre / valFor;
                    break;
                case "%":
                    if (valFor == 0.0) throw new Exception("Cannot divide by zero");
                    valResD = valPre % valFor;
                    break;
                default:
                    throw new IllegalStateException("No operator selected");
            }
        }
        public void PopError (String catchError){
            Frame frame = new Frame();
            dialog = new Dialog(frame, "Calculator Error", true);
            dialog.setLayout(new GridLayout());
            Button ok = new Button("OK");
            ok.setSize(20, 10);
            ok.addActionListener(err -> Calculator.dialog.setVisible(false));
            dialog.add(new Label(catchError.toUpperCase()));
            dialog.add(ok);
            //Clear();
            valueSum.setText(errHold);
            dialog.setSize(450, 100);
            dialog.setVisible(true);
        }
}