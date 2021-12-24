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
/*

    @Given("^que me encuentro en la pagina de Ebay$")
    public void queMeEncuentroEnLaPaginaDeEbay() {actor.can
            (BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://co.ebay.com/")));}



 */

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
