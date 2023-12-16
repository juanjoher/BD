import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.sql.ResultSet;




public class Factura extends JFrame {
    private Connection connection;
    public void Factura() {
        establecerConexion();


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        JLabel label = new JLabel("Ingrese numero pedido:");
        JFrame frame = new JFrame("Facturador");
        JTextField textFieldnumeropedido = new JTextField();
        JButton btnFacturar = new JButton("Facturar");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JButton btnSalir = new JButton("Salir");
        btnFacturar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para buscar en la base de datos y generar factura
                int numeropedido = Integer.parseInt(textFieldnumeropedido.getText());
                    try {
                        // Buscar información en la base de datos
                        String query = "SELECT idcliente, cantidad, preciototal, numeropedido FROM encargo WHERE numeropedido = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, numeropedido);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            // Obtener datos de la consulta
                            int idCliente = resultSet.getInt("idcliente");
                            int cantidad = resultSet.getInt("cantidad");
                            double precioTotal = resultSet.getDouble("preciototal");

                            // Generar factura en una ventana emergente
                            JOptionPane.showMessageDialog(frame,
                                    "ID Cliente: " + idCliente + "\nNúmero de Pedido: " + numeropedido +
                                            "\nCantidad: " + cantidad + "\nPrecio Total: " + precioTotal,
                                    "Factura de Venta", JOptionPane.INFORMATION_MESSAGE);

                            actualizarEstadoEntrega(numeropedido, "Entregado");

                        } else {
                            JOptionPane.showMessageDialog(frame,
                                    "No se encontró información para el número de pedido: " + numeropedido,
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        // Cierra los recursos de base de datos
                        resultSet.close();
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

            }
            private void actualizarEstadoEntrega(int numeroPedido, String nuevoEstado) {
                try {
                    // Consulta para actualizar el estado de entrega
                    String queryActualizar = "UPDATE encargo SET estadoentrega = ? WHERE numeropedido = ?";
                    PreparedStatement preparedStatementActualizar = connection.prepareStatement(queryActualizar);
                    preparedStatementActualizar.setString(1, nuevoEstado);
                    preparedStatementActualizar.setInt(2, numeroPedido);
                    preparedStatementActualizar.executeUpdate();

                    // Cierra los recursos de base de datos
                    preparedStatementActualizar.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                InterfazInicioSesion Interfaz = new InterfazInicioSesion();
                Interfaz.VentanaRegistro();

            }
        });

        panel.add(label);
        panel.add(textFieldnumeropedido);
        panel.add(btnFacturar);
        panel.add(btnSalir);

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setSize(300, 150);
        frame.setVisible(true);

    }



    public void establecerConexion() {

        try {
            Class.forName("org.postgresql.Driver");
            String usuario="postgres";
            String contrasena="1234";
            String bd="Proyecto";
            String ip="localhost";
            String puerto="5432";

            // Reemplaza "jdbc:postgresql://localhost:5432/nombre_de_tu_bd" con tu URL de conexión
            String url = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;;


            connection = DriverManager.getConnection(url, usuario, contrasena);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
