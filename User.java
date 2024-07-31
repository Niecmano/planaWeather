package accu;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class User {
	public static void main(String[] args) {
		try {
			Socket veza = new Socket("localhost",3400);
			System.out.println("Unesite grad za koji zelite da vidite temperaturu: ");
			Scanner kb = new Scanner(System.in);
			String grad = kb.nextLine();
			System.out.println("Trazi se info o temperaturi za "+grad+"...");
			DataOutputStream kaServeru = new DataOutputStream(veza.getOutputStream());
			kaServeru.writeBytes(grad+'\n');
			DataInputStream odServera = new DataInputStream(veza.getInputStream());
			double temp = odServera.readDouble();
			if(temp==999) System.out.println("<Grad ne postoji u bazi>");
			else System.out.println("<Temperatura: "+temp+">");
			veza.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
