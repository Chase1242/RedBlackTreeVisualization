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
	
	public static RedBlackTree tree2;
	
	public TreeVisualization() {
		tree2 = new RedBlackTree();
		
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
		error.setText("Integer input is needed");
		error.setLocation(new Point(600, 600));
		
		insert = new JButton("Insert");
		delete = new JButton("Delete");
		search = new JButton("Search");
		
		insert.addActionListener(this);
		delete.addActionListener(this);
		search.addActionListener(this);
		
		JPanel panel = new JPanel();
		
		panel.add(insert);
		panel.add(insertBox);
		panel.add(delete);
		panel.add(deleteBox);
		panel.add(search);
		panel.add(searchBox);
		panel.add(error);
		
		this.add(panel);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(153,24,46));
	    g.fillRect(0, 0, getWidth(), getHeight());
	    makeTree(g, 550, 400);
	  }

	  public static void main(String args[]) {
	    JFrame frame = new JFrame("TreeVis");
	    frame.setSize(1200, 1200);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    TreeVisualization tree = new TreeVisualization();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(tree);
		frame.setVisible(true);
	  }

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
			} catch (NumberFormatException v) {
				error.setVisible(true);
			}
		}
		repaint();
	}
	
	public void makeCircle(Graphics g, Color color, int x, int y, String str) {
		 g.setColor(color);
		 g.fillOval(x, y, 100, 100);
		 g.setColor(Color.white);
		 g.drawString(str, x + 50, y + 50);
	}
	
	public void makeTree(Graphics g, int startX, int startY) {
		LinkedList<Node> inOrder = tree2.makeStringInOrder();
		makeTreeHelper(g, startX, startY, tree2.getRoot());
	}
	
	private void makeTreeHelper(Graphics g, int x, int y, Node tree) {
		if (!(tree == null)) {
			Integer num = (Integer)tree.data;
			String str2 = num.toString();
			if (tree.color == 1) {
				makeCircle(g, Color.red, x, y, str2);
			} else {
				makeCircle(g, Color.black, x, y, str2);
			}
			makeTreeHelper(g, x - 100, y + 100, tree.left);
			makeTreeHelper(g, x + 100, y + 100, tree.right);
		}
	}
}
