package com.vanced.manager.ui.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.vanced.manager.R
import com.vanced.manager.databinding.FragmentChosenPreferencesBinding

class ChosenPreferenceDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentChosenPreferencesBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.apply { setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chosen_preferences, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireActivity().getSharedPreferences("installPrefs", Context.MODE_PRIVATE)

        binding.chosenTheme.text = requireActivity().getString(R.string.chosen_theme, prefs.getString("theme", "dark"))
        binding.chosenLang.text = requireActivity().getString(R.string.chosen_lang, prefs.getString("lang", "en"))

        binding.chosenPrefsClose.setOnClickListener { dismiss() }
        binding.chosenPrefsReset.setOnClickListener {
            prefs.edit {
                putString("theme", "dark")
                putString("lang", "en")
                putBoolean("valuesModified", false)
            }
            dismiss()
        }
    }
}
