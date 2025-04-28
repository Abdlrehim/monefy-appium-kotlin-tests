# Exploratory Testing – Monefy App (iOS)

**Tester:** Alaa Abdlrehim  
**Device:** iPhone 12 Pro Max

---

## 1. Test Cases

### 1.1 Balance and Transactions

1. **Test Case: Verify that Balance Calculation is updated correctly when you have one account and when you have multiple accounts**

2. **Test Case: Verify that Balance Calculation is updated correctly when you have chosen all accounts View and when you choose only one account.**

3. **Test Case: Initial Account Balance Is Included in Calculations**

  **Steps:**
- Clear all existing accounts.
- Add a new account ("My Wallet") with an initial balance of €100.
- Add income of €30.
- Add an expense of €50.
- Check the balance of the account and the “All accounts” total.

  **Expected Result:**
- Account balance = €100 + €30 - €50 = €80.
- “All accounts” should also show €80.

   **Actual Result:**
- Account shows –€20.
- “All accounts” shows –€49.83.
- Initial balance is ignored.

  4. **Test Case: Verify that we can add a new account first thing.**
   - **Expected Result:** User should be able to create a new account with the currency that they want and a Balance.


5. **Test Case: Verify that income added to an account is reflected correctly in the account balance.**  
   - **Expected Result:** Balance should increase by the income amount.

6. **Test Case: Check that expenses are subtracted from the correct account’s balance.**  
   - **Expected Result:** Balance should decrease by the expense amount.

7. **Test Case: Verify that account transfers correctly adjust source and target account balances.**  
   - **Expected Result:** Source balance should decrease, and target should increase by the same amount.

8. **Test Case: Check that the sum of all transactions (income – expense + transfer) is consistent with the displayed account balance.**  
   - **Expected Result:** Displayed balance should match the transaction history calculation.

9. **Test Case: Verify that adding a new transfer from "Cash" to "Payment card" reduces "Cash" and increases "Payment card".**  
   - **Expected Result:** Cash decreases, payment card increases by the same value.

10. **Test Case: Check that the balance in "All accounts" reflects the correct net total of all accounts combined.**  
   - **Expected Result:** All account balances summed together match the total.

11. **Test Case: Verify that the "Included in the balance" toggle affects whether an account contributes to the overall balance.**  
   - **Expected Result:** Excluded accounts are not included in the “All accounts” balance.

12. **Test Case: Toggle OFF “Included in the Balance” on an Account**
  **Steps:**
- Add a new account ("Cash") with an initial balance of €100.
- Toggle OFF “Included in the balance.”
- Add income of €150 and expense of €60.
- Return to “All accounts” view and transaction list.

   **Expected Result:**
- The account's **balance** is not included in the “All accounts” total.
- **Income and expense transactions remain visible** in the main transaction list.
- The account is accessible, and data can be reviewed from within it.

13. **Test Case: Toggle ON “Included in the Balance” on an Account**
   **Steps:**
- Add a new account ("Cash") with an initial balance of €100.
- Toggle ON “Included in the balance.”
- Add income of €150 and expense of €60.
- Return to “All accounts” view.

    **Expected Result:**
- “All accounts” balance includes this account.
- All transactions are visible in the main transaction list.
- Balance calculation includes initial balance + income – expense.

  14. **Test Case: Verify that the User can't make a transfer without his accounts being different**
 
  15.  **Test Case: Verify that the user can switch between his accounts normally**
 
  16.  **Test Case: Verify that the User can delete any of his accounts and check the Balance after that.**
          **Expected Result:** The User should be able to delete any of their accounts.

 17.  **Test Case: Verify that the User can delete and edit any income or Expense.**
       **Expected Result:** The User should be able to delete and edit any income and expense that they added before successfully.

  

---

### 1.2 Date and Filter Features

11. Check that the filter by day, week, month, year, and custom date range updates the transaction view accordingly.  
    - **Expected Result:** Only transactions in the selected range are visible.

12. Verify that transactions are displayed based on the selected date filter.  
    - **Expected Result:** Transaction list updates based on filter type.

13. Check that rotating the device does not affect the UI or data integrity.  
    - **Expected Result:** App remains in portrait or rotates cleanly with no data loss.

---

### 1.3 Categories and Entry Flow

14. Verify that clicking on a category icon ("Food", "Sports") opens a new expense screen.  
    - **Expected Result:** New expense screen appears pre-filled with selected category.

