package hu.bme.aut.androidwallet

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.salary_row.view.*

class MainActivity : AppCompatActivity() {

    var sum = 0
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all -> {
                list_of_rows.removeAllViews()
                summa.visibility = View.INVISIBLE
                sum = 0
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        save_button.setOnClickListener(fun(it: View) {
            if (salary_name.text.toString().isEmpty() || salary_amount.text.toString().isEmpty()) {
                val snackBar =
                    Snackbar.make(it, "Hiányos adatok!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                snackBar.setActionTextColor(Color.WHITE)
                val snackBarView = snackBar.view
                snackBarView.setBackgroundColor(Color.MAGENTA)
                snackBar.show()

                return;
            }

            val rowItem = LayoutInflater.from(this).inflate(R.layout.salary_row, null)
            rowItem.salary_direction_icon.setImageResource(
                if (expense_or_income.isChecked)
                    R.drawable.expense
                else R.drawable.income
            )
            rowItem.row_salary_name.text = salary_name.text.toString();
            rowItem.row_salary_amount.text = salary_amount.text.toString()
            list_of_rows.addView(rowItem)

            if (expense_or_income.isChecked)
                sum -= salary_amount.text.toString().toInt()
            else
                sum += salary_amount.text.toString().toInt()
            summa.text = sum.toString()
            summa.visibility = View.VISIBLE
        })
    }

}