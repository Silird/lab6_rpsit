package ru.SilirdCo.Lab6.Main.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.Lab6.Main.Entities.Department;
import ru.SilirdCo.Lab6.Main.Entities.Employer;
import ru.SilirdCo.Lab6.Main.Entities.IpPhone;
import ru.SilirdCo.Lab6.Main.Services.ServerService;
import ru.SilirdCo.Lab6.Util.ExceptionHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class DAOClient implements IDAO {
    private static final Logger logger = LoggerFactory.getLogger(DAOClient.class);

    private final static String ADDRESS = "127.0.0.1";

    public static final int ID_GET_DEPARTMENT_BY_ID = 4;
    @Override
    public Department getDepartmentById(int id) {
        Department result = new Department();
        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            Socket socket = new Socket(ipAddress, ServerService.PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(ID_GET_DEPARTMENT_BY_ID);

            out.writeInt(id);
            out.flush();

            result = receiveDepartment(in);

            in.close();
            out.close();
            socket.close();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return result;
    }

    public static final int ID_GET_DEPARTMENT_BY_NAME = 5;
    @Override
    public Department getDepartmentByName(String name) {
        Department result = new Department();
        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            Socket socket = new Socket(ipAddress, ServerService.PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(ID_GET_DEPARTMENT_BY_NAME);

            out.writeUTF(name);
            out.flush();

            result = receiveDepartment(in);

            in.close();
            out.close();
            socket.close();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return result;
    }

    public static final int ID_GET_DEPARTMENT_BY_NUMBER = 6;
    @Override
    public Department getDepartmentByNumber(String number) {
        Department result = new Department();
        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            Socket socket = new Socket(ipAddress, ServerService.PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(ID_GET_DEPARTMENT_BY_NUMBER);

            out.writeUTF(number);
            out.flush();

            result = receiveDepartment(in);

            in.close();
            out.close();
            socket.close();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return result;
    }

    public static final int ID_GET_EMPLOYER_BY_ID = 7;
    @Override
    public Employer getEmployerById(int id) {
        Employer result = new Employer();
        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            Socket socket = new Socket(ipAddress, ServerService.PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(ID_GET_EMPLOYER_BY_ID);

            out.writeInt(id);
            out.flush();

            result = receiveEmployer(in);

            in.close();
            out.close();
            socket.close();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return result;
    }

    public static final int ID_GET_EMPLOYER_BY_NAME = 8;
    @Override
    public Employer getEmployerByName(String name) {
        Employer result = new Employer();
        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            Socket socket = new Socket(ipAddress, ServerService.PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(ID_GET_EMPLOYER_BY_NAME);

            out.writeUTF(name);
            out.flush();

            result = receiveEmployer(in);

            in.close();
            out.close();
            socket.close();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return result;
    }

    public static final int ID_GET_EMPLOYER_BY_DEPARTMENT = 9;
    @Override
    public Employer getEmployerByDepartment(String nameDepartment) {
        Employer result = new Employer();
        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            Socket socket = new Socket(ipAddress, ServerService.PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(ID_GET_EMPLOYER_BY_DEPARTMENT);

            out.writeUTF(nameDepartment);
            out.flush();

            result = receiveEmployer(in);

            in.close();
            out.close();
            socket.close();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return result;
    }

    public static final int ID_GET_EMPLOYER_BY_IP_PHONE = 10;
    @Override
    public Employer getEmployerByIpPhone(String nameIpPhone) {
        Employer result = new Employer();
        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            Socket socket = new Socket(ipAddress, ServerService.PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(ID_GET_EMPLOYER_BY_IP_PHONE);

            out.writeUTF(nameIpPhone);
            out.flush();

            result = receiveEmployer(in);

            in.close();
            out.close();
            socket.close();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return result;
    }

    public static final int ID_GET_IP_PHONE_BY_ID = 11;
    @Override
    public IpPhone getIpPhoneById(int id) {
        IpPhone result = new IpPhone();
        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            Socket socket = new Socket(ipAddress, ServerService.PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(ID_GET_IP_PHONE_BY_ID);

            out.writeInt(id);
            out.flush();

            result = receiveIpPhone(in);

            in.close();
            out.close();
            socket.close();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return result;
    }

    public static final int ID_GET_IP_PHONE_BY_NAME = 12;
    @Override
    public IpPhone getIpPhoneByName(String name) {
        IpPhone result = new IpPhone();
        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            Socket socket = new Socket(ipAddress, ServerService.PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(ID_GET_IP_PHONE_BY_NAME);

            out.writeUTF(name);
            out.flush();

            result = receiveIpPhone(in);

            in.close();
            out.close();
            socket.close();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return result;
    }

    public static final int ID_GET_IP_PHONE_BY_NUMBER = 13;
    @Override
    public IpPhone getIpPhoneByNumber(String number) {
        IpPhone result = new IpPhone();
        try {
            InetAddress ipAddress = InetAddress.getByName(ADDRESS);
            Socket socket = new Socket(ipAddress, ServerService.PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(ID_GET_IP_PHONE_BY_NUMBER);

            out.writeUTF(number);
            out.flush();

            result = receiveIpPhone(in);

            in.close();
            out.close();
            socket.close();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
        }

        return result;
    }

    private static Department receiveDepartment(DataInputStream in) throws IOException {
        Department result = new Department();

        result.setId(in.readInt());
        result.setName(in.readUTF());
        result.setNumber(in.readUTF());

        return result;
    }

    private static Employer receiveEmployer(DataInputStream in) throws IOException {
        Employer employer = new Employer();

        employer.setId(in.readInt());
        employer.setName(in.readUTF());

        Department department = new Department();
        department.setId(in.readInt());
        department.setName(in.readUTF());
        department.setNumber(in.readUTF());
        employer.setDepartment(department);

        IpPhone ipPhone = new IpPhone();
        ipPhone.setId(in.readInt());
        ipPhone.setName(in.readUTF());
        ipPhone.setNumber(in.readUTF());
        employer.setIpPhone(ipPhone);

        return employer;
    }

    private static IpPhone receiveIpPhone(DataInputStream in) throws IOException {
        IpPhone result = new IpPhone();

        result.setId(in.readInt());
        result.setName(in.readUTF());
        result.setNumber(in.readUTF());

        return result;
    }

    @Override
    public List<Department> getDepartments() {
        return null;
    }

    @Override
    public List<Employer> getEmployers() {
        return null;
    }

    @Override
    public List<IpPhone> getIpPhones() {
        return null;
    }
}
