import java.io.*;
import java.util.*;

public final class Data {

    private List<Customer> customerList = new ArrayList<>();
    private int i = 0;

    public Data() {
        try {
            readUsersFromFile();
            i++;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private final void readUsersFromFile() throws IOException {
        try {
            Scanner scanner = new Scanner(new FileReader(new File("customersData.txt")));
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length == 9 && findCustomer(data[6].trim()) == null) {
                            customerList.add(new Customer(data[0].trim(), data[1].trim(),
                            data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), data[6].trim(), data[7].trim(), Double.parseDouble(data[8].trim())));
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new IOException("Can not open file data.txt :-(");
        }
    }
    public Customer findCustomer(String login) {
        for (Customer customer : customerList) {
            if (customer.getLogin().equals(login)) {
                return customer;
            }
        }
        return null;
    }
    public void addNewCustomer(String name, String surname,             // here only adding without validation
                               String phoneNumber, String address,      // in register page check if:
                               String emailAddress, String PESEL_ID,    // 1. The same user exists
                               String login, String password, Double balance) throws IOException{         // 2. Two times correct password
        this.customerList.add(new Customer(name, surname, phoneNumber, address, emailAddress, PESEL_ID, login, password, balance));
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("customersData.txt"), true));){
            bufferedWriter.write(name + "," + surname + "," +
                    phoneNumber + "," + address + "," + emailAddress + "," + PESEL_ID + "," + login + "," +
                    password + "," + balance);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public void changeField () throws IOException {

        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("customersData.txt"));
                ) {

                StringBuilder builder = new StringBuilder();
                for (Customer currentCustomer : customerList) {
                    builder.append(currentCustomer.getName()).append(",");
                    builder.append(currentCustomer.getSurname()).append(",");
                    builder.append(currentCustomer.getPhoneNumber()).append(",");
                    builder.append(currentCustomer.getAddress()).append(",");
                    builder.append(currentCustomer.getEmailAddress()).append(",");
                    builder.append(currentCustomer.getPESEL_ID()).append(",");
                    builder.append(currentCustomer.getLogin()).append(",");
                    builder.append(currentCustomer.getPassword()).append(",");
                    builder.append(currentCustomer.getBalance());
                    builder.append("\n");
                    bufferedWriter.newLine();
                    bufferedWriter.write(builder.toString());
                }


        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public void writeInputInfo(Transaction transaction, String amountOfMoney, TransactionsTypes type, Customer customer) throws IOException {
        try (
                var writer = new BufferedWriter(new FileWriter("infobase.txt", true));
                ){
            writer.write("==============================================================================");
            writer.newLine();
            writer.write("User: " + customer.getLogin() + "   Surname: " + customer.getSurname() + "   Name: " + customer.getName());
            writer.newLine();
            writer.write("Transaction type: " + type.name() + "  Description: " + type.getDescription() + "   Value: " + amountOfMoney);
            writer.newLine();
            writer.write("Transaction ID: " + transaction.getTRANSACTION_ID() );
            writer.newLine();
            writer.write("==============================================================================");
        } catch (IOException e) {
            throw new IOException("Can not open file infobase.txt");
        }

    }

    public Customer findCustomerByPhone(String phoneNumber) {

        for (Customer customer : customerList) {
            String customerPhoneNumber = customer.getPhoneNumber();
            customerPhoneNumber = customerPhoneNumber.replaceAll("_", "");
            customerPhoneNumber = customerPhoneNumber.replaceAll(" ", "");
            customerPhoneNumber = customerPhoneNumber.replaceAll("-", "");
            if (customerPhoneNumber.equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }
    public List<Customer> getCustomerList() {
        return customerList;
    }


}
