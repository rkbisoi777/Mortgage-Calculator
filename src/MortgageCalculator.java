public class MortgageCalculator {
    private int principal;
    private float interestAnnual;
    private byte period;
    private static int totalMonth = 12;
    private static int totalPercent = 100;

    public MortgageCalculator(int principal, float interestAnnual, byte period) {
        this.principal = principal;
        this.interestAnnual = interestAnnual;
        this.period = period;
    }

    public double calculateBalance(
            byte numberOfPaymentsMade
    ) {
        double interestMonth = getInterestMonth();
        double x = Math.pow(1 + interestMonth, getNumberOfPayments());
        double balance = (principal * (x - Math.pow(1 + interestMonth, numberOfPaymentsMade))) / (x - 1);
        return balance;
    }

    public double calculateMortgage(
    ){
        double interestMonth = getInterestMonth();
        double mortgage = principal*(
                interestMonth*Math.pow(1+interestMonth,period)
                /Math.pow(1+interestMonth,period)-1);
        return mortgage;
    }

    public double[] getRemainingBalances(){
        var balances = new double[getNumberOfPayments()];
        for (byte month = 1; month<=balances.length; month++) {
            balances[month - 1] = calculateBalance(month);
        }
        return balances;
    }

    private int getNumberOfPayments(){
        return totalMonth*period;
    }

    private float getInterestMonth() {
        return interestAnnual / totalPercent / totalMonth;
    }
}
