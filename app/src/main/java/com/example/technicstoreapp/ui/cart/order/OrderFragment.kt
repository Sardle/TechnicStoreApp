package com.example.technicstoreapp.ui.cart.order

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentOrderBinding
import com.example.technicstoreapp.ui.custom.CustomAlertDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<OrderViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser()
        viewModel.getTotalSum()

        viewModel.setupPriceWithDiscountLiveData()
        observeLoadingLiveData()
        setupOrderRecyclerView()
        observeCartTechnicLiveData()
        observePriceLiveData()
        observeUserLiveData()
    }

    private fun observeLoadingLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBarOrder.isVisible = it
            binding.orderGroup.isVisible = !it
        }
    }

    private fun observePriceLiveData() {
        viewModel.priceLiveData.observe(viewLifecycleOwner) { price ->
            binding.totalPrice.text = getString(R.string.set_price, price.toString())
            binding.addOrder.setOnClickListener {
                viewModel.update(
                    if (binding.address.text.toString() != "") binding.address.text.toString() else binding.address.hint.toString(),
                    price
                )
                showDialogAccess()
            }
        }
    }

    private fun showDialogAccess() {
        val customAlertDialog = this@OrderFragment.context?.let {
            CustomAlertDialog(it)
                .setImage(R.drawable.success)
                .setMessage(getString(R.string.log_out))
                .setBackText(getString(R.string.back_to_cart))
                .setOkText(getString(R.string.to_history))
                .setOnCancelable(false)
                .setOnBackClickListener { _, _ ->
                    val action = OrderFragmentDirections.actionOrderFragmentToNavigationCart()
                    findNavController().navigate(action)
                }
        }

        customAlertDialog?.show()
    }

    private fun observeUserLiveData() {
        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            binding.discountPoints.hint =
                getString(R.string.total_discount, user.discountPoints.toString())
            if (user.address != "") {
                binding.address.hint = user.address
            }
            with(binding) {
                done.setOnClickListener {
                    if (discountPoints.text.toString() != "" && discountPoints.text.toString().toInt() > user.discountPoints) {
                        Toast.makeText(
                            this@OrderFragment.context,
                            "Недостаточно баллов!",
                            Toast.LENGTH_SHORT
                        ).show()
                        discountPoints.setText("")
                    } else if (done.text.toString() == getString(R.string.done) && discountPoints.text.toString() != "") {
                        done.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.gray_sup
                            )
                        )
                        viewModel.calculatingPriceWithDiscount(
                            binding.discountPoints.text.toString().toInt()
                        )
                        discountPoints.setText("")
                        done.text = getString(R.string.cancel)
                        Toast.makeText(
                            this@OrderFragment.context,
                            "Скидка применена",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (done.text.toString() == getString(R.string.cancel)) {
                        done.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.purple_700
                            )
                        )
                        viewModel.calculatingPriceWithDiscount(0)
                        discountPoints.setText("")
                        done.text = getString(R.string.done)
                        Toast.makeText(
                            this@OrderFragment.context,
                            "Скидка отменена",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setupOrderRecyclerView() {
        val popularAdapter = OrderAdapter()

        binding.orderRecycler.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.getTechnicCart()
    }

    private fun observeCartTechnicLiveData() {
        viewModel.technicCartLiveData.observe(viewLifecycleOwner) { technicList ->
            binding.orderRecycler.adapter?.let { adapter ->
                if (adapter is OrderAdapter) {
                    adapter.setItems(technicList)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}