package com.example.twoactivitiesnew;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Nombre de la clase para el log
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // Etiqueta única para el mensaje extra
    public static final String EXTRA_MESSAGE
            = "com.example.android.twoactivitiesnew.extra.MESSAGE";


    private EditText mMessageEditText;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;


    // NUEVA MANERA DE LANZAR UNA ACTIVIDAD DADO QUE startActivityForResult ESTÁ DEPRECATED
    // DEFINIMOS/CREAMOS UN LANZADOR DE ACTIVITY PARA OBTENER UN RESULTADO
    // Primer parámetro: contrato. Inicio la actividad para un resultado
    // Segundo parámetro: resultado a la llamada.
    ActivityResultLauncher<Intent> secondActivityResultLauncher = registerForActivityResult(

            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                //Se implementa el método onActivityResult
                @Override
                public void onActivityResult(ActivityResult result) {
                    //Se comprueba que el código del resultado sea correcto
                    //Es decir, que reciba lo que debe recibir
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String reply = null;
                        //Si existen datos, se cargan en reply desde la second activity
                        if (data != null) {
                            reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                        }

                        //Se hace visible la vista de la respuesta
                        mReplyHeadTextView.setVisibility(View.VISIBLE);

                        //Se fijan los datos de la respuesta
                        mReplyTextView.setText(reply);
                        mReplyTextView.setVisibility(View.VISIBLE);
                    }
                }
            });

    /**
     * Se inicia la activity
     *
     * @param savedInstanceState The current state data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        // Se inicializan las variables
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);


    }


    /**
     * Gestiona el evento onclick. Obtiene el valor del Edit text principal
     * creando un intent y lanzando la segunda actividad con un intent
     * El retorno del intent de la segunda actividad es onActivityResult().
     *
     * @param view The view (Button) that was clicked.
     */
    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Botón pulsado");
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();
        //Indico quién envía el mensaje y el mensaje que se envía
        intent.putExtra(EXTRA_MESSAGE, message);
        //lanza la segunda activity
        secondActivityResultLauncher.launch(intent);

    }

    /* Cómo se hacía antes onActivityResult
    /**
     * Handles the data in the return intent from SecondActivity.
     *
     * @param requestCode Code for the SecondActivity request.
     * @param resultCode  Code that comes back from SecondActivity.
     * @param data        Intent data sent back from SecondActivity.
     */
    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply.
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                // Make the reply head visible.
                mReplyHeadTextView.setVisibility(View.VISIBLE);

                // Set the reply and make it visible.
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

     */


}
