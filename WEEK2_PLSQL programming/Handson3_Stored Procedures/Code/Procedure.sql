
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


CREATE TABLE Employees2 (
    EmployeeID INT PRIMARY KEY,
    Name VARCHAR(100),
    Position VARCHAR(50),
    Salary INT,
    Department VARCHAR(50),
    HireDate DATE
);


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


-- Procedure 1: Process monthly interest
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
  FOR rec IN (
    SELECT AccountID, Balance 
    FROM Accounts1 
    WHERE AccountType = 'Savings'
  ) LOOP
    UPDATE Accounts1
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountID = rec.AccountID;
  END LOOP;
END;
/

-- Procedure 2: Update salary with department-wise bonus
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
  p_Department IN VARCHAR2,
  p_BonusPercent IN NUMBER
) IS
BEGIN
  UPDATE Employees2
  SET Salary = Salary + (Salary * p_BonusPercent / 100)
  WHERE Department = p_Department;
END;
/

-- Procedure 3: Transfer funds between accounts
CREATE OR REPLACE PROCEDURE TransferFunds (
  p_SourceAccountID IN INT,
  p_TargetAccountID IN INT,
  p_Amount IN INT
) IS
  v_SourceBalance INT;
BEGIN

  SELECT Balance INTO v_SourceBalance 
  FROM Accounts1 
  WHERE AccountID = p_SourceAccountID FOR UPDATE;


  IF v_SourceBalance >= p_Amount THEN

    UPDATE Accounts1
    SET Balance = Balance - p_Amount
    WHERE AccountID = p_SourceAccountID;

    UPDATE Accounts1
    SET Balance = Balance + p_Amount
    WHERE AccountID = p_TargetAccountID;

    DBMS_OUTPUT.PUT_LINE('Transfer successful.');
  ELSE
    DBMS_OUTPUT.PUT_LINE('Insufficient funds. Transfer failed.');
  END IF;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('One of the accounts does not exist.');
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END;
/

BEGIN
  ProcessMonthlyInterest;
  UpdateEmployeeBonus('IT', 10);
  TransferFunds(1, 2, 300);
END;
/

SELECT * FROM Accounts1;
SELECT * FROM Employees2;