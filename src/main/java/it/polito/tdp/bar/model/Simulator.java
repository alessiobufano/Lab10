package it.polito.tdp.bar.model;

import java.util.*;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {
	
	//events type queue
	private PriorityQueue<Event> queue;
	private int eventsNumber;
	
	//input values
	private List<Table> tablesList;
	private final int startTime = 0;
	private final double minimumOccupation = 0.5;
	
	//system status
	private List<Table> freeTables;
	
	//output values
	private int totalClients;
	private int dissatisfiedClients;
	
	//initialition of the simulator
	public void init() {
		
		this.queue = new PriorityQueue<>();
		this.eventsNumber = 2000;
		
		this.tablesList = new LinkedList<>();
		this.tablesList.add(new Table(4));
		this.tablesList.add(new Table(4));
		this.tablesList.add(new Table(4));
		this.tablesList.add(new Table(4));
		this.tablesList.add(new Table(4));
		this.tablesList.add(new Table(6));
		this.tablesList.add(new Table(6));
		this.tablesList.add(new Table(6));
		this.tablesList.add(new Table(6));
		this.tablesList.add(new Table(8));
		this.tablesList.add(new Table(8));
		this.tablesList.add(new Table(8));
		this.tablesList.add(new Table(8));
		this.tablesList.add(new Table(10));
		this.tablesList.add(new Table(10));
		
		this.freeTables = new LinkedList<>(this.tablesList);
				
		this.totalClients = this.dissatisfiedClients = 0;
	}
	
	//to run the simulator
	public void run() {
		
		this.queue.clear();
		int time = this.startTime;
		
		do 
		{
			Event e = new Event(time, EventType.NEW_CLIENTS);
			this.queue.add(e);
			this.eventsNumber--;
			
			int addingTime = (int) (Math.random() * 11);
			time += addingTime;
		}
		while(this.eventsNumber>0);
		
		while(!this.queue.isEmpty())
		{
			Event e = this.queue.poll();
			this.processEvent(e);
		}
	}
	
	private void processEvent(Event e) {
		
		switch(e.getType()) 
		{
		
		case NEW_CLIENTS:
			
			int clients = (int) (Math.random() * 11);
			int stayingTime = (int) (Math.random() * 61);
			stayingTime += 60;
			
			double tolerance = 0.91;
			while(tolerance>0.9) {
				tolerance = Math.random();
			}
			
			double minimumTolerance = 0.91;
			while(minimumTolerance>0.9) {
				minimumTolerance = Math.random();
			}
			
			this.totalClients += clients;
			
			boolean found = false;
			Collections.sort(this.freeTables);
			
			for(Table t : this.freeTables)
			{
				if(clients<=t.getPlaces() && clients>=(t.getPlaces()*minimumOccupation))
				{
					Event newEv = new Event(e.getTime()+stayingTime, t, EventType.TABLE_RETURNED);
					this.queue.add(newEv);
					this.freeTables.remove(t);
					found = true;
					break;
				}
			}
			
			if(!found && tolerance<minimumTolerance)
				this.dissatisfiedClients += clients;
			
			break;
			
		case TABLE_RETURNED:
			
			if(e.getTable()!=null)
				this.freeTables.add(e.getTable());
			
			break;
			
		}
	}
	
	
	public void optimizedrun() {
		
		this.queue.clear();
		int time = this.startTime;
		
		do 
		{
			Event e = new Event(time, EventType.NEW_CLIENTS);
			this.queue.add(e);
			this.eventsNumber--;
			
			int addingTime = (int) (Math.random() * 11);
			time += addingTime;
		}
		while(this.eventsNumber>0);
		
		while(!this.queue.isEmpty())
		{
			Event e = this.queue.poll();
			this.optimizedProcessEvent(e);
		}
	}

	private void optimizedProcessEvent(Event e) {
		
		switch(e.getType()) 
		{
		
		case NEW_CLIENTS:
			
			int clients = (int) (Math.random() * 11);
			int stayingTime = (int) (Math.random() * 61);
			stayingTime += 60;
		
			double tolerance = 0.91;
			while(tolerance>0.9) {
				tolerance = Math.random();
			}
			
			double minimumTolerance = 0.91;
			while(minimumTolerance>0.9) {
				minimumTolerance = Math.random();
			}
			
			this.totalClients += clients;
			
			//to optimize we can fix that if the tolerance is lower than the minimum, 
			//clients automatically don't sit and are satisfied
			
			if(tolerance<minimumTolerance)
			{
				boolean found = false;
				Collections.sort(this.freeTables);
				
				for(Table t : this.freeTables)
				{
					if(clients<=t.getPlaces() && clients>=(t.getPlaces()*minimumOccupation))
					{
						Event newEv = new Event(e.getTime()+stayingTime, t, EventType.TABLE_RETURNED);
						this.queue.add(newEv);
						this.freeTables.remove(t);
						found = true;
						break;
					}
				}
				
				if(!found && tolerance<minimumTolerance)
					this.dissatisfiedClients += clients;
			}
			
			break;
			
		case TABLE_RETURNED:
			
			if(e.getTable()!=null)
				this.freeTables.add(e.getTable());
			
			break;
			
		}
	}

	public int getTotalClients() {
		return totalClients;
	}

	public int getDissatisfiedClients() {
		return dissatisfiedClients;
	}


}
