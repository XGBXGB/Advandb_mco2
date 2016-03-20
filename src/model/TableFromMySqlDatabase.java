package model;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import model.DBConnect;

public class TableFromMySqlDatabase extends JFrame {
	public TableFromMySqlDatabase() {
		
	}

	public DefaultTableModel getResultTable(String query) {
		ArrayList<String> columnNames = new ArrayList<String>();
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		try{
			Connection connection = DBConnect.getConnection();
			Statement stmt = connection.createStatement();
			Statement anotherStmt = connection.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(query);
			
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			System.out.println("c " + columns);
			// Get column names
			for (int i = 1; i <= columns; i++) {
				columnNames.add(md.getColumnName(i));
			}

			// Get row data
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>(columns);

				for (int i = 1; i <= columns; i++) {
					if(rs.getObject(i)!=null)
						row.add(rs.getObject(i).toString());
					else
						row.add("null");
				}

				data.add(row);
			}
			System.out.println("r " + data.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Create Vectors and copy over elements from ArrayLists to them
		// Vector is deprecated but I am using them in this example to keep
		// things simple - the best practice would be to create a custom defined
		// class which inherits from the AbstractTableModel class
		Vector<String> columnNamesVector = new Vector<String>();
		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

		for (int i = 0; i < data.size(); i++)

		{
			ArrayList<String> subArray = (ArrayList<String>) data.get(i);
			Vector<String> subVector = new Vector<String>();
			for (int j = 0; j < subArray.size(); j++) {
				subVector.add(subArray.get(j));
			}
			dataVector.add(subVector);
		}

		for (int i = 0; i < columnNames.size(); i++)
			columnNamesVector.add(columnNames.get(i));
		DefaultTableModel tableModel = new DefaultTableModel(dataVector,columnNamesVector) {
			public Class getColumnClass(int column) {
				for (int row = 0; row < getRowCount(); row++) {
					Object o = getValueAt(row, column);

					if (o != null) {
						return o.getClass();
					}
				}

				return Object.class;
			}
		};
		return tableModel;

	}
}