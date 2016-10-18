import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
  
public class AddressBookGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 250;
  
    private JTextField nameTextField, addressTextField, phoneTextField;
    public AddressBook a;
    
	public AddressBookGUI() {
		setSize(WIDTH,HEIGHT);
		setTitle("Address Book");
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.RED);
        contentPane.setLayout(new GridLayout(8,2));
  
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        JMenuItem newItem = new JMenuItem("Create AddressBook", 'C');
        newItem.addActionListener(this);
        fileMenu.add(newItem);
        
        JMenuItem saveItem = new JMenuItem("Save AddressBook", 'S');
        saveItem.addActionListener(this);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        
        JMenuItem addItem = new JMenuItem("Add BuddyInfo", 'A');	
        addItem.addActionListener(this);
        fileMenu.add(addItem);
        
        JMenuItem removeItem = new JMenuItem("Remove BuddyInfo", 'R');	//NEW
        removeItem.addActionListener(this);
        fileMenu.add(removeItem);
        
        JMenuItem editItem = new JMenuItem("Edit BuddyInfo", 'E');	//NEW
        editItem.addActionListener(this);
        fileMenu.add(newItem);
        
        JMenuItem displayItem = new JMenuItem("Display BuddyInfos", 'D');
        displayItem.addActionListener(this);
        fileMenu.add(displayItem);
        fileMenu.addSeparator();
       
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
 
    public void actionPerformed(ActionEvent e) {
    	String actionCommand = e.getActionCommand();
  
    	if(actionCommand.equals("Create AddressBook"))
    	{
    		a = new AddressBook();
    	}
    	else if(actionCommand.equals("Save AddressBook"))
    	{
    		String s = "";
    		
    		BufferedWriter out;
			try {
				out = new BufferedWriter(new FileWriter("myFile.txt"));
				for (Buddyinfo b : a.bl) {
	    			s += b.toString();
	    			out.write(s);
	    			out.write("\n");
	    		}	
	    		out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    	}
    	else if(actionCommand.equals("Display BuddyInfos"))
    	{
    		for (Buddyinfo b : a.bl) {
    			System.out.println(b.toString());
    		}
    	}
    	else if(actionCommand.equals("Add BuddyInfo"))
    	{
    		Container contentPane = getContentPane();
    		JLabel nameLabel = new JLabel("Name: ");
            contentPane.add(nameLabel);
            nameTextField = new JTextField(25);
            contentPane.add(nameTextField);
      
            JLabel addressLabel = new JLabel("Address:  ");
            contentPane.add(addressLabel);
            addressTextField = new JTextField(25);
            contentPane.add(addressTextField);
      
            JLabel phoneLabel = new JLabel("Phone number: ");
            contentPane.add(phoneLabel);
            phoneTextField = new JTextField(25);
            contentPane.add(phoneTextField);
      
            JButton enterButton = new JButton("Enter BuddyInfo");
            enterButton.addActionListener(this);
            contentPane.add(enterButton);
            
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(this);
            contentPane.add(exitButton);
            
            this.invalidate();
            this.validate();
            this.repaint();
    	}
    	else if (actionCommand.equals("Remove BuddyInfo")) {
    		Container contentPane = getContentPane();
    		
    		/*String[] allBuddies = a.getAllBuddies();
    		JList removableBuddies = new JList(allBuddies);
    		removableBuddies.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    		removableBuddies.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    		contentPane.add(removableBuddies);
    		
    		JScrollPane listScroller = new JScrollPane(removableBuddies);
    		listScroller.setPreferredSize(new Dimension(3, 2));
    		*/
    		JLabel nameLabel = new JLabel("Name: ");
            contentPane.add(nameLabel);
            nameTextField = new JTextField(25);
            contentPane.add(nameTextField);
      
            JButton enterButton = new JButton("Remove");
            enterButton.addActionListener(this);
            contentPane.add(enterButton);
            
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(this);
            contentPane.add(exitButton);
            
            this.invalidate();
            this.validate();
            this.repaint();
    	}
    	else if(actionCommand.equals("Enter BuddyInfo"))
    	{
    		Buddyinfo b = new Buddyinfo(nameTextField.getText(), addressTextField.getText(), Integer.parseInt(phoneTextField.getText()) );
    		String display = "Added: " + b.toString();
    		
    		nameTextField.setText("");
    		addressTextField.setText("");
    		phoneTextField.setText("");
  
    		a.addBuddy(b);
    		JOptionPane.showMessageDialog(null, display);
    	}
    	else if(actionCommand.equals("Remove"))
    	{
    		System.out.println(nameTextField.getText());
    		Buddyinfo removed = a.getBuddy(nameTextField.getText());
    		if (removed == null) System.out.println("YAASSSSS");
    		String display = "Removed: " + removed.toString();
    		
    		nameTextField.setText("");
  
    		a.removeBuddy(removed);
    		JOptionPane.showMessageDialog(null, display);
    	}
    	else if (actionCommand.equals("Exit")) System.exit(0);
    	else System.out.println("t");
    }
  
    public static void main(String[] args) {
    	AddressBookGUI gui = new AddressBookGUI();
    	gui.setVisible(true);
    }
}