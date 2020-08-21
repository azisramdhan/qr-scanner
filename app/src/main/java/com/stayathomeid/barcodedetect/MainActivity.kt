package com.stayathomeid.barcodedetect

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
    }

    fun setupUI(){
        button.setOnClickListener {

        }
        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.qr)
        imgview.setImageBitmap(bitmap)
        val detector = BarcodeDetector.Builder(applicationContext).setBarcodeFormats(Barcode.QR_CODE).build()
        if (!detector.isOperational){
            txtContent.text = "Could not set up the detector!"
            return
        }
        val frame = Frame.Builder().setBitmap(bitmap).build()
        val barcodes = detector.detect(frame)
        val thisCode = barcodes.valueAt(0)
        txtContent.text = thisCode.rawValue
    }
}