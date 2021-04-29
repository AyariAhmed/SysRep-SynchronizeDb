import javax.swing.*;

import java.awt.*;

public class Ho_graphique {
    JFrame f;
    JScrollPane sp;
    String[] column = {"Date", "region", "product","quantity","cost","amt","tax","total"};

    public Ho_graphique( ) {
        f = new JFrame("HO");
        JTable jt = new JTable((new String[][] {}), column);
        sp = new JScrollPane(jt);
        f.add(sp,BorderLayout.CENTER);
        f.setSize(850, 400);
        f.setVisible(true);
    }

    public void setTableRows(String[][] data) {

        f.remove(sp);
        JTable jt = new JTable(data, column);
        jt.setEnabled(false);

        sp=new JScrollPane(jt);
        f.add(sp,BorderLayout.CENTER);
        f.setVisible(true);

    }

}
