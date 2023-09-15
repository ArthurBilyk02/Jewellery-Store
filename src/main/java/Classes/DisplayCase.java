package Classes;

public class DisplayCase {
	private String id;
	private boolean type;
	private boolean isLit;
	private LinkedListImpl<DisplayTray> trays;

	// constructor, getters, setters, and other methods
	public DisplayCase(String id, boolean type, boolean isLit) {
		this.id = id;
		this.type = type;
		this.isLit = isLit;
		this.trays = new LinkedListImpl<DisplayTray>();
	}

	public void addTray(DisplayTray tray) {
		trays.add(tray);
	}

	public LinkedListImpl<DisplayTray> getDisplayTrays(){
		return this.trays;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append(" ").append(type).append(" ").append(isLit ? "Lit" : "Unlit").append("\n");
		for (int i = 1; i <= trays.getLength(); i++) {
			sb.append(trays.getEntry(i).toString()).append("\n");
		}
		return sb.toString();
	}
}