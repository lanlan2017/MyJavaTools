package test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Mikle Garin
 * @see https://stackoverflow.com/questions/45439231/implementing-autocomplete-with-jtextfield-and-jpopupmenu
 */

public final class AutocompleteField extends JTextField implements FocusListener, DocumentListener, KeyListener {
    /**
     * {@link Function} for text lookup.
     * It simply returns {@link List} of {@link String} for the text we are looking results for.
     */
    private final Function<String, List<String>> lookup;

    /**
     * {@link List} of lookup results.
     * It is cached to optimize performance for more complex lookups.
     */
    private final List<String> results;

    /**
     * {@link JWindow} used to display offered options.
     */
    private final JWindow popup;

    /**
     * Lookup results {@link JList}.
     */
    private final JList list;

    /**
     * {@link #list} model.
     */
    private final ListModel model;

    /**
     * Constructs {@link AutocompleteField}.
     *
     * @param lookup {@link Function} for text lookup
     */
    public AutocompleteField(final Function<String, List<String>> lookup) {
        super();
        this.lookup = lookup;
        this.results = new ArrayList<>();

        final Window parent = SwingUtilities.getWindowAncestor(this);
        popup = new JWindow(parent);
        popup.setType(Window.Type.POPUP);
        popup.setFocusableWindowState(false);
        popup.setAlwaysOnTop(true);

        model = new ListModel();
        list = new JList(model);

        popup.add(new JScrollPane(list) {
            @Override
            public Dimension getPreferredSize() {
                final Dimension ps = super.getPreferredSize();
                ps.width = AutocompleteField.this.getWidth();
                return ps;
            }
        });

        addFocusListener(this);
        getDocument().addDocumentListener(this);
        addKeyListener(this);
    }

    /**
     * Displays autocomplete popup at the correct location.
     */
    private void showAutocompletePopup() {
        final Point los = AutocompleteField.this.getLocationOnScreen();
        popup.setLocation(los.x, los.y + getHeight());
        popup.setVisible(true);
    }

    /**
     * Closes autocomplete popup.
     */
    private void hideAutocompletePopup() {
        popup.setVisible(false);
    }

    @Override
    public void focusGained(final FocusEvent e) {
        SwingUtilities.invokeLater(() -> {
            if (results.size() > 0) {
                showAutocompletePopup();
            }
        });
    }

    private void documentChanged() {
        SwingUtilities.invokeLater(() -> {
            // Updating results list
            results.clear();
            results.addAll(lookup.apply(getText()));

            // Updating list view
            model.updateView();
            list.setVisibleRowCount(Math.min(results.size(), 10));

            // Selecting first result
            if (results.size() > 0) {
                list.setSelectedIndex(0);
            }

            // Ensure autocomplete popup has correct size
            popup.pack();

            // Display or hide popup depending on the results
            if (results.size() > 0) {
                showAutocompletePopup();
            } else {
                hideAutocompletePopup();
            }
        });
    }

    @Override
    public void focusLost(final FocusEvent e) {
        SwingUtilities.invokeLater(this::hideAutocompletePopup);
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            final int index = list.getSelectedIndex();
            if (index != -1 && index > 0) {
                list.setSelectedIndex(index - 1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            final int index = list.getSelectedIndex();
            if (index != -1 && list.getModel().getSize() > index + 1) {
                list.setSelectedIndex(index + 1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            final String text = (String) list.getSelectedValue();
            setText(text);
            setCaretPosition(text.length());
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            hideAutocompletePopup();
        }
    }

    @Override
    public void insertUpdate(final DocumentEvent e) {
        documentChanged();
    }

    @Override
    public void removeUpdate(final DocumentEvent e) {
        documentChanged();
    }

    @Override
    public void changedUpdate(final DocumentEvent e) {
        documentChanged();
    }

    @Override
    public void keyTyped(final KeyEvent e) {
        // Do nothing
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        // Do nothing
    }

    /**
     * Custom list model providing data and bridging view update call.
     */
    private class ListModel extends AbstractListModel {
        @Override
        public int getSize() {
            return results.size();
        }

        @Override
        public Object getElementAt(final int index) {
            return results.get(index);
        }

        /**
         * Properly updates list view.
         */
        public void updateView() {
            super.fireContentsChanged(AutocompleteField.this, 0, getSize());
        }
    }

    /**
     * Sample {@link AutocompleteField} usage.
     *
     * @param args run arguments
     */
    public static void main(final String[] args) {
        final JFrame frame = new JFrame("Sample autocomplete field");

        // Sample data list
        final List<String> values = Arrays.asList("Frame", "Dialog", "Label", "Tree", "Table", "List", "Field");

        // Simple lookup based on our data list
        final Function<String, List<String>> lookup = text -> values.stream()
                .filter(v -> !text.isEmpty() && v.toLowerCase().contains(text.toLowerCase()) && !v.equals(text))
                .collect(Collectors.toList());

        // Autocomplete field itself
        final AutocompleteField field = new AutocompleteField(lookup);
        field.setColumns(15);

        final JPanel border = new JPanel(new BorderLayout());
        border.setBorder(new EmptyBorder(50, 50, 50, 50));
        border.add(field);
        frame.add(border);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}