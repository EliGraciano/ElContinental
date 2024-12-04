package Continental.vista.consola;

import Continental.controlador.Controlador;
import Continental.interfaces.IVista;

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
    private JTextField inputFieldMenu;
    private JButton enviarButtonMenu;

    //entrada registro jugador
    private JTextField inputFieldNombreJugador;
    private JButton enviarButtonJugador;

    //entrada turno del jugador
    private JTextField inputTurnoJugador; // mostrar array de cartas
    private JButton enviarButtonTurnoJugador;

    //entrada descarte carta(indice)
    private JTextField inputDescarteCarta; // mostrar array de cartas
    private JButton enviarButtonDescarteCarta;

    // entrada fuera de turno
    private JTextField inputfueraDeTurnoJugador; // mostrar array de cartas
    private JButton enviarButtonFueraDeTurnoJugador;

    // entrada juego bajado
    private JTextField inputJuegoBajadoJugador; // mostrar array de cartas
    private JButton enviarButtonJuegoBajadoJugador;


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

        //ENTRADA MENU
        panelInput = new JPanel();
        this.enviarButtonMenu = new JButton("Enviar");
        this.inputFieldMenu = new JTextField();
        this.inputFieldMenu.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.inputFieldMenu.setBackground(Color.BLACK);
        this.inputFieldMenu.setForeground(Color.GREEN);
        panelInput.setLayout(new BorderLayout());
        panelInput.add(enviarButtonMenu,BorderLayout.WEST);
        panelInput.add(inputFieldMenu,BorderLayout.CENTER);

        //ENTRADA JUGADOR
        this.inputFieldNombreJugador = new JTextField();
        this.inputFieldNombreJugador.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.inputFieldNombreJugador.setBackground(Color.BLACK);
        this.inputFieldNombreJugador.setForeground(Color.GREEN);
        this.enviarButtonJugador = new JButton("Enviar");

        //ENTRADA TURNO JUGADOR
        this.inputTurnoJugador= new JTextField();
        this.inputTurnoJugador.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.inputTurnoJugador.setBackground(Color.BLACK);
        this.inputTurnoJugador.setForeground(Color.GREEN);
        this.enviarButtonTurnoJugador= new JButton("Enviar");

        //ENTRADA TURNO DESCARTE JUGADOR
        this.inputDescarteCarta= new JTextField();
        this.inputDescarteCarta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.inputDescarteCarta.setBackground(Color.BLACK);
        this.inputDescarteCarta.setForeground(Color.GREEN);
        this.enviarButtonDescarteCarta= new JButton("Enviar");

        //ENTRADA FUERA DE TURNO JUGADOR
        this.inputfueraDeTurnoJugador= new JTextField();
        this.inputfueraDeTurnoJugador.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.inputfueraDeTurnoJugador.setBackground(Color.BLACK);
        this.inputfueraDeTurnoJugador.setForeground(Color.GREEN);
        this.enviarButtonFueraDeTurnoJugador= new JButton("Enviar");

        //ENTRADA TURNO JUEGO BAJADO JUGADOR
        this.inputJuegoBajadoJugador= new JTextField();
        this.inputJuegoBajadoJugador.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.inputJuegoBajadoJugador.setBackground(Color.BLACK);
        this.inputJuegoBajadoJugador.setForeground(Color.GREEN);
        this.enviarButtonJuegoBajadoJugador= new JButton("Enviar");

        add(panelInput,BorderLayout.SOUTH);

        // Agregar acción al botón ENTRADA MENU
        this.enviarButtonMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputFieldMenu.getText();
                procesarComando(input);
                inputFieldMenu.setText("");  // Limpiar el campo de texto
            }
        });

        // Agregar acción al botón ENTRADA JUGADOR
        this.enviarButtonJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputDescarteCarta.getText();
                addjugador(input);
                inputDescarteCarta.setText("");  // Limpiar el campo de texto
            }
        });

        // Agregar acción al botón JUGADOR EN TURNO
        this.enviarButtonTurnoJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (inputTurnoJugador.getText().trim()){
                    case "1" -> { robarDelMazo(); }
                    case "2" -> { robarDelPozo(); }
                    case "3" -> { bajarJuegos(); } // tirar la exception de que si no puede bajar los juegos lo deje de nuevo elegir otra opcion
                    default -> { mostrarMensaje("opcion no valida");menuTurno();}
                }
                inputTurnoJugador.setText("");  // Limpiar el campo de texto
            }
        });

        // Agregar acción al botón DESCARTE DEL JUGADOR EN TURNO
        this.enviarButtonDescarteCarta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputDescarteCarta.getText().trim();
                descartarCarta(input);
                inputDescarteCarta.setText("");  // Limpiar el campo de texto
            }
        });

        // Agregar acción al botón FUERA DE TURNO JUGADOR
        this.enviarButtonFueraDeTurnoJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (inputfueraDeTurnoJugador.getText().trim()){
                    case "1" ->{robarFueraDeTurno();}
                    default -> {mostrarMensaje("opcion no valida");menuFueraDeTurno();}
                }
                inputfueraDeTurnoJugador.setText("");  // Limpiar el campo de texto
            }
        });

        // Agregar acción al botón TURNO JUEGO BAJADO JUGADOR
        this.enviarButtonJuegoBajadoJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (inputJuegoBajadoJugador.getText().trim()){
                    case "1" -> {}
                    case "2" -> {}
                    case "3" -> {}
                    case "4" -> {}
                    case "5" -> {}
                    default -> {mostrarMensaje("opcion no valida");menuJuegoBajado();}
                }
                inputJuegoBajadoJugador.setText("");  // Limpiar el campo de texto
            }
        });

        //TODO ME FALTA LA OPCION DE FUERA DE TURNO JUEGO BAJADO JUGADOR
        //TODO ME FALTA LA OPCION DE QUE SEA ULTIMA RONDA, Y SOLO PUEDAN UBICAR CARTAS(OTRO MENU DISTINTO)
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

    private void procesarComando(String input) {
        switch (input.trim()){
            case "1" -> {
                mostrarMensaje("Ingrese su Nombre: ");
                setPanelInput(TipoPanel.ENTRADA);
            }
            case "2" -> {
                reglas();
            }
            case "3" -> {
                iniciarRonda();
                mostrarCartasSolas();
            }
            case "0" -> {
                System.exit(0);
            }
            default -> {
                mostrarMensaje("Opcion Invalida");
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
        switch (tipo){
            case ENTRADA-> {
                panelInput.removeAll();
                panelInput.setLayout(new BorderLayout());
                panelInput.add(inputFieldNombreJugador,BorderLayout.CENTER);
                panelInput.add(enviarButtonJugador,BorderLayout.WEST);
                panelInput.updateUI();
            }
            case TURNO->{
                panelInput.removeAll();
                panelInput.setLayout(new BorderLayout());
                panelInput.add(enviarButtonTurnoJugador,BorderLayout.WEST);
                panelInput.add(inputTurnoJugador,BorderLayout.CENTER);
                panelInput.updateUI();
            }
            case FUERADETURNO->{
                panelInput.removeAll();
                panelInput.setLayout(new BorderLayout());
                panelInput.add(enviarButtonFueraDeTurnoJugador,BorderLayout.WEST);
                panelInput.add(inputfueraDeTurnoJugador,BorderLayout.CENTER);
                panelInput.updateUI();
            }
            case TURNODESCARTE->{
                panelInput.removeAll();
                panelInput.setLayout(new BorderLayout());
                panelInput.add(enviarButtonDescarteCarta,BorderLayout.WEST);
                panelInput.add(inputDescarteCarta,BorderLayout.CENTER);
                panelInput.updateUI();
            }
            case TURNOJUEGOBAJADO -> {
                panelInput.removeAll();
                panelInput.setLayout(new BorderLayout());
                panelInput.add(enviarButtonJuegoBajadoJugador,BorderLayout.WEST);
                panelInput.add(inputJuegoBajadoJugador,BorderLayout.CENTER);
                panelInput.updateUI();
            }

            case MENUINICIO -> {
                panelInput.removeAll();
                panelInput.setLayout(new BorderLayout());
                panelInput.add(enviarButtonMenu,BorderLayout.WEST);
                panelInput.add(inputFieldMenu,BorderLayout.CENTER);
                panelInput.updateUI();
            }

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
