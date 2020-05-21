package it.polito.tdp.bar.model;

import java.text.*;

public class Model {
	
	private Simulator simulator;
	
	public Model() {
		this.simulator = new Simulator();
	}

	public void simulate() {
		simulator.init();
		simulator.run();
	}
	
	public void optmizeSimulation() {
		simulator.init();
		simulator.optimizedrun();
	}
	
	public String getSimulationResults() {
		
		String result="";
		
		int satisfiedClients = simulator.getTotalClients() - simulator.getDissatisfiedClients();
		double satisfaction = (double) ((double) 10000*satisfiedClients/simulator.getTotalClients());
		Format format = new DecimalFormat("0,00");
		result = "The simulation results are:\n"+simulator.getTotalClients()+" total clients\n"+satisfiedClients+" satisfied clients\n"+simulator.getDissatisfiedClients()+" dissatisfied clients\n"+format.format(satisfaction)+"% of satisfied clients\n";
		
		return result;
	}

	
}
