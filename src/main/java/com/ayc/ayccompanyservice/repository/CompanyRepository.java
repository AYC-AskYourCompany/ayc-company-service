package com.ayc.ayccompanyservice.repository;

import com.ayc.ayccompanyservice.model.CompanyEntity;
import com.ayc.ayccompanyservice.model.CompanyMemberDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository<CompanyEntity, String> {
    List<CompanyEntity> findAllByCeoOrCompanyMembers(String ceo, CompanyMemberDTO companyMember);
}
