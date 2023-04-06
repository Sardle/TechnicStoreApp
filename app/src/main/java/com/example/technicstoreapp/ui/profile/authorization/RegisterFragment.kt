package com.example.technicstoreapp.ui.profile.authorization

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.view.allViews
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.technicstoreapp.R
import com.example.technicstoreapp.databinding.FragmentRegisterBinding
import com.example.technicstoreapp.domain.UserData
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkDataEntry()
        comeToProfilePage()
        leadToFormat(FORMAT_NUMBER, binding.phone)
        leadToFormat(FORMAT_DATE, binding.dateOfBirth)
        removeStrokeForAll()
    }

    private fun checkDataEntry() {
        binding.registerBtn.setOnClickListener {
            val checkPassword = checkPassword()
            val areAllEditTextsFilled = areAllEditTextsFilled()
            val checkDate = checkDate()
            val checkNumber = checkNumber()
            if (checkPassword && areAllEditTextsFilled && checkDate && checkNumber) {
                binding.progressBarRegister.isVisible = true
                binding.registerGroup.isVisible = false
                viewModel.addUserToList(
                    UserData(
                        "",
                        binding.name.text.toString(),
                        binding.passwordReg.text.toString(),
                        binding.phone.text.toString(),
                        "",
                        binding.email.text.toString(),
                        0,
                        emptyList(),
                        binding.dateOfBirth.text.toString()
                    )
                )
            }
        }
    }

    private fun comeToProfilePage() {
        viewModel.checkLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBarRegister.isVisible = false
                val action = RegisterFragmentDirections.actionRegisterFragmentToAuthSuccessDialog()
                findNavController().navigate(action)
            } else {
                binding.registerGroup.isVisible = true
                binding.progressBarRegister.isVisible = false
                binding.phone.error = getString(R.string.error_to_number_is_occupied)
                binding.phone.background =
                    this@RegisterFragment.context?.let { context ->
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.error_style_edittext
                        )
                    }
            }
        }
    }

    private fun leadToFormat(format: String, editText: EditText) {
        val slots = UnderscoreDigitSlotsParser().parseSlots(format)
        val mask = MaskImpl.createTerminated(slots)
        mask.isHideHardcodedHead = true
        val formatWatcher: FormatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(editText)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun areAllEditTextsFilled(): Boolean {
        var allFilled = true
        for (view in requireView().allViews) {
            if (view is EditText) {
                val text = view.text.toString().trim()
                if (text.isEmpty()) {
                    allFilled = false
                    view.background = requireContext().getDrawable(R.drawable.error_style_edittext)
                } else {
                    view.background = requireContext().getDrawable(R.drawable.style_login_edittext)
                }
            }
        }
        return allFilled
    }

    private fun removeStrokeForAll() {
        for (view in requireView().allViews) {
            if (view is EditText) {

                val textWatcher = object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    @SuppressLint("UseCompatLoadingForDrawables")
                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        view.background =
                            requireContext().getDrawable(R.drawable.style_login_edittext)
                    }

                    override fun afterTextChanged(s: Editable?) {}
                }
                view.addTextChangedListener(textWatcher)
            }
        }
    }

    private fun checkNumber(): Boolean {
        if (binding.phone.text.toString().length != 19) {
            binding.phone.error = getString(R.string.error_to_number)
            binding.phone.background =
                this@RegisterFragment.context?.let { context ->
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.error_style_edittext
                    )
                }
            return false
        }
        return true
    }

    private fun checkPassword(): Boolean {
        if (!binding.passwordReg.text.toString().matches(REGEX_PASSWORD.toRegex())) {
            binding.passwordReg.error = getString(R.string.error_to_password)
            binding.passwordReg.background =
                this@RegisterFragment.context?.let { context ->
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.error_style_edittext
                    )
                }
            return false
        }
        return true
    }

    private fun checkDate(): Boolean {
        if (!binding.dateOfBirth.text.toString().matches(REGEX_DATE.toRegex())) {
            binding.dateOfBirth.error = getString(R.string.error_to_date)
            binding.dateOfBirth.background =
                this@RegisterFragment.context?.let { context ->
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.error_style_edittext
                    )
                }
            return false
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\w{8,}\$"

        private const val REGEX_DATE = "^(0[1-9]|[12]\\d|3[01])\\.(0[1-9]|1[0-2])\\.\\d{4}\$"

        private const val FORMAT_NUMBER = "+375 (__) ___-__-__"

        private const val FORMAT_DATE = "__.__.____"
    }
}