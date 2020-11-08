/* 
 * Chase Conaway
 * CptS 233: Programming Assignment: TreeVisualization
 * Date November 9th, 2020
 * git repo url: https://github.com/Chase1242/RedBlackTreeVisualization.git
 * Takes a red black tree implementation taken from algorithmtutorprograms and makes a visualization of what
 * is happening in the tree itself.
 * git repo url for RedBlackImplementation: https://github.com/Bibeknam/algorithmtutorprograms.git
 */

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class TreeVisualization extends JPanel implements ActionListener {
	
	public static String insertNum;
	public static JTextField insertBox;
	public static String deleteNum;
	public static JTextField deleteBox;
	public static String searchNum;
	public static JTextField searchBox;
	
	public static JButton insert;
	public static JButton delete;
	public static JButton search;
	
	public static JLabel error;
	public static JLabel instructs;
	
	public static JPanel newPane;
	
	public static RedBlackTree tree2;
	
	public static boolean searchBool;
	
	public static Node nodeFound;
	
	public static final int DIAMETER = 100;
	
	// primes the JPanel for insertion into the frame as well as all the components of the screen.
	public TreeVisualization() {
		tree2 = new RedBlackTree();
		
		searchBool = false;
		
		insertNum = "";
		insertBox = new JTextField(12);
		insertBox.addActionListener(this);
		
		deleteNum = "";
		deleteBox = new JTextField(12);
		deleteBox.addActionListener(this);
		
		searchNum = "";
		searchBox = new JTextField(12);
		searchBox.addActionListener(this);
		
		error = new JLabel();
		error.setVisible(false);
		error.setLocation(new Point(600, 600));
		
		instructs = new JLabel();
	    instructs.setText("To use this application, insert the integer to the left of the "
	    		+ "function you want to use and then press the button. The tree will adjust"
	    		+ " accordingly");
	    instructs.setVisible(true);
		
		insert = new JButton("Insert");
		delete = new JButton("Delete");
		search = new JButton("Search");
		
		insert.addActionListener(this);
		delete.addActionListener(this);
		search.addActionListener(this);
		
		JPanel panel = new JPanel();
		newPane = new JPanel();
		newPane.setSize(200, 200);
		newPane.setLocation(900, 900);
		newPane.setBackground(Color.white);
		newPane.add(instructs);
		
		panel.add(insert);
		panel.add(insertBox);
		panel.add(delete);
		panel.add(deleteBox);
		panel.add(search);
		panel.add(searchBox);
		panel.add(error);
		
		this.add(newPane);
		
		this.add(panel);
	}
	
	// Overrides the paintComponent method from JPanel to draw on the JPanel
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(153,24,46));
	    g.fillRect(0, 0, getWidth(), getHeight());
	    if (!searchBool) {
	    	makeTree(g, (getWidth() / 2) - 50, (getHeight() / 2) - 400);
	    } else {
	    	search2(g, (getWidth() / 2) - 50, (getHeight() / 2) - 400, nodeFound.data);
	    	
	    }
	  }

	// Sets up the frame itself.
	public static void main(String args[]) {
		JFrame frame = new JFrame("TreeVis");
	    frame.setSize(1800, 1800);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    TreeVisualization tree = new TreeVisualization();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(tree);
		frame.setVisible(true);
	  }
	
	// Overrides the actionPerformed function from actionListener to provide the functionality
	// screen
	@Override
	public void actionPerformed(ActionEvent e) {
		// Auto-generated method stub
		String s = e.getActionCommand();

		if (s.toLowerCase().equals("insert")) {
			insertNum = insertBox.getText();
			int num = 0;
			try {
				num = Integer.parseInt(insertNum);
				error.setVisible(false);
				tree2.insert(num);
				insertBox.setText("");
				searchBool = false;
			} catch (NumberFormatException v) {
				error.setText("Integer input is needed");
				error.setVisible(true);
			}
		}
		
		// TODO: add delete functionality
		if (s.toLowerCase().equals("delete")) {
			deleteNum = deleteBox.getText();
			int num = 0;
			try {
				num = Integer.parseInt(deleteNum);
				error.setVisible(false);
				tree2.deleteNode(num);
				deleteBox.setText("");
				searchBool = false;
			} catch (NumberFormatException v) {
				error.setText("Integer input is needed");
				error.setVisible(true);
			}
		}
		// TODO: add search functionality (I will do this)
		if (s.toLowerCase().equals("search")) {
			searchBool = true;
			searchNum = searchBox.getText();
			int num = 0;
			try {
				num = Integer.parseInt(searchNum);
				error.setVisible(false);
				nodeFound = tree2.searchTree(num);
				searchBox.setText("");
			} catch (NumberFormatException v) {
				error.setText("Integer input is needed");
				error.setVisible(true);
			}
		}
		newPane.setVisible(false);
		repaint();
	}
	
	// Draws a circle on the JPanel
	public void makeCircle(Graphics g, Color color, int x, int y, String str) {
		 g.setColor(color);
		 g.fillOval(x, y, DIAMETER, DIAMETER);
		 g.setColor(Color.white);
		 g.drawString(str, x + 45, y + 45);
	}
	
	//Makes the whole tree on the JPanel
	public void makeTree(Graphics g, int startX, int startY) {
		makeTreeHelper(g, startX, startY, tree2.getRoot(), 400);
	}
	
	//The recursive function using inOrder traversal to print the whole tree
	private void makeTreeHelper(Graphics g, int x, int y, Node tree, int width) {
		if (!(tree == null)) {
			Integer num = (Integer)tree.data;
			String str2 = num.toString();
			
			if (tree.color == 1) {
				makeCircle(g, Color.red, x, y, str2);
			} else {
				makeCircle(g, Color.black, x, y, str2);
			}
			
			makeTreeHelper(g, x - width, y + 100, tree.left, width / 3);
			makeTreeHelper(g, x + width, y + 100, tree.right, width / 3);
		}
	}
	
	// Utilized to search the tree
	public void search2(Graphics g, int startX, int startY, int data) {
		searchHelper(g, startX, startY, tree2.getRoot(), data, 400);
		error.setText("If all the leaf nodes with value 0 turn green, your value was not found"
				+ " and if your value is found, the correct node will light up green");
		error.setVisible(true);
		
	}
	// Uses recursion to do what the makeTree function does, but when it finds the specified node
	// it turns it green.
	private void searchHelper(Graphics g, int x, int y, Node tree, int data, 
							  int width) {
		if (!(tree == null)) {
			Integer num = (Integer)tree.data;
			String str2 = num.toString();
			if (tree.color == 1) {
				if (tree.data != data) {
					makeCircle(g, Color.red, x, y, str2);
				} else {
					makeCircle(g, Color.green, x, y, str2);
				}
			} else {
				if (tree.data != data) {
					makeCircle(g, Color.black, x, y, str2);
				} else {
					makeCircle(g, Color.green, x, y, str2);
				}
			}
			searchHelper(g, x - width, y + 100, tree.left, data, width / 3);
			searchHelper(g, x + width, y + 100, tree.right, data, width / 3);
		}
	}
		
}
