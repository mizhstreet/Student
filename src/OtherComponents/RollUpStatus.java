package OtherComponents;

/**
 * A model class represents a country.
 * @author www.codejava.net
 *
 */
public class RollUpStatus {
	private String name;

	public RollUpStatus(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
}
