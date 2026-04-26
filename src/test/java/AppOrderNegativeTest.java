import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppOrderNegativeTest {

    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    public void ErrorMessageWithAnEmptyPhoneField() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Иван Олегович");
        //driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+89854561232");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();
        WebElement invalidField = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid"));
        assertTrue(invalidField.isDisplayed());

    }

    @Test
    public void ErrorMessageWithAnEmptyNameField() {
        //driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Иван Олегович");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+89854561232");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();
        WebElement invalidField = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid"));
        assertTrue(invalidField.isDisplayed());

    }

    @Test
    public void ErrorMessageWithInvalidDataInThePhoneField() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Иван Олегович");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+8985488561232");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();
        WebElement invalidField = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid"));
        assertTrue(invalidField.isDisplayed());

    }

    @Test
    public void ErrorMessageWithInvalidDataInTheNameField() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петроkв Ивgан Олеdгович");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+89854561232");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();
        WebElement invalidField = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid"));
        assertTrue(invalidField.isDisplayed());

    }

    @Test
    public void ErrorWhenTheCheckboxIsNotClick() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петроkв Ивgан Олеdгович");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+89854561232");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();
        WebElement invalidField = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid"));
        assertTrue(invalidField.isDisplayed());

    }
}
