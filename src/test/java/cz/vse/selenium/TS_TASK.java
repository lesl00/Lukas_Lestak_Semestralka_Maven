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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class TS_TASK {
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

    public void Prihlasenie(){
        driver.get(url);
        WebElement searchInput = driver.findElement(By.name("username"));
        searchInput.sendKeys("rukovoditel");
        searchInput = driver.findElement(By.name("password"));
        searchInput.sendKeys("vse456ru");
        searchInput.sendKeys(Keys.ENTER);
    }


    public void projekt_novy(){
        driver.findElement(By.cssSelector("li:nth-child(4) .title:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        //Name: xname
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fields_158")));
        WebElement searchInput = driver.findElement(By.id("fields_158"));
        searchInput.sendKeys("lesl00");
        //status
        driver.findElement(By.id("fields_157"));
        Select select = new Select(driver.findElement(By.id("fields_157")));
        select.selectByIndex(0);
        //priority: high
        driver.findElement(By.id("fields_156"));
        select = new Select(driver.findElement(By.id("fields_156")));
        select.selectByIndex(1);
        //Status: new
        //Assert.assertTrue(driver.findElement(By.id("fields_157")).isDisplayed());
        driver.findElement(By.id("fields_159")).click();
        driver.findElement(By.cssSelector("td[class='active day']")).click();
        driver.findElement(By.className("btn-primary-modal-action")).click();
    }

    @Test
    public void uloha_nova_kontrola() {
        //preconditions
        Prihlasenie();
        projekt_novy();

        driver.findElement(By.cssSelector(".btn-primary")).click();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fields_168")));

        //name
        WebElement searchInput = driver.findElement(By.id("fields_168"));
        searchInput.sendKeys("lesl00Uloha");

        //type
        driver.findElement(By.id("fields_167"));
        Select select = new Select(driver.findElement(By.id("fields_167")));
        select.selectByIndex(1);

        //status
        driver.findElement(By.id("fields_169"));
        select = new Select(driver.findElement(By.id("fields_169")));
        select.selectByIndex(0);

        //priority
        driver.findElement(By.id("fields_170"));
        select = new Select(driver.findElement(By.id("fields_170")));
        select.selectByIndex(2);

        //description
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        driver.findElement(By.tagName("body")).sendKeys("BLa BLa BLa Popis");
        driver.switchTo().defaultContent();
        driver.findElement(By.className("btn-primary-modal-action")).click();

        wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='table table-striped table-bordered table-hover'] tr")));
        List<WebElement> elements = driver.findElements(By.cssSelector("[class='table table-striped table-bordered table-hover'] tr"));
        List<WebElement> cells = elements.get(1).findElements(By.tagName("td"));
        List<WebElement> obsah = cells.get(1).findElements(By.tagName("a"));
        obsah.get(2).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='table table-bordered table-hover table-item-details'] tr")));
        elements = driver.findElements(By.cssSelector("[class='table table-bordered table-hover table-item-details'] tr"));

        WebElement nazov = driver.findElement(By.className("caption"));
        Assert.assertEquals("lesl00Uloha", nazov.getText());
        WebElement desc = driver.findElement(By.className("fieldtype_textarea_wysiwyg"));
        Assert.assertEquals("BLa BLa BLa Popis", desc.getText());



        cells = elements.get(3).findElements(By.tagName("td"));
        obsah = cells.get(0).findElements(By.tagName("div"));
        Assert.assertTrue(obsah.get(0).getText().equals("Task"));


        cells = elements.get(4).findElements(By.tagName("td"));
        obsah = cells.get(0).findElements(By.tagName("div"));
        Assert.assertTrue(obsah.get(0).getText().equals("New"));

        cells = elements.get(5).findElements(By.tagName("td"));
        obsah = cells.get(0).findElements(By.tagName("div"));
        Assert.assertTrue(obsah.get(0).getText().equals("Medium"));


        //task rip
        driver.executeScript("window.history.go(-1)");
        wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='table table-striped table-bordered table-hover'] tr")));
        elements = driver.findElements(By.cssSelector("[class='table table-striped table-bordered table-hover'] tr"));
        cells = elements.get(1).findElements(By.tagName("td"));
        obsah = cells.get(1).findElements(By.tagName("a"));
        obsah.get(0).click();
        wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary-modal-action")));
        driver.findElement(By.className("btn-primary-modal-action")).click();






    }

}