package com.example.qamber.disaster_update;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qamber.disaster_update.ModelClass.MSG;
import com.example.qamber.disaster_update.ModelClass.SharedPrefManager;
import com.example.qamber.disaster_update.ModelClass.User;
import com.example.qamber.disaster_update.Services.APIService;
import com.example.qamber.disaster_update.Services.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private ProgressDialog pDialog;

    TextView LogRoot;
    ProgressDialog progressDialog ;
    private EditText Eemail;
    private EditText Epass;
    private Button Reg_coch;
    EditText fname,phone,org;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        org = (EditText)findViewById(R.id.Organization);
        Eemail = (EditText) findViewById(R.id.Email);
        Epass = (EditText) findViewById(R.id.Password);
        fname = (EditText) findViewById(R.id.Username);
//        phone = (EditText) findViewById(R.id.Phone);
        Reg_coch = (Button) findViewById(R.id.reg);


        LogRoot = (TextView) findViewById(R.id.LogRoot);
        LogRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });

        Reg_coch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSignUp();
            }
        });
        progressDialog = new ProgressDialog(this);
    }


    private void userSignUp() {


        Intent intent = new Intent(Signup.this, Home.class);
        startActivity(intent);

//        Log.d(TAG, "Signup");
//
//        if (validate() == false) {
//            onSignupFailed();
//            return;
//        }
//            saveToServerdb();

   }

    private void  saveToServerdb(){

        pDialog = new ProgressDialog(Signup.this);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Creating Account...");
        pDialog.setCancelable(false);

        showpDialog();

        //getting the user values
        //final RadioButton radioSex = (RadioButton) findViewById(radioGender.getCheckedRadioButtonId());

        String name = Eemail.getText().toString().trim();
        String email = Epass.getText().toString().trim();
        String password = fname.getText().toString().trim();
        //String gender = radioSex.getText().toString();


        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);
        //Defining the user object as we need to pass it with the call
//        User user = new User(name, email, password, gender);
        User user = new User(name, email, password);
        //defining the call
        Call<MSG> call = service.userSignUp(
                user.getName(),
                user.getEmail(),
                user.getPassword()
                //user.getGender()
        );

        //calling the api
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                hidepDialog();
                //onSignupSuccess();
                Log.d("onResponse", "" + response.body().getMessage());

//                https://www.simplifiedcoding.net/retrofit-android-tutorial/#Creating-Retrofit-API

                if(response.body().getSuccess() == 1) {
                    startActivity(new Intent(Signup.this, Home.class));
                    finish();
                }else {
                    Toast.makeText(Signup.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

                //if there is no error
//                if (response.isSuccessful()) {
//                    //starting profile activity
//                    finish();
//                    Toast.makeText(Signup.this, "Register", Toast.LENGTH_SHORT).show();
//                    //SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUser());
//                    startActivity(new Intent(getApplicationContext(), Home.class));
//                }
//                else {
//                    Toast.makeText(Signup.this, "Error", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                hidepDialog();
                Log.d("onFailure", t.toString());
            }

        });
    }

    public void onSignupSuccess() {
        //Reg_coch.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Invalid", Toast.LENGTH_LONG).show();
//        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = fname.getText().toString();
        String email = Eemail.getText().toString();
        String password = Epass.getText().toString();
        //String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            fname.setError("at least 3 characters");
            valid = false;
        } else {
            fname.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Eemail.setError("enter a valid email address");
            valid = false;
        } else {
            Eemail.setError(null);
        }


        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            Epass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            Epass.setError(null);
        }

//        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
//            _reEnterPasswordText.setError("Password Do not match");
//            valid = false;
//        } else {
//            _reEnterPasswordText.setError(null);
//        }

        return valid;
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
