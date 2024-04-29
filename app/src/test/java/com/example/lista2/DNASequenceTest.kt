package com.example.lista2

import org.junit.jupiter.api.Assertions.*

class DNASequenceTest {

    @org.junit.jupiter.api.Test
    fun testToString() {

    }

    @org.junit.jupiter.api.Test
    fun mutate() {
        val DNA = DNASequence("aatgttg")
    }

    @org.junit.jupiter.api.Test
    fun complement() {
        val DNA = DNASequence("aatgttg")
        assertEquals("CAACATT",DNA.complement(), {"Test funkcji komplement zakończony niepowodzeniem"})
        val DNA1 = DNASequence("")

    }

    @org.junit.jupiter.api.Test
    fun transcribe() {
    }

    @org.junit.jupiter.api.Test
    fun findMotif() {
    }
}