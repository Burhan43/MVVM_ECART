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

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import app.powered.by.mvvmecart.R;
import app.powered.by.mvvmecart.adapters.ShopListAdapter;
import app.powered.by.mvvmecart.databinding.FragmentShopBinding;
import app.powered.by.mvvmecart.models.Product;
import app.powered.by.mvvmecart.viewmodels.ShopViewModel;


public class ShopFragment extends Fragment implements ShopListAdapter.ShopInterface {

    private FragmentShopBinding shopBinding;
    private ShopListAdapter shopListAdapter;
    private ShopViewModel shopViewModel;
    private NavController navController;

    public ShopFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_shop, container, false);

//        instead we will use data binding here

        shopBinding= FragmentShopBinding.inflate(inflater,container,false);

        return  shopBinding.getRoot();


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shopListAdapter= new ShopListAdapter(this);

        shopBinding.shopRecyclerView.setAdapter(shopListAdapter);

        shopBinding.shopRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL));
        shopBinding.shopRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));

        shopViewModel=new ViewModelProvider(requireActivity()).get(ShopViewModel.class);

        shopViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                shopListAdapter.submitList(products);
            }
        });

        navController= Navigation.findNavController(view);


    }

    @Override
    public void addItem(Product product) {

        boolean isAdded=shopViewModel.addItemToCart(product);
//        Toast.makeText(getContext(), ""+product.getName()+" "+isAdded, Toast.LENGTH_SHORT).show();


        if (isAdded)
        {
            Snackbar.make(requireView(),product.getName()+" added to cart",
                    Snackbar.LENGTH_LONG).setAction("checkout", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navController.navigate(R.id.action_shopFragment_to_cartFragment);
                }
            }).show();

        }
        else
            {
                Snackbar.make(requireView(),product.getName()+"something went wrong",
                        Snackbar.LENGTH_LONG).show();
            }


    }

    @Override
    public void onItemClick(Product product) {

        shopViewModel.setProduct(product);

        navController.navigate(R.id.action_shopFragment_to_productDetailFragment);


    }
}