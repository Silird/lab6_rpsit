package ru.SilirdCo.Lab6.Main.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.Lab6.Main.DAO.DAOClient;
import ru.SilirdCo.Lab6.Main.DAO.DAOServer;
import ru.SilirdCo.Lab6.Main.DAO.IDAO;
import ru.SilirdCo.Lab6.Main.Entities.Department;
import ru.SilirdCo.Lab6.Main.Entities.Employer;
import ru.SilirdCo.Lab6.Main.Entities.IpPhone;
import ru.SilirdCo.Lab6.Util.ExceptionHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerService {
    private static final Logger logger = LoggerFactory.getLogger(ServerService.class);

    public final static int PORT = 6666;

    private final static IDAO dao = new DAOServer();
    private static DataOutputStream out;

    public static void start() {
        try {
            ServerSocket ss = new ServerSocket(PORT);

            boolean cont = true;

            while (cont) {
                Socket socket = ss.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                int intValue;
                String stringValue;
                Department department;
                Employer employer;
                IpPhone ipPhone;

                int id = in.readInt();
                switch (id) {
                    case DAOClient.ID_GET_DEPARTMENT_BY_ID:
                        intValue = in.readInt();

                        department = dao.getDepartmentById(intValue);

                        sendDepartment(department);

                        cont = true;
                        break;
                    case DAOClient.ID_GET_DEPARTMENT_BY_NAME:
                        stringValue = in.readUTF();

                        department = dao.getDepartmentByName(stringValue);

                        sendDepartment(department);

                        cont = true;
                        break;
                    case DAOClient.ID_GET_DEPARTMENT_BY_NUMBER:
                        stringValue = in.readUTF();

                        department = dao.getDepartmentByNumber(stringValue);

                        sendDepartment(department);

                        cont = true;
                        break;
                    case DAOClient.ID_GET_EMPLOYER_BY_ID:
                        intValue = in.readInt();

                        employer = dao.getEmployerById(intValue);

                        sendEmployer(employer);

                        cont = true;
                        break;
                    case DAOClient.ID_GET_EMPLOYER_BY_NAME:
                        stringValue = in.readUTF();

                        employer = dao.getEmployerByName(stringValue);

                        sendEmployer(employer);

                        cont = true;
                        break;
                    case DAOClient.ID_GET_EMPLOYER_BY_DEPARTMENT:
                        stringValue = in.readUTF();

                        employer = dao.getEmployerByDepartment(stringValue);

                        sendEmployer(employer);

                        cont = true;
                        break;
                    case DAOClient.ID_GET_EMPLOYER_BY_IP_PHONE:
                        stringValue = in.readUTF();

                        employer = dao.getEmployerByIpPhone(stringValue);

                        sendEmployer(employer);

                        cont = true;
                        break;
                    case DAOClient.ID_GET_IP_PHONE_BY_ID:
                        intValue = in.readInt();

                        ipPhone = dao.getIpPhoneById(intValue);

                        sendIpPhone(ipPhone);

                        cont = true;
                        break;
                    case DAOClient.ID_GET_IP_PHONE_BY_NAME:
                        stringValue = in.readUTF();

                        ipPhone = dao.getIpPhoneByName(stringValue);

                        sendIpPhone(ipPhone);

                        cont = true;
                        break;
                    case DAOClient.ID_GET_IP_PHONE_BY_NUMBER:
                        stringValue = in.readUTF();

                        ipPhone = dao.getIpPhoneByNumber(stringValue);

                        sendIpPhone(ipPhone);

                        cont = true;
                        break;
                    default:
                        cont = false;
                        break;
                }

                in.close();
                out.close();
                socket.close();
            }

            ss.close();
            System.exit(0);
        }
        catch (Exception ex) {
            ExceptionHandler.handle(logger, ex);
        }
    }

    private static void sendDepartment(Department department) throws IOException {
        out.writeInt(department.getId());
        out.writeUTF(department.getName());
        out.writeUTF(department.getNumber());
        out.flush();
    }

    private static void sendEmployer(Employer employer) throws IOException {
        out.writeInt(employer.getId());
        out.writeUTF(employer.getName());


        out.writeInt(employer.getDepartment().getId());
        out.writeUTF(employer.getDepartment().getName());
        out.writeUTF(employer.getDepartment().getNumber());

        out.writeInt(employer.getIpPhone().getId());
        out.writeUTF(employer.getIpPhone().getName());
        out.writeUTF(employer.getIpPhone().getNumber());

        out.flush();
    }

    private static void sendIpPhone(IpPhone ipPhone) throws IOException {
        out.writeInt(ipPhone.getId());
        out.writeUTF(ipPhone.getName());
        out.writeUTF(ipPhone.getNumber());
        out.flush();
    }
}
