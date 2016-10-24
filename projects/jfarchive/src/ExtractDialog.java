/**
 * Created : Mar 28, 2012
 *
 * @author pquiring
 */

public class ExtractDialog extends javax.swing.JDialog {

  /**
   * Creates new form ExtractDialog
   */
  public ExtractDialog(java.awt.Frame parent, boolean modal, boolean selectFiles) {
    super(parent, modal);
    initComponents();
    this.selectFiles.setEnabled(selectFiles);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        allFiles = new javax.swing.JRadioButton();
        selectFiles = new javax.swing.JRadioButton();
        createFolders = new javax.swing.JCheckBox();
        overwriteFiles = new javax.swing.JCheckBox();
        newerOnly = new javax.swing.JCheckBox();
        accept = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Extract Options");

        buttonGroup1.add(allFiles);
        allFiles.setSelected(true);
        allFiles.setText("All Files");

        buttonGroup1.add(selectFiles);
        selectFiles.setText("Selected Files");
        selectFiles.setEnabled(false);

        createFolders.setSelected(true);
        createFolders.setText("Re-create folders");
        createFolders.setEnabled(false);

        overwriteFiles.setSelected(true);
        overwriteFiles.setText("Overwrite existing files");

        newerOnly.setText("Do not extract older files");

        accept.setText("Accept");
        accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptActionPerformed(evt);
            }
        });

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accept))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(allFiles)
                            .addComponent(selectFiles))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(overwriteFiles)
                            .addComponent(createFolders)
                            .addComponent(newerOnly))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allFiles)
                    .addComponent(createFolders))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectFiles)
                    .addComponent(overwriteFiles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newerOnly)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(accept))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
    dispose();
  }//GEN-LAST:event_cancelActionPerformed

  private void acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptActionPerformed
    accepted = true;
    dispose();
  }//GEN-LAST:event_acceptActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accept;
    private javax.swing.JRadioButton allFiles;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancel;
    private javax.swing.JCheckBox createFolders;
    private javax.swing.JCheckBox newerOnly;
    private javax.swing.JCheckBox overwriteFiles;
    private javax.swing.JRadioButton selectFiles;
    // End of variables declaration//GEN-END:variables

  public boolean accepted;

  public boolean getSelectFiles() { return selectFiles.isSelected(); }

  public boolean getCreateFolders() { return createFolders.isSelected(); }

  public boolean getOverwriteFiles() { return overwriteFiles.isSelected(); }

  public boolean getNewerOnly() { return newerOnly.isSelected(); }
}