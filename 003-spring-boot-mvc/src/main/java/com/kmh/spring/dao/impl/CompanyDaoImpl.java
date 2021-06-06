package com.kmh.spring.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.kmh.spring.dao.CompanyDao;
import com.kmh.spring.dao.mapper.CompanyMapper;
import com.kmh.spring.entity.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Integer save(Company company) {

		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT");
		sb.append(" INTO");
		sb.append(" COMPANY");
		sb.append(" (");
		sb.append("   name");
		sb.append("  ,address");
		sb.append("  ,type");
		sb.append("  ,phone");
		sb.append("  ,size");
		sb.append(" )");
		sb.append(" VALUES");
		sb.append(" (");
		sb.append("   :name");
		sb.append("  ,:address");
		sb.append("  ,:type");
		sb.append("  ,:phone");
		sb.append("  ,:size");
		sb.append(" )");
		String sql = sb.toString();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", company.getName());
		params.put("address", company.getAddress());
		params.put("type", company.getType());
		params.put("phone", company.getPhone());
		params.put("size", company.getSize());
		SqlParameterSource paramSource = new MapSqlParameterSource(params);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(sql, paramSource, keyHolder);

		int returnKey = keyHolder.getKey().intValue();

		return returnKey;
	}

	@Override
	public Company findById(Integer id) {

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT");
		sb.append("  *");
		sb.append(" FROM");
		sb.append(" COMPANY");
		sb.append(" WHERE");
		sb.append("  id = :id");
		String sql = sb.toString();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		SqlParameterSource paramSource = new MapSqlParameterSource(params);

		Company company = jdbcTemplate.queryForObject(sql, paramSource, companyMapper);

		return company;
	}

	@Override
	public void update(Company company) {

		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE");
		sb.append(" COMPANY");
		sb.append(" SET");
		sb.append("  id = :id");
		sb.append("  ,name = :name");
		sb.append("  ,address = :address");
		sb.append("  ,type = :type");
		sb.append("  ,phone = :phone");
		sb.append("  ,size = :size");
		sb.append(" WHERE");
		sb.append("  id = :id");
		String sql = sb.toString();

		// @formatter:off
		SqlParameterSource paramSource = new MapSqlParameterSource()
		.addValue("name", company.getName())
		.addValue("address", company.getAddress())
		.addValue("type", company.getType())
		.addValue("phone", company.getPhone())
		.addValue("size", company.getSize())
		.addValue("id", company.getId());
		// @formatter:on

		jdbcTemplate.update(sql, paramSource);
	}

	@Override
	public void deleteById(Integer id) {
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE");
		sb.append(" FROM");
		sb.append(" COMPANY");
		sb.append(" WHERE");
		sb.append(" id = :id");

		String sql = sb.toString();

		SqlParameterSource paramSource = new MapSqlParameterSource().addValue("id", id);
		jdbcTemplate.update(sql, paramSource);

	}

	@Override
	public void deleteAll() {

		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE");
		sb.append(" FROM");
		sb.append(" COMPANY");
		String sql = sb.toString();

		jdbcTemplate.update(sql, new MapSqlParameterSource());

	}

	@Override
	public List<Company> findAll() {

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT");
		sb.append(" *");
		sb.append(" FROM");
		sb.append(" COMPANY");
		String sql = sb.toString();

		List<Company> list = jdbcTemplate.query(sql, companyMapper);

		return list;
	}
}