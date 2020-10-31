package app.powered.by.mvvmecart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import app.powered.by.mvvmecart.databinding.ShopRowBinding;
import app.powered.by.mvvmecart.models.Product;

public class ShopListAdapter extends ListAdapter<Product, ShopListAdapter.ShopViewHolder> {


    private ShopInterface shopInterface;

    public ShopListAdapter(ShopInterface shopInterface) {
        super(Product.itemCallback);
        this.shopInterface=shopInterface;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ShopRowBinding rowBinding=ShopRowBinding.inflate(inflater,parent,false);

        rowBinding.setShopInterface(shopInterface);
        return new ShopViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {

        Product product=getItem(position);

        holder.rowBinding.setProduct(product);

        holder.rowBinding.executePendingBindings();


    }

    class ShopViewHolder extends RecyclerView.ViewHolder{
        ShopRowBinding rowBinding;

        public ShopViewHolder(ShopRowBinding rowBinding) {
            super(rowBinding.getRoot());
            this.rowBinding=rowBinding;

            this.rowBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }


    public interface ShopInterface
    {
        void addItem(Product product);
        void onItemClick(Product product);
    }
}
