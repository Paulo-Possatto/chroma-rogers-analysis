package com.chromamon.analysis.rogers.controller;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RogersAnalysisControllerTest {

    @Autowired
    private RogersAnalysisController rogersAnalysisController;

    @Autowired
    private JobLauncher jobLauncher;

    @Test
    void testStartAnalysisJob() throws Exception {
        ResponseEntity<String> response = rogersAnalysisController.startAnalysisJob();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Job Started!", response.getBody());
    }
}
