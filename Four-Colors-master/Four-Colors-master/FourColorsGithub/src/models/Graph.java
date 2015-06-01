package models;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Color;

public class Graph {
	private int width;
	private int height;
	private int numRegions;
	private ArrayList<Region> regions;
	private int[][] adjacencies;

	public Graph(int width, int height, ArrayList<Region> regions) {
		this.width = width;
		this.height = height;
		this.regions = regions;
		this.numRegions = regions.size();
		adjacencies = new int[numRegions][numRegions];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getNumRegions() {
		return numRegions;
	}

	public ArrayList<Region> getRegions() {
		return regions;
	}

	public int[][] getAdjacencyMatrix() {
		return adjacencies;
	}
}
