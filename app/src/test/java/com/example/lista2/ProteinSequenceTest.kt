package com.example.lista2

import org.junit.jupiter.api.Assertions.*
import org.junit.Assert.*
import org.junit.Test


class ProteinSequenceTest {

    @Test
    fun ProteinSequenceCreate(){
        val list = mutableListOf<String>("Gly", "Met")
        val Prot = ProteinSequence(list)

        val id:String = Prot.identifier
        val data: MutableList<String> = Prot.data
        assertTrue(list==data, {"Błędna wartość data"})
        assertEquals(2, Prot.length, {"Błędna wartość length"})
        assertTrue("ProteinSequence GM"==id, {"Błędna wartość identifier"})

    }
    @Test
    fun mutate() {
        val list = mutableListOf<String>("Gly", "Met")
        val Prot = ProteinSequence(list)
        Prot.mutate(1,"Leu")
        var str = Prot.data
        assertTrue(mutableListOf<String>("Gly", "Leu") ==str, {"Błąd w mutacji"})
        Prot.mutate(0,"Ala")
        str=Prot.data
        assertTrue(mutableListOf<String>("Ala", "Leu") ==str, {"Błąd w mutacji"})
    }

    @Test
    fun findMotif() {
        val list = mutableListOf<String>("Gly", "Met", "Met", "Ser", "Gly" )
        val Prot = ProteinSequence(list)
        assertEquals(2, Prot.findMotif(mutableListOf("Met", "Ser")), {"Błąd w szukaniu motywu"})
        assertEquals(3, Prot.findMotif(mutableListOf("Ser", "Gly")), {"Błąd w szukaniu motywu"})
    }

    @Test
    fun testToString() {
        val list = mutableListOf<String>("Gly", "Met")
        val Prot = ProteinSequence(list)
        val str2: String = Prot.toString()
        assertTrue(">${Prot.identifier}\nGM"==str2, {"Błąd funkcji toString()"})
    }
}