# Mobile App Test Automation - Monefy Android App

## Tech Stack
- **Appium**: Chosen for mobile test automation as it supports both **Android** and **iOS**, allowing you to write tests once and run them across multiple platforms. Appium works with various languages, including **Kotlin**, making it an ideal choice for Android automation.
- **JUnit**: A widely used **testing framework** for Java and Kotlin that integrates well with **Gradle**, provides detailed test reports, and supports annotations like `@Before` and `@After`.
- **Kotlin**: A modern language that offers concise syntax and full interoperability with **Java** (which is used in Appium's core). Kotlin was chosen because it’s becoming the standard for Android development, and it integrates smoothly with **Appium** and **JUnit**.

## Approach:

-Creating a base class AppiumSetup that handles the initialization and cleanup of the Appium driver.
-Writing test classes like MoneyfyTest that extend AppiumSetup, ensuring all tests share a common setup and teardown.
-Storing view IDs and XPaths in the MoneyfyViewIds.kt file to keep tests clean, maintainable, and easy to update.

## Let’s set up this project together!

### **1. Install the Android SDK**
You must have the Android SDK installed. You can download it here:  
[Android SDK](https://developer.android.com/studio).

After downloading and installing the SDK, make sure to set up the **ANDROID_HOME** environment variable to point to the location of the SDK.

---

### **2. Install Appium**
You also need to download Appium. You can get it here:  
[Appium](https://appium.io/).

Alternatively, if you have **npm** installed, you can install Appium globally by running the following command:

```bash
npm install -g appium
```

---
### Setting Up and Running the Emulator

- Open AVD Manager:
Go to Tools > AVD Manager in Android Studio.

-Create Virtual Device:
Click Create Virtual Device, select a hardware profile (e.g., Pixel 4), and choose an appropriate system image.

-Start Emulator:
After the device creation, click Play to launch the emulator.

-Run the Project on the Emulator:
Click the Run button in Android Studio (green play icon) and select the running emulator.

-Check Emulator Status:

`adb devices`

---

### Install AppiumInspector to get elements.

-Then launch AppiumInspector and enter the following parameters:

`“platformName” : “Android”
“automationName”: “UiAutomator2”`

`Also, set up the remote path with: /wd/hub`

-And click on start session.
(If you click on any element, you can get the resource ID of that element.)

---


### **3. Create a New Project**

Create a project in **Android Studio**, select **Kotlin** as the language and **Gradle** as the build tool.

---

### **4. Add Dependencies**
In the `build.gradle.kts` file, add the following dependencies to include Appium and JUnit for test automation:

```kotlin
dependencies {
    // Appium dependency
    testImplementation("io.appium:java-client:8.5.1")

    // JUnit 4 dependency for testing framework
    testImplementation("junit:junit:4.13.2")
}
```

Sync your Gradle project by clicking **"Sync Now"** in Android Studio.

---

### **5. Create the `AppiumSetup` Class**
Now we are creating a new Kotlin class called `AppiumSetup` in the `qa` folder.  
The purpose of this class is to set up which device and which application we want to test. It will act as the base class for all test classes.

#### **Key Capabilities to Define:**
1. **App Package**: The package name of the app.
2. **Activity Name**: The full path to the activity you want to test.  
   You can get both by running:
   ```bash
   adb logcat | grep LAUNCHER
   ```
3. **Automation Name**: The automation engine Appium is using (e.g., `UiAutomator2`).
4. **Platform Name**: The mobile OS platform (e.g., `Android`).

---

### **6. Example of `AppiumSetup` Class**
Here’s an example of how to set up the Appium driver and capabilities:

```kotlin
package com.qa.n26

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.After
import org.junit.Before
import java.net.URL
import java.util.logging.Level

open class AppiumSetup {

    lateinit var driver: AppiumDriver

    @Before
    fun setup() {
        val caps = DesiredCapabilities()
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2")
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.monefy.app.lite")
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.monefy.activities.main.MainActivity")
        caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true)

        driver = AndroidDriver(URL("http://0.0.0.0:4723/wd/hub"), caps)
        driver.setLogLevel(Level.ALL)
    }

    @After
    fun tearDown() {
        driver.quit()
    }
}
```

---

### **7. Define View IDs**
To simplify element interactions, create a file called `MonefyViewIds.kt` to store all resource IDs and XPaths.  
For example:

```kotlin
enum class MonefyViewIds(val id: String? = null, val xpath: String? = null) {
    TRANSFER_BUTTON(id = "com.monefy.app.lite:id/transfer_menu_item"),
    INCOME_BUTTON(id = "com.monefy.app.lite:id/income_button_title"),
    EXPENSE_BUTTON(id = "com.monefy.app.lite:id/expense_button_title"),
    BALANCE_AMOUNT(id = "com.monefy.app.lite:id/balance_amount")
}
```

---

### **8. Write Your First Test**
Create a test class (e.g., `MoneyfyTest`) and write your test cases.  

---

### **9. Run the Tests**
1. Ensure your device is connected and recognized by ADB:
   ```bash
   adb devices
   ```

2. Start the Appium server:
   ```bash
   appium
   ```

3. Run the tests using Gradle:
   ```bash
   ./gradlew test
   ```

---

### **10. Generate Test Reports**
JUnit automatically generates test reports after each run. You can find them in the following directory:
```
./build/reports/tests/test/classes/
```

---

### **11. Additional Notes**

- **XPath Usage in Mobile App Test Automation:**
  
    **It's generally not recommended to rely on **XPath** for locating elements, as it can be slower and less reliable. However, in cases 
   where IDs are not available or are dynamic, Path can be used as a fallback.


   -  **I use Appium Inspector to inspect the app's UI and extract element locators such as ID and XPath for test automation.**
 
   - **Open the cloned project in Android Studio and sync Gradle by clicking 'Sync Now' or manually via File > Sync Project with Gradle 
   Files.**
  

### Project Directory Structure
alaa-abdlrehim-n26/ Task2-Andriod

├── app/
│   ├── src/
│   │   └── main/
│   │       └── test/
│   │           └── java/
│   │               └── com/
│   │                   └── qa/
│   │                       └── n26/
│   │                           ├── MoneyfyViewIds.kt       # View IDs for the app
│   │                           └── MoneyfyTest.kt           # Test cases for app automation


-
### Key Files:
- **`MoneyfyViewIds.kt`**: Contains view IDs used in the app for test automation.
- **`MoneyfyTest.kt`**: Contains the test cases for the Monefy app automation using Appium.


