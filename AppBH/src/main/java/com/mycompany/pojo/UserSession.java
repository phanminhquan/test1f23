/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;


/**
 *
 * @author DELL
 */
public final class UserSession {
    private static UserSession instance;

    private static String userID;
;

    public UserSession(String userID) {
        this.userID = userID;
    }

    public static UserSession getInstace(String userID) {
        if(instance == null) {
            instance = new UserSession(userID);
        }
        return instance;
    }

    public static String getUserID() {
        return userID;
    }



    public void cleanUserSession() {
        userID = "";
    }
    
}
