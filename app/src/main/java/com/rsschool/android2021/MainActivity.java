package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstFragment.CallBacks, SecondFragment.CallBacks {

    private Fragment fragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        fragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        fragment = SecondFragment.newInstance(min, max);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .commit();

    }


    @Override
    public void onGeneration(int min, int max) {
        openSecondFragment(min, max);
    }

    @Override
    public void getGeneration(int random) {
        openFirstFragment(random);
    }
}
