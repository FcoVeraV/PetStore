package demo.PetStore.test;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import demo.PetStore.page.PetStorePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test_demo {

	protected WebDriver driver;
	protected ExtentReports extent;
	protected ExtentTest test;
	protected static String SUBDIR = "TCS ";
	protected static Boolean TAKE_SS = true;

	@BeforeSuite
	public void configExtentReports() throws IOException, AWTException {
		// ExtentReports config
		this.extent = new ExtentReports("ExtentReports/ReporteDemo.html", true);
		this.extent.addSystemInfo("Host Name", "TCS");
		this.extent.addSystemInfo("Enviroment", "Automation Testing");
		this.extent.addSystemInfo("User Name", "Francisco Vera");
	}

	@BeforeMethod
	public void configSelenium() throws IOException {
		// Selenium config
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("TCS", "Test Demo");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://petstore.octoperf.com/actions/Catalog.action");

	}

	// Programamos la ejecucion de las pruebas
	@Test(description = "Test Demo")
	public void test_Demo() throws Exception {
		String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
		// Configuramos el reporte
		test = extent.startTest("Prueba TCS", "Prueba Capturar List Price");
		test.log(LogStatus.INFO, "Prueba Capturar List Price");

		PetStorePage pag = new PetStorePage(driver, test, TAKE_SS, 5);
		pag.detalleIguana(subDir);
		pag.mostrarPrecioLista(subDir);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test failed.- <br>" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test skipped.- <br>" + result.getThrowable());
		} else {
			test.log(LogStatus.PASS, "Test passed.-");
		}
		// driver.close();
		extent.endTest(test);
	}

	@AfterSuite
	public void closeExtentReports() {
		// Escribimos los datos al reporte.
		extent.flush();
	}

}