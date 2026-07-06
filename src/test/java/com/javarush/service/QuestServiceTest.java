package com.javarush.service;

import com.javarush.model.QuestStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

//@Disabled
class QuestServiceTest {

    private static final String EXPECTED_STEP_START_ID = "start";
    private static final String EXPECTED_STEP_HALLWAY_ID = "hallway";
    private static final String EXPECTED_STEP_HOTEL_ROOM_ID = "hotelRoom";
    private static final String EXPECTED_STEP_FINAL_ID = "finalStep";
    private static final String EXPECTED_STEP_CLEAN_SHEET_ID = "cleanSheet";
    private static final String EXPECTED_STEP_TRUE_FINAL_ID = "trueFinal";
    private static final String EXPECTED_STEP_TIMELESSNESS_ID = "timelessness";
    private static final String EXPECTED_STEP_DEFEAT_ID = "defeat";


    private QuestService questService;

    @BeforeEach
    void SetUp() {
        questService = new QuestService();
    }

    @ParameterizedTest
    @ValueSource(strings = {EXPECTED_STEP_START_ID, EXPECTED_STEP_HALLWAY_ID, EXPECTED_STEP_HOTEL_ROOM_ID,
            EXPECTED_STEP_FINAL_ID, EXPECTED_STEP_CLEAN_SHEET_ID, EXPECTED_STEP_TRUE_FINAL_ID, EXPECTED_STEP_TIMELESSNESS_ID, EXPECTED_STEP_DEFEAT_ID})
    @DisplayName("Тест на существование всех шагов")
    void stepShouldExist(String stepId) {
        QuestStep step = questService.getStep(stepId);
        assertNotNull(step, "Шаг '" + stepId + "' не должен быть null");
    }

    @ParameterizedTest
    @ValueSource(strings = {EXPECTED_STEP_START_ID, EXPECTED_STEP_HALLWAY_ID, EXPECTED_STEP_HOTEL_ROOM_ID,
            EXPECTED_STEP_FINAL_ID, EXPECTED_STEP_CLEAN_SHEET_ID, EXPECTED_STEP_TRUE_FINAL_ID, EXPECTED_STEP_TIMELESSNESS_ID, EXPECTED_STEP_DEFEAT_ID})
    @DisplayName("Тест наличия необходимых Step ID")
    void stepHasRightId(String stepId) {
        QuestStep step = questService.getStep(stepId);
        assertEquals(stepId ,step.getId(), "ID шага должен быть '" + stepId + "'");
    }

    @ParameterizedTest
    @ValueSource(strings = {EXPECTED_STEP_START_ID, EXPECTED_STEP_HALLWAY_ID, EXPECTED_STEP_HOTEL_ROOM_ID,
            EXPECTED_STEP_FINAL_ID, EXPECTED_STEP_CLEAN_SHEET_ID, EXPECTED_STEP_TRUE_FINAL_ID, EXPECTED_STEP_TIMELESSNESS_ID, EXPECTED_STEP_DEFEAT_ID})
    @DisplayName("Тест наличия текста у шагов")
    void stepHasText(String stepId) {
        QuestStep step = questService.getStep(stepId);
        assertFalse(step.getText().isBlank(), "Шаг '" + stepId + "' должен иметь текст");
    }

