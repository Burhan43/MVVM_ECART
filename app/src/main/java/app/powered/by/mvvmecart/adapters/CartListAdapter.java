package app.powered.by.mvvmecart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.powered.by.mvvmecart.databinding.CartRowBinding;
import app.powered.by.mvvmecart.models.CartItem;

public class CartListAdapter extends ListAdapter<CartItem, CartListAdapter.CartVh> {
    private CartInterface cartInterface;

    public CartListAdapter(CartInterface cartInterface) {
        super(CartItem.itemCallback);
        this.cartInterface=cartInterface;
    }

    @NonNull
    @Override
    public CartVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        CartRowBinding cartRowBinding= CartRowBinding.inflate(inflater,parent,false);


        return new  CartVh(cartRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartVh holder, int position) {

        holder.cartRowBinding.setCartItem(getItem(position));
        holder.cartRowBinding.executePendingBindings();
    }

    class CartVh extends RecyclerView.ViewHolder{

        CartRowBinding cartRowBinding;
        public CartVh(@NonNull CartRowBinding cartRowBinding) {
            super(cartRowBinding.getRoot());
            this.cartRowBinding= cartRowBinding;
            cartRowBinding.deleteProductButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartInterface.deleteItem(getItem(getAdapterPosition()));
                }
            });


            cartRowBinding.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int quantity=i+1;
                    if (quantity==getItem(getAdapterPosition()).getQuantity()){return;}
                    cartInterface.changeQuantity(getItem(getAdapterPosition()),quantity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    public interface CartInterface
    {
        void deleteItem(CartItem cartItem);
        void changeQuantity(CartItem cartItem,int Quantity);
    }
}
