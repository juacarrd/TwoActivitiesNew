package com.example.twoactivitiesnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    // Etiqueta única para la respuesta del intent.
    public static final String EXTRA_REPLY =
            "com.example.android.twoactivitiesnew.extra.REPLY";

    // EditText para la respuesta.
    private EditText mReply;

    /**
     * Se inicializa la activity.
     *
     * @param savedInstanceState El estado actual de los datos
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Se inicializan las views.
        mReply = findViewById(R.id.editText_second);


        // Se obtiene el intent que fija esta actividad y el mensaje que contiene
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Se pone el mensaje en el text view
        TextView textView = findViewById(R.id.text_message);
        textView.setText(message);
    }

    /**
     * Se gestiona el onClick para el botón Reply. Se obtiene el mensaje
     * del segundo EditText. Se cre un intent y se devuelve el mensaje a la
     * segunda activity
     *
     * @param view The view (Button) that was clicked.
     */
    public void returnReply(View view) {
        // Se obtiene el mensaje de vuelta del editText.
        String reply = mReply.getText().toString();

        // Se crea un intent para la respuesta y se añade el mensaje de
        // vuelta como un extra. Se cierra la activity.
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}