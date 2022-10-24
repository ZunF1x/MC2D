package fr.zunf1x.mc2d.console;

import fr.zunf1x.mc2d.MC2D;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Console extends JFrame implements ActionListener {

    public JTextPane console;
    public JTextField input;
    public JScrollPane scrollPane;

    public JButton submit = new JButton("Submit");

    public StyledDocument document;

    public boolean trace;

    public Console() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        setTitle("MC2D Console");

        console = new JTextPane();
        console.setEditable(false);
        console.setFont(new Font("Courier New", Font.PLAIN, 12));
        console.setOpaque(false);
        console.setForeground(Color.WHITE);
        console.setCaretColor(Color.WHITE);

        document = console.getStyledDocument();

        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                print(String.valueOf((char) b), false);
            }
        });

        System.setOut(ps);
        System.setErr(ps);

        Panel p = new Panel();
        p.setLayout(new BorderLayout());

        input = new JTextField();
        input.setEditable(true);
        input.setForeground(Color.WHITE);
        input.setCaretColor(Color.WHITE);
        input.setOpaque(false);

        submit.addActionListener(this);

        scrollPane = new JScrollPane(console);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        p.add(input);
        p.add(submit, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(p, BorderLayout.SOUTH);

        getContentPane().setBackground(new Color(50, 50, 50));

        setSize(660, 350);
        setLocationRelativeTo(null);
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if (input.getText().length() >= 1) {
                Thread t = new Thread("Chat") {
                    @Override
                    public void run() {
                        try {
                            if (!input.getText(0, 1).equals("/")) {
                                MC2D.instance.logger.log(input.getText());
                            } else {
                                MC2D.instance.logger.err("Command Not Found !");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                };
                t.start();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                t.interrupt();

                input.setText(null);
            }
        }
    }

    public void update() {

    }

    public void print(String s, boolean trace) {
        this.print(s, trace, new Color(255, 255, 255));
    }

    public void print(String s, boolean trace, Color c) {
        console.setCaretPosition(console.getDocument().getLength());
        console.update(console.getGraphics());

        Style style = console.addStyle("Style", null);
        StyleConstants.setForeground(style, c);

        if (trace) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String caller = elements[0].getClassName();

            s = caller + " -> " + s;
        }

        try {
            document.insertString(document.getLength(), s + "\n", style);
        } catch (Exception ignored) {}
    }
}
