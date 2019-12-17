package cz.vse.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class TS_PROJECT {
    private ChromeDriver driver;
    private String url="https://digitalnizena.cz/rukovoditel/index.php?module=users/login";

    @Before
    public void init() throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        ChromeOptions cho = new ChromeOptions();
        cho.addArguments("--headless");
        cho.addArguments("start-maximized");
        cho.addArguments("window-size=1200,1100");
        cho.addArguments("--disable-gpu");
        cho.addArguments("--disable-extensions");
        //driver = new ChromeDriver(cho);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
//        driver.close();
    }

    public void Login(){
        driver.get(url);
        WebElement searchInput = driver.findElement(By.name("username"));
        searchInput.sendKeys("rukovoditel");
        searchInput = driver.findElement(By.name("password"));
        searchInput.sendKeys("vse456ru");
        searchInput.sendKeys(Keys.ENTER);
    }


    @Test
    public void bad_project_fourth_test() {
        Login();
        driver.findElement(By.cssSelector("li:nth-child(4) .title:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary-modal-action")));
        driver.findElement(By.cssSelector(".btn-primary-modal-action")).click();
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert")));
        Assert.assertTrue(driver.findElement(By.cssSelector(".alert")).isDisplayed());
        driver.findElement(By.cssSelector(".btn:nth-child(4)")).click();
        driver.quit();
    }

    @Test
    public void new_project_fifth_test() {
        Login();
        driver.findElement(By.cssSelector("li:nth-child(4) .title:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        //Name: xname
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fields_158")));
        WebElement searchInput = driver.findElement(By.id("fields_158"));
        searchInput.sendKeys("vism00");
        //priority: high
        driver.findElement(By.id("fields_156"));
        Select select = new Select(driver.findElement(By.id("fields_156")));
        select.selectByIndex(1);
        //Status: new
        //Assert.assertTrue(driver.findElement(By.id("fields_157")).isDisplayed());
        driver.findElement(By.id("fields_159")).click();
        driver.findElement(By.cssSelector("td[class='active day']")).click();
        driver.findElement(By.className("btn-primary-modal-action")).click();

        driver.findElement(By.xpath("//a[contains(text(),'vism00')]")).click();

        driver.findElement(By.cssSelector(".btn-default:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".btn-default:nth-child(1)")).click();
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-group li:nth-child(2) > a")));
        driver.findElement(By.cssSelector(".btn-group li:nth-child(2) > a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-delete_confirm")));
        driver.findElement(By.id("delete_confirm")).click();
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn:nth-child(3)")));
        driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();



/*
        WebDriverWait wait = new WebDriverWait(driver, 5);
        searchInput.sendKeys("vism00");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary-modal-action")));
        driver.findElement(By.cssSelector(".btn-primary-modal-action")).click();
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert")));
        Assert.assertTrue(driver.findElement(By.cssSelector(".alert")).isDisplayed());
        driver.findElement(By.cssSelector(".btn:nth-child(4)")).click();
        driver.quit();*/
    }

}