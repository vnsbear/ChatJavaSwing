/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.ltm.lmt.view;
import com.ltm.lmt.model.User;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
/**
 *
 * @author Son
 */
public class ChatPanel extends javax.swing.JPanel {

    private JPanel messageContainer;
    private User currentUser;
    private User selectedUser;
    public ChatPanel(User currentUser, User selectedUser) {
        initComponents();
        setLayout(new BorderLayout());
        this.currentUser = currentUser;
        this.selectedUser = selectedUser;
        add(titleBar(), BorderLayout.NORTH);
        add(messageContainerPanel(), BorderLayout.CENTER);
        add(inputPane(), BorderLayout.SOUTH);
        MessagePanel messagePanel = new MessagePanel();

        addSendMessage(messagePanel, "Hi, how are you?", "10:00 AM");
        addRecieveMessage(messagePanel, "I'm good, thanks! How about you?", "10:01 AM");
        addSendMessage(messagePanel, "I'm great, thanks for asking!", "10:02 AM");
        addRecieveMessage(messagePanel, "Glad to hear that!", "10:03 AM");
        addRecieveMessage(messagePanel, "I'm good, thanks! How about you?", "10:01 AM");
        addSendMessage(messagePanel, "I'm great, thanks for asking!", "10:02 AM");
        addRecieveMessage(messagePanel, "Glad to hear that!", "10:03 AM");
        addRecieveMessage(messagePanel, "I'm good, thanks! How about you?", "10:01 AM");
        addSendMessage(messagePanel, "I'm great, thanks for asking!", "10:02 AM");
        addRecieveMessage(messagePanel, "Glad to hear that!", "10:03 AM");
        
        scrollToBottom();
    }
    private JPanel titleBar() {
        JPanel titleBar = new JPanel();
        titleBar.setLayout(new BorderLayout());
        titleBar.setPreferredSize(new Dimension(300, 30));
        titleBar.setBackground(new Color(173, 23, 28));
        titleBar.setBorder(null);
        
        // Thêm tiêu đề
        JLabel titleLabel = new JLabel(selectedUser.getFullname());
        titleLabel.setFont(new Font("Quicksand", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleBar.setBorder(new EmptyBorder(0, 5, 0, 0));
        titleBar.add(titleLabel, BorderLayout.WEST);

        JLabel closeLabel = new JLabel("X");
        closeLabel.setFont(new Font("Quicksand", Font.BOLD, 16));
        closeLabel.setBorder(new LineBorder(new Color(173, 23, 28)));
        closeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        closeLabel.setPreferredSize(new Dimension(30, 30));
        closeLabel.setOpaque(true);
        closeLabel.setBackground(Color.WHITE);
        closeLabel.setForeground(Color.BLACK);
        closeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChatPanel.this.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                closeLabel.setBackground(new Color(255, 241, 241));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeLabel.setBackground(Color.WHITE);
            }
        });

        titleBar.add(closeLabel, BorderLayout.EAST);
        return titleBar;
    }

    private JScrollPane messageContainerPanel() {
        messageContainer = new JPanel();
        messageContainer.setOpaque(true);
        messageContainer.setBackground(new Color(255, 241, 241));
        messageContainer.setLayout(new BoxLayout(messageContainer, BoxLayout.Y_AXIS));
        
        JScrollPane scrollPane = new JScrollPane(messageContainer);
        scrollPane.setBorder(new MatteBorder(0, 1, 0, 1, new Color(173, 23, 28)));
        
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        customizeScrollBar(scrollPane);
        return scrollPane;
    }

    private JTextPane inputPane() {
        JTextPane inputPane = new JTextPane();
        inputPane.setPreferredSize(new Dimension(300, 35));
        inputPane.setBorder(new MatteBorder(1, 1, 1, 1, new Color(173, 23, 28)));
        
        StyledDocument doc = inputPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
         
        String placeholder = "Enter your message...";
        inputPane.setText(placeholder);
        inputPane.setForeground(Color.GRAY);

        inputPane.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputPane.getText().equals(placeholder)) {
                    inputPane.setText("");
                    inputPane.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputPane.getText().isEmpty()) {
                    inputPane.setText(placeholder);
                    inputPane.setForeground(Color.GRAY);
                }
            }
        });
        
        ((AbstractDocument) doc).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= 100) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) <= 100) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        return inputPane;
    }

    public void addRecieveMessage(MessagePanel messagePanel, String message, String time) {
        messageContainer.add(messagePanel.createRecieveMessagePanel(message, time));
        messageContainer.revalidate(); // Cập nhật layout
        messageContainer.repaint();
    }

    public void addSendMessage(MessagePanel messagePanel, String message, String time) {
        messageContainer.add(messagePanel.createSendMessagePanel(message, time));
        messageContainer.revalidate(); // Cập nhật layout
        messageContainer.repaint();
    }

    private static void customizeScrollBar(JScrollPane scrollPane){
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setBackground(Color.WHITE);
        verticalScrollBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(236, 236, 236);
                this.trackColor = Color.WHITE;
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton(); 
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });
    }
    
    private void scrollToBottom() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = ((JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, messageContainer)).getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
