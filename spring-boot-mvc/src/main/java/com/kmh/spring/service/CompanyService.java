package com.kmh.spring.service;

import java.util.List;

import com.kmh.spring.entity.Company;

public interface CompanyService {

	Integer save(Company company);

	void update(Company company);

	Company findById(Integer id);

	void deleteById(Integer id);

	void deleteAll();

	List<Company> findAll();
}
