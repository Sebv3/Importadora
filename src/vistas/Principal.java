/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import bd.conexionLogin;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author salme
 */
public class Principal extends javax.swing.JFrame {
    
    private static final String SELECT_USUARIO_SQL = "SELECT TIPO_ID FROM USUARIO WHERE CORREO = ? AND CONTRASENIA = ?";
    
    conexionLogin con = new conexionLogin();
    Connection cn = con.ConectarBD();
    
    public Principal() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCorreoLogin = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        jBotonRecuperar = new javax.swing.JButton();
        jBotonRegistro1 = new javax.swing.JButton();
        txtPasswordLogin = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel4.setText("!Bienvenido!");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 260, 70));

        jLabel3.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Larrain");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 350, 70));

        jLabel2.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Importadora ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 350, 70));

        txtCorreoLogin.setForeground(java.awt.SystemColor.controlShadow);
        txtCorreoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoLoginActionPerformed(evt);
            }
        });
        jPanel1.add(txtCorreoLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 330, 50));

        btnLogin.setBackground(new java.awt.Color(155, 179, 232));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogin.setText("Iniciar sesión");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, 150, 50));

        jBotonRecuperar.setBackground(new java.awt.Color(250, 249, 248));
        jBotonRecuperar.setText("¿Olvidaste tu contraseña?");
        jBotonRecuperar.setBorder(null);
        jBotonRecuperar.setBorderPainted(false);
        jBotonRecuperar.setContentAreaFilled(false);
        jBotonRecuperar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBotonRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonRecuperarActionPerformed(evt);
            }
        });
        jPanel1.add(jBotonRecuperar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 450, -1, 30));

        jBotonRegistro1.setBackground(new java.awt.Color(250, 249, 248));
        jBotonRegistro1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBotonRegistro1.setText("Crear nueva cuenta");
        jBotonRegistro1.setBorder(null);
        jBotonRegistro1.setBorderPainted(false);
        jBotonRegistro1.setContentAreaFilled(false);
        jBotonRegistro1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBotonRegistro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonRegistro1ActionPerformed(evt);
            }
        });
        jPanel1.add(jBotonRegistro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 110, 30));

        txtPasswordLogin.setForeground(javax.swing.UIManager.getDefaults().getColor("controlShadow"));
        jPanel1.add(txtPasswordLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 330, 50));

        jLabel5.setText("Contraseña:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, -1, -1));

        jLabel6.setText("Correo Electronico:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, -1, -1));

        jLabel1.setBackground(new java.awt.Color(155, 179, 232));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 898, 666));

        jMenu1.setText("File");

        jMenuItem1.setText("Registrarse");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Salir");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorreoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoLoginActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String usuario = txtCorreoLogin.getText();
        String pass = new String(txtPasswordLogin.getPassword());

        if (!usuario.isEmpty() && !pass.isEmpty()) {
            String resultado = iniciarSesion(usuario, pass);
            switch (resultado) {
                case "CLIENTE":
                    dispose();
                    MenuCliente menucli = new MenuCliente();
                    menucli.setVisible(true);
                    menucli.setLocationRelativeTo(null);
                    JOptionPane.showMessageDialog(null, "Bienvenido CLIENTE");                    
                    break;
                case "SUPERVISOR":
                    dispose();
                    MenuSupervisor menusup = new MenuSupervisor();
                    menusup.setVisible(true);
                    menusup.setLocationRelativeTo(null);
                    JOptionPane.showMessageDialog(null, "Bienvenido SUPERVISOR");                  
                    break;
                case "AGENTE":
                    dispose();
                    MenuAgente menuage = new MenuAgente();
                    menuage.setVisible(true);
                    menuage.setLocationRelativeTo(null);
                    JOptionPane.showMessageDialog(null, "Bienvenido AGENTE");
                    break;
                case "USUARIO_NO_ENCONTRADO":
                    JOptionPane.showMessageDialog(null, "Correo o contraseña incorrecta");
                    break;
                case "ERROR":
                default:
                    JOptionPane.showMessageDialog(null, "Error al iniciar sesión");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor completar los campos");
        }
    }//GEN-LAST:event_btnLoginActionPerformed
    
    public String iniciarSesion(String correoUsuario, String password) {
        try (PreparedStatement preparedStatement = (PreparedStatement) cn.prepareStatement(SELECT_USUARIO_SQL)) {
            preparedStatement.setString(1, correoUsuario);
            preparedStatement.setString(2, password);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    int tipoId = rs.getInt("TIPO_ID");
                    return getUserRole(tipoId);
                } else {
                    return "USUARIO_NO_ENCONTRADO";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
    
    private String getUserRole(int tipoId) {
        switch (tipoId) {
            case 1:
                return "CLIENTE";
            case 2:
                return "SUPERVISOR";
            case 3:
                return "AGENTE";
            default:
                return "DESCONOCIDO";
        }
    }

    
    
    private void jBotonRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonRecuperarActionPerformed
        
    }//GEN-LAST:event_jBotonRecuperarActionPerformed

    private void jBotonRegistro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonRegistro1ActionPerformed
        dispose();
        Registro registro = new Registro();
        registro.setVisible(true);
        registro.setLocationRelativeTo(null);
    }//GEN-LAST:event_jBotonRegistro1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Principal princ = new Principal();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton jBotonRecuperar;
    private javax.swing.JButton jBotonRegistro1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCorreoLogin;
    private javax.swing.JPasswordField txtPasswordLogin;
    // End of variables declaration//GEN-END:variables
}
