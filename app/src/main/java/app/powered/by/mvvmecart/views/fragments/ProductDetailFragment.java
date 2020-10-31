package app.powered.by.mvvmecart.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.powered.by.mvvmecart.R;
import app.powered.by.mvvmecart.adapters.ShopListAdapter;
import app.powered.by.mvvmecart.databinding.FragmentProductDetailBinding;
import app.powered.by.mvvmecart.databinding.FragmentShopBinding;
import app.powered.by.mvvmecart.viewmodels.ShopViewModel;

public class ProductDetailFragment extends Fragment {
    private FragmentProductDetailBinding detailBinding;
    private ShopListAdapter shopListAdapter;
    private ShopViewModel shopViewModel;
    private NavController navController;



    public ProductDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_product_detail, container, false);

        detailBinding= FragmentProductDetailBinding.inflate(inflater,container,false);

        return  detailBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shopViewModel= new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        detailBinding.setShopViewModel(shopViewModel);

    }
}