import java.awt.event.*; 
import javax.swing.*; 
import java.awt.*;

public class TreeVisualization extends JFrame implements ActionListener {
	// create the frame
	public static JFrame frame;
	// create the insert text box
	public static JTextField insertBox;
	// store the insert value
	public String insertNum;
	// make color
	public static final Color GRAY = Color.gray;
	
	public TreeVisualization() {
		insertNum = "";
	}
	
	public static void main(String args[]) {
		frame = new JFrame("Tree");
		try { 
			// set look and feel 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		} 
		catch (Exception e) { 
			System.err.println(e.getMessage()); 
		} 
		
		// create a treevisualization object
		TreeVisualization tree = new TreeVisualization();
		
		// create the insert text box and set it to uneditable
		insertBox = new JTextField(16);
		insertBox.setEditable(true);
		
		// creat insert and delete buttons
		JButton insert, delete;
		// create insert and delete buttons
		insert = new JButton("Insert");
		delete = new JButton("Delete");
		
		// create the panel
		JPanel panel = new JPanel();
		
		// add the listeners
		insert.addActionListener(tree);
		delete.addActionListener(tree);
		
		// add buttons to panel
		panel.add(insert);
		panel.add(delete);
		panel.add(insertBox);
		
		// set bg color
		panel.setBackground(GRAY);
		
		//add panel to frame
		frame.add(panel);
		
		// set size of frame
		frame.setSize(600, 600);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		
		if (s.toLowerCase().equals("insert")) {
			insertBox.setText("gottem");
		}
	}
}
