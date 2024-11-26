package com.chromamon.analysis.rogers.writer;

import com.chromamon.analysis.rogers.model.DiagnosticData;
import com.chromamon.analysis.rogers.repository.DiagnosticRepository;
import com.chromamon.analysis.rogers.repository.MongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.item.Chunk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RogersAnalysisWriterTest {

    @Mock
    private DiagnosticRepository diagnosticRepository;

    @Mock
    private MongoRepository mongoRepository;

    @InjectMocks
    private DiagnosticResultWriter writer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testWrite() throws Exception {
        when(diagnosticRepository.count()).thenReturn(1L);

        DiagnosticData data = new DiagnosticData();
        data.setResult("Normal");
        data.setDocumentId("testId");

        writer.write(Chunk.of(data));

        verify(diagnosticRepository, times(1)).saveAll(any());

        verify(mongoRepository, times(1)).updateAnalysisResult(eq("testId"), eq(data));

        assertEquals(1L, diagnosticRepository.count());
    }

}
