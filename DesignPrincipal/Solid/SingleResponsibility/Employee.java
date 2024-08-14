package DesignPrincipal.Solid.SingleResponsibility;

class Employee {
  int id;
  int age;
  String name;

  public Employee(int id, int age, String name) {
    this.id = id;
    this.age = age;
    this.name = name;
  }

  protected void sendSalary() {
    System.out.println("Sending Salary");
  }

  protected void doMarketing() {
    System.out.println("Doing marketing");
  }
}