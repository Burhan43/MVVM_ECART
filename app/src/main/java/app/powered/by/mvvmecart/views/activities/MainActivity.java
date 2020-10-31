package app.powered.by.mvvmecart.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.powered.by.mvvmecart.R;
import app.powered.by.mvvmecart.models.CartItem;
import app.powered.by.mvvmecart.viewmodels.ShopViewModel;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private ShopViewModel shopViewModel;
    private int cartQuantity=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController= Navigation.findNavController(this,R.id.nav_host_fragment);


        NavigationUI.setupActionBarWithNavController(this,navController);
        shopViewModel=new ViewModelProvider(this).get(ShopViewModel.class);

        shopViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
//                Toast.makeText(MainActivity.this, "onChanged: "+cartItems.size(), Toast.LENGTH_SHORT).show();

                int quantity=0;
                for (CartItem cartItem: cartItems)
                {
                    quantity +=cartItem.getQuantity();


                }

                cartQuantity= quantity;
                invalidateOptionsMenu();
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        MenuItem menuItem=menu.findItem(R.id.cartFragment);

        View actionView= menuItem.getActionView();


        TextView cartBadgeTv=actionView.findViewById(R.id.cart_badge_text_view);


        cartBadgeTv.setText(String.valueOf(cartQuantity));

        cartBadgeTv.setVisibility(cartQuantity==0? View.GONE : View.VISIBLE);
        actionView.setOnClickListener(view -> onOptionsItemSelected(menuItem));

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item,navController)|| super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }
}