
![Image text](https://github.com/dsanchep7/Screenplay/tree/main/src/resource)

div<img src="src/resource/reporte.jpg" height="400" width="650" />

# Mi segundo reto

Mi segundo reto consta de realizar la automatización de ingreso y busqueda de productos en la paginan https://co.ebay.com/, con diferentes escenarios background,fallido y exitoso.

## Empezando

Las pruebas fueron apoyados por el IDE Intelling con el gestor de dependencias gradle sobre la parte front de la pagina apoyado por el patron de diseño POM y la herramienta de software gherkin.


### Implementación de Screnplay
 
Se realiza en los paquetes 
- drivers Clase - ChromeDriver )
- taks (Clase - BuscadorEbay)
- uis (Clase - Pages)

####   La clase ChromeDriver
  ```
Esta clase se utiliza para levantar el navegador

package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleChromeDriver {

    public static WebDriver driver;

    public static  GoogleChromeDriver chromeHisBrowserWeb() {


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-infobars");

        driver = new ChromeDriver(options);
        return new GoogleChromeDriver();

    }

    public  WebDriver on(String url) {
        driver.get(url);
        return driver;
    }

}

  ```
####  La clase BuscadorEbay
  ```
Es donde definimos los actores para la iteración con la pagina que estamos automatizando.

package taks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import uis.EbayPage;

public class BuscadorEbay implements Task {

    private  String producto;

    public BuscadorEbay(String producto) {
        this.producto = producto;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(producto).into(EbayPage.TXT_BUSCADOR),
                Click.on(EbayPage.BTN_BUSCADOR),
                Click.on(EbayPage.BTN_ELEMENTO_BUSQUEDA.of(producto))

        );
    }

    public static BuscadorEbay EnEbay (String producto){

        return Instrumented.instanceOf(BuscadorEbay.class).withProperties(producto);
    }
}
  ```

####  La clase uis
  ```
Es donde definimos los xpath para la iteración entre la pagina

package uis;

import net.serenitybdd.screenplay.targets.Target;

public class EbayPage {

    public static final Target TXT_BUSCADOR = Target.the("").locatedBy("//input[@placeholder='Buscar artículos']");
    public static final Target BTN_BUSCADOR = Target.the("").locatedBy("//input[@class ='btn btn-prim gh-spr' ]");
    public static final Target BTN_ELEMENTO_BUSQUEDA = Target.the("").locatedBy("//li[@class='s-item s-item__pl-on-bottom']//div[@class='s-item__wrapper clearfix']//h3[contains(text(),'{0}')]");
    public static final Target TXT_ELEMENTO_BUSQUEDA = Target.the("").locatedBy("//h3[contains(text(),'{0}')]");

}

  ```


### Implementación del Screenplay

Se realiza en los paquetes 

-runners (Clase - Runners )
-stepsDefinition (Clase - stepsDefinition)
-Directorio feature (Archivo.feature)


####  EL runner
  ```
Es el que nos permite ejecutar el proyecto, en este caso se crea runner por escenario presentado desde los features (EbayBackgroundRunner, EbayFallidoYExitosoRunner y EbayRunner)


package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src\\test\\resources\\features\\EbayBuscador.feature",
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
)

public class EbayRunner {


}

  ```

