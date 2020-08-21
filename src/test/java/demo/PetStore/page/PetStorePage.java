package demo.PetStore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import demo.helpers.Helper;

public class PetStorePage {

	private WebDriver driver;
	private ExtentTest test;
	private Boolean TAKE_SS;
	private WebDriverWait wait;
//Declaramos variables
	private By varReptiles;
	private By varProductoID;
	private By varPrecioLista;

//Parametrizamos nuestra clase ContactosSBLPage
	public PetStorePage(WebDriver driver, ExtentTest test, Boolean TAKE_SS, int seconds) {
		this.driver = driver;
		this.test = test;
		this.TAKE_SS = TAKE_SS;
		wait = new WebDriverWait(driver, seconds);
		this.varReptiles = By.xpath("(//img[contains(@src,'icon.gif')])[4]");
		this.varProductoID = By.xpath("//a[contains(.,'RP-LI-02')]");
		this.varPrecioLista = By.xpath("//td[contains(.,'$18.50')]");
	}

	public void detalleIguana(String subDir) throws Exception {

		wait.until(ExpectedConditions.visibilityOfElementLocated(varReptiles));
		System.out.println("Seleccionar Opción Reptiles");
		Helper.addEvidence(TAKE_SS, driver, test, "Evidencia - Pagina_Principal / Precio Lista ", subDir,
		"Precio_Lista_1");
		wait.until(ExpectedConditions.elementToBeClickable(varReptiles)).click();
		Helper.waitSeconds(1);
		Helper.addEvidence(TAKE_SS, driver, test, "Evidencia - Pagina_Reptiles / Precio Lista ", subDir,
				"Precio_Lista_2");
		System.out.println("Seleccionar Producto ID Iguana");
		wait.until(ExpectedConditions.elementToBeClickable(varProductoID)).click();
	}

	public void mostrarPrecioLista(String subDir) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(varPrecioLista));
		Helper.addEvidence(TAKE_SS, driver, test, "Evidencia - Pagina_Iguana / Precio Lista ", subDir,
		"Precio_Lista_3");
		
		WebElement campoPrecio = driver.findElement(varPrecioLista);
		String valor = campoPrecio.getText();
		System.out.println("El valor capturado es:" + valor);
	}

}