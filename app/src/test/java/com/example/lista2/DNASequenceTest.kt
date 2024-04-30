package com.example.lista2

import org.junit.jupiter.api.Assertions.*
import org.junit.Test

import org.junit.Assert.*


class DNASequenceTest {

    @Test
    fun DNASequenceLesserThanSix(){
        val str = "atgga"
        val DNA = DNASequence(str)

        val id:String = DNA.identifier
        val data: String = DNA.data
      assertTrue("ATGGA"==data, {"Błędna wartość data"})
      assertEquals(5, DNA.length, {"Błędna wartość length"})
      assertTrue("DNASequenceatgga"==id, {"Błędna wartość identifier"})

    }
    @Test
    fun DNASequenceBiggerThanSix(){
        val str = "atggaaaa"
        val DNA = DNASequence(str)

        val id:String = DNA.identifier
        val data: String = DNA.data
        assertTrue("ATGGAAAA"==data, {"Błędna wartość data"})
        assertEquals(8, DNA.length, {"Błędna wartość length"})
        assertTrue("DNASequenceatggaa"==id, {"Błędna wartość identifier"})
    }


    @Test
   fun testToString() {
        val str = "atgga"
        val DNA = DNASequence(str)
        val str2: String = DNA.toString()
        assertTrue(">${DNA.identifier}\n${DNA.data}"==str2, {"Błąd funkcji toString()"})

    }

    @Test
    fun mutate() {
        val DNA = DNASequence("aatgttg")
        DNA.mutate(2,'G')
        val str: String = DNA.data
        assertTrue("AAGGTTG"==str, {"Błąd w mutacji"})
    }


    @Test
    fun complement() {
        val DNA = DNASequence("aaa")
        assertTrue(DNASequence("ttt").data==DNA.complement().data, {"Błąd przy tworzeniu nici komplementarnej"})

        val DNA1 = DNASequence("tgac")
        assertTrue(DNASequence("gtca").data==DNA1.complement().data, {"Błąd przy tworzeniu nici komplementarnej"})
    }

    @Test
    fun transcribe() {
        val DNA = DNASequence("aaa")
        assertTrue(RNASequence("uuu").data==DNA.transcribe().data, {"Błąd przy tworzeniu nici RNA"})

        val DNA1 = DNASequence("tgac")
        assertTrue(RNASequence("acug").data==DNA1.transcribe().data, {"Błąd przy tworzeniu nici RNA"})
    }

    @Test
    fun findMotif() {
        val DNA = DNASequence("atggat")
        assertEquals(1, DNA.findMotif("TGG"), {"Błąd w szukaniu motywu"})
        assertEquals(0, DNA.findMotif("at"), {"Błąd w szukaniu motywu"})
    }
}