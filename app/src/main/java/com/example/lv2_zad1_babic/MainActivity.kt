package com.example.lv2_zad1_babic

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(PeopleRepository.getSize()<2){loadInitialPersons()}
        setupRecyclerView()
    }

    private fun loadInitialPersons() {
        var newPerson:InspiringPerson = InspiringPerson()
        newPerson.id = 0
        newPerson.firstName = "Alan"
        newPerson.lastName = "Turing"
        newPerson.description = "Alan Mathison Turing bio je britanski matematičar, kriptograf i teoretičar računalstva.\n" +
                "\n" +
                "Alan Turing je 1950. objavio provokativan Turingov test o kojem se i dan danas vode rasprave. On je također izmislio Turingov stroj i po njemu je Turingova nagrada dobila ime."
        newPerson.dateOfBirth = "23.6.1912."
        newPerson.dateOfDeath = "7.6.1954."
        newPerson.quotes = mutableListOf(
                "Sometimes it is the people no one can imagine anything of who do the things no one can imagine.",
                "We can only see a short distance ahead, but we can see plenty there that needs to be done.",
                "Those who can imagine anything, can create the impossible.",
                "If a machine is expected to be infallible, it cannot also be intelligent.")
        newPerson.image = BitmapFactory.decodeResource(getResources(), R.mipmap.alan)
        PeopleRepository.savePerson(newPerson)

        var newPerson2:InspiringPerson = InspiringPerson()

        newPerson2.id=PeopleRepository.getSize()
        newPerson2.firstName = "Dennis"
        newPerson2.lastName = "Ritchie"
        newPerson2.description = "Dennis MacAlistair Ritchie je bio američki računalni znanstvenik poznat po svojem utjecaju na ALTRAN, B, BCPL, C, Multics i Unix. Dobio je Turingovu nagradu 1983. i Nacionalnu medalju tehnologije 1998 21. travnja 1999. godine."
        newPerson2.dateOfBirth = "9.9.1941"
        newPerson2.dateOfDeath = "12.10.2011"
        newPerson2.quotes = mutableListOf(
                "UNIX is basically a simple operating system, but you have to be a genius to understand the simplicity.",
                "The only way to learn a new programming language is by writing programs in it.",
                "I'm not a person who particularly had heros when growing up.",
                "A language that doesn't have everything is actually easier to program in than some that do")
        newPerson2.image= BitmapFactory.decodeResource(getResources(), R.mipmap.dennis);

        PeopleRepository.savePerson(newPerson2)

        var newPerson3:InspiringPerson = InspiringPerson()

        newPerson3.id=PeopleRepository.getSize()
        newPerson3.firstName = "Bill"
        newPerson3.lastName = "Gates"
        newPerson3.description = "William Henry \"Bill\" Gates III. američki je poslovni magnat, filantrop, druga najbogatija osoba na svijetu (provjereno 5. ožujka 2019.),prvi najbogatiji Amerikanac (provjereno 13. veljače 2009.) i direktor Microsofta, softverske tvrtke koju je pokrenuo s Paulom Allenom."
        newPerson3.dateOfBirth = "28.10.1955"
        newPerson3.dateOfDeath = null
        newPerson3.quotes= mutableListOf(
                "Don’t compare yourself with anyone in this world…if you do so, you are insulting yourself.",
                "I choose a lazy person to do a hard job. Because a lazy person will find an easy way to do it.",
                "If you are born poor it’s not your mistake, but if you die poor it’s your mistake.",
                "We make the future sustainable when we invest in the poor, not when we insist on their suffering.")
        newPerson3.image= BitmapFactory.decodeResource(getResources(), R.mipmap.bill);
        PeopleRepository.savePerson(newPerson3);

    }

    private fun setupRecyclerView() {
        rvPersonsList.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rvPersonsList.itemAnimator= DefaultItemAnimator();
        rvPersonsList.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL));
        displayItems();
    }

    private fun displayItems() {
        val quoteListener = object: InteractionListener{
            override fun onShowQuote(id: Int?) {
                fun IntRange.random() = Random().nextInt((endInclusive + 1) - start) + start
                val random = (0..((PeopleRepository.getById(id)!!.quotes.size)-1)).random()
                var randomQuote = PeopleRepository.getById(id)!!.quotes.get(random)
                Toast.makeText(this@MainActivity,randomQuote , Toast.LENGTH_SHORT).show();
            }
        }
        rvPersonsList.adapter= PersonsAdapter(PeopleRepository.getList(), quoteListener);
    }


    fun newPerson(v: View){

        var intent= Intent(this, InputActivity::class.java);
        startActivity(intent);

    }

}