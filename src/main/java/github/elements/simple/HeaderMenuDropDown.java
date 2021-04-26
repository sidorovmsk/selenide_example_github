package github.elements.simple;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import github.elements.ElementPrototype;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.textCaseSensitive;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.getLifecycle;

/**
 * Класс для работы с выпадающим списком HeaderMenu
 */
public class HeaderMenuDropDown extends ElementPrototype {
    private String collectionBlockXPath;
    String collectionElementAppendix = "//a";

    /**
     * стандартный конструктор элементов снаследованый от ElementPrototype с использованием стандартного элемента выпадающего списка
     *
     * @param name       имя выпадающего списка
     * @param inputXPath путь до элемента вида <div class="el-select">
     */
    public HeaderMenuDropDown(String name, String inputXPath) {
        super(name, inputXPath);
        this.collectionBlockXPath = "//details[@open='']//div";
    }

    @Step("Выбор в выпадающем списке '%name%' элемента {elementName}")
    public void select(String elementName) {
        getLifecycle().updateStep(step -> step.setName(step.getName().replace("%name%", this.elementName)));
        findElement();
        clickElement();
        crutch();
        $x(collectionBlockXPath).waitUntil(Condition.exist, defaultTimeout);
        selectItemFromList(elementName);
        getLifecycle().updateStep(step -> step.getParameters().remove(0));
    }

    @Step("Проверка наличия в выпадающем списке '%name%' элемента {elementName}")
    public void checkExist(String elementName) {
        getLifecycle().updateStep(step -> step.setName(step.getName().replace("%name%", this.elementName)));
        findElement();
        clickElement();
        crutch();
        if (!checkItemInListExist(elementName)) {
            Assertions.fail("В списке нет элемента '" + elementName + "', есть только: " + getAllDropDownElementsAsString());
        }
        clickElement();
        getLifecycle().updateStep(step -> step.getParameters().remove(0));
    }

    @Step("Проверка наличия {elementsCount} строк в выпадающем списке '%name%'")
    public void checkCount(int elementsCount) {
        getLifecycle().updateStep(step -> step.setName(step.getName().replace("%name%", this.elementName)));
        findElement();
        clickElement();
        if ($$x(collectionBlockXPath + collectionElementAppendix).size() == 0)
            clickElement();
        $$x(collectionBlockXPath + collectionElementAppendix).shouldHave(CollectionCondition.size(elementsCount), defaultTimeout);
        clickElement();
        getLifecycle().updateStep(step -> step.getParameters().remove(0));
    }

    @Step("Проверка отсутствия в выпадающем списке '%name%' элемента {elementName}")
    public void checkAbsent(String elementName) {
        getLifecycle().updateStep(step -> step.setName(step.getName().replace("%name%", this.elementName)));
        findElement();
        clickElement();
        crutch();
        if (checkItemInListExist(elementName)) {
            Assertions.fail("В списке есть элемент '" + elementName + "' (" + getAllDropDownElementsAsString() + ")");
        }
        clickElement();
        getLifecycle().updateStep(step -> step.getParameters().remove(0));
    }

    protected SelenideElement getItemFromListByName(String itemName) {
        $x(collectionBlockXPath + collectionElementAppendix).waitUntil(Condition.exist, defaultTimeout);
        return $$x(collectionBlockXPath + collectionElementAppendix).findBy(textCaseSensitive(itemName));
    }

    protected void selectItemFromList(String itemName) {
        SelenideElement foundItem = getItemFromListByName(itemName);
        if (foundItem.exists()) {
            foundItem.click();
        } else {
            Assertions.fail("В списке нет элемента '" + itemName + "', есть только: " + getAllDropDownElementsAsString());
        }
    }

    protected String getAllDropDownElementsAsString() {
        List<String> values = new ArrayList<>();
        for (SelenideElement line : $$x(collectionBlockXPath + collectionElementAppendix)) {
            values.add(line.text());
        }
        return Arrays.toString(values.toArray());
    }

    protected boolean checkItemInListExist(String itemName) {
        SelenideElement foundItem = getItemFromListByName(itemName);
        if (foundItem.exists()) {
            return true;
        }
        return false;
    }

    // TODO: 25.04.2021 в некоторых случаях при клике на элемент блок не открывается и нужно повторно кликать элемент
    private void crutch() {
        try {
            $x(collectionBlockXPath).waitUntil(Condition.exist, defaultTimeout);
        } catch (ElementNotFound e) {
            clickElement();
            $x(collectionBlockXPath).waitUntil(Condition.exist, defaultTimeout);
        }
    }
}
