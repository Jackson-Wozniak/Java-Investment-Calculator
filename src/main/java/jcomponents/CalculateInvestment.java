package jcomponents;

import javax.swing.*;

public class CalculateInvestment {
    private final double startingAmount;
    private int numberOfYears;
    private final double returnRate;
    private final double yearlyIncrease;
    private double totalInvestment;
    private int[] years;
    private double[] values;


    public CalculateInvestment(double startingAmount,
                               int numberOfYears,
                               double returnRate,
                               double yearlyIncrease){
        this.startingAmount = startingAmount;
        this.numberOfYears = numberOfYears;
        this.returnRate = returnRate;
        this.yearlyIncrease = yearlyIncrease;
        this.totalInvestment = getFinalAmount(this.totalInvestment);
    }

    public double getFinalAmount(double totalInvestment){
        this.years = new int[numberOfYears];
        this.values = new double[numberOfYears];
        totalInvestment += startingAmount;
        for(int i = 0; i < numberOfYears; i++){
            years[i] = i;
            values[i] = totalInvestment;
            double interest = totalInvestment * (returnRate / 100);
            if(i >= numberOfYears - 1) break; //only add totalInvestment until array is complete
            totalInvestment += interest;
            totalInvestment += yearlyIncrease;
        }
        return Math.round(totalInvestment * 100.0) / 100.0;
    }

    //create and get chart panel
    public JPanel getChartPanel(){
        return new SetChart(years, values);
    }

    //outputs the final amount and some extra info to the output window from setFrame class
    public void outputString(JTextPane outputWindow){
        double interest = this.totalInvestment - this.startingAmount;
        double addedAmount = this.yearlyIncrease * this.numberOfYears;
        interest -= addedAmount;
        outputWindow.setText("Final Amount: $" + Math.round(this.totalInvestment * 100.0) / 100.0
                + "\n\nInitial Balance: $" + Math.round(this.startingAmount * 100.0) / 100.0
                + "\nTotal Amount Added: $" + Math.round(addedAmount * 100.0) / 100.0
                + "\nTotal Interest Earned: $" + Math.round(interest * 100.0) / 100.0
                + "\nTotal Years: " + --this.numberOfYears
                );
    }

}
