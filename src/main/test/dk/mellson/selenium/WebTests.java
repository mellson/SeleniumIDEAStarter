package dk.mellson.selenium;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WebTests extends AbstractSeleniumTest {
    @Test
    public void theWorldIsSafe() {
        browser.get("https://en.wikipedia.org/wiki/List_of_Presidents_of_the_United_States");
        assertTrue(!browser.getPageSource().contains("Donald Trump"));
    }
}
