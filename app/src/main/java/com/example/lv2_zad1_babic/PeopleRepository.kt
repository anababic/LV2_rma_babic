package com.example.lv2_zad1_babic

object PeopleRepository {
    private var inspiringPersons:MutableList<InspiringPerson> = mutableListOf()

    fun savePerson(inspiringPerson: InspiringPerson){
        this.inspiringPersons.add(inspiringPerson)
    }
    fun getList():MutableList<InspiringPerson>{
        return inspiringPersons;
    }
    fun getSize():Int{
        return inspiringPersons.size;
    }
    fun getById(id:Int?):InspiringPerson?{
        return inspiringPersons.find{ person -> person.id == id }
    }
}