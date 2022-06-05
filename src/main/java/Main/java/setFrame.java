package Main.java;

import javax.swing.*;
import java.awt.*;

public class setFrame extends JFrame {
    public setFrame(){
        this.setTitle("Investment Calculator");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);

        JLabel startAmountLabel = new JLabel("Starting Amount:");
        JLabel totalYearLabel = new JLabel("Years Of Investment:");
        JLabel returnRateLabel = new JLabel("Return Rate(%):");
        JLabel annualIncreaseLabel = new JLabel("Annual Account Increase:");
        JLabel[] arrLabel = {startAmountLabel, totalYearLabel, returnRateLabel, annualIncreaseLabel};
        for(int i = 0; i < arrLabel.length; i++){
            panel.add(arrLabel[i]);
            arrLabel[i].setBounds(10, 10 + (i  * 40), 150, 30);
        }

        JTextField startAmountText = new JTextField();
        JTextField totalYearText = new JTextField();
        JTextField returnRateText = new JTextField();
        JTextField annualIncreaseText = new JTextField();
        JTextField[] arrText = {startAmountText, totalYearText, returnRateText, annualIncreaseText};
        for(int i = 0; i < arrText.length; i++){
            panel.add(arrText[i]);
            arrText[i].setBounds(160, 10 + (i * 40), 150, 30);
        }

        JTextArea outputWindow = new JTextArea();
        outputWindow.setBounds(320,10,340,150);
        outputWindow.setEditable(false);
        outputWindow.setFocusable(false);
        outputWindow.setFont(font);
        panel.add(outputWindow);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(75,175,100,30);
        calculateButton.setFocusable(false);
        calculateButton.setFocusable(false);
        panel.add(calculateButton);
        calculateButton.addActionListener(e -> {
            try{
                double startingAmount = Double.parseDouble(startAmountText.getText());
                int years = Integer.parseInt(totalYearText.getText());
                double returnRate = Double.parseDouble(returnRateText.getText());
                double yearlyIncrease = Double.parseDouble(annualIncreaseText.getText());

                if(years > 100){
                    oneHundredYearLimitError();
                    return;
                }
                if(startingAmount > 1000000000 || yearlyIncrease > 1000000000){
                    inputTooLargeError();
                    return;
                }
                if(returnRate > 1000){
                    invalidInterestRateError();
                    return;
                }
                if(yearlyIncrease >= 0 && years >= 0 && startingAmount >= 0 && returnRate >= 0) {
                    calculateInvestment cc =
                            new calculateInvestment(startingAmount, years, returnRate, yearlyIncrease);
                    cc.outputString(outputWindow);
                    addChart(cc);
                }else{
                    errorMessage();
                }
            }catch(Exception ex){
                errorMessage();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(455, 175, 80,30);
        exitButton.setFocusable(false);
        panel.add(exitButton);
        exitButton.addActionListener(e -> System.exit(0));

        this.setResizable(false);
        //this.setLocationRelativeTo(null);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,250);
        this.setVisible(true);
    }

    public void oneHundredYearLimitError(){
        JOptionPane.showMessageDialog(new JFrame(), "Investment must be under 100 years",
                "Invalid timeframe", JOptionPane.INFORMATION_MESSAGE);
    }

    public void inputTooLargeError(){
        JOptionPane.showMessageDialog(new JFrame(), "Yearly and initial" +
                        "investment must be under $1 billion in value",
                "Invalid investment amount", JOptionPane.INFORMATION_MESSAGE);
    }

    public void invalidInterestRateError(){
        JOptionPane.showMessageDialog(new JFrame(), "Return rate must be under %1000",
                "Invalid return rate", JOptionPane.INFORMATION_MESSAGE);
    }

    public void errorMessage(){
        JOptionPane.showMessageDialog(new JFrame(), "Numbers need to be positive",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void addChart(calculateInvestment cc){
        this.setLayout(null);
        JPanel panel = cc.getChartPanel();
        panel.setBounds(0,220,680,350);
        this.setSize(700,620);
        this.add(panel);
    }
}
