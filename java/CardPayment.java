public class CardPayment implements Payment {
    private final String cardNumber;
    public CardPayment(String cardNumber){this.cardNumber=cardNumber;}
    @Override public boolean pay(double amount){ return cardNumber != null && cardNumber.replaceAll("\\D", "").length() == 16; }
    @Override public String getMethodName(){return "CARD";}
}
