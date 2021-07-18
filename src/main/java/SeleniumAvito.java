import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumAvito {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(" https://www.avito.ru/");

        Select categorySelect = new Select(driver.findElement(By.xpath("//select[@name='category_id']")));
        categorySelect.selectByValue("99");
//id("search")
        driver.findElement(By.xpath("//input[@data-marker='search-form/suggest']")).sendKeys("Принтер");
        WebElement params = driver.findElement(By.xpath("//div[@data-marker='search-form/region']"));
        params.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.findElement(By.xpath("//input[@class='suggest-input-3p8yi']")).sendKeys("Владивосток");
        wait
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//strong[contains(text(),'Владивосток')]")));
        WebElement switchLi = driver.findElement(By.xpath("//li[contains(@class,'suggest-suggest-1wwEm text-text-1PdBw text-size-m-4mxHN')][1]"));
        switchLi.click();

        WebElement buttonAd = driver.findElement(By.xpath("//button[@class='button-button-2Fo5k button-size-m-7jtw4 button-primary-1RhOG']"));
        // или //button[@class='button-button-2Fo5k button-size-m-7jtw4 button-primary-1RhOG']
        buttonAd.click();
        WebElement checkbox = driver.findElement(By.xpath("//span[contains(text(),'С Авито Доставкой')]"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        WebElement buttonAdMain = driver.findElement(By.xpath("//button[@class='button-button-2Fo5k button-size-s-3-rn6 button-primary-1RhOG width-width-12-2VZLz']"));
        buttonAdMain.click();
        Select listSort = new Select(driver.findElement(By.xpath("//div[@class='index-topPanel-1F0TP']//select[@class='select-select-3CHiM']")));
        listSort.selectByValue("2");
        List<WebElement> webElementList = driver.findElements(By.xpath("//div[@data-marker='catalog-serp']/div[@data-marker='item']"));
        for (int i = 0; i < webElementList.size(); i++) {
            System.out.println("Товар " +
                    webElementList.get(i).findElement(By.xpath(".//a[@data-marker='item-title']")).getText() + " Цена " + webElementList.get(i).findElement(By.xpath(".//span[@data-marker='item-price']")).getText());
            if (i == 2)
                break;
        }
        driver.quit();
    }
}
