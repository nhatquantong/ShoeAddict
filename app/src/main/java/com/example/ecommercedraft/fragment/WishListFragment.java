package com.example.ecommercedraft.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommercedraft.AnimationUtil;
import com.example.ecommercedraft.BottomNavigationActivity;
import com.example.ecommercedraft.Product;
import com.example.ecommercedraft.ProductAdapter;
import com.example.ecommercedraft.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishListFragment extends Fragment {

    private RecyclerView rcvProduct;
    private View mView;
    private BottomNavigationActivity bottomNavigationActivity;
    private ProductAdapter productAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WishListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishListFragment newInstance(String param1, String param2) {
        WishListFragment fragment = new WishListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_wish_list, container, false);
        bottomNavigationActivity = (BottomNavigationActivity) getActivity();

        rcvProduct = mView.findViewById(R.id.rcv_product);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(bottomNavigationActivity);
        rcvProduct.setLayoutManager(linearLayoutManager);

        productAdapter = new ProductAdapter();
        productAdapter.setData(getListProduct(), new ProductAdapter.IClickAddToCartListener() {
            @Override
            public void onClickAddToCart(ImageView imgAddToCart, Product product) {
                AnimationUtil.translateAnimation(bottomNavigationActivity.getViewAnimation(), imgAddToCart, bottomNavigationActivity.getViewEndAnimation(), new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        product.setAddToCart(true);
                        imgAddToCart.setBackgroundResource(R.drawable.bg_grey_corner_6);
                        productAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
        rcvProduct.setAdapter(productAdapter);

        return mView;
    }
    private List<Product> getListProduct() {
        List<Product> list = new ArrayList<>();

        list.add(new Product(R.drawable.shoe, "Product name 1", "This is description"));
        list.add(new Product(R.drawable.shoe, "Product name 2", "This is description"));
        list.add(new Product(R.drawable.shoe, "Product name 3", "This is description"));
        list.add(new Product(R.drawable.shoe, "Product name 4", "This is description"));
        list.add(new Product(R.drawable.shoe, "Product name 5", "This is description"));
        list.add(new Product(R.drawable.shoe, "Product name 6", "This is description"));
        list.add(new Product(R.drawable.shoe, "Product name 7", "This is description"));
        list.add(new Product(R.drawable.shoe, "Product name 8", "This is description"));
        list.add(new Product(R.drawable.shoe, "Product name 9", "This is description"));
        list.add(new Product(R.drawable.shoe, "Product name 10", "This is description"));

        return list;
    }
}