    @ParameterizedTest
    @ValueSource(strings = {EXPECTED_STEP_START_ID, EXPECTED_STEP_HALLWAY_ID, EXPECTED_STEP_HOTEL_ROOM_ID, EXPECTED_STEP_FINAL_ID})
    @DisplayName("Тест наличия параметров у не финальных шагов")
    void nonFinalStepHasAttributes(String stepId) {
        QuestStep step = questService.getStep(stepId);
        assertAll(
                () -> assertFalse(step.getNextStep1id().isBlank(), "Шаг '" + stepId + "' должен иметь id следующего шага №1"),
                () -> assertFalse(step.getOption1().isBlank(), "Шаг '" + stepId + "' должен иметь текст выбора следующего шага №1"),
                () -> assertNotNull(step.getFragment1id(), "Модификатор осколков для шага №1 '" + stepId + "' должен существовать"),
                () -> assertNotNull(step.getLucidity1id(), "Модификатор рассудка для шага №1 '" + stepId + "' должен существовать"),
                () -> assertFalse(step.getNextStepText1id().isBlank(), "Шаг '" + stepId + "' должен иметь текст для передачи следующему шагу №1"),

                () -> assertFalse(step.getNextStep2id().isBlank(), "Шаг '" + stepId + "' должен иметь id следующего шага №2"),
                () -> assertFalse(step.getOption2().isBlank(), "Шаг '" + stepId + "' должен иметь текст выбора следующего шага №2"),
                () -> assertNotNull(step.getFragment2id(), "Модификатор осколков для шага №2 '" + stepId + "' должен существовать"),
                () -> assertNotNull(step.getLucidity2id(), "Модификатор рассудка для шага №2 '" + stepId + "' должен существовать"),
                () -> assertFalse(step.getNextStepText2id().isBlank(), "Шаг '" + stepId + "' должен иметь текст для передачи следующему шагу №2")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {EXPECTED_STEP_CLEAN_SHEET_ID, EXPECTED_STEP_TRUE_FINAL_ID, EXPECTED_STEP_TIMELESSNESS_ID, EXPECTED_STEP_DEFEAT_ID})
    @DisplayName("Тест отсутствия параметров у финальных шагов")
    void finalStepHasNoAttributes(String stepId) {
        QuestStep step = questService.getStep(stepId);
        assertAll(
                () -> assertNull(step.getNextStep1id(), "Финал '" + stepId + "' не должен иметь id следующего шага №1"),
                () -> assertNull(step.getOption1(), "Финал '" + stepId + "' не должен иметь текст выбора следующего шага №1"),
                () -> assertNull(step.getFragment1id(), "Модификатор осколков для финала '" + stepId + "' не должен существовать"),
                () -> assertNull(step.getLucidity1id(), "Модификатор рассудка для финала '" + stepId + "' не должен существовать"),
                () -> assertNull(step.getNextStepText1id(), "Финал '" + stepId + "' не должен иметь текст для передачи следующему шагу №1"),

                () -> assertNull(step.getNextStep2id(), "Финал '" + stepId + "' не должен иметь id следующего шага №2"),
                () -> assertNull(step.getOption2(), "Финал '" + stepId + "' не должен иметь текст выбора следующего шага №2"),
                () -> assertNull(step.getFragment2id(), "Модификатор осколков для финала '" + stepId + "' не должен существовать"),
                () -> assertNull(step.getLucidity2id(), "Модификатор рассудка для финала '" + stepId + "' не должен существовать"),
                () -> assertNull(step.getNextStepText2id(), "Финал '" + stepId + "' не должен иметь текст для передачи следующему шагу №2")
        );
    }

    @Test
    @DisplayName("Тест получения несуществующего шага")
    void getNonExistentStep() {
        QuestStep step = questService.getStep("non-existent");
        assertNull(step, "Несуществующий шаг должен возвращать null");
    }

    @ParameterizedTest
    @MethodSource("provideStepId")
    @DisplayName("Тест для проверки корректности ветвления логики квеста")
    void correctnessOfLogicBranching(String stepId, String expectedNextStep1Id, String expectedNextStep2Id){
        QuestStep step = questService.getStep(stepId);

        assertAll("Связи шага: " + stepId,
                () -> assertNotNull(step, "Шаг не найден"),
                () -> assertEquals(expectedNextStep1Id, step.getNextStep1id(), "Неверный путь №1"),
                () -> assertEquals(expectedNextStep2Id, step.getNextStep2id(), "Неверный путь №2")
        );
    }

    static Stream<Arguments> provideStepId() {
        return Stream.of(
                arguments(EXPECTED_STEP_START_ID, EXPECTED_STEP_HOTEL_ROOM_ID, EXPECTED_STEP_HALLWAY_ID),
                arguments(EXPECTED_STEP_HALLWAY_ID, EXPECTED_STEP_HOTEL_ROOM_ID, EXPECTED_STEP_HOTEL_ROOM_ID),
                arguments(EXPECTED_STEP_HOTEL_ROOM_ID, EXPECTED_STEP_FINAL_ID, EXPECTED_STEP_FINAL_ID),
                arguments(EXPECTED_STEP_FINAL_ID, EXPECTED_STEP_CLEAN_SHEET_ID, EXPECTED_STEP_TRUE_FINAL_ID)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {EXPECTED_STEP_CLEAN_SHEET_ID, EXPECTED_STEP_TRUE_FINAL_ID, EXPECTED_STEP_TIMELESSNESS_ID, EXPECTED_STEP_DEFEAT_ID})
    @DisplayName("Тест: шаг должен быть финальным")
    void isFinalStep(String stepId) {
        QuestStep step = questService.getStep(stepId);
        assertTrue(questService.isFinalStep(step), "Шаг '" + stepId + "' должен быть финальным шагом");
    }

    @ParameterizedTest
    @ValueSource(strings = {EXPECTED_STEP_START_ID, EXPECTED_STEP_HALLWAY_ID, EXPECTED_STEP_HOTEL_ROOM_ID, EXPECTED_STEP_FINAL_ID})
    @DisplayName("Тест: шаг не должен быть финальным")
    void isNotFinalStep(String stepId) {
        QuestStep step = questService.getStep(stepId);
        assertFalse(questService.isFinalStep(step), "Шаг '" + stepId + "' не должен быть финальным шагом");
    }

}