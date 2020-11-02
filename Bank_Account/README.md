### To Do

- [ ] BE: Auto create table if not exists
  - [x] user_login
  - [x] user_info
  - [ ] transactions
  - [x] user_detail
  - [ ] statements

- [ ] BE: Endpoint
  - [x] Post `api/v1/register`
    - [x] INSERT INTO table `user_login`
      - [x] Hash the PIN in `column Hashed_PIN` with format `iterations:salt:hash`
    - [x] INSERT INTO table `user_info`
    - [x] INSERT INTO table `user_detail`
  - [x] Post `api/v1/login`
    - [x] Validate `storedhashed_pin` with `PIN` from @RequestBody
  - [x] Post `api/v1/token`
    - [x] Refresh Access Token
  - [ ] Get `api/v1/account`
    - [ ] Get Account Info By Account Number
  - [ ] Get `api/v1/history`
    - [ ] Get Transactions History By Start and End Date
  - [ ] Post `api/v1/transaction`
    - [ ] Post Transaction
      - [ ] INSERT INTO table `transactions`
  - [ ] Get `api/v1/balance`
    - [ ] Get Account Balance
      - [ ] SELECT Ending_Balance FROM table `statements` WHERE Account_Number=#{Account_Number} AND Month=#{PreviousMonth} AND YEAR=#{Year}
        - [ ] IF NOT EXISTS, Ending Balance = 0
      - [ ] Debit: SELECT Transaction_Value FROM table `transactions` WHERE Source=#{Account_Number}
      - [ ] Credit: SELECT Transaction_Value FROM table `transactions` WHERE Destination=#{Account_Number}

- FE: View
  - [ ] Register
  - [ ] Login
  - [ ] Logout
  - [ ] Get Transactions History By Start and End Date
  - [ ] Transfer Betweeen Accounts
  - [ ] Get Account Balance