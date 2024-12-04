package Continental.vista.consola;

import Continental.controlador.Controlador;
import Continental.interfaces.IObservable;
import Continental.interfaces.IVista;
import Continental.modelo.juego.Mesa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Consola extends JFrame implements IVista {
    //TODO hacer que un jugador se registre cuando abre el juego(que le pida el nombre)
    private Controlador controlador;

    private JPanel panelPrincipal;
    private JScrollPane panelSalida;
    private JTextArea areaSalida;

    private JPanel panelInput;

    //entrada del menu
    private JTextField inputField;
    private JButton enviarButton;

    public Consola() {
        this.controlador = new Controlador(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setExtendedState(Frame.MAXIMIZED_BOTH);
        setTitle("El Continental");
        setSize(500,400);
        setLayout(new BorderLayout());

        //COLOR
        this.areaSalida = new JTextArea();
        panelSalida = new JScrollPane(areaSalida);
        areaSalida.setBackground(Color.BLACK);
        areaSalida.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaSalida.setForeground(Color.GREEN);
        add(panelSalida,BorderLayout.CENTER);

        //Color a los botones
        panelInput = new JPanel();
        this.enviarButton = new JButton("Enviar");
        this.inputField = new JTextField();
        this.inputField.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.inputField.setBackground(Color.BLACK);
        this.inputField.setForeground(Color.GREEN);
        panelInput.setLayout(new BorderLayout());
        panelInput.add(enviarButton,BorderLayout.WEST);
        panelInput.add(inputField,BorderLayout.CENTER);
        add(panelInput,BorderLayout.SOUTH);
        setPanelInput(TipoPanel.MENUINICIO);

    }
    // Agregar acción al botón ENTRADA MENU
    ActionListener menu = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (inputField.getText().trim()){
                case "1" -> {
                    mostrarMensaje("Ingrese su Nombre: ");
                    setPanelInput(TipoPanel.ENTRADA);
                    inputField.setText("");
                }
                case "2" -> {
                    reglas();
                    inputField.setText("");
                }
                case "3" -> {
                    iniciarRonda();
                    inputField.setText("");
                }
                case "0" -> {
                    System.exit(0);
                    inputField.setText("");
                }
                default -> {
                    mostrarMensaje("Opcion Invalida");
                    menuInicio();
                    inputField.setText("");
                }

            }
            inputField.setText("");  // Limpiar el campo de texto
        }
    };

    // Agregar acción al botón ENTRADA JUGADOR
    ActionListener entradaJugador = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addjugador(inputField.getText());
            inputField.setText("");  // Limpiar el campo de texto
        }
    };

    // Agregar acción al botón DESCARTE DEL JUGADOR EN TURNO
    ActionListener turnoDescarteJugador = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText().trim();
            descartarCarta(input);
            inputField.setText("");  // Limpiar el campo de texto
        }
    };

    // Agregar acción al botón FUERA DE TURNO JUGADOR
    ActionListener fueraDeTurno = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (inputField.getText().trim()) {
                case "1" -> {robarFueraDeTurno();}
                default -> {mostrarMensaje("opcion no valida");menuFueraDeTurno();}
            }
            inputField.setText("");  // Limpiar el campo de texto
        }
    };

    // Agregar acción al botón JUGADOR EN TURNO
    ActionListener jugadorEnTurno = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (inputField.getText().trim()) {
                case "1" -> {
                    robarDelMazo();
                }
                case "2" -> {
                    robarDelPozo();
                }
                case "3" -> {
                    bajarJuegos();
                } // tirar la exception de que si no puede bajar los juegos lo deje de nuevo elegir otra opcion
                default -> {
                    mostrarMensaje("opcion no valida");
                    menuTurno();
                }
            }
            inputField.setText("");  // Limpiar el campo de texto
        }
    };

    // Agregar acción al botón TURNO JUEGO BAJADO JUGADOR
    ActionListener turnoJuegoBajado = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (inputField.getText().trim()) {
                case "1" -> {}
                case "2" -> {}
                case "3" -> {}
                case "4" -> {}
                case "5" -> {}
                default -> {mostrarMensaje("opcion no valida");menuJuegoBajado();}
            }
            inputField.setText("");  // Limpiar el campo de texto
        }
    };



    public void setModelo(Mesa mesa){
        this.controlador.setModelo(mesa);
    }

    private void robarFueraDeTurno() {
        controlador.robarFueraDeTurnoJugador();
    }

    private void robarDelMazo() {
        //TODO agregar exception para que si el mazo no tiene mas cartas se pongan las del pozo o algo por el estilo
        controlador.robarMazo();
    }

    private void robarDelPozo() {
        controlador.robarPozo();
    }

    private void descartarCarta(String input) {
        try {
            controlador.descartarCarta(input);
        } catch (Exception e){
            mostrarMensaje(e.toString());
            menuDescarte();
        }
    }

    private void bajarJuegos() {
    }

    public void menuInicio(){
        mostrarMensaje("------ MENU PRINCIPAL ---------\n" +
                            "------ 1: agregar jugador ----- \n" +
                            "------ 2: ver reglas ---------- \n" +
                            "------ 3: comenzar juego ------ \n" +
                            "------ 0: Salir --------------- \n"
        );
        setPanelInput(TipoPanel.MENUINICIO);
    }

    public void menuTurno(){
        mostrarMensaje("------ Tu turno -------\n" +
                            "------ 1: robar del mazo ------ \n" +
                            "------ 2: robar del pozo ------ \n" +
                            "------ 3: bajar juegos -------- \n"
        );
        setPanelInput(TipoPanel.TURNO);
        mostrarCartasConMazoYConPozo();
        //mostrar mazo y pozo y su mano(para saber que robar y de donde conviene)
    }

    public void menuDescarte(){
        mostrarMensaje("------ Tu turno -------\n" +
                            "------ Seleccione la posicion de carta a descartar ------ \n"
                //mostrar la mano(y esperar un numero que sea el indice)
        );
        //crear panel para el menu de fuera de turno
        setPanelInput(TipoPanel.TURNODESCARTE);
        mostrarCartasSolas();
    }

    public void menuFueraDeTurno(){
        mostrarMensaje("------ fuera de turno -------\n" +
                            "------ 1: robar del pozo ------ \n"
        );
        setPanelInput(TipoPanel.FUERADETURNO);
        mostrarCartasConMazoYConPozo();
        // mostrar mazo y pozo
    }

    public void menuJuegoBajado(){
        mostrarMensaje("----- Tu turno -----------\n" +
                            "----- 1: robar del mazo -------- \n" +
                            "----- 2: robar del pozo -------- \n" +
                            "----- 3: ubicar carta ---------- \n" +
                            "----- 4: ubicar carta por mono - \n" +
                            "----- 5: saltar turno ---------- \n"
        );
        setPanelInput(TipoPanel.TURNOJUEGOBAJADO);
        mostrarCartasConMazoYConPozoYJuegosEnMesa();
    }

    private void mostrarCartasConMazoYConPozoYJuegosEnMesa() {
        mostrarCartasConMazoYConPozo();
        //mostrar solo los juegos en mesa
    }

    public void mostrarCartasSolas(){
        mostrarMensaje("Tus cartas son: ");
        mostrarMensaje(controlador.cartasManoUsuario().toString());
    }

    public void mostrarCartasConMazoYConPozo(){
        mostrarCartasSolas();
        mostrarMensaje(controlador.mostrarMazo());
        mostrarMensaje(controlador.mostrarPozo());
        //mostrar mazo y pozo
    }

    @Override
    public void iniciar() {
        setVisible(true);
        menuInicio();
    }

    @Override
    public void mostrarMensaje(String s) {
        areaSalida.append(s + "\n");
    }
    @Override
    public void jugarTurno(){
        menuTurno();
    }

    private void iniciarRonda(){
        try {
            controlador.inicarRonda();
            setPanelInput(TipoPanel.TURNO);
            mostrarCartasConMazoYConPozo();
        } catch (Exception e){
            mostrarMensaje(e.toString());
            menuInicio();
        }
    }

    private void addjugador(String nombre){
        controlador.registrarUsuario(nombre);
        menuInicio();
    }

    private void setPanelInput(TipoPanel tipo){
        //ELIMINAR TODOS LOS ACTION LSITENERS VIEJOS:
        for (ActionListener al : enviarButton.getActionListeners()) {
            enviarButton.removeActionListener(al);
        }
        switch (tipo){
            case ENTRADA-> {this.enviarButton.addActionListener(entradaJugador);}
            case TURNO->{this.enviarButton.addActionListener(jugadorEnTurno);}
            case FUERADETURNO->{this.enviarButton.addActionListener(fueraDeTurno);}
            case TURNODESCARTE->{this.enviarButton.addActionListener(turnoDescarteJugador);}
            case TURNOJUEGOBAJADO -> {this.enviarButton.addActionListener(turnoJuegoBajado);}
//          case FUERADETURNOJUEGOBAJADO ->{}
//          case TURNOULTIMARONDA ->{}
            case MENUINICIO -> {this.enviarButton.addActionListener(menu);}
        }
    }


    private void reglas() {
        mostrarMensaje("-------- El Continental Reglas ---------\n" +
                "-\tde 2 a 8 jugadores.\n" +
                "-\tObjetivo: conformar las combinaciones que se exigen en cada ronda en el menor numero de turnos posibles\n" +
                "-\tlos comodines no se pueden poner seguidos\n" +
                "-\tEn todos los turnos el jugador debe robar y pagar otra al pozo(puede tomar la del pozo, pero igual debe tirar)\n" +
                "-\tsi no es su turno y roba del pozo, debe agarrar tambien una del mazo en forma de castigo \n" +
                "-\tPara bajarse(tiene que ser su turno) se tienen que tener todas las combinaciones que exige la ronda completa. \n" +
                "-\tcuando un jugador se baja si no le queda ninguna carta,termina la ronda pero si le quedan cartas, \n" +
                "\tla ronda sigue pero no puede bajar mas juegos ni tampoco robar del pozo a menos que sea el turno del jugador de la izquierda.\n" +
                "-\tuna vez bajado sus juegos, el jugador tiene que ubicar sus cartas en sus juegos o el de los demás \n" +
                "----- RONDAS ----- \n" +
                "•\tRonda 1: 2 Tercias(escaleras de mismos numeros)  :  6 cartas\n" +
                "•\tRonda 2: 1 tercia y 1 escalera  :  7 cartas\n" +
                "•\tRonda 3: 2 escaleras    :   8 cartas\n" +
                "•\tRonda 4: 3 tercias   :   9 cartas\n" +
                "•\tRonda 5: 2 tercias y 1 escalera  :  10 cartas\n" +
                "•\tRonda 6: 1 tercia y 2 escaleras   : 11 cartas\n" +
                "•\tRonda 7: 3 escaleras   :   12 carta\n" +
                "------ VALOR DE CARTAS ------ \n" +
                "- \tDel 2 al 9 la carta vale su numero. \n" +
                "- \tJ; Q; K = 10. \n" +
                "- \tA = 20. \n" +
                "- \tMONO = 50. \n"
                );
        menuInicio();
    }
}
