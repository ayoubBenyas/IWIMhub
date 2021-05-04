package com.example.iwimhub.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iwimhub.R;
import com.example.iwimhub.ui.calendar.CalendarFragment;
import com.example.iwimhub.ui.hub.HubFragment;
import com.example.iwimhub.ui.notification.NotificationFragment;
import com.example.iwimhub.ui.account.AccountFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DefaultActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private TextView titleTextView;
    private ImageView accountImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
        titleTextView = findViewById(R.id.header_title);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        accountImageView  = findViewById(R.id.navigation_account);
        accountImageView.setOnClickListener(v->{
            titleTextView.setText("Account");
            openFragment(AccountFragment.newInstance());
        });

        titleTextView.setText(R.string.title_notification);
        openFragment(NotificationFragment.newInstance());

    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
        item -> {
            titleTextView.setText(item.getTitle());
            switch (item.getItemId()) {
                case R.id.navigation_notification:
                    openFragment(NotificationFragment.newInstance());
                    break;
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
