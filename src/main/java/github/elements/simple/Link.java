package github.elements.simple;

import github.elements.ElementPrototype;
import io.qameta.allure.Step;

import static io.qameta.allure.Allure.getLifecycle;

public class Link extends ElementPrototype {
    public Link(String name, String xpath) {
        super(name, xpath);
    }

    @Step("Клик по ссылке -%name%-")
    public void click() {
        getLifecycle().updateStep(step -> step.setName(step.getName().replace("%name%", elementName)));
        findElement();
        clickElement();
    }
}
