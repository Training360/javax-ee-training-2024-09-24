package employees;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeesDao {

    @PersistenceContext
    private EntityManager em;

    public List<Employee> findAll(ListEmployeesFilter listEmployeesFilter) {
//        return em.createQuery("select e from Employee e", Employee.class)
//                .getResultList();

        var sql = "select e from Employee e where 1 = 1";
        if (listEmployeesFilter.getNamePrefix().isPresent()) {
            sql += " and lower(e.name) like lower(:namePrefix)";
        }

        var query = em.createQuery(sql, Employee.class);

        if (listEmployeesFilter.getNamePrefix().isPresent()) {
            query.setParameter("namePrefix", listEmployeesFilter.getNamePrefix().get() + "%");
        }

        return query
                .setMaxResults(listEmployeesFilter.getMaxResult())
                .getResultList();
    }

    public Employee findById(long id) {
//        return em.createNamedQuery("findById", Employee.class)
//                .setParameter("id", id)
//                .getSingleResult();
        return Optional.ofNullable(em.find(Employee.class, id))
            .orElseThrow(() -> new NotFoundException("Employee with id " + id + " not found"));
    }

    @Transactional
    public Employee create(Employee employee) {
        em.persist(employee);
        return employee;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Employee update(Employee employee) {
        var original = findById(employee.getId());
        original.setName(employee.getName());
        return original;
    }

    public void delete(long id) {
        em.remove(em.find(Employee.class, id));
    }

    public Optional<Employee> findEmployeeByName(String name) {
        try {
            return Optional.of(
                    em.createNamedQuery("findEmployeeByName", Employee.class)
                            .setParameter("name", name)
                            .getSingleResult());
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
