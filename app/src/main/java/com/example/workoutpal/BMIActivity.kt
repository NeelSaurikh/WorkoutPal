package com.example.workoutpal

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutpal.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisibleView: String = METRIC_UNITS_VIEW
    private var binding: ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "BMI Calculator"
        }

        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitsView()
            } else {
                makeVisibleUsUnitsView()
            }
        }

        binding?.btnCalculateUnits?.setOnClickListener {
            calculateUnits()
        }
    }

    private fun calculateUnits() {
        if (currentVisibleView == METRIC_UNITS_VIEW) {
            if (validateMetricUnits()) {
                val heightValue = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
                val weightValue = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi = weightValue / (heightValue * heightValue)
                displayBMIResult(bmi)
            } else {
                showToast("Please enter valid values.")
            }
        } else {
            if (validateUsUnits()) {
                val heightFeet = binding?.etUsMetricUnitHeightFeet?.text.toString().toFloat()
                val heightInch = binding?.etUsMetricUnitHeightInch?.text.toString().toFloat()
                val weight = binding?.etUsMetricUnitWeight?.text.toString().toFloat()

                val heightTotalInInches = (heightFeet * 12) + heightInch
                val bmi = 703 * (weight / (heightTotalInInches * heightTotalInInches))

                displayBMIResult(bmi)
            } else {
                showToast("Please enter valid values.")
            }
        }
    }

    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.apply {
            tilMetricUnitWeight.visibility = View.VISIBLE
            tilMetricUnitHeight.visibility = View.VISIBLE
            tilUsMetricUnitWeight.visibility = View.GONE
            tilMetricUsUnitHeightFeet.visibility = View.GONE
            tilMetricUsUnitHeightInch.visibility = View.GONE

            etMetricUnitHeight.text?.clear()
            etMetricUnitWeight.text?.clear()
            llDiplayBMIResult.visibility = View.INVISIBLE
        }
    }

    private fun makeVisibleUsUnitsView() {
        currentVisibleView = US_UNITS_VIEW
        binding?.apply {
            tilMetricUnitWeight.visibility = View.INVISIBLE
            tilMetricUnitHeight.visibility = View.INVISIBLE
            tilUsMetricUnitWeight.visibility = View.VISIBLE
            tilMetricUsUnitHeightFeet.visibility = View.VISIBLE
            tilMetricUsUnitHeightInch.visibility = View.VISIBLE

            etUsMetricUnitWeight.text?.clear()
            etUsMetricUnitHeightFeet.text?.clear()
            etUsMetricUnitHeightInch.text?.clear()
            llDiplayBMIResult.visibility = View.INVISIBLE
        }
    }

    private fun displayBMIResult(bmi: Float) {
        val (bmiLabel, bmiDescription) = when {
            bmi <= 15f -> "Very severely underweight" to "Oops! Eat more!"
            bmi in 15f..16f -> "Severely underweight" to "Oops! Eat more!"
            bmi in 16f..18.5f -> "Underweight" to "Oops! Eat more!"
            bmi in 18.5f..25f -> "Normal" to "Congratulations! You are in good shape!"
            bmi in 25f..30f -> "Overweight" to "Oops! Workout maybe!"
            bmi in 30f..35f -> "Obese Class I" to "Oops! Workout maybe!"
            bmi in 35f..40f -> "Obese Class II" to "OMG! Act now!"
            else -> "Obese Class III" to "OMG! Act now!"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.apply {
            llDiplayBMIResult.visibility = View.VISIBLE
            tvBMIValue.text = bmiValue
            tvBMIType.text = bmiLabel
            tvBMIDescription.text = bmiDescription
        }
    }

    private fun validateMetricUnits(): Boolean {
        return binding?.etMetricUnitWeight?.text.toString().trim().isNotEmpty() &&
                binding?.etMetricUnitHeight?.text.toString().trim().isNotEmpty()
    }

    private fun validateUsUnits(): Boolean {
        return binding?.etUsMetricUnitWeight?.text.toString().trim().isNotEmpty() &&
                binding?.etUsMetricUnitHeightFeet?.text.toString().trim().isNotEmpty() &&
                binding?.etUsMetricUnitHeightInch?.text.toString().trim().isNotEmpty()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
