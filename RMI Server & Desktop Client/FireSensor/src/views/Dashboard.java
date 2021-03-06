/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Common.Email_Send;
import Models.getAllData;
import Models.sensor;
import java.awt.event.ActionEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import service.ServiceInterface;



public class Dashboard extends javax.swing.JFrame {

   
    private static ServiceInterface s1;
    
    public Dashboard() {
        initComponents();
        
        try {
            GetDataToTable();
        } catch (RemoteException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sensor ID", "Floor No", "Room No", "CO2 Level", "Smoke Level", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Admin Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Dashboard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );

        setSize(new java.awt.Dimension(1010, 560));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        AdminLogin login = new AdminLogin();
        this.setVisible(false);
        login.setVisible(true);
    }
    
    
    
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    List<getAllData> allDatasExists;
private void GetDataToTable() throws RemoteException, NotBoundException {

        allDatasExists = new ArrayList<>();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Registry reg = LocateRegistry.getRegistry("localhost", 9090);
                    s1 = (ServiceInterface) reg.lookup("Sensor_server");
                    while (true) {

                        List<getAllData> allDatas = s1.allData();
                        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                        dtm.setRowCount(0);

                        int i = 0;

                        for (getAllData data : allDatas) {

                            Vector v = new Vector();
                            v.add(data.getSensor());
                            v.add(data.getFloor());
                            v.add(data.getRoom());
                             v.add(data.getCo2_level());
                            v.add(data.getSmoke_level());
                           
                           // v.add(sensor.getStatus_val());
                           // v.add((sensor.getStatus_val().equals("Active")) ? "<html><p style='color:red'>ACTIVE</p></html>" : "<html><p style='color:green'>DEACTIVE</p></html>");
                            
                            
                            int n=Integer.parseInt(data.getCo2_level());
                            int m=Integer.parseInt(data.getSmoke_level());
                           if(n >5  || m>5){
                               String user_email="akulatheepan@gmail.com";
                            v.add("<html><p style='color:red'>ACTIVE</p></html>");
                             new Email_Send().sendEmail("Fire Alarm Activated ", user_email, "Warning!!! The Floor Number: "+data.getFloor()+",  The Room Number: " + data.getRoom() + " alarm has been activated. Please call 101 for Fire Emergency Services");
                                    System.out.println("Email sent");
                                    
                           
                           }
                           else{
                             v.add("<html><p style='color:green'>NOT ACTIVE</p></html>");
                           }
                           
                            dtm.addRow(v);

                           

                            }

			Thread.sleep(15000);
                        
                    
                    } } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }).start();

    }




}
