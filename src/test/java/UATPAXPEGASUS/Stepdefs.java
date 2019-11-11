package UATPAXPEGASUS;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class Stepdefs {
    private static ChromeDriverService service;
    private WebDriver driver;

    @Given("today is Sunday")
    public void today_is_Sunday() {
        // Write code here that turns the phrase above into concrete actions
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/test/java/UATPAXPEGASUS/input/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        // Write code here that turns the phrase above into concrete actions
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
    }

    @Then("I should be told")
    public void i_should_be_told() {
        // Write code here that turns the phrase above into concrete actions
        driver.get("http://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("webdriver");
        assertEquals("webdriver - Google Search", driver.getTitle());
    }

}
