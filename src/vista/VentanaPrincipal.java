package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import controlador.Controlador;

import javax.swing.JTextArea;


public class VentanaPrincipal {

	private JFrame frame;
	private JPanel panel_cabecera;
	private JPanel panel_central;
	private JLabel lblimg;
	private ImageIcon imgs;
	private ImageIcon ico;
	private JScrollPane scrollPane;
	Controlador controlador;
	private JTextArea texto;
	private JButton btnResetear;
	private JMapViewer mapa;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrincipal() {
		initialize();
	}

	private void initialize() {

		controlador = new Controlador();

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(500, 25, 1008, 765);
		frame.setTitle("CENSO 2022");
		frame.setIconImage(
		Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/archivos/Icono.png")));
		frame.getContentPane().setBackground(new Color(70, 164, 233));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		crearPanel_Cabecera();
		crearPanel_Central();
		crearTitulo();
		crearBotones();
		crearCentro();
		
		crearMapa();
		marcarManzanas();
		marcarRadioCensal();
		imagenDeFondo();

	}


	private void imagenDeFondo() {

		lblimg = new JLabel();
		lblimg.setBounds(0, 0, 1002, 736);
		lblimg.setBorder(null);
		lblimg.setForeground(Color.BLACK);
		imgs = new ImageIcon(getClass().getResource("/archivos/fondo.jpg"));
		ico = new ImageIcon(
		imgs.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(), Image.SCALE_SMOOTH));
		lblimg.setIcon(ico);
		frame.getContentPane().add(lblimg);
		
	}

	private void crearPanel_Cabecera() {
		frame.getContentPane().setLayout(null);
		panel_cabecera = new JPanel();
		panel_cabecera.setBounds(0, 0, 1002, 50);
		panel_cabecera.setBackground(new Color(70, 164, 233));
		panel_cabecera.setLayout(null);
		frame.getContentPane().add(panel_cabecera);
	}
	
	private void crearPanel_Central() {
		panel_central = new JPanel();
		panel_central.setBounds(20,80,543,538);
		panel_central.setBackground(new Color(79, 181, 229));
		frame.getContentPane().add(panel_central);
	}

	private void crearTitulo() {
		JLabel titulo = new JLabel("CENSO 2022");
		titulo.setBounds(272, 0, 500, 35);
		titulo.setBackground(Color.WHITE);
		titulo.setForeground(Color.white);
		titulo.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_cabecera.add(titulo);

	}

	private void crearBotones() {

		JButton btnAsignar = new JButton("Mostrar Asignacion");
		btnAsignar.setBounds(20, 668, 233, 36);
		frame.getContentPane().add(btnAsignar);

		asignarCensistas(btnAsignar);

		btnResetear = new JButton("Resetear asignacion");
		btnResetear.setBounds(279, 668, 233, 36);
		frame.getContentPane().add(btnResetear);
		btnResetear.setEnabled(false);
		
		comportamientoResetear(btnResetear);
	}

	private void crearCentro() {
		
		texto = new JTextArea();
		texto.setBounds(20,80, 400, 538);
		texto.setForeground(new Color(255, 255, 255));
		texto.setBackground(new Color(79, 181, 229));
		texto.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		texto.setLineWrap(true);
		texto.setEditable(false);
		mostrarCensistasManzanas(texto);
		panel_central.add(texto);
		
		scrollPane = new JScrollPane(panel_central);
		scrollPane.setBounds(20,80,493,540);
		frame.getContentPane().add(scrollPane);
		
	}
	
	private void comportamientoResetear(JButton resetear) {
		resetear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.reiniciarAsignacion();
				texto.setText(controlador.toString());
			}
			
		});
	}
	
	private void mostrarCensistasManzanas(JTextArea text) {
		text.setText(controlador.getNombreCensistasTotales().toString() 
				+ "--------------------------------------------" + "\n" + "\n" +
				controlador.getConjuntoManzanasTotales().toString());

	}
		
	private void asignarCensistas(JButton btnAsignar) {
			btnAsignar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				
					controlador.resolverPorHeuristica();
					texto.setText(controlador.toString());
					btnAsignar.setEnabled(false);
					btnResetear.setEnabled(true);
				
				}
				
			});
		}
	
	private void crearMapa() {
		mapa = new JMapViewer();
		mapa.setZoomContolsVisible(false);
		Coordinate coordinada=  new Coordinate (-34.518859,-58.703861);
		mapa.setDisplayPosition(coordinada, 16);
		Component map = mapa;
		mapa.setBounds(530, 80, 450, 540);
		frame.getContentPane().add(map);
	}

	private void marcarRadioCensal() {
		ArrayList<Coordinate> radioCensal = new ArrayList<Coordinate>();
		Coordinate punto1=  new Coordinate (-34.520410,-58.699938);
		Coordinate punto2=  new Coordinate (-34.522389,-58.701979);
		Coordinate punto3=  new Coordinate (-34.518413,-58.706976);
		Coordinate punto4=  new Coordinate (-34.516279,-58.704781);
		
		radioCensal.add(punto1);
		radioCensal.add(punto2);
		radioCensal.add(punto3);
		radioCensal.add(punto4);
		
		MapPolygonImpl poligono = new MapPolygonImpl(radioCensal);
		mapa.addMapPolygon(poligono);
	}

	private void marcarManzanas() {
		Coordinate manzana1=  new Coordinate (-34.520338,-58.700718);
		Coordinate manzana2=  new Coordinate (-34.521047,-58.701309);
		Coordinate manzana3=  new Coordinate (-34.521504,-58.702321);
		Coordinate manzana4=  new Coordinate (-34.519423,-58.701837);
		Coordinate manzana5=  new Coordinate (-34.520014,-58.702607);
		Coordinate manzana6=  new Coordinate (-34.520567,-58.703306);
		Coordinate manzana7=  new Coordinate (-34.518489,-58.702887);
		Coordinate manzana8=  new Coordinate (-34.519108,-58.703537);
		Coordinate manzana9=  new Coordinate (-34.519787,-58.704267);
		Coordinate manzana10=  new Coordinate (-34.517364,-58.704286);
		Coordinate manzana11=  new Coordinate (-34.518063,-58.704948);
		Coordinate manzana12=  new Coordinate (-34.518675,-58.705710);
		
		MapMarkerDot manzana =new MapMarkerDot("0",manzana1);
		manzana.getStyle().setBackColor(Color.red);
		
		mapa.addMapMarker(new MapMarkerDot("0",manzana1));
		mapa.addMapMarker(new MapMarkerDot("1",manzana2));
		mapa.addMapMarker(new MapMarkerDot("2",manzana3));
		mapa.addMapMarker(new MapMarkerDot("3",manzana4));
		mapa.addMapMarker(new MapMarkerDot("4",manzana5));
		mapa.addMapMarker(new MapMarkerDot("5",manzana6));
		mapa.addMapMarker(new MapMarkerDot("6",manzana7));
		mapa.addMapMarker(new MapMarkerDot("7",manzana8));
		mapa.addMapMarker(new MapMarkerDot("8",manzana9));
		mapa.addMapMarker(new MapMarkerDot("9",manzana10));
		mapa.addMapMarker(new MapMarkerDot("10",manzana11));
		mapa.addMapMarker(new MapMarkerDot("11",manzana12));
	}
}
