package Test_Scenarios;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Add_To_Cart;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;



public class Test_Methods {
	WebDriver driver;
	FileReader reader = null;
	Properties props = new Properties();
	LoginPage lp; 
	ProductsPage pp; 
	Add_To_Cart atc; 

	String product_1_Name; 
	String product_2_Name; 
	protected static String product_1_Price; 
	protected static String product_2_Price; 

	
	@Test(priority = 0, enabled = true)
	public void readConfig() {
		
		try {
			reader = new FileReader(".//config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			props.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("OPENING URL: "+props.getProperty("URL"));
	}
	
	

	@Test(priority = 1, enabled = true)
	public void open_Browser() {
		System.setProperty("webdriver.chrome.driver", ".//Drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(props.getProperty("URL"));
		System.out.println("URL IS OPENED>>>>>>>>>>>>>>>>>>>>");
	}
	
	

	@Test(priority = 2, enabled = true)
	public void login() throws InterruptedException {
		lp = new LoginPage(driver); //
		lp.setEmailId(props.getProperty("emial"));
		lp.setPassword(props.getProperty("pass"));
		Thread.sleep(2000);
		lp.clickLoginBtn();
	}
	
	
	

	@Test(priority = 3, enabled = true)
	public void compare_Products() throws InterruptedException {
		pp = new ProductsPage(driver);
		pp.move_To_Mp3Player();
		pp.click_ShowAll_Mp3Players();

		product_1_Name = pp.get_Product_1_Name();
		product_2_Name = pp.get_product_2_Name();
		product_1_Price = pp.get_Product_1_Price().substring(0, 7);
		product_2_Price = pp.get_Product_2_Price().substring(0, 7);

		Assert.assertTrue(pp.verify_Compare_Count()); 

		pp.click_compare_ipodClassic(); 
		String comp_Msg_0 = pp.get_Compare_Msg();
		System.out.println("IN Main" + comp_Msg_0);
		Assert.assertEquals(comp_Msg_0, props.getProperty("verifyComMsgFor_iPod_Classic")); 

		pp.click_Compare_ipodNano();
		String comp_Msg_1 = pp.get_Compare_Msg();
		System.out.println("IN Main" + comp_Msg_1);
		Assert.assertEquals(comp_Msg_1, props.getProperty("verifyComMsgFor_iPod_Nano"));

		Assert.assertTrue(pp.verify_Compare_Count());

		pp.click_Product_Compare(); 

		
		
		Assert.assertEquals(pp.get_Product_1_Name_C(), product_1_Name);
		Assert.assertEquals(pp.get_product_2_Name_C(), product_2_Name);
		Assert.assertEquals(pp.get_Product_1_Price_C(), product_1_Price);
		Assert.assertEquals(pp.get_Product_2_Price_C(), product_2_Price);
	}

	
	@Test(priority = 4, enabled = true)
	public void add_To_Cart() throws InterruptedException {
		atc = new Add_To_Cart(driver);
		atc.click_iPodClassic_AddToCart();

		String comp_Msg_2 = pp.get_Compare_Msg();
		System.out.println("IN Main" + comp_Msg_2);
		Assert.assertEquals(comp_Msg_2, props.getProperty("verifyCartMsgFor_iPod_Classic"));

		atc.click_iPodNano_AddToCart();
		atc.click_iPodNano_AddToCart();
		
		
		String comp_Msg_3 = pp.get_Compare_Msg();
		System.out.println("IN Main" + comp_Msg_3);
		Assert.assertEquals(comp_Msg_3, props.getProperty("verifyCartMsgFor_iPod_Nano"));

		atc.click_Cart();
		Assert.assertTrue(atc.cartCompare(product_1_Name, atc.total_Price_Of_iPodClassic()));
		Assert.assertTrue(atc.cartCompare(product_2_Name, atc.total_Price_Of_iPodNano()));
		Assert.assertTrue(atc.verify_Total_Cart_Price());
	}

	
	@Test(priority = 5, enabled = true)
	public void remove_Products_From_Compare() throws InterruptedException {
		pp.remove_ipodClassic();
		Assert.assertEquals(pp.get_Compare_Msg(), props.getProperty("verifyRemoveMsg"));
		Assert.assertFalse(pp.verify_Remove_ipodClassic());
		pp.move_To_Mp3Player();
		pp.click_ShowAll_Mp3Players();
		
		Assert.assertTrue(pp.verify_Compare_Count());
	}

	
	@Test(priority = 6, enabled = true)
	public void verifyLayout() {
		pp.clickListView();
		Assert.assertTrue(pp.verify_List_Layout());
		pp.clickGridView();
		Assert.assertTrue(pp.verify_Grid_Layout());
	}

	
	@Test(priority = 7, enabled = true)
	public void verifySortByOrder() {
		pp.selectSort();
		Assert.assertTrue(pp.verifySort());
	}

}
