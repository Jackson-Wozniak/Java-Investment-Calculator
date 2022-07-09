package jcomponents;

import customcolors.CustomColors;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class setFrame extends JFrame {
    JPanel panel;

    public setFrame(){
        this.setTitle("Investment Calculator");
        ImageIcon icon = new ImageIcon("img.png");
        this.setIconImage(icon.getImage());
        panel = new JPanel();
        panel.setLayout(null);
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        panel.setBackground(CustomColors.darker);

        addLabels();

        JTextField startAmountText = new JTextField();
        JTextField totalYearText = new JTextField();
        JTextField returnRateText = new JTextField();
        JTextField annualIncreaseText = new JTextField();
        JTextField[] arrText =
                {startAmountText, totalYearText, returnRateText, annualIncreaseText};
        for(int i = 0; i < arrText.length; i++){
            panel.add(arrText[i]);
            arrText[i].setBounds(160, 10 + (i * 40), 150, 30);
            arrText[i].setBackground(CustomColors.dark);
            arrText[i].setForeground(CustomColors.light);
            arrText[i].setCaretColor(CustomColors.light);
            //arrText[i].setBorder(new LineBorder(CustomColors.teal, 1));
            arrText[i].setBorder(null);
        }

        JTextArea outputWindow = new JTextArea();
        outputWindow.setBounds(320,10,340,150);
        outputWindow.setEditable(false);
        outputWindow.setFocusable(false);
        outputWindow.setForeground(CustomColors.light);
        outputWindow.setFont(font);
        outputWindow.setBorder(null);
        outputWindow.setBackground(CustomColors.dark);
        panel.add(outputWindow);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(75,175,100,30);
        calculateButton.setFocusable(false);
        calculateButton.setFocusable(false);
        calculateButton.setBackground(CustomColors.dark);
        calculateButton.setBorder(null);
        calculateButton.setForeground(CustomColors.teal);
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
        exitButton.setBackground(CustomColors.dark);
        exitButton.setBorder(null);
        exitButton.setForeground(CustomColors.teal);
        exitButton.addActionListener(e -> System.exit(0));

        this.setResizable(false);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(690,250);
        this.setVisible(true);
    }

    public void addLabels(){
        JLabel startAmountLabel = new JLabel("Starting Amount:");
        JLabel totalYearLabel = new JLabel("Years Of Investment:");
        JLabel returnRateLabel = new JLabel("Return Rate(%):");
        JLabel annualIncreaseLabel = new JLabel("Annual Contribution:");
        JLabel[] arrLabel = {startAmountLabel, totalYearLabel, returnRateLabel, annualIncreaseLabel};
        for(int i = 0; i < arrLabel.length; i++){
            panel.add(arrLabel[i]);
            arrLabel[i].setForeground(CustomColors.light);
            arrLabel[i].setBounds(10, 10 + (i  * 40), 150, 30);
        }
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
        panel.setBounds(0,200,680,380);
        panel.setBackground(CustomColors.darker);
        this.setSize(690,610);
        this.add(panel);
    }
}
