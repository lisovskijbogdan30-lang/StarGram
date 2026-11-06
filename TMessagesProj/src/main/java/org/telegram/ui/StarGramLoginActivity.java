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
import okhttp3.*;
import org.json.JSONObject;
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

    try {
        JSONObject json = new JSONObject();
        json.put("number", number);

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url("https://stargram-server.onrender.com/request-code")
                .post(body)
                .build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                AndroidUtilities.runOnUIThread(() ->
                        statusText.setText("Ошибка подключения к серверу")
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final boolean success = response.isSuccessful();
                response.close();

                AndroidUtilities.runOnUIThread(() ->
                        statusText.setText(success
                                ? "Код отправлен в Telegram-бота!"
                                : "Ошибка: аккаунт не найден или запрос отклонён")
                );
            }
        });

        statusText.setText("Отправляем запрос...");

    } catch (Exception e) {
        statusText.setText("Ошибка обработки номера");
    }
});
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
