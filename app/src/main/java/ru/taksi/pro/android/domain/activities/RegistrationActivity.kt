package ru.taksi.pro.android.domain.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.RegistrMainBinding
import ru.taksi.pro.android.domain.fragments.ChoiceTariffFragment
import ru.taksi.pro.android.domain.helpers.SpannableHelper

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding: RegistrMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.registr_main)
        binding.headerTitle.text = SpannableHelper.spannableTaxi(getString(R.string.taxi_pro))
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
 //           .add(R.id.container, RegistrationFragment())
            .add(R.id.container, ChoiceTariffFragment())
            .commit()
    }
}