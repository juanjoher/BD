import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Consulta extends JFrame {
    private JButton btnConsultarColegios;
    private JButton btnConsultarClientes;
    private JButton btnConsultarProductos;
    private JButton btnConsultarProveedores;
    private JTextArea txtResultado;
    private Connection connection;

    public Consulta() {
        super("Consulta de Datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 300);
        setLocationRelativeTo(null);

        btnConsultarColegios = new JButton("Consultar Colegios");
        btnConsultarClientes = new JButton("Consultar Clientes");
        btnConsultarProductos = new JButton("Consultar Productos");
        btnConsultarProveedores = new JButton("Consultar Proveedores");
        txtResultado = new JTextArea();

        btnConsultarColegios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarConsulta((JButton) e.getSource());
            }
        });

        btnConsultarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarConsulta((JButton) e.getSource());
            }
        });

        btnConsultarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarConsulta((JButton) e.getSource());
            }
        });

        btnConsultarProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarConsulta((JButton) e.getSource());
            }
        });

        JPanel panel = new JPanel();
        panel.add(btnConsultarColegios);
        panel.add(btnConsultarClientes);
        panel.add(btnConsultarProductos);
        panel.add(btnConsultarProveedores);

        add(panel, "North");
        add(new JScrollPane(txtResultado), "Center");

        // Establecer la conexión a la base de datos
        establecerConexion();
    }
    private void realizarConsulta(JButton sourceButton) {
        String consulta = "";
        String resultado = "";

        // Determinar la consulta según el botón presionado
        if (sourceButton == btnConsultarColegios) {
            consulta = "SELECT * FROM Colegios";
        } else if (sourceButton == btnConsultarClientes) {
            consulta = "SELECT * FROM Clientes";
        } else if (sourceButton == btnConsultarProductos) {
            consulta = "SELECT * FROM Productos";
        } else if (sourceButton == btnConsultarProveedores) {
            consulta = "SELECT * FROM Proveedores";
        }

        // Ejecutar la consulta y mostrar los resultados en el JTextArea
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            while (resultSet.next()) {
                // Aquí puedes personalizar cómo se muestra cada fila en el JTextArea
                resultado += resultSet.getString(1) + " " +
                        resultSet.getString(2) + " " +
                        resultSet.getString(3) + "\n";
            }

            txtResultado.setText(resultado);

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            String url = "jdbc:postgresql://localhost:5432/Proyecto";


            connection = DriverManager.getConnection(url, usuario, contrasena);
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos PostgreSQL");
            } else {
                connection = DriverManager.getConnection(url, usuario, "1234");
                if (connection != null) {
                    System.out.println("Conexión exitosa a la base de datos PostgreSQL");
                } else {

                    System.out.println("No se pudo establecer la conexión");}
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
}