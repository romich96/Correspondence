package ua.com.splan;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ReferenceDAOImpl implements ReferenceDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addType(Type type) { entityManager.persist(type); }

    @Override
    public void deleteType(long toDelete) {
        Type type =  entityManager.find(Type.class, toDelete);
        if (type != null) {
            entityManager.remove(type);
        }
    }
    @Override
    public void updateType(Type type, long toUpdate) {
        Type typeToUpdate =  entityManager.find(Type.class, toUpdate);
        typeToUpdate.setName(type.getName());
    }

    @Override
    public Type getType (long id) {
        Type type =  entityManager.find(Type.class, id);
        return type;
    }

    @Override
    public List<Type> listOfTypes() {
        Query query = entityManager.createQuery("SELECT c FROM Type c", Type.class);
        return (List<Type>) query.getResultList();
    }
    //---------------------------------------------------

    @Override
    public void addOutOrganization(OutOrganization outOrganization) { entityManager.persist(outOrganization); }

    @Override
    public void deleteOutOrganization(long toDelete) {
        OutOrganization outOrganization =  entityManager.find(OutOrganization.class, toDelete);
        if (outOrganization != null) {
            entityManager.remove(outOrganization);
        }
    }
    @Override
    public void updateOutOrganization(OutOrganization outOrganization, long toUpdate) {
        OutOrganization outOrganizationToUpdate =  entityManager.find(OutOrganization.class, toUpdate);
        outOrganizationToUpdate.setName(outOrganization.getName());
    }

    @Override
    public OutOrganization getOutOrganization (long id) {
        OutOrganization outOrganization =  entityManager.find(OutOrganization.class, id);
        return outOrganization;
    }

    @Override
    public List<OutOrganization> listOfOutOrganizations() {
        Query query = entityManager.createQuery("SELECT c FROM OutOrganization c", OutOrganization.class);
        return (List<OutOrganization>) query.getResultList();
    }
    //---------------------------------------------------

    @Override
    public void addEmployee(Employee employee) { entityManager.persist(employee); }

    @Override
    public void deleteEmployee(long toDelete) {
        Employee employee =  entityManager.find(Employee.class, toDelete);
        if (employee != null) {
            entityManager.remove(employee);
        }
    }
    @Override
    public void updateEmployee(Employee employee, long toUpdate) {
        Employee employeeToUpdate =  entityManager.find(Employee.class, toUpdate);
        employeeToUpdate.setName(employee.getName());
    }

    @Override
    public Employee getEmployee (long id) {
        Employee employee =  entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public List<Employee> listOfEmployees() {
        Query query = entityManager.createQuery("SELECT c FROM Employee c", Employee.class);
        return (List<Employee>) query.getResultList();
    }
    //---------------------------------------------------

    @Override
    public void addExecutor(Executor executor) { entityManager.persist(executor); }

    @Override
    public void deleteExecutor(long toDelete) {
        Executor executor =  entityManager.find(Executor.class, toDelete);
        if (executor != null) {
            entityManager.remove(executor);
        }
    }
    @Override
    public void updateExecutor(Executor executor, long toUpdate) {
        Executor executorToUpdate =  entityManager.find(Executor.class, toUpdate);
        executorToUpdate.setName(executor.getName());
    }

    @Override
    public Executor getExecutor (long id) {
        Executor executor =  entityManager.find(Executor.class, id);
        return executor;
    }

    @Override
    public List<Executor> listOfExecutors() {
        Query query = entityManager.createQuery("SELECT c FROM Executor c", Executor.class);
        return (List<Executor>) query.getResultList();
    }
    //---------------------------------------------------
}
