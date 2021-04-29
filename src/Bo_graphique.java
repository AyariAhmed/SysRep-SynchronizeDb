
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;


public class Bo_graphique {

    JFrame frame;
    JTextField dateTextField;
    JTextField regionTextField;
    JTextField productTextField;
    JTextField quantityTextField;
    JTextField costTextField;
    JTextField amtTextField;
    JTextField taxTextField;
    JTextField totalTextField;
    JScrollPane sp;
    String[] column = {"Date", "region", "product", "quantity", "cost", "amt", "tax", "total"};


    public Bo_graphique(String branchOfficeNumber, ActionListener saveToDB,ActionListener syncWithHO,ActionListener saveAndSync) {


        frame = new JFrame("BO "+branchOfficeNumber);
        JPanel addSaleForm = new JPanel();
        Border border = addSaleForm.getBorder();
        Border margin = new EmptyBorder(10, 10, 10, 10);
        addSaleForm.setBorder(new CompoundBorder(border, margin));

        GridBagLayout panelGridBagLayout = new GridBagLayout();
        panelGridBagLayout.columnWidths = new int[]{86, 86, 0};
        panelGridBagLayout.rowHeights = new int[]{20, 20, 20, 20, 20, 20, 20, 20, 0};
        panelGridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        panelGridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0, 0,
                Double.MIN_VALUE};
        addSaleForm.setLayout(panelGridBagLayout);

        dateTextField = addLabelAndTextField("Date:", 0, addSaleForm);
        regionTextField = addLabelAndTextField("Region:", 1, addSaleForm);
        productTextField = addLabelAndTextField("Product:", 2, addSaleForm);
        quantityTextField = addLabelAndTextField("Quantity:", 3, addSaleForm);
        costTextField = addLabelAndTextField("Cost:", 4, addSaleForm);
        amtTextField = addLabelAndTextField("Amt:", 5, addSaleForm);
        taxTextField = addLabelAndTextField("Tax:", 6, addSaleForm);
        totalTextField = addLabelAndTextField("Total:", 7, addSaleForm);

        JTable jt = new JTable((new String[][]{}), column);
        JPanel buttonsPanel = new JPanel();
        JButton saveButton = new JButton("SAVE SALE");
        JButton syncButton = new JButton("SYNC ");
        JButton saveAndSyncButton = new JButton("SAVE AND SYNC");

        GridLayout grid =new GridLayout(0,1,1,1);
        frame.setLayout(grid);

        sp = new JScrollPane(jt);
        buttonsPanel.add(saveButton, BorderLayout.WEST);
        buttonsPanel.add(syncButton, BorderLayout.WEST);
        buttonsPanel.add(saveAndSyncButton, BorderLayout.WEST);

        saveButton.addActionListener(saveToDB);
        syncButton.addActionListener(syncWithHO);
        saveAndSyncButton.addActionListener(saveAndSync);

        frame.add(addSaleForm);
        frame.add(buttonsPanel);
        frame.add(sp);

        frame.setSize(800, 800);
        frame.setVisible(true);
    }


    public void setTableRows(String[][] data) {

        frame.remove(sp);
        JTable jt = new JTable(data, column);

        jt.setEnabled(false);

        sp = new JScrollPane(jt);
        frame.add(sp, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void clearFields() {

        dateTextField.setText("");
        regionTextField.setText("");
        productTextField.setText("");
        quantityTextField.setText("");
        costTextField.setText("");
        amtTextField.setText("");
        taxTextField.setText("");
        totalTextField.setText("");
    }

    public Sale getSaleFromForm() {
        return new Sale(
                dateTextField.getText(),
                regionTextField.getText(),
                productTextField.getText(),
                quantityTextField.getText().equals("") ?0: Integer.parseInt(quantityTextField.getText()),
                costTextField.getText().equals("") ?0: Float.parseFloat(costTextField.getText()),
                amtTextField.getText().equals("") ?0: Float.parseFloat(amtTextField.getText()),
                taxTextField.getText().equals("") ?0: Float.parseFloat(taxTextField.getText()),
                totalTextField.getText().equals("") ?0: Float.parseFloat(totalTextField.getText())
        );
    }

    private JTextField addLabelAndTextField(String labelText, int yPos,
                                            Container containingPanel) {

        JLabel label = new JLabel(labelText);
        GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
        gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
        gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
        gridBagConstraintForLabel.gridx = 0;
        gridBagConstraintForLabel.gridy = yPos;
        containingPanel.add(label, gridBagConstraintForLabel);

        JTextField textField = new JTextField();
        GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
        gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
        gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
        gridBagConstraintForTextField.gridx = 1;
        gridBagConstraintForTextField.gridy = yPos;
        containingPanel.add(textField, gridBagConstraintForTextField);
        textField.setColumns(10);
        return textField;
    }


}
