package ua.goit.repository;


import lombok.SneakyThrows;
import ua.goit.dto.ProjectDevDto;
import ua.goit.model.*;
import ua.goit.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class QueryExecutorImpl implements QueryExecutor{

    private  List<Developer> developerList = new ArrayList<> ();
    private final Connection connection = DatabaseConnector.getInstance ().getConnection ();
    private Statement statement;
    {
        try {
            statement = connection.createStatement ();
        } catch (SQLException e) {

        }
    }

    @SneakyThrows
    @Override
    public List getTotalSalaryDevelopersByProject(Long id) {
        List salary = new ArrayList<> ();
        String query = "SELECT SUM(developers.salary) AS sumSalaries FROM jdbc.developers_projects " +
                "inner join jdbc.developers on jdbc.developers_projects.id_developer = developers.id " +
                "inner join jdbc.projects on jdbc.developers_projects.id_project ='" + id + "'" +
                "GROUP BY projects.id  limit 1";
        ResultSet resultSet = statement.executeQuery (query);
        while (resultSet.next ())
            salary.add (resultSet.getString ("sumSalaries"));
        return salary;
    }

    @SneakyThrows
    @Override
    public List<Developer> getListOfDevelopersFromProject(Long id)  {
        String query = "SELECT * FROM jdbc.developers d, jdbc.projects p, jdbc.developers_projects dp" +
                " where  d.id=dp.id_developer and p.id = dp.id_project and p.id ='" +id +"'";
        return getDevelopers (query, developerList);
    }

    @SneakyThrows
    @Override
    public List<Developer> getDevelopersBySkill(String skill){
        String query = "SELECT d.id, d.name,d.age, d.gender, d.salary FROM jdbc.developers d " +
                "INNER JOIN jdbc.developers_skills ds ON d.id = ds.id_developer " +
                "INNER JOIN jdbc.skills s ON ds.id_skill = s.id" +
                " WHERE s.language ='"+skill+"'";
        return getDevelopers (query, developerList);
    }

    @SneakyThrows
    @Override
    public List<Developer> getDeveloperByLevel (String level) {
        String query = "SELECT d.id, d.name,d.age, d.gender, d.salary FROM jdbc.developers d " +
                "INNER JOIN jdbc.developers_skills ds ON d.id = ds.id_developer " +
                "INNER JOIN jdbc.skills s ON ds.id_skill = s.id" +
                " WHERE s.level ='"+level+"'";
        return getDevelopers (query, developerList);
    }

    @SneakyThrows
    private List<Developer> getDevelopers(String query, List<Developer> developerList) {
        ResultSet resultSet = statement.executeQuery (query);
        while (resultSet.next ()){
            Developer developer = Developer.builder ()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .age(resultSet.getInt("age"))
                    .gender(resultSet.getString("gender"))
                    .salary(resultSet.getLong("salary"))
                    .build();
            developerList.add (developer);

        }

        return developerList;
    }

    @SneakyThrows
    @Override
    public List projectWithCountDevAndDate()  {
        List projects = new ArrayList ();
        String query = "SELECT p.id, p.create_date, p.name, count(d.name) AS quantity FROM  projects p" +
                " JOIN  developers_projects dp ON  p.id = dp.id_project " +
                " JOIN  developers d on d.id = dp.id_developer " +
                "GROUP BY  p.name order by p.create_date";
        ResultSet resultSet = statement.executeQuery (query);
        while (resultSet.next ()) {
            ProjectDevDto project = ProjectDevDto.builder ()
                    .projectName(resultSet.getString ("name"))
                    .projectDate(resultSet.getString ("create_date"))
                    .devCount(resultSet.getInt ("quantity"))
                    .build ();
            projects.add (project);
        }
        return projects;
    }

}





