package Classes;

public class MaterialComponent {
	private String name;
	private String description;
	private String quality;
	private String weight;

	public MaterialComponent(String name, String description, String quality, String weight) {
		this.name = name;
		this.description = description;
		this.quality = quality;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuality() {
		return quality;
	}

	public String getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "MaterialComponent {" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
