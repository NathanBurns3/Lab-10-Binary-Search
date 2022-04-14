import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

public class SortingFrame extends JFrame{

    JPanel mainPnl;
    JPanel topPnl;
    JPanel displayPnl;

    JTextArea displayTA;
    JScrollPane scroller;

    JButton addBtn;
    JButton searchBtn;

    JTextField addTF;
    JTextField searchTF;

    JLabel addLbl;
    JLabel searchLbl;

    ArrayList<String> strings = new ArrayList<String>();
    ArrayList<String> strings2 = new ArrayList<String>();

    public SortingFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTopPnl();
        mainPnl.add(topPnl, BorderLayout.NORTH);

        createDisplayPnl();
        mainPnl.add(displayPnl, BorderLayout.CENTER);

        add(mainPnl);
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTopPnl() {
        topPnl = new JPanel();
        topPnl.setLayout(new GridLayout(2, 3));

        addLbl = new JLabel("Add a string:");
        addTF = new JTextField(10);
        addBtn = new JButton("Add");

        topPnl.add(addLbl);
        topPnl.add(addTF);
        topPnl.add(addBtn);

        searchLbl = new JLabel("Search for a string:");
        searchTF = new JTextField(10);
        searchBtn = new JButton("Search");

        topPnl.add(searchLbl);
        topPnl.add(searchTF);
        topPnl.add(searchBtn);

        addBtn.addActionListener((ActionEvent ae) -> {
            String sr = addTF.getText();

            strings.add(sr);
            strings2.add(sr);

            Collections.sort(strings);
            Collections.sort(strings2);

            addTF.setText("");
        });

        searchBtn.addActionListener((ActionEvent ae) -> {
            String st = searchTF.getText();
            String[] stringArray = strings.toArray(new String[0]);

            int result = binarySearch(stringArray, st);

            if (result != -1) {
                displayTA.append(st);
                displayTA.append("\n");
            }
            else {
                strings2.add(st);
                Collections.sort(strings2);
                displayTA.append("The list does not contain that string. If it did it would be in index " + strings2.indexOf(st));
                strings2.remove(st);
                displayTA.append("\n");
            }
        });
    }

    static int binarySearch(String [] arr, String str) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;

            int res = str.compareTo(arr[middle]);

            if (res == 0) {
                return middle;
            }
            else if (res > 0) {
                left = middle + 1;
            }
            else {
                right = middle - 1;
            }
        }
        return -1;
    }

    private void createDisplayPnl() {
        displayPnl = new JPanel();
        displayTA = new JTextArea(30,40);
        displayTA.setEditable(false);
        scroller = new JScrollPane(displayTA);
        displayPnl.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
        displayPnl.add(scroller);
    }
}