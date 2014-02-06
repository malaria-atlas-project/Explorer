package uk.ac.ox.map.explorer.client.proxy;

import java.io.Serializable;

public class DuffyProxy implements EntityProxy, NamedProxy, Serializable {
	private static final long serialVersionUID = -6308673010475937442L; // auto
	// generated
	private Long id;

	private String name;

	private Double minX;

	private Double minY;

	private Double maxX;

	private Double maxY;

	public Long getId() {
		return id;
	}

	@Override
	public Double getMaxX() {
		return maxX;
	}

	@Override
	public Double getMaxY() {
		return maxY;
	}

	@Override
	public Double getMinX() {
		return minX;
	}

	@Override
	public Double getMinY() {
		return minY;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setMaxX(Double maxX) {
		this.maxX = maxX;
	}

	@Override
	public void setMaxY(Double maxY) {
		this.maxY = maxY;
	}

	@Override
	public void setMinX(Double minX) {
		this.minX = minX;
	}

	@Override
	public void setMinY(Double minY) {
		this.minY = minY;
	}

	public void setName(String name) {
		this.name = name;
	}

}
