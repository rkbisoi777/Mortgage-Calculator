
import java.text.NumberFormat;
import java.util.Scanner;

public class Main
{
    public static void main(String args[]){
        int principal = 0;
        float interestAnnual =0;
        byte period = 0;
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        principal = (int) readNumber("Principal : ",1000,1000000);
        interestAnnual = (float) readNumber("Annual Interest Rate : ",1,30);
        period = (byte) readNumber("Period : ",1,30);
        printMortgage(principal, interestAnnual, period, currency);
        printPaymentSchedule(principal, interestAnnual, period, currency);
    }

    private static void printMortgage(int principal, float interestAnnual, byte period, NumberFormat currency) {
        double Mortgage = calculateMortgage(principal, interestAnnual, period);
        System.out.println("MORTGAGE");
        System.out.println("-------------------------");
        System.out.println("Monthly Payment : "+ currency.format(Mortgage));
    }

    private static void printPaymentSchedule(int principal, float interestAnnual, byte period, NumberFormat currency) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("---------------------------");
        for (byte month = 1; month<=12* period; month++){
            double payments = paymentSchedule(principal, interestAnnual, period,month);
            System.out.println(currency.format(payments));
        }
    }

    public static double readNumber(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true){
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >=min && value<=max)
                break;
            System.out.println("Enter a value between "+min+" and "+max);
        }
        return value;
    }

    public static double calculateMortgage(
            int principal,
            float interestAnnual,
            byte period){
        final double TOTAL_MONTHS = 12;
        final double TOTAL_PERCENT = 100;
        double interestMonth = interestAnnual/TOTAL_PERCENT/TOTAL_MONTHS;
        double upper = interestMonth*Math.pow(1+interestMonth,period);
        double lower = Math.pow(1+interestMonth,period)-1;
        double mortgage = principal*(upper/lower);
        return mortgage;
    }

    public static double paymentSchedule(int principal, float interestAnnual, byte period, byte numberOfPaymentsMade) {
        final double TOTAL_MONTHS = 12;
        final double TOTAL_PERCENT = 100;
        double balance = 0;
        double interestMonth = interestAnnual / TOTAL_PERCENT / TOTAL_MONTHS;
        double x = Math.pow(1 + interestMonth, TOTAL_MONTHS * period);
        balance = (principal * (x - Math.pow(1 + interestMonth, numberOfPaymentsMade))) / (x - 1);
        return balance;
    }
 }
