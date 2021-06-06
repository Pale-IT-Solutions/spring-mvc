package com.kmh.spring.web.controller;

import static com.kmh.spring.common.Page.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kmh.spring.entity.Company;
import com.kmh.spring.service.CompanyService;
import com.kmh.spring.web.form.CompanyForm;
import com.kmh.spring.web.validator.CompanyValidator;

@Controller
@RequestMapping(value = "/company")
public class CompanyController extends AbstractController {

	@Autowired
	private CompanyService service;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new CompanyValidator());
	}

	@GetMapping(value = "/add")
	public String add(Model model) {

		model.addAttribute(FORM, new CompanyForm());
		return COM_ADD_PG;
	}

	@PostMapping(value = "/add")
	public String add(@ModelAttribute(FORM) @Validated CompanyForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return COM_ADD_PG;
		}

		Company company = new Company();
		BeanUtils.copyProperties(form, company);
		this.service.save(company);

		return comRedirect("/add");
	}

	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable String id, Model model) {

		Company company = this.service.findById(Integer.valueOf(id));
		CompanyForm form = new CompanyForm();
		BeanUtils.copyProperties(company, form);

		model.addAttribute(FORM, form);

		return COM_EDIT_PG;
	}

	@PostMapping(value = "/edit")
	public String edit(@ModelAttribute(FORM) @Validated CompanyForm form, BindingResult result, Model model) {

		// TODO validation
		Company company = new Company();
		BeanUtils.copyProperties(form, company);
		this.service.update(company);

		return COM_EDIT_PG;
	}

	@GetMapping(value = "/details")
	public String details(@RequestParam("id") String id, Model model) {

		Company company = this.service.findById(Integer.valueOf(id));
		CompanyForm form = new CompanyForm();
		BeanUtils.copyProperties(company, form);

		model.addAttribute(FORM, form);

		return COM_DETAILS_PG;
	}

	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") String id, Model model) {

		this.service.deleteById(Integer.valueOf(id));

		return comRedirect("/list");
	}

	@GetMapping(value = "/list")
	public String list(Model model) {

		List<CompanyForm> forms = new ArrayList<>();

		List<Company> list = this.service.findAll();
		list.forEach(l -> {
			CompanyForm form = new CompanyForm();
			BeanUtils.copyProperties(l, form);
			forms.add(form);
		});

		model.addAttribute("list", forms);

		return COM_LIST_PG;
	}
}