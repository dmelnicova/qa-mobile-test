package com.alfa.constans;

import com.alfa.utils.PropertiesUtil;

public interface IConstants {

    String CONFIG_FILE_PATH = "src/main/resources/_config.properties";
    String TESTDATA_FILE_PATH = "src/main/resources/_testdata.properties";
    String SELENIUM_URL = PropertiesUtil.getConfigValue("selenium_url");
    String APP_PATH = PropertiesUtil.getConfigValue("capabilities.app");

    long SHORT_TIMEOUT = 3;

    String PASSWORD_ATTRIBUTE = "password";
    String CHECKED_ATTRIBUTE = "checked";

    String PAGE_NAME_LOGIN = "[ LOGIN page ]:";
    String PAGE_NAME_LOGIN_SUCCESS = "[ LOGIN SUCCESS page ]:";

    String VALID_LOGIN_REGEX = "[a-zA-Z.,/'_ -]*";
    String VALID_PASSWORD_REGEX = "[a-zA-Z.,/'_ -]*";

    String LOGIN_LABEL = "Логин";
    String PASSWORD_LABEL = "Пароль";

    String LOGIN_PAGE_TITLE = "Вход в Alfa-Test";
    String LOGIN_SUCCESS_PAGE_TITLE = "Вход в Alfa-Test выполнен";

    String AUTORIZE_ERROR_MESSAGE = "Введены неверные данные";
}
