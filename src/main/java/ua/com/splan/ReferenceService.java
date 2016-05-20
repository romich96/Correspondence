package ua.com.splan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReferenceService {
    @Autowired
    private ReferenceDAO referenceDAO;

    // -------------------------------------------------
    @Transactional
    public void addType(Type type) { referenceDAO.addType(type); }

    @Transactional
    public void deleteType(long toDelete) { referenceDAO.deleteType(toDelete); }

    @Transactional
    public void updateType(Type type,long toUpdate) { referenceDAO.updateType(type, toUpdate); }

    @Transactional
    public Type getType(long id) {
        return referenceDAO.getType(id);
    }

    @Transactional(readOnly = true)
    public List<Type> listOfTypes() {
        return referenceDAO.listOfTypes();
    }

    // -------------------------------------------------
    @Transactional
    public void addOutOrganization(OutOrganization outOrganization) { referenceDAO.addOutOrganization(outOrganization); }

    @Transactional
    public void deleteOutOrganization(long toDelete) { referenceDAO.deleteOutOrganization(toDelete); }

    @Transactional
    public void updateOutOrganization(OutOrganization outOrganization,long toUpdate) { referenceDAO.updateOutOrganization(outOrganization, toUpdate); }

    @Transactional
    public OutOrganization getOutOrganization(long id) {
        return referenceDAO.getOutOrganization(id);
    }

    @Transactional(readOnly = true)
    public List<OutOrganization> listOfOutOrganizations() {
        return referenceDAO.listOfOutOrganizations();
    }

    // -------------------------------------------------
    @Transactional
    public void addEmployee(Employee employee) { referenceDAO.addEmployee(employee); }

    @Transactional
    public void deleteEmployee(long toDelete) { referenceDAO.deleteEmployee(toDelete); }

    @Transactional
    public void updateEmployee(Employee employee,long toUpdate) { referenceDAO.updateEmployee(employee, toUpdate); }

    @Transactional
    public Employee getEmployee(long id) {
        return referenceDAO.getEmployee(id);
    }

    @Transactional(readOnly = true)
    public List<Employee> listOfEmployees() {
        return referenceDAO.listOfEmployees();
    }

    // -------------------------------------------------
    @Transactional
    public void addExecutor(Executor executor) { referenceDAO.addExecutor(executor); }

    @Transactional
    public void deleteExecutor(long toDelete) { referenceDAO.deleteExecutor(toDelete); }

    @Transactional
    public void updateExecutor(Executor executor,long toUpdate) { referenceDAO.updateExecutor(executor, toUpdate); }

    @Transactional
    public Executor getExecutor(long id) {
        return referenceDAO.getExecutor(id);
    }

    @Transactional(readOnly = true)
    public List<Executor> listOfExecutors() {
        return referenceDAO.listOfExecutors();
    }
}
