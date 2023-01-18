package com.example.ecommercedraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommercedraft.fragment.CartFragment;
import com.example.ecommercedraft.fragment.HomeFragment;
import com.example.ecommercedraft.fragment.NotificationFragment;
import com.example.ecommercedraft.fragment.ProfileFragment;
import com.example.ecommercedraft.fragment.WishListFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BottomNavigationActivity extends AppCompatActivity {

//    Button btnLogOut;
//    FirebaseAuth mAuth;
    BottomNavigationView navView;

    HomeFragment homeFragment = new HomeFragment();
    CartFragment cartFragment = new CartFragment();
    WishListFragment wishListFragment = new WishListFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    ProfileFragment profileFragment = new ProfileFragment();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomnavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
//
//        btnLogOut = findViewById(R.id.logout);
//        mAuth = FirebaseAuth.getInstance();
//
//        btnLogOut.setOnClickListener(view ->{
//            mAuth.signOut();
//            Intent intent = new Intent(BottomNavigationActivity.this,LoginActivity.class);
//            startActivity(intent);
//        });



        navView = findViewById(R.id.bottom_nav);
//        setupViewPager();
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()){
                   case R.id.home:
                       getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                       return true;
                   case R.id.cart:
                       getSupportFragmentManager().beginTransaction().replace(R.id.container,cartFragment).commit();
                       return true;
                   case R.id.wishlist:
                       getSupportFragmentManager().beginTransaction().replace(R.id.container,wishListFragment).commit();
                       return true;
                   case R.id.notification:
                       getSupportFragmentManager().beginTransaction().replace(R.id.container,notificationFragment).commit();
                       return true;
                   case R.id.profile:
                       getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                       return true;
               }
               return false;
           }
       });



    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user == null){
//            Intent intent = new Intent(BottomNavigationActivity.this,LoginActivity.class);
//            startActivity(intent);
//        }
//    }
}