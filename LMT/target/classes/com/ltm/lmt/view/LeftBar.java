/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.ltm.lmt.view;
import com.ltm.lmt.view.UserPanel;
import com.ltm.lmt.model.User;
import java.awt.Color;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.color.ColorSpace;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author Son
 */
public class LeftBar extends javax.swing.JPanel {

    private ContentHome contentHome;
    private User currentUser;
    public LeftBar(List<User> users, User currentUser, ContentHome contentHome) {
        initComponents();
        this.contentHome = contentHome;
        this.currentUser = currentUser;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        updateUserList(users);
    }
    public void updateUserList(List<User> users) {
        users.removeIf(user -> user.getId() == currentUser.getId());
        for (User user : users) {
            JPanel spacerPanel = new JPanel();
            spacerPanel.setPreferredSize(new Dimension(250, 10));
            spacerPanel.setBackground(new Color(232, 190, 191)); 
            spacerPanel.setMaximumSize(new Dimension(250, 10));
            add(spacerPanel);
            UserPanel userPanel = new UserPanel(user.getFullname(), user.getStatus());

            Dimension fixedSize = new Dimension(250, 60);
            userPanel.setPreferredSize(fixedSize);

            JPanel wrapperPanel = new JPanel();
            wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
            wrapperPanel.setPreferredSize(fixedSize);
            wrapperPanel.setMinimumSize(fixedSize);
            wrapperPanel.setMaximumSize(fixedSize);
            
            wrapperPanel.setBorder(null);

            wrapperPanel.add(userPanel);
            
            add(wrapperPanel);
            
            userPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openChatPanel(currentUser, user);
            }
        });

        }
    }
    
    public static JScrollPane createScrollPanelLeftBar(List<User> users, User currentUser,ContentHome contentHome){
        LeftBar leftBar = new LeftBar(users, currentUser, contentHome);
        
        JScrollPane scrollPane = new JScrollPane(leftBar);
        
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        scrollPane.setPreferredSize(new Dimension(270, 300));
        scrollPane.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(173, 23, 28)));
        customizeScrollBar(scrollPane);
        return scrollPane;
    }
    
    private static void customizeScrollBar(JScrollPane scrollPane){
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setBackground(Color.WHITE);
        verticalScrollBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(236, 236, 236); // Màu của thumb: #999999
                this.trackColor = Color.WHITE; // Màu của track: #F0F0F0
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();  // Bỏ nút trên cùng
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();  // Bỏ nút dưới cùng
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });
    }
    
    private void openChatPanel(User currentUser, User selectedUser) {
        contentHome.openChat(currentUser, selectedUser);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 564, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
