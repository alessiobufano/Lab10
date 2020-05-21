package it.polito.tdp.bar.model;

public class Table implements Comparable<Table>{
	
	private int places;

	public Table(int places) {
		super();
		this.places = places;
	}

	public int getPlaces() {
		return places;
	}

	@Override
	public String toString() {
		return "Table [places=" + places + "]";
	}

	@Override
	public int compareTo(Table t2) {
		return this.places - t2.places;
	}
	
	

}
