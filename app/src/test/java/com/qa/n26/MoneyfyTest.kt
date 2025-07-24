package com.qa.n26

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import java.util.logging.Level
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MoneyfyTest {

    lateinit var driver: AppiumDriver
    private val caps = DesiredCapabilities()

    @Before
    fun setup() {
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM)
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATOR)
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, PACKAGE_NAME)
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ACTIVITY_NAME)
        caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true)
        driver = AndroidDriver(URL(SERVER_URL), caps)
        driver.setLogLevel(Level.ALL)
    }

    @Test
    fun `transfer 200 from cash acc to payment acc and validate the balance `() {

        // Step 1: Open transfer screen and transfer 200 from cash acc
        driver.findElement(By.id(MonefyViewIds.TRANSFER_BUTTON.id)).click()
        driver.findElement(By.xpath(MonefyViewIds.TEXT_VIEW.xpath)).click()
        driver.findElement(By.xpath(MonefyViewIds.BUTTON_2.xpath)).click()
        driver.findElement(By.xpath(MonefyViewIds.BUTTON_0.xpath)).click()
        driver.findElement(By.xpath(MonefyViewIds.BUTTON_0.xpath)).click()

        // Step 2: Assert amount entered is 200
        val amountEntered = driver.findElement(By.id(MonefyViewIds.TRANSFER_AMOUNT_TEXT.id)).text
        assertEquals("200", amountEntered, "Amount input should be 200")

        // Step 3: Complete the transfer
        driver.findElement(By.id(MonefyViewIds.TRANSFER_AMOUNT_ADD_BUTTON.id)).click()
        Thread.sleep(3000) // wait for UI update

        // Step 4: Navigate to accounts
        driver.findElement(By.id(MonefyViewIds.OVER_FLOW.id)).click()
        val accountsBtn = driver.findElement(By.id(MonefyViewIds.ACCOUNTS_BUTTON.id))

        assertTrue(accountsBtn.isDisplayed, "Accounts button should be visible")
        assertTrue(accountsBtn.isEnabled, "Accounts button should be enabled")
        accountsBtn.click()

        // Step 5: Assert that the new "Cash" account with $-200.00 is visible
        val cashAccountLabel = driver.findElement(By.xpath(MonefyViewIds.CASH_ACCOUNT.xpath))
        val cashAccountBalance = driver.findElement(By.xpath(MonefyViewIds.CASH_BALANCE.xpath))

        assertTrue(cashAccountLabel.isDisplayed, "Cash account name should be visible")
        assertTrue(cashAccountBalance.isDisplayed, "Cash account balance should be visible")

        // Step 6: Assert on main balance

        val balanceText = driver.findElement(By.id(MonefyViewIds.BALANCE_AMOUNT.id)).text
        assertEquals("Balance $0.00", balanceText, "Balance should be $0.00 after transfer")

    }

    @Test
    fun `add new income of 60 and Verify balance`() {
        // Step 1: Tap the INCOME button
        driver.findElement(By.id(MonefyViewIds.INCOME_BUTTON.id)).click()
        Thread.sleep(2000)
        // Step 2: Input amount 60
        driver.findElement(By.id(MonefyViewIds.KEYBOARD_BUTTON_6.id)).click()
        driver.findElement(By.id(MonefyViewIds.KEYBOARD_BUTTON_0.id)).click()

        // Step 3: Assert amount input is 60
        Thread.sleep(3000)
        val amountEntered = driver.findElement(By.id(MonefyViewIds.TRANSFER_AMOUNT_TEXT.id)).text
        assertEquals("60", amountEntered, "Amount input should be 60")

        // Step 4: Tap "CHOOSE CATEGORY"
        driver.findElement(By.id(MonefyViewIds.TRANSFER_AMOUNT_ADD_BUTTON.id)).click()
        Thread.sleep(3000)
        driver.findElement(By.xpath(MonefyViewIds.CATEGORY_VIEW.xpath)).click()
        // Step 8: Assert the balance is updated to 60
        val balanceText = driver.findElement(By.xpath(MonefyViewIds.BALANCE_bUTTON.xpath)).text

        // Step 10: format the balance text
        val numericBalance = balanceText.replace("[^0-9.-]".toRegex(), "") // Keep only numbers, decimal, and minus sign

        val formattedBalance = "Balance $$numericBalance" 

        assertEquals("Balance $60.00", formattedBalance, "Balance should be 'Balance $60.00' after adding income")

    }
@Test
fun `add new expense of 50 and Verify balance`() {
    // Step 1: Tap the EXPENSE button
    driver.findElement(By.id(MonefyViewIds.EXPENSE_BUTTON.id)).click()

    // Step 2: Wait for the amount text field to be visible 
    Thread.sleep(2000)  // Wait for the UI to settle

    // Step 3: Enter the amount 50 into the field 
    driver.findElement(By.id(MonefyViewIds.KEYBOARD_BUTTON_5.id)).click()
    driver.findElement(By.id(MonefyViewIds.KEYBOARD_BUTTON_0.id)).click()

    // Step 4: Click the "Choose Category" button 
    driver.findElement(By.id(MonefyViewIds.TRANSFER_AMOUNT_ADD_BUTTON.id)).click()

    Thread.sleep(2000)  
    driver.findElement(By.xpath(MonefyViewIds.EXPENSE_CATEGORY.xpath)).click()
    val balanceText = driver.findElement(By.xpath(MonefyViewIds.BALANCE_bUTTON.xpath)).text
    val numericBalance = balanceText.replace("[^0-9.-]".toRegex(), "") // Keep only numbers, decimal, and minus sign

    val formattedBalance = "Balance $$numericBalance" // Example: Balance $210.00

    assertEquals("Balance $-50.00", formattedBalance, "Balance should be 'Balance $-50.00' after expense")
}


    @After
    fun tearDown() {
        driver.quit()
    }

    companion object {
        private const val PACKAGE_NAME = "com.monefy.app.lite"
        private const val ACTIVITY_NAME = "com.monefy.activities.main.MainActivity_"
        private const val AUTOMATOR = "UiAutomator2"
        private const val PLATFORM = "Android"
        private const val SERVER_URL = "http://0.0.0.0:4723/"
    }
}
