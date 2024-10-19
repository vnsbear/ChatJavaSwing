/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ltm.lmt;

import com.ltm.lmt.model.User;
import com.ltm.lmt.controller.LoginController;
import com.ltm.lmt.view.Login;
import com.ltm.lmt.view.Main;
import com.ltm.lmt.view.Home;
import java.io.*;
import java.net.*;
import java.util.*;

public class LMT {

    public static void main(String[] args) {
        
        // Tạo model, view và controller
        Login loginPanel = new Login();
        Main mainFrame = new Main();
        LoginController controller = new LoginController(loginPanel, mainFrame);
       
        mainFrame.showLogin(loginPanel);
        mainFrame.setVisible(true);
        
    }
}
