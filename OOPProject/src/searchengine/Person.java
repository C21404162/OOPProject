package searchengine;

public class Person {
	
	private String sterm;
	
	@Override
	public String toString() {
		return " Results for: " +sterm;
	}

	Person(String sterm) {
		this.sterm = sterm;
	}
	
}