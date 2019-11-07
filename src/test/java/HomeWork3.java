import net.bytebuddy.utility.RandomString;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomeWork3 {
    WebDriver driver;
    Item item = new Item();
    String productName;
    int bq;

    @Before
    public void openBrowser(){
        driver = new ChromeDriver();
    }

    @Test
    // Part 1
    public void thirdHomeWork(){

        driver.get("http://demo.litecart.net/admin/");
        waitEl(By.xpath("//*[contains(@class,'btn') and (@name='login')]"));
        driver.findElement(By.xpath("//*[contains(@class,'btn') and (@name='login')]")).click();
        //waitEl(By.id("logotype"));
        waitEl(By.xpath("//*[@data-code='catalog']/a"));
        driver.get("http://demo.litecart.net/admin/?app=catalog&doc=catalog");
        waitEl(By.cssSelector("[name = 'search_form']"));
        driver.findElement(By.xpath("//*[contains(@class,'btn')and contains(text(),'Product')]")).click();
        waitEl(By.xpath("//*[contains(text(),'Add New Product')]"));
        item.FillData(driver);
        productName = item.getName(driver);
        List<String> fillData = item.getData(driver);
        driver.findElement(By.name("save")).click();
        //waitEl();
        driver.findElement(By.xpath("//*[text()='"+productName+"']")).click();
        List<String> newData = item.getData(driver);
        Assert.assertEquals(newData, fillData);

    }

    @Test
    //Part2
    public void HomeWork3Part2() throws InterruptedException {
        int bq;
        for (int i=0; i<3; i++) {
            driver.get("http://demo.litecart.net/");
            waitEl(By.id("header"));
            WebElement elem = driver.findElement(By.className("product-column"));
            Actions mouse = new Actions(driver);
            Action mouseMove = mouse
                    .moveToElement(elem)
                    .build();
            mouseMove.perform();
            elem.click();
            waitEl(By.className("title"));
            Select drpdown = new Select(driver.findElement(By.name("options[Size]")));
            drpdown.selectByValue("Small");
            int firstData = getBangetQ();
            driver.findElement(By.className("btn-success")).click();
            while (getBangetQ()==firstData){
                Thread.sleep(10);
            }
            int secondData = getBangetQ();
            driver.findElement(By.xpath("//*[@class='badge quantity']")).click();
            waitEl(By.id("box-checkout-customer"));
            driver.findElements(By.name("remove_cart_item")).forEach(d -> d.click());
            waitEl(By.xpath("//*[em]"));
            Assert.assertTrue(driver.findElement(By.xpath("//*[text() = 'There are no items in your cart.']")).isEnabled());
        }

    }

    public void waitEl(By elem)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
    }

    public int getBangetQ(){
        String bandgeQ = driver.findElement(By.xpath("//*[@class='badge quantity']")).getAttribute("innerText");
        if (bandgeQ.isEmpty()){
            bq = 0;
        }else{
            bq = Integer.parseInt(bandgeQ);
        }
        return bq;
    }

}


