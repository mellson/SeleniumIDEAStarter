package dk.mellson.selenium;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.concurrent.TimeUnit;

public abstract class AbstractSeleniumTest {
    @Rule
    public TestRule globalTimeout = new Timeout(30, TimeUnit.SECONDS);
    @Rule
    public TestWatcher screenShootRule;
    protected WebDriver browser;

    public AbstractSeleniumTest() {
        PhantomJsDriverManager.getInstance().setup();
        browser = new PhantomJSDriver();

        // Set the browser to have a "screen-size" of 1920 x 1080
        browser.manage().window().setSize(new Dimension(1920, 1080));

        // Take a screenshot if a test fails
        screenShootRule = new ScreenshotRule(browser, this);
    }
}
