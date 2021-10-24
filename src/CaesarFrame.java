import javafx.scene.layout.Border;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CaesarFrame extends JFrame {
    private JButton jb;
    private JTextField jtf1, jtf2;
    private JComboBox jcb;
    private JPanel jp1, jp2;

    private boolean decode = false;

    /*private String caesarCode(String input, char offset) {
        String s = "";
        input=input.toUpperCase();
        offset=Character.toUpperCase(offset);
        int numericOffset=(int)offset - (int)'A';
        int len = input.length();
        for(int x = 0; x < len; x++){
            char c = (char)(input.charAt(x) + numericOffset);
            if (c > 'Z')
                s += (char)(input.charAt(x) - (26-numericOffset));
            else
                s += (char)(input.charAt(x) + numericOffset);
        }
        return s;
    }*/

    public CaesarFrame() {
        super("SwingLab");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400,110);
        //this.setResizable(false);
        setResizable(true);
        GridLayout gl = new GridLayout();
        gl.setRows(2);
        gl.setColumns(1);
        this.setLayout(gl);

        //első panel -> felső
        jp1 = new JPanel(new FlowLayout());
        add(jp1);
        Character chars[] = new Character[26];
        for(int i = 0;i<=25;i++) {
            chars[i] = (char)(i+65);
        }
        jcb = new JComboBox<Character>(chars);
        jp1.add(jcb);
        jtf1 = new JTextField(20);
        jtf1.setEnabled(true);
        jtf1.getDocument().addDocumentListener(new InputKeyFieldListener());
        jtf1.addFocusListener(new TextFieldFocusListener(false));
        jp1.add(jtf1);
        jb = new JButton();
        jb.addActionListener(new OkButtonActionListener());
        jb.setText("Code!");
        jp1.add(jb);

        //második panel -> alsó
        jp2 = new JPanel(new BorderLayout());
        add(jp2);
        jp2.add(new JLabel("Output:"), BorderLayout.WEST);
        jtf2 = new JTextField(20);
        jtf2.setEditable(true);
        jtf2.addFocusListener(new TextFieldFocusListener(true));
        jp2.add(jtf2);

        setVisible(true);
    }

    private class OkButtonActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae) {
            if(decode==true)
                jtf1.setText(Caesar.caesarDecode(jtf2.getText(), (char)jcb.getSelectedItem()));
            else
                jtf2.setText(Caesar.caesarCode(jtf1.getText(), (char)jcb.getSelectedItem()));

        }
    }

    private class InputKeyFieldListener implements DocumentListener
    {
        public void insertUpdate(DocumentEvent de) {
            jtf2.setText(Caesar.caesarCode(jtf1.getText(), (char)jcb.getSelectedItem()));
        }

        public void removeUpdate(DocumentEvent de) {
            jtf2.setText(Caesar.caesarCode(jtf1.getText(), (char)jcb.getSelectedItem()));
        }

        public void changedUpdate(DocumentEvent de) {
            jtf2.setText(Caesar.caesarCode(jtf1.getText(), (char)jcb.getSelectedItem()));
        }
    }

    private class TextFieldFocusListener implements FocusListener {
        boolean direction;
        public TextFieldFocusListener(boolean b) {
            direction = b;
        }
        public void focusGained(FocusEvent fe) {
        }
        public void focusLost(FocusEvent fe) {
            decode = direction;
        }
    }

}
