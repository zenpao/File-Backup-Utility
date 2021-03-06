/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_filechamber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.nio.file.StandardCopyOption.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JFileChooser;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.Timer.*;
import javax.swing.ImageIcon;
//import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Zen
 */

public class App_betaFileChamber extends javax.swing.JFrame {

    /**
     * Creates new form app_betaFileChamber
     */
    public App_betaFileChamber() {
        initComponents();    
        
        this.setIconImage(new  ImageIcon(getClass().getResource("noxnox_52x52.png")).getImage());
        
        but_Archive.setEnabled(false);
        but_Browse.setEnabled(false);
        cmb_Time.setEnabled(false);
        but_Stop.setEnabled(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    protected javax.swing.Timer refresherTimer = null;
    protected void stopRefreshing() {
    if (refresherTimer != null) {
        refresherTimer.stop();
        refresherTimer = null;
    }
    }
    protected void startRefreshing() {
    stopRefreshing();
    int ctr = Integer.parseInt(cmb_Time.getSelectedItem().toString()) * 1000;
    refresherTimer = new Timer(ctr, e -> {
        try{
        back_up_file();
        }catch (Exception x){
            JOptionPane.showMessageDialog(null,"Something went wrong :(","Error",JOptionPane.ERROR_MESSAGE);
         }
        });
    refresherTimer.start();
    }
    public void onStartButtonClicked() {
    startRefreshing();
    }
    public void onStopButtonClicked() {
    stopRefreshing();
    }
    
    public void browse_file(){
        try{
     JFileChooser choosefile = new JFileChooser();
     choosefile.setCurrentDirectory(new java.io.File("."));
     choosefile.setDialogTitle("Browse file..");
     choosefile.setFileSelectionMode(JFileChooser.FILES_ONLY);
     choosefile.setAcceptAllFileFilterUsed(true);
     
     if (choosefile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      System.out.println("getCurrentDirectory(): " + choosefile.getCurrentDirectory());
      System.out.println("getSelectedFile() : " + choosefile.getSelectedFile());
      txt_file.setText(choosefile.getSelectedFile().toString());
    } else {
      System.out.println("No Selection ");
    }
     }catch(Exception e){
             // Handle the error
             JOptionPane.showMessageDialog(null,"Something went wrong :(","Error",JOptionPane.ERROR_MESSAGE);
            }
    
}
    
    public void browse_save() {
        String[] options = new String[] {"Yes", "No", "Cancel"};
        int response = JOptionPane.showOptionDialog(null, "Specify directory for saving?", "Title",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
        null, options, options[0]);

    switch (response){
        case 0:
     try{
     JFileChooser choosesave = new JFileChooser();
     choosesave.setCurrentDirectory(new java.io.File("."));
     choosesave.setDialogTitle("Browse path..");
     choosesave.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     choosesave.setAcceptAllFileFilterUsed(false);
     
     if (choosesave.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      System.out.println("getCurrentDirectory(): " + choosesave.getCurrentDirectory());
      System.out.println("getSelectedFile() : " + choosesave.getSelectedFile());
      txt_save.setText(choosesave.getSelectedFile().toString());
    } else {
      System.out.println("No Selection ");
    }
     }catch(Exception e){
             // Handle the error
             JOptionPane.showMessageDialog(null,"Something went wrong :(","Error",JOptionPane.ERROR_MESSAGE);
            }
     break;
        case 1:
     try{
    File desktop = FileSystemView.getFileSystemView().getHomeDirectory();
    txt_save.setText(desktop.getAbsolutePath());
        }catch(Exception e){
             // Handle the error
             JOptionPane.showMessageDialog(null,"Something went wrong :(","Error",JOptionPane.ERROR_MESSAGE);
            }
      break;
        default:
    System.exit(0);
    break;
    }
    }
    
    public void back_up_file() throws IOException{
         

    //String basename = FilenameUtils.getBaseName(txt_file.getText());
    //String extension = FilenameUtils.getExtension(txt_file.getText());
    
    File userFile = new File(txt_file.getText());
    String basename = userFile.getName(); //gets the full file format.

    if (basename.indexOf(".") > 0) { //checks if the file has an extension and removes its extension.
    basename = basename.substring(0, basename.lastIndexOf("."));
    }
    
    String extension = txt_file.getText().replaceAll("^.*\\.(.*)$", "$1"); //removes the filename and only the extension.
    
    //JOptionPane.showMessageDialog(null,""+basename,"Test",JOptionPane.PLAIN_MESSAGE);
         
         Path path1 = Paths.get(txt_file.getText());
         Path path2 = Paths.get(txt_save.getText().concat("\\"+basename+"_"+getCurrentTimeStamp()+"."+extension));
         CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING,
            StandardCopyOption.COPY_ATTRIBUTES
                                }; 
            Files.copy(path1, path2 , options);
             
        //JOptionPane.showMessageDialog(null,getCurrentTimeStamp()+ "\n" + path1 + "\n" + path2 + "\n" + basename + "." + extension,"Error",JOptionPane.ERROR_MESSAGE);
    }
     
    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lab_Txt1 = new javax.swing.JLabel();
        but_File = new javax.swing.JButton();
        txt_file = new javax.swing.JTextField();
        lab_Txt2 = new javax.swing.JLabel();
        but_Browse = new javax.swing.JButton();
        txt_save = new javax.swing.JTextField();
        but_Archive = new javax.swing.JButton();
        lab_Txt3 = new javax.swing.JLabel();
        cmb_Time = new javax.swing.JComboBox<>();
        but_Stop = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FileChamber BETA");
        setResizable(false);

        jPanel1.setLayout(null);

        lab_Txt1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lab_Txt1.setText("Select file:");
        jPanel1.add(lab_Txt1);
        lab_Txt1.setBounds(30, 30, 70, 16);

        but_File.setText("Browse..");
        but_File.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_FileActionPerformed(evt);
            }
        });
        jPanel1.add(but_File);
        but_File.setBounds(260, 30, 80, 20);

        txt_file.setEditable(false);
        txt_file.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txt_file.setText("null");
        txt_file.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txt_file.setFocusable(false);
        jPanel1.add(txt_file);
        txt_file.setBounds(50, 50, 290, 20);

        lab_Txt2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lab_Txt2.setText("Saves to:");
        jPanel1.add(lab_Txt2);
        lab_Txt2.setBounds(30, 100, 60, 16);

        but_Browse.setText("Browse..");
        but_Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_BrowseActionPerformed(evt);
            }
        });
        jPanel1.add(but_Browse);
        but_Browse.setBounds(260, 100, 80, 20);

        txt_save.setEditable(false);
        txt_save.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txt_save.setText("null");
        txt_save.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txt_save.setFocusable(false);
        jPanel1.add(txt_save);
        txt_save.setBounds(50, 120, 290, 20);

        but_Archive.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        but_Archive.setText("Waiting..");
        but_Archive.setBorder(null);
        but_Archive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_ArchiveActionPerformed(evt);
            }
        });
        jPanel1.add(but_Archive);
        but_Archive.setBounds(230, 170, 130, 40);

        lab_Txt3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lab_Txt3.setText("Every (seconds):");
        jPanel1.add(lab_Txt3);
        lab_Txt3.setBounds(30, 170, 100, 16);

        cmb_Time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "20", "30", "45", "60", " " }));
        cmb_Time.setToolTipText("");
        cmb_Time.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmb_TimeFocusGained(evt);
            }
        });
        jPanel1.add(cmb_Time);
        cmb_Time.setBounds(50, 190, 70, 20);

        but_Stop.setBackground(new java.awt.Color(255, 0, 0));
        but_Stop.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        but_Stop.setText("Pause / Stop");
        but_Stop.setBorder(null);
        but_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_StopActionPerformed(evt);
            }
        });
        jPanel1.add(but_Stop);
        but_Stop.setBounds(270, 210, 90, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Zen\\Documents\\NetBeansProjects\\app_FileChamber\\res\\nox.png")); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(-10, -40, 400, 330);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(410, 303));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void but_FileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_FileActionPerformed
        // TODO add your handling code here:
        browse_file();
        
        if (txt_file.getText().equalsIgnoreCase("null") == true){
            but_Browse.setEnabled(false);
        }else{
            but_Browse.setEnabled(true);
        }
    }//GEN-LAST:event_but_FileActionPerformed

    private void but_BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_BrowseActionPerformed
        // TODO add your handling code here:
        browse_save();
        
        if (txt_save.getText().equalsIgnoreCase("null") == true){
            cmb_Time.setEnabled(false);
        }else{
            cmb_Time.setEnabled(true);
        }
    }//GEN-LAST:event_but_BrowseActionPerformed

    private void but_ArchiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_ArchiveActionPerformed
        // TODO add your handling code here:  
        but_File.setEnabled(false);
        but_Browse.setEnabled(false);
        cmb_Time.setEnabled(false);
        but_Archive.setText("Ongoing..");
        but_Archive.setEnabled(false);
        but_Stop.setEnabled(true);
        
        onStartButtonClicked();
