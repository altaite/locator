package com.github.altaite.locator;

import com.github.altaite.locator.engine.Engine;
import com.github.altaite.locator.engine.EngineFactory;
import com.github.altaite.locator.engine.EngineFromDirectory;
import com.github.altaite.locator.engine.Query;
import com.github.altaite.locator.engine.Unit;
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

    private final AppState state = new AppState();
    private JList hitJList;
    private final DefaultListModel<HitLink> model = new DefaultListModel<>();
    private final Executor executor = new Executor();

    private void run() {
        executor.runEmacsDaemon();

        JFrame f = new JFrame("Locator");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextField searchField;
        searchField = new JTextField("");
        searchField.addActionListener((ActionEvent e) -> {
            int i = hitJList.getSelectedIndex();
            if (i >= 0) {
                HitLink hl = model.getElementAt(i);
                executor.openInEmacsClient(hl.getFile());
            }
        });
        searchField.setBounds(10, 10, 750, 30);
        f.add(searchField);
        
        JLabel l = new JLabel("Type to search.");
        l.setBounds(10, 40, 750, 40);
        f.add(l);

        hitJList = new JList(model);
        hitJList.setBounds(10, 80, 750, 500);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) hitJList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.LEFT);
        f.add(hitJList);

        hitJList.getInputMap(JComboBox.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("ENTER"), "doSomething");
        hitJList.getActionMap().put("doSomething", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int i = hitJList.getSelectedIndex();
                if (i >= 0) {
                    HitLink hl = model.getElementAt(i);
                    executor.openInEmacsClient(hl.getFile());
                }
            }
        });

        f.setSize(800, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

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
                                break;
                        }
                        return false;
                    }
                });

        JTextArea t = new JTextArea();
        t.setText("bla\nbla");
        f.add(t);

        EngineFactory ef = new EngineFromDirectory();
        Engine e = ef.create(new File("d:/t/memo/thoughts"));
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
                List<Unit> units = e.findAndMatches(q);

                List<String> hitList = new ArrayList<>();
                units.forEach(u -> hitList.add(u.getId()));
                state.setHitList(hitList);

                state.setHits(units.size());
                StringBuilder sb = new StringBuilder("<html>Found: ");
                sb.append(units.size());
                for (int i = 0; i < Math.min(units.size(), 20); i++) {
                    Unit u = units.get(i);
                    String id = u.getId();
                    model.addElement(new HitLink(new File(id))); // todo getFile
                }
                sb.append("</html>");
                l.setText(sb.toString() + units.size());
                hitJList.setSelectedIndex(0);
            }
        });
    }

    public static void main(String args[]) {
        Window window = new Window();
        window.run();
    }
}
