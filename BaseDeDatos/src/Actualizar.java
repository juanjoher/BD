import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class Actualizar extends JFrame {
    private JComboBox<String> tablaComboBox;
    private Connection connection;

    public void Actualizar(){
        establecerConexion();
        JFrame ventanaAct = new JFrame("Insertar Datos");
        ventanaAct.setSize(300, 200);
        ventanaAct.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAct.setLocationRelativeTo(null);


        tablaComboBox = new JComboBox<>(new String[]{"UniformeColegio", "PrendaVestir", "Proveedor", "MateriaPrima", "Cliente", "Encargo"});


        ventanaAct.add(new JLabel("Seleccione una tabla:"));
        ventanaAct.add(tablaComboBox);



        // Crear botón para confirmar la selección
        JButton confirmarButton = new JButton("Confirmar");
        JButton AtrasButton = new JButton("Atras");

        // Configurar el layout
        ventanaAct.setLayout(new GridLayout(6, 2));


        ventanaAct.add(confirmarButton);
        ventanaAct.add(AtrasButton);

        // Acción del botón de confirmar
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tablaSeleccionada = tablaComboBox.getSelectedItem().toString();
                if (tablaComboBox.getSelectedItem() == "UniformeColegio"){
                   UniformeColegio(tablaSeleccionada);


                }   else if (tablaComboBox.getSelectedItem() == "PrendaVestir"){
                   PrendaVestir(tablaSeleccionada);

                } else if (tablaComboBox.getSelectedItem() == "Proveedor"){
                    Proveedor(tablaSeleccionada);

                } else    if (tablaComboBox.getSelectedItem() == "MateriaPrima"){
                    MateriaPrima(tablaSeleccionada);
                } else    if (tablaComboBox.getSelectedItem() == "Cliente"){
                    Cliente(tablaSeleccionada);
                }  else if (tablaComboBox.getSelectedItem() == "Encargo"){
                    Encargo(tablaSeleccionada);
                }





            }
        });

        AtrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAct.dispose();

            }
        });

        // Hacer visible la ventana de inserción
        ventanaAct.setVisible(true);
        dispose();
    }

    public void  UniformeColegio(String tablaSeleccionada){
        JFrame ventanaAct = new JFrame("Insertar Datos");
        ventanaAct.setSize(300, 200);
        ventanaAct.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAct.setLocationRelativeTo(null);


         JLabel NombreAtributoLabel = new JLabel("Ingrese el nombre del atributo a actualizar:");
        JLabel valorLabel = new JLabel("Ingrese el nuevo valor del atributo:");



        JTextField NombreAtributoTextField = new JTextField();
        JTextField valorTextField = new JTextField();


        ventanaAct.add(NombreAtributoLabel);
        ventanaAct.add(NombreAtributoTextField);
        ventanaAct.add(valorLabel);
        ventanaAct.add(valorTextField);


        JLabel atributoCondicion = new JLabel ("Ingrese el codigo del uniforme: ");
        JTextField atributoTextField = new JTextField();





        // Crear botón para confirmar la selección
        JButton confirmarButton = new JButton("Actualizar");

        // Configurar el layout
        ventanaAct.setLayout(new GridLayout(6, 2));

        ventanaAct.add(NombreAtributoLabel);
        ventanaAct.add(NombreAtributoTextField);
        ventanaAct.add(valorLabel);
        ventanaAct.add(valorTextField);
        ventanaAct.add(atributoCondicion);
        ventanaAct.add(atributoTextField);
        ventanaAct.add(confirmarButton);

        // Acción del botón de confirmar
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Atributo = NombreAtributoTextField.getText();
                Object NuevoValor = valorTextField.getText();
                String Condicion = "CodigoUniforme";
                int AtrCondicion = Integer.parseInt(atributoTextField.getText());
                Act(tablaSeleccionada,Atributo,NuevoValor,Condicion,AtrCondicion);
                ventanaAct.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaAct.setVisible(true);
        dispose();


    }



    public void PrendaVestir(String tablaSeleccionada){
        JFrame ventanaAct = new JFrame("Insertar Datos");
        ventanaAct.setSize(300, 200);
        ventanaAct.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAct.setLocationRelativeTo(null);


        JLabel NombreAtributoLabel = new JLabel("Ingrese el nombre del atributo a actualizar:");
        JLabel valorLabel = new JLabel("Ingrese el nuevo valor del atributo:");



        JTextField NombreAtributoTextField = new JTextField();
        JTextField valorTextField = new JTextField();


        ventanaAct.add(NombreAtributoLabel);
        ventanaAct.add(NombreAtributoTextField);
        ventanaAct.add(valorLabel);
        ventanaAct.add(valorTextField);


        JLabel atributoCondicion = new JLabel ("Ingrese el codigo de la prenda: ");
        JTextField atributoTextField = new JTextField();





        // Crear botón para confirmar la selección
        JButton confirmarButton = new JButton("Actualizar");

        // Configurar el layout
        ventanaAct.setLayout(new GridLayout(6, 2));

        ventanaAct.add(NombreAtributoLabel);
        ventanaAct.add(NombreAtributoTextField);
        ventanaAct.add(valorLabel);
        ventanaAct.add(valorTextField);
        ventanaAct.add(atributoCondicion);
        ventanaAct.add(atributoTextField);
        ventanaAct.add(confirmarButton);

        // Acción del botón de confirmar
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Atributo = NombreAtributoTextField.getText();
                Object NuevoValor = valorTextField.getText();
                String Condicion = "CodigoPrenda";
                int AtrCondicion = Integer.parseInt(atributoTextField.getText());
                Act(tablaSeleccionada,Atributo,NuevoValor,Condicion,AtrCondicion);
                ventanaAct.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaAct.setVisible(true);
        dispose();
    }
    public void Proveedor(String tablaSeleccionada){
        JFrame ventanaAct = new JFrame("Insertar Datos");
        ventanaAct.setSize(300, 200);
        ventanaAct.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAct.setLocationRelativeTo(null);


        JLabel NombreAtributoLabel = new JLabel("Ingrese el nombre del atributo a actualizar:");
        JLabel valorLabel = new JLabel("Ingrese el nuevo valor del atributo:");



        JTextField NombreAtributoTextField = new JTextField();
        JTextField valorTextField = new JTextField();


        ventanaAct.add(NombreAtributoLabel);
        ventanaAct.add(NombreAtributoTextField);
        ventanaAct.add(valorLabel);
        ventanaAct.add(valorTextField);


        JLabel atributoCondicion = new JLabel ("Ingrese el Nit: ");
        JTextField atributoTextField = new JTextField();





        // Crear botón para confirmar la selección
        JButton confirmarButton = new JButton("Actualizar");

        // Configurar el layout
        ventanaAct.setLayout(new GridLayout(6, 2));

        ventanaAct.add(NombreAtributoLabel);
        ventanaAct.add(NombreAtributoTextField);
        ventanaAct.add(valorLabel);
        ventanaAct.add(valorTextField);
        ventanaAct.add(atributoCondicion);
        ventanaAct.add(atributoTextField);
        ventanaAct.add(confirmarButton);

        // Acción del botón de confirmar
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Atributo = NombreAtributoTextField.getText();
                Object NuevoValor = valorTextField.getText();
                String Condicion = "Nit";
                int AtrCondicion = Integer.parseInt(atributoTextField.getText());
                Act(tablaSeleccionada,Atributo,NuevoValor,Condicion,AtrCondicion);
                ventanaAct.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaAct.setVisible(true);
        dispose();
    }

    public void MateriaPrima(String tablaSeleccionada){
        JFrame ventanaAct = new JFrame("Insertar Datos");
        ventanaAct.setSize(300, 200);
        ventanaAct.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAct.setLocationRelativeTo(null);


        JLabel NombreAtributoLabel = new JLabel("Ingrese el nombre del atributo a actualizar:");
        JLabel valorLabel = new JLabel("Ingrese el nuevo valor del atributo:");



        JTextField NombreAtributoTextField = new JTextField();
        JTextField valorTextField = new JTextField();


        ventanaAct.add(NombreAtributoLabel);
        ventanaAct.add(NombreAtributoTextField);
        ventanaAct.add(valorLabel);
        ventanaAct.add(valorTextField);


        JLabel atributoCondicion = new JLabel ("Ingrese el codigo de la materia prima: ");
        JTextField atributoTextField = new JTextField();





        // Crear botón para confirmar la selección
        JButton confirmarButton = new JButton("Actualizar");

        // Configurar el layout
        ventanaAct.setLayout(new GridLayout(6, 2));

        ventanaAct.add(NombreAtributoLabel);
        ventanaAct.add(NombreAtributoTextField);
        ventanaAct.add(valorLabel);
        ventanaAct.add(valorTextField);
        ventanaAct.add(atributoCondicion);
        ventanaAct.add(atributoTextField);
        ventanaAct.add(confirmarButton);

        // Acción del botón de confirmar
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Atributo = NombreAtributoTextField.getText();
                Object NuevoValor = valorTextField.getText();
                String Condicion = "CodigoMateriaPrima";
                int AtrCondicion = Integer.parseInt(atributoTextField.getText());
                Act(tablaSeleccionada,Atributo,NuevoValor,Condicion,AtrCondicion);
                ventanaAct.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaAct.setVisible(true);
        dispose();
    }
    public void Cliente(String tablaSeleccionada){
        JFrame ventanaAct = new JFrame("Insertar Datos");
        ventanaAct.setSize(300, 200);
        ventanaAct.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAct.setLocationRelativeTo(null);


        JLabel NombreAtributoLabel = new JLabel("Ingrese el nombre del atributo a actualizar:");
        JLabel valorLabel = new JLabel("Ingrese el nuevo valor del atributo:");



        JTextField NombreAtributoTextField = new JTextField();
        JTextField valorTextField = new JTextField();


        ventanaAct.add(NombreAtributoLabel);
        ventanaAct.add(NombreAtributoTextField);
        ventanaAct.add(valorLabel);
        ventanaAct.add(valorTextField);


        JLabel atributoCondicion = new JLabel ("Ingrese el Id del cliente: ");
        JTextField atributoTextField = new JTextField();





        // Crear botón para confirmar la selección
        JButton confirmarButton = new JButton("Actualizar");

        // Configurar el layout
        ventanaAct.setLayout(new GridLayout(6, 2));

        ventanaAct.add(NombreAtributoLabel);
        ventanaAct.add(NombreAtributoTextField);
        ventanaAct.add(valorLabel);
        ventanaAct.add(valorTextField);
        ventanaAct.add(atributoCondicion);
        ventanaAct.add(atributoTextField);
        ventanaAct.add(confirmarButton);

        // Acción del botón de confirmar
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Atributo = NombreAtributoTextField.getText();
                Object NuevoValor = valorTextField.getText();
                String Condicion = "IdCliente";
                int AtrCondicion = Integer.parseInt(atributoTextField.getText());

                 if (Atributo == "Telefono") {
                    Act("TelCliente", "Telefono", NuevoValor, Condicion, AtrCondicion);
                } else {
                    Act(tablaSeleccionada, Atributo, NuevoValor, Condicion, AtrCondicion);
                }


                ventanaAct.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaAct.setVisible(true);
        dispose();
    }


    public void Encargo(String tablaSeleccionada){
        JFrame ventanaAct = new JFrame("Insertar Datos");
        ventanaAct.setSize(300, 200);
        ventanaAct.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAct.setLocationRelativeTo(null);


        JLabel NombreAtributoLabel = new JLabel("Ingrese el nombre del atributo a actualizar:");
        JLabel valorLabel = new JLabel("Ingrese el nuevo valor del atributo:");



        JTextField NombreAtributoTextField = new JTextField();
        JTextField valorTextField = new JTextField();


        ventanaAct.add(NombreAtributoLabel);
        ventanaAct.add(NombreAtributoTextField);
        ventanaAct.add(valorLabel);
        ventanaAct.add(valorTextField);


        JLabel atributoCondicion = new JLabel ("Ingrese el numero del pedido: ");
        JTextField atributoTextField = new JTextField();





        // Crear botón para confirmar la selección
        JButton confirmarButton = new JButton("Actualizar");

        // Configurar el layout
        ventanaAct.setLayout(new GridLayout(6, 2));

        ventanaAct.add(NombreAtributoLabel);
        ventanaAct.add(NombreAtributoTextField);
        ventanaAct.add(valorLabel);
        ventanaAct.add(valorTextField);
        ventanaAct.add(atributoCondicion);
        ventanaAct.add(atributoTextField);
        ventanaAct.add(confirmarButton);

        // Acción del botón de confirmar
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Atributo = NombreAtributoTextField.getText();
                Object NuevoValor = valorTextField.getText();
                String Condicion = "NumeroPedido";
                int AtrCondicion = Integer.parseInt(atributoTextField.getText());
                Act(tablaSeleccionada,Atributo,NuevoValor,Condicion,AtrCondicion);
                ventanaAct.dispose();


            }
        });

        // Hacer visible la ventana de inserción
        ventanaAct.setVisible(true);
        dispose();
    }



    public void Act(String NombreTabla, String Atributo, Object NuevoValor, String Condicion, int AtrCondicion){
        if(NuevoValor instanceof String){
              try {

                String consultaInsercion = "UPDATE " + NombreTabla + " SET "+Atributo+"='"+NuevoValor+"' WHERE " + Condicion+"="+AtrCondicion+";";



                try (PreparedStatement preparedStatement = connection.prepareStatement(consultaInsercion)) {
                    // Ejecutar la consulta de inserción
                    int filasAfectadas = preparedStatement.executeUpdate();

                    // Imprimir mensaje de éxito si se insertaron filas
                    if (filasAfectadas > 0) {
                        JOptionPane.showMessageDialog(null, "Datos actualizados con éxito en la tabla " +NombreTabla, "Informacion", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "No se actualizaron los datos de la tabla " +NombreTabla, "Error", JOptionPane.ERROR_MESSAGE);

                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Manejar la excepción según tus necesidades
                JOptionPane.showMessageDialog(null, "Error al actualizar datos en la tabla " +NombreTabla, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{ try {

                // Crear la consulta de inserción (ajusta según tu esquema)
                String consultaInsercion = "UPDATE " + NombreTabla + " SET " + Atributo + "=" + NuevoValor + " WHERE " + Condicion + "=" + AtrCondicion;


            try (PreparedStatement preparedStatement = connection.prepareStatement(consultaInsercion)) {
                // Ejecutar la consulta de inserción
                int filasAfectadas = preparedStatement.executeUpdate();

                // Imprimir mensaje de éxito si se insertaron filas
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(null, "Datos actualizados con éxito en la tabla " +NombreTabla, "Informacion", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "No se actualizaron los datos de la tabla " +NombreTabla, "Error", JOptionPane.ERROR_MESSAGE);

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejar la excepción según tus necesidades
            JOptionPane.showMessageDialog(null, "Error al eliminar datos en la tabla " +NombreTabla, "Error", JOptionPane.ERROR_MESSAGE);
        }}

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
