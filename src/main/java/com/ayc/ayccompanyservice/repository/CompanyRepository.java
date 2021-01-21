package com.ayc.ayccompanyservice.repository;

import com.ayc.ayccompanyservice.model.CompanyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<CompanyEntity, String> {
}
