package ca.sociology;

public class Personality {

	private final String character;
	private final int dopaminLevel;
	
	public Personality(String character, int level) {
		this.character = character;
		this.dopaminLevel = level;
	}

	public String getCharacter() {
		return character;
	}


	public int getDopaminLevel() {
		return dopaminLevel;
	}
	
	public int getDopaminDifference(Personality other) {
		return this.dopaminLevel - other.getDopaminLevel();
	}
	
	@Override
	public String toString() {
		return String.format("%s - %d", character, dopaminLevel);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((character == null) ? 0 : character.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personality other = (Personality) obj;
		if (character == null) {
			if (other.character != null)
				return false;
		} else if (!character.equals(other.character))
			return false;
		return true;
	}
	
	
	
}
