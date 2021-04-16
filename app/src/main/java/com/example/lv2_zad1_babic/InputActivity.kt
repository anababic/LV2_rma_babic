package com.example.lv2_zad1_babic

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class InputActivity : AppCompatActivity() {

    var quotes: MutableList<String> = mutableListOf<String>();
    var bitmap: Bitmap?=null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        setListeners()
    }

    private fun setListeners():Unit{
        btnSave.setOnClickListener({v -> storePerson(v)})
        tvBorn.setOnClickListener({ v-> openDialog(v)})
        tvDied.setOnClickListener({v -> openDialog(v)})
        ivSlika.setOnClickListener({v -> getImage(v)})
        btnAddToList.setOnClickListener({ v-> addQuote(v)})
    }

    private fun addQuote(v: View?) {
        quotes.add(etCitat.text.toString())
        etCitat.setText(" ")
        Toast.makeText(this@InputActivity, "Citat je dodan.", Toast.LENGTH_SHORT).show()
    }

    private fun storePerson(v: View){
        if((etFirstName.text.toString() == "") && (etLastName.text.toString() == "")){
            Toast.makeText(this@InputActivity, "Potrebno je unijeti ime i prezime.", Toast.LENGTH_SHORT).show()
        }
        else{
            val newPerson:InspiringPerson=InspiringPerson()
            newPerson.id = PersonRepo.getSize();
            newPerson.firstName = etFirstName.text.toString()
            newPerson.lastName = etLastName.text.toString()
            newPerson.description = etDesc.text.toString()
            newPerson.dateOfBirth = tvBorn.text.toString()
            newPerson.dateOfDeath = tvDied.text.toString()
            newPerson.quotes = this.quotes

            when(this.bitmap){
                null -> newPerson.image= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)
                else -> newPerson.image= bitmap
            }

            PersonRepo.savePerson(newPerson);
            var intent= Intent(this, MainActivity::class.java)
            startActivity(intent)}
    }

    private fun openDialog(view: View){
        var calendar = Calendar.getInstance()
        val bornDateSetListener = DatePickerDialog.OnDateSetListener { context, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.US)

            tvBorn.text = sdf.format(calendar.time)
        }

        val diedDateSetListener = DatePickerDialog.OnDateSetListener { context, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.US)

            tvDied.text = sdf.format(calendar.time)}

        when(view.id){
            tvBorn.id -> tvBorn.setOnClickListener{
                DatePickerDialog(this@InputActivity, bornDateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
            tvDied.id-> tvDied.setOnClickListener {
                DatePickerDialog(this@InputActivity, diedDateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        }

    }

    private fun getImage(view: View){
        var intentGallery= Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intentGallery, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null)
        {
            val contentURI = data!!.data
            try
            {
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                this.bitmap=bitmap
                ivSlika!!.setImageBitmap(bitmap)

            }
            catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this@InputActivity, "Failed!", Toast.LENGTH_SHORT).show()
            }

        }
    }
    fun abort(view: View){
        var intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}