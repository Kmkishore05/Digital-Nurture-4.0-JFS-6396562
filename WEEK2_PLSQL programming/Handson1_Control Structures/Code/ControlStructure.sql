
CREATE TABLE CustomersX (
    CustomerID INT PRIMARY KEY,
    Name VARCHAR(100),
    DOB DATE,
    Balance INT,
    LastModified DATE,
    IsVIP VARCHAR(5)
);


CREATE TABLE Accounts1 (
    AccountID INT PRIMARY KEY,
    CustomerID INT,
    AccountType VARCHAR(20),
    Balance INT,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES CustomersX(CustomerID)
);


CREATE TABLE Transactions1 (
    TransactionID INT PRIMARY KEY,
    AccountID INT,
    TransactionDate DATE,
    Amount INT,
    TransactionType VARCHAR(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts1(AccountID)
);

CREATE TABLE LoansX (
    LoanID INT PRIMARY KEY,
    CustomerID INT,
    LoanAmount INT,
    InterestRate INT,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES CustomersX(CustomerID)
);

-- 
CREATE TABLE Employees2 (
    EmployeeID INT PRIMARY KEY,
    Name VARCHAR(100),
    Position VARCHAR(50),
    Salary INT,
    Department VARCHAR(50),
    HireDate DATE
);

-- I
INSERT INTO CustomersX VALUES (1, 'John Doe', TO_DATE('1950-11-29', 'YYYY-MM-DD'), 1000, SYSDATE, NULL);
INSERT INTO CustomersX VALUES (2, 'Jane Smith', TO_DATE('1970-06-23', 'YYYY-MM-DD'), 15000, SYSDATE, NULL);
INSERT INTO CustomersX VALUES (3, 'Adam West', TO_DATE('1980-03-15', 'YYYY-MM-DD'), 20000, SYSDATE, NULL);

INSERT INTO Accounts1 VALUES (1, 1, 'Savings', 1000, SYSDATE);
INSERT INTO Accounts1 VALUES (2, 2, 'Checking', 1500, SYSDATE);
INSERT INTO Accounts1 VALUES (3, 3, 'Savings', 5000, SYSDATE);

INSERT INTO Transactions1 VALUES (1, 1, SYSDATE, 200, 'Deposit');
INSERT INTO Transactions1 VALUES (2, 2, SYSDATE, 300, 'Withdrawal');
INSERT INTO Transactions1 VALUES (3, 3, SYSDATE, 1000, 'Deposit');

INSERT INTO LoansX VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));
INSERT INTO LoansX VALUES (2, 2, 7000, 7, SYSDATE, ADD_MONTHS(SYSDATE, 24));
INSERT INTO LoansX VALUES (3, 3, 8000, 6, SYSDATE, ADD_MONTHS(SYSDATE, 1)); 

INSERT INTO Employees2 VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('1989-05-15', 'YYYY-MM-DD'));
INSERT INTO Employees2 VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('1977-05-15', 'YYYY-MM-DD'));

-- Scenario 1: Reduce interest rate for customers older than 60
BEGIN
  FOR rec IN (
    SELECT l.LoanID, c.DOB 
    FROM LoansX l JOIN CustomersX c ON l.CustomerID = c.CustomerID
  ) LOOP
    IF MONTHS_BETWEEN(SYSDATE, rec.DOB)/12 > 60 THEN
      UPDATE LoansX SET InterestRate = InterestRate - 1 WHERE LoanID = rec.LoanID;
    END IF;
  END LOOP;
END;
/

-- Scenario 2: Mark VIPs
BEGIN
  FOR rec IN (SELECT CustomerID, Balance FROM CustomersX) LOOP
    IF rec.Balance > 10000 THEN
      UPDATE CustomersX
      SET IsVIP = 'TRUE'
      WHERE CustomerID = rec.CustomerID;
    END IF;
  END LOOP;
END;
/

-- Scenario 3: Reminders for loans ending soon
BEGIN
  FOR rec IN (
    SELECT CustomerID, LoanID, EndDate 
    FROM LoansX 
    WHERE EndDate BETWEEN SYSDATE AND SYSDATE + 30
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder sent to Customer ID: ' || rec.CustomerID || 
                         ' for Loan ID: ' || rec.LoanID || 
                         ' (End Date: ' || TO_CHAR(rec.EndDate, 'YYYY-MM-DD') || ')');
  END LOOP;
END;
/
SELECT * FROM CustomersX JOIN LoansX ON CustomersX.CustomerID = LoansX.CustomerID;
SELECT * FROM Accounts1 WHERE AccountType = 'Savings';
SELECT * FROM Employees2;