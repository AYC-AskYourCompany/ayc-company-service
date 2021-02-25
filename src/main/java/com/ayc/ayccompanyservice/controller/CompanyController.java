package com.ayc.ayccompanyservice.controller;

import com.ayc.ayccompanyservice.model.CompanyEntity;
import com.ayc.ayccompanyservice.service.CompanyService;
import com.ayc.exceptionhandler.exception.EntityNotFoundException;
import com.ayc.exceptionhandler.exception.NotAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{companyId}")
    public CompanyEntity getCompanyById(@PathVariable("companyId") String companyId) throws EntityNotFoundException, NotAuthorizedException {
        return this.companyService.getCompanyById(companyId);
    }

    @GetMapping
    public List<CompanyEntity> getCompanyByUsername() {
        return this.companyService.getCompanyByUsername();
    }

    @PostMapping
    public CompanyEntity saveCompany(@RequestBody CompanyEntity company) {
        return this.companyService.saveCompany(company);
    }

    @PutMapping
    public CompanyEntity updateCompany(@RequestBody CompanyEntity company) throws NotAuthorizedException {
        return this.companyService.updateCompany(company);
    }

    @PutMapping("/{companyId}/{username}")
    public CompanyEntity addCompanyMember(@PathVariable("companyId") String companyId, @PathVariable("username") String username) throws NotAuthorizedException, EntityNotFoundException {
        return this.companyService.addCompanyMember(companyId, username);
    }

    @DeleteMapping("/{companyId}/{username}")
    public CompanyEntity deleteCompanyMember(@PathVariable("companyId") String companyId, @PathVariable("username") String username) throws NotAuthorizedException, EntityNotFoundException {
        return this.companyService.deleteCompanyMember(companyId, username);
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompany(@PathVariable("companyId") String companyId) throws NotAuthorizedException, EntityNotFoundException {
        this.companyService.deleteCompany(companyId);
    }
}
