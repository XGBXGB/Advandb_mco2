package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.TableFromMySqlDatabase;

public class FirstQueryPanel extends JPanel implements ConditionParent, ActionListener{
	private StringBuilder query;
	private JLabel rollUpDrillDown_lbl, sliceNdice_lbl, table_lbl;
	private JCheckBox rd_1, rd_2, rd_3, rd_4;
	private JCheckBox rd_5, rd_6, rd_7, rd_8;
	private JLabel rd_1_lbl, rd_2_lbl, rd_3_lbl, rd_4_lbl;
	private JLabel rd_5_lbl, rd_6_lbl, rd_7_lbl, rd_8_lbl;
	private JPanel rollUpDrillDown_panel, sliceNdice_panel;
	private JTable table;
	private JScrollPane tableSp, conditionSp;
	private JButton condition_btn, query_btn;
	private TitledBorder rollUpDrillDown_border;
	private TableFromMySqlDatabase tfmsd;
	private TitledBorder sliceNdice_border;
	private ArrayList<ConditionPanel> conditions;
	private JTable conditionTable;
	private ArrayList<String> columns;
	private ArrayList<String> groups;
	
	
	public FirstQueryPanel(){
		
		groups = new ArrayList();
		
		columns = new ArrayList();
		columns.add("avg_calam_freq");
		columns.add("count_calam_aid");
		columns.add("count_prepared");
		columns.add("count_exphousehold");
		columns.add("count_household");
		tfmsd = new TableFromMySqlDatabase();
		conditions = new ArrayList();
		UIManager.put("nimbusBase", new Color(0, 153, 204));
        UIManager.put("control", new Color(204, 255, 204));
        UIManager.put("nimbusBlueGrey", new Color(0, 153, 204));
		try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
		
		
		
		sliceNdice_border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Slice and Dice Section:");
		rollUpDrillDown_border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Roll Up/Drill Down Section:");
		rollUpDrillDown_panel = new JPanel();
		sliceNdice_panel = new JPanel();
		condition_btn = new JButton("Add Condition");
		query_btn = new JButton("Execute");
		rd_1_lbl = new JLabel("Column1");
		rd_2_lbl = new JLabel("Column2");
		rd_3_lbl = new JLabel("Column3");
		rd_4_lbl = new JLabel("Column4");
		rd_5_lbl = new JLabel("Column5");
		rd_6_lbl = new JLabel("Column6");
		rd_7_lbl = new JLabel("Column7");
		rd_8_lbl = new JLabel("Column8");
		table_lbl = new JLabel("Table:");
		rd_1 = new JCheckBox();
		rd_2 = new JCheckBox();
		rd_3 = new JCheckBox();
		rd_4 = new JCheckBox();
		rd_5 = new JCheckBox();
		rd_6 = new JCheckBox();
		rd_7 = new JCheckBox();
		rd_8 = new JCheckBox();
		table=new JTable(){
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
        conditionTable=new JTable(new ConditionTableModel(conditions));
        tableSp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        conditionSp = new JScrollPane(conditionTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
		
        sliceNdice_panel.setBounds(10, 370, 600,200);
        condition_btn.setBounds(420, 26, 150,80);
        query_btn.setBounds(650,130,200,80);
		rollUpDrillDown_panel.setBounds(620, 370, 250, 200);
		conditionSp.setBounds(30,26,376, 160);
		table_lbl.setBounds(270,0,150,20);
		tableSp.setBounds(10,15, 600,330);
		rd_1_lbl.setBounds(30, 35, 100, 15);
		rd_1_lbl.setText("calamity type");
		rd_2_lbl.setBounds(150, 35, 100, 15);
		rd_2_lbl.setText("location id");
		rd_3_lbl.setBounds(30, 65, 100, 15);
		rd_3_lbl.setText("area id");
/*		rd_4_lbl.setBounds(150, 65, 100, 15);
		rd_5_lbl.setBounds(30, 95, 100, 15);
		rd_6_lbl.setBounds(150, 95, 100, 15);
		rd_7_lbl.setBounds(30, 125, 100, 15);
		rd_8_lbl.setBounds(150, 125, 100, 15);*/
		rd_1.setBounds(10, 35, 20, 15);
		rd_2.setBounds(130, 35, 20, 15);
		rd_3.setBounds(10, 65, 20, 15);
/*		rd_4.setBounds(130, 65, 20, 15);
		rd_5.setBounds(10, 95, 20, 15);
		rd_6.setBounds(130, 95, 20, 15);
		rd_7.setBounds(10, 125, 20, 15);
		rd_8.setBounds(130, 125, 20, 15);*/
		
		conditionTable.setDefaultEditor(ConditionPanel.class, new ConditionCellEditor());
		conditionTable.setDefaultRenderer(ConditionPanel.class, new ConditionCellRenderer());
		conditionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		conditionTable.getColumnModel().getColumn(0).setPreferredWidth(370);
		conditionTable.setRowHeight(80);
		sliceNdice_panel.setBorder(sliceNdice_border);
		rollUpDrillDown_panel.setBorder(rollUpDrillDown_border);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setEnabled(false);
		condition_btn.addActionListener(this);
		query_btn.addActionListener(this);
		rd_1.addActionListener(this);
		rd_2.addActionListener(this);
		rd_3.addActionListener(this);
		
		rollUpDrillDown_panel.setLayout(null);
		sliceNdice_panel.setLayout(null);
		rollUpDrillDown_panel.add(rd_1);
		rollUpDrillDown_panel.add(rd_2);
		rollUpDrillDown_panel.add(rd_3);
		rollUpDrillDown_panel.add(rd_4);
		rollUpDrillDown_panel.add(rd_5);
		rollUpDrillDown_panel.add(rd_6);
		rollUpDrillDown_panel.add(rd_7);
		rollUpDrillDown_panel.add(rd_8);
		rollUpDrillDown_panel.add(rd_1_lbl);
		rollUpDrillDown_panel.add(rd_2_lbl);
		rollUpDrillDown_panel.add(rd_3_lbl);
		rollUpDrillDown_panel.add(rd_4_lbl);
		rollUpDrillDown_panel.add(rd_5_lbl);
		rollUpDrillDown_panel.add(rd_6_lbl);
		rollUpDrillDown_panel.add(rd_7_lbl);
		rollUpDrillDown_panel.add(rd_8_lbl);
		sliceNdice_panel.add(conditionSp);
		sliceNdice_panel.add(condition_btn);
		this.add(query_btn);
		this.add(sliceNdice_panel);
		this.add(table_lbl);
		this.add(tableSp);
		this.add(rollUpDrillDown_panel);
		this.setLayout(null);
		this.setBackground(new Color(204, 255, 204));
		setVisible(true);
		setSize(900,650);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == condition_btn){
			conditions.add(new ConditionPanel(this, columns));
			conditionTable.setModel(new ConditionTableModel(conditions));
			conditionTable.getColumnModel().getColumn(0).setPreferredWidth(370);
			conditionTable.setRowHeight(35);
		}
		else if(e.getSource() == rd_1)
		{
			updateConditionColumns();
			
			if(rd_1.isSelected())
			{
				if(!groups.contains("One"))
					groups.add("One");
				
			}
			else
			{
				if(groups.contains("One"))
					groups.remove("One");
			}
		}
		else if(e.getSource() == rd_2)
		{
			updateConditionColumns();
			
			if(rd_2.isSelected())
			{
				if(!groups.contains("Two"))
					groups.add("Two");
				
			}
			else
			{
				if(groups.contains("Two"))
					groups.remove("Two");
			}
		}
		else if(e.getSource() == rd_3)
		{
			updateConditionColumns();
			if(rd_3.isSelected())
			{
				if(!groups.contains("Three"))
					groups.add("Three");
				
			}
			else
			{
				if(groups.contains("Three"))
					groups.remove("Three");
			}
		}
		else if(e.getSource() == query_btn){
			
			query = new StringBuilder();
			String select_stmt = "SELECT ";
			String from_stmt = " FROM calam_summary_fact C ";
			String additional_join_cond = "";
			String group_stmt = "GROUP BY ";
			String where_stmt = "WHERE ";
			ArrayList<String> calam_types = new ArrayList<String>();
			ArrayList<String> areas = new ArrayList<String>();
			boolean calam_flag = false;
			boolean area_flag = false;
			
			for(int x = 0; x < groups.size(); x++)
			{
				if(groups.get(x).equals("One"))
				{
					select_stmt += "CT.calamity_type, ";
					from_stmt += "INNER JOIN calamitytypes CT ON C.calam_id = CT.calamity_type_id ";
					if(!group_stmt.endsWith("GROUP BY "))
						group_stmt += " , C.calam_id ";
					else
						group_stmt += "C.calam_id ";
				}
				else if(groups.get(x).equals("Two"))
				{
					select_stmt += "C.loc_id, ";
					if(groups.contains("Three") && (groups.indexOf("Two") < groups.indexOf("Three")))
						from_stmt += "INNER JOIN location L ON C.loc_id = L.loc_id ";
					else if(!groups.contains("Three"))
						from_stmt += "INNER JOIN location L ON C.loc_id = L.loc_id ";
					if(!group_stmt.endsWith("GROUP BY "))
						group_stmt += " , C.loc_id ";
					else
						group_stmt += "C.loc_id ";

				}
				else if(groups.get(x).equals("Three"))
				{
					int index1 = 0, index2 = 0;
					
					select_stmt += "L.area_id, ";					
					
					if(groups.contains("Two") && (groups.indexOf("Two") > groups.indexOf("Three")))
						from_stmt += "INNER JOIN location L ON L.loc_id = C.loc_id ";
					else if(!groups.contains("Two"))
						from_stmt += "INNER JOIN location L ON C.loc_id = L.loc_id ";
					from_stmt += "INNER JOIN Area A ON L.area_id = A.area_id ";
					if(!group_stmt.endsWith("GROUP BY "))
						group_stmt += ",L.area_id ";
					else
						group_stmt += "L.area_id ";
				}
			}
			
			/*if(rd_3.isSelected())
			{
				select_stmt += "L.area_id, ";
				from_stmt += "INNER JOIN location L ON L.loc_id = C.loc_id ";
				from_stmt += "INNER JOIN Area A ON L.area_id = A.area_id ";
				if(!group_stmt.endsWith("GROUP BY "))
					group_stmt += ",L.area_id ";
				else
					group_stmt += "L.area_id ";
			}
			if(rd_2.isSelected())
			{
			
				select_stmt += "C.loc_id, ";
				if(!group_stmt.endsWith("GROUP BY "))
					group_stmt += " , C.loc_id ";
				else
					group_stmt += "C.loc_id ";
				
			}
			if(rd_1.isSelected())
			{
				//select_stmt += "C.calam_id, ";
				//group_stmt += "C.calam_id  ";
				select_stmt += "CT.calamity_type, ";
				from_stmt += "INNER JOIN calamitytypes CT ON C.calam_id = CT.calamity_type_id ";
				if(!group_stmt.endsWith("GROUP BY "))
					group_stmt += " , C.calam_id ";
				else
					group_stmt += "C.calam_id ";
			}*/
			
			select_stmt += " AVG(C.avg_calam_freq) AS \"AveFreq\"," +
					"SUM(C.count_calam_aid) AS \"#ofAidedHH\", SUM(C.count_prepared) AS \"#ofPrepHH\","
					+ "SUM(C.count_exphousehold) AS\"#ofExperiencedHH\", SUM(C.count_household) AS \"#ofTotalHH\"";
			
			for(int i=0; i<conditions.size(); i++){
				if(conditions.get(i).getColumn().equals("avg_calam_freq")
						|| conditions.get(i).getColumn().equals("count_calam_aid")
						|| conditions.get(i).getColumn().equals("count_prepared")
						|| conditions.get(i).getColumn().equals("count_exphousehold")
						|| conditions.get(i).getColumn().equals("count_household")
						|| conditions.get(i).getColumn().equals("mun")
						|| conditions.get(i).getColumn().equals("zone")
						|| conditions.get(i).getColumn().equals("brgy")
						|| conditions.get(i).getColumn().equals("purok")
						){
					if(where_stmt.endsWith("WHERE "))
						where_stmt += conditions.get(i).getQueryCondition()+" ";
					else
						where_stmt +=" AND " + conditions.get(i).getQueryCondition()+" ";
				}
				else if(((ConditionPanel)conditions.get(i)).getColumn().equals("calamity type")){
						calam_types.add(conditions.get(i).getCalamType());
						calam_flag = true;
				}
				else if(((ConditionPanel)conditions.get(i)).getColumn().equals("area")){
					areas.add(conditions.get(i).getAreas());
					area_flag = true;
				}
			}
			if(calam_flag){
				//if(where_stmt.isEmpty())
				//	where_stmt = "WHERE ";
				if(calam_types.size()>0)
				{
					if(!where_stmt.equals("WHERE "))
						where_stmt += " AND ";
					/*where_stmt += " (calam_id = " + 1 +" ";*/
					where_stmt += " (CT.calamity_type = \"" + calam_types.get(0) +"\" ";
					
					if(calam_types.size()>1)
					{
						for(int x = 1; x < calam_types.size(); x++)
						{
							/*where_stmt += " OR calam_id = " + (x+1) +" ";*/
							where_stmt += " OR CT.calamity_type = \"" + calam_types.get(x) +"\" ";
						}
					
					}
					where_stmt += ") ";
			
				}
			}
			
			if(area_flag){
				//if(where_stmt.isEmpty())
				//	where_stmt = "WHERE ";
				if(areas.size()>0)
				{
					if(!where_stmt.equals("WHERE "))
						where_stmt += " AND ";
					/*where_stmt += " (calam_id = " + 1 +" ";*/
					where_stmt += " (A.area_name = \"" + areas.get(0) +"\" ";
					
					if(areas.size()>1)
					{
						for(int x = 1; x < areas.size(); x++)
						{
							/*where_stmt += " OR calam_id = " + (x+1) +" ";*/
							where_stmt += " OR A.area_name = \"" + areas.get(x) +"\" ";
						}
					
					}
					where_stmt += ") ";
			
				}
			}
			
			
			query.append(select_stmt);
			query.append(from_stmt);
			query.append(additional_join_cond);
			if(!where_stmt.endsWith("WHERE "))
				query.append(where_stmt);
			if(!group_stmt.endsWith("GROUP BY "))
				query.append(group_stmt);
			
			System.out.println(query.toString());
			table.setModel(tfmsd.getResultTable(query.toString()));
		}
	}
	
	public void updateConditionColumns(){
		
		if(rd_2.isSelected() && !columns.contains("loc_id")){
			columns.add("loc_id");
			columns.add("mun");
			columns.add("zone");
			columns.add("brgy");
			columns.add("purok");
		}
		else if(!rd_2.isSelected()){
			columns.remove("loc_id");
			columns.remove("mun");
			columns.remove("zone");
			columns.remove("brgy");
			columns.remove("purok");
		}
		
		if(rd_1.isSelected() && !columns.contains("calamity type")){
			columns.add("calamity type");
		}
		else if(!rd_1.isSelected()){
			columns.remove("calamity type");
		}
		
		if(rd_3.isSelected() && !columns.contains("area")){
			columns.add("area");
		}
		else if(!rd_3.isSelected()){
			columns.remove("area");
		}
		
		conditions.clear();
		conditionTable.setModel(new ConditionTableModel(conditions));
		conditionTable.getColumnModel().getColumn(0).setPreferredWidth(370);
		conditionTable.setRowHeight(35);
	}
	
	public void removePanel(ConditionPanel object){
		conditions.remove(object);
		conditionTable.setModel(new ConditionTableModel(conditions));
		conditionTable.getColumnModel().getColumn(0).setPreferredWidth(370);
		conditionTable.setRowHeight(35);
		
	}
	
}
