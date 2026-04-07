# 💳 Banking Transaction Analyzer (Java Swing)

A simple Java Swing application to manage and analyze banking transactions.

---

## 🚀 Features
- Add transactions (Deposit / Withdraw)
- Auto-generated Transaction ID
- Input validation with exception handling
- Display transactions > ₹10000
- Sort transactions (Ascending & Descending)
- Show current balance

---

## 🧠 Concepts Used
- **OOP**: `Transaction` class  
- **Data Structure**: `ArrayList` (dynamic storage & sorting)  
- **Swing UI**: JFrame, JTextField, JCheckBox, JButton  
- **Exception Handling**: `try-catch`, `throw`, `throws`  
- **String Handling**: `substring()`  
- **Sorting**:
  - Lambda Comparator (Ascending)
  - Non-Lambda Comparator (Descending)

---

## ⚠️ Exceptions Handled
- Invalid amount → `NumberFormatException`
- Negative amount → `IllegalArgumentException`
- No type selected → Custom `Exception`

---

## ▶️ How to Run
```bash
javac BankingAnalyzer.java
java BankingAnalyzer
