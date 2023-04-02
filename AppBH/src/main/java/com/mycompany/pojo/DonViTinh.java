/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author DELL
 */
public class DonViTinh {

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }
    public int getID ()
    {
        return Id;
    }
    /**
     * @return the Value
     */
    public String getValue() {
        return Value;
    }

    /**
     * @param Value the Value to set
     */
    public void setValue(String Value) {
        this.Value = Value;
    }
    private int Id;
    private String Value;

    public DonViTinh() {
    }

    public DonViTinh(int Id, String Value) {
        this.Id = Id;
        this.Value = Value;
    }

    @Override
    public String toString()
    {
        return this.Value;
    }
    
}
