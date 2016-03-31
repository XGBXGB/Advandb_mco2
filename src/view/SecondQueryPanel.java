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

public class SecondQueryPanel extends JPanel implements ConditionParent, ActionListener{
	private StringBuilder query;
	private JLabel rollUpDrillDown_lbl, sliceNdice_lbl, table_lbl;
	private JCheckBox rd_1, rd_2, rd_3;
	private JLabel rd_1_lbl, rd_2_lbl, rd_3_lbl, query_desc;
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

	private ArrayList<String> select_stmt = new ArrayList();
	private ArrayList<String> from_stmt = new ArrayList();
	private ArrayList<String> group_stmt = new ArrayList();
	private String additional_join_cond = "";
	private String where_stmt = "";
	private String kit_types = "";
	private boolean kit_flag = false;

	
	public SecondQueryPanel(){
		
		
		columns = new ArrayList();
		columns.add("expiry");
		query = new StringBuilder();
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
		query_desc = new JLabel("<html>Query description: <br>Lorem ipsum<hmtl>");
		rd_1_lbl = new JLabel("loc_id");
		rd_2_lbl = new JLabel("kit_type");
		rd_3_lbl = new JLabel("area_name");
		/*rd_4_lbl = new JLabel("Column4");
		rd_5_lbl = new JLabel("Column5");
		rd_6_lbl = new JLabel("Column6");
		rd_7_lbl = new JLabel("Column7");
		rd_8_lbl = new JLabel("Column8");*/
		table_lbl = new JLabel("Table:");
		rd_1 = new JCheckBox();
		rd_2 = new JCheckBox();
		rd_3 = new JCheckBox();
		/*rd_4 = new JCheckBox();
		rd_5 = new JCheckBox();
		rd_6 = new JCheckBox();
		rd_7 = new JCheckBox();
		rd_8 = new JCheckBox();*/
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
		query_desc.setBounds(650, 0, 100,100);
		table_lbl.setBounds(270,0,150,20);
		tableSp.setBounds(10,15, 600,330);
		rd_1_lbl.setBounds(30, 35, 100, 15);
		rd_2_lbl.setBounds(150, 35, 100, 15);
		rd_3_lbl.setBounds(30, 65, 100, 15);
		/*rd_4_lbl.setBounds(150, 65, 100, 15);
		rd_5_lbl.setBounds(30, 95, 100, 15);
		rd_6_lbl.setBounds(150, 95, 100, 15);
		rd_7_lbl.setBounds(30, 125, 100, 15);
		rd_8_lbl.setBounds(150, 125, 100, 15);*/
		rd_1.setBounds(10, 35, 20, 15);
		rd_2.setBounds(130, 35, 20, 15);
		rd_3.setBounds(10, 65, 20, 15);
		/*rd_4.setBounds(130, 65, 20, 15);
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
		/*rollUpDrillDown_panel.add(rd_4);
		rollUpDrillDown_panel.add(rd_5);
		rollUpDrillDown_panel.add(rd_6);
		rollUpDrillDown_panel.add(rd_7);
		rollUpDrillDown_panel.add(rd_8);*/
		rollUpDrillDown_panel.add(rd_1_lbl);
		rollUpDrillDown_panel.add(rd_2_lbl);
		rollUpDrillDown_panel.add(rd_3_lbl);
		/*rollUpDrillDown_panel.add(rd_4_lbl);
		rollUpDrillDown_panel.add(rd_5_lbl);
		rollUpDrillDown_panel.add(rd_6_lbl);
		rollUpDrillDown_panel.add(rd_7_lbl);
		rollUpDrillDown_panel.add(rd_8_lbl);*/
		sliceNdice_panel.add(conditionSp);
		sliceNdice_panel.add(condition_btn);
		this.add(query_desc);
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
		else if(e.getSource() == rd_1 || e.getSource() == rd_2 || e.getSource() == rd_3){
			if(e.getSource() == rd_2){
				if(rd_2.isSelected()){
					select_stmt.add("K.kit_type");
					from_stmt.add("INNER JOIN kittypes K ON LK.kit_id = K.kit_type_id");
					group_stmt.add("K.kit_type");
				}else{
					select_stmt.remove("K.kit_type");
					from_stmt.remove("INNER JOIN kittypes K ON LK.kit_id = K.kit_type_id");
					group_stmt.remove("K.kit_type");
				}
				updateConditionColumns(2);
			}
			else if(e.getSource() == rd_1){
				if(rd_1.isSelected()){
					select_stmt.add("LK.loc_id");
					group_stmt.add("LK.loc_id");
					if(!from_stmt.contains("INNER JOIN location L ON LK.loc_id = L.loc_id"))
						from_stmt.add("INNER JOIN location L ON LK.loc_id = L.loc_id");
				}else{
					select_stmt.remove("LK.loc_id");
					group_stmt.remove("LK.loc_id");
					if(!group_stmt.contains("area_name"))
						from_stmt.remove("INNER JOIN location L ON LK.loc_id = L.loc_id");
				}
				updateConditionColumns(1);
			}
			else if(e.getSource() == rd_3){
				if(rd_3.isSelected()){
					select_stmt.add("area_name");
					if(!from_stmt.contains("INNER JOIN location L ON LK.loc_id = L.loc_id"))
						from_stmt.add("INNER JOIN location L ON LK.loc_id = L.loc_id");
					from_stmt.add("INNER JOIN area A ON L.area_id = A.area_id");
					group_stmt.add("area_name");
				}else{
					select_stmt.remove("area_name");
					from_stmt.remove("INNER JOIN area A ON L.area_id = A.area_id");
					group_stmt.remove("area_name");
					if(!group_stmt.contains("LK.loc_id"))
						from_stmt.remove("INNER JOIN location L ON LK.loc_id = L.loc_id");
				}
				updateConditionColumns(3);
			}
		}
		else if(e.getSource() == query_btn){
			/*
		  	SELECT loc_id, K.kit_type, AVG(expiry), COUNT(household_id) 
			FROM loc_households_kit LK
			INNER JOIN kittypes K
			ON LK.kit_id = K.kit_type_id
			GROUP BY loc_id, K.kit_type;
			 */
			additional_join_cond = "";
			where_stmt = "";
			kit_types = "";
			kit_flag = false;
			
			query = new StringBuilder();
			select_stmt.add("AVG(expiry)");
			select_stmt.add("COUNT(household_id)");
			
			query.append("SELECT ");
			for(int i=0; i< select_stmt.size(); i++){
				query.append(select_stmt.get(i));
				if(i+1 != select_stmt.size())
					query.append(", ");
				else
					query.append(" ");
			}
			
			query.append("FROM loc_households_kit LK ");
			for(int i=0; i< from_stmt.size(); i++){
				query.append(from_stmt.get(i));
				if(i+1 != from_stmt.size())
					query.append(" ");
				else
					query.append(" ");
			}
			
			for(int i=0; i<conditions.size(); i++){
				if(conditions.get(i).getColumn().equals("expiry")){
					if(where_stmt.isEmpty())
						where_stmt = "WHERE " + conditions.get(i).getQueryCondition()+" ";
					else
						where_stmt +="AND " + conditions.get(i).getQueryCondition()+" ";
				}
				else if(((ConditionPanel)conditions.get(i)).getColumn().equals("kit_type")){
						kit_types +=  "'"+conditions.get(i).getKitType()+"',";
						kit_flag = true;
				}else if(conditions.get(i).getColumn().equals("area")){
					if(where_stmt.isEmpty())
						where_stmt = "WHERE area_name = '"+conditions.get(i).getAreas()+"' ";
					else
						where_stmt +="AND area_name = '"+conditions.get(i).getAreas()+"' ";
				}else{
					if(where_stmt.isEmpty())
						where_stmt = "WHERE " + conditions.get(i).getQueryCondition()+" ";
					else
						where_stmt +="AND " + conditions.get(i).getQueryCondition()+" ";
				}
			}
			
			if(kit_flag){
				if(where_stmt.isEmpty())
					where_stmt = "WHERE " + "kit_type IN ("+kit_types.substring(0, kit_types.length()-1)+") ";
				else
					where_stmt +="AND " + "kit_type IN ("+kit_types.substring(0, kit_types.length()-1)+") ";
			}
		
			query.append(where_stmt);
			if(group_stmt.size()!=0){
				query.append("GROUP BY ");
				for(int i=0; i< group_stmt.size(); i++){
					query.append(group_stmt.get(i));
					if(i+1 != group_stmt.size())
						query.append(", ");
					else
						query.append(" ");
				}
			}
			
			
			System.out.println(query.toString());
			table.setModel(tfmsd.getResultTable(query.toString()));
			select_stmt.remove("AVG(expiry)");
			select_stmt.remove("COUNT(household_id)");
			
		}
	}
	
	public void updateConditionColumns(int n){
		if(n==2){
			if(rd_2.isSelected() && !columns.contains("kit_type")){
				columns.add("kit_type");
			}
			else if(!rd_2.isSelected()){
				columns.remove("kit_type");
			}
		}
		else if(n==1){
			if(rd_1.isSelected() && !columns.contains("mun")){
				columns.add("loc_id");
				columns.add("mun");
				columns.add("zone");
				columns.add("purok");
				columns.add("brgy");
			}
			else if(!rd_1.isSelected()){
				columns.remove("loc_id");
				columns.remove("mun");
				columns.remove("zone");
				columns.remove("purok");
				columns.remove("brgy");
			}
		}
		else if(n==3){
			if(rd_3.isSelected() && !columns.contains("area_name")){
				columns.add("area");
			}
			else if(!rd_3.isSelected()){
				columns.remove("area");
			}
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
