package ui;

import model.ItemList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    //Parameter
    private static final int WIDTH = 900;
    private static final int HEIGHT = 400;


    //Buttons
    private Buttons buttons;
    private JButton addButton;
    private JButton showButton;
    private JButton sortButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton quitButton;

    //Menu bar
    private JMenuBar mainMenuBar;
    private JMenu loadButtonMenuBar;
    private JMenu saveButtonMenuBar;



    // Item, ItemList fields
    private ItemList itemList;

    public MainGUI() {
        //Holds title of frame
        super("Supper Market Items Management");
        initializeGUI();
    }

    public static void main(String[] args) {
        new MainGUI();
    }

    public void initializeGUI() {
        //construct the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);

        //add menubar and buttons for frame
        addMenuBar();

        //set visible for Frame
        setVisible(true);

        // initiate new item list
        itemList = new ItemList("My Items");
    }

    //MODIFIERS: this
    //EFFECTS: adds a menu bar with load and save options
    public void addMenuBar() {
        mainMenuBar = new JMenuBar();

        //Constructs Load button
        loadButtonMenuBar = new JMenu("Load");
        loadButtonMenuBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLoad();
            }
        });

        //Constructs Save button
        saveButtonMenuBar = new JMenu("Save");
        saveButtonMenuBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doSave();
            }
        });

        //Adds Load, Save buttons into main menu bar
        mainMenuBar.add(loadButtonMenuBar);
        mainMenuBar.add(saveButtonMenuBar);

        setJMenuBar(mainMenuBar);
    }


    //MODIFIES: this
    //EFFECTS:
    public void buttonsImplementItem() {
        addButton = buttons.getAddButton();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doAdd();
            }
        });

        showButton = buttons.getShowButton();
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doShowList();
            }
        });

        sortButton = buttons.getSortButton();
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doSortUnder();
            }
        });

        quitButton = buttons.getExitButton();
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doExit();
            }
        });
    }

    //MODIFIES:
    //EFFECTS:
    public void buttonsImplementItemInfo() {
        deleteButton = buttons.getDeleteButton();
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doDelete();
            }
        });

        editButton = buttons.getEditButton();
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doEdit();
            }
        });
    }



    /**
     * Main methods
     */

    //MODIFIES: this
    //EFFECTS: load
    public void doLoad() {

    }

    //MODIFIES: this
    //EFFECTS: save
    public void doSave() {

    }

    //EFFECTS:
    public void doAdd() {

    }

    //EFFECTS:
    public void doShowList() {

    }

    //EFFECTS:
    public void doSortUnder() {

    }

    //EFFECTS:
    public void doExit() {

    }

    //EFFECTS:
    public void doDelete() {

    }

    //EFFECTS:
    public void doEdit() {

    }

}
