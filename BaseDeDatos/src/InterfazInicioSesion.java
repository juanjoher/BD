
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.DriverManager;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;


public class InterfazInicioSesion extends JFrame {
    private Connection connection;

    private JComboBox<String> tipoUsuarioComboBox;
    private JTextField usuarioTextField;
    private JPasswordField contrasenaPasswordField;

    private Map<String, String> usuarios = new HashMap<>();




    public InterfazInicioSesion() {


        establecerConexion();

        // Configuración de la ventana de inicio de sesión
        setTitle("Inicio de Sesión");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cargar usuarios desde el archivo al iniciar la aplicación
        cargarUsuarios();

        // Crear componentes
        JLabel usuarioLabel = new JLabel("Usuario:");
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        usuarioTextField = new JTextField();
        contrasenaPasswordField = new JPasswordField();
        tipoUsuarioComboBox = new JComboBox<>(new String[]{"Administrador", "Vendedor"});

        JButton iniciarSesionButton = new JButton("Iniciar Sesión");

        // Configuración del layout
        setLayout(new GridLayout(4, 2));

        // Añadir componentes a la ventana
        add(usuarioLabel);
        add(usuarioTextField);
        add(contrasenaLabel);
        add(contrasenaPasswordField);
        add(new JLabel("Tipo de Usuario:"));
        add(tipoUsuarioComboBox);
        add(new JLabel()); // Espacio en blanco
        add(iniciarSesionButton);

        // Acción del botón de inicio de sesión
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
    }

