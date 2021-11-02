package com.example.listadecompras.list

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.listadecompras.ProductViewModel
import com.example.listadecompras.R
import com.example.listadecompras.data.entities.Product
import com.example.listadecompras.databinding.FragmentListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    private val viewModel: ProductViewModel by activityViewModels()

    private val editDirection = ListFragmentDirections.actionListFragmentToEditFragment()

    private var adapter: ProductListAdapter? = null

    private val productSelectObserver = Observer<Product> {
        editDirection.product = it
        findNavController().navigate(editDirection)
    }

    private val divider : DividerItemDecoration by lazy{
        DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL).apply {
            setDrawable(ShapeDrawable().apply {
                intrinsicHeight = resources.getDimensionPixelOffset(R.dimen.dp_1)
                paint.color = Color.BLACK //
            })
        }
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val productsObserver = Observer<List<Product>>{
        adapter?.updateList(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        adapter = ProductListAdapter()
        viewModel.products.observe(viewLifecycleOwner, productsObserver)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchProducts()
        binding.productList.adapter = adapter

        adapter?.selectedListItem?.observe(viewLifecycleOwner, productSelectObserver)
        binding.productList.addItemDecoration(divider)
        binding.addItems.setOnClickListener {
            findNavController().navigate(R.id.action_ListFragment_to_AddItemsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }
}