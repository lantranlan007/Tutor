package on.demand;

import java.util.Scanner;

public class Exercise02_21 {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter investment amount: ");

        double investmentAmount = input.nextDouble();

        System.out.println("Enter annual interets rate in percentage: ");

        double annualInterestRate = input.nextDouble();

        double monthlyInterestRate = annualInterestRate / 1200;

        System.out.println("Enter number of years: ");

        double years = input.nextDouble();
      	
        double futureInvestmentValue = (investmentAmount) * Math.pow( 1 +  monthlyInterestRate, years * 12);

        System.out.println("Future value is " + "$" + futureInvestmentValue);
    }

}
