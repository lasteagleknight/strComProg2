package models;

import java.util.ArrayList;

import utils.PuzzleBoard;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Defines the location of a point in a 2D array. posY is the row, posX is the column. 
 * The origin is the top left corner, posY and posX are both nonnegative.
 */
public class Point {
	private int posX;
	private int posY;

	/*
	 * Construct a point with the given position.
	 */
	public Point(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	/*
	 * Get the X position.
	 */
	public int getX() {
		return posX;
	}

	/*
	 * Get the Y position.
	 */
	public int getY() {
		return posY;
	}

	/*
	 * Calculate distance from another point.
	 */
	public double dist(Point p) {
		int xDist = Math.abs(this.posX - p.posX);
		int yDist = Math.abs(this.posY - p.posY);

		return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
	}

	/*
	 * Compare if two points are equal. Returns true iff both X and Y are the
	 * same.
	 */
	public boolean equals(Point p) {
		return this.posX == p.getX() && this.posY == p.getY();
	}

	public ArrayList<Point> getAdjacentPoints() {
		ArrayList<Point> adjacent = new ArrayList<Point>();

		Point left = new Point(posX - 1, posY);
		Point right = new Point(posX + 1, posY);
		Point top = new Point(posX, posY - 1);
		Point bottom = new Point(posX, posY + 1);

		adjacent.add(left);
		adjacent.add(right);
		adjacent.add(top);
		adjacent.add(bottom);

		return adjacent;
	}
}
