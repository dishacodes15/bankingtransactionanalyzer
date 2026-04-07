import java.awt.*;
import java.util.*;
import javax.swing.*;

// Transaction Class
class Transaction {
    String transactionId;
    String type;
    double amount;

    public Transaction(String transactionId, String type, double amount) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
    }

    public String toString() {
        return transactionId + " | " + type + " | ₹" + amount;
    }
}

// Non-Lambda Comparator
class AmountComparator implements Comparator<Transaction> {
    public int compare(Transaction t1, Transaction t2) {
        return Double.compare(t1.amount, t2.amount);
    }
}

// Main Class
public class BankingTransactionAnalyzer extends JFrame {

    private JTextField amountField;
    private JCheckBox depositCheck, withdrawCheck;
    private JTextArea outputArea;

    private ArrayList<Transaction> transactions = new ArrayList<>();
    private int txnCounter = 1000;
    private double balance = 0;

    public BankingTransactionAnalyzer() {
        setTitle("Banking Transaction Analyzer");
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        amountField = new JTextField(10);

        depositCheck = new JCheckBox("Deposit");
        withdrawCheck = new JCheckBox("Withdraw");

        // ✅ Ensure only one checkbox can be selected
        ButtonGroup group = new ButtonGroup();
        group.add(depositCheck);
        group.add(withdrawCheck);

        JButton addBtn = new JButton("Add Transaction");
        JButton showBtn = new JButton("Show > 10000");
        JButton sortBtn = new JButton("Sort Transactions");

        outputArea = new JTextArea(15, 40);

        add(new JLabel("Amount:"));
        add(amountField);
        add(depositCheck);
        add(withdrawCheck);
        add(addBtn);
        add(showBtn);
        add(sortBtn);
        add(new JScrollPane(outputArea));

        addBtn.addActionListener(e -> {
            try {
                addTransaction();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        showBtn.addActionListener(e -> showLargeTransactions());
        sortBtn.addActionListener(e -> sortTransactions());

        setVisible(true);
    }

    // Method with throws
    public void addTransaction() throws Exception {

        String amountText = amountField.getText();

        // substring usage
        String fakeInput = "TXN" + txnCounter + "|TYPE|" + amountText;
        String txnId = fakeInput.substring(0, fakeInput.indexOf("|"));
        String amountStr = fakeInput.substring(fakeInput.lastIndexOf("|") + 1);

        double amount;

        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid amount!");
        }

        // throw example
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive!");
        }

        String type;
        if (depositCheck.isSelected()) {
            type = "DEPOSIT";
            balance += amount;
        } else if (withdrawCheck.isSelected()) {
            type = "WITHDRAW";
            balance -= amount;
        } else {
            throw new Exception("Select a transaction type!");
        }

        Transaction txn = new Transaction(txnId, type, amount);
        transactions.add(txn);
        txnCounter++;

        outputArea.append("Added: " + txn + "\n");
        outputArea.append("Balance: ₹" + balance + "\n\n");

        amountField.setText("");
    }

    // Show transactions > 10000
    public void showLargeTransactions() {
        outputArea.append("\nTransactions > ₹10000:\n");

        for (Transaction t : transactions) {
            if (t.amount > 10000) {
                outputArea.append(t + "\n");
            }
        }
    }

    // Sorting (Ascending + Descending)
    public void sortTransactions() {

        // Lambda sort (Ascending)
        transactions.sort((t1, t2) -> Double.compare(t1.amount, t2.amount));

        outputArea.append("\nSorted Ascending:\n");
        for (Transaction t : transactions) {
            outputArea.append(t + "\n");
        }

        // Non-Lambda sort (Descending)
        Collections.sort(transactions, new AmountComparator().reversed());

        outputArea.append("\nSorted Descending:\n");
        for (Transaction t : transactions) {
            outputArea.append(t + "\n");
        }
    }

    public static void main(String[] args) {
        new BankingTransactionAnalyzer();
    }
}
