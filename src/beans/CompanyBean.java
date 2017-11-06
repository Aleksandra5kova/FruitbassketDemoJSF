package beans;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import csv.ReportGenerator;
import model.Company;

public class CompanyBean {

	private List<Company> companies;
	private Company company;

	private CrudService<Company> companyService;
	
	private ReportGenerator reportGenerator;

	public CompanyBean() throws Exception {
		initServices();
		this.companies = getCompanies();
	}

	private void initServices() throws Exception {
		Context ctx = new InitialContext();
		
		Object objCompanyService = ctx.lookup("java:global/FruitbasketDemo/FruitbasketDemoEJB/crudService!beans.CrudService");
		this.companyService = (CrudService<Company>) objCompanyService;
		
		Object objReportGenerator = ctx.lookup("java:global/FruitbasketDemo/FruitbasketDemoEJB/reportGenerator!csv.ReportGenerator");
		this.reportGenerator = (ReportGenerator) objReportGenerator;
	}

	public List<Company> getCompanies() {
		return companyService.findWithNamedQuery("Company.findAll");
	}

}
