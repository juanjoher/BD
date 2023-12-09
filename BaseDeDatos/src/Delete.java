import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class Delete extends JFrame {
    private Connection connection;
    private JComboBox<String> tablaComboBox;
    private JComboBox<String> uniformeComboBox;
    private JComboBox<String> prendaComboBox;
    private JComboBox<String> proveedorComboBox;
    private JComboBox<String> materiaComboBox;
    private JComboBox<String> clienteComboBox;
    private JComboBox<String> encargoComboBox;

    public void Delete(){
        establecerConexion();
        JFrame ventanaDelete = new JFrame("Eliminar Datos");
        ventanaDelete.setSize(300, 200);
        ventanaDelete.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaDelete.setLocationRelativeTo(null);


        tablaComboBox = new JComboBox<>(new String[]{"UniformeColegio", "PrendaVestir", "Proveedor", "MateriaPrima", "Cliente", "TelCliente", "Encargo"});






        // Crear botón para confirmar la selección
        JButton confirmarButton = new JButton("Confirmar");

        // Configurar el layout
        ventanaDelete.setLayout(new GridLayout(3, 1));

        // Agregar componentes al panel
        ventanaDelete.add(new JLabel("Seleccione una tabla:"));
        ventanaDelete.add(tablaComboBox);
        ventanaDelete.add(confirmarButton);

        // Acción del botón de confirmar
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tablaSeleccionada = tablaComboBox.getSelectedItem().toString();

                if (Objects.equals(tablaSeleccionada, "UniformeColegio")){

                    UniformeColegio();
                    ventanaDelete.dispose();

                } else if(Objects.equals(tablaSeleccionada, "PrendaVestir")){
                    PrendaVestir();
                    ventanaDelete.dispose();
                }else if(Objects.equals(tablaSeleccionada, "Proveedor")){
                    Proveedor();
                    ventanaDelete.dispose();
                }else if(Objects.equals(tablaSeleccionada, "MateriaPrima")){
                    MateriaPrima();
                    ventanaDelete.dispose();
                }else if(Objects.equals(tablaSeleccionada, "Cliente")){
                    Cliente();
                    ventanaDelete.dispose();
                }else if(Objects.equals(tablaSeleccionada, "TelCliente")){
                    TelCliente();
                    ventanaDelete.dispose();
                }else if(Objects.equals(tablaSeleccionada, "Encargo")){
                    Encargo();
                    ventanaDelete.dispose();
                }

            }
        });

        // Hacer visible la ventana de inserción
        ventanaDelete.setVisible(true);
        dispose();

    }

    public void UniformeColegio(){
        JFrame ventanaUniforme = new JFrame("Uniforme colegio");
        ventanaUniforme.setSize(300, 200);
        ventanaUniforme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaUniforme.setLocationRelativeTo(null);



        uniformeComboBox = new JComboBox<>(new String[]{"CodigoUniforme"});
        JLabel AtributoLabel = new JLabel("Codigo del uniforme:");
        JTextField AtributoTextField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");


        ventanaUniforme.setLayout(new GridLayout(6, 2));


        ventanaUniforme.add(new JLabel("Atributo y su valor para hacer la condicion de eliminación"));
        ventanaUniforme.add(uniformeComboBox);



        ventanaUniforme.add(AtributoLabel);
        ventanaUniforme.add(AtributoTextField);

        ventanaUniforme.add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String atributoSeleccionado = uniformeComboBox.getSelectedItem().toString();
                String NombreTabla= "UniformeColegio";
                Object valor= AtributoTextField.getText();

                DeleteFrom(NombreTabla,atributoSeleccionado,valor);
                ventanaUniforme.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaUniforme.setVisible(true);
        dispose();



    }
    public void PrendaVestir(){
        JFrame ventanaPrenda = new JFrame("Prenda de vestir");
        ventanaPrenda.setSize(300, 200);
        ventanaPrenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaPrenda.setLocationRelativeTo(null);



        prendaComboBox = new JComboBox<>(new String[]{"CodigoPrenda"});
        JLabel AtributoLabel = new JLabel("Codigo de prenda :");
        JTextField AtributoTextField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");


        ventanaPrenda.setLayout(new GridLayout(6, 2));


        ventanaPrenda.add(new JLabel("Atributo y su valor para hacer la condicion de eliminación"));
        ventanaPrenda.add(prendaComboBox);



        ventanaPrenda.add(AtributoLabel);
        ventanaPrenda.add(AtributoTextField);

        ventanaPrenda.add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String atributoSeleccionado = prendaComboBox.getSelectedItem().toString();
                String NombreTabla= "PrendaVestir";
                Object valor= AtributoTextField.getText();

                DeleteFrom(NombreTabla,atributoSeleccionado,valor);
                ventanaPrenda.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaPrenda.setVisible(true);
        dispose();
    }
    public void Proveedor(){
        JFrame ventanaProveedor = new JFrame("Proveedor");
        ventanaProveedor.setSize(300, 200);
        ventanaProveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaProveedor.setLocationRelativeTo(null);



        proveedorComboBox = new JComboBox<>(new String[]{"Nit"});
        JLabel AtributoLabel = new JLabel("Digite el Nit :");
        JTextField AtributoTextField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");


        ventanaProveedor.setLayout(new GridLayout(6, 2));


        ventanaProveedor.add(new JLabel("Atributo y su valor para hacer la condicion de eliminación"));
        ventanaProveedor.add(proveedorComboBox);



        ventanaProveedor.add(AtributoLabel);
        ventanaProveedor.add(AtributoTextField);

        ventanaProveedor.add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String atributoSeleccionado = proveedorComboBox.getSelectedItem().toString();
                String NombreTabla= "Proveedor";
                Object valor= AtributoTextField.getText();

                DeleteFrom(NombreTabla,atributoSeleccionado,valor);
                ventanaProveedor.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaProveedor.setVisible(true);
        dispose();
    }
    public void MateriaPrima(){
        JFrame ventanaMateria = new JFrame("Materia prima");
        ventanaMateria.setSize(300, 200);
        ventanaMateria.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaMateria.setLocationRelativeTo(null);



        materiaComboBox = new JComboBox<>(new String[]{"CodigoMateriaPrima"});
        JLabel AtributoLabel = new JLabel("Digite el codigo de la materia prima :");
        JTextField AtributoTextField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");


        ventanaMateria.setLayout(new GridLayout(6, 2));


        ventanaMateria.add(new JLabel("Atributo y su valor para hacer la condicion de eliminación"));
        ventanaMateria.add(materiaComboBox);



        ventanaMateria.add(AtributoLabel);
        ventanaMateria.add(AtributoTextField);

        ventanaMateria.add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String atributoSeleccionado = materiaComboBox.getSelectedItem().toString();
                String NombreTabla= "MateriaPrima";
                Object valor= AtributoTextField.getText();

                DeleteFrom(NombreTabla,atributoSeleccionado,valor);
                ventanaMateria.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaMateria.setVisible(true);
        dispose();
    }
    public void Cliente(){
        JFrame ventanaCliente = new JFrame("Cliente");
        ventanaCliente.setSize(300, 200);
        ventanaCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaCliente.setLocationRelativeTo(null);



        clienteComboBox = new JComboBox<>(new String[]{"IdCliente"});
        JLabel AtributoLabel = new JLabel("Digite el Id del cliente :");
        JTextField AtributoTextField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");


        ventanaCliente.setLayout(new GridLayout(6, 2));


        ventanaCliente.add(new JLabel("Atributo y su valor para hacer la condicion de eliminación"));
        ventanaCliente.add(clienteComboBox);



        ventanaCliente.add(AtributoLabel);
        ventanaCliente.add(AtributoTextField);

        ventanaCliente.add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String atributoSeleccionado = clienteComboBox.getSelectedItem().toString();
                String NombreTabla= "Cliente";
                Object valor= AtributoTextField.getText();

                DeleteFrom(NombreTabla,atributoSeleccionado,valor);
                ventanaCliente.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaCliente.setVisible(true);
        dispose();
    }
    public void TelCliente(){
        JFrame ventanaCliente = new JFrame("Telefono cliente");
        ventanaCliente.setSize(300, 200);
        ventanaCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaCliente.setLocationRelativeTo(null);



        clienteComboBox = new JComboBox<>(new String[]{"IdCliente"});
        JLabel AtributoLabel = new JLabel("Digite el Id del cliente :");
        JTextField AtributoTextField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");


        ventanaCliente.setLayout(new GridLayout(6, 2));


        ventanaCliente.add(new JLabel("Atributo y su valor para hacer la condicion de eliminación"));
        ventanaCliente.add(clienteComboBox);



        ventanaCliente.add(AtributoLabel);
        ventanaCliente.add(AtributoTextField);

        ventanaCliente.add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String atributoSeleccionado = clienteComboBox.getSelectedItem().toString();
                String NombreTabla= "TelCliente";
                Object valor= AtributoTextField.getText();

                DeleteFrom(NombreTabla,atributoSeleccionado,valor);
                ventanaCliente.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaCliente.setVisible(true);
        dispose();
    }
    public void Encargo(){
        JFrame ventanaEncargo = new JFrame("Encargo");
        ventanaEncargo.setSize(300, 200);
        ventanaEncargo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaEncargo.setLocationRelativeTo(null);



        encargoComboBox = new JComboBox<>(new String[]{"NumeroPedido"});
        JLabel AtributoLabel = new JLabel("Digite el numero del pedido :");
        JTextField AtributoTextField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");


        ventanaEncargo.setLayout(new GridLayout(6, 2));


        ventanaEncargo.add(new JLabel("Atributo y su valor para hacer la condicion de eliminación"));
        ventanaEncargo.add(encargoComboBox);



        ventanaEncargo.add(AtributoLabel);
        ventanaEncargo.add(AtributoTextField);

        ventanaEncargo.add(eliminarButton);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String atributoSeleccionado = encargoComboBox.getSelectedItem().toString();
                String NombreTabla= "Encargo";
                Object valor= AtributoTextField.getText();

                DeleteFrom(NombreTabla,atributoSeleccionado,valor);
                ventanaEncargo.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaEncargo.setVisible(true);
        dispose();
    }











    public void DeleteFrom(String NombreTabla, String atributo, Object valor){
        try {

            // Crear la consulta de inserción (ajusta según tu esquema)
            String consultaInsercion = "DELETE FROM " + NombreTabla + " WHERE " + atributo+"="+valor;
            System.out.println(consultaInsercion);


            try (PreparedStatement preparedStatement = connection.prepareStatement(consultaInsercion)) {
                // Ejecutar la consulta de inserción
                int filasAfectadas = preparedStatement.executeUpdate();

                // Imprimir mensaje de éxito si se insertaron filas
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(null, "Datos eliminados con éxito en la tabla " +NombreTabla, "Informacion", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "No se eliminaron datos de la tabla " +NombreTabla, "Error", JOptionPane.ERROR_MESSAGE);

                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejar la excepción según tus necesidades
            JOptionPane.showMessageDialog(null, "Error al eliminar datos en la tabla " +NombreTabla, "Error", JOptionPane.ERROR_MESSAGE);
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

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
