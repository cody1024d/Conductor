package com.bluelinelabs.conductor.demo.controllers;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.demo.R;
import com.bluelinelabs.conductor.demo.controllers.base.BaseController;
import com.bluelinelabs.conductor.demo.util.BundleBuilder;

import butterknife.BindView;
import butterknife.OnClick;

public class TextController extends BaseController {

    private static final String KEY_TEXT = "TextController.text";
    private static final String KEY_COLOR_RES_ID = "TextController.color";

    @BindView(R.id.text_view) TextView textView;

    public TextController(String text) {
        this(new BundleBuilder(new Bundle())
                .putString(KEY_TEXT, text)
                .putBoolean(KEY_COLOR_RES_ID, false)
                .build()
        );
    }

    public TextController(String text, boolean showAsDialog) {
        this(new BundleBuilder(new Bundle())
                .putString(KEY_TEXT, text)
                .putBoolean(KEY_COLOR_RES_ID, showAsDialog)
                .build()
        );
    }

    public TextController(Bundle args) {
        super(args);
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_text, container, false);
    }

    @Override
    public void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        textView.setText(getArgs().getString(KEY_TEXT));
        boolean showAsDialog = getArgs().getBoolean(KEY_COLOR_RES_ID);
        if (showAsDialog) {
            view.setBackgroundColor(getResources().getColor(R.color.dialog_background));
        } else {
            view.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
    }

    @OnClick(R.id.text_view)
    protected void dismiss() {
        getRouter().popCurrentController();
    }

}
