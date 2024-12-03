package Continental.vista.consola;

import Continental.controlador.Controlador;
import Continental.vista.IVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Consola extends JFrame implements IVista {
    private Controlador controlador;

    private JPanel panelPrincipal;
    private JScrollPane panelSalida;
    private JTextArea areaSalida;

    private JPanel panelInput;
    private JTextField inputField;
    private JTextField inputFieldNombreJugador;
    private JButton enviarButtonJugador;
    private JButton enviarButton;

    public Consola() {
        this.controlador = new Controlador(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setExtendedState(Frame.MAXIMIZED_BOTH);
        setTitle("El Continental");
        setSize(500,400);
        setLayout(new BorderLayout());
        //SALIDA
        this.areaSalida = new JTextArea();
        panelSalida = new JScrollPane(areaSalida);
        areaSalida.setBackground(Color.BLACK);
        areaSalida.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaSalida.setForeground(Color.GREEN);
        add(panelSalida,BorderLayout.CENTER);

        //ENTRADA
        panelInput = new JPanel();
        this.enviarButton = new JButton("Enviar");
        this.inputField = new JTextField();
        this.inputField.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.inputField.setBackground(Color.BLACK);
        this.inputField.setForeground(Color.GREEN);
        panelInput.setLayout(new BorderLayout());
        panelInput.add(enviarButton,BorderLayout.WEST);
        panelInput.add(inputField,BorderLayout.CENTER);

        //ENTRADA JUGADOR
        this.inputFieldNombreJugador = new JTextField();
        this.inputFieldNombreJugador.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.inputFieldNombreJugador.setBackground(Color.BLACK);
        this.inputFieldNombreJugador.setForeground(Color.GREEN);
        this.enviarButtonJugador = new JButton("Enviar");

        add(panelInput,BorderLayout.SOUTH);
        //this.setContentPane(panelPrincipal);
        // Agregar acción al botón
        this.enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                procesarComando(input);
                inputField.setText("");  // Limpiar el campo de texto
            }
        });

        this.enviarButtonJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputFieldNombreJugador.getText();
                addjugador(input);
                inputFieldNombreJugador.setText("");  // Limpiar el campo de texto
            }
        });

    }

    private void procesarComando(String input) {
        switch (input.trim()){
            case "1" -> {
                MostrarMensaje("Ingrese su Nombre: ");
                setPanelInput("entrada");
            }
            case "2" -> {

            }
            case "0" -> {
                System.exit(0);
            }

            default -> {
                MostrarMensaje("Opcion Invalida");
                menuInicio();
            }

        }

    }

    @Override
    public void iniciar() {
        setVisible(true);
        menuInicio();
    }

    @Override
    public void MostrarMensaje(String s) {
        areaSalida.append(s + "\n");
    }

    public void menuInicio(){
        areaSalida.append("------ MENU PRINCIPAL ---------\n" +
                "------ 1: agregar jugado ------ \n" +
                "------ 2: ver reglas ---------- \n" +
                "------ 3: comenzar juego ------ \n" +
                "------ 0: Salir --------------- \n"
        );
        setPanelInput("default");
    }


    private void addjugador(String nombre){
        controlador.registrarUsuario(nombre);
        menuInicio();
    }

    private void setPanelInput(String s){
        switch (s){
            case "entrada" -> {
                panelInput.removeAll();
                panelInput.setLayout(new BorderLayout());
                panelInput.add(inputFieldNombreJugador,BorderLayout.CENTER);
                panelInput.add(enviarButtonJugador,BorderLayout.WEST);
                panelInput.updateUI();
            }
            case "juego" ->{

            }
            default -> {
                panelInput.removeAll();
                panelInput.setLayout(new BorderLayout());
                panelInput.add(enviarButton,BorderLayout.WEST);
                panelInput.add(inputField,BorderLayout.CENTER);
                panelInput.updateUI();
            }

        }


    }


}