//        try{
//             while (true) {
//            back_up_file();
//            Thread.sleep(ctr * 1000L);
//        }
//        }catch (IOException | InterruptedException e){
          //JOptionPane.showMessageDialog(null,new java.io.File(txt_file.getText()).getAbsoluteFile() + "\n" + new java.io.File(txt_save.getText()).getAbsoluteFile(),"Error",JOptionPane.INFORMATION_MESSAGE);   
//        }
    }//GEN-LAST:event_but_ArchiveActionPerformed

    private void cmb_TimeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_TimeFocusGained
        // TODO add your handling code here:
        but_Archive.setText("Start");
        but_Archive.setEnabled(true);
    }//GEN-LAST:event_cmb_TimeFocusGained

    private void but_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_StopActionPerformed
        // TODO add your handling code here:
        onStopButtonClicked();
        but_Archive.setEnabled(true);
        but_File.setEnabled(true);
        but_Browse.setEnabled(true);
        cmb_Time.setEnabled(true);
        but_Archive.setText("Start");
    }//GEN-LAST:event_but_StopActionPerformed

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
            java.util.logging.Logger.getLogger(App_betaFileChamber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App_betaFileChamber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App_betaFileChamber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App_betaFileChamber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App_betaFileChamber().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but_Archive;
    private javax.swing.JButton but_Browse;
    private javax.swing.JButton but_File;
    private javax.swing.JButton but_Stop;
    private javax.swing.JComboBox<String> cmb_Time;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lab_Txt1;
    private javax.swing.JLabel lab_Txt2;
    private javax.swing.JLabel lab_Txt3;
    private javax.swing.JTextField txt_file;
    private javax.swing.JTextField txt_save;
    // End of variables declaration//GEN-END:variables
}
