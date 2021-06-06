package com.kmh.spring.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kmh.spring.entity.Company;

@Component
public class CompanyMapper implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {

		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		company.setAddress(rs.getString("address"));
		company.setType(rs.getString("type"));
		company.setPhone(rs.getString("phone"));
		company.setSize(rs.getString("size"));

		return company;
	}
}