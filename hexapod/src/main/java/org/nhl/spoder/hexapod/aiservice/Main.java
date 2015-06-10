package org.nhl.spoder.hexapod.aiservice;

import org.nhl.spoderpod.hexapod.components.C_AICalculate;
import org.nhl.spoderpod.hexapod.components.C_AIFormat;
import org.nhl.spoderpod.hexapod.components.C_RouterClient;
import org.nhl.spoderpod.hexapod.core.Service;
import org.nhl.spoderpod.hexapod.interfaces.I_Component;

/***
 * Welkom bij de AI service Main class. Deze service verzorgt de beslissingen
 * die gemaakt worden door de spin zelf tijdens autonome taken.
 * 
 * @author Driving Ghost TODO calculate moet nog af worden gemaakt om een
 *         gezonde gebalanceerde afweging te maken.
 *
 */
public class Main {
	/*
	 * Starts the service.
	 */
	public static void main(String[] args) throws InterruptedException {
		//MOET DE C_RouterClient component nog toevoegen! 

		Service s = new Service("AiService", new I_Component[] { 	
					new C_AICalculate("C_AICalculate"),
					new C_AIFormat("C_AIFormat"),
					new C_RouterClient("C_RouterClient", "127.0.0.1",1234)});

		s.start();
	}
}
