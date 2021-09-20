import ua.goit.model.Company;
import ua.goit.repository.CrudRepository;
import ua.goit.repository.RepositoryFactory;
import ua.goit.util.ScriptExecutor;

import java.util.List;

public class Application {

    public static void main(String[] args) {
   ScriptExecutor.start ();
         CrudRepository<Company, Long> companyRepository  = RepositoryFactory.of (Company.class);
         companyRepository.deleteById (1L);
        List<Company> companies = companyRepository.findAll ();

        System.out.println (companies);
    }
}
