package com.chromamon.analysis.rogers.reader;

import com.chromamon.analysis.rogers.model.rabbitmq.ChromatographyData;
import com.chromamon.analysis.rogers.model.rabbitmq.EnvironmentParameters;
import com.chromamon.analysis.rogers.model.rabbitmq.HistoryAndObservations;
import com.chromamon.analysis.rogers.model.rabbitmq.RabbitMQData;
import com.chromamon.analysis.rogers.model.rabbitmq.TransformerIdentification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RabbitMQReaderTest {

    private final RabbitMQReader rabbitMQReader = new RabbitMQReader();

    @Test
    void testReceiveMessage() {
        RabbitMQData data = new RabbitMQData();
        TransformerIdentification transformerIdentification = new TransformerIdentification();
        transformerIdentification.setTransformerId("T13542");
        transformerIdentification.setTransformerName("Transformer Test");
        transformerIdentification.setLocation("Test Location");
        transformerIdentification.setInstallationDate("2020-01-01 00:00:00");

        ChromatographyData chromatographyData = new ChromatographyData();
        chromatographyData.setAnalysisTimestamp("2024-11-26T10:00:00");
        chromatographyData.setH2(10);
        chromatographyData.setCo(50);
        chromatographyData.setCo2(12);
        chromatographyData.setCh4(2);
        chromatographyData.setC2h2(5);
        chromatographyData.setC2h4(3);
        chromatographyData.setC2h6(11);
        chromatographyData.setOilAcidity(32.5);
        chromatographyData.setOilPressure(1.02);
        chromatographyData.setOilTemperature(40);

        EnvironmentParameters environmentParameters = new EnvironmentParameters();
        environmentParameters.setEnvironmentTemperature(25);
        environmentParameters.setEnvironmentHumidity(60);
        environmentParameters.setAtmosphericPressure(1.01);

        HistoryAndObservations historyAndObservations = new HistoryAndObservations();
        historyAndObservations.setLastMaintenanceDate("2023-12-12 00:00:00");
        historyAndObservations.setMaintenanceDone("Test Maintenance");
        historyAndObservations.setObservations("Test Observations");

        data.setTransformerIdentification(transformerIdentification);
        data.setChromatographyData(chromatographyData);
        data.setEnvironmentParameters(environmentParameters);
        data.setHistoryAndObservations(historyAndObservations);

        rabbitMQReader.receiveMessage(data);

        assertNotNull(rabbitMQReader.read());
    }
}
