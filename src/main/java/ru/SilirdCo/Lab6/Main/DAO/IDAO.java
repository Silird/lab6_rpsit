package ru.SilirdCo.Lab6.Main.DAO;

import ru.SilirdCo.Lab6.Main.Entities.Department;
import ru.SilirdCo.Lab6.Main.Entities.Employer;
import ru.SilirdCo.Lab6.Main.Entities.IpPhone;

import java.util.List;

public interface IDAO {
    List<Department> getDepartments();

    List<Employer> getEmployers();

    List<IpPhone> getIpPhones();

    Department getDepartmentById(int id);

    Department getDepartmentByName(String name);

    Department getDepartmentByNumber(String number);

    Employer getEmployerById(int id);

    Employer getEmployerByName(String name);

    Employer getEmployerByDepartment(String nameDepartment);

    Employer getEmployerByIpPhone(String nameIpPhone);

    IpPhone getIpPhoneById(int id);

    IpPhone getIpPhoneByName(String name);

    IpPhone getIpPhoneByNumber(String number);
}
