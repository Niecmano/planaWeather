package accu;

import java.io.*;
import java.net.*;

public class Server {
	
	private static TempGrada[] prognoza;
	private static void dodajGrad(String grad, double t) {
		for (int i = 0; i < prognoza.length; i++) {
			if (prognoza[i]==null) {
				prognoza[i] = new TempGrada(grad, t);
				break;
			}
		}
	}
	public static void main(String[] args) {
		try {
			ServerSocket prijemZahteva = new ServerSocket(3400);
			prognoza = new TempGrada[40];
			dodajGrad("Menton", 29.4);
			dodajGrad("Perpignan", 30.2);
			dodajGrad("Villefranche-sur-Mer", 29.9);
			dodajGrad("Portofino", 28.2);
			dodajGrad("Savona", 28.9);
			while (true) {
				Socket veza = prijemZahteva.accept();
				System.out.println("Connected..");
				BufferedReader odKlijenta = new BufferedReader(new InputStreamReader(veza.getInputStream()));
				String grad = odKlijenta.readLine().trim();
				System.out.println("Korisnik je poslao upit za temperaturu u "+grad+"...");
				DataOutputStream kaKlijentu = new DataOutputStream(veza.getOutputStream());
				for (TempGrada i : prognoza) {
					if(i==null) {
						kaKlijentu.writeDouble(999);
						break;
					}
					if(i.getGrad().equalsIgnoreCase(grad)) {
						kaKlijentu.writeDouble(i.getTemp());
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
