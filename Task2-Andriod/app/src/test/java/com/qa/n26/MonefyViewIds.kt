package com.qa.n26

enum class MonefyViewIds(val id: String? = null, val xpath: String? = null) {
    // Main Menu
    TRANSFER_BUTTON(id = "com.monefy.app.lite:id/transfer_menu_item"),
    INCOME_BUTTON(id = "com.monefy.app.lite:id/income_button_title"),
    EXPENSE_BUTTON(id = "com.monefy.app.lite:id/expense_button_title"),
    SHOW_KEYBOARD_FAB(id = "com.monefy.app.lite:id/show_keyboard_fab"),

    // Keyboard Buttons
    KEYBOARD_BUTTON_0(id = "com.monefy.app.lite:id/buttonKeyboard0"),
    KEYBOARD_BUTTON_1(id = "com.monefy.app.lite:id/buttonKeyboard1"),
    KEYBOARD_BUTTON_5(id = "com.monefy.app.lite:id/buttonKeyboard5"),
    KEYBOARD_BUTTON_6(id = "com.monefy.app.lite:id/buttonKeyboard6"),
    KEYBOARD_BUTTON_9(id = "com.monefy.app.lite:id/buttonKeyboard9"),

    TRANSFER_AMOUNT_TEXT(id = "com.monefy.app.lite:id/amount_text"),
    TRANSFER_AMOUNT_ADD_BUTTON(id = "com.monefy.app.lite:id/keyboard_action_button"),


    ACCOUNTS_BUTTON(id = "com.monefy.app.lite:id/accounts_imagebutton"),
    CASH_ACCOUNT_LABEL(id = "com.monefy.app.lite:id/cash_account_label"),

    BALANCE_AMOUNT(id = "com.monefy.app.lite:id/balance_amount"),

    OVER_FLOW(id = "com.monefy.app.lite:id/overflow"),
    TEXT_VIEW(xpath = "//android.widget.TextView[@text='0']"),
    BUTTON_2(xpath = "//android.widget.Button[@text='2']"),
    BUTTON_0(xpath = "//android.widget.Button[@text='0']"),
    CASH_ACCOUNT(xpath = "//android.widget.TextView[@text='Cash']"),
    CASH_BALANCE(xpath = "//android.widget.TextView[@text='$200.00']"),
    CATEGORY_VIEW(xpath = "(//android.widget.ImageView[@resource-id='com.monefy.app.lite:id/imageView'])[1]"),
    BALANCE_bUTTON(xpath = "//android.widget.TextView[@resource-id='com.monefy.app.lite:id/balance_amount']"),
    EXPENSE_CATEGORY(xpath = "(//android.widget.ImageView[@resource-id='com.monefy.app.lite:id/imageView'])[3]"),




}
