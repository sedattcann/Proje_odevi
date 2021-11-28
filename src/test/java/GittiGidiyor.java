import org.apache.commons.logging.impl.Log4JLogger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.ui.Select;
import static  org.openqa.selenium.By.*;




public class GittiGidiyor {
    protected  WebDriver driver;
    public static String GGUrl="https://www.gittigidiyor.com/";
    private static Logger log  = Logger.getLogger(TestLog.class.getName());
    @Before
    public void setUp(){
        PropertyConfigurator.configure("C:\\javademos\\TestiniumWork\\src\\main\\resources\\log4j.properties");

        try {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            System.setProperty("webdriver.chrome.driver","C:\\javademos\\TestiniumWork\\ChromeWebDriver\\chromedriver.exe");
            driver=new ChromeDriver(capabilities);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(30,TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
            log.info("Driver Başlatıldı");


        }catch (Exception e){
            e.printStackTrace();
            log.info("Driver Başlatılırken Hata Alındı");
        }
    }


    @Test
    public void GG() {

        try {

            driver.get(GGUrl);

            driver.findElement(xpath("//*[@id='main-header']/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input")).sendKeys("Bilgisayar");
            log.info("Arama kutusuna text girildi");
            Thread.sleep(2000);
            driver.findElement(xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button")).click();
            log.info("arama butonuna basıldı");
            Thread.sleep(2000);
            driver.findElement(By.tagName("body")).sendKeys(Keys.SPACE);
            driver.findElement(By.tagName("body")).sendKeys(Keys.SPACE);
            driver.findElement(By.tagName("body")).sendKeys(Keys.SPACE);
            driver.findElement(By.tagName("body")).sendKeys(Keys.SPACE);
            driver.findElement(By.tagName("body")).sendKeys(Keys.SPACE);
            driver.findElement(By.tagName("body")).sendKeys(Keys.SPACE);
            driver.findElement(By.tagName("body")).sendKeys(Keys.SPACE);
            driver.findElement(xpath("//*[@id=\"__next\"]/main/div[4]/section[1]/section[2]/a")).click();
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1000)", "");
            Thread.sleep(3000);
            driver.findElement(linkText("Sonraki")).click();
            log.info("2.Sayfaya Geçildi");
            Thread.sleep(5000);
            List<WebElement> link=driver.findElements(By.cssSelector("#__next > main > div.vfy45n-0.bWfBMK > div > div > div.sc-1nx8ums-0.fbkkZW > div > div.sc-533kbx-0.sc-1v2q8t1-0.iCRwxx.gyNBA > div.pmyvb0-0.jCCkZh > ul > li:nth-child(9) > article > div.sc-533kbx-0.sc-1v2q8t1-0.iCRwxx.ixSZpI.sc-1n49x8z-12.bhlHZl"));
            link.get(new Random().nextInt(link.size())).click();
            log.info("Rasgele Seçildi");
            log.info("Ürün detayına girildi");
            FileWriter fwriter=new FileWriter("C:\\javademos\\TestiniumWork\\src\\test\\java\\info.txt",true);
            fwriter.write(driver.findElement(xpath("//*[@id=\"sp-title\"]")).getText());
            fwriter.write("\n");
            fwriter.write("------------------------------------------------------");
            fwriter.write("\n");
            fwriter.write(driver.findElement(xpath("//*[@id=\"sp-price-lowPrice\"]")).getText());
            fwriter.write("\n");
            fwriter.close();
            log.info("İnfo dosyası oluşturuldu ve bilgiler yazıldı");
            Thread.sleep(9000);
            var firstPrice=driver.findElement(xpath("//*[@id=\"sp-price-lowPrice\"]")).getText();
            log.info("seçilen ürünün fiyatı"+" : "+firstPrice);
            driver.findElement(By.tagName("body")).sendKeys(Keys.SPACE);
            driver.findElement(id("add-to-basket")).click();
            log.info("Ürün Sepet'e Eklendi");
            Thread.sleep(3000);
            driver.findElement(xpath("//*[@id=\"header_wrapper\"]/div[4]/div[3]/a/div[1]/div")).click();
            Thread.sleep(2000);
            log.info("Sepet e gidildi");
           var lastPrice =driver.findElement(xpath("//*[@id=\"cart-price-container\"]/div[3]/p")).getText();
            log.info("sepetteki ürünün fiyatı"+" : "+lastPrice);
            Thread.sleep(2000);

            if (firstPrice!=null&&lastPrice!=null&&lastPrice==firstPrice){
                log.info("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatı doğru");
            }else if (firstPrice==null){
                log.info("Seçilen ürün fiyatı yanlış");
            }else if (lastPrice==null){
                log.info("Sepete eklenen ürünün sepetteki fiyarı farklı");
            }else {
                log.info("Seçilen ürün ile fiyatı yanlış");
            }
            Thread.sleep(5000);


            driver.quit();
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }


}
