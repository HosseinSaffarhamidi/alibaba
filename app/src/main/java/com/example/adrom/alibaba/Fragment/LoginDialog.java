package com.example.adrom.alibaba.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adrom.alibaba.R;

import java.util.HashMap;
import java.util.Map;

public class LoginDialog extends DialogFragment {

    EditText edtEmail,edtPass;
    Button btnSignup,btnLogin;
    OnSignupClicked onSignupClicked;
    View view;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        view = LayoutInflater.from(getContext()).inflate(R.layout.login_dialog,null);
        setupViews();
        builder.setView(view);
        return builder.create();
    }


    public void setOnSignupClicked(OnSignupClicked onSignupClicked) {
        this.onSignupClicked = onSignupClicked;
    }

    private void setupViews() {
        edtEmail = (EditText) view.findViewById(R.id.edt_loginDialog_email);
        edtPass = (EditText)view.findViewById(R.id.edt_loginDialog_pass);
        btnLogin = (Button)view.findViewById(R.id.btn_loginDialog_login);
        btnSignup = (Button)view.findViewById(R.id.btn_loginDialog_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                userSignup(email, pass);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(edtEmail.getText().toString(), edtPass.getText().toString());
            }
        });

    }

    private void login(final String myEmail, final String pass) {
        String url = "http://adromsh.ir/alibaba/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("not found")) {
                    Toast.makeText(getContext(), "پست الکترونیک یا رمز عبور اشتباه است", Toast.LENGTH_SHORT).show();
                } else {
                    onSignupClicked.onClicked(response);
                    dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("LOG", "onErrorResponse: " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", myEmail);
                params.put("pass", pass);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void userSignup(final String email, final String pass) {
        String url = "http://adromsh.ir/alibaba/signup.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onSignupClicked.onClicked(response);
                dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onErrorResponse: " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("email", email);
                param.put("pass", pass);
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


    public interface OnSignupClicked {
        void onClicked(String email);
    }
}
