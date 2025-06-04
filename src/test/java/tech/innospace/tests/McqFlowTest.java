package tech.innospace.tests;

import org.junit.jupiter.api.Test;
import tech.innospace.pages.LoginPage;
import tech.innospace.pages.McqCreatePage;
import tech.innospace.pages.Sidebar;
import tech.innospace.utils.WaitUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class McqFlowTest extends BaseTest {

    @Test
    public void testMcqFlow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();
        assertTrue(loginPage.isAtDashboard(), "Login did not redirect to Dashboard.");

        Sidebar sidebar = new Sidebar(driver);
        sidebar.openMcqDropdown();
        sidebar.clickMcqCreateLink();
        assertTrue(sidebar.isAtMcqCreatePage(), "Mcq Create page did not open.");

        McqCreatePage mcqCreatePage = new McqCreatePage(driver);
        mcqCreatePage.selectClassItem("AutomationClass1");
        mcqCreatePage.selectCourseItem("AutomationCourse1");
        mcqCreatePage.selectSubjectItem("AutomationSubject1");
        mcqCreatePage.selectChapterItem("AutomationChapter1");
        mcqCreatePage.selectTopicItem("AutomationTopic1");
        mcqCreatePage.selectDifficulty("Easy");
        mcqCreatePage.writeQuestion("What is 2 + 2?");
        mcqCreatePage.setOptions(new String[]{"1", "2", "3", "4"});
        mcqCreatePage.selectCorrectOption(3);
        mcqCreatePage.clickSubmitButton();

        WaitUtils.waitForSeconds(10);
    }
}
