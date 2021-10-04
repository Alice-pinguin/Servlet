package ua.goit.controller;

import ua.goit.model.Developer;
import ua.goit.repository.CrudRepository;
import ua.goit.repository.RepositoryFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;
import java.util.Optional;


@WebServlet (urlPatterns = "/developer/*")
public class DeveloperServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 8144470900821213687L;
    private CrudRepository<Developer, Long> developerRepository;

    @Override
    public void init() {
        developerRepository = RepositoryFactory.of (Developer.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith("/findDeveloper")) {
            req.getRequestDispatcher("/view/developer/find_developer.jsp").forward(req, resp);
        }
        if (action.startsWith("/createDeveloper")) {
            req.getRequestDispatcher("/view/developer/create_developer.jsp").forward(req, resp);
        }
        if (action.startsWith("/updateDeveloper")) {
            req.getRequestDispatcher("/view/developer/update_developer.jsp").forward(req, resp);
        }
        if (action.startsWith("/deleteDeveloper")) {
            req.getRequestDispatcher("/view/developer/delete_developer.jsp").forward(req, resp);
        }
        if (action.startsWith("/allDeveloper")) {
            List<Developer> developerList = developerRepository.findAll ();
            req.setAttribute("developers", developerList);
            req.getRequestDispatcher("/view/developer/all_developers.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/createDeveloper")) {
            Developer developer = mapDeveloper (req);
            req.getRequestDispatcher ("/view/developer/create_developer.jsp").forward (req, resp);
            developerRepository.create (developer);
            req.setAttribute ("message", "New developer created: "+ developer);
        }
        if (action.startsWith ("/findDeveloper")) {
            final String id = req.getParameter ("id").trim ();
            final Optional<Developer> developerOptional = developerRepository.findById (Long.valueOf (id));
            if (!developerOptional.isPresent ()) {
                req.setAttribute ("message", "Developer not found");
                req.getRequestDispatcher ("/view/developer/find_developer.jsp").forward (req, resp);
            } else {
                req.setAttribute ("message", String.format ("Developer found: %s", developerOptional));
                req.getRequestDispatcher ("/view/developer/find_developer.jsp").forward (req, resp);
            }
        }
        if (action.startsWith ("/deleteDeveloper")) {
            Long id = Long.valueOf ((req.getParameter ("id")));
            Optional<Developer> developer = developerRepository.findById (id);
            if (!developer.isPresent ()) {
                req.setAttribute ("message", "Developer not found");
            } else {
                developerRepository.deleteById (id);
                req.setAttribute ("message", "Developer deleted");
            }
            req.getRequestDispatcher("/view/developer/delete_developer.jsp").forward(req, resp);
        }
        if (action.startsWith ("/updateDeveloper")) {
            Long id = Long.valueOf ((req.getParameter ("id")));
            Optional<Developer> developer = developerRepository.findById (id);
            String newSalary = req.getParameter ("salary");
            developer.get ().setSalary (Long.valueOf (newSalary));
            developerRepository.update (developer.get ());
            req.setAttribute ("message", "Developer  updated");

            req.getRequestDispatcher ("/view/developer/update_developer.jsp").forward (req, resp);
        }
    }



    private Developer mapDeveloper(HttpServletRequest req) {
        final Long developerId = Long.valueOf (req.getParameter ("id"));
        final String developerName = req.getParameter ("name");
        final Integer developerAge = Integer.valueOf (req.getParameter ("age"));
        final String developerGender = req.getParameter ("gender");
        final Long developerSalary = Long.valueOf (req.getParameter ("salary"));
        return new Developer (developerId, developerName, developerAge, developerGender, developerSalary);
    }

}
