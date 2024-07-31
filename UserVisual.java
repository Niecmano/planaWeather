package accu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class UserVisual extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField zeljeniGrad;
	private JLabel lblTemperatura;
	private JLabel ispisTemp;
	private JButton saznajTemp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserVisual frame = new UserVisual();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserVisual() {
		setResizable(false);
		setTitle("PlanaWeather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 451);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getZeljeniGrad());
		contentPane.add(getLblTemperatura());
		contentPane.add(getIspisTemp());
		contentPane.add(getSaznajTemp());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Unesite zeljeni grad:");
			lblNewLabel.setForeground(new Color(0, 0, 153));
			lblNewLabel.setFont(new Font("Perpetua Titling MT", Font.BOLD, 25));
			lblNewLabel.setBounds(31, 51, 341, 51);
		}
		return lblNewLabel;
	}
	private JTextField getZeljeniGrad() {
		if (zeljeniGrad == null) {
			zeljeniGrad = new JTextField();
			zeljeniGrad.setFont(new Font("Tahoma", Font.PLAIN, 20));
			zeljeniGrad.setBounds(41, 115, 255, 32);
			zeljeniGrad.setColumns(10);
		}
		return zeljeniGrad;
	}
	private JLabel getLblTemperatura() {
		if (lblTemperatura == null) {
			lblTemperatura = new JLabel("Temperatura:");
			lblTemperatura.setForeground(new Color(0, 0, 153));
			lblTemperatura.setFont(new Font("Perpetua Titling MT", Font.BOLD, 25));
			lblTemperatura.setBounds(31, 190, 341, 51);
		}
		return lblTemperatura;
	}
	private JLabel getIspisTemp() {
		if (ispisTemp == null) {
			ispisTemp = new JLabel("");
			ispisTemp.setForeground(new Color(0, 0, 153));
			ispisTemp.setFont(new Font("Perpetua Titling MT", Font.BOLD, 25));
			ispisTemp.setBounds(269, 190, 477, 51);
		}
		return ispisTemp;
	}
	private JButton getSaznajTemp() {
		if (saznajTemp == null) {
			saznajTemp = new JButton("Saznaj temperaturu");
			saznajTemp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Socket veza = new Socket("localhost",3400);
						String grad = zeljeniGrad.getText(); 
						DataOutputStream kaServeru = new DataOutputStream(veza.getOutputStream());
						kaServeru.writeBytes(grad+'\n');
						DataInputStream odServera = new DataInputStream(veza.getInputStream());
						double temp = odServera.readDouble();
						if(temp==999) ispisTemp.setText("Grad ne postoji u bazi");
						else ispisTemp.setText(temp+"°C");
						veza.close();
					} catch (UnknownHostException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			});
			saznajTemp.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
			saznajTemp.setBounds(347, 115, 202, 31);
		}
		return saznajTemp;
	}
}
