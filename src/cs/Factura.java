
package cs;

import Conectar.Conectar;
import java.awt.print.*;
import java.awt.*; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Factura extends javax.swing.JFrame implements Printable{

   DefaultTableModel modelo;
    public Factura() {
        initComponents();
    }
    public Factura(String indice) {
        initComponents();
        actualizar(indice);
        
    }
    
    private void actualizar(String cod){
        modelo=new DefaultTableModel();
        modelo.addColumn("N° ORDEN");
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("PRECIO U.");
        modelo.addColumn("SUBTOTAL");
        this.jTextField1.setText(cod);
        
        
        this.jTable1.setModel(modelo);
        this.setLocationRelativeTo(null);
        try{
            
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            Conectar conn = new Conectar();
            Connection con = conn.getConnection();
            String sql = "SELECT PEDFEC FROM PEDIDO WHERE PEDCOD = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            rs.next();
            this.jTextField2.setText(rs.getObject(1).toString());
            sql = "SELECT PEDHOR FROM PEDIDO WHERE PEDCOD = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            rs.next();
            this.jTextField5.setText(rs.getObject(1).toString());
            
            
            sql = "SELECT PEDDETCOMCOD, PEDDETCAN, PEDDETSUB FROM PEDIDODETALLE WHERE PEDDETPEDCOD = ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
         
            int iter = 1;
            while(rs.next()){
                Object[] filas = new Object[5];
                for(int i = 0; i<5; i++){
                    if(i == 0)
                        filas[i] = iter;
                    else if(i == 1){
                        ResultSet rs2 = null;
                        sql = "SELECT COMNOM FROM COMIDA WHERE COMCOD = ?";
                        ps = con.prepareStatement(sql);
                        ps.setString(1, rs.getObject(i).toString());
                        rs2 = ps.executeQuery();
                        rs2.next();
                        filas[i] = rs2.getObject(1);
                    }
                    else if(i == 3){
                        ResultSet rs2 = null;
                        sql = "SELECT COMPRE FROM COMIDA WHERE COMCOD = ?";
                        ps = con.prepareStatement(sql);
                        ps.setString(1, rs.getObject(1).toString());
                        rs2 = ps.executeQuery();
                        rs2.next();
                        filas[i] = rs2.getObject(1);
                    }
                    else if(i == 2){
                        filas[i] = rs.getObject(i);
                    }
                    else{
                        filas[i] = rs.getObject(i-1);
                    }
                }
                modelo.addRow(filas);
                iter++;
            }
            int fila = jTable1.getSelectedRow();
            float saldo = 0;
            for(int i = 0; i<iter-1; i++){
                float sub = Float.parseFloat(jTable1.getValueAt(i, 4).toString());
                saldo += sub;
            }
            float igv = saldo*18/100;
            float total = saldo + igv;
            this.jTextField4.setText(""+igv);
            this.total.setText(""+total);
            
            conn.desconectar();
        }
        catch(SQLException ex){
            System.err.println(ex.toString());
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        recibo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        total1 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        titulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(74, 4, 21));

        recibo.setBackground(new java.awt.Color(74, 4, 21));
        recibo.setForeground(new java.awt.Color(255, 255, 255));
        recibo.setToolTipText("");

        jTable1.setBackground(new java.awt.Color(74, 4, 21));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "N° ORDEN", "PRODUCTO", "CANTIDAD", "PRECIO U.", "SUBTOTAL"
            }
        ));
        jTable1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jTable1);

        total1.setForeground(new java.awt.Color(255, 255, 255));
        total1.setText("TOTAL");

        titulo.setBackground(new java.awt.Color(0, 0, 0));
        titulo.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setText("Restaurante Origenes");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("EXPEDIDO EN:   LOCAL PRINCIPAL");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TELÉFONO     :   054-523263");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("N° DE BOLETA:");

        jTextField1.setBorder(null);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("FECHA            : ");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("HORA             : ");

        jTextField2.setBorder(null);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("IGV (18%):");

        jTextField5.setBorder(null);

        javax.swing.GroupLayout reciboLayout = new javax.swing.GroupLayout(recibo);
        recibo.setLayout(reciboLayout);
        reciboLayout.setHorizontalGroup(
            reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reciboLayout.createSequentialGroup()
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reciboLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(titulo))
                    .addGroup(reciboLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addGroup(reciboLayout.createSequentialGroup()
                                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reciboLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reciboLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reciboLayout.createSequentialGroup()
                        .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(total1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(total)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))))
        );
        reciboLayout.setVerticalGroup(
            reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reciboLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(reciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(total, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jButton1.setBackground(new java.awt.Color(74, 4, 21));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("IMPRIMIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(recibo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(recibo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            PrinterJob g = PrinterJob.getPrinterJob();
            g.setPrintable(this);
            boolean top = g.printDialog();
            if(top){
                g.print();
            }
        }
        catch(PrinterException pex){
            JOptionPane.showMessageDialog(null, "Error de programa", "Error\n"+pex, JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Factura().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JPanel recibo;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField total;
    private javax.swing.JLabel total1;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graf, PageFormat pagfor, int index) throws PrinterException {
       if(index>0){
           return NO_SUCH_PAGE;
       } 
       Graphics2D x=(Graphics2D) graf;
       x.translate(pagfor.getImageableX() + 30, pagfor.getImageableY()+30);
       x.scale(1.0, 1.0);
       
       recibo.printAll(graf);
       return PAGE_EXISTS;
       
    }
}


























