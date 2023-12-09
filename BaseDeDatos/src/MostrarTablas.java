import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class MostrarTablas extends JFrame {
    private JComboBox<String> tablaComboBox;
    private Connection connection;

    public void MostrarTablas(){
        establecerConexion();

        JFrame ventanaTablas = new JFrame("Tablas");
        ventanaTablas.setSize(1000, 1000);
        ventanaTablas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaTablas.setLocationRelativeTo(null);

        tablaComboBox = new JComboBox<>(new String[]{"UniformeColegio", "PrendaVestir", "Proveedor", "MateriaPrima", "Cliente", "TelCliente", "Encargo"});
        JPanel panelBotones = new JPanel(new GridLayout(4, 1));
        JButton confirmarButton = new JButton("Confirmar");


        panelBotones.add(confirmarButton);
        ventanaTablas.setLayout(new GridLayout(2, 1));
        ventanaTablas.add(tablaComboBox, BorderLayout.CENTER);
        ventanaTablas.add(panelBotones, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable tablaBaseDatos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaBaseDatos);

        ventanaTablas.add(scrollPane, BorderLayout.CENTER);

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tablaSeleccionada = tablaComboBox.getSelectedItem().toString();
                TablaMostrar(tableModel, tablaSeleccionada);

            }
        });
        ventanaTablas.setVisible(true);
        dispose();


    }
    private void TablaMostrar(DefaultTableModel tableModel, String tablaSeleccionada) {
        try (Statement statement = connection.createStatement()) {
            // Cambia "nombre_de_tu_vista" con el nombre de la vista que deseas mostrar
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+tablaSeleccionada+";");

            // Limpiar el contenido actual de la tabla
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);

            // Obtener información sobre las columnas
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numColumns = metaData.getColumnCount();

            // Añadir nombres de columnas al modelo de tabla
            Vector<String> columnNames = new Vector<>();
            for (int column = 1; column <= numColumns; column++) {
                columnNames.add(metaData.getColumnName(column));
            }
            tableModel.setColumnIdentifiers(columnNames);

            // Añadir filas al modelo de tabla
            while (resultSet.next()) {
                Vector<Object> rowData = new Vector<>();
                for (int column = 1; column <= numColumns; column++) {
                    rowData.add(resultSet.getObject(column));
                }
                tableModel.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void establecerConexion() {

        try {
            Class.forName("org.postgresql.Driver");
            String usuario="postgres";
            String contrasena="202259500";
            String bd="Proyecto";
            String ip="localhost";
            String puerto="5432";

            // Reemplaza "jdbc:postgresql://localhost:5432/nombre_de_tu_bd" con tu URL de conexión
            String url = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;;


            connection = DriverManager.getConnection(url, usuario, contrasena);
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos PostgreSQL");
            } else {
                System.out.println("No se pudo establecer la conexión");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
