package ua.com.splan;

import java.util.List;

public interface ReferenceDAO {
    void addType(Type type);
    void deleteType(long toDelete);
    void updateType(Type type, long toUpdate);
    Type getType(long id);
    List<Type> listOfTypes();

    void addOutOrganization(OutOrganization outOrganization);
    void deleteOutOrganization(long toDelete);
    void updateOutOrganization(OutOrganization outOrganization, long toUpdate);
    OutOrganization getOutOrganization(long id);
    List<OutOrganization> listOfOutOrganizations();

    void addEmployee(Employee employee);
    void deleteEmployee(long toDelete);
    void updateEmployee(Employee employee, long toUpdate);
    Employee getEmployee(long id);
    List<Employee> listOfEmployees();

    void addExecutor(Executor executor);
    void deleteExecutor(long toDelete);
    void updateExecutor(Executor executor, long toUpdate);
    Executor getExecutor(long id);
    List<Executor> listOfExecutors();
}
