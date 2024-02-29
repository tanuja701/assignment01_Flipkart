import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FlipkartAssignment {
    WebDriver driver;
    @BeforeMethod
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com/");
        Thread.sleep(1000);
    }

    @Test(priority = 0)
    public void linkUsingForLoop() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (int i = 0; i < links.size(); i++) {
            System.out.println(links.get(i).getText() + " : " + links.get(i).getAttribute("href"));
        }
    }

    @Test(priority = 1)
    public void linkUsingStream() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        links.stream()
                .map(WebElement::getText)
                .forEach(System.out::println);
    }

    @Test(priority = 2)
    public void linkUsingParallelStream() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        links.parallelStream()
                .filter(link -> !link.getText().isEmpty())
                .forEach(link -> System.out.println(link.getText() + " : " + link.getAttribute("href")));
    }

    @Test(priority = 3)
    public void linkUsingLambdaExpression() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        links.stream()
                .filter(link -> !link.getText().isEmpty())
                .forEach(link -> System.out.println(link.getText() + " : " + link.getAttribute("href")));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
