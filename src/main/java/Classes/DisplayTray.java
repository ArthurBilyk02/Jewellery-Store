package Classes;

public class DisplayTray {
	private String id;
	private String color;
	private LinkedListImpl<JewelleryItem> items;

	// constructor and other methods
	public DisplayTray(String id, String color, int size) {
		this.id = id;
		this.color = color;
		this.items = new LinkedListImpl<JewelleryItem>();
	}

	public void addItem(JewelleryItem item) {
		items.add(item);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append(" ").append(color).append("\n");
		for (int i = 1; i <= items.getLength(); i++) {
			sb.append(items.getEntry(i).toString()).append("\n");
		}
		return sb.toString();
	}
}
