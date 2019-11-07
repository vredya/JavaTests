import com.sun.deploy.security.SelectableSecurityManager;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Item
{

    public String getDataValidFrom(WebDriver driver)
    {
        return driver.findElement(By.name("date_valid_from")).getAttribute("value");
    }

    public void setDataValidFrom(WebDriver driver){
        driver.findElement(By.name("date_valid_from")).clear();
        //driver.findElement(By.name("date_valid_from")).sendKeys(LocalDate.now().toString());
        driver.findElement(By.name("date_valid_from")).sendKeys("11/07/2019");
    }

    public String getDataValidTo(WebDriver driver){
        return driver.findElement(By.name("date_valid_to")).getAttribute("value");
    }

    public void setDataValidTo(WebDriver driver){
        driver.findElement(By.name("date_valid_to")).clear();
        //driver.findElement(By.name("date_valid_to")).sendKeys(LocalDate.now().toString());
        driver.findElement(By.name("date_valid_to")).sendKeys("11/07/2019");
    }

    public String getName(WebDriver driver){
        return driver.findElement(By.name("name[en]")).getAttribute("value");
    }

    public void setName(WebDriver driver, String name){
        driver.findElement(By.name("name[en]")).sendKeys(name);
    }

    public String getSku(WebDriver driver){
        return driver.findElement(By.name("sku")).getAttribute("value");
    }

    public void setSku(WebDriver driver, String str){
        driver.findElement(By.name("sku")).sendKeys(str);
    }
    public String getMpn(WebDriver driver){
        return driver.findElement(By.name("mpn")).getAttribute("value");
    }

    public void setMpn(WebDriver driver, String str){
        driver.findElement(By.name("mpn")).sendKeys(str);
    }

    public String getGtin(WebDriver driver){
        return driver.findElement(By.name("gtin")).getAttribute("value");
    }

    public void setGtin(WebDriver driver, String str){
        driver.findElement(By.name("gtin")).sendKeys(str);
    }
    public String getTaric(WebDriver driver){
        return driver.findElement(By.name("taric")).getAttribute("value");
    }

    public void setTaric(WebDriver driver, String str){
        driver.findElement(By.name("taric")).sendKeys(str);
    }

    public String getCode(WebDriver driver){
        return driver.findElement(By.name("code")).getAttribute("value");
    }

    public void setCode(WebDriver driver, String code){
        driver.findElement(By.name("code")).sendKeys(code);
    }

    public String getKeywords(WebDriver driver){
        return driver.findElement(By.name("keywords")).getAttribute("value");
    }

    public void setKeywords(WebDriver driver, String keywords){
        driver.findElement(By.name("keywords")).sendKeys(keywords);
    }

    public void setImage(WebDriver driver){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("duckling.png").getFile());
        driver.findElement(By.cssSelector(".image .input-group .form-control")).sendKeys(file.getAbsolutePath());
    }

    public boolean isImagePresent(WebDriver driver){
        String somethingPresent = driver.findElement(By.cssSelector(".image .input-group .form-control")).getAttribute("value");
        return somethingPresent!=null;
    }

    public void FillData(WebDriver driver){
        String generatedString = RandomString.make(7);
        setDataValidFrom(driver);
        setDataValidTo(driver);
        setName(driver, "Name "+generatedString);
        setCode(driver, generatedString);
        setKeywords(driver, "Keywords");
        setSku(driver, generatedString);
        setMpn(driver, generatedString);
        setGtin(driver, generatedString);
        setTaric(driver, generatedString);
        setImage(driver);
    }

    public List<String> getData(WebDriver driver){
        ArrayList<String> data= new ArrayList<>();
        data.add(getDataValidFrom(driver));
        data.add(getDataValidTo(driver));
        data.add(getName(driver));
        data.add(getCode(driver));
        data.add(getKeywords(driver));
        data.add(getSku(driver));
        data.add(getMpn(driver));
        data.add(getGtin(driver));
        data.add(getTaric(driver));
        if (isImagePresent(driver)==true)
        {
            data.add("true");
        }
        else {
            data.add("false");
        }
        return data;
    }
}