#### StepsDefinition
  ```
Aqui se definen los paso a paso que se van hacer en la pagina cuando se ejecute la automatización que se componen por el given, when y then presentados en cada uno de los escenarios presentados en el feature.


package stepsDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import drivers.GoogleChromeDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.junit.Before;
import taks.BuscadorEbay;
import uis.EbayPage;

public class EbayStepsDefinitions {

  Actor actor = new Actor("Daniela");

  @Before
  public void before(){
    OnStage.setTheStage(new OnlineCast());
  }


  @Given("^Ingreso a la pagina de Ebay$")
  public void ingresoALaPaginaDeEbay() {actor.can
          (BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://co.ebay.com/")));

  }

  @When("^busque el producto (.*)$")
  public void busqueElProductoLenovoIdeaPadGaming3156120HzLaptopparaJuegosAMDRyzen55600H8GBRam512G(String producto) {
    actor.attemptsTo(BuscadorEbay.EnEbay(producto));
  }

  @Then("^podre ver (.*) en pantalla$")
  public void podreVerLenovoIdeaPadGaming3156120HzLaptopparaJuegosAMDRyzen55600H8GBRam512GEnPantalla(String producto) {
    actor.should(GivenWhenThen.seeThat(WebElementQuestion.the(EbayPage.TXT_ELEMENTO_BUSQUEDA.of(producto)), WebElementStateMatchers.containsText(producto)));
  }

}


  ```

#### Los feature
  ```
Es donde se arma la historia de usuario, la cual tiene una estructura inicial y final, para este reto se generaron tres tipos de feature:
 
 ```
      ##### Ejecutado con la lista de productos desde el feature
      Feature: HU-001 Buscador Ebay
      Yo como usuario de Ebay
      Quiero buscar un producto en la plataforma
      Para ver el nombre del producto en pantalla

      Scenario Outline: Buscar producto
      Given Ingreso a la pagina de Ebay
      When busque el producto <NombreProducto>
      Then podre ver <NombreProducto> en pantalla
      Examples:
      |NombreProducto|
      |Lenovo IdeaPad Gaming 3 15.6 120Hz Laptop para Juegos AMD Ryzen 5-5600H 8GB Ram 512G|
      |Controlador inalámbrico Xbox choque Azul-inalámbrico y conectividad Bluetooth-Nuevo|
      |Samsung Galaxy S9 G960 Verizon TracFone Straight Talk total liberado B Page Plus|
      |Cable para Samsung Galaxy S20 S21 Cable Usb-C A USB-Carga Tipo C Charger C|
      |DIYGame Boy Advance GBA ITA TFT retroiluminación ajuste del brillo EVA Ayanami|

	  
      ##### Ejecutado backgrpund el cual permite ejecutar precondiciones en común con diferentes escenarios
      Feature: HU-001 Buscador Ebay
      Yo como usuario de Ebay
      Quiero buscar un producto en la plataforma
      Para ver el nombre del producto en pantalla

      Background: Navegar en la pagina
      Given Encontrar en la pagina de Ebay

      Scenario Outline: Busqueda de un producto
      When Encontrar un producto
      |<Producto>|
      Then voy a visualizar <Producto> en pantalla
      Examples:
      |Producto|
      |Google Pixel 3 XL 64GB Desbloqueado de fábrica pantalla de 6.3" 4G LTE Smartphone Nuevo|

      Scenario Outline: Busqueda de un producto
      When Encontrar un producto
      |<Producto>|
      Then voy a visualizar <Producto> en pantalla
      Examples:
      |Producto|
      |Auriculares Bluetooth para iPhone Samsung Android Inalámbrico Auriculares IPX7 Impermeable|
	  
	  ##### Ejecutado con escenarios fallido y exitoso
      Feature: HU-001 Buscador Ebay
      Yo como usuario de Ebay
      Quiero buscar un producto en la plataforma
      Para ver el nombre del producto en pantalla

      Scenario: Busqueda exitosa de un producto
      Given Entrar en la pagina de Ebay
      When Hallar un producto
      |Auriculares Bluetooth para iPhone Samsung Android Inalámbrico Auriculares IPX7 Impermeable|
      Then Habilitar en pantalla
      |Auriculares Bluetooth para iPhone Samsung Android Inalámbrico Auriculares IPX7 Impermeable|



      Scenario: Busqueda fallida de un producto
      Given Entrar en la pagina de Ebay
      When Hallar un producto
      |Google Pixel 2 XL 64GB Unlocked|
      Then Habilitar en pantalla
      |Google Pixel 2 XL 64GB Unlocked0|
 ```

