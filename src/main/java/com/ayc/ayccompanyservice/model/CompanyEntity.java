package com.ayc.ayccompanyservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("company")
public class CompanyEntity {

    @Id
    private String companyId;
    private String companyName;
    private String companyDescription;
    private String ceo;
    private List<CompanyMemberDTO> companyMembers;
}
