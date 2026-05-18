CREATE DATABASE IF NOT EXISTS OnlineBanking;
USE OnlineBanking;

CREATE TABLE IF NOT EXISTS Customer (
    customerID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Account (
    accountID INT AUTO_INCREMENT PRIMARY KEY,
    accountType VARCHAR(50) NOT NULL,
    balance DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    customerID INT NOT NULL,
    CONSTRAINT fk_account_customer
        FOREIGN KEY (customerID) REFERENCES Customer(customerID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `Transaction` (
    transactionID INT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(12,2) NOT NULL,
    date VARCHAR(20) NOT NULL,
    description VARCHAR(255),
    accountID INT NOT NULL,
    CONSTRAINT fk_transaction_account
        FOREIGN KEY (accountID) REFERENCES Account(accountID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO Customer (customerID, username, password, firstName, lastName)
VALUES
    (1, 'bob', '1234', 'Bob', 'Smith')
ON DUPLICATE KEY UPDATE username = VALUES(username);

INSERT INTO Account (accountID, accountType, balance, customerID)
VALUES
    (1, 'Chequing', 1000.00, 1),
    (2, 'Savings', 2500.00, 1)
ON DUPLICATE KEY UPDATE balance = VALUES(balance);

INSERT INTO `Transaction` (transactionID, amount, date, description, accountID)
VALUES
    (1, 1000.00, '2026-01-01', 'Initial deposit', 1),
    (2, 2500.00, '2026-01-01', 'Initial deposit', 2)
ON DUPLICATE KEY UPDATE description = VALUES(description);
