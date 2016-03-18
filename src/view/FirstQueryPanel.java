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

public class FirstQueryPanel extends JPanel implements ConditionParent, ActionListener{
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
	private TitledBorder sliceNdice_border;
	private ArrayList<ConditionPanel> conditions;
	private JTable conditionTable;
	
	public FirstQueryPanel(){
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
		rd_2_lbl.setBounds(150, 35, 100, 15);
		rd_3_lbl.setBounds(30, 65, 100, 15);
		rd_4_lbl.setBounds(150, 65, 100, 15);
		rd_5_lbl.setBounds(30, 95, 100, 15);
		rd_6_lbl.setBounds(150, 95, 100, 15);
		rd_7_lbl.setBounds(30, 125, 100, 15);
		rd_8_lbl.setBounds(150, 125, 100, 15);
		rd_1.setBounds(10, 35, 20, 15);
		rd_2.setBounds(130, 35, 20, 15);
		rd_3.setBounds(10, 65, 20, 15);
		rd_4.setBounds(130, 65, 20, 15);
		rd_5.setBounds(10, 95, 20, 15);
		rd_6.setBounds(130, 95, 20, 15);
		rd_7.setBounds(10, 125, 20, 15);
		rd_8.setBounds(130, 125, 20, 15);
		
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
		ArrayList<String> columns = new ArrayList();
		columns.add("Wakoko");
		columns.add("Wakok2");
		if(e.getSource() == condition_btn){
			conditions.add(new ConditionPanel(this, columns));
			conditionTable.setModel(new ConditionTableModel(conditions));
			conditionTable.getColumnModel().getColumn(0).setPreferredWidth(370);
			conditionTable.setRowHeight(35);
		}
	}
	
	public void removePanel(ConditionPanel object){
		conditions.remove(object);
		conditionTable.setModel(new ConditionTableModel(conditions));
		conditionTable.getColumnModel().getColumn(0).setPreferredWidth(370);
		conditionTable.setRowHeight(35);
		
	}
	
}
