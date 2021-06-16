import java.text.NumberFormat;
import java.util.Scanner;

public class Mortgage {
    private int minPrincipal;
    private int maxPrincipal;
    private int minAnnualInterest;
    private int maxAnnualInterest;
    private int minPeriod;
    private int maxPeriod;
    private int totalMonth = 12;
    private int totalPercent = 100;

    public Mortgage(){
        setMinMaxPrincipal(1000,1000000);
        setMinMaxAnnualInterest(1,30);
        setMinMaxPeriod(1,30);
    }

    NumberFormat currency = NumberFormat.getCurrencyInstance();

    public void printMortgage(
            int principal,
            float interestAnnual,
            byte period
    ){
        principal = (int) readNumber("Principal : ",minPrincipal,maxPrincipal);
        interestAnnual = (float) readNumber("Annual Interest Rate : ",minAnnualInterest,maxAnnualInterest);
        period = (byte) readNumber("Period : ",minPeriod,maxPeriod);
        printMonthlyMortgage(principal, interestAnnual,  period, currency);
        printPaymentSchedule(principal, interestAnnual, period, currency);
    }

    private void printMonthlyMortgage(
            int principal,
            float interestAnnual,
            byte period,
            NumberFormat currency
    ) {
        double Mortgage = calculateMortgage(principal, interestAnnual, period);
        System.out.println("MORTGAGE");
        System.out.println("-------------------------");
        System.out.println("Monthly Payment : "+ currency.format(Mortgage));
    }

    private void printPaymentSchedule(
            int principal,
            float interestAnnual,
            byte period,
            NumberFormat currency
    ) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("---------------------------");
        for (byte month = 1; month<=totalMonth* period; month++){
            double payments = paymentSchedule(principal, interestAnnual, period,month);
            System.out.println(currency.format(payments));
        }
    }

    public double readNumber(
            String prompt,
            double min,
            double max
    ){
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

    public double calculateMortgage(
            int principal,
            float interestAnnual,
            byte period
    ){
        double interestMonth = interestAnnual/totalPercent/totalMonth;
        double upper = interestMonth*Math.pow(1+interestMonth,period);
        double lower = Math.pow(1+interestMonth,period)-1;
        double mortgage = principal*(upper/lower);
        return mortgage;
    }

    public double paymentSchedule(
            int principal,
            float interestAnnual,
            byte period,
            byte numberOfPaymentsMade
    ) {
        double interestMonth = interestAnnual / totalPercent / totalMonth;
        double x = Math.pow(1 + interestMonth, totalMonth * period);
        double balance = (principal * (x - Math.pow(1 + interestMonth, numberOfPaymentsMade))) / (x - 1);
        return balance;
    }

    public void setMinMaxPrincipal(
            int minPrincipal,
            int maxPrincipal
    ){
        if(minPrincipal <= 0){
            throw new IllegalArgumentException("Minimum Principal cannot be 0.");
        }
        this.minPrincipal = minPrincipal;
        this.maxPrincipal = maxPrincipal;
    }
    public void setMinMaxAnnualInterest(
            int minAnnualInterest,
            int maxAnnualInterest
    ){
        if(minAnnualInterest <=0){
            throw new IllegalArgumentException("Minimum Annual Interest cannot be 0.");
        }
        this.minAnnualInterest = minAnnualInterest;
        this.maxAnnualInterest = maxAnnualInterest;
    }
    public void setMinMaxPeriod(
            int minPeriod,
            int maxPeriod
    ){
        if(minAnnualInterest <=0){
            throw new IllegalArgumentException("Minimum Annual Interest cannot be 0.");
        }
        this.minPeriod = minPeriod;
        this.maxPeriod = maxPeriod;
    }
}

