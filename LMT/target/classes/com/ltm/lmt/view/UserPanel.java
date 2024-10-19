/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.ltm.lmt.view;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Son
 */
public class UserPanel extends javax.swing.JPanel {

    public UserPanel(String fullName, String status) {
        initComponents();
        setPreferredSize(new Dimension(250, 60));
        setupCustomUI(fullName, status);
    }

    private void setupCustomUI(String fullName, String status) {
        setLayout(new BorderLayout(15, 5));
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        String initialName = getInitialName(fullName);
        JPanel avatarPanel = createAvatarPanel(initialName, status);
        JPanel infoPanel = createInfoPanel(fullName, status);
        JButton inviteButton = createInviteButton();

        add(avatarPanel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
        if(status.equals("online")){
            add(inviteButton, BorderLayout.EAST);
        }
    }
    private String getInitialName(String fullName) {
        String[] nameParts = fullName.split(" ");
        return nameParts[nameParts.length - 1].substring(0, 1).toUpperCase();
    }
    private JPanel createAvatarPanel(String initialName, String status) {
        JPanel avatarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int diameter = Math.min(getWidth(), getHeight()) - 2;
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;

                g2d.setColor(status.equals("offline") ? new Color(166, 166, 166) : Color.BLACK);
                g2d.fillOval(x, y, diameter, diameter);

                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Quicksand", Font.BOLD, 20));
                FontMetrics fm = g2d.getFontMetrics();
                int textX = x + (diameter - fm.stringWidth(initialName)) / 2;
                int textY = y + (diameter + fm.getAscent()) / 2 - 3;
                g2d.drawString(initialName, textX, textY);
            }
        };
        avatarPanel.setPreferredSize(new Dimension(40, 40));
        avatarPanel.setOpaque(false);
//        avatarPanel.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                JOptionPane.showMessageDialog(null, "Avatar Panel Clicked!");
//            }
//        });
        return avatarPanel;
    }
    private JPanel createInfoPanel(String fullName, String status) {
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(getBackground());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel(fullName);
        nameLabel.setFont(new Font("Quicksand", Font.BOLD, 16));
        nameLabel.setForeground(status.equals("offline") ? new Color(166, 166, 166) : Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        infoPanel.add(nameLabel, gbc);

        JPanel statusPanel = createStatusPanel(status);
        gbc.gridy = 1;
        infoPanel.add(statusPanel, gbc);

        return infoPanel;
    }
    private JPanel createStatusPanel(String status) {
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        statusPanel.setOpaque(false);

        JPanel onlineIndicator = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int diameter = Math.min(getWidth(), getHeight()) - 3;
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;

                if (status.equals("online")) {
                    g2d.setColor(new Color(193, 255, 114));
                } else if (status.equals("offline")) {
                    g2d.setColor(new Color(166, 166, 166));
                } else if (status.equals("playing")) {
                    g2d.setColor(new Color(56, 182, 255));
                }
                g2d.fillOval(x, y, diameter, diameter);

                if (status.equals("online")) {
                    g2d.setColor(new Color(0, 191, 99));
                } else if (status.equals("offline")) {
                    g2d.setColor(new Color(115, 115, 115));
                } else if (status.equals("playing")) {
                    g2d.setColor(new Color(0, 74, 173));
                }
                g2d.setStroke(new BasicStroke(1));
                g2d.drawOval(x, y, diameter, diameter);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(16, 16);
            }
        };
        statusPanel.add(onlineIndicator);

        JLabel statusLabel = new JLabel(status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase());
        statusLabel.setFont(new Font("Quicksand", Font.PLAIN, 12));
        statusLabel.setForeground(status.equals("offline") ? new Color(166, 166, 166) : Color.BLACK);
        statusPanel.add(statusLabel);

        return statusPanel;
    }
    private JButton createInviteButton() {
        JButton inviteButton = new JButton("MỜI");
        inviteButton.setForeground(new Color(0, 204, 102));
        inviteButton.setBackground(Color.WHITE);
        inviteButton.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 102)));
        inviteButton.setFocusPainted(false);
        inviteButton.setFont(new Font("Quicksand", Font.BOLD, 14));
        inviteButton.setContentAreaFilled(false);
        inviteButton.setOpaque(true);
        inviteButton.setPreferredSize(new Dimension(60, 30));
        return inviteButton;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 241, 241));
        setPreferredSize(new java.awt.Dimension(250, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
