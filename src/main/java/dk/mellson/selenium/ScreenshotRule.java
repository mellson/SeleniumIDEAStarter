package dk.mellson.selenium;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

class ScreenshotRule extends TestWatcher {
    private final WebDriver browser;
    private final AbstractSeleniumTest abstractSeleniumTest;

    ScreenshotRule(WebDriver browser, AbstractSeleniumTest abstractSeleniumTest) {
        this.browser = browser;
        this.abstractSeleniumTest = abstractSeleniumTest;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        File scrFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
        File destFile = getDestinationFile(description);
        try {
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    protected void finished(Description description) {
        browser.close();
    }

    private File getDestinationFile(Description description) {
        String fileName = description.getClassName() + "_" + description.getMethodName() + ".png";
        String absoluteFileName = "SeleniumScreenshots/" + fileName;

        return new File(absoluteFileName);
    }
}
