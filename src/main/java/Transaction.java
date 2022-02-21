public class Transaction {
    private final String TRANSACTION_ID;
    private double value;
    private boolean accepted;
    private TransactionsTypes transactionType;

    public Transaction(String TRANSACTION_ID, double value, TransactionsTypes transactionType) {
        this.TRANSACTION_ID = TRANSACTION_ID;
        this.value = value;
        this.transactionType = transactionType;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    public double getValue() {
        return value;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public TransactionsTypes getTransactionType() {
        return transactionType;
    }
}
