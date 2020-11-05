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
		g.setColor(Color.BLACK);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    g.setColor(Color.RED);
	    g.fillOval(50, 50, 100, 100);
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
		// TODO Auto-generated method stub
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
			tree2.preorder();
		}
	}
}
