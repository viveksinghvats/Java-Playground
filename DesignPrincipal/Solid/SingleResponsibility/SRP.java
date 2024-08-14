package DesignPrincipal.Solid.SingleResponsibility;
public class SRP {
    public static void main(String[] args) {
        Employee employee = new Employee(1, 27, "Vivek");
        System.out.println(employee.name);
        employee.sendSalary();
        employee.doMarketing();

        System.out.println("--------SRP---------");

        EmployeeSRP employeeSRP = new EmployeeSRP(1, 27, "Vivek");
        System.out.println(employeeSRP.name);
        SalarySendor salarySendor = new SalarySendor();
        salarySendor.sendSalary(employeeSRP);
        DoMarketing doMarketing = new DoMarketing();
        doMarketing.doMarketing(employeeSRP);
    } 
}
