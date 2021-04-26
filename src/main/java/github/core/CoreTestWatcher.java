package github.core;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CoreTestWatcher implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.addAttachment(context.getDisplayName(), new ByteArrayInputStream(((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    @Attachment(value = "Last screenshot of browser state", type = "image/png")
    public byte[] makeBrowserLastScreenshot(byte[] screenshot) {
        return screenshot;
    }
}
