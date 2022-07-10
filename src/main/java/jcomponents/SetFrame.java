package jcomponents;

import customcolors.CustomColors;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/*
    Image link : http://clipart-library.com/investment-cliparts.html
 */

public class SetFrame extends JFrame {

    JPanel panel;
    private static final Font font = new Font("geneva", Font.PLAIN, 15);
    private static final Font outputFont = new Font("geneva", Font.PLAIN, 16);

    public SetFrame(){
        this.setTitle("Investment Calculator");
        this.setLayout(null);

        ImageIcon icon = new ImageIcon("img.png");
        this.setIconImage(icon.getImage());

        panel = new JPanel();
        panel.setLayout(null);
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
            arrText[i].setBounds(170, 10 + (i * 50), 150, 30);
            arrText[i].setBackground(CustomColors.dark.darker());
            arrText[i].setForeground(CustomColors.light);
            arrText[i].setCaretColor(CustomColors.light);
            arrText[i].setBorder(new LineBorder(CustomColors.teal.darker(), 1));
        }

        JTextPane outputWindow = new JTextPane();
        outputWindow.setBounds(380,10,350,180);
        StyledDocument documentStyle = outputWindow.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0,
                documentStyle.getLength(), centerAttribute, false);
        setOutputWindowCustomization(outputWindow);
        panel.add(outputWindow);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(190,215,100,30);
        setButtonCustomization(calculateButton);
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
                if(startingAmount > 1000000000.0 || yearlyIncrease > 1000000000.0){
                    inputTooLargeError();
                    return;
                }
                if(returnRate > 1000){
                    invalidInterestRateError();
                    return;
                }
                if(yearlyIncrease >= 0 && years >= 0 && startingAmount >= 0 && returnRate >= 0) {
                    CalculateInvestment calculateInvestment =
                            new CalculateInvestment(startingAmount, years + 1, returnRate, yearlyIncrease);
                    calculateInvestment.outputString(outputWindow);
                    addChart(calculateInvestment);
                }else{
                    errorMessage();
                }
            }catch(Exception ex){
                errorMessage();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(515, 215, 80,30);
        setButtonCustomization(exitButton);
        panel.add(exitButton);
        exitButton.addActionListener(e -> System.exit(0));

        panel.setBounds(265,10, 760, 280);

        this.getContentPane().setBackground(CustomColors.darker);
        this.setResizable(false);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280,720);
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
            arrLabel[i].setFont(font);
            arrLabel[i].setBounds(10, 10 + (i  * 50), 150, 30);
        }
    }

    public static void setButtonCustomization(JButton button){
        button.setFocusable(false);
        button.setBackground(CustomColors.dark);
        //other border: new LineBorder(CustomColors.light.darker(),1)
        button.setBorder(null);
        button.setForeground(CustomColors.teal);
    }

    public static void setOutputWindowCustomization(JTextPane outputWindow){
        outputWindow.setEditable(false);
        outputWindow.setFocusable(false);
        outputWindow.setForeground(CustomColors.light);
        outputWindow.setFont(outputFont);
        outputWindow.setBorder(new LineBorder(CustomColors.teal.darker(),1));
        outputWindow.setBackground(CustomColors.dark.darker());
    }

    public static void oneHundredYearLimitError(){
        JOptionPane.showMessageDialog(new JFrame(), "Investment must be under 100 years",
                "Invalid timeframe", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void inputTooLargeError(){
        JOptionPane.showMessageDialog(new JFrame(), "Yearly and initial" +
                        "investment must be under $1 billion in value",
                "Invalid investment amount", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void invalidInterestRateError(){
        JOptionPane.showMessageDialog(new JFrame(), "Return rate must be under %1000",
                "Invalid return rate", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void errorMessage(){
        JOptionPane.showMessageDialog(new JFrame(), "Numbers need to be positive",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void addChart(CalculateInvestment calculateInvestment){
        JPanel panel = calculateInvestment.getChartPanel();
        panel.setBounds(260,290,740,375);
        panel.setBackground(CustomColors.darker);
        this.add(panel);
    }
}
