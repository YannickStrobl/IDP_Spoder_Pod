package org.nhl.spoder.hexapod.httpservice;

import org.nhl.spoderpod.hexapod.components.C_HTTPAppSocket;
import org.nhl.spoderpod.hexapod.components.C_RouterClient;
import org.nhl.spoderpod.hexapod.core.Service;
import org.nhl.spoderpod.hexapod.interfaces.I_Component;

/***
 * HTTP Service verstuurt de gegevens naar de server van de webapp.
 * 
 * @author Driving Ghost
 */
public class Main {

	/*
	 * Starts the service.
	 */
	public static void main(String[] args) throws InterruptedException {
		Service s = new Service("HttpService", new I_Component[] {
				new C_HTTPAppSocket("C_HTTPAppSocket", 8080),
				new C_RouterClient("C_RouterClient", "127.0.0.1", 1234) });
		s.start();

	}
}