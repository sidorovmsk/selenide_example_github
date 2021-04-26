package tests.header;

import github.core.TestCore;
import github.pages.ViewportPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Epic")
@Feature("Feature")
@Story("FailedTests")
public class FailedTest extends TestCore {
    ViewportPage viewportPage = new ViewportPage();

    @Test
    @TmsLink("TmsLink")
    @DisplayName("checkHeaderElementsTest")
    public void checkHeaderElementsTest() {
        viewportPage.siteHeader.whyDropDown.elementExist();
        viewportPage.siteHeader.teamLink.elementExist();
        viewportPage.siteHeader.enterpriseLink.elementExist();
        viewportPage.siteHeader.exploreDropDown.elementExist();
        viewportPage.siteHeader.marketplaceLink.elementExist();
        viewportPage.siteHeader.pricingDropDown.elementAbsent();
    }

    @Test
    @TmsLink("TmsLink")
    @DisplayName("checkElementsInWhyDropDownTest")
    public void checkElementsInWhyDropDownTest() {
        viewportPage.siteHeader.whyDropDown.elementExist();
        viewportPage.siteHeader.whyDropDown.checkExist("Features");
        viewportPage.siteHeader.whyDropDown.checkExist("Mobile");
        viewportPage.siteHeader.whyDropDown.checkExist("Actions");
        viewportPage.siteHeader.whyDropDown.checkExist("Codespaces");
        viewportPage.siteHeader.whyDropDown.checkExist("Packages");
        viewportPage.siteHeader.whyDropDown.checkExist("Security");
        viewportPage.siteHeader.whyDropDown.checkExist("Code review");
        viewportPage.siteHeader.whyDropDown.checkExist("Project management");
        viewportPage.siteHeader.whyDropDown.checkExist("Integrations");
        viewportPage.siteHeader.whyDropDown.checkExist("GitHub Sponsors");
        viewportPage.siteHeader.whyDropDown.checkAbsent("Customer stories");
    }

    @Test
    @TmsLink("TmsLink")
    @DisplayName("checkCountInWhyDropDownTest")
    public void checkCountInWhyDropDownTest() {
        viewportPage.siteHeader.whyDropDown.checkCount(10);
    }

    @Test
    @TmsLink("TmsLink")
    @DisplayName("checkAbsentElementInWhyDropDownTest")
    public void checkAbsentElementInWhyDropDownTest() {
        viewportPage.siteHeader.whyDropDown.checkAbsent("awdawd");
        viewportPage.siteHeader.whyDropDown.checkAbsent("awdawsdasd");
        viewportPage.siteHeader.whyDropDown.checkAbsent("awasddawd");
        viewportPage.siteHeader.whyDropDown.checkAbsent("awdsaddawd");
        viewportPage.siteHeader.whyDropDown.checkAbsent("Security");
    }
}
