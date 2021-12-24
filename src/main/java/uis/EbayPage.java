package uis;

import net.serenitybdd.screenplay.targets.Target;

public class EbayPage {

    public static final Target TXT_BUSCADOR = Target.the("").locatedBy("//input[@placeholder='Buscar artículos']");
    public static final Target BTN_BUSCADOR = Target.the("").locatedBy("//input[@class ='btn btn-prim gh-spr' ]");
    public static final Target BTN_ELEMENTO_BUSQUEDA = Target.the("").locatedBy("//li[@class='s-item s-item__pl-on-bottom']//div[@class='s-item__wrapper clearfix']//h3[contains(text(),'{0}')]");
    public static final Target TXT_ELEMENTO_BUSQUEDA = Target.the("").locatedBy("//h3[contains(text(),'{0}')]");

}
