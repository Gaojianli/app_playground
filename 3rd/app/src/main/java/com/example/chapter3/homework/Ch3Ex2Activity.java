package com.example.chapter3.homework;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

public class Ch3Ex2Activity extends AppCompatActivity {

    private View target;
    private View startColorPicker;
    private View endColorPicker;
    private Button durationSelector;
    private AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch3ex2);

        target = findViewById(R.id.target);
        startColorPicker = findViewById(R.id.start_color_picker);
        endColorPicker = findViewById(R.id.end_color_picker);
        durationSelector = findViewById(R.id.duration_selector);

        startColorPicker.setOnClickListener(v -> {
            ColorPicker picker = new ColorPicker(Ch3Ex2Activity.this);
            picker.setColor(getBackgroundColor(startColorPicker));
            picker.enableAutoClose();
            picker.setCallback(this::onStartColorChanged);
            picker.show();
        });

        endColorPicker.setOnClickListener(v -> {
            ColorPicker picker = new ColorPicker(Ch3Ex2Activity.this);
            picker.setColor(getBackgroundColor(endColorPicker));
            picker.enableAutoClose();
            picker.setCallback(this::onEndColorChanged);
            picker.show();
        });

        durationSelector.setText(String.valueOf(1000));
        durationSelector.setOnClickListener(v -> new MaterialDialog.Builder(Ch3Ex2Activity.this)
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .input(getString(R.string.duration_hint), durationSelector.getText(), (dialog, input) -> onDurationChanged(input.toString()))
                .show());
        resetTargetAnimation();
    }

    private void onStartColorChanged(int color) {
        startColorPicker.setBackgroundColor(color);
        resetTargetAnimation();
    }

    private void onEndColorChanged(int color) {
        endColorPicker.setBackgroundColor(color);
        resetTargetAnimation();
    }

    private void onDurationChanged(String input) {
        boolean isValid = true;
        try {
            int duration = Integer.parseInt(input);
            if (duration < 100 || duration > 10000) {
                isValid = false;
            }
        } catch (Throwable e) {
            isValid = false;
        }
        if (isValid) {
            durationSelector.setText(input);
            resetTargetAnimation();
        } else {
            Toast.makeText(Ch3Ex2Activity.this, R.string.invalid_duration, Toast.LENGTH_LONG).show();
        }
    }

    private int getBackgroundColor(View view) {
        Drawable bg = view.getBackground();
        if (bg instanceof ColorDrawable) {
            return ((ColorDrawable) bg).getColor();
        }
        return Color.WHITE;
    }

    private void resetTargetAnimation() {
        if (animatorSet != null) {
            animatorSet.cancel();
            animatorSet = null;
        }
        int duration = Integer.parseInt(durationSelector.getText().toString());
        ObjectAnimator animatorColor = ObjectAnimator.ofArgb(target, "backgroundColor", getBackgroundColor(startColorPicker), getBackgroundColor(endColorPicker));
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(target, "scaleX", 1f, 2f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(target, "scaleY", 1f, 2f);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(target, "alpha", 1f, 0.5f);
        animatorInit(animatorColor, duration);
        animatorInit(animatorScaleX, duration);
        animatorInit(animatorScaleY, duration);
        animatorInit(animatorAlpha, duration);
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorColor, animatorScaleX, animatorScaleY, animatorAlpha);
        animatorSet.start();
    }

    private void animatorInit(ObjectAnimator animator, int duration) {
        animator.setDuration(duration);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
    }
}
