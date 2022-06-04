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
        JLabel annualIncreaseLabel = new JLabel("Annual Return Rate:");
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
        outputWindow.setBounds(320,10,250,150);
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
                if(yearlyIncrease >= 0 && years >= 0 && startingAmount >= 0 && returnRate >= 0) {
                    calculateInvestment cc =
                            new calculateInvestment(startingAmount, years, returnRate, yearlyIncrease);
                    cc.outputString(outputWindow);
                }else{
                    errorMessage();
                }
            }catch(Exception ex){
                errorMessage();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(405, 175, 80,30);
        exitButton.setFocusable(false);
        panel.add(exitButton);
        exitButton.addActionListener(e -> System.exit(0));

        this.setResizable(false);
        //this.setLocationRelativeTo(null);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setVisible(true);
    }

    public void errorMessage(){
        JOptionPane.showMessageDialog(new JFrame(), "Numbers need to be positive",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
