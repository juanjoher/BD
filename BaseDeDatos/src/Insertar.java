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
    private JComboBox<String> tablaComboBox;
    private Connection connection;

    public void Insert(){
        establecerConexion();
        JFrame ventanaInsercion = new JFrame("Insertar Datos");
        ventanaInsercion.setSize(300, 200);
        ventanaInsercion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaInsercion.setLocationRelativeTo(null);


        tablaComboBox = new JComboBox<>(new String[]{"UniformeColegio", "PrendaVestir", "Proveedor", "MateriaPrima", "Cliente", "TelCliente", "Encargo"});






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
                String tablaSeleccionada = tablaComboBox.getSelectedItem().toString();

                if (Objects.equals(tablaSeleccionada, "UniformeColegio")){

                    UniformeColegio();
                    ventanaInsercion.dispose();

                } else if(Objects.equals(tablaSeleccionada, "PrendaVestir")){
                    PrendaVestir();
                    ventanaInsercion.dispose();
                }else if(Objects.equals(tablaSeleccionada, "Proveedor")){
                    Proveedor();
                    ventanaInsercion.dispose();
                }else if(Objects.equals(tablaSeleccionada, "MateriaPrima")){
                    MateriaPrima();
                    ventanaInsercion.dispose();
                }else if(Objects.equals(tablaSeleccionada, "Cliente")){
                    Cliente();
                    ventanaInsercion.dispose();
                }else if(Objects.equals(tablaSeleccionada, "TelCliente")){
                    TelCliente();
                    ventanaInsercion.dispose();
                }else if(Objects.equals(tablaSeleccionada, "Encargo")){
                    Encargo();
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

                int CodigoUniforme = Integer.parseInt(CodigoUniformeTextField.getText());
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
                ventanaUniforme.dispose();
            }
        });

        ventanaUniforme.setVisible(true);
        dispose();


    }



    public void PrendaVestir(){
        JFrame ventanaPrenda = new JFrame("Prenda de vestir");
        ventanaPrenda.setSize(500, 500);
        ventanaPrenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaPrenda.setLocationRelativeTo(null);

        JLabel CodigoPrendaLabel = new JLabel("Codigo de prenda:");
        JLabel DescripcionLabel = new JLabel("Descripcion:");
        JLabel TallaLabel = new JLabel("Talla:");
        JLabel SexoLabel = new JLabel("Sexo:");
        JLabel CantidadLabel = new JLabel("Cantidad:");
        JLabel PrecioVentaLabel = new JLabel("Precio:");


        JTextField CodigoPrendaTextField = new JTextField();
        JTextField DescripcionTextField = new JTextField();
        JTextField TallaTextField = new JTextField();
        JTextField SexoTextField = new JTextField();
        JTextField CantidadTextField = new JTextField();
        JTextField PrecioVentaTextField = new JTextField();


        JButton InsertarButton = new JButton("Insertar");

        ventanaPrenda.setLayout(new GridLayout(8, 2));



        ventanaPrenda.add(CodigoPrendaLabel);
        ventanaPrenda.add(CodigoPrendaTextField);
        ventanaPrenda.add(DescripcionLabel);
        ventanaPrenda.add(DescripcionTextField);
        ventanaPrenda.add(TallaLabel);
        ventanaPrenda.add(TallaTextField);
        ventanaPrenda.add(SexoLabel);
        ventanaPrenda.add(SexoTextField);
        ventanaPrenda.add(CantidadLabel);
        ventanaPrenda.add(CantidadTextField);
        ventanaPrenda.add(PrecioVentaLabel);
        ventanaPrenda.add(PrecioVentaTextField);
        ventanaPrenda.add(InsertarButton);




        InsertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int CodigoPrenda = Integer.parseInt(CodigoPrendaTextField.getText());
                String Descripcion = DescripcionTextField.getText();
                String Talla = TallaTextField.getText();
                String Sexo = SexoTextField.getText();
                int Cantidad = Integer.parseInt(CantidadTextField.getText());
                int TipoTela = Integer.parseInt(PrecioVentaTextField.getText());


                String NombreTabla ="PrendaVestir";
                String values = "("+CodigoPrenda+",'"+Descripcion+"','"+Talla+"','"+Sexo+"',"+Cantidad+","+TipoTela+ ");";
                System.out.println(values);
                InsertInto(NombreTabla, values);
                ventanaPrenda.dispose();
            }
        });

        ventanaPrenda.setVisible(true);
        dispose();
    }

    public void Proveedor(){


        JFrame ventanaProveedor = new JFrame("Proveedor");
        ventanaProveedor.setSize(500, 500);
        ventanaProveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaProveedor.setLocationRelativeTo(null);

        JLabel NitLabel = new JLabel("Nit:");
        JLabel NombreLabel = new JLabel("Nombre del proveedor:");
        JLabel DireccionLabel = new JLabel("Direccion:");
        JLabel ContactoLabel = new JLabel("Nombre del contacto:");




        JTextField NitTextField = new JTextField();
        JTextField NombreTextField = new JTextField();
        JTextField DireccionTextField = new JTextField();
        JTextField ContactoTextField = new JTextField();




        JButton InsertarButton = new JButton("Insertar");

        ventanaProveedor.setLayout(new GridLayout(6, 2));



        ventanaProveedor.add(NitLabel);
        ventanaProveedor.add(NitTextField);
        ventanaProveedor.add(NombreLabel);
        ventanaProveedor.add(NombreTextField);
        ventanaProveedor.add(DireccionLabel);
        ventanaProveedor.add(DireccionTextField);
        ventanaProveedor.add(ContactoLabel);
        ventanaProveedor.add(ContactoTextField);

        ventanaProveedor.add(InsertarButton);




        InsertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int Nit = Integer.parseInt(NitTextField.getText());
                String nombre = NombreTextField.getText();
                String Direccion = DireccionTextField.getText();
                String contacto = ContactoTextField.getText();



                String NombreTabla ="PrendaVestir";
                String values = "("+Nit+",'"+nombre+"','"+Direccion+"','"+contacto+"');";
                System.out.println(values);
                InsertInto(NombreTabla, values);
                ventanaProveedor.dispose();
            }
        });

        ventanaProveedor.setVisible(true);
        dispose();


    }

    public void MateriaPrima(){


        JFrame ventanaMateria = new JFrame("Prenda de vestir");
        ventanaMateria.setSize(500, 500);
        ventanaMateria.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaMateria.setLocationRelativeTo(null);

        JLabel CodigoMateriaLabel = new JLabel("Codigo de la materia prima:");
        JLabel NitLabel = new JLabel("Nit:");
        JLabel TipoLabel = new JLabel("Tipo:");
        JLabel DescripcionLabel = new JLabel("Descripcion:");
        JLabel CantidadLabel = new JLabel("Cantidad:");
        JLabel MedidaCantidadLabel = new JLabel("Medida:");


        JTextField CodigoMateriaTextField = new JTextField();
        JTextField NitTextField = new JTextField();
        JTextField TipoTextField = new JTextField();
        JTextField DescripcionTextField = new JTextField();
        JTextField CantidadTextField = new JTextField();
        JTextField MedidaCantidadTextField = new JTextField();


        JButton InsertarButton = new JButton("Insertar");

        ventanaMateria.setLayout(new GridLayout(8, 2));



        ventanaMateria.add(CodigoMateriaLabel);
        ventanaMateria.add(CodigoMateriaTextField);
        ventanaMateria.add(NitLabel);
        ventanaMateria.add(NitTextField);
        ventanaMateria.add(TipoLabel);
        ventanaMateria.add(TipoTextField);
        ventanaMateria.add(DescripcionLabel);
        ventanaMateria.add(DescripcionTextField);
        ventanaMateria.add(CantidadLabel);
        ventanaMateria.add(CantidadTextField);
        ventanaMateria.add(MedidaCantidadLabel);
        ventanaMateria.add(MedidaCantidadTextField);
        ventanaMateria.add(InsertarButton);





        InsertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int CodigoMateria = Integer.parseInt(CodigoMateriaTextField.getText());
                int Nit = Integer.parseInt(NitTextField.getText());
                String Tipo = TipoTextField.getText();
                String Descripcion = DescripcionTextField.getText();
                int Cantidad = Integer.parseInt(CantidadTextField.getText());
                int Medida = Integer.parseInt(MedidaCantidadTextField.getText());


                String NombreTabla ="PrendaVestir";
                String values = "("+CodigoMateria+","+Nit+",'"+Tipo+"','"+Descripcion+"',"+Cantidad+","+Medida+ ");";
                System.out.println(values);
                InsertInto(NombreTabla, values);
                ventanaMateria.dispose();
            }
        });

        ventanaMateria.setVisible(true);
        dispose();


    }
    public void Cliente(){

        JFrame ventanaCliente = new JFrame("Ingresar cliente");
        ventanaCliente.setSize(500, 500);
        ventanaCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaCliente.setLocationRelativeTo(null);

        JLabel IdLabel = new JLabel("Id del cliente:");
        JLabel NombreLabel = new JLabel("Nombre del cliente:");


        JTextField IdTextField = new JTextField();
        JTextField NombreTextField = new JTextField();


        JButton InsertarButton = new JButton("Insertar");

        ventanaCliente.setLayout(new GridLayout(6, 2));



        ventanaCliente.add(IdLabel);
        ventanaCliente.add(IdTextField);
        ventanaCliente.add(NombreLabel);
        ventanaCliente.add(NombreTextField);
        ventanaCliente.add(InsertarButton);




        InsertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int Id = Integer.parseInt(IdTextField.getText());
                String nombre = NombreTextField.getText();



                String NombreTabla ="Cliente";
                String values = "("+Id+",'"+nombre+"');";
                System.out.println(values);
                InsertInto(NombreTabla, values);
                ventanaCliente.dispose();
            }
        });

        ventanaCliente.setVisible(true);
        dispose();


    }

    public void TelCliente(){

        JFrame ventanaTel = new JFrame("Ingresar cliente");
        ventanaTel.setSize(500, 500);
        ventanaTel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaTel.setLocationRelativeTo(null);

        JLabel IdLabel = new JLabel("Id del cliente:");
        JLabel TelefonoLabel = new JLabel("Telefono del cliente:");


        JTextField IdTextField = new JTextField();
        JTextField TelefonoTextField = new JTextField();


        JButton InsertarButton = new JButton("Insertar");

        ventanaTel.setLayout(new GridLayout(6, 2));



        ventanaTel.add(IdLabel);
        ventanaTel.add(IdTextField);
        ventanaTel.add(TelefonoLabel);
        ventanaTel.add(TelefonoTextField);
        ventanaTel.add(InsertarButton);




        InsertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int Id = Integer.parseInt(IdTextField.getText());
                String Telefono = TelefonoTextField.getText();



                String NombreTabla ="TelCliente";
                String values = "("+Id+",'"+Telefono+"');";
                System.out.println(values);
                InsertInto(NombreTabla, values);
                ventanaTel.dispose();
            }
        });

        ventanaTel.setVisible(true);
        dispose();

    }

    public void Encargo(){
        JFrame ventanaEncargo = new JFrame("Encargo");
        ventanaEncargo.setSize(500, 500);
        ventanaEncargo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaEncargo.setLocationRelativeTo(null);




        JLabel NumeroPedidoLabel = new JLabel("Numero de pedido:");
        JLabel MedidasLabel = new JLabel("Medidas:");
        JLabel FechaLabel = new JLabel("Fecha(yyyy-MM-dd):");
        JLabel AbonoLabel = new JLabel("Abono:");
        JLabel FechaProbEntregaLabel = new JLabel("Fecha probable de entrega(yyyy-MM-dd):");
        JLabel PrecioTotalLabel = new JLabel("Precio total:");
        JLabel IdClienteLabel = new JLabel("Id del cliente:");
        JLabel CodigoPrendaLabel = new JLabel("Codigo de prenda:");
        JLabel CodigoUniformeLabel = new JLabel("Codigo del uniforme:");
        JLabel EstadoEntregaLabel = new JLabel("Estado de entrega:");

        JTextField NumeroPedidoTextField = new JTextField();
        JTextField MedidasTextField = new JTextField();
        JTextField FechaTextField = new JTextField();
        JTextField AbonoTextField = new JTextField();
        JTextField FechaProbEntregaTextField = new JTextField();
        JTextField PrecioTotalTextField = new JTextField();
        JTextField IdClienteTextField = new JTextField();
        JTextField CodigoPrendaTextField = new JTextField();
        JTextField CodigoUniformeTextField = new JTextField();
        JTextField EstadoEntregaTextField = new JTextField();

        JButton InsertarButton = new JButton("Insertar");

        ventanaEncargo.setLayout(new GridLayout(12, 2));



        ventanaEncargo.add(NumeroPedidoLabel);
        ventanaEncargo.add(NumeroPedidoTextField);
        ventanaEncargo.add(MedidasLabel);
        ventanaEncargo.add(MedidasTextField);
        ventanaEncargo.add(FechaLabel);
        ventanaEncargo.add(FechaTextField);
        ventanaEncargo.add(AbonoLabel);
        ventanaEncargo.add(AbonoTextField);
        ventanaEncargo.add(FechaProbEntregaLabel);
        ventanaEncargo.add(FechaProbEntregaTextField);
        ventanaEncargo.add(PrecioTotalLabel);
        ventanaEncargo.add(PrecioTotalTextField);
        ventanaEncargo.add(IdClienteLabel);
        ventanaEncargo.add(IdClienteTextField);
        ventanaEncargo.add(CodigoPrendaLabel);
        ventanaEncargo.add(CodigoPrendaTextField);
        ventanaEncargo.add(CodigoUniformeLabel);
        ventanaEncargo.add(CodigoUniformeTextField);
        ventanaEncargo.add(EstadoEntregaLabel);
        ventanaEncargo.add(EstadoEntregaTextField);
        ventanaEncargo.add(InsertarButton);




        InsertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {





                int NumeroPedido = Integer.parseInt(NumeroPedidoTextField.getText());
                String Medidas = MedidasTextField.getText();
                String Fecha = (FechaTextField.getText());
                int Abono = Integer.parseInt(AbonoTextField.getText());
                String FechaProbEntrega = (FechaProbEntregaTextField.getText());
                int PrecioTotal = Integer.parseInt((PrecioTotalTextField.getText()));
                int IdCliente = Integer.parseInt(IdClienteTextField.getText());
                int CodigoPrenda = Integer.parseInt((CodigoPrendaTextField.getText()));
                int CodigoUniforme = Integer.parseInt(CodigoUniformeTextField.getText());
                String EstadoEntrega = EstadoEntregaTextField.getText();



                String NombreTabla ="Encargo";
                String values = "("+NumeroPedido+",'"+Medidas+"','"+Fecha+"',"+Abono+",'"+FechaProbEntrega+"',"+PrecioTotal+","+IdCliente+","+
                        CodigoPrenda+","+CodigoUniforme+",'"+EstadoEntrega+"');";
                System.out.println(values);
                InsertInto(NombreTabla, values);
                ventanaEncargo.dispose();
            }
        });

        ventanaEncargo.setVisible(true);
        dispose();
    }

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
                    JOptionPane.showMessageDialog(null, "Datos insertados con éxito en la tabla " +Nombretabla, "Informacion", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se eliminaron datos de la tabla " +Nombretabla, "Error", JOptionPane.ERROR_MESSAGE);

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
