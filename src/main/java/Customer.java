import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Customer {
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private String emailAddress;
    private final String PESEL_ID;
    private String login;
    private String password;
    private double balance = 0.0;
    private List<Transaction> transactionList = new ArrayList<>();

    public Customer(String name, String surname, String phoneNumber, String address, String emailAddress,
                    String PESEL_ID) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.PESEL_ID = PESEL_ID;
    }

    public Customer(String name, String surname, String phoneNumber, String address, String emailAddress,
                    String PESEL_ID, String login, String password, double balance) {
        this( name,  surname, phoneNumber,  address,  emailAddress,
                PESEL_ID);
        this.balance = balance;
        this.login = login;
        this.password = password;
    }

    public void makeTransaction(double value, TransactionsTypes type) {
        this.transactionList.add(new Transaction(UUID.randomUUID().toString(), value, type));
        if (type.equals(TransactionsTypes.INPUT)) {
            this.balance += value;
        } else if (type.equals(TransactionsTypes.WITHDRAWAL)) {
            this.balance -= value;
        }


    }

    public void makeTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public Customer (String name, String surname, String phoneNumber, String address,
                     String emailAddress, String PESEL_ID, String login, String password) {
        this(name,  surname,  phoneNumber, address, emailAddress,  PESEL_ID);
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPESEL_ID() {
        return PESEL_ID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(surname, customer.surname) && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(address, customer.address) && Objects.equals(emailAddress, customer.emailAddress) && Objects.equals(PESEL_ID, customer.PESEL_ID) && Objects.equals(login, customer.login) && Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, phoneNumber, address, emailAddress, PESEL_ID, login, password);
    }


}