    private void iniciarSesion() {
        String usuario = usuarioTextField.getText();
        String contrasena = new String(contrasenaPasswordField.getPassword());
        String tipoUsuario = (String) tipoUsuarioComboBox.getSelectedItem();

        if ("Administrador".equals(tipoUsuario) && "admin".equals(usuario) && "adminpass".equals(contrasena)) {
            abrirInterfazAdministrador();
        } else if ("Vendedor".equals(tipoUsuario) && "vendedor".equals(usuario) && "vendedorpass".equals(contrasena)) {
            abrirInterfazVendedor();
        }

        // Verificar las credenciales
        else if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(contrasena)
                && usuarios.get(usuario + "_tipo").equals(tipoUsuario)) {
            if ("Administrador".equals(tipoUsuario)) {
                abrirInterfazAdministrador();
            } else if ("Vendedor".equals(tipoUsuario)) {
                abrirInterfazVendedor();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirInterfazAdministrador() {
        JFrame ventanaAdministrador = new JFrame("Panel de Administrador");
        ventanaAdministrador.setSize(1000, 1000);
        ventanaAdministrador.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAdministrador.setLocationRelativeTo(null);

        JButton crearUsuarioButton = new JButton("Crear Usuario");
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");
        JButton productosEncargadosPendientesButton = new JButton("Productos Encargados Pendientes");
        JButton productosEncargadosNoEntregadosButton = new JButton("Productos Encargados No Entregados");
        JButton cantidadEnExistenciaButton = new JButton("Cantidad en Existencia");
        JButton listadoDeColegiosButton = new JButton("Listado de Colegios");
        JButton caracteristicasUniformeColegioButton = new JButton("Características de Uniforme Colegio");
        JButton registroButton = new JButton("Registro, Consulta, Modificación y eliminación");
        JButton totalProductosVendidosButton = new JButton("Total Productos Vendidos Por Colegio");
        JButton totalVentasButton = new JButton("Total Ventas");

        cerrarSesionButton.setBackground(Color.RED);
        cerrarSesionButton.setForeground(Color.WHITE);


        // Crear panel para los botones en la parte superior
        JPanel panelBotones = new JPanel(new GridLayout(10, 1));
        panelBotones.add(productosEncargadosPendientesButton);
        panelBotones.add(productosEncargadosNoEntregadosButton);
        panelBotones.add(cantidadEnExistenciaButton);
        panelBotones.add(listadoDeColegiosButton);
        panelBotones.add(caracteristicasUniformeColegioButton);
        panelBotones.add(totalProductosVendidosButton);
        panelBotones.add(totalVentasButton);
        panelBotones.add(crearUsuarioButton);
        panelBotones.add(registroButton);
        panelBotones.add(cerrarSesionButton);



        // Añadir el panel de botones al norte
        ventanaAdministrador.add(panelBotones, BorderLayout.WEST);

        crearUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuario();
            }
        });

        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAInicio();
                ventanaAdministrador.dispose();
            }
        });

        registroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaRegistro();
                ventanaAdministrador.dispose();
            }
        });



        // Crear panel para la pantalla grande
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable tablaBaseDatos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaBaseDatos);

        // Añadir el panel de pantalla grande al centro
        ventanaAdministrador.add(scrollPane, BorderLayout.CENTER);

        productosEncargadosPendientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EncargadosPendientes(tableModel);
            }
        });

        productosEncargadosNoEntregadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EncargadosNoEntregados(tableModel);
            }
        });

        cantidadEnExistenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExistenciaDescontando(tableModel);
            }
        });

        listadoDeColegiosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListadoColegios(tableModel);
            }
        });

        caracteristicasUniformeColegioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Caracteristicas(tableModel);
            }
        });

        totalProductosVendidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TotalPorColegio(tableModel);
            }
        });

        totalVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TotalVentas(tableModel);
            }
        });

        ventanaAdministrador.setVisible(true);
        dispose();
    }

    public void VentanaRegistro() {
        JFrame ventanaRegistro = new JFrame("Panel de Registro y eliminacion");
        ventanaRegistro.setSize(1000, 1000);
        ventanaRegistro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaRegistro.setLocationRelativeTo(null);

        JButton InsertarButton = new JButton("Insertar datos a tablas");
        JButton ActualizarButton = new JButton("Actualizar datos");
        JButton EliminarButton = new JButton("Eliminar datos");
        JButton FacturaButton = new JButton("Facturar");
        JButton TablasButton = new JButton("Consultar tablas");
        JButton AtrasButton = new JButton("Atras");
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");

        cerrarSesionButton.setBackground(Color.RED);
        cerrarSesionButton.setForeground(Color.WHITE);


        JPanel panelBotones = new JPanel(new GridLayout(7, 1));
        panelBotones.add(InsertarButton);
        panelBotones.add(ActualizarButton);
        panelBotones.add(EliminarButton);
        panelBotones.add(FacturaButton);
        panelBotones.add(TablasButton);
        panelBotones.add(AtrasButton);
        panelBotones.add(cerrarSesionButton);

        ventanaRegistro.add(panelBotones, BorderLayout.CENTER);

        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAInicio();
                ventanaRegistro.dispose();
            }
        });
        AtrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoUsuario = (String) tipoUsuarioComboBox.getSelectedItem();

                if ("Administrador".equals(tipoUsuario)){
                    abrirInterfazAdministrador();
                    ventanaRegistro.dispose();}else{
                    abrirInterfazVendedor();
                    ventanaRegistro.dispose();
                }

            }
        });

        InsertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaRegistro.dispose();
                Insertar Insertar = new Insertar();
                Insertar.Insert();
            }
        });

        ActualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaRegistro.dispose();
                Actualizar Actualizar = new Actualizar();
                Actualizar.Actualizar();

            }
        });

       EliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoUsuario = (String) tipoUsuarioComboBox.getSelectedItem();
                if (tipoUsuario=="Administrador"){
                ventanaRegistro.dispose();
                Delete Delete = new Delete();
                Delete.Delete();} else{
                    JOptionPane.showMessageDialog(null, "Acceso restringido, contacte al administrador para eliminar algun dato.","ERROR", JOptionPane.ERROR_MESSAGE);

                }

            }
        });
        FacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaRegistro.dispose();
                Factura Factura = new Factura();
                Factura.Factura();

            }
        });



        TablasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaRegistro.dispose();
                MostrarTablas Mostrar = new MostrarTablas();
                Mostrar.MostrarTablas();


            }
        });



        ventanaRegistro.setVisible(true);
        dispose();



    }

    private void abrirInterfazVendedor() {
        JFrame ventanaVendedor = new JFrame("Panel de Vendedor");
        ventanaVendedor.setSize(1000, 1000);
        ventanaVendedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaVendedor.setLocationRelativeTo(null);


        JButton cerrarSesionButton = new JButton("Cerrar Sesión");
        JButton productosEncargadosPendientesButton = new JButton("Productos Encargados Pendientes");
        JButton productosEncargadosNoEntregadosButton = new JButton("Productos Encargados No Entregados");
        JButton cantidadEnExistenciaButton = new JButton("Cantidad en Existencia");
        JButton listadoDeColegiosButton = new JButton("Listado de Colegios");
        JButton caracteristicasUniformeColegioButton = new JButton("Características de Uniforme Colegio");
        JButton registroButton = new JButton("Registro, Consulta, Modificación y eliminación");

        cerrarSesionButton.setBackground(Color.RED);

        cerrarSesionButton.setForeground(Color.WHITE);


        // Crear panel para los botones en la parte superior
        JPanel panelBotones = new JPanel(new GridLayout(7, 1));
        panelBotones.add(productosEncargadosPendientesButton);
        panelBotones.add(productosEncargadosNoEntregadosButton);
        panelBotones.add(cantidadEnExistenciaButton);
        panelBotones.add(listadoDeColegiosButton);
        panelBotones.add(caracteristicasUniformeColegioButton);
        panelBotones.add(registroButton);
        panelBotones.add(cerrarSesionButton);




        // Añadir el panel de botones al norte
        ventanaVendedor.add(panelBotones, BorderLayout.WEST);



        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAInicio();
                ventanaVendedor.dispose();
            }
        });

        // Crear panel para la pantalla grande
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable tablaBaseDatos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaBaseDatos);
        // Añadir el panel de pantalla grande al centro
        ventanaVendedor.add(scrollPane, BorderLayout.CENTER);

        productosEncargadosPendientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EncargadosPendientes(tableModel);
            }
        });

        productosEncargadosNoEntregadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EncargadosNoEntregados(tableModel);
            }
        });

        cantidadEnExistenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExistenciaDescontando(tableModel);
            }
        });

        listadoDeColegiosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ListadoColegios(tableModel);
            }
        });

        caracteristicasUniformeColegioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Caracteristicas(tableModel );
            }
        });

        registroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaRegistro();
                ventanaVendedor.dispose();
            }
        });





        ventanaVendedor.setVisible(true);
        dispose();
    }




    private void volverAInicio() {
        InterfazInicioSesion inicioSesion = new InterfazInicioSesion();
        inicioSesion.setVisible(true);
    }

    private void crearUsuario() {
        String nuevoUsuario = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre de usuario:");
        String nuevaContrasena = JOptionPane.showInputDialog(this, "Ingrese la nueva contraseña:");
        String nuevoTipoUsuario = (String) JOptionPane.showInputDialog(this, "Seleccione el tipo de usuario:",
                "Tipo de Usuario", JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Administrador", "Vendedor"}, "Vendedor");

        // Verificar si el usuario ya existe
        if (usuarios.containsKey(nuevoUsuario)) {
            JOptionPane.showMessageDialog(this, "El usuario ya existe. Por favor, elija otro nombre de usuario.",
                    "Error al crear usuario", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Guardar el nuevo usuario en el mapa y en el archivo de usuarios
        usuarios.put(nuevoUsuario, nuevaContrasena);
        usuarios.put(nuevoUsuario + "_tipo", nuevoTipoUsuario);
        guardarUsuarios();

        JOptionPane.showMessageDialog(this, "Usuario creado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cargarUsuarios() {
        try (Scanner scanner = new Scanner(new File("usuarios.txt"))) {
            while (scanner.hasNextLine()) {
                String[] partes = scanner.nextLine().split(",");
                if (partes.length == 3) {
                    usuarios.put(partes[0], partes[1]);
                    usuarios.put(partes[0] + "_tipo", partes[2]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void guardarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"))) {
            for (Map.Entry<String, String> entry : usuarios.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "," + usuarios.get(entry.getKey() + "_tipo"));
                writer.newLine();
            }
        } catch (IOException e) {
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
            String url = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;;


            connection = DriverManager.getConnection(url, usuario, contrasena);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void EncargadosPendientes(DefaultTableModel tableModel) {
        try (Statement statement = connection.createStatement()) {
            // Cambia "nombre_de_tu_vista" con el nombre de la vista que deseas mostrar
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PendientePorEntregar");

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

    private void EncargadosNoEntregados(DefaultTableModel tableModel) {
        try (Statement statement = connection.createStatement()) {
            // Cambia "nombre_de_tu_vista" con el nombre de la vista que deseas mostrar
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EncargadosNoEntregados");

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

    private void ExistenciaDescontando(DefaultTableModel tableModel) {
        try (Statement statement = connection.createStatement()) {
            // Cambia "nombre_de_tu_vista" con el nombre de la vista que deseas mostrar
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ExistenciaDescontando");

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

    private void ListadoColegios(DefaultTableModel tableModel) {
        try (Statement statement = connection.createStatement()) {
            // Cambia "nombre_de_tu_vista" con el nombre de la vista que deseas mostrar
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ListadoColegios");

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

    private void Caracteristicas(DefaultTableModel tableModel ) {

        try (Statement statement = connection.createStatement()) {
            String Colegio = JOptionPane.showInputDialog(this, "Ingrese el colegio a buscar:");
            // Cambia "nombre_de_tu_vista" con el nombre de la vista que deseas mostrar
            ResultSet resultSet = statement.executeQuery("SELECT * FROM VistaCaracteristicasUniformeColegio WHERE NombreColegio = "+"'"+Colegio+"'");

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

    private void TotalPorColegio(DefaultTableModel tableModel) {
        try (Statement statement = connection.createStatement()) {
            // Cambia "nombre_de_tu_vista" con el nombre de la vista que deseas mostrar
            ResultSet resultSet = statement.executeQuery("SELECT * FROM TotalPorColegio");

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

    private void TotalVentas(DefaultTableModel tableModel) {
        try (Statement statement = connection.createStatement()) {
            // Cambia "nombre_de_tu_vista" con el nombre de la vista que deseas mostrar
            ResultSet resultSet = statement.executeQuery("SELECT * FROM TotalVentas");

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

    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                InterfazInicioSesion interfaz = new InterfazInicioSesion();
                interfaz.setVisible(true);
            }
        });
    }
}
