package com.bonuswallet.app.widget;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bonuswallet.app.R;

import org.web3j.crypto.WalletUtils;

public class CopyTextView extends LinearLayout {

    public static final String KEY_ADDRESS = "key_address";

    private Context context;
    private ImageView copy;
    private TextView text;
    private LinearLayout layout;

    private int textResId;
    private int textColor;
    private int gravity;
    private int background;
    private int height;
    private int padding;
    private boolean showToast;
    private boolean boldFont;
    private boolean removePadding;
    private String rawAddress;
    private float marginRight;

    public CopyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        inflate(context, R.layout.item_copy_textview, this);

        getAttrs(context, attrs);

        bindViews();
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CopyTextView,
                0, 0
        );

        try {
            textResId = a.getResourceId(R.styleable.CopyTextView_text, R.string.action_add_wallet);
            textColor = a.getColor(R.styleable.CopyTextView_textColour, context.getColor(R.color.text_black));
            gravity = a.getInt(R.styleable.CopyTextView_android_gravity, Gravity.NO_GRAVITY);
            background = a.getResourceId(R.styleable.CopyTextView_backgroundView, R.drawable.background_round_border);
            height = a.getResourceId(R.styleable.CopyTextView_minHeightView, 0);
            showToast = a.getBoolean(R.styleable.CopyTextView_showToast, true);
            boldFont = a.getBoolean(R.styleable.CopyTextView_bold, false);
            removePadding = a.getBoolean(R.styleable.CopyTextView_removePadding, false);
            marginRight = a.getDimension(R.styleable.CopyTextView_marginRight, 10.0f);
            padding = a.getInt(R.styleable.CopyTextView_paddingView, 0);
        } finally {
            a.recycle();
        }
    }

    private void bindViews() {
        layout = findViewById(R.id.view_container);
        copy = findViewById(R.id.img_copy);
        text = findViewById(R.id.text);
        text.setText(textResId);
        text.setTextColor(textColor);
        text.setGravity(gravity);
        layout.setBackgroundResource(background);
        layout.setMinimumHeight(height);

        if(padding > 0)
        layout.setPadding(padding, padding, padding, padding);

        LayoutParams layoutParams = (LayoutParams) text.getLayoutParams();
        layoutParams.rightMargin = (int) marginRight;
        text.setLayoutParams(layoutParams);

        if(boldFont)
        {
            text.setTypeface(text.getTypeface(), Typeface.BOLD);
        }

        if(removePadding)
        {
            copy.setPadding(0, 0, 0, 0);
        }

        layout.setOnClickListener(v -> copyToClipboard());
        copy.setOnClickListener(v -> copyToClipboard());
    }

    public String getText() {
        return rawAddress;
    }

    public void setText(CharSequence text) {
        rawAddress = text.toString();
        String breakAddr = rawAddress;
        if ((gravity & Gravity.CENTER_HORIZONTAL) == Gravity.CENTER_HORIZONTAL && WalletUtils.isValidAddress(breakAddr)) //split string across two lines
        {
            breakAddr = breakAddr.substring(0, 22) + " " + breakAddr.substring(22);
        }

        this.text.setText(breakAddr);
    }

    private void copyToClipboard()
    {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(KEY_ADDRESS, rawAddress);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
        }

        if(showToast) Toast.makeText(context, R.string.copied_to_clipboard, Toast.LENGTH_SHORT).show();
    }
}
