package ua.goit.controller;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import ua.goit.model.Customer;
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

@Log
@WebServlet (urlPatterns = "/customer/*")
public class CustomerServlet extends HttpServlet {

    private static final long serialVersionUID = -3850586523812238656L;
    private CrudRepository<Customer, Long> customerRepository;

    @Override
    public void init() throws ServletException {
        super.init ();
        customerRepository = RepositoryFactory.of (Customer.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith("/findCustomer")) {
            req.getRequestDispatcher("/view/customer/find_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/createCustomer")) {
            req.getRequestDispatcher("/view/customer/create_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/updateCustomer")) {
            req.getRequestDispatcher("/view/customer/update_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/deleteCustomer")) {
            req.getRequestDispatcher("/view/customer/delete_customer.jsp").forward(req, resp);
        }
        if (action.startsWith("/allCustomers")) {
            List<Customer> customers = customerRepository.findAll ();
            req.setAttribute("customers", customers);
            req.getRequestDispatcher("/view/customer/all_customers.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/createCustomer")) {
            Customer customer = mapCustomer (req);
            req.getRequestDispatcher ("/view/customer/create_customer.jsp").forward (req, resp);
            customerRepository.create (customer);
            req.setAttribute ("message", "New customer created: " + customer);
        }
        if (action.startsWith ("/findCustomer")) {
            final String id = req.getParameter ("id");
            final Optional<Customer> customer = customerRepository.findById (Long.valueOf (id));
            if (!customer.isPresent ()) {
                req.setAttribute ("message", "Customer not found");
            } else {
                req.setAttribute ("message", String.format ("Customer found: %s", customer));
            }
            req.getRequestDispatcher ("/view/customer/find_customer.jsp").forward (req, resp);
        }
        if (action.startsWith ("/deleteCustomer")) {
            Long id = Long.valueOf ((req.getParameter ("id")));
            Optional<Customer> customer = customerRepository.findById (id);
            if (!customer.isPresent ()) {
                req.setAttribute ("message", "Customer not found");
            } else {
                customerRepository.deleteById (id);
                req.setAttribute ("message", String.format ("Customer with ID=%s deleted", id));
            }
            req.getRequestDispatcher ("/view/customer/delete_customer.jsp");
        }
        if (action.startsWith ("/updateCustomer")) {
            Long id = Long.valueOf ((req.getParameter ("id")));
            Optional<Customer> customer = customerRepository.findById (id);
                String newIndustry = req.getParameter ("industry");
                String newCity = req.getParameter ("city");
                customer.get ().setIndustry (newIndustry);
                customer.get ().setCity (newCity);
                customerRepository.update (customer.get ());
                req.setAttribute ("message", "Customer updated");
            }
            req.getRequestDispatcher ("/view/customer/update_customer.jsp").forward (req, resp);
        }

    private Customer mapCustomer(HttpServletRequest req) {
        final Long customerId = Long.valueOf (req.getParameter ("id"));
        final String customerName = req.getParameter ("name");
        final String customerCity = req.getParameter ("city");
        final String customerIndustry = req.getParameter ("city");
        return new Customer (customerId, customerName, customerCity, customerIndustry);
    }

    }


