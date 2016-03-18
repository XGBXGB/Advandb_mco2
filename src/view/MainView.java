package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainView extends JFrame{
	private JTabbedPane tabbedPane;
	private FirstQueryPanel queryOne, queryTwo;
	
	public MainView(){
		queryOne = new FirstQueryPanel();
		queryTwo = new FirstQueryPanel();
		tabbedPane = new JTabbedPane();
		tabbedPane.add("Query 1", queryOne);
		tabbedPane.add("Query 2", queryTwo);
		this.add(tabbedPane);
		setVisible(true);
		setSize(900,650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	public static void main(String[] args){
		new MainView();
	}
}
