/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab10;

/**
 *
 * @author chiew256
 */
public class Q1 {
    public static void main(String[] args) {
        Employee worker1 = new PermanentEmployee("Worker A", 'B');
        Employee worker2 = new ContractStaff("Worker B", 1000);
        Employee worker3 = new TemporaryStaff("Worker C", 10);
        worker1.display();
        worker2.display();
        worker3.display();
    }
}

abstract class Employee{
    protected String name;
    protected double salary;

    public Employee(String name){
        this.name = name;
    }

    abstract void display();
}

class PermanentEmployee extends Employee{

    public PermanentEmployee(String name, char category) {
        super(name);
        if(category == 'A'){
            this.salary = 4000;
        }
        else if(category == 'B'){
            this.salary = 3000;
        }
        else if(category == 'C'){
            this.salary = 2000;
        }
        else{
        }
    }

    @Override
    void display() {
        System.out.printf("The worker %s working as a permanent employee " + "has a salary of RM%.2f for this month \n", this.name, this.salary);
    }
}

class ContractStaff extends Employee{

    public ContractStaff(String name, double sales) {
        super(name);
        this.salary = 500 + sales * 0.5;
    }

    @Override
    void display() {
        System.out.printf("The worker %s working as a contract staff " + "has a salary of RM%.2f for this month \n", this.name, this.salary);
    }
}

class TemporaryStaff extends Employee{

    public TemporaryStaff(String name, int hour) {
        super(name);
        this.salary = 15 * hour;
    }

    @Override
    void display() {
        System.out.printf("The worker %s working as a temporary staff " + "has a salary of RM%.2f for this month \n", this.name, this.salary);
    }
}
