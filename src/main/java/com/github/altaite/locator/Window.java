package com.github.altaite.locator;

import com.github.altaite.locator.engine.Engine;
import com.github.altaite.locator.engine.EngineFactory;
import com.github.altaite.locator.engine.EngineFromDirectory;
import com.github.altaite.locator.engine.FileResult;
import com.github.altaite.locator.engine.Query;
import com.github.altaite.locator.engine.TextFile;
import com.github.altaite.locator.execute.Executor;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public final class Window {

    private JList hitJList;
    private final DefaultListModel<FileResult> model = new DefaultListModel<>();
    private final Executor executor = new Executor();

    private void run() {
        //executor.runEmacsDaemon();

        JFrame frame = new JFrame("Locator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextField searchField;
        searchField = new JTextField("");
        searchField.addActionListener((ActionEvent e) -> {
            openFileInEditor();
        });
        searchField.setBounds(10, 10, 750, 30);
        frame.add(searchField);

        JLabel label = new JLabel("Type to search.");
        label.setBounds(10, 40, 750, 40);
        frame.add(label);

        hitJList = new JList(model);
        hitJList.setBounds(10, 80, 750, 500);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) hitJList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.LEFT);
        frame.add(hitJList);

        hitJList.getInputMap(JComboBox.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("ENTER"), "doSomething");
        hitJList.getActionMap().put("doSomething", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileInEditor();
            }
        });

        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        int c = e.getKeyCode();
                        switch (c) {
                            case 38:
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        hitJList.requestFocus();
                                    }
                                });
                                break;
                            case 40:
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        hitJList.requestFocus();
                                    }
                                });
                                break;
                            default:
                                searchField.requestFocus();
                                break;
                        }
                        return false;
                    }
                });

        /*JTextArea t = new JTextArea();
        t.setText("bla\nbla");
        frame.add(t);
         */
        EngineFactory ef = new EngineFromDirectory();
        Engine e = ef.create(new File("d:/t/documents/private"));
        System.out.println("Engine created.");

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                runSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                runSearch();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                runSearch();
            }

            public void displayHits() {

            }

            public void runSearch() {
                model.clear();
                Query q = new Query(searchField.getText());
                List<FileResult> results = e.search(q);
                StringBuilder sb = new StringBuilder("<html>Found: ");
                sb.append(results.size());
                for (int i = 0; i < Math.min(results.size(), 20); i++) {
                    FileResult u = results.get(i);
                    model.addElement(u);
                }
                sb.append("</html>");
                label.setText(sb.toString() + results.size());
                hitJList.setSelectedIndex(0);
            }
        });
    }

    private void openFileInEditor() {
        int i = hitJList.getSelectedIndex();
        if (i >= 0) {
            FileResult f = model.getElementAt(i);
            executor.openInBrackets(f.getFile());
        }
    }

    public static void main(String args[]) {
        Window window = new Window();
        window.run();
    }
}
