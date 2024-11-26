package com.chromamon.analysis.rogers.processor;

import com.chromamon.analysis.rogers.model.AnalysisData;
import com.chromamon.analysis.rogers.model.DiagnosticData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RogersAnalysisProcessor implements ItemProcessor<AnalysisData, DiagnosticData> {

    @Override
    public DiagnosticData process(AnalysisData data) {
        log.info("Data processing started! - Document ID: {}", data.getDocumentId());
        String result = applyRogersMethod(data);
        log.info("Data process finished! - Document ID: {}", data.getDocumentId());
        return new DiagnosticData(
                data.getTransformerIdentification(),
                "Rogers",
                result,
                data.getAnalysisTimestamp(),
                data.getDocumentId()
        );
    }

    private String applyRogersMethod(AnalysisData data) {
        if (data.getC2h4() > data.getCo() && data.getC2h6() > data.getCo2()) {
            return "Electrical Fault";
        } else if (data.getCo2() > data.getCo()) {
            return "Thermal Fault";
        } else if (data.getC2h6() > data.getC2h4()) {
            return "Mechanical Fault";
        } else {
            return "Normal";
        }
    }
}
