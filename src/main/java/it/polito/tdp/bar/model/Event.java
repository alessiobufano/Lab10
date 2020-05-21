package it.polito.tdp.bar.model;

public class Event implements Comparable<Event>{

	public enum EventType {
		NEW_CLIENTS, TABLE_RETURNED
	}
	
	private int time;
	private Table table;
	private EventType type;
	
	public Event(int time, EventType type) {
		super();
		this.time = time;
		this.type = type;
	}
	
	public Event(int time, Table table, EventType type) {
		super();
		this.time = time;
		this.table = table;
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Table getTable() {
		return table;
	}
	
	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + "]";
	}

	@Override
	public int compareTo(Event e2) {
		return this.time-e2.time;
	}
	
	
}
