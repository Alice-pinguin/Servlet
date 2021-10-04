package ua.goit.controller;

import ua.goit.model.Project;
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

@WebServlet(urlPatterns = "/project/*")
public class ProjectServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -1916083755011099167L;

    private  CrudRepository<Project, Long> projectRepository;

    @Override
    public void init() throws ServletException {
        super.init ();
        projectRepository = RepositoryFactory.of (Project.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/findProject")) {
            req.getRequestDispatcher ("/view/project/find_project.jsp").forward (req, resp);
        }
        if (action.startsWith ("/createProject")) {
            req.getRequestDispatcher ("/view/project/create_project.jsp").forward (req, resp);
        }
        if (action.startsWith ("/updateProject")) {
            req.getRequestDispatcher ("/view/project/update_project.jsp").forward (req, resp);
        }
        if (action.startsWith ("/deleteProject")) {
            req.getRequestDispatcher ("/view/project/delete_project.jsp").forward (req, resp);
        }
        if (action.startsWith ("/allProject")) {
            List<Project> projectList = projectRepository.findAll ();
            req.setAttribute ("projects", projectList);
            req.getRequestDispatcher ("/view/project/all_projects.jsp").forward (req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo ();
        if (action.startsWith ("/createProject")) {
            Project project = mapProject (req);
            req.getRequestDispatcher ("/view/project/create_project.jsp").forward (req, resp);
            projectRepository.create (project);
            req.setAttribute ("message", "New project created: " + project);
            req.getRequestDispatcher ("/view/project/create_project.jsp").forward (req, resp);
        }
        if (action.startsWith ("/findProject")) {
            final String id = req.getParameter ("id");
            final Optional<Project> project = projectRepository.findById (Long.valueOf (id));
            if (!project.isPresent ()) {
                req.setAttribute ("message", "Project not found");
                req.getRequestDispatcher ("/view/project/find_project.jsp").forward (req, resp);
            } else {
                req.setAttribute ("message", String.format ("Project found: %s", project));
                req.getRequestDispatcher ("/view/project/find_project.jsp").forward (req, resp);
            }
        }
        if (action.startsWith ("/deleteProject")) {
            Long id = Long.valueOf ((req.getParameter ("id")));
            Optional<Project> project = projectRepository.findById (id);
            if (!project.isPresent ()) {
                req.setAttribute ("message", "Project not found");
            } else {
                projectRepository.deleteById (id);
                req.setAttribute ("message", "Project deleted");
            }
            req.getRequestDispatcher ("/view/project/delete_project.jsp").forward (req, resp);
        }
        if (action.startsWith ("/updateProject")) {
            Long id = Long.valueOf ((req.getParameter ("id")));
            Optional<Project> project = projectRepository.findById (id);
            String newCost = req.getParameter ("cost");
            project.get ().setCost (Long.valueOf (newCost));
            projectRepository.update (project.get ());
            req.setAttribute ("message", "Project  updated");
            req.getRequestDispatcher ("/view/project/update_project.jsp").forward (req, resp);
        }
    }

    private Project mapProject(HttpServletRequest req) {
        final Long projectId = Long.valueOf (req.getParameter ("id"));
        final String projectName = req.getParameter ("name");
        final String projectField = req.getParameter ("field");
        final Long projectCost = Long.valueOf (req.getParameter ("cost"));
        return new Project (projectId, projectName, projectField, projectCost);
    }

}
