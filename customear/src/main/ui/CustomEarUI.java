package ui;

import model.Earrings;
import model.Event;
import model.EventLog;
import model.Order;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents application's main window frame. Based on AlarmSystem + Workspace project (JSON serialization demo)
 * from CPSC 210. I learned everything about the GUI from the
 * Java Oracle site: https://docs.oracle.com/javase/tutorial/uiswing/components
 * however no code was copy or pasted from this source
 */
public class CustomEarUI {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static final String JSON_STORE = "./data/order.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Earrings earrings;
    private Order order;
    private JFrame mainDashboard;
    private JPanel headingPanel;
    private JPanel buttonPanel;
    private JPanel customerInfoPanel;
    private JLabel logoLabel;
    private ImageIcon logo;
    private ImageIcon fillerImage;
    private Font headingFont;
    private Font headingFontNonBold;
    private JButton addButton;
    private JButton saveButton;
    private JButton viewButton;
    private JFrame loadOption;
    private JTextField nameInput;
    private JTextField phoneNumInput;
    private JFrame newOrderFrame;
    private JComboBox charmList;
    private ButtonGroup metals;
    private JTextField quantityInput;
    private JRadioButton silver;
    private JRadioButton gold;
    private JFrame earringOrderFrame;
    private JList<String> earringList;
    private JFrame displayEarringFrame;
    private JPanel earringsPanel;


    // EFFECTS: constructs new CustomEarUI; initializes with new order and fonts
    public CustomEarUI() {
        order = new Order();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        headingPanel = new JPanel();
        headingFont = new Font("Roboto", Font.BOLD, 20);
        headingFontNonBold = new Font("Roboto", Font.PLAIN, 20);

        addLoadOption();


    }

    // EFFECTS: helper that creates a popup frame where user can choose to load from file or not
    public void addLoadOption() {
        JPanel yesNoPanel = new JPanel();
        JButton yesButton = new JButton(new LoadFileAction());
        JButton noButton = new JButton(new NewUserAction());
        yesNoPanel.setLayout(null);
        loadOption = new JFrame();
        loadOption.setSize(700, 400);
        loadOption.setLocationRelativeTo(null);  // centers the frame on the user's screen

        questionPanelHelper();


        buttonHelper(yesButton);
        yesButton.setFont(headingFont);
        yesButton.setBounds(100, 250, 200, 100);
        yesNoPanel.setBounds(100, 250, 200, 100);
        yesNoPanel.add(yesButton);

        buttonHelper(noButton);
        noButton.setFont(headingFont);
        noButton.setBounds(400, 250, 200, 100);
        yesNoPanel.setBounds(400, 250, 200, 100);
        yesNoPanel.add(noButton);

        loadOption.add(yesNoPanel);

        loadOption.setUndecorated(true);
        loadOption.setVisible(true);
        loadOption.setResizable(false);

    }

    // EFFECTS: helper that helps sets up the panel with question text for load option frame
    public void questionPanelHelper() {
        JPanel questionPanel = new JPanel();
        JLabel askLoad = new JLabel("Question");
        askLoad.setText("Would you like to load order from file?");
        askLoad.setFont(headingFont);
        askLoad.setBounds(100, 100, 500, 50);
        loadOption.add(questionPanel);

        questionPanel.add(askLoad);
        questionPanel.setBounds(100, 100, 500, 50);
    }

    // EFFECTS: helper that sets up the main dashboard
    public void mainDashboardHelper() {
        logo = new ImageIcon("logo.png");
        logoLabel = new JLabel(logo);

        mainDashboard = new JFrame();
        mainDashboard.setTitle("CustomEar: Main Dashboard");

        mainDashboard.setSize(WIDTH, HEIGHT);

        headingPanelHelper();
        customerInfoPanelHelper();
        buttonPanelHelper();

        addQuitCustomEar();

    }

