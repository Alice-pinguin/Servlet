package ua.goit.repository;

import ua.goit.model.BaseEntity;

import java.util.List;

public interface QueryExecutor<E extends BaseEntity<ID>, ID> {

    List<E> getTotalSalaryDevelopersByProject(Long id);

    List<E> getListOfDevelopersFromProject(Long id);

    List<E> getDevelopersBySkill(String skill);

    List<E> getDeveloperByLevel(String level);

    List<E> projectWithCountDevAndDate();

}

