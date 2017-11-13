package beans;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import model.Company;
import report.CompanyReportGenerator;

public class CompanyBean {

	private List<Company> companies;

	private CrudServiceLocal<Company> companyService;
	
	private CompanyReportGenerator companyReportGenerator;

	public CompanyBean() throws Exception {
		initServices();
		this.companies = getCompanies();
	}

	private void initServices() throws Exception {
		Context ctx = new InitialContext();
		
		Object objCompanyService = ctx.lookup("java:global/FruitbasketDemo/FruitbasketDemoEJB/crudService!beans.CrudServiceLocal");
		this.companyService = (CrudServiceLocal<Company>) objCompanyService;
		
		Object objReportGenerator = ctx.lookup("java:global/FruitbasketDemo/FruitbasketDemoEJB/reportGenerator!report.CompanyReportGenerator");
		this.companyReportGenerator = (CompanyReportGenerator) objReportGenerator;
	}

	public List<Company> getCompanies() {
		return companyService.findWithNamedQuery("Company.findAll");
	}

}
