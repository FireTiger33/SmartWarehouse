package com.stacktivity.smartwarehouse

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stacktivity.smartwarehouse.catalog.CatalogActivity
import com.stacktivity.smartwarehouse.inventory.InventoryActivity
import com.stacktivity.smartwarehouse.report.ReportActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_edit_database.setOnClickListener {
            showCatalogScreen()
        }

        btn_start_inventory.setOnClickListener {
            showInventoryScreen()
        }

        btn_create_report.setOnClickListener {
            showReportScreen()
        }


    }

    private fun showCatalogScreen() {
        val intent = Intent(this, CatalogActivity::class.java)
        startActivity(intent)
    }

    private fun showInventoryScreen() {
        val intent = Intent(this, InventoryActivity::class.java)
        startActivity(intent)
    }

    private fun showReportScreen() {
        val intent = Intent(this, ReportActivity::class.java)
        startActivity(intent)
    }
}
