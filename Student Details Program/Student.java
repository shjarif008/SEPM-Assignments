import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudentApp extends JFrame {
    JTextField nameTxt = new JTextField(15);
    JTextField emailTxt = new JTextField(15);
    DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Email"}, 0);
    JTable table = new JTable(model);
    ArrayList<String[]> data = new ArrayList<>();

    public StudentApp() {
        setTitle("Student Details");
        setSize(400, 500);
        setLayout(new FlowLayout());

        add(new JLabel("Name:")); add(nameTxt);
        add(new JLabel("Email:")); add(emailTxt);

        JButton insBtn = new JButton("Insert");
        JButton viewBtn = new JButton("View");
        JButton updBtn = new JButton("Update");
        JButton delBtn = new JButton("Delete");

        add(insBtn); add(viewBtn); add(updBtn); add(delBtn);
        add(new JScrollPane(table));

        insBtn.addActionListener(e -> {
            data.add(new String[]{nameTxt.getText(), emailTxt.getText()});
            nameTxt.setText(""); emailTxt.setText("");
        });

        viewBtn.addActionListener(e -> {
            model.setRowCount(0);
            for (String[] row : data) model.addRow(row);
        });

        updBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                data.get(row)[0] = nameTxt.getText();
                data.get(row)[1] = emailTxt.getText();
                viewBtn.doClick();
            }
        });

        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                data.remove(row);
                viewBtn.doClick();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentApp();
    }
}
