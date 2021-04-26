package github.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.getLifecycle;

public abstract class ElementPrototype {
    protected SelenideElement element;
    protected String elementXPath;
    protected String elementName;
    protected final int defaultTimeout = 5000;

    public ElementPrototype() {
    }

    public ElementPrototype(String name, String xpath) {
        this.elementName = name;
        this.elementXPath = xpath;
    }

    @Step("Проверка отсутствия элемента '%name%' на экране")
    public void elementAbsent() {
        getLifecycle().updateStep(step -> step.setName(step.getName().replace("%name%", elementName)));
        $x(elementXPath).waitUntil(Condition.disappear, defaultTimeout);
        //TODO: данный метод проверяет неотображение элементов на экране,
        // но при этом не делает разницы между "виден" и отсутствует - надо понять насколько это критично
    }

    @Step("Ожидание исчезновения элемента '%name%' с экрана в течении {seconds} секунд")
    public void elementAbsent(int seconds) {
        getLifecycle().updateStep(step -> step.setName(step.getName().replace("%name%", elementName)));
        $x(elementXPath).waitUntil(Condition.disappear, 1000 * seconds);
    }

    @Step("Проверка наличия элемента '%name%' на экране")
    public void elementExist() {
        getLifecycle().updateStep(step -> step.setName(step.getName().replace("%name%", elementName)));
        $x(elementXPath).waitUntil(Condition.exist, defaultTimeout);
    }

    @Step("Ожидание наличия элемента '%name%' на экране в течении {seconds} секунд")
    public void elementExist(int seconds) {
        getLifecycle().updateStep(step -> step.setName(step.getName().replace("%name%", elementName)));
        $x(elementXPath).waitUntil(Condition.exist, seconds * 1000);
        getLifecycle().updateStep(step -> step.getParameters().remove(0));
    }

    @Step("Проверка активности элемента '%name%'")
    public void elementEnabled() {
        getLifecycle().updateStep(step -> step.setName(step.getName().replace("%name%", elementName)));
        $x(elementXPath).waitWhile(Condition.attribute("disabled"), defaultTimeout);
    }

    @Step("Проверка неактивности элемента '%name%'")
    public void elementDisabled() {
        getLifecycle().updateStep(step -> step.setName(step.getName().replace("%name%", elementName)));
        $x(elementXPath).waitUntil(Condition.attribute("disabled", "true"), defaultTimeout);
    }

    protected void findElement() {
        element = $x(elementXPath).waitUntil(Condition.exist, defaultTimeout);
    }

    protected void checkElementText(String expectedValue) {
        element.waitUntil(Condition.text(expectedValue), defaultTimeout);
    }

    protected void checkElementAttribute(String attributeName, String attributeValue) {
        Assertions.fail("Не реализовано");
    }

    protected boolean checkElementAttributeContains(String attributeName, String attributePartValue) {
        if (element.getAttribute(attributeName).contains(attributePartValue)) {
            return true;
        }
        return false;
    }

    protected void clickElement() {
        element.click();
    }
}
