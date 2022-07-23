package ui;

import model.Event;
import model.EventLog;
import model.Item;
import model.ItemList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MainGUI extends JFrame {
    //Static fields
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;
    private static final int ICON_WIDTH = 160;
    private static final int ICON_HEIGHT = 100;

    //Jframe
    private Container container;

    //Icons
    private Icon addIcon;
    private Icon showIcon;
    private Icon sortIcon;
    private Icon editIcon;
    private Icon deleteIcon;
    private Icon quitIcon;

    //Item & ItemList
    private ItemList itemList;
    //    private Item item;
    private int itemIndex;
    private int getEditOrderInput;

    //Save & Load
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String jsonStoreAddress = "./data/itemList.json";

    /**
     * GUI Code base ====================================================================
     */
    //represents the main panel
    public MainGUI() {
        super("Supper Market Items Management"); //Holds title of the main frame
        initializeGUI();
    }

    //EFFECTS: initiates the main program
    public static void main(String[] args) {
        new MainGUI();
    }

    //MODIFIES: this
    //EFFECTS: this method is called by MainGUI
    //          - creates the main frame by container, set background for container
    //          - creates Menu bar, buttons panel
    //          - creates new item list, file for reader and writer
    public void initializeGUI() {
        //constructs the main frame
        container = new JLabel(new ImageIcon(new ImageIcon("./data/icons/background.jpg").getImage()
                .getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT))); //Source: freepik.com

//        container.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        container.setLayout(new FlowLayout()); //set style of layout for buttons

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stop application when closing window

        //adds menu bar and buttons to frame
        addMenuBar();
        add(container); //add container containing buttons, background into frame
        buttonsGUI();

        //sets visible for Frame
        setResizable(false);
        setSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);

        //creates new item list
        itemList = new ItemList("My Store");

        //creates file location for save and load
        jsonReader = new JsonReader(jsonStoreAddress);
        jsonWriter = new JsonWriter(jsonStoreAddress);

    }

    /**
     * printlog ====================================================================
     */

    private class PrintLogAction extends AbstractAction {
        PrintLogAction() {
            super("Print log to...");
            for (Iterator<Event> e = EventLog.getInstance().iterator(); e.hasNext(); ) {
                Event ev = e.next();
                System.out.println(ev);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    /**
     * create menu bar ================================================================
     */
    //MODIFIERS: this
    //EFFECTS: adds a menu bar with load and save options
    private void addMenuBar() {
        //constructs main menu bar
        JMenuBar mainMenuBar = new JMenuBar();
        mainMenuBar.setPreferredSize(new Dimension(WIDTH, 40));
        JMenu menu = new JMenu("File");

        //constructs Save button
        JMenuItem saveButtonMenuBar = new JMenuItem("Save", new ImageIcon("./data/icons/saveIcon.png"));
        saveButtonMenuBar.addActionListener(e -> doSave());

        //constructs Load button
        JMenuItem loadButtonMenuBar = new JMenuItem("Load", new ImageIcon("./data/icons/loadIcon.png"));
        loadButtonMenuBar.addActionListener(e -> doLoad());


        //adds Load, Save buttons to main menu bar
        menu.add(saveButtonMenuBar);
        menu.add(loadButtonMenuBar);
        mainMenuBar.add(menu);

        setJMenuBar(mainMenuBar);
    }

    /**
     * creates set of buttons ==========================================================
     */
    //EFFECTS: creates all buttons for main frame when application is run
    private void buttonsGUI() {
        buttonsImplementItem(); //creates Add, Show, Sort buttons
        buttonsImplementItemInfo(); //creates Edit, Delete buttons
        buttonsQuit();
    }

    //MODIFIES: this
    //EFFECTS: creates Add, Show, Sort buttons and their functionality
    private void buttonsImplementItem() {
        addIcon = new ImageIcon("./data/icons/addIcon.png");
        JButton addButton = new JButton("Add Item", addIcon);
        addButton.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        addButton.addActionListener(e -> doAdd());
        container.add(addButton);

        showIcon = new ImageIcon("./data/icons/showIcon.png");
        JButton showButton = new JButton("Show Items", showIcon);
        showButton.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        showButton.addActionListener(e -> doShowList());
        container.add(showButton);

        sortIcon = new ImageIcon("./data/icons/sortIcon.png");
        JButton sortButton = new JButton("Sort Items", sortIcon);
        sortButton.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        sortButton.addActionListener(e -> doSortUnderCount());
        container.add(sortButton);
    }

    //MODIFIES: this
    //EFFECTS: creates Edit, Delete button and their functionality
    private void buttonsImplementItemInfo() {
        editIcon = new ImageIcon("./data/icons/editIcon.png");
        JButton editButton = new JButton("Edit Item", editIcon);
        editButton.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        editButton.addActionListener(e -> doEdit());
        container.add(editButton);


        deleteIcon = new ImageIcon("./data/icons/deleteIcon.png");
        JButton deleteButton = new JButton("Delete Item", deleteIcon);
        deleteButton.setPreferredSize(new Dimension(ICON_WIDTH, ICON_HEIGHT));
        deleteButton.addActionListener(e -> doDelete());
        container.add(deleteButton);

    }

    //MODIFIES: this
    //EFFECTS: creates Quit button and its functionality
    private void buttonsQuit() {
        quitIcon = new ImageIcon("./data/icons/quitIcon.png");
        JButton quitButton = new JButton("Quit Application", quitIcon);
        quitButton.setPreferredSize(new Dimension(ICON_WIDTH, 60));
        quitButton.addActionListener(e -> doExit());
        container.add(quitButton);
    }


    /**
     * Main functionality for buttons ===================================================
     */

    //MODIFIES: this
    //EFFECTS: parses the list of items in string type to text to be displayed by the label
    private ArrayList<JComponent> parseStringListToLabelList(ArrayList<String> items) {
        ArrayList<JComponent> allItems = new ArrayList<>();
        if (items.isEmpty()) {
            allItems.add(new JLabel("You currently have no item!"));
        } else {
            for (String s : items) {
                allItems.add(new JLabel(s));
            }
        }
        return allItems;
    }

    //MODIFIES: this
    //EFFECTS: parses the list of items to the list of strings following specific construction
    private ArrayList<String> parseConstructedStringList(ArrayList<Item> list) {
        ArrayList<String> stringList = new ArrayList<>();
        itemIndex = 0;
        for (Item i : list) {
            itemIndex++;
            stringList.add(itemIndex + "# -> " + "ID: " + i.getItemID() + " | " + "Name: " + i.getItemName()
                    + " | " + "Count: " + i.getItemCount() + " | " + "Position: " + i.getItemPosition() + " | "
                    + "Price included BC tax: " + i.priceAfterBCTax() + "$");
        }
        return stringList;
    }

    /**
     * creates functionality for "Add" button =============
     */

    //MODIFIES: this
    //EFFECTS: creates functionality for Add button
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void doAdd() {
        //creates Text field for item's information
        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField count = new JTextField();
        JTextField position = new JTextField();
        JTextField inPrice = new JTextField();
        JTextField outPrice = new JTextField();

        //adds all fields which need to show on the popup dialog when clicking the Add button
        JComponent[] addInputs = new JComponent[]{
                new JLabel("ID (input number | eg: 123"), id,
                new JLabel("Name (input character | eg. apple"), name,
                new JLabel("Count (input number | eg: 123)"), count,
                new JLabel("Position (input character | eg. A123)"), position,
                new JLabel("In-price (input number | eg: 123)"), inPrice,
                new JLabel("Out-price (input number | eg: 123)"), outPrice
        };

        //creates the popup dialog for the Add button
        int addPopDialog = JOptionPane.showOptionDialog(this, addInputs, "Add Item",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, addIcon, null, null);
        if (addPopDialog == JOptionPane.OK_OPTION) {
            //get inputs from the text fields
            int getIDInput = Integer.parseInt(id.getText());
            String getNameInput = name.getText();
            int getCountInput = Integer.parseInt(count.getText());
            String getPositionInput = position.getText();
            double getInPriceInput = Double.parseDouble(inPrice.getText());
            double getOutPriceInput = Double.parseDouble(outPrice.getText());
            // creates new item and adds item to Item List
            Item item = new Item(getIDInput, getNameInput, getCountInput,
                    getPositionInput, getInPriceInput, getOutPriceInput);
            itemList.addItemToList(item);
        }
    }

    /**
     * creates functionality for "Show List" button ===================
     */

    //MODIFIES: this
    //EFFECTS: creates functionality for the Show button
    private void doShowList() {
        //parses the list of items in string type to the list of JLabel
        ArrayList<JComponent> itemListComponent =
                parseStringListToLabelList(parseConstructedStringList(itemList.getList()));
        JComponent[] itemStringsToComponents = itemListComponent.toArray(new JComponent[0]);

        //creates the popup dialog for the Show button
        JOptionPane.showMessageDialog(this, itemStringsToComponents,
                "All Of Items", JOptionPane.PLAIN_MESSAGE, showIcon);
    }

    /**
     * creates functionality for "Sort" button ==========================
     */

    //MODIFIES: this
    //EFFECTS: creates functionality for the Sort button
    private void doSortUnderCount() {
        //parses the list of items to the list of strings
        ArrayList<String> itemListString = parseConstructedStringList(this.itemList.getList());

        JPanel listLabels = new JPanel();
        //creates list of labels from list of strings
        for (String str : itemListString) {
            JLabel j1 = new JLabel(str);
            listLabels.setLayout(new GridLayout(0, 1));
            listLabels.add(j1);
        }


        JTextField underCountInput = new JTextField();
        JLabel message = new JLabel("All items");

        JComponent[] fielsForPopUpDialog = new JComponent[]{message, listLabels,
                new JLabel("Filter all items under this count"), underCountInput
        };

        //creates the popup dialog for the Sort button
        if (itemList.getList().isEmpty()) {
            JLabel sortNoItemNotification = new JLabel("You currently have no item!");
            JOptionPane.showMessageDialog(this, sortNoItemNotification,
                    "Sort no item notification", JOptionPane.PLAIN_MESSAGE, sortIcon);
        } else {
            int sortPopDialog = JOptionPane.showOptionDialog(this, fielsForPopUpDialog, "Add Item",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, sortIcon, null, null);
            int getCountInput = Integer.parseInt(underCountInput.getText());
            sorting(sortPopDialog, getCountInput);
        }
    }

    //MODIFIES: this
    //EFFECTS: helper method for Sorting
    private void sorting(int sortPopDialog, int count) {
        if (sortPopDialog == JOptionPane.OK_OPTION) {
            //parses the list of SORTED items to the list of strings
            ArrayList<String> listItemString = parseConstructedStringList(itemList.listItemUnderCount(count));
            if (listItemString.isEmpty()) {
                JLabel messageEmptyList = new JLabel("No item under expected count!");
                JOptionPane.showMessageDialog(this, messageEmptyList,
                        "No items notification", JOptionPane.PLAIN_MESSAGE, sortIcon);
            } else {
                ArrayList<JComponent> itemListComponentUnderCount = parseStringListToLabelList(listItemString);
                JComponent[] itemStringsToComponents = itemListComponentUnderCount.toArray(new JComponent[0]);

                JOptionPane.showMessageDialog(this, itemStringsToComponents,
                        "All items under the count", JOptionPane.PLAIN_MESSAGE, sortIcon);
            }
        }
    }

    /**
     * creates functionality for "Delete" buttons =============================
     */
    //MODIFIES: this
    //EFFECTS: creates functionality for the Delete button
    private void doDelete() {
        //parses the list of items to the list of strings
        ArrayList<String> itemListComponent = parseConstructedStringList(itemList.getList());

        JPanel listLabels = new JPanel();

        //creates list of labels from list of strings
        for (String str : itemListComponent) {
            JLabel j1 = new JLabel(str);
            listLabels.setLayout(new GridLayout(0, 1));
            listLabels.add(j1);
        }

        JTextField deleteItemOrder = new JTextField();
        JLabel message = new JLabel("All items");

        JComponent[] deleteInputs = new JComponent[]{message, listLabels,
                new JLabel("Please input the item order you want to delete"), deleteItemOrder
        };
        if (itemList.getList().isEmpty()) {
            JLabel sortNoItemNotification = new JLabel("You currently have no item!");
            JOptionPane.showMessageDialog(this, sortNoItemNotification,
                    "Delete no item notification", JOptionPane.PLAIN_MESSAGE, deleteIcon);
        } else {
            int deletePopDialog = JOptionPane.showOptionDialog(this, deleteInputs, "Delete Item",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, deleteIcon, null, null);
            int getDeleteOrderInput = Integer.parseInt(deleteItemOrder.getText());

            deleting(deletePopDialog, getDeleteOrderInput);
        }
    }

    //MODIFIES: this
    //EFFECTS: helper method for Deleting
    private void deleting(int deletePopDialog, int itemOrder) {
        if (deletePopDialog == JOptionPane.OK_OPTION) {
            if (itemOrder >= 1 && itemOrder <= itemIndex) {
                ArrayList<String> listItemString =
                        parseConstructedStringList(itemList.listItemDeletedItem(itemOrder - 1));
                ArrayList<JComponent> itemListComponentUnderCount = parseStringListToLabelList(listItemString);
                JComponent[] itemStringsToComponents = itemListComponentUnderCount.toArray(new JComponent[0]);

                JOptionPane.showMessageDialog(this, itemStringsToComponents,
                        "All Items", JOptionPane.PLAIN_MESSAGE, deleteIcon);
            } else {
                JLabel messageNoOrder = new JLabel("No item has that order!");
                JOptionPane.showMessageDialog(this, messageNoOrder,
                        "Input invalid item order notification", JOptionPane.PLAIN_MESSAGE, deleteIcon);
            }
        }

    }

    /**
     * creates functionality for "Edit" buttons =============================
     */
    //MODIFIES: this
    //EFFECTS: creates functionality for the Edit button
    private void doEdit() {
        //parses the list of items to the list of strings
        ArrayList<String> itemListComponent = parseConstructedStringList(itemList.getList());

        JPanel listLabels = new JPanel();

        //creates list of labels from list of strings
        for (String str : itemListComponent) {
            JLabel j1 = new JLabel(str);
            listLabels.setLayout(new GridLayout(0, 1));
            listLabels.add(j1);
        }

        JTextField editItemOrder = new JTextField();
        JLabel message = new JLabel("All items");

        JComponent[] editInputs = new JComponent[]{message, listLabels,
                new JLabel("Please input the item order you want to edit"), editItemOrder
        };
        if (itemList.getList().isEmpty()) {
            JLabel sortNoItemNotification = new JLabel("You currently have no item!");
            JOptionPane.showMessageDialog(this, sortNoItemNotification,
                    "Edit no item notification", JOptionPane.PLAIN_MESSAGE, editIcon);
        } else {
            int editPopDialog = JOptionPane.showOptionDialog(this, editInputs, "Edit Item",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, editIcon, null, null);
//            int getEditOrderInput = Integer.parseInt(editItemOrder.getText());
            getEditOrderInput = Integer.parseInt(editItemOrder.getText());


            editing(editPopDialog, getEditOrderInput);
        }
    }

    //MODIFIES: this
    //EFFECTS: helper method for Editing
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void editing(int editPopDialog, int itemOrder) {
        if (editPopDialog == JOptionPane.OK_OPTION) {
            if (itemOrder >= 1 && itemOrder <= itemIndex) {
                Item getItemWithOrder = itemList.getItemFromList(itemOrder - 1);

                //creates current Text field for item's
                JTextField id = new JTextField();
                id.setText(Integer.toString(getItemWithOrder.getItemID()));
                JTextField name = new JTextField();
                name.setText(getItemWithOrder.getItemName());
                JTextField count = new JTextField();
                count.setText(Integer.toString(getItemWithOrder.getItemCount()));
                JTextField position = new JTextField();
                position.setText(getItemWithOrder.getItemPosition());
                JTextField inPrice = new JTextField();
                inPrice.setText(Double.toString(getItemWithOrder.getItemInPrice()));
                JTextField outPrice = new JTextField();
                outPrice.setText(Double.toString(getItemWithOrder.getItemOutPrice()));


                //adds all fields which need to show on the popup dialog when clicking the Edit button
                JComponent[] addInputs = new JComponent[]{
                        new JLabel(("Input the new information to the item's you want to edit")),
                        new JLabel("ID (input number | eg: 123"), id,
                        new JLabel("Name (input character | eg. apple"), name,
                        new JLabel("Count (input number | eg: 123)"), count,
                        new JLabel("Position (input character | eg. A123)"), position,
                        new JLabel("In-price (input number | eg: 123)"), inPrice,
                        new JLabel("Out-price (input number | eg: 123)"), outPrice
                };

                int specificItemEditingPopDialog = JOptionPane.showOptionDialog(this, addInputs,
                        "Add Item", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        editIcon, null, null);

                //gets the new item's data after editing
                if (specificItemEditingPopDialog == JOptionPane.OK_OPTION) {
                    //gets inputs from the text fields
                    int getIDInput = Integer.parseInt(id.getText());
                    String getNameInput = name.getText();
                    int getCountInput = Integer.parseInt(count.getText());
                    String getPositionInput = position.getText();
                    double getInPriceInput = Double.parseDouble(inPrice.getText());
                    double getOutPriceInput = Double.parseDouble(outPrice.getText());


                    itemList.replaceNewInput(itemOrder, getIDInput, getNameInput, getCountInput, getPositionInput,
                            getInPriceInput, getOutPriceInput);

                    doShowList();

                } else {
                    JLabel messageNoOrder = new JLabel("No item has that order!");
                    JOptionPane.showMessageDialog(this, messageNoOrder,
                            "Input invalid item order notification", JOptionPane.PLAIN_MESSAGE, editIcon);
                }
            }
        }
    }


    /**
     * creates functionality for "Exit" buttons =============================
     */

    //MODIFIES: this
    //EFFECTS: creates functionality for the Exit button
    private void doExit() {
        Object[] options = {"Yes", "No", "Cancel"};

        int quitPopDialog = JOptionPane.showOptionDialog(this,
                "Would you like to save?", "Confirm Exit", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, quitIcon, options, options[2]);

        if (quitPopDialog == 0) {
            doSave();
            new PrintLogAction();
            this.dispose();
        } else if (quitPopDialog == 1) {
            new PrintLogAction();
            this.dispose();
        }
    }

    /**
     * creates functionality for "Save" & "Load" buttons =============================
     */

    //MODIFIES: this
    //EFFECTS: creates functionality for the Load button
    private void doLoad() {
        try {
            itemList = jsonReader.read();
            System.out.println("Loaded items in " + itemList.getNameOfList() + "from " + jsonStoreAddress);


            Icon loadIcon = new ImageIcon("./data/icons/loadIcon.png");
            JLabel saveNotification = new JLabel("Loaded successfully!");
            JOptionPane.showMessageDialog(this, saveNotification,
                    "Load Notification", JOptionPane.PLAIN_MESSAGE, loadIcon);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + jsonStoreAddress);
        }

        //refresh the frame
        container.revalidate();
        container.repaint();
    }


    //MODIFIES: this
    //EFFECTS: creates functionality for the Save button
    private void doSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(itemList);
            jsonWriter.close();


            Icon saveIcon = new ImageIcon("./data/icons/saveIcon.png");
            JLabel saveNotification = new JLabel("Saved successfully!");
            JOptionPane.showMessageDialog(this, saveNotification,
                    "Save Notification", JOptionPane.PLAIN_MESSAGE, saveIcon);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read from file: " + jsonStoreAddress);
        }
    }
}


