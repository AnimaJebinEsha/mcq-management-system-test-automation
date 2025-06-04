package tech.innospace.tests;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.*;
import tech.innospace.pages.*;
import tech.innospace.utils.JSONUtils;
import tech.innospace.utils.WaitUtils;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class McqFlowTest extends BaseTest {

    private static JsonNode testData;

    @BeforeAll
    public static void loginOnce() throws Exception {
        testData = JSONUtils.readJsonFile("src/test/resources/mcq_test_data.json");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidCredentials();

        assertTrue(loginPage.isAtDashboard(), "Login did not redirect to Dashboard.");
    }

    @Test
    @Order(1)
    public void testMcqCreation() {
        navigateToMcqCreatePage();
        createMcq();
        navigateToMcqListPage();
        verifyMcqCreation();
    }

    @Test
    @Order(2)
    public void testMcqUpdate() {
        navigateToMcqListPage();
        verifyMcqCreation();
        navigateToMcqUpdatePage();
        WaitUtils.waitForSeconds(2);
        updateMcQ();
        navigateToMcqListPage();
        verifyMcqUpdate();
    }

    @Test
    @Order(3)
    public void testMcqDeletion() {
        navigateToMcqListPage();
        deleteMcq();
        verifyMcqDelete();
    }

    private void navigateToMcqCreatePage() {
        Sidebar sidebar = new Sidebar(driver);
        sidebar.openMcqDropdown();
        sidebar.clickMcqCreateLink();

        assertTrue(sidebar.isAtMcqCreatePage(), "Mcq Create page did not open.");
    }

    private void createMcq() {
        JsonNode creation = testData.get("creation");

        McqCreatePage mcqCreatePage = new McqCreatePage(driver);
        mcqCreatePage.selectClassItem(creation.get("class").asText());
        mcqCreatePage.selectCourseItem(creation.get("course").asText());
        mcqCreatePage.selectSubjectItem(creation.get("subject").asText());
        mcqCreatePage.selectChapterItem(creation.get("chapter").asText());
        mcqCreatePage.selectTopicItem(creation.get("topic").asText());
        mcqCreatePage.selectDifficulty(creation.get("difficulty").asText());
        mcqCreatePage.writeQuestion(creation.get("question").asText());

        String[] options = new String[4];

        for (int i = 0; i < 4; i++) {
            options[i] = creation.get("options").get(i).asText();
        }

        mcqCreatePage.setOptions(options);
        mcqCreatePage.selectCorrectOption(creation.get("correctOptionIndex").asInt());
        mcqCreatePage.clickSubmitButton();
        assertTrue(mcqCreatePage.isSuccessNotificationVisible(), "The success notification was not displayed after creating the MCQ.");
    }

    private void navigateToMcqListPage() {
        McqListPage mcqListPage = new McqListPage(driver);
        mcqListPage.open();
        WaitUtils.waitForSeconds(5);
    }

    private void verifyMcqCreation() {
        JsonNode creation = testData.get("creation");

        McqListPage mcqListPage = new McqListPage(driver);
        mcqListPage.selectClassItem(creation.get("class").asText());
        mcqListPage.selectCourseItem(creation.get("course").asText());
        mcqListPage.selectSubjectItem(creation.get("subject").asText());
        mcqListPage.selectChapterItem(creation.get("chapter").asText());
        mcqListPage.selectTopicItem(creation.get("topic").asText());

        try {
            assertNotNull(mcqListPage.getElementsByQuestionText(creation.get("question").asText()), "The question '" + creation.get("question").asText() + "' was not found in the list.");
        } catch (Exception e) {
            throw new AssertionError("The question '" + creation.get("question").asText() + "' was not found in the list.");
        }
    }

    private void navigateToMcqUpdatePage() {
        McqListPage mcqListPage = new McqListPage(driver);
        String rowId = mcqListPage.getRowId();
        mcqListPage.clickEditButton(rowId);
    }

    private void updateMcQ() {
        JsonNode update = testData.get("update");

        McqUpdatePage mcqUpdatePage = new McqUpdatePage(driver);
        mcqUpdatePage.selectDifficulty(update.get("difficulty").asText());
        mcqUpdatePage.writeQuestion(update.get("question").asText());

        String[] options = new String[4];

        for (int i = 0; i < 4; i++) {
            options[i] = update.get("options").get(i).asText();
        }

        mcqUpdatePage.setOptions(options);
        mcqUpdatePage.unSelectCorrectOption(update.get("unselectCorrectOptionIndex").asInt());
        mcqUpdatePage.selectCorrectOption(update.get("correctOptionIndex").asInt());
        mcqUpdatePage.clickUpdateButton();
    }

    private void verifyMcqUpdate() {
        JsonNode creation = testData.get("creation");
        JsonNode update = testData.get("update");
        JsonNode verify = testData.get("verify");

        McqListPage mcqListPage = new McqListPage(driver);
        mcqListPage.selectClassItem(creation.get("class").asText());
        mcqListPage.selectCourseItem(creation.get("course").asText());
        mcqListPage.selectSubjectItem(creation.get("subject").asText());
        mcqListPage.selectChapterItem(creation.get("chapter").asText());
        mcqListPage.selectTopicItem(creation.get("topic").asText());

        String rowId = mcqListPage.getRowId();

        McqViewPage mcqViewPage = new McqViewPage(driver);
        mcqViewPage.open(rowId);

        assertEquals(update.get("question").asText(), mcqViewPage.getQuestionValue(), "The question text did not match the expected value after update.");
        assertEquals(update.get("difficulty").asText(), mcqViewPage.getDifficultyValue(), "The difficulty did not match the expected value after update.");
        assertTrue(mcqViewPage.isCorrectOptionSelected(verify.get("updatedCorrectOption").asText()), "The correct option '" + verify.get("updatedCorrectOption").asText() + "' was not selected after update.");
    }

    private void deleteMcq() {
        McqListPage mcqListPage = new McqListPage(driver);
        String rowId = mcqListPage.getRowId();
        mcqListPage.clickDeleteButton(rowId);
        WaitUtils.waitForSeconds(3);
    }

    private void verifyMcqDelete() {
        JsonNode creation = testData.get("creation");
        JsonNode update = testData.get("update");

        McqListPage mcqListPage = new McqListPage(driver);
        mcqListPage.selectClassItem(creation.get("class").asText());
        mcqListPage.selectCourseItem(creation.get("course").asText());
        mcqListPage.selectSubjectItem(creation.get("subject").asText());
        mcqListPage.selectChapterItem(creation.get("chapter").asText());
        mcqListPage.selectTopicItem(creation.get("topic").asText());

        boolean isQuestionPresent;

        try {
            isQuestionPresent = mcqListPage.getElementsByQuestionText(update.get("question").asText()) != null;
        } catch (Exception e) {
            isQuestionPresent = false;
        }

        assertFalse(isQuestionPresent);
    }
}
