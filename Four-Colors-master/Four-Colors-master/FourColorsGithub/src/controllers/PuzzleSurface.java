package controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PuzzleSurface extends SurfaceView implements
		SurfaceHolder.Callback {
	private Context c;
	private SurfaceHolder holder;
	private Bitmap bmp;

	public PuzzleSurface(Context context) {
		super(context);
		c = context;

		holder = getHolder();
		holder.addCallback(this);
		bmp = null;
	}

	public PuzzleSurface(Context context, AttributeSet attrs) {
		super(context, attrs);
		c = context;
		holder = getHolder();
		holder.addCallback(this);
		bmp = null;
	}

	public PuzzleSurface(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		c = context;
		holder = getHolder();
		holder.addCallback(this);
		bmp = null;
	}

	public void surfaceCreated(SurfaceHolder holder) {
		if (bmp != null) {
			drawBitmap(bmp);
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	public void drawBitmap(Bitmap bmp) {
		Canvas mCanvas = holder.lockCanvas();
		if (mCanvas == null)
			return;
		mCanvas.drawBitmap(bmp, 0, 0, null);
		holder.unlockCanvasAndPost(mCanvas);
	}

	public void setImage(Bitmap bmp) {
		this.bmp = bmp;
	}
}
