public enum TransactionsTypes {
    WITHDRAWAL("Withdrawal of money"), INPUT("Money input");
    private String description;

     TransactionsTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
