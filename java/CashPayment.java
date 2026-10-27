public class CashPayment implements Payment {
    @Override public boolean pay(double amount){ return true; }
    @Override public String getMethodName(){return "CASH";}
}
