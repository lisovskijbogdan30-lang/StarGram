package org.telegram.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.StarGramSession;
import org.telegram.ui.ActionBar.BaseFragment;

public class StarGramLoginActivity extends BaseFragment {

    private EditText numberField;
    private EditText codeField;
    private TextView statusText;

    @Override
    public View createView(android.content.Context context) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32, 48, 32, 32);

        TextView title = new TextView(context);
        title.setText("Добро пожаловать в StarGram");
        title.setTextSize(24);
        layout.addView(title);

        numberField = new EditText(context);
        numberField.setHint("Номер StarGram (+79...)");
        layout.addView(numberField);

        Button sendButton = new Button(context);
        sendButton.setText("Получить код");
        layout.addView(sendButton);

        codeField = new EditText(context);
        codeField.setHint("Пятизначный код");
        codeField.setInputType(2);
        layout.addView(codeField);

        Button loginButton = new Button(context);
        loginButton.setText("Войти");
        layout.addView(loginButton);

        statusText = new TextView(context);
        layout.addView(statusText);

        sendButton.setOnClickListener(v -> {
    String number = numberField.getText().toString().trim();

    if (number.isEmpty()) {
        statusText.setText("Введи номер StarGram");
        return;
    }

    statusText.setText("Подключаемся к серверу...");
});

        loginButton.setOnClickListener(v ->
                statusText.setText("Следующим шагом подключим проверку кода"));

        fragmentView = layout;
        return fragmentView;
    }
}
