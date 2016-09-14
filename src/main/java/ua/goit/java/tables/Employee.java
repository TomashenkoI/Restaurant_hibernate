package ua.goit.java.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 7 on 12.08.2016.
 */
@Entity
@Table(name = "employees")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public class Employee extends Tables {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int ID;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "position_id")
    private int position;

    @Column(name = "salary")
    private double salary;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +  "\t" +
                " lastName=" + "\t" + lastName +
                " firstName=" +  "\t" + firstName +
                " dateOfBirth=" +  "\t" + dateOfBirth +
                " phoneNumber=" +  "\t" + phoneNumber +
                " position=" +  "\t" + position + "\t" +
                " salary= " + salary +
                '}';
    }
}