    // EFFECTS: adds window listener to main dashboard; if user clicks close on the main dashboard,
    // this prints events in eventLog and quits the application
    public void addQuitCustomEar() {

        mainDashboard.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                EventLog eventLog = EventLog.getInstance();
                for (Event event : eventLog) {
                    System.out.println(event);
                }

                System.exit(0);

            }
        });

    }

    // EFFECTS: helper that sets up the heading panel of the main dashboard
    public void headingPanelHelper() {
        headingPanel = new JPanel();
        headingPanel.setLayout(null);
        JLabel welcomeText = new JLabel("Welcome");
        welcomeText.setText("Welcome to CustomEar");
        welcomeText.setFont(headingFont);
        welcomeText.setForeground(Color.white);
        welcomeText.setBounds(400, 25, 700, 50);
        logoLabel.setBounds(220, 10, 300, 84);

        headingPanel.add(logoLabel);
        headingPanel.add(welcomeText);
        headingPanel.setBounds(0, 0, WIDTH, 100);
        headingPanel.setBackground(Color.BLACK);
        headingPanel.setVisible(true);

        mainDashboard.add(headingPanel);
    }

    // EFFECTS: helps create a panel that displays the order's name and phone number
    public void customerInfoPanelHelper() {
        customerInfoPanel = new JPanel();
        customerInfoPanel.setBounds(0, 100, WIDTH, 100);
        customerInfoPanel.setBackground(Color.pink);

        JLabel name = new JLabel();
        name.setText("Name: " + this.order.getName());
        name.setForeground(Color.black);
        name.setFont(headingFontNonBold);
        name.setBorder(new EmptyBorder(30, 0, 0, 260));
        customerInfoPanel.add(name);

        JLabel phoneNum = new JLabel();
        phoneNum.setText("Phone Number: " + this.order.getPhoneNum());
        phoneNum.setForeground(Color.black);
        phoneNum.setFont(headingFontNonBold);
        phoneNum.setBorder(new EmptyBorder(30, 0, 0, 0));
        customerInfoPanel.add(phoneNum);

        mainDashboard.add(customerInfoPanel);
    }

    // EFFECTS: helps create the button panel for the main dashboard
    public void buttonPanelHelper() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        fillerImage = new ImageIcon("cute.png");
        JLabel fillerImageLabel = new JLabel(fillerImage); // I have this here as a filler for a button if I want
                                                           // to add another in the future

        fillerImageLabel.setBounds(550, 500, 300, 100);

        addButton = new JButton(new AddEarringAction());
        buttonHelper(addButton);
        addButton.setBounds(150, 300, 300, 100);

        viewButton = new JButton(new ViewAllEarringsAction());
        buttonHelper(viewButton);
        viewButton.setBounds(550, 300, 300, 100);

        saveButton = new JButton(new SaveOrderAction());
        buttonHelper(saveButton);
        saveButton.setBounds(150, 500, 300, 100);

        buttonPanel.setBounds(100, 600, 100, 500);

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(fillerImageLabel);

        mainDashboard.add(buttonPanel);
    }


    // MODIFIES: button
    // EFFECTS: styles given button to have black background, white font, and header font
    private void buttonHelper(JButton button) {
        button.setFont(headingFont);
        button.setForeground(Color.white);
        button.setBackground(Color.BLACK);
    }


    /**
     * Represents action to be taken when user wants to load an order
     * from file.
     */
    // MODIFIES: this
    // EFFECTS: loads order from file if there is a save file available; only available when user first opens app
    private class LoadFileAction extends AbstractAction {

        LoadFileAction() {
            super("Yes");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                order = jsonReader.read();
                JOptionPane.showMessageDialog(null,
                        "Welcome back, " + order.getName() + "! We loaded your order to " + JSON_STORE,
                        "Returning User", JOptionPane.INFORMATION_MESSAGE);
                mainDashboardHelper();
                mainDashboard.setResizable(false);
                mainDashboard.setVisible(true);
                loadOption.setVisible(false);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Oh no! We were unable to read from file: " + JSON_STORE,
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (JSONException e) {
                JOptionPane.showMessageDialog(null, "No load file detected! Creating new order..",
                        "Error", JOptionPane.ERROR_MESSAGE);
                loadOption.setVisible(false);
                newOrderMaker();
            }

        }
    }


    /**
     * Represents action to be taken when user wants to save an order
     * to file.
     */
    // MODIFIES: this
    // EFFECTS: saves the order to file
    private class SaveOrderAction extends AbstractAction {

        SaveOrderAction() {
            super("Save Order");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(order);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null,
                        "Hey, " + order.getName() + "! We saved your order to " + JSON_STORE,
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Oh no! We were unable to write to file: " + JSON_STORE,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Represents action to be taken when user does not load from file and needs to set their name and phone number
     */
    // MODIFIES: this
    // EFFECTS: creates new frame that allows user to enter their contact info
    private class NewUserAction extends AbstractAction {

        NewUserAction() {
            super("No");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            newOrderMaker();
        }
    }

    /**
     * Represents action to be taken when user adds an earring to their order
     */
    // MODIFIES: this
    // EFFECTS: creates new frame that lets user create new earring
    private class AddEarringAction extends AbstractAction {

        AddEarringAction() {
            super("Add Earring");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            newEarringMaker();
        }
    }

    /**
     * Represents action to be taken when user removes an earring from their order
     */
    // MODIFIES: this
    // EFFECTS: allows user to remove selected earring
    private class RemoveEarringAction extends AbstractAction {

        RemoveEarringAction() {
            super("Remove Selected Earring");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            removeEarring();
        }
    }


    /**
     * Represents action to be taken when user clicks on "View All Earrings"
     */
    // EFFECTS: allows user to view all earrings and remove selected earring
    private class ViewAllEarringsAction extends AbstractAction {

        ViewAllEarringsAction() {
            super("View All Earrings");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            displayEarringFrameMaker(displayEarrings());
        }
    }

    // EFFECTS: allows user to add an earring to the order
    public void newEarringMaker() {
        earringOrderFrame = new JFrame();
        JPanel doneButtonPanel = new JPanel();
        doneButtonPanel.setLayout(null);
        earringOrderFrame.setTitle("New Earring Maker");

        earringMakerTitleHelper(earringOrderFrame);
        metalTypePanelHelper(earringOrderFrame);
        charmTypePanelHelper(earringOrderFrame);
        quantityPanelHelper(earringOrderFrame);
        doneEarringPanelHelper(doneButtonPanel);
        earringOrderFrame.add(doneButtonPanel);

        earringOrderFrame.setSize(800,700);
        earringOrderFrame.setVisible(true);
        earringOrderFrame.setResizable(false);


    }

    // EFFECTS: sets up the heading of the earring maker frame
    public void earringMakerTitleHelper(JFrame earringOrderFrame) {
        JPanel earringMakerTitlePanel = new JPanel();
        earringMakerTitlePanel.setLayout(null);
        earringMakerTitlePanel.setBounds(100, 30, 800, 100);
        earringMakerTitlePanel.setPreferredSize(new Dimension(600, 100));

        JLabel headingTitle = new JLabel();
        headingTitle.setText("Earring Maker: Customize your earrings");
        headingTitle.setFont(headingFont);
        headingTitle.setBounds(100, 0, 800, 100);
        earringMakerTitlePanel.add(headingTitle);
        earringMakerTitlePanel.setBackground(Color.pink);

        earringOrderFrame.add(earringMakerTitlePanel);
    }

    // EFFECTS: sets up the panel containing the metal type selection
    public void metalTypePanelHelper(JFrame earringOrderFrame) {
        JPanel metalTypePanel = new JPanel();
        metalTypePanel.setBounds(60, 200, 600, 50);
        metalTypePanel.setSize(new Dimension(600, 50));

        JLabel headingTitle = new JLabel();
        headingTitle.setText("Metal Type: ");
        headingTitle.setFont(headingFont);
        headingTitle.setBounds(60, 200, 300, 50);
        metalTypePanel.add(headingTitle);

        silver = new JRadioButton("sterling silver - $10");
        silver.setFont(headingFontNonBold);
        silver.setSelected(true);
        gold = new JRadioButton("gold plated - $15");
        gold.setFont(headingFontNonBold);
        metals = new ButtonGroup();
        metals.add(silver);
        metals.add(gold);
        metalTypePanel.add(silver);
        metalTypePanel.add(gold);

        earringOrderFrame.add(metalTypePanel);
        metalTypePanel.setVisible(true);
    }

    // EFFECTS: sets up the panel containing the charm type selection
    public void charmTypePanelHelper(JFrame earringOrderFrame) {
        JPanel charmTypePanel = new JPanel();
        charmTypePanel.setBounds(60, 300, 600, 50);
        charmTypePanel.setPreferredSize(new Dimension(600, 50));

        JLabel headingTitle = new JLabel();
        headingTitle.setText("Charm Type: ");
        headingTitle.setFont(headingFont);
        charmTypePanel.add(headingTitle);


        String[] charmTypeChoices = {"strawberry", "cow", "mushroom"};
        charmList = new JComboBox(charmTypeChoices);
        charmList.setSelectedItem("strawberry");
        charmList.setFont(headingFontNonBold);
        charmTypePanel.add(charmList);

        earringOrderFrame.add(charmTypePanel);
    }

    // EFFECTS: sets up the panel containing the metal type selection
    public void quantityPanelHelper(JFrame earringOrderFrame) {
        JPanel quantityPanel = new JPanel();
        quantityPanel.setBounds(60, 400, 600, 50);
        quantityPanel.setSize(new Dimension(600, 50));

        JLabel headingTitle = new JLabel();
        headingTitle.setText("Quantity: ");
        headingTitle.setSize(300,50);
        headingTitle.setBounds(120, 400, 200, 50);
        headingTitle.setFont(headingFont);
        quantityPanel.add(headingTitle);

        quantityInput = new JTextField();
        quantityInput.setFont(headingFontNonBold);
        quantityInput.setPreferredSize(new Dimension(300, 50));
        quantityInput.setBounds(220, 400, 50, 50);
        quantityPanel.add(quantityInput);

        earringOrderFrame.add(quantityPanel);
        quantityPanel.setVisible(true);

    }

    // EFFECTS: helper that creates a panel when creating new earring with a button that says Done.
    public void doneEarringPanelHelper(JPanel doneButtonPanel) {
        JButton doneButton = new JButton("Done");
        buttonHelper(doneButton);
        doneButton.setBounds(250, 550, 300, 50);

        doneButtonPanel.setBounds(250, 550, 500, 50);
        doneButtonPanel.add(doneButton);

        doneEarringButtonActionListenerHelper(doneButton);

    }



    // EFFECTS: allows user to input their name and phone number for order.
    public void newOrderMaker() {
        loadOption.setVisible(false);
        newOrderFrame = new JFrame();
        JPanel infoPanel = new JPanel();
        JPanel contactInfoPanel = new JPanel();
        JPanel okayButtonPanel = new JPanel();
        newOrderFrame.setSize(700, 400);
        newOrderFrame.setLocationRelativeTo(null);  // centers the frame on the user's screen
        newOrderFrame.setResizable(false);
        newOrderFrame.setTitle("New Order Creation: Add Contact Info");


        JLabel infoText = new JLabel("Info");
        infoText.setText("Please enter the contact info for your order.");
        infoText.setForeground(Color.white);
        infoText.setFont(headingFont);
        infoText.setBounds(0, 20, 700, 50);
        infoText.setBorder(new EmptyBorder(10,0,0,0));
        infoPanel.setBounds(0, 30, 700, 50);
        infoPanel.add(infoText);
        infoPanel.setBackground(Color.BLACK);

        contactInfoPanelHelper(newOrderFrame);

        newOrderFrame.add(infoPanel);
        newOrderFrame.add(contactInfoPanel);
        newOrderFrame.add(okayButtonPanel);
        newOrderFrame.setVisible(true);

    }

    // EFFECTS: helps set up the contact info panel in frame
    public void contactInfoPanelHelper(JFrame newOrderFrame) {
        JPanel namePanel = new JPanel();
        JPanel phoneNumPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        phoneNumPanelHelper(phoneNumPanel);
        namePanelHelper(namePanel);
        doneButtonPanelHelper(buttonPanel);

        newOrderFrame.add(namePanel);
        newOrderFrame.add(phoneNumPanel);
        newOrderFrame.add(buttonPanel);


    }

    // EFFECTS: helps set up name panel
    public void namePanelHelper(JPanel namePanel) {
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setText("Name: ");
        nameLabel.setFont(headingFont);
        nameLabel.setBounds(0, 100, 200, 50);

        nameInput = new JTextField();
        nameInput.setBounds(0, 100, 400, 50);
        nameInput.setPreferredSize(new Dimension(300, 50));
        nameInput.setFont(headingFontNonBold);

        namePanel.add(nameLabel);
        namePanel.add(nameInput);
        namePanel.setBounds(40, 100, 700, 50);

    }

    // EFFECTS: helps set up phone number panel
    public void phoneNumPanelHelper(JPanel phoneNumPanel) {
        JLabel phoneNumLabel = new JLabel("PhoneNum");
        phoneNumLabel.setText("Phone Number: ");
        phoneNumLabel.setFont(headingFont);
        phoneNumLabel.setBounds(0, 150, 700, 50);

        phoneNumInput = new JTextField();
        phoneNumInput.setBounds(0, 150, 400, 50);
        phoneNumInput.setPreferredSize(new Dimension(300, 50));
        phoneNumInput.setFont(headingFontNonBold);

        phoneNumPanel.add(phoneNumLabel);
        phoneNumPanel.add(phoneNumInput);
        phoneNumPanel.setBounds(0, 200, 700, 50);
    }

    // EFFECTS: helper that creates a panel with a button that says Done
    public void doneButtonPanelHelper(JPanel buttonPanel) {
        JButton doneButton = new JButton("Done");
        buttonHelper(doneButton);
        doneButton.setBounds(0, 300, 300, 100);

        buttonPanel.setBounds(0, 300, 500, 100);
        buttonPanel.add(doneButton);
        buttonPanel.setBorder(new EmptyBorder(0, 220, 0, 0));

        doneButtonActionListenerHelper(doneButton);

    }

    // MODIFIES: this
    // EFFECTS: helper for remove earring menu selection in remove earring frame. Removes selected earring.
    public void removeEarring() {
        int selectedEarringItemIndex = earringList.getSelectedIndex();

        if (selectedEarringItemIndex == -1) {
            JOptionPane.showMessageDialog(null,
                    "Please select an earring to be removed!",
                    "No earring selected", JOptionPane.ERROR_MESSAGE);
        } else {
            order.removeEarrings(selectedEarringItemIndex);
            JOptionPane.showMessageDialog(null,
                    "Earring successfully removed!",
                    "Removed successfully", JOptionPane.INFORMATION_MESSAGE);
            displayEarringFrame.setVisible(false);
        }
    }




    // MODIFIES: this
    // EFFECTS: action listener for done button. updates order information if valid information is entered.
    public void doneButtonActionListenerHelper(JButton doneButton) {
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String givenName = nameInput.getText();
                String givenPhoneNum = phoneNumInput.getText();

                if (givenName.equals("") && givenPhoneNum.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter your name and phone number!",
                            "No name detected", JOptionPane.ERROR_MESSAGE);

                } else if (givenName.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter your name!",
                            "No name detected", JOptionPane.ERROR_MESSAGE);
                } else if (givenPhoneNum.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter your phone number!",
                            "No phone number detected", JOptionPane.ERROR_MESSAGE);
                } else {
                    isValidPhoneNumber(givenPhoneNum, givenName);
                }

            }

        });
    }

    // MODIFIES: this
    // EFFECTS: action listener for remove button in remove earring frame. Removes selected earring.
    public void removeButtonActionListenerHelper(JButton removeButton) {
        removeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedEarringItemIndex = earringList.getSelectedIndex();

                if (selectedEarringItemIndex == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Please select an earring to be removed!",
                            "No earring selected", JOptionPane.ERROR_MESSAGE);
                } else {
                    order.removeEarrings(selectedEarringItemIndex);
                    JOptionPane.showMessageDialog(null,
                            "Earring successfully removed!",
                            "Removed successfully", JOptionPane.INFORMATION_MESSAGE);
                    displayEarringFrame.setVisible(false);
                }


            }

        });
    }

    // MODIFIES: this
    // EFFECTS: action listener for done button in earring maker. Adds new earring if quantity is valid,
    public void doneEarringButtonActionListenerHelper(JButton doneButton) {
        doneButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String givenQuantity = quantityInput.getText();
                int selection = 0;

                try {
                    Integer.parseInt(givenQuantity);
                    selection = Integer.parseInt(givenQuantity);
                    if (selection <= 0) {
                        JOptionPane.showMessageDialog(null,
                                "Quantity must be greater than 0!",
                                "Invalid quantity", JOptionPane.ERROR_MESSAGE);
                    } else {

                        addNewEarring(selection);

                    }
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(null,
                            "This is not a valid quantity!",
                            "Invalid quantity", JOptionPane.ERROR_MESSAGE);
                }

            }

        });
    }

    // MODIFIES: this
    // EFFECTS: checks if the phone number entered is valid; if valid, set order info to be user input
    public void isValidPhoneNumber(String givenPhoneNum, String givenName) {
        long selectedPhoneNum;

        try {
            Long.parseLong(givenPhoneNum);
            selectedPhoneNum = Long.parseLong(givenPhoneNum);
            if (selectedPhoneNum < 0) {
                JOptionPane.showMessageDialog(null,
                        "This is not a valid phone number! Please try again!",
                        "Invalid phone number", JOptionPane.ERROR_MESSAGE);
            } else {
                order.setPhoneNum(selectedPhoneNum);
                order.setName(givenName);
                JOptionPane.showMessageDialog(null, "New order has been created!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                newOrderFrame.setVisible(false);
                mainDashboardHelper();
                mainDashboard.setResizable(false);
                mainDashboard.setVisible(true);
            }

        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null,
                    "This is not a valid phone number! Please try again!",
                    "Invalid phone number", JOptionPane.ERROR_MESSAGE);
        }
    }



    // MODIFIES: this
    // EFFECTS: creates and adds a new custom earring to order
    public void addNewEarring(int selection) {
        earrings = new Earrings();
        String givenCharmType = charmList.getSelectedItem().toString();
        earrings.setQuantity(selection);

        if (silver.isSelected()) {
            earrings.setMetalType("sterling silver");
        } else if (gold.isSelected()) {
            earrings.setMetalType("gold plated");
        }
        earrings.setCharmType(givenCharmType);
        earrings.setQuantity(selection);
        earrings.calculatePrice(earrings.getQuantity());
        order.addEarrings(earrings);
        JOptionPane.showMessageDialog(null,
                "Earring successfully added to your order! Price: $" + earrings.getPrice(),
                "Successful Add", JOptionPane.INFORMATION_MESSAGE);
        earringOrderFrame.setVisible(false);
    }

    // EFFECTS: returns all the earrings in the order list into scrollable pane
    public JPanel displayEarrings() {
        earringsPanel = new JPanel();

        earringsPanel.setBounds(20, 30, 1000, 300);
        earringsPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        earringsPanel.add(earringScrollPaneMaker());
        return earringsPanel;

    }


    // EFFECTS: creates a frame for displaying all earrings in order that can be removed
    public void displayEarringFrameMaker(JPanel earringsPanel) {
        displayEarringFrame = new JFrame();
        displayEarringFrame.setResizable(false);
        displayEarringFrame.setTitle("My Order: View Earrings");
        displayEarringFrame.setSize(1000, 600);
        displayEarringFrame.add(earringsPanel);

        JPanel removeButtonPanel = new JPanel();
        JButton removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(200, 50));
        buttonHelper(removeButton);
        removeButtonPanel.setBounds(200, 400, 200, 50);
        removeButtonPanel.setBorder(new EmptyBorder(400, 0, 0, 0));

        removeButtonPanel.add(removeButton);
        displayEarringFrame.add(removeButtonPanel);
        displayEarringFrame.setVisible(true);
        addMenu();
        removeButtonActionListenerHelper(removeButton);

    }


    // EFFECTS: makes an earring scroll pane with information of all earrings in the order
    public JScrollPane earringScrollPaneMaker() {
        DefaultListModel earringsListModel = new DefaultListModel();
        earringList = new JList<>(earringsListModel);
        earringList.setFont(headingFontNonBold);
        ArrayList<Earrings> allEarrings = order.getOrder();

        for (Earrings e : allEarrings) {
            earringsListModel.addElement("Item Number: " + (order.getOrder().indexOf(e) + 1)
                    + " " + " Metal Type: " + e.getMetalType() + " " + " Charm Type: " + e.getCharmType()
                    + " " + " Quantity: " + e.getQuantity() + " " + " Price: " + "$" + e.getPrice());
        }

        JScrollPane earringScrollPane = new JScrollPane(earringList);
        return earringScrollPane;
    }


    /**
     * Adds menu bar.
     */
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu codeMenu = new JMenu("Remove");
        addMenuItem(codeMenu, new RemoveEarringAction());
        menuBar.add(codeMenu);

        displayEarringFrame.setJMenuBar(menuBar);
    }

    /**
     * EFFECTS: Adds an item with given handler to the given menu
     * @param theMenu  menu to which new item is added
     * @param action   handler for new menu item
     */
    private void addMenuItem(JMenu theMenu, AbstractAction action) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        theMenu.add(menuItem);
    }


    // starts the application
    public static void main(String[] args) {
        new CustomEarUI();

    }
}
