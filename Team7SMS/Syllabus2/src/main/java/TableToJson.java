import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;

public class TableToJson {

    public static void saveTableToJson(JTable table, String fileName) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        // JSON verisini temsil eden bir ObjectNode oluştur
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNode = mapper.createObjectNode();

        // Sütun isimlerini JSON array'ine ekle
        ArrayNode columnsArray = mapper.createArrayNode();
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            columnsArray.add(tableModel.getColumnName(i));
        }
        jsonNode.set("columns", columnsArray);

        // Verileri temsil eden JSON array'ini oluştur
        ArrayNode dataArray = mapper.createArrayNode();
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            ObjectNode rowNode = mapper.createObjectNode();
            for (int col = 0; col < tableModel.getColumnCount(); col++) {
                String columnName = tableModel.getColumnName(col);
                Object value = tableModel.getValueAt(row, col);

                // Değer null ise varsayılan değeri kullan
                String stringValue = (value != null) ? value.toString() : "";

                rowNode.put(columnName, stringValue);
            }
            dataArray.add(rowNode);
        }
        jsonNode.set("data", dataArray);

        // JSON verisini dosyaya yaz
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), jsonNode);
            System.out.println("JSON data has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public static void loadTableFromJson(JTable table, String fileName) {
    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
    tableModel.setRowCount(0); // Tabloyu temizle

    ObjectMapper mapper = new ObjectMapper();

    try {
        JsonNode jsonNode = mapper.readTree(new File(fileName));
        ArrayNode columnsArray = (ArrayNode) jsonNode.get("columns");

        if (columnsArray != null) {
            // Sütun isimlerini ayarla
            for (JsonNode columnNameNode : columnsArray) {
                tableModel.addColumn(columnNameNode.asText());
            }

            ArrayNode dataArray = (ArrayNode) jsonNode.get("data");
            if (dataArray != null) {
                // Verileri tabloya ekle
                for (JsonNode rowNode : dataArray) {
                    Object[] rowData = new Object[columnsArray.size()];
                    for (int i = 0; i < columnsArray.size(); i++) {
                        String columnName = columnsArray.get(i).asText();
                        JsonNode columnValueNode = rowNode.get(columnName);

                        // Eğer değer varsa, değeri kullan, yoksa boş bir string olarak kullan
                        String columnValue = (columnValueNode != null) ? columnValueNode.asText() : "";
                        rowData[i] = columnValue;
                    }
                    tableModel.addRow(rowData);
                }
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
}



}
