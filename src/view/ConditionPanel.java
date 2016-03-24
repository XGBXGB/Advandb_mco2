package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConditionPanel extends JPanel implements ActionListener{
	private ConditionParent parent;
	private JComboBox column, operator, condition_kit;
	private JTextField condition;
	private JPanel container;
	private JButton close;
	private ArrayList<String> columns;
	private ArrayList<String> operators, kit_types;

	private JComboBox condition_calam;
	private ArrayList<String> calam_types;
	
	private JComboBox condition_area;
	private ArrayList<String> areas;
	
	
	public ConditionPanel(ConditionParent parent, ArrayList<String> columns){
		this.columns = columns;
		
		operators = new ArrayList();
		//operators.add("LIKE");
		operators.add("=");
		operators.add("<");
		operators.add(">");
		operators.add("<=");
		operators.add(">=");
		
		kit_types = new ArrayList();
		kit_types.add("Baterya");
		kit_types.add("Damit");
		kit_types.add("Flashlight/Emergency Light");
		kit_types.add("Kandila");
		kit_types.add("Kumot");
		kit_types.add("Mahalagang Dokumento");
		kit_types.add("Medical Kit");
		kit_types.add("Pagkain");
		kit_types.add("Pito");
		kit_types.add("Posporo/Lighter");
		kit_types.add("Radyong de baterya");
		kit_types.add("Tubig");
		kit_types.add("Iba pang uri");
		
		calam_types = new ArrayList<>();
		/*calam_types.add("1");
		calam_types.add("2");
		calam_types.add("3");
		calam_types.add("4");
		calam_types.add("5");
		calam_types.add("6");
		calam_types.add("7");
		calam_types.add("8");
		calam_types.add("9");
		calam_types.add("10");
		calam_types.add("11");*/
		calam_types.add("Bagyo");
		calam_types.add("Baha");
		calam_types.add("Tagtuyot");
		calam_types.add("Lindol");
		calam_types.add("Pagsabog ng Bulkan");
		calam_types.add("Landslide");
		calam_types.add("Tsunami");
		calam_types.add("Sunog");
		calam_types.add("Forest fire");
		calam_types.add("Armadong digmaan");
		calam_types.add("Iba pang uri");
		
		areas = new ArrayList<String>();
		areas.add("Palawan");
		areas.add("Marinduque");
		
		this.parent = parent;
		container = new JPanel();
		container.setBounds(0, 0, 370, 80);
		
		close = new JButton("X");

		condition_kit = new JComboBox(kit_types.toArray());
		column = new JComboBox(columns.toArray());
		condition = new JTextField();
		operator = new JComboBox(operators.toArray());
		
		condition_calam = new JComboBox(calam_types.toArray());
		condition_area = new JComboBox(areas.toArray());
		
		close.setBounds(305, 4, 45, 28);
		close.addActionListener(this);
		column.addActionListener(this);
		
		//condition_kit = new JComboBox
		
		
		condition_kit.setBounds(190, 4, 110, 30);
		condition_kit.setVisible(false);
		column.setBounds(5, 4, 120, 30);
		operator.setBounds(130, 4, 55, 30);
		condition.setBounds(190, 4, 110, 30);
		
		condition_calam.setBounds(190, 4, 110, 30);
		condition_calam.setVisible(false);
		
		condition_area.setBounds(190, 4, 110, 30);
		condition_area.setVisible(false);
		
		container.add(condition_area);
		container.add(condition_calam);
		container.add(condition_kit);
		container.add(column);
		container.add(operator);
		container.add(condition);
		container.add(close);
		this.setLayout(null);
		container.setLayout(null);
		this.add(container);
		this.setVisible(true);
		this.setSize(370, 80);
		
	}
	
	public String getColumn(){
		return column.getSelectedItem().toString();
	}
	
	public String getOperator(){
		return operator.getSelectedItem().toString();
	}
	
	public String getCondition(){
		return condition.getText();
	}
	
	public String getQueryCondition(){
		
			return column.getSelectedItem().toString() +" " 
				+ operator.getSelectedItem().toString() +" "
				+ condition.getText();
	}
	
	
	public String getKitType(){
		return condition_kit.getSelectedItem().toString();
	}
	
	public String getCalamType()
	{
		return condition_calam.getSelectedItem().toString();
	}
	
	public String getAreas()
	{
		return condition_area.getSelectedItem().toString();
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==close){
			parent.removePanel(this);
		}
		else if(e.getSource() == column){
			if(column.getSelectedItem().toString().equals("kit_type")){
				condition.setVisible(false);
				operator.setVisible(false);
				condition_kit.setVisible(true);
				condition_calam.setVisible(false);
				condition_area.setVisible(false);
			}
			else if(column.getSelectedItem().toString().equals("calamity type"))
			{
				condition.setVisible(false);
				operator.setVisible(false);
				condition_kit.setVisible(false);
				condition_calam.setVisible(true);
				condition_area.setVisible(false);
			}
			else if(column.getSelectedItem().toString().equals("area"))
			{
				condition.setVisible(false);
				operator.setVisible(false);
				condition_kit.setVisible(false);
				condition_calam.setVisible(false);
				condition_area.setVisible(true);
			}
			else{
				condition.setVisible(true);
				operator.setVisible(true);
				condition_kit.setVisible(false);
				condition_calam.setVisible(false);
				condition_area.setVisible(false);
				this.revalidate();
				this.repaint();
			}
		}
	}
	

}
