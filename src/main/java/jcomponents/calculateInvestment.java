package jcomponents;

import customcolors.CustomColors;
import jcomponents.setChart;

import javax.swing.*;

public class calculateInvestment {
    private final double startingAmount;
    private final int numberOfYears;
    private final double returnRate;
    private final double yearlyIncrease;
    private double totalInvestment;
    private int[] years;
    private double[] values;


    public calculateInvestment(double startingAmount,
                               int numberOfYears, double returnRate, double yearlyIncrease){
        this.startingAmount = startingAmount;
        this.numberOfYears = numberOfYears;
        this.returnRate = returnRate;
        this.yearlyIncrease = yearlyIncrease;
        this.totalInvestment = 0; //this variable will be changed to calculate the final amount
        this.totalInvestment = getFinalAmount(this.totalInvestment);
    }

    public double getFinalAmount(double totalInvestment){
        this.years = new int[numberOfYears + 1];
        this.values = new double[numberOfYears + 1];

        totalInvestment += startingAmount;
        years[0] = 0;
        values[0] = this.startingAmount;
        for(int i = 0; i < numberOfYears; i++){
            years[i + 1] = i + 1;
            values[i + 1] = totalInvestment;
            double interest = totalInvestment * (returnRate / 100);
            totalInvestment += interest;
            totalInvestment += yearlyIncrease;
        }
        return Math.round(totalInvestment * 100.0) / 100.0;
    }

    public JPanel getChartPanel(){
        return new setChart(years, values);
    }

    //outputs the final amount and some extra info to the output window from setFrame class
    public void outputString(JTextArea outputWindow){
        //Math.round used to make the output round to a single decimal place
        outputWindow.setText("Final Amount: $" + Math.round(this.totalInvestment * 100.0) / 100.0);
        outputWindow.append("\n\nInitial Balance: $" + Math.round(this.startingAmount * 100.0) / 100.0);
        double interest = this.totalInvestment - this.startingAmount;
        double addedAmount = this.yearlyIncrease * this.numberOfYears;
        interest -= addedAmount;
        outputWindow.append("\nTotal Amount Added: $" + Math.round(addedAmount * 100.0) / 100.0);
        outputWindow.append("\nTotal Interest Earned: $" + Math.round(interest * 100.0) / 100.0);
        outputWindow.append("\nTotal Years: " + this.numberOfYears);


    }

}
