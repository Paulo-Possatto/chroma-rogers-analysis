package com.chromamon.analysis.rogers.processor;

import com.chromamon.analysis.rogers.model.AnalysisData;
import com.chromamon.analysis.rogers.model.DiagnosticData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RogersAnalysisProcessorTest {

    @Test
    void testProcessAnalysis() {
        RogersAnalysisProcessor processor = new RogersAnalysisProcessor();
        AnalysisData input = new AnalysisData();
        input.setH2(2);
        input.setCo(120);
        input.setCo2(320);
        input.setC2h4(50);
        input.setCh4(12);
        input.setC2h2(13);
        input.setC2h6(3);

        DiagnosticData result = processor.process(input);

        assertNotNull(result);
        assertEquals("Thermal Fault", result.getResult());
    }
}
