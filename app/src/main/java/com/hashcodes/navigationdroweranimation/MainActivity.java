package com.hashcodes.navigationdroweranimation;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hashcodes.navigationdroweranimation.AllFragments.AddPet;
import com.hashcodes.navigationdroweranimation.AllFragments.Adoption;
import com.hashcodes.navigationdroweranimation.AllFragments.Donation;
import com.hashcodes.navigationdroweranimation.AllFragments.Favorites;
import com.hashcodes.navigationdroweranimation.AllFragments.Messages;
import com.hashcodes.navigationdroweranimation.AllFragments.Profile;
import com.hashcodes.navigationdroweranimation.menu.DrawerAdapter;
import com.hashcodes.navigationdroweranimation.menu.DrawerItem;
import com.hashcodes.navigationdroweranimation.menu.SimpleItem;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity  implements DrawerAdapter.OnItemSelectedListener {

    private static final int POS_PROFILE = 0;
    private static final int POS_ADOPTION = 1;
    private static final int POS_DONATION = 2;
    private static final int POS_ADD_PET = 3;
    private static final int POS_FAVORITES = 4;
    private static final int POS_MESSAGES = 5;


    private String[] screenTitles;
    private Drawable[] screenIcons;
    private Toolbar toolbar;

    private SlidingRootNav slidingRootNav;
//    private LinearLayout setting;
//    private Button logOut;
    private ImageButton opendrower;

    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =  findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);



        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();


        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_PROFILE).setChecked(true),
                createItemFor(POS_ADOPTION),
                createItemFor(POS_DONATION),
                createItemFor(POS_ADD_PET),
                createItemFor(POS_FAVORITES),
                createItemFor(POS_MESSAGES)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_PROFILE);
    }


    @Override
    public void onItemSelected(int position) {
        if (position== POS_PROFILE){
            Toast.makeText(this, "POS_PROFILE", Toast.LENGTH_SHORT).show();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,new Profile());
            fragmentTransaction.commit();
        }else if (position== POS_ADOPTION){
            Toast.makeText(this, "POS_ADOPTION", Toast.LENGTH_SHORT).show();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,new Adoption());
            fragmentTransaction.commit();
        }else if (position== POS_DONATION){
            Toast.makeText(this, "POS_DONATION", Toast.LENGTH_SHORT).show();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,new Donation());
            fragmentTransaction.commit();
        }else if (position== POS_ADD_PET){
            Toast.makeText(this, "POS_ADD_PET", Toast.LENGTH_SHORT).show();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,new AddPet());
            fragmentTransaction.commit();
        }else if (position== POS_FAVORITES){
            Toast.makeText(this, "POS_FAVORITES", Toast.LENGTH_SHORT).show();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,new Favorites());
            fragmentTransaction.commit();
        }else if (position == POS_MESSAGES) {
            Toast.makeText(this, "POS_FAVORITES", Toast.LENGTH_SHORT).show();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,new Messages());
            fragmentTransaction.commit();

        }else {
            Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
        }
        slidingRootNav.closeMenu();

    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorPrimary))
                .withSelectedIconTint(color(R.color.colorAccent))
                .withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

}

