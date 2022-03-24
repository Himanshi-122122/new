package ui;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JPanel {
    private JButton addButton;
    private JButton showButton;
    private JButton sortButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton exitButton;

    //EFFECTS: construct all buttons using in the application
    public Buttons() {

        //constructs addButton
        addButton = new JButton("Adds Item");
        addButton.setPreferredSize(new Dimension(50, 50));

        //constructs showButton button
        showButton = new JButton("Show All Items");
        showButton.setPreferredSize(new Dimension(50, 50));

        //constructs sortButton button
        sortButton = new JButton("Sort Items");
        sortButton.setPreferredSize(new Dimension(50, 50));

        //constructs deleteButton button
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(50, 50));

        //constructs editButton button
        editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(50, 50));

        //constructs exitButton button
        exitButton = new JButton("Quit");
        exitButton.setPreferredSize(new Dimension(50, 50));
    }

    //EFFECTS: getters
    public JButton getAddButton() {
        return addButton;
    }

    public JButton getShowButton() {
        return showButton;
    }

    public JButton getSortButton() {
        return sortButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

}
