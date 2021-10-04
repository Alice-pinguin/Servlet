package ua.goit.controller;

import ua.goit.model.*;
import ua.goit.repository.CrudRepository;
import ua.goit.repository.QueryExecutorImpl;
import ua.goit.repository.RepositoryFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/query/*")
public class QueryController extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -4009220532099402867L;

    private CrudRepository<Developer, Long> developerRepository;
    private CrudRepository<Skill, Long> skillRepository;
    private CrudRepository<DeveloperProject, Long> developerProjectRepository;
    private CrudRepository<DeveloperSkill, Long> developerSkillRepository;
    private QueryExecutorImpl queryExecutor;

    @Override
    public void init() {
        developerRepository = RepositoryFactory.of (Developer.class);
        skillRepository = RepositoryFactory.of (Skill.class);
        developerProjectRepository = RepositoryFactory.of (DeveloperProject.class);
        developerSkillRepository = RepositoryFactory.of (DeveloperSkill.class);
        queryExecutor = new QueryExecutorImpl ();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/getDeveloperByProject")) {
            req.getRequestDispatcher ("/view/query/get_developers_by_project.jsp").forward (req, resp);
        }
        if (action.startsWith ("/getLanguage")) {
            req.getRequestDispatcher ("/view/query/get_language.jsp").forward (req, resp);
        }
        if (action.startsWith ("/getLevel")) {
            req.getRequestDispatcher ("/view/query/get_level.jsp").forward (req, resp);
        }
        if (action.startsWith ("/getSalary")) {
            req.getRequestDispatcher ("/view/query/get_salary.jsp").forward (req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/getDeveloperByProject")) {
            final String id = req.getParameter ("id");
            List<Developer> byProject = queryExecutor.getListOfDevelopersFromProject (Long.valueOf (id));
            req.setAttribute ("message", byProject);
            req.getRequestDispatcher ("/view/query/get_developers_by_project.jsp").forward (req, resp);
        }

        if (action.startsWith ("/getLevel")) {
            final String level = req.getParameter ("level");
            List<Developer> byLevel = queryExecutor.getDeveloperByLevel (level);
            req.setAttribute ("allDevelopers", byLevel);
            req.getRequestDispatcher ("/view/query/get_level.jsp").forward (req, resp);
        }

        if (action.startsWith ("/getLanguage")) {
            final String skill = req.getParameter ("language");
            List<Developer> bySkill = queryExecutor.getDevelopersBySkill (skill);
            req.setAttribute ("message", bySkill);
            req.getRequestDispatcher ("/view/query/get_language.jsp").forward (req, resp);
        }
        if (action.startsWith ("/getSalary")) {
            final String id = req.getParameter ("id");
            Long salary = queryExecutor.getTotalSalaryDevelopersByProject (Long.valueOf (id));
            req.setAttribute ("allDevelopers", salary);
            req.getRequestDispatcher ("/view/query/get_salary.jsp").forward (req, resp);
        }
    }
}



