import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Registro extends JFrame {
/*
    public void Insert(){
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
                Insertar Insertar = new Insertar();
                if (Objects.equals(tablaSeleccionada, "UniformeColegio")){
                    Uni
                    ventanaInsercion.dispose();

                }



            }
        });

        // Hacer visible la ventana de inserción
        ventanaInsercion.setVisible(true);
    }*/


    public void Eliminar(){}
    public void Actualizar(){}

}
