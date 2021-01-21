package com.ayc.ayccompanyservice.service;

import com.ayc.ayccompanyservice.consts.ErrorConst;
import com.ayc.ayccompanyservice.model.CompanyEntity;
import com.ayc.ayccompanyservice.repository.CompanyRepository;
import com.ayc.exceptionhandler.exception.EntityNotFoundException;
import com.ayc.exceptionhandler.exception.NotAuthorizedException;
import com.ayc.keycloaksecurity.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SecurityUtil securityUtil;

    public CompanyEntity getCompanyById(String companyId) throws EntityNotFoundException, NotAuthorizedException {
        CompanyEntity company = this.findCompanyById(companyId);

        return company;
    }

    public CompanyEntity saveCompany(CompanyEntity company) {
        if (company.getCompanyMember() == null) {
            company.setCompanyMember(new ArrayList<String>());
        }
        if (company.getCeo() == null) {
            company.setCeo(this.securityUtil.getUsername());
        }

        return this.companyRepository.save(company);
    }

    public CompanyEntity updateCompany(CompanyEntity company) throws NotAuthorizedException {
        this.securityUtil.isAdminOrUser(company.getCeo());

        return this.companyRepository.save(company);
    }

    public CompanyEntity addCompanyMember(String companyId, String username) throws EntityNotFoundException, NotAuthorizedException {
        CompanyEntity company = this.findCompanyById(companyId);

        this.securityUtil.isAdminOrUser(username);
        company.getCompanyMember().add(username);

        return this.companyRepository.save(company);
    }

    public CompanyEntity deleteCompanyMember(String companyId, String username) throws EntityNotFoundException, NotAuthorizedException {
        CompanyEntity company = this.findCompanyById(companyId);

        this.securityUtil.isAdminOrUser(username);
        company.getCompanyMember().remove(username);

        return this.companyRepository.save(company);
    }

    public void deleteCompany(String companyId) throws EntityNotFoundException, NotAuthorizedException {
        CompanyEntity company = this.findCompanyById(companyId);

        this.securityUtil.isAdminOrUser(company.getCeo());

        this.companyRepository.deleteById(companyId);
    }

    private CompanyEntity findCompanyById(String companyId) throws EntityNotFoundException, NotAuthorizedException {
        CompanyEntity company = this.companyRepository.findById(companyId)
                .orElseThrow((() -> new EntityNotFoundException(String.format(ErrorConst.COMPANY_NOT_FOUND, companyId))));
        Boolean projectMemberOrCeo = this.securityUtil.getUsername().equals(company.getCeo());

        for (String companyMember : company.getCompanyMember()) {
            if (companyMember.equals(this.securityUtil.getUsername())) {
                projectMemberOrCeo = true;
            }
        }
        if (!projectMemberOrCeo) {
            throw new NotAuthorizedException(String.format(ErrorConst.NOT_AUTHORIZED, company.getCompanyName()));
        }

        return company;
    }
}
