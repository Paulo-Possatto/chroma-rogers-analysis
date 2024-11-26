package com.chromamon.analysis.rogers.repository;

import com.chromamon.analysis.rogers.model.AnalysisData;
import com.chromamon.analysis.rogers.model.DiagnosticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void updateAnalysisResult(String documentId, DiagnosticData data){
        Query query = new Query(Criteria.where("_id").is(documentId));
        Update update = new Update().set("analysis_rogers_result", data.getResult());

        mongoTemplate.updateFirst(query, update, "analysis_data");
    }
}
