import javax.swing.*;

public class calculateInvestment {
    private final double startingAmount;
    private final int numberOfYears;
    private final double returnRate;
    private final double yearlyIncrease;
    private double totalInvestment;


    public calculateInvestment(double startingAmount,
                               int numberOfYears, double returnRate, double yearlyIncrease){
        this.startingAmount = startingAmount;
        this.numberOfYears = numberOfYears;
        this.returnRate = returnRate;
        this.yearlyIncrease = yearlyIncrease;
        this.totalInvestment = 0; //this variable will be changed to calculate the final amount
        getFinalAmount();
    }

    public void getFinalAmount(){
        totalInvestment += startingAmount;
        for(int i = 0; i < numberOfYears; i++){
            double interest = this.totalInvestment * (returnRate / 100);
            this.totalInvestment += interest;
            this.totalInvestment += yearlyIncrease;
        }
    }

    //outputs the final amount and some extra info to the output window from setFrame class
    public void outputString(JTextArea outputWindow){
        //Math.round used to make the output round to a single decimal place
        outputWindow.setText("Final Amount: $" + Math.round(this.totalInvestment * 100.0) / 100.0);
        outputWindow.append("\n\nInitial Balance: $" + Math.round(this.startingAmount * 100.0) / 100.0);
        double interest = this.totalInvestment - this.startingAmount;
        outputWindow.append("\nTotal Interest Earned: $" + Math.round(interest * 100.0) / 100.0);
        outputWindow.append("\nTotal Years: " + this.numberOfYears);


    }

}
