package demo.PetStore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.relevantcodes.extentreports.ExtentTest;

import demo.helpers.Helper;
import demo.helpers.PageWeb;

public class PetStorePage extends PageWeb {

//Declaramos variables
	private By varReptiles;
	private By varProductoID;
	private By varPrecioLista;

//Parametrizamos nuestra clase ContactosSBLPage
	public PetStorePage(WebDriver driver, ExtentTest test, Boolean TAKE_SS, int seconds) {
		super(driver, test, TAKE_SS, seconds);
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