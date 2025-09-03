package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

public class LoginTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // 设置 ChromeDriver 路径（建议使用 WebDriverManager 更好）
        String driverPath = System.getProperty("user.dir") + "/resources/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 无头模式（适合 Jenkins）
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void testSuccessfulLogin() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement message = driver.findElement(By.id("flash"));
        Assert.assertTrue(message.getText().contains("You logged into a secure area"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}