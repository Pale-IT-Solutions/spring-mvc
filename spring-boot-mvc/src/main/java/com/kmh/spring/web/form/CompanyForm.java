package com.kmh.spring.web.form;

import lombok.Data;

@Data
public class CompanyForm {

	private Integer id;
	private String name;
	private String address;
	private String type;
	private String phone;
	private String size;
}