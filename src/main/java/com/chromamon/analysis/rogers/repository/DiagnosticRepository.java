package com.chromamon.analysis.rogers.repository;

import com.chromamon.analysis.rogers.model.DiagnosticData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticRepository extends JpaRepository<DiagnosticData, Long> {
}
