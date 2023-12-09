import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;


public class Insertar extends JFrame {
    private Connection connection;

    public void Insert(){
        establecerConexion();
        JFrame ventanaInsercion = new JFrame("Insertar Datos");
        ventanaInsercion.setSize(300, 200);
        ventanaInsercion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaInsercion.setLocationRelativeTo(null);



        // Crear el JComboBox con los nombres de las tablas
        String[] opcionesTablas = {"UniformeColegio", "PrendaVestir", "Proveedor", "MateriaPrima", "Cliente", "TelCliente", "Encargo"};
        JComboBox<String> tablaComboBox = new JComboBox<>(opcionesTablas);

        String tablaSeleccionada = tablaComboBox.getSelectedItem().toString();



        // Crear botón para confirmar la selección
        JButton confirmarButton = new JButton("Confirmar");

        // Configurar el layout
        ventanaInsercion.setLayout(new GridLayout(3, 1));

        // Agregar componentes al panel
        ventanaInsercion.add(new JLabel("Seleccione una tabla:"));
        ventanaInsercion.add(tablaComboBox);
        ventanaInsercion.add(confirmarButton);

        // Acción del botón de confirmar
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Objects.equals(tablaSeleccionada, "UniformeColegio")){
                    System.out.println("Conexión kk");
                    UniformeColegio();
                    ventanaInsercion.dispose();

                }



            }
        });

        // Hacer visible la ventana de inserción
        ventanaInsercion.setVisible(true);
        dispose();
    }



    public void  UniformeColegio(){
        JFrame ventanaUniforme = new JFrame("Uniforme Colegio");
        ventanaUniforme.setSize(500, 500);
        ventanaUniforme.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaUniforme.setLocationRelativeTo(null);

        JLabel CodigoUniformeLabel = new JLabel("Codigo de uniforme:");
        JLabel TipoPrendaLabel = new JLabel("Tipo de prenda:");
        JLabel NombreColegioLabel = new JLabel("Nombre del colegio:");
        JLabel ColorLabel = new JLabel("Color del uniforme:");
        JLabel TipoTelaLabel = new JLabel("Tipo de tela:");
        JLabel BordadoLabel = new JLabel("Tiene bordado? (true/false):");
        JLabel UbicacionBordadoLabel = new JLabel("Ubicación del bordado:");
        JLabel EstampadoLabel = new JLabel("Tiene estampado? (true/false):");
        JLabel BordesMangasLabel = new JLabel("Color de bordes de la manga:");
        JLabel BordesCuelloLabel = new JLabel("Color de bordes del cuello:");

        JTextField CodigoUniformeTextField = new JTextField();
        JTextField TipoPrendaTextField = new JTextField();
        JTextField NombreColegioTextField = new JTextField();
        JTextField ColorTextField = new JTextField();
        JTextField TipoTelaTextField = new JTextField();
        JTextField BordadoTextField = new JTextField();
        JTextField UbicacionBordadoTextField = new JTextField();
        JTextField EstampadoTextField = new JTextField();
        JTextField BordesMangasTextField = new JTextField();
        JTextField BordesCuelloTextField = new JTextField();

        JButton InsertarButton = new JButton("Insertar");

        ventanaUniforme.setLayout(new GridLayout(12, 2));



        ventanaUniforme.add(CodigoUniformeLabel);
        ventanaUniforme.add(CodigoUniformeTextField);
        ventanaUniforme.add(TipoPrendaLabel);
        ventanaUniforme.add(TipoPrendaTextField);
        ventanaUniforme.add(NombreColegioLabel);
        ventanaUniforme.add(NombreColegioTextField);
        ventanaUniforme.add(ColorLabel);
        ventanaUniforme.add(ColorTextField);
        ventanaUniforme.add(TipoTelaLabel);
        ventanaUniforme.add(TipoTelaTextField);
        ventanaUniforme.add(BordadoLabel);
        ventanaUniforme.add(BordadoTextField);
        ventanaUniforme.add(UbicacionBordadoLabel);
        ventanaUniforme.add(UbicacionBordadoTextField);
        ventanaUniforme.add(EstampadoLabel);
        ventanaUniforme.add(EstampadoTextField);
        ventanaUniforme.add(BordesMangasLabel);
        ventanaUniforme.add(BordesMangasTextField);
        ventanaUniforme.add(BordesCuelloLabel);
        ventanaUniforme.add(BordesCuelloTextField);
        ventanaUniforme.add(InsertarButton);




        InsertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String CodigoUniforme = CodigoUniformeTextField.getText();
                String TipoPrenda = TipoPrendaTextField.getText();
                String NombreColegio = NombreColegioTextField.getText();
                String Color = ColorTextField.getText();
                String TipoTela = TipoTelaTextField.getText();
                Boolean Bordado = Boolean.valueOf(BordadoTextField.getText());
                String UbicacionBordado = UbicacionBordadoTextField.getText();
                Boolean Estampado = Boolean.valueOf(EstampadoTextField.getText());
                String BordesMangas = BordesMangasTextField.getText();
                String BordesCuello = BordesCuelloTextField.getText();

                String NombreTabla ="UniformeColegio";
                String values = "("+CodigoUniforme+",'"+TipoPrenda+"','"+NombreColegio+"','"+Color+"','"+TipoTela+"',"+Bordado+",'"+UbicacionBordado+"',"+
                        Estampado+",'"+BordesMangas+"','"+BordesCuello+"');";
                System.out.println(values);
                InsertInto(NombreTabla, values);
            }
        });

        ventanaUniforme.setVisible(true);
        dispose();


    }



    public void PrendaVestir(){}

    public void Proveedor(){}

    public void MateriaPrima(){}
    public void Cliente(){}

    public void TelCliente(){}

    public void Encargo(){}

    public void InsertInto(String Nombretabla, String values){
        try {

            // Crear la consulta de inserción (ajusta según tu esquema)
            String consultaInsercion = "INSERT INTO " + Nombretabla + " VALUES " + values;
            System.out.println(consultaInsercion);


            try (PreparedStatement preparedStatement = connection.prepareStatement(consultaInsercion)) {
                // Ejecutar la consulta de inserción
                int filasAfectadas = preparedStatement.executeUpdate();

                // Imprimir mensaje de éxito si se insertaron filas
                if (filasAfectadas > 0) {
                    System.out.println("Datos insertados con éxito en la tabla " + Nombretabla);
                } else {
                    System.out.println("No se insertaron datos en la tabla " + Nombretabla);
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejar la excepción según tus necesidades
                JOptionPane.showMessageDialog(null, "Error al insertar datos en la tabla " +Nombretabla, "Error", JOptionPane.ERROR_MESSAGE);
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
