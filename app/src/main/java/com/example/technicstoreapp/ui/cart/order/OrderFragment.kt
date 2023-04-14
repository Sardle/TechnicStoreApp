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
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentOrderBinding
import com.example.technicstoreapp.di.app.App
import com.example.technicstoreapp.di.view_model.ViewModelFactory
import com.example.technicstoreapp.domain.UserData
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
        back()
    }

    private fun setupViewModel() {
        viewModel.getUser()
        viewModel.getTotalSum()
        viewModel.setupPriceWithDiscountLiveData()
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
        }
    }

    private fun addOrder() {
        binding.addOrder.setOnClickListener {
            if (checkAddressEmpty()) {
                viewModel.update(
                    if (binding.address.text.toString() != "") binding.address.text.toString() else binding.address.hint.toString(),
                    binding.totalPrice.text.toString().replace("\\s.*".toRegex(), "").toDouble()
                )
                showPurchaseSuccessNotification()
                showDialogAccess()
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun checkAddressEmpty(): Boolean {
        if (binding.address.text.toString() == "" && binding.address.hint.toString() == getString(R.string.your_address)) {
            binding.address.background =
                requireContext().getDrawable(R.drawable.bg_error_edittext_order)
            return false
        }
        return true
    }

    @SuppressLint("MissingPermission")
    private fun showPurchaseSuccessNotification() {
        val channelId = "my_channel_id"
        val notificationId = 1

        val notification = NotificationCompat.Builder(requireActivity(), channelId)
            .setSmallIcon(R.drawable.ic_electro)
            .setContentTitle("Успешная покупка")
            .setContentText("Покупка успешно завершена")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        with(NotificationManagerCompat.from(requireActivity())) {
            notify(notificationId, notification)
        }
    }

    private fun showDialogAccess() {
        val customAlertDialog = this@OrderFragment.context?.let {
            CustomAlertDialog(it)
                .setImage(R.drawable.ic_success)
                .setMessage(getString(R.string.order_access))
                .setBackText(getString(R.string.back_to_cart))
                .setOkText(getString(R.string.to_profile))
                .setOnBackClickListener { _, _ -> }
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
            checkDiscount(user)
        }
    }

    private fun checkDiscount(user: UserData) {
        with(binding) {
            done.setOnClickListener {
                if (discountPoints.text.toString() != "" && discountPoints.text.toString().toInt() > user.discountPoints) {
                    showToastWithMessage(getString(R.string.not_enough_points))
                    clearDiscountPointsInput()
                } else if (done.text.toString() == getString(R.string.done) && discountPoints.text.toString() != "") {
                    setDoneButtonTextColor(ContextCompat.getColor(requireContext(), R.color.gray_sup))
                    viewModel.calculatingPriceWithDiscount(discountPoints.text.toString().toInt())
                    clearDiscountPointsInput()
                    setDoneButtonText(getString(R.string.cancel))
                    showToastWithMessage(getString(R.string.discount_applied))
                } else if (done.text.toString() == getString(R.string.cancel)) {
                    setDoneButtonTextColor(ContextCompat.getColor(requireContext(), R.color.purple_700))
                    viewModel.calculatingPriceWithDiscount(0)
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
        binding.discountPoints.setText("")
    }

    private fun setDoneButtonTextColor(color: Int) {
        binding.done.setTextColor(color)
    }

    private fun setDoneButtonText(text: String) {
        binding.done.text = text
    }

    private fun back() {
        binding.backOrder.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
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