/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trss.project.Model;

import java.sql.Date;

public class ModelContract {
    
    private int id;
    private int user_id;
    private int customer_id;
    private double total_cost;
    private double deposit;
    private Date date;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUser_id() {
        return user_id;
    }
    
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    public int getCustomer_id() {
        return customer_id;
    }
    
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    
    public double getTotal_cost() {
        return total_cost;
    }
    
    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }
    
    public double getDeposit() {
        return deposit;
    }
    
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
}
