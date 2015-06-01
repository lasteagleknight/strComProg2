package utils;

// Color Utilities
public class ColorUtil {

	// Converts RGB to LAB color scheme.
	public static double[] RGBToLAB(int color) {
		double[] XYZ = RGBtoXYZ(color);

		double x = XYZ[0];
		double y = XYZ[1];
		double z = XYZ[2];

		x /= 95.047;
		y /= 100;
		z /= 108.883;

		if (x > 0.008856)
			x = Math.pow(x, 1.0 / 3);
		else
			x = 7.787 * x + 16.0 / 116;

		if (y > 0.008856)
			y = Math.pow(y, 1.0 / 3);
		else
			y = 7.787 * y + 16.0 / 116;

		if (z > 0.008856)
			z = Math.pow(z, 1.0 / 3);
		else
			z = 7.787 * z + 16.0 / 116;

		double l = 116 * y - 16;
		double a = 500 * (x - y);
		double b = 200 * (y - z);

		return new double[] { l, a, b };
	}

	// Converts RGB to XYZ color scheme.
	private static double[] RGBtoXYZ(int color) {
		double red = (color >> 16) & 0xFF;
		double blue = color & 0xFF;
		double green = (color >> 8) & 0xFF;

		double redFract = red / 255.0;
		double greenFract = green / 255.0;
		double blueFract = blue / 255.0;

		if (redFract > 0.04045) {
			redFract = (redFract + 0.055) / 1.055;
			redFract = Math.pow(redFract, 2.4);
		} else
			redFract = redFract / 12.92;

		if (greenFract > 0.04045) {
			greenFract = (greenFract + 0.055) / 1.055;
			greenFract = Math.pow(greenFract, 2.4);
		} else
			greenFract = greenFract / 12.92;

		if (blueFract > 0.04045) {
			blueFract = (blueFract + 0.055) / 1.055;
			blueFract = Math.pow(blueFract, 2.4);
		} else
			blueFract = blueFract / 12.92;

		redFract *= 100;
		greenFract *= 100;
		blueFract *= 100;

		double x = redFract * 0.4124 + greenFract * 0.3576 + blueFract * 0.1805;
		double y = redFract * 0.2126 + greenFract * 0.7152 + blueFract * 0.0722;
		double z = redFract * 0.0193 + greenFract * 0.1192 + blueFract * 0.9505;

		return new double[] { x, y, z };
	}

	// Calculates LAB distance using the CIE76 Algorithm
	public static double LABDist(double[] color1, double[] color2) {
		double l1 = color1[0];
		double a1 = color1[1];
		double b1 = color1[2];

		double l2 = color2[0];
		double a2 = color2[1];
		double b2 = color2[2];

		return Math.sqrt(Math.pow(l2 - l1, 2) + Math.pow(a2 - a1, 2)
				+ Math.pow(b2 - b1, 2));
	}
}
