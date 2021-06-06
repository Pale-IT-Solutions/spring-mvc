package com.kmh.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kmh.spring.dao.CompanyDao;
import com.kmh.spring.entity.Company;
import com.kmh.spring.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao dao;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer save(Company company) {
		Integer id = this.dao.save(company);
		return id;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(Company company) {
		this.dao.update(company);
	}

	@Override
	public Company findById(Integer id) {
		Company company = this.dao.findById(id);
		return company;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteById(Integer id) {
		this.dao.deleteById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteAll() {
		this.dao.deleteAll();
	}

	@Override
	public List<Company> findAll() {
		List<Company> list = this.dao.findAll();
		return list;
	}
}