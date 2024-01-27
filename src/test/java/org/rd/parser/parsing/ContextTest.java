package org.rd.parser.parsing;

import org.rd.parser.QcmChoice;
import org.rd.parser.QcmIndicator;
import org.rd.parser.QcuIndicator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.rd.parser.ImportRow.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class ContextTest {

    @Test
    void definitionDUnIndicateurQcu() {
        // GIVEN
        List<Row> rows = List.of(createQcuIndicatorRow("indicateur qcu", 2023));

        Context context = new Context();

        // WHEN
        context.parseRows(rows);

        // THEN
        assertThat(context.getIndicators())
            .singleElement()
            .isInstanceOfSatisfying(
                QcuIndicator.class,
                indicator ->
                    assertThat(indicator)
                        .extracting(QcuIndicator::getName, QcuIndicator::period)
                        .containsExactly("indicateur qcu", 2023));
    }

    @Test
    void definitionDUnIndicateurQcmAvecSesChoix() {
        // GIVEN
        List<Row> rows = List.of(
            createQcmIndicatorRow("indicateur qcm", 2023),
            createQcmIndicatorChoiceRow("choix 1", true),
            createQcmIndicatorChoiceRow("choix 2", false));

        Context context = new Context();

        // WHEN
        context.parseRows(rows);

        // THEN
        assertThat(context.getIndicators())
            .singleElement()
            .isInstanceOfSatisfying(
                QcmIndicator.class,
                indicator -> {
                    assertThat(indicator)
                        .extracting(QcmIndicator::getName, QcmIndicator::period)
                        .containsExactly("indicateur qcm", 2023);
                    assertThat(indicator.getChoices())
                        .extracting(QcmChoice::getName, QcmChoice::getValue)
                        .containsExactly(
                            tuple("choix 1", true),
                            tuple("choix 2", false));
                });
    }

    @Test
    void definitionDePlusieursIndicateursDeTypesDifferents() {
        // GIVEN
        List<Row> rows = List.of(
            createQcuIndicatorRow("indicateur qcu 1", 2023),
            createQcmIndicatorRow("indicateur qcm", 2023),
            createQcmIndicatorChoiceRow("choix 1", true),
            createQcmIndicatorChoiceRow("choix 2", false),
            createQcuIndicatorRow("indicateur qcu 2", 2023));

        Context context = new Context();

        // WHEN
        context.parseRows(rows);

        // THEN
        assertThat(context.getIndicators())
            .hasSize(3)
            .satisfiesOnlyOnce(indicator ->
                assertThat(indicator)
                    .isInstanceOfSatisfying(
                        QcuIndicator.class,
                        qcuIndicator ->
                            assertThat(qcuIndicator)
                                .extracting(QcuIndicator::getName, QcuIndicator::period)
                                .containsExactly("indicateur qcu 1", 2023)))
            .satisfiesOnlyOnce(indicator ->
                assertThat(indicator)
                    .isInstanceOfSatisfying(
                        QcmIndicator.class,
                        qcmIndicator -> {
                            assertThat(qcmIndicator)
                                .extracting(QcmIndicator::getName, QcmIndicator::period)
                                .containsExactly("indicateur qcm", 2023);
                            assertThat(qcmIndicator.getChoices())
                                .extracting(QcmChoice::getName, QcmChoice::getValue)
                                .containsExactly(
                                    tuple("choix 1", true),
                                    tuple("choix 2", false));
                        }))
            .satisfiesOnlyOnce(indicator ->
                assertThat(indicator)
                    .isInstanceOfSatisfying(
                        QcuIndicator.class,
                        qcuIndicator ->
                            assertThat(qcuIndicator)
                                .extracting(QcuIndicator::getName, QcuIndicator::period)
                                .containsExactly("indicateur qcu 2", 2023)));
    }
}