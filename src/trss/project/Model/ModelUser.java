/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trss.project.Model;

/**
 * @author خالد
 */
public class ModelUser {
    
    
    private int id;
    private String firstName, lastName, userName, email, role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String  firstName) {
        this.firstName = firstName;
    }

    public String  getLastName() {
        return lastName;
    }

    public void setLastName(String  lastName) {
        this.lastName = lastName;
    }

    public String  getUserName() {
        return userName;
    }

    public void setUserName(String  userName) {
        this.userName = userName;
    }

    public String  getEmail() {
        return email;
    }

    public void setEmail(String  email) {
        this.email = email;
    }

    public String  getRole() {
        return role;
    }

    public void setRole(String  role) {
        this.role = role;
    }

        public String toString() {
    return "ModelUser{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", role='" + role + '\'' +
            '}';
}
}
