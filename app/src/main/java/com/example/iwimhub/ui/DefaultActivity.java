package com.example.iwimhub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iwimhub.R;
import com.example.iwimhub.ui.calendar.CalendarFragment;
import com.example.iwimhub.ui.home.HomeFragment;
import com.example.iwimhub.ui.hub.HubFragment;
import com.example.iwimhub.ui.notification.NotificationFragment;
import com.example.iwimhub.ui.account.AccountFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DefaultActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
        titleTextView = findViewById(R.id.header_title);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        ImageView accountImageView  = findViewById(R.id.navigation_account);
        ImageView backImageView = findViewById(R.id.back);

        backImageView.setOnClickListener(v->{
            getSupportFragmentManager().popBackStackImmediate();
            return ;
        });

        accountImageView.setOnClickListener(v->{
            openFragment(AccountFragment.newInstance(), "Account");
        });

        titleTextView.setText(R.string.title_home);
        openFragment(HomeFragment.newInstance());

    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    public void openFragment(Fragment fragment, String title) {
        titleTextView.setText(title);
        openFragment( fragment);
    }

    public void openFragmentWithBackStack(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void openFragmentWithBackStack(Fragment fragment, String title) {
        titleTextView.setText(title);
        openFragmentWithBackStack(fragment);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
        item -> {
            titleTextView.setText(item.getTitle());
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openFragment(HomeFragment.newInstance());
                    break;
                /*case R.id.navigation_notification:
                    openFragment(NotificationFragment.newInstance());
                    break;*/
                case R.id.navigation_hub:
                    openFragment(HubFragment.newInstance());
                    break;
                case R.id.navigation_calendar:
                    openFragment(CalendarFragment.newInstance());
                    break;
            }
            return false;
        };
}
