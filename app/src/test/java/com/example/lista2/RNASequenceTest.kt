package com.example.lista2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.Assert.*

class RNASequenceTest {

    @org.junit.Test
    fun RNASequenceLesserThanSix(){
        val str = "augga"
        val RNA = RNASequence(str)

        val id:String = RNA.identifier
        val data: String = RNA.data
        assertTrue("AUGGA"==data, {"Błędna wartość data"})
        assertEquals(5, RNA.length, {"Błędna wartość length"})
        assertTrue("RNASequenceaugga"==id, {"Błędna wartość identifier"})

    }
    @org.junit.Test
    fun RNASequenceBiggerThanSix(){
        val str = "auggaaaa"
        val RNA = RNASequence(str)

        val id:String = RNA.identifier
        val data: String = RNA.data
        assertTrue("AUGGAAAA"==data, {"Błędna wartość data"})
        assertEquals(8, RNA.length, {"Błędna wartość length"})
        assertTrue("RNASequenceauggaa"==id, {"Błędna wartość identifier"})
    }

    @org.junit.Test
    fun testToString() {
        val str = "augga"
        val RNA = RNASequence(str)
        val str2: String = RNA.toString()
        assertTrue(">${RNA.identifier}\n${RNA.data}"==str2, {"Błąd funkcji toString()"})
    }

    @org.junit.Test
    fun mutate() {
        val RNA = RNASequence("aauguug")
        RNA.mutate(2,'G')
        val str: String = RNA.data
        assertTrue("AAGGUUG"==str, {"Błąd w mutacji"})
    }

    @org.junit.Test
    fun findMotif() {
        val RNA = RNASequence("auggau")
        assertEquals(1, RNA.findMotif("UGG"), {"Błąd w szukaniu motywu"})
        assertEquals(0, RNA.findMotif("au"), {"Błąd w szukaniu motywu"})
    }

    @org.junit.Test
    fun translate() {
        var RNA = RNASequence("uuuagcugguaugaa")
        var list = mutableListOf<String>("Phe", "Ser", "Trp", "Tyr", "Glu")
        assertTrue(ProteinSequence(list).data==RNA.translate().data, {"Błąd przy tworzeniu sekwencji aminokwasów"})
       RNA = RNASequence("gggaugaa")
        var list1 = mutableListOf<String>("Gly", "Met")
        assertTrue(ProteinSequence(list1).data==RNA.translate().data, {"Błąd przy tworzeniu sekwencji aminokwasów"})
    }
}