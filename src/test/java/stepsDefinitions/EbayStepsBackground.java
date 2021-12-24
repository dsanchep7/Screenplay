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

import java.util.List;

public class EbayStepsBackground {

/*
    Actor actor = new Actor("Daniela");

    @Before
    public void before(){
        OnStage.setTheStage(new OnlineCast());
    }


    @Given("^que me encuentro en la pagina de Ebay$")
    public void queMeEncuentroEnLaPaginaDeEbay() {
        actor.can(BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://co.ebay.com/")));
    }

    @When("^Encontrar un producto$")
    public void encontrarUnProducto(List<String> Productos) {
        actor.attemptsTo(BuscadorEbay.EnEbay(Productos.get(0)));
    }

    @Then("^voy a visualizar (.*) en pantalla$")
    public void voyAVisualizarEnPantalla(List<String>Productos) {
        actor.should(GivenWhenThen.seeThat(WebElementQuestion.the(EbayPage.TXT_ELEMENTO_BUSQUEDA.of(Productos.get(0))), WebElementStateMatchers.containsText(Productos.get(0))));
    }
*/

    Actor actor = new Actor("Daniela");

    @Before
    public void before(){
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^Encontrar en la pagina de Ebay$")
    public void encontrarEnLaPaginaDeEbay() {

        actor.can(BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://co.ebay.com/")));
    }


    @When("^Encontrar un producto$")
    public void encontrarUnProducto(List<String> Productos) {
        actor.attemptsTo(BuscadorEbay.EnEbay(Productos.get(0)));
    }


    @Then("^voy a visualizar (.*) en pantalla$")
    public void voyAVisualizarEnPantalla(List<String>Productos) {
        actor.should(GivenWhenThen.seeThat(WebElementQuestion.the(EbayPage.TXT_ELEMENTO_BUSQUEDA.of(Productos.get(0))), WebElementStateMatchers.containsText(Productos.get(0))));
    }

}
