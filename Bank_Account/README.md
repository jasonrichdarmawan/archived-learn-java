### Documentation

- [ ] [Presentation](https://lnkd.in/gGJ36Pz)
- [x] [API Documentation](https://lnkd.in/gFYxHAs)

### To Do

- [x] BE: Auto create table if not exists
  - [x] user_login
  - [x] user_info
  - [x] transactions
  - [x] user_detail
  - [x] statements

- [x] BE: Endpoint
  - [x] Post `api/v1/register`
    - [x] INSERT INTO table `user_login`
      - [x] Hash the PIN in `column Hashed_PIN` with format `iterations:salt:hash`
    - [x] INSERT INTO table `user_info`
    - [x] INSERT INTO table `user_detail`
  - [x] Post `api/v1/login`
    - [x] Validate `storedhashed_pin` with `PIN` from @RequestBody
  - [x] Post `api/v1/token`
    - [x] Refresh Access Token
  - [x] Get `api/v1/account`
    - [x] Get Account Info By Account Number
  - [x] Get `api/v1/history`
    - [x] Get Transactions History By Start and End Date
  - [x] Post `api/v1/transaction`
    - [x] Post Transaction
      - [x] INSERT INTO table `transactions`
  - [x] Get `api/v1/balance`
    - [x] Get Account Balance
      - [x] SELECT Ending_Balance FROM table `statements` WHERE Account_Number=#{Account_Number} AND Month=#{PreviousMonth} AND YEAR=#{Year}
        - [x] IF NOT EXISTS, Ending Balance = 0
      - [x] Debit: SELECT Transaction_Value FROM table `transactions` WHERE Source=#{Account_Number}
      - [x] Credit: SELECT Transaction_Value FROM table `transactions` WHERE Destination=#{Account_Number}

- FE: View
  - [x] Register route: `/register`
  - [x] Login route: `/logout`
  - [x] Logout `button`
  - [x] Get Transactions History By Start and End Date route: `/history`
  - [ ] Transfer Betweeen Accounts route: `/transfer`
  - [ ] Get Account Balance route: `/balance`