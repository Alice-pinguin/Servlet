package ua.goit.controller;

import ua.goit.model.Company;
import ua.goit.repository.CrudRepository;
import ua.goit.repository.RepositoryFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet (urlPatterns = "/company/*")
public class CompanyServlet extends HttpServlet {

    private static final long serialVersionUID = -6281148666235643052L;

    private CrudRepository<Company, Long> companyRepository;

    @Override
    public void init() {
        companyRepository = RepositoryFactory.of (Company.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/findCompany")) {
            req.getRequestDispatcher ("/view/company/find_company.jsp").forward (req, resp);

        }
        if (action.startsWith ("/createCompany")) {
            req.getRequestDispatcher ("/view/company/create_company.jsp").forward (req, resp);
        }
        if (action.startsWith ("/deleteCompany")) {
            req.getRequestDispatcher ("/view/company/delete_company.jsp").forward (req, resp);
        }
        if (action.startsWith ("/allCompanies")) {
            List<Company> companies = companyRepository.findAll ();
            System.out.println (companies.get (0));
            req.setAttribute ("companies", companies);
            System.out.println (companies.get (1));
            req.getRequestDispatcher ("/view/company/all_companies.jsp").forward (req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/createCompany")) {
            Company company = mapCompany (req);
            req.getRequestDispatcher ("/view/company/create_company.jsp").forward (req, resp);
            companyRepository.save (company);
            req.setAttribute ("message", "New Company created: " + company);
        }
        if (action.startsWith ("/findCompany")) {
            final String id = req.getParameter ("id").trim ();
            final Optional<Company> company = companyRepository.findById (Long.valueOf (id));
            if (!company.isPresent ()) {
                req.setAttribute ("message", "Company not found");
                req.getRequestDispatcher ("/view/company/find_company.jsp").forward (req, resp);
            } else {
                req.setAttribute ("message", String.format ("Company found: %s", company));
                req.getRequestDispatcher ("/view/company/find_company.jsp").forward (req, resp);
            }
        }
        if (action.startsWith ("/deleteCompany")) {
            Long id = Long.valueOf ((req.getParameter ("id")));
            Optional<Company> company = companyRepository.findById (id);
            if (!company.isPresent ()) {
                req.setAttribute ("message", "Company not found");
            } else {
                companyRepository.deleteById (id);
                req.setAttribute ("message", String.format ("Company with ID=%s deleted", id));
            }
        }
    }

    private Company mapCompany(HttpServletRequest req) {
        final Long companyId = Long.valueOf (req.getParameter ("id"));
        final String companyName = req.getParameter ("name");
        final String companyCity = req.getParameter ("city");
        return new Company (companyId, companyName, companyCity);
    }

    }


