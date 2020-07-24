package com.rmartinper.filepicker.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.rmartinper.filepicker.R;

/**
 * <p>
 * Created by Raul Martin on 02-07-2020.
 * </p>
 */
public class MaterialCheckbox extends View {
    private Context context;
    private int minDim;
    private Paint paint;
    private RectF bounds;
    private boolean checked;
    private OnCheckedChangeListener onCheckedChangeListener;
    private Path tick;

    public MaterialCheckbox(Context context) {
        super(context);
        initView(context);
    }

    public MaterialCheckbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MaterialCheckbox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        this.context = context;
        checked = false;
        tick = new Path();
        paint = new Paint();
        bounds = new RectF();
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(!checked);
                onCheckedChangeListener.onCheckedChanged(MaterialCheckbox.this, isChecked());
            }
        };

        setOnClickListener(onClickListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.reset();
        paint.setAntiAlias(true);
        bounds.set(minDim / 10f, minDim / 10f, minDim - (minDim / 10f), minDim - (minDim / 10f));
        if(isChecked()) {
            paint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
            canvas.drawRoundRect(bounds, minDim / 8f, minDim / 8f, paint);

            paint.setColor(Color.parseColor("#FFFFFF"));
            paint.setStrokeWidth(minDim / 10f);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.BEVEL);
            canvas.drawPath(tick, paint);
        }
        else {
            paint.setColor(Color.parseColor("#C1C1C1"));
            canvas.drawRoundRect(bounds, minDim / 8f, minDim / 8f, paint);

            bounds.set(minDim / 5f, minDim / 5f, minDim - (minDim / 5f), minDim - (minDim / 5f));
            paint.setColor(Color.parseColor("#FFFFFF"));
            canvas.drawRect(bounds, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        minDim = Math.min(width, height);
        bounds.set(minDim / 10f, minDim / 10f, minDim - (minDim / 10f), minDim - (minDim / 10f));
        tick.moveTo(minDim / 4f, minDim / 2f);
        tick.lineTo(minDim / 2.5f, minDim - (minDim / 3f));

        tick.moveTo(minDim / 2.75f, minDim - (minDim / 3.25f));
        tick.lineTo(minDim - (minDim / 4f), minDim / 3f);
        setMeasuredDimension(width, height);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        invalidate();
    }

    public void setOnCheckedChangedListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }
}
