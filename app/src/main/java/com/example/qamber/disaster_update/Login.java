package com.example.qamber.disaster_update;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qamber.disaster_update.ModelClass.MSG;
import com.example.qamber.disaster_update.ModelClass.SharedPrefManager;
import com.example.qamber.disaster_update.Services.APIService;
import com.example.qamber.disaster_update.Services.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private ProgressDialog pDialog;
    TextView SigRoot;
    private Boolean isClicked = false;
    ImageView showBtn;


    private EditText editTextEmail, editTextPassword;

    private Button buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        showBtn = (ImageView) findViewById(R.id.showBtn);
        //final EditText editText = (EditText) findViewById(R.id.passwordField);

        buttonSignIn = (Button) findViewById(R.id.logintnit);
        editTextEmail = (EditText) findViewById(R.id.Log_emaiId);
        editTextPassword = (EditText) findViewById(R.id.Log_pass);

        SigRoot = (TextView) findViewById(R.id.SigPAth);


        SigRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                userSignIn();
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClicked = isClicked ? false : true;
                if (isClicked) {
                    //button.setText("Hide Password");
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    //Toast.makeText(Login.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    //button.setText("Show password");
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });

    }

    private void userSignIn() {

        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
//        Log.d(TAG, "Login");
//
//        if (validate() == false) {
//            onLoginFailed();
//            return;
//        }
//
//        //_loginButton.setEnabled(false);
//        loginServer();
    }

    private void loginServer(){
        pDialog = new ProgressDialog(Login.this);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Sign In...");
        pDialog.setCancelable(false);

        showpDialog();

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ApiClient.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        //APIService service = retrofit.create(APIService.class);
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<MSG> call = service.userLogIn(email, password);

        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                hidepDialog();
                //onSignupSuccess();
                Log.d("onResponse", "" + response.body().getMessage());

                if(response.body().getSuccess() == 1) {
                    startActivity(new Intent(Login.this, Home.class));
                    finish();
                }else {
                    Toast.makeText(Login.this, "can't", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Login.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                hidepDialog();
                Log.d("onFailure", t.toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    public void onLoginSuccess() {
        //buttonSignIn.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Invalid", Toast.LENGTH_LONG).show();
        //buttonSignIn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("enter a valid email address");
            requestFocus(editTextEmail);
            valid = false;
        } else {
            editTextEmail.setError(null);
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is empty");
            requestFocus(editTextPassword);
            valid = false;
        } else {
            editTextPassword.setError(null);
        }

        return valid;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
