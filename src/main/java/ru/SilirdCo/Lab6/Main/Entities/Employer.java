package ru.SilirdCo.Lab6.Main.Entities;

public class Employer {
    private int id;
    private String name;
    private Department department;
    private IpPhone ipPhone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public IpPhone getIpPhone() {
        return ipPhone;
    }

    public void setIpPhone(IpPhone ipPhone) {
        this.ipPhone = ipPhone;
    }

    @Override
    public String toString() {
        return name;
    }
}
