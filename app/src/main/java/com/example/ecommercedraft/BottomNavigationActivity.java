package com.example.ecommercedraft;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ecommercedraft.fragment.CartFragment;
import com.example.ecommercedraft.fragment.HomeFragment;
import com.example.ecommercedraft.fragment.NotificationFragment;
import com.example.ecommercedraft.fragment.ProfileFragment;
import com.example.ecommercedraft.fragment.WishListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class BottomNavigationActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

//    Button btnLogOut;
//    FirebaseAuth mAuth;
    BottomNavigationView navView;

    HomeFragment homeFragment = new HomeFragment();
    CartFragment cartFragment = new CartFragment();
    WishListFragment wishListFragment = new WishListFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    ProfileFragment profileFragment = new ProfileFragment();


    //add to cart
    private ViewPagerAdapter adapter;
    private View viewEndAnimation;
    private ImageView viewAnimation;


    //Auto Image Slider
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private List<Photo> mListPhoto;
    private Timer mTimer;


    //Database url for write and read from realtime database
    public static final String DATABASE_URL = "https://ecommercedraft-default-rtdb.firebaseio.com";


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


        //image slider
        viewPager = findViewById(R.id.viewpager);
        circleIndicator = findViewById(R.id.circle_indicator);
        mListPhoto = getListPhoto();
        photoAdapter = new PhotoAdapter(this, mListPhoto );
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSlideImages();


        //add to cart

        viewEndAnimation = findViewById(R.id.view_end_animation);
        viewAnimation = findViewById(R.id.view_animation);

    }
    private List<Photo> getListPhoto () {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.adidas_logo));
        list.add(new Photo(R.drawable.nike_logo));
        list.add(new Photo(R.drawable.mlb_logo));
        list.add(new Photo(R.drawable.puma_logo));
        return list;
    }
    private void autoSlideImages() {
        if(mListPhoto == null || mListPhoto.isEmpty() || viewPager == null) {
            return;
        }

        //Init timer
        if(mTimer == null) {
            mTimer = new Timer();
        }

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int toalItem = mListPhoto.size() - 1;
                        if(currentItem < toalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    //add to cart
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public View getViewEndAnimation() {
        return viewEndAnimation;
    }

    public ImageView getViewAnimation() {
        return viewAnimation;
    }

    public void setCountProductInCart(int count) {

    }
    public void showPopUp(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,cartFragment).commit();
                Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,wishListFragment).commit();
                Toast.makeText(this, "Wishlist clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,notificationFragment).commit();
                Toast.makeText(this, "Notification clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show();
            default:
                return false;
        }
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