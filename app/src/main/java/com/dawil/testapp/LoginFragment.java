package com.dawil.testapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private NavController navController;
    private EditText txtUser;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView txtErr;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        txtUser = view.findViewById(R.id.txtUsername);
        txtPassword = view.findViewById(R.id.txtPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        txtErr = view.findViewById(R.id.txtErr);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String userName = txtUser.getText().toString();
        String passWord = txtPassword.getText().toString();

        if (userName.equals("Admin") && passWord.equals("Admin")){
            navController.navigate(R.id.action_loginFragment_to_homeFragment);
        }else {
            txtErr.setText(R.string.Err_login);
            txtUser.setText("");
            txtPassword.setText("");
        }
    }
}