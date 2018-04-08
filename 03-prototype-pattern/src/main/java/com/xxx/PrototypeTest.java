package com.xxx;

import com.xxx.prototype.clone.simple.Address;
import com.xxx.prototype.clone.simple.Student;

public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {

        Address address = new Address("湖南省", "湘潭市", "自治区");
        Student student = new Student();
        student.setName("毛泽东");
        student.setAge(28);
        student.setGender(true);
        student.setAddress(address);
        Student s = (Student) student.clone();
        // Student s = (Student) student.deepClone();
        Address a = s.getAddress();
        System.out.println(student == s);
        System.out.println(address == a);
    }
}
