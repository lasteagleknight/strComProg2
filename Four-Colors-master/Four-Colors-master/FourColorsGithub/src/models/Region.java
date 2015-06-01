package models;

import java.util.ArrayList;

import utils.PuzzleBoard;

import com.example.dc.fourcolors.R;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

public class Region implements Comparable {
	private int color;
	private int size;
	private Point anchor;
	private ArrayList<Point> interior;

	public Region(Point anchor) {
		color = 0xFF838383;
		interior = new ArrayList<Point>();
		size = interior.size();
		this.anchor = anchor;
	}

	public int getColor() {
		return color;
	}

	public int getSize() {
		return size;
	}

	public ArrayList<Point> getInteriorPoints() {
		return interior;
	}

	public void addInteriorPoint(Point p) {
		interior.add(p);
		size++;
	}

	public void addPoints(ArrayList<Point> p) {
		interior.addAll(p);
		size += p.size();
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Point getAnchor() {
		return anchor;
	}

	public boolean contains(Point p) {
		return interior.contains(p);
	}

	/**
	 * Returns 0 if they have the same anchor point or if they are the same
	 * size.
	 */
	public int compareTo(Object another) {
		Region other = (Region) another;
		if (anchor == other.anchor)
			return 0;
		return (int) Math.signum(size - other.size);

	}
}
