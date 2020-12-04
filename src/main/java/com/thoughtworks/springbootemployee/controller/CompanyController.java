package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    private final CompanyMapper companyMapper;

    public CompanyController(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public List<CompanyResponse> getAll() {
        return companyService.getAll().stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{companyId}")
    public CompanyResponse getSpecificCompany(@PathVariable String companyId) {
        return companyMapper.toResponse(companyService.get(companyId));
    }

    @GetMapping("/{companyId}/employees")
    public List<String> getSpecificCompanyEmployees(@PathVariable String companyId) {
        return companyService.getEmployeeList(companyId);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<CompanyResponse> getAllByPaging(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize
    ) {
        return companyService.getAllByPage(page, pageSize)
                .getContent().stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse create(@RequestBody CompanyRequest companyUpdate) {
        Company company = companyService.add(companyMapper.toEntity(companyUpdate));
        return companyMapper.toResponse(company);
    }

    @PutMapping("/{companyId}")
    public CompanyResponse update(@PathVariable String companyId, @RequestBody Company companyUpdate) {
        return companyMapper.toResponse(companyService.update(companyId, companyUpdate));
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String companyId) {
        companyService.remove(companyId);
    }
}
