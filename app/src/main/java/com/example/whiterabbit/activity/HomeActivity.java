package com.example.whiterabbit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.whiterabbit.R;
import com.example.whiterabbit.viewmodel.HomeActivityViewModel;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HomeActivity extends AppCompatActivity {

    private NavController navController;
    private HomeActivityViewModel mViewModel;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mViewModel = new ViewModelProvider(this).get(HomeActivityViewModel.class);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = host.getNavController();


        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.homeFragment);


        appBarConfiguration = new AppBarConfiguration.Builder(
                topLevelDestinations)
                .build();


        NavigationUI.setupWithNavController(toolbar,navController);
        //NavigationUI.setupActionBarWithNavController(this, navController);
    }
}