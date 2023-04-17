package com.example.technicstoreapp.ui.cart.order

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicstoreapp.App
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentOrderBinding
import com.example.technicstoreapp.di.view_model.ViewModelFactory
import com.example.technicstoreapp.domain.models.UserData
import com.example.technicstoreapp.ui.custom.CustomAlertDialog
import javax.inject.Inject

class OrderFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: OrderViewModel by viewModels { factory }
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

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

        setupViewModel()
        addOrder()
        observeLoadingLiveData()
        setupOrderRecyclerView()
        observeCartTechnicLiveData()
        observePriceLiveData()
        observeUserLiveData()
        binding.backOrder.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            getUser()
            getTotalSum()
            setupPriceWithDiscountLiveData()
        }
    }

    private fun observeLoadingLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) { show ->
            binding.progressBarOrder.isVisible = show
            binding.orderGroup.isVisible = !show
        }
    }

    private fun observePriceLiveData() {
        viewModel.priceLiveData.observe(viewLifecycleOwner) { price ->
            binding.totalPrice.text = getString(R.string.set_price, price.toString())
        }
    }

    private fun addOrder() {
        binding.addOrder.setOnClickListener {
            if (checkAddressEmpty()) {
                viewModel.update(
                    if (binding.address.text.toString() != EMPTY_STRING) binding.address.text.toString() else binding.address.hint.toString(),
                    binding.totalPrice.text.toString().replace("\\s.*".toRegex(), EMPTY_STRING)
                        .toDouble()
                )
                showPurchaseSuccessNotification()
                showDialogAccess()
            }
        }
    }

    private fun checkAddressEmpty(): Boolean {
        if (binding.address.text.toString() == EMPTY_STRING && binding.address.hint.toString() == getString(
                R.string.your_address
            )
        ) {
            binding.address.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_error_edittext_order)
            return false
        }
        return true
    }

    @SuppressLint("MissingPermission")
    private fun showPurchaseSuccessNotification() {
        val notification = NotificationCompat.Builder(requireActivity(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_electro)
            .setContentTitle(getString(R.string.success_order_title))
            .setContentText(getString(R.string.success_order_description))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        with(NotificationManagerCompat.from(requireActivity())) {
            notify(NOTIFICATION_ID, notification)
        }
    }

    private fun showDialogAccess() {
        val customAlertDialog =
            CustomAlertDialog(requireContext())
                .setImage(R.drawable.ic_success)
                .setMessage(getString(R.string.order_access))
                .setBackText(getString(R.string.back_to_cart))
                .setOkText(getString(R.string.to_profile))
                .setOnCancelable(false)
                .setOnBackClickListener { _, _ ->
                    val action = OrderFragmentDirections.actionOrderFragmentToNavigationCart()
                    findNavController().navigate(action)
                }

        customAlertDialog.show()
    }

    private fun observeUserLiveData() {
        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            binding.discountPoints.hint =
                getString(R.string.total_discount, user.discountPoints.toString())
            if (user.address != EMPTY_STRING) {
                binding.address.hint = user.address
            }
            checkDiscount(user)
        }
    }

    private fun checkDiscount(user: UserData) {
        with(binding) {
            done.setOnClickListener {
                if (discountPoints.text.toString() != EMPTY_STRING && discountPoints.text.toString()
                        .toInt() > user.discountPoints) {
                    showToastWithMessage(getString(R.string.not_enough_points))
                    clearDiscountPointsInput()
                } else if (done.text.toString() == getString(R.string.done) && discountPoints.text.toString() != EMPTY_STRING) {
                    setDoneButtonTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.gray_sup
                        )
                    )
                    viewModel.calculatingPriceWithDiscount(discountPoints.text.toString().toInt())
                    clearDiscountPointsInput()
                    setDoneButtonText(getString(R.string.cancel))
                    showToastWithMessage(getString(R.string.discount_applied))
                } else if (done.text.toString() == getString(R.string.cancel)) {
                    setDoneButtonTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.purple_700
                        )
                    )
                    viewModel.calculatingPriceWithDiscount(ZERO)
                    clearDiscountPointsInput()
                    setDoneButtonText(getString(R.string.done))
                    showToastWithMessage(getString(R.string.discount_cancelled))
                }
            }
        }
    }

    private fun showToastWithMessage(message: String) {
        Toast.makeText(
            this@OrderFragment.context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun clearDiscountPointsInput() {
        binding.discountPoints.setText(EMPTY_STRING)
    }

    private fun setDoneButtonTextColor(color: Int) {
        binding.done.setTextColor(color)
    }

    private fun setDoneButtonText(text: String) {
        binding.done.text = text
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

    companion object {
        private const val EMPTY_STRING = ""

        private const val CHANNEL_ID = "my_channel_id"

        private const val NOTIFICATION_ID = 1

        private const val ZERO = 0
    }
}