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
