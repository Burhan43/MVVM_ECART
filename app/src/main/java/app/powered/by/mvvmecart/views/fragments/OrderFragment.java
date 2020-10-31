package app.powered.by.mvvmecart.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.powered.by.mvvmecart.R;
import app.powered.by.mvvmecart.databinding.FragmentCartBinding;
import app.powered.by.mvvmecart.databinding.FragmentOrderBinding;
import app.powered.by.mvvmecart.viewmodels.ShopViewModel;

public class OrderFragment extends Fragment {
    private ShopViewModel shopViewModel;
    private FragmentOrderBinding orderBinding;
    private NavController navController;


    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_order, container, false);

        orderBinding= FragmentOrderBinding.inflate(inflater,container,false);

        return orderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        shopViewModel= new ViewModelProvider(requireActivity()).get(ShopViewModel.class);


        orderBinding.continueShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopViewModel.resetCart();
                navController.navigate(R.id.action_orderFragment_to_shopFragment);



            }
        });
    }
}