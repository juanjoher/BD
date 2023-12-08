
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.sql.*;

public class InterfazInicioSesion extends JFrame {

    private JComboBox<String> tipoUsuarioComboBox;
    private JTextField usuarioTextField;
    private JPasswordField contrasenaPasswordField;

    private Map<String, String> usuarios = new HashMap<>();

    // Configuración de la conexión a PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/PRINCIPALBD";
    private static final String USUARIO_BD = "POSTGRES";
    private static final String CONTRASENA_BD = "1234";

    public InterfazInicioSesion() {
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
    private void cargarUsuariosDesdeBD() {
        try {
            // Cargar el controlador JDBC
            Class.forName("org.postgresql.Driver");

            // Establecer la conexión a la base de datos
            try (Connection conexion = DriverManager.getConnection(URL, USUARIO_BD, CONTRASENA_BD);
                 Statement statement = conexion.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM public.administracion")) {

                while (resultSet.next()) {
                    String usuario = resultSet.getString("usuario");
                    String contrasena = resultSet.getString("contrasena");
                    String tipoUsuario = resultSet.getString("tipo_usuario");

                    usuarios.put(usuario, contrasena);
                    usuarios.put(usuario + "_tipo", tipoUsuario);
                }

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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
        ventanaAdministrador.setSize(500, 500);
        ventanaAdministrador.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAdministrador.setLocationRelativeTo(null);

        JButton crearUsuarioButton = new JButton("Crear Usuario");
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");
        JButton productosEncargadosPendientesButton = new JButton("Productos Encargados Pendientes");
        JButton productosEncargadosNoEntregadosButton = new JButton("Productos Encargados No Entregados");
        JButton cantidadEnExistenciaButton = new JButton("Cantidad en Existencia");
        JButton estadoDeColegiosButton = new JButton("Listado de Colegios");
        JButton caracteristicasUniformeColegioButton = new JButton("Características de Uniforme Colegio");
        JButton registroButton = new JButton("Registro, Consulta, Modificación y eliminación");
        JButton totalProductosVendidosButton = new JButton("Total Productos Vendidos");
        JButton totalVentasButton = new JButton("Total Ventas");


        // Crear panel para los botones en la parte superior
        JPanel panelBotones = new JPanel(new GridLayout(5, 2));
        panelBotones.add(crearUsuarioButton);
        panelBotones.add(cerrarSesionButton);
        panelBotones.add(productosEncargadosPendientesButton);
        panelBotones.add(productosEncargadosNoEntregadosButton);
        panelBotones.add(cantidadEnExistenciaButton);
        panelBotones.add(estadoDeColegiosButton);
        panelBotones.add(caracteristicasUniformeColegioButton);
        panelBotones.add(registroButton);
        panelBotones.add(totalProductosVendidosButton);
        panelBotones.add(totalVentasButton);



        // Añadir el panel de botones al norte
        ventanaAdministrador.add(panelBotones, BorderLayout.NORTH);

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

        // Crear panel para la pantalla grande
        JTextArea pantallaGrande = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(pantallaGrande);
        pantallaGrande.setEditable(false);

        // Añadir el panel de pantalla grande al centro
        ventanaAdministrador.add(scrollPane, BorderLayout.CENTER);

        ventanaAdministrador.setVisible(true);
        dispose();
    }

    private void abrirInterfazVendedor() {
        JFrame ventanaVendedor = new JFrame("Panel de Vendedor");
        ventanaVendedor.setSize(500, 500);
        ventanaVendedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaVendedor.setLocationRelativeTo(null);


        JButton cerrarSesionButton = new JButton("Cerrar Sesión");
        JButton productosEncargadosPendientesButton = new JButton("Productos Encargados Pendientes");
        JButton productosEncargadosNoEntregadosButton = new JButton("Productos Encargados No Entregados");
        JButton cantidadEnExistenciaButton = new JButton("Cantidad en Existencia");
        JButton estadoDeColegiosButton = new JButton("Listado de Colegios");
        JButton caracteristicasUniformeColegioButton = new JButton("Características de Uniforme Colegio");
        JButton registroButton = new JButton("Registro, Consulta, Modificación y eliminación");


        // Crear panel para los botones en la parte superior
        JPanel panelBotones = new JPanel(new GridLayout(5, 2));
        panelBotones.add(cerrarSesionButton);
        panelBotones.add(productosEncargadosPendientesButton);
        panelBotones.add(productosEncargadosNoEntregadosButton);
        panelBotones.add(cantidadEnExistenciaButton);
        panelBotones.add(estadoDeColegiosButton);
        panelBotones.add(caracteristicasUniformeColegioButton);
        panelBotones.add(registroButton);




        // Añadir el panel de botones al norte
        ventanaVendedor.add(panelBotones, BorderLayout.NORTH);



        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAInicio();
                ventanaVendedor.dispose();
            }
        });

        // Crear panel para la pantalla grande
        JTextArea pantallaGrande = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(pantallaGrande);
        pantallaGrande.setEditable(false);

        // Añadir el panel de pantalla grande al centro
        ventanaVendedor.add(scrollPane, BorderLayout.CENTER);

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
