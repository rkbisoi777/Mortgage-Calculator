import java.text.NumberFormat;

public class Main
{
    public static NumberFormat currency = NumberFormat.getCurrencyInstance();
    public static void main(String[] args){
        int principal = (int) Console.readNumber("Principal : ",1000,1000000);
        float interestAnnual = (float) Console.readNumber("Annual Interest Rate : ",1,20);
        byte period = (byte) Console.readNumber("Period : ",1,30);
        var calculator = new MortgageCalculator(principal, interestAnnual, period);
        var report = new MortgageReport(calculator);
        report.printMonthlyMortgage(currency);
        report.printPaymentSchedule();
    }
 }
