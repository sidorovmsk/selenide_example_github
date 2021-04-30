package github.core;

import com.codeborne.selenide.Configuration;
import github.utils.ConfigReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@ExtendWith(CoreTestWatcher.class)
public class TestCore {
    public static ConfigReader configReader = new ConfigReader("config.properties");
    private final String mainAppUrl = configReader.getParameter("system.url");

    @BeforeEach
    void setUpEach() {
        try {   //проблема со скриншотами в tearDownEach -> выключаю браузер здесь
            getWebDriver().close();
        } catch (Exception ignored) {
        }
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        ChromeOptions options = new ChromeOptions();
        options.setCapability ("enableVideo", true);
        open(mainAppUrl);
    }

    @AfterEach
    void tearDownEach() {
//        getWebDriver().close();
    }

    @AfterAll
    static void tearDown() {
        try {
            getWebDriver().close();
        } catch (Exception ignored) {
        }
    }
}
