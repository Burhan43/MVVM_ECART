package app.powered.by.mvvmecart.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import app.powered.by.mvvmecart.models.CartItem;
import app.powered.by.mvvmecart.models.Product;
import app.powered.by.mvvmecart.repos.CartRepo;
import app.powered.by.mvvmecart.repos.ShopRepo;

public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo= new ShopRepo();
    CartRepo cartRepo= new CartRepo();
    MutableLiveData<Product> mutableProduct= new MutableLiveData<>();

   public LiveData<List<Product>> getProducts() { return shopRepo.getProducts(); }
   public void setProduct(Product product) { mutableProduct.setValue(product); }
   public LiveData<Product> getProduct(){return mutableProduct;}

   public LiveData<List<CartItem>> getCart(){return cartRepo.getCart();}

   public boolean addItemToCart(Product product){return cartRepo.addItemToCart(product);}

   public void removeItemFromCart(CartItem cartItem){cartRepo.removeItemFromCart(cartItem);}
   public void changeQuantity(CartItem cartItem,int quantity){cartRepo.changeQuantity(cartItem,quantity);}

   public LiveData<Double> getTotalPrice()
   {
       return cartRepo.getTotalPrice();

   }

   public void resetCart()
   {
       cartRepo.initCart();
   }
}
