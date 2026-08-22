# 📈 Stock Trading Platform

A **console-based Java Stock Trading Platform** that simulates a basic stock market and portfolio management system.

The project demonstrates **Object-Oriented Programming (OOP)** concepts along with **File I/O** for saving portfolio data.

---

## ✨ Features

* 📊 Display market data
* 💰 Buy stocks
* 📉 Sell stocks
* 📁 Track portfolio holdings
* 🧾 View transaction history
* 💵 Add money to account
* 📈 Calculate portfolio value
* 💹 Calculate profit/loss and return
* 💾 Save portfolio data using File I/O
* ☕ Designed using Object-Oriented Programming

---

## 🧠 OOP Concepts Used

### 🔐 Encapsulation

Private fields with public methods are used in:

* `Stock`
* `User`
* `Portfolio`
* `Transaction`

### 🧩 Composition

A `User` has:

* One `Portfolio`
* Multiple `Transactions`

### 🎯 Abstraction

Trading logic is separated into different classes:

* `Market`
* `TradingPlatform`
* `FileManager`

---

## 📂 Project Structure

```text
StockTradingPlatform/
│
├── src/
│   ├── Main.java
│   ├── Stock.java
│   ├── User.java
│   ├── Portfolio.java
│   ├── Transaction.java
│   ├── Market.java
│   ├── TradingPlatform.java
│   └── FileManager.java
│
├── data/
│
└── README.md
```

---

## ⚙️ Requirements

Before running the project, make sure you have:

* ☕ **Java JDK 17 or newer**
* 💻 **IntelliJ IDEA / VS Code / Eclipse**

---

## 🚀 How to Run in IntelliJ IDEA

1. Open **IntelliJ IDEA**.
2. Select **Open**.
3. Open the `StockTradingPlatform` folder.
4. Mark the `src` folder as **Sources Root** if IntelliJ does not detect it automatically.
5. Open `Main.java`.
6. Click the **Run ▶️** button.

---

## 💰 Initial Account

The demo user starts with:

### 💵 `$10,000.00`

---

## 🔄 Example Workflow

1. Select `1` to view market data.
2. Select `2` to buy a stock.
3. Select `4` to view your portfolio.
4. Select `3` to sell a stock.
5. Select `5` to view transactions.
6. Select `7` to view performance.
7. Select `8` to save and exit.

---

## 💾 File I/O

When the program exits using **option 8**, portfolio information is saved to:

```text
data/portfolio.txt
```

---

## 🔮 Future Improvements

The project can be improved by adding:

* 🔑 Login and registration system
* 👥 Multiple users
* 📡 Real-time stock API
* 🗄️ Database integration using MySQL
* 🖥️ GUI using JavaFX
* 📊 Charts for portfolio performance
* 🛑 Stop-loss and limit orders
* 💰 Dividend tracking
* ⭐ Watchlist

---

## 🛠️ Technologies Used

* ☕ Java
* 🧠 Object-Oriented Programming
* 💾 File I/O
* 🖥️ Console-based Application

---

## 📌 Project Overview

This project provides a simple simulation of a stock trading environment where users can manage their money, buy and sell stocks, monitor their portfolio, view transaction history, and calculate their overall performance.

It is designed as a practical demonstration of **Java programming, OOP concepts, portfolio management, and file handling**.

---

## 👨‍💻 Author
Md Faizan Hassan <br>
Java Developer / Computer Science & Engineering Student

**Stock Trading Platform – Java Project**
