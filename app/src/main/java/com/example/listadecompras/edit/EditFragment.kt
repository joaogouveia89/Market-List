package com.example.listadecompras.edit

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.listadecompras.ProductViewModel
import com.example.listadecompras.R
import com.example.listadecompras.data.entities.Product
import com.example.listadecompras.databinding.FragmentEditBinding
import com.example.listadecompras.databinding.FragmentListBinding
import android.widget.Toast

import com.example.listadecompras.MainActivity

import android.content.DialogInterface




class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null

    private val viewModel: ProductViewModel by activityViewModels()

    val product: Product by lazy {
        arguments?.get("product") as Product
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.productNameEditScreen.text = product.name
        binding.etProductValue.setText(product.price.toString())
        binding.etProductQuantity.setText(product.quantity.toString())
        binding.btSave.setOnClickListener {
            product.quantity = binding.etProductQuantity.text.toString().toInt()
            product.price = binding.etProductValue.text.toString().toDouble()
            viewModel.updateProduct(product)
            findNavController().popBackStack()
        }

        binding.btDelete.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Confirmação")
                .setMessage("Quer mesmo excluir ${product.name}?")
                .setIcon(android.R.drawable.ic_delete)
                .setPositiveButton(R.string.yes) { _, _ ->
                    viewModel.deleteProduct(product)
                    findNavController().popBackStack()
                }
                .setNegativeButton(R.string.no, null).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}