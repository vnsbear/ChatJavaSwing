package com.ltm.lmt.controller;

import com.ltm.lmt.model.User;
import com.ltm.lmt.view.Login;
import com.ltm.lmt.view.Welcome;
import com.ltm.lmt.view.Home;
import com.ltm.lmt.view.Main;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;

public class LoginController {
    private Login loginPanel;
    private Main mainFrame;
    private List<User> users;
    private User currentUser;
    
    public List<User> getListUsers() {
        return users;
    }
    
    public User currentUser(){
        return currentUser;
    }

    // Constructor kết nối Model và View
    public LoginController(Login loginPanel, Main mainFrame) {
        this.loginPanel = loginPanel;
        this.mainFrame = mainFrame;

        // Gắn sự kiện cho nút login
        loginPanel.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
    }

    // Phương thức xác thực người dùng
    // Phương thức xác thực người dùng
    private void authenticateUser() {
        String username = loginPanel.getUsername();
        String password = loginPanel.getPassword();

        // Send login information and receive response
        try (Socket socket = new Socket("localhost", 1234);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(username);
            out.println(password);

            String userListData = in.readLine();
            String userInfoData = in.readLine();
            String response = in.readLine();

            users = parseUserList(userListData);

            currentUser = parseUserInfo(userInfoData);

            // Check login response
            if ("Login successful!".equals(response)) {
                loginPanel.showMessage("Đăng nhập thành công");
                openHome();
            } else {
                loginPanel.showMessage("Invalid username or password.");
            }

        } catch (IOException ex) {
            loginPanel.showMessage("Connection error: " + ex.getMessage());
        }
    }

    private List<User> parseUserList(String userListJson) {
        Gson gson = new Gson();
        return gson.fromJson(userListJson, new TypeToken<List<User>>() {}.getType());
    }

// Phương thức chuyển JSON của thông tin người dùng thành User
    private User parseUserInfo(String userInfoJson) {
        Gson gson = new Gson();
        return gson.fromJson(userInfoJson, User.class);
    }

    private String receiveResponse(BufferedReader in) throws IOException {
        return in.readLine();
    }
    
    private void openWelcome(String currentUser, List<Map<String, String>> users) {
        // Ẩn cửa sổ đăng nhập
        loginPanel.setVisible(false);

        // Tạo cửa sổ Welcome
        Welcome welcomePanel = new Welcome(currentUser);
        welcomePanel.updateUserTable(users);
        mainFrame.showPanel(welcomePanel);
    }
    
    private void openHome() {
        // Ẩn cửa sổ đăng nhập
        loginPanel.setVisible(false);
        
        Home home = new Home(users, currentUser);
        mainFrame.showPanel(home);
    }
    
    
    
}
