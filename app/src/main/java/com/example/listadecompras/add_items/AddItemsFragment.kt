package com.example.listadecompras.add_items

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.listadecompras.ProductViewModel
import com.example.listadecompras.R
import com.example.listadecompras.data.entities.Product
import com.example.listadecompras.databinding.FragmentAddItemsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddItemsFragment : Fragment() {

    private var _binding: FragmentAddItemsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btSave.setOnClickListener {
            binding.etList.lineCount
            val lines = binding.etList.text.split("\n")
            val products = lines.map {
                Product(name = it)
            }
            viewModel.insertProducts(products)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}