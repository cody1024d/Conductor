package com.bluelinelabs.conductor.demo.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.bluelinelabs.conductor.demo.R;
import com.bluelinelabs.conductor.demo.changehandler.FadeInChangeHandler;
import com.bluelinelabs.conductor.demo.changehandler.FadeOutChangeHandler;
import com.bluelinelabs.conductor.demo.controllers.base.BaseController;
import com.bluelinelabs.conductor.demo.util.BundleBuilder;

import butterknife.BindView;

public class ChildController extends BaseController {

    private static final String KEY_TITLE = "ChildController.title";
    private static final String KEY_BG_COLOR = "ChildController.bgColor";
    private static final String KEY_COLOR_IS_RES = "ChildController.colorIsResId";

    @BindView(R.id.btn_dialog_next)
    Button btnDlgNext;

    @BindView(R.id.btn_clear_next)
    Button btnClearNext;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    public ChildController(String title, int backgroundColor, boolean colorIsResId) {
        this(new BundleBuilder(new Bundle())
                .putString(KEY_TITLE, title)
                .putInt(KEY_BG_COLOR, backgroundColor)
                .putBoolean(KEY_COLOR_IS_RES, colorIsResId)
                .build());
    }

    public ChildController(Bundle args) {
        super(args);
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_child, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        btnDlgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterTransaction transaction = RouterTransaction.with(new TextController(String.format("DIALOG Next from %s", getArgs().getString(KEY_TITLE)), true))
                        .pushChangeHandler(new FadeInChangeHandler())
                        .popChangeHandler(new FadeOutChangeHandler());
                getRouter().pushController(transaction);
            }
        });

        btnClearNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RouterTransaction transaction = RouterTransaction.with(new TextController(String.format("CLEAR BKGRND Next from %s", getArgs().getString(KEY_TITLE))));
                getRouter().pushController(transaction);
            }
        });

        tvTitle.setText(getArgs().getString(KEY_TITLE));

        int bgColor = getArgs().getInt(KEY_BG_COLOR);
        if (getArgs().getBoolean(KEY_COLOR_IS_RES)) {
            bgColor = ContextCompat.getColor(getActivity(), bgColor);
        }
        view.setBackgroundColor(bgColor);
    }
}