15. Verify that clicking the “+” button on the main screen opens the "New income" screen.  
    - **Expected Result:** "New income" screen appears.

16. Verify that clicking the “–” button opens the "New expense" screen.  
    - **Expected Result:** "New expense" screen appears.

17. Check that the new categories created are listed and selectable.  
    - **Expected Result:** A new category will appear in the category list and can be tapped.

18. Check that category totals match the sum of their entries.  
    - **Expected Result:** Category displays the correct accumulated amount.

---

### 1.4 Settings and Language

19. Verify that changing the app language updates all text labels correctly.  
    - **Expected Result:** App text changes to the selected language.

20. Check that passcode protection prompts the setup or unlock flow.  
    - **Expected Result:** App navigates to passcode setup or input screen.

21. Verify that data sync and backup settings (Google Drive, Dropbox) navigate correctly.  
    - **Expected Result:** User is redirected to login/upgrade or setup flow.

22. Check that the currency selection lists all available currencies and updates the default currency.  
    - **Expected Result:** Currency is saved and used throughout the app.

---


## 2. Prioritization of Charters

### 2.1 Prioritized List

1. **Balance Calculation & Transaction Flow – High**  
2. **Transfers Between Accounts – High**  
3. **Navigation via Icons (+/– and Categories), Adding New Income, Adding Expense, and Checking Balance – High**  
4. **Performance of the App – High**  
5. **Currency and Language Switching – Medium**  
6. **Offline Mode – Low**

---

## 3. Risks and Mitigation

1. **Incorrect Balance Calculations**  
   - Mitigation: Add backend/unit tests for formulas.

2. **Transfers Impacting Net Balance Incorrectly**  
   - Mitigation: Ensure transfers are net-neutral.

3. **Data Loss**  
   - Mitigation: Save persistently with local/cloud options.

4. **Incorrect Post-Transaction State**  
   - Mitigation: Validate calculations on each transaction.

5. **Device/Screen Inconsistencies**  
   - Mitigation: Test UI responsiveness thoroughly.

6. **Crashes or Errors on Edge Cases**  
   - Mitigation: Add lifecycle and memory handling.

---

## 4. Bug Reports

### Bug 1: When I have 2 different accounts, and switched to have all Accounts View for my balance, the Balance calculation is wrong.
**Steps to Reproduce:**
- Click on the Add New Account button
- Choose the currency
- Add a new account with a 100 Visa
- Click on the add button
- Click another Acc2 with 100 cash
- Check Balance when you navigate to the Have all accounts option

**Expected:** Balance should be 200  
**Actual:** Balance is 0  
**Severity:** High  
**Priority:** High  
**Status:** Open

---

### Bug 2: When I have one account, the Balance calculation is not updated correctly when I select All Accounts View
**Steps to Reproduce:**
- Click on the Add New Account button
- Choose the currency
- Add a new account with a 100 Visa
- Click on the add button
- Add new income with 50
- Add a new expense of 50
- Check Balance when I select All Accounts View

**Expected:** Balance should be 100  
**Actual:** Balance is -49  
**Severity:** High  
**Priority:** High  
**Status:** Open

---

### Bug 3: After adding a new account, the account that I added is duplicated when I check my accounts
**Steps to Reproduce:**
- Click on the Add New Account button
- Choose the currency
- Add a new account with a 100 Visa
- Click on the add button

**Expected:** I should have only 1 account that I created  
**Actual:** My account is duplicated  
**Severity:** High  
**Priority:** High  
**Status:** Open

---

### Bug 4: Transfers don't affect the Overall Account Balance when I have all accounts View.
**Steps to Reproduce:**
- Add a new transfer, it doesn't update in the balance calculation.

**Expected:** Transfers should be updated in the Balance  
**Actual:** Balance doesn't update with the new transfer  
**Severity:** High  
**Priority:** High  
**Status:** Open

---

### Bug 5: Device Rotation Not Supported
**Expected:** Rotate or show lock  
**Actual:** Stuck in portrait  
**Severity:** Low  
**Priority:** Low  
**Status:** Open

---

### Bug 6: Income and expenses are not visible when adding a new Transaction with the toggle set OFF for “not included in Balance” transaction
**Steps:**
1. Add an account with “Included in balance” OFF.
2. Add income and expense.
3. Check the main transaction view.

**Expected:**  
- Income and expense should still appear in history  
- Only the balance is excluded from the totals

**Actual:**  
- Incomes and expenses have disappeared completely from the main view  

**Severity:** High  
**Priority:** High  
**Status:** Open
