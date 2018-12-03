package ru.SilirdCo.Lab6.Main.DAO;

import javafx.collections.FXCollections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.Lab6.Main.Entities.Department;
import ru.SilirdCo.Lab6.Main.Entities.Employer;
import ru.SilirdCo.Lab6.Main.Entities.IpPhone;
import ru.SilirdCo.Lab6.Util.ExceptionHandler;

import java.sql.*;
import java.util.List;

public class DAOServer implements IDAO {
    private static final Logger logger = LoggerFactory.getLogger(DAOServer.class);

    private final static String URL = "jdbc:sqlite:C:\\Work\\SQLLite\\data.db";

    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public List<Department> getDepartments() {
        final String sql = "SELECT * from Departments";

        Connection c;
        List<Department> result = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            c = connect();
            rs = c.createStatement().executeQuery(sql);
            while(rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt(0));
                department.setName(rs.getString(1));
                department.setNumber(rs.getString(2));

                result.add(department);
            }
        }
        catch(Exception ex) {
            ExceptionHandler.handle(logger, ex);
            System.out.println("Error on Building Data");
        }

        return result;
    }

    @Override
    public List<Employer> getEmployers() {
        final String sql = "SELECT * from Employers";

        Connection c;
        List<Employer> result = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            c = connect();
            rs = c.createStatement().executeQuery(sql);
            while(rs.next()) {
                Department department = new Department();
                IpPhone ipPhone = new IpPhone();

                Employer employer = new Employer();
                employer.setId(rs.getInt(0));
                employer.setName(rs.getString(1));
                department.setId(rs.getInt(2));
                employer.setDepartment(department);
                ipPhone.setId(rs.getInt(3));
                employer.setIpPhone(ipPhone);

                result.add(employer);
            }
        }
        catch(Exception ex) {
            ExceptionHandler.handle(logger, ex);
            System.out.println("Error on Building Data");
        }

        for (Employer employer : result) {
            employer.setDepartment(getDepartmentById(employer.getDepartment().getId()));
            employer.setIpPhone(getIpPhoneById(employer.getIpPhone().getId()));
        }

        return result;
    }

    @Override
    public List<IpPhone> getIpPhones() {
        final String sql = "SELECT * from IpPhones";

        Connection c;
        List<IpPhone> result = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            c = connect();
            rs = c.createStatement().executeQuery(sql);
            while(rs.next()) {
                IpPhone ipPhone = new IpPhone();
                ipPhone.setId(rs.getInt(0));
                ipPhone.setName(rs.getString(1));
                ipPhone.setNumber(rs.getString(2));

                result.add(ipPhone);
            }
        }
        catch(Exception ex) {
            ExceptionHandler.handle(logger, ex);
            System.out.println("Error on Building Data");
        }

        return result;
    }

    @Override
    public Department getDepartmentById(int id) {
        String sql = "select * from Departments where id=\""+ id + "\"";

        Department department = new Department();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                department.setId(rs.getInt(0));
                department.setName(rs.getString(1));
                department.setNumber(rs.getString(2));
            }
        }
        catch (SQLException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return department;
    }

    @Override
    public Department getDepartmentByName(String name) {
        String sql = "select * from Departments where name=\""+ name + "\"";

        Department department = new Department();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                department.setId(rs.getInt(0));
                department.setName(rs.getString(1));
                department.setNumber(rs.getString(2));
            }
        }
        catch (SQLException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return department;
    }

    @Override
    public Department getDepartmentByNumber(String number) {
        String sql = "select * from Departments where number=\""+ number + "\"";

        Department department = new Department();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                department.setId(rs.getInt(0));
                department.setName(rs.getString(1));
                department.setNumber(rs.getString(2));
            }
        }
        catch (SQLException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return department;
    }

    @Override
    public Employer getEmployerById(int id) {
        String sql = "select * from Employers where id=\""+ id + "\"";

        Employer employer = new Employer();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                Department department = new Department();
                IpPhone ipPhone = new IpPhone();

                employer.setId(rs.getInt(0));
                employer.setName(rs.getString(1));
                department.setId(rs.getInt(2));
                employer.setDepartment(department);
                ipPhone.setId(rs.getInt(3));
                employer.setIpPhone(ipPhone);
            }
        }
        catch (SQLException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        if (employer.getDepartment() != null) {
            employer.setDepartment(getDepartmentById(employer.getDepartment().getId()));
        }
        if (employer.getIpPhone() != null) {
            employer.setIpPhone(getIpPhoneById(employer.getIpPhone().getId()));
        }

        return employer;
    }

    @Override
    public Employer getEmployerByName(String name) {
        String sql = "select * from Employers where name=\""+ name + "\"";

        Employer employer = new Employer();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                Department department = new Department();
                IpPhone ipPhone = new IpPhone();

                employer.setId(rs.getInt(0));
                employer.setName(rs.getString(1));
                department.setId(rs.getInt(2));
                employer.setDepartment(department);
                ipPhone.setId(rs.getInt(3));
                employer.setIpPhone(ipPhone);
            }
        }
        catch (SQLException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        if (employer.getDepartment() != null) {
            employer.setDepartment(getDepartmentById(employer.getDepartment().getId()));
        }
        if (employer.getIpPhone() != null) {
            employer.setIpPhone(getIpPhoneById(employer.getIpPhone().getId()));
        }

        return employer;
    }

    @Override
    public Employer getEmployerByDepartment(String nameDepartment) {
        Department tmp = getDepartmentByName(nameDepartment);

        String sql = "select * from Employers where idDepartment=\""+ tmp.getId() + "\"";

        Employer employer = new Employer();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                Department department = new Department();
                IpPhone ipPhone = new IpPhone();

                employer.setId(rs.getInt(0));
                employer.setName(rs.getString(1));
                department.setId(rs.getInt(2));
                employer.setDepartment(department);
                ipPhone.setId(rs.getInt(3));
                employer.setIpPhone(ipPhone);
            }
        }
        catch (SQLException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        if (employer.getDepartment() != null) {
            employer.setDepartment(getDepartmentById(employer.getDepartment().getId()));
        }
        if (employer.getIpPhone() != null) {
            employer.setIpPhone(getIpPhoneById(employer.getIpPhone().getId()));
        }

        return employer;
    }

    @Override
    public Employer getEmployerByIpPhone(String nameIpPhone) {
        IpPhone tmp = getIpPhoneByName(nameIpPhone);

        String sql = "select * from Employers where idIpPhone=\""+ tmp.getId() + "\"";

        Employer employer = new Employer();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                Department department = new Department();
                IpPhone ipPhone = new IpPhone();

                employer.setId(rs.getInt(0));
                employer.setName(rs.getString(1));
                department.setId(rs.getInt(2));
                employer.setDepartment(department);
                ipPhone.setId(rs.getInt(3));
                employer.setIpPhone(ipPhone);
            }
        }
        catch (SQLException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        if (employer.getDepartment() != null) {
            employer.setDepartment(getDepartmentById(employer.getDepartment().getId()));
        }
        if (employer.getIpPhone() != null) {
            employer.setIpPhone(getIpPhoneById(employer.getIpPhone().getId()));
        }

        return employer;
    }

    @Override
    public IpPhone getIpPhoneById(int id) {
        String sql = "select * from IpPhones where id=\""+ id + "\"";

        IpPhone ipPhone = new IpPhone();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                ipPhone.setId(rs.getInt(0));
                ipPhone.setName(rs.getString(1));
                ipPhone.setNumber(rs.getString(2));
            }
        }
        catch (SQLException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return ipPhone;
    }

    @Override
    public IpPhone getIpPhoneByName(String name) {
        String sql = "select * from IpPhones where name=\""+ name + "\"";

        IpPhone ipPhone = new IpPhone();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                ipPhone.setId(rs.getInt(0));
                ipPhone.setName(rs.getString(1));
                ipPhone.setNumber(rs.getString(2));
            }
        }
        catch (SQLException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return ipPhone;
    }

    @Override
    public IpPhone getIpPhoneByNumber(String number) {
        String sql = "select * from IpPhones where number=\""+ number + "\"";

        IpPhone ipPhone = new IpPhone();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                ipPhone.setId(rs.getInt(0));
                ipPhone.setName(rs.getString(1));
                ipPhone.setNumber(rs.getString(2));
            }
        }
        catch (SQLException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return ipPhone;
    }
}
