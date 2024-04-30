package com.example.lista2

import org.junit.jupiter.api.Assertions.*
import org.junit.Assert.*
import org.junit.Test

class WielomianTest {


    @Test
    fun stopien() {
        val list = mutableListOf<Double>(1.0,1.0,1.0,1.0)
        val wiel = Wielomian(list)
        assertEquals(3, wiel.Stopien())
        val list1 = mutableListOf<Double>(1.0,1.0,1.0,0.0)
        val wiel1 = Wielomian(list1)
        assertEquals(2, wiel1.Stopien())
        val list2 = mutableListOf<Double>(0.0,0.0,0.0)
        val wiel2 = Wielomian(list2)
        assertEquals(0, wiel2.Stopien())
        val list3 = mutableListOf<Double>(1.0,4.0,0.0,-3.0)
        val wiel3 = Wielomian(list3)
        assertEquals(3, wiel3.Stopien())
    }

    @Test
    fun ToStringTest() {
        val list = mutableListOf<Double>(1.0,1.0,1.0,1.0)
        val wiel = Wielomian(list)
        var str = wiel.ToString()
        assertTrue("1.0*x^3+1.0*x^2+1.0*x+1.0+1.0"==str, {"Błędny String 1"})
        val list1 = mutableListOf<Double>(1.0,0.0,-2.0,0.0)
        val wiel1 = Wielomian(list1)
        str = wiel1.ToString()
        assertTrue("-2.0*x^2+1.0"==str, {"Błędny String 2"})
        val list2 = mutableListOf<Double>(0.0,0.0,0.0)
        val wiel2 = Wielomian(list2)
        str = wiel2.ToString()
        assertTrue("0"==str, {"Błędny String 3"})
        val list3 = mutableListOf<Double>(-3.0)
        val wiel3 = Wielomian(list3)
        str = wiel3.ToString()
        assertTrue("-3.0"==str, {"Błędny String 4"})
        val list4 = mutableListOf<Double>(0.0, -1.0)
        val wiel4 = Wielomian(list4)
        str = wiel4.ToString()
        assertTrue("-1.0*x"==str, {"Błędny String 5"})
    }

    @Test
    fun wartoscTest() {
        val list = mutableListOf<Double>(1.0,1.0,1.0,1.0)
        val wiel = Wielomian(list)
        var abs = wiel.Wartosc(1.0)-4.0
        assertTrue({-0.0001<abs&&abs<0.0001}, {"Obliczona błędna wartość"})
        abs = wiel.Wartosc(2.0)-15.0
        assertTrue({-0.0001<abs&&abs<0.0001}, {"Obliczona błędna wartość"})
        val list1 = mutableListOf<Double>(-3.2, 0.9, -1.0, -5.0)
        val wiel1 = Wielomian(list1)
        assertEquals(-3.2, wiel1.Wartosc(0.0), {"Obliczona błędna wartość"})
        abs = wiel1.Wartosc(-1.0)+0.1
        assertTrue({-0.0001<abs&&abs<0.0001}, {"Obliczona błędna wartość"})
        abs = wiel1.Wartosc(2.2)+59.3
        assertTrue({-0.0001<abs&&abs<0.0001}, {"Obliczona błędna wartość"})

    }

    @Test
    fun plus() {
        val list = mutableListOf<Double>(1.0,1.0,1.0,1.0)
        val wiel = Wielomian(list)
        val list1 = mutableListOf<Double>(-3.2, 0.9, -1.0)
        val wiel1 = Wielomian(list1)
        var wiel3 = wiel+wiel1
        var list2 = mutableListOf<Double>(-2.2, 1.9, 0.0, 1.0)
        assertTrue(wiel3.wsp == list2, {"Błąd w dodawaniu"})
        wiel3 = wiel1+wiel
        assertTrue(wiel3.wsp == list2, {"Błąd w dodawaniu"})
        list2 = mutableListOf(0.0)
        wiel3 = Wielomian(mutableListOf(-1.0))+ Wielomian(mutableListOf(1.0))
        assertTrue(wiel3.wsp == list2, {"Błąd w dodawaniu"})
    }

    @Test
    fun minus() {
         val list = mutableListOf<Double>(1.0,1.0,1.0,1.0)
         val wiel = Wielomian(list)
         val list1 = mutableListOf<Double>(-3.2, -0.8, -1.0)
         val wiel1 = Wielomian(list1)
         var wiel3 = wiel-wiel1
        println(wiel3.wsp)
         var list2 = mutableListOf<Double>(4.2, 1.8, 2.0, 1.0)
        assertTrue(wiel3.wsp == list2, {"Błąd w odejmowaniu"} )
         var list3 = mutableListOf<Double>(-4.2, -1.8, -2.0, -1.0)
         wiel3 = wiel1-wiel
        assertTrue(wiel3.wsp == list3, {"Błąd w odejmowaniu"})
        list3 = mutableListOf(0.0, 0.0, 0.0)
        wiel3 = wiel1-wiel1
       assertTrue(wiel3.wsp == list3, {"Błąd w odejmowaniu"})   
    }

    @Test
    fun times() {
    }
}