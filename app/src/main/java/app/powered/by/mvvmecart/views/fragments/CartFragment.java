package app.powered.by.mvvmecart.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import app.powered.by.mvvmecart.R;
import app.powered.by.mvvmecart.adapters.CartListAdapter;
import app.powered.by.mvvmecart.databinding.FragmentCartBinding;
import app.powered.by.mvvmecart.models.CartItem;
import app.powered.by.mvvmecart.viewmodels.ShopViewModel;


public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    private ShopViewModel shopViewModel;
    private FragmentCartBinding cartBinding;
    private NavController navController;




    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_cart, container, false);

        cartBinding= FragmentCartBinding.inflate(inflater,container,false);

        return cartBinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController= Navigation.findNavController(view);

        CartListAdapter cartListAdapter= new CartListAdapter(this);

        cartBinding.cartRecyclerView.setAdapter(cartListAdapter);
//        cartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL));
        cartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));


        shopViewModel= new ViewModelProvider(requireActivity()).get(ShopViewModel.class);

        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {

                cartListAdapter.submitList(cartItems);
                cartBinding.placeOrderButton.setEnabled(cartItems.size()>0);



            }
        });

        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                cartBinding.orderTotalTextView.setText("Total: $"+aDouble.toString());
            }
        });


        cartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_cartFragment_to_orderFragment);
            }
        });



    }

    @Override
    public void deleteItem(CartItem cartItem) {
//        Toast.makeText(getContext(), "Item: "+cartItem.getProduct().getName()+" Deleted", Toast.LENGTH_SHORT).show();

        shopViewModel.removeItemFromCart(cartItem);
    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {

        shopViewModel.changeQuantity(cartItem,quantity);

    }
}