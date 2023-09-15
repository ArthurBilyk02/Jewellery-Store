package Classes;

public class JewelleryItem {
	private String description;
	private String type;
	private boolean gender;

	private double cost;
	private LinkedListImpl<MaterialComponent> materials;

	// constructor and other methods

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public boolean isGender() {
		return gender;
	}

	public double getCost() {
		return cost;
	}

	public LinkedListImpl<MaterialComponent> getMaterials() {
		return materials;
	}

	public JewelleryItem(String description, String type, boolean gender, double cost) {
		this.description = description;
		this.type = type;
		this.gender = gender;
		this.cost = cost;
		this.materials = new LinkedListImpl<MaterialComponent>();
	}

	public void addMaterial(MaterialComponent material) {
		materials.add(material);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(description).append(" ").append(type).append(gender + "\n").append(cost);
		for (int i = 1; i <= materials.getLength(); i++) {
			sb.append(materials.getEntry(i).toString()).append("\n");
		}
		return sb.toString();
	}

}
