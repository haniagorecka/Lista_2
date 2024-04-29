package com.example.lista2

import kotlin.math.pow

/**
 * Klasa opisująca wielomian, na podstawie listy jego współczynników
 * oraz zawierająca funkcje, pozwalające na operacje na wielomianach
 * @param wsp modyfikowalna lista współczynników, gdzie
 * indeks elementu odpowiada potędze (element o indeksie 0 to wyraz wolny,
 * element o indeksie 1 to wyraz z x^1)
 *@author Hanna Górecka
 */
class Wielomian (val wsp: MutableList<Double>) {

    /**
     * Funkcja zwraca stopień wielomianu
     * @return stopień wielomianu jako Int
     */
    fun Stopien(): Int {
        var i: Int = wsp.size - 1
        if (wsp[i] != 0.0) {
            return i
        }

        while (i > 0) {
            i--
            if (wsp[i] != 0.0) {
                return i
            }
        }

        return 0
    }

    /**
     * Fukcja wypisuje wielomian w formacie ax^n+bx^(n-1)+...+cx+d
     */
    fun Wypisz() {
        var i: Int = wsp.size - 1
        while (i > 0) {
            if (i == 1 && wsp[i] != 0.0 && wsp[0] != 0.0) {
                print(wsp[i].toString() +" x + " + wsp[0].toString())
            } else if (i == 1 && wsp[i] != 0.0 && wsp[0] == 0.0) {
                print(wsp[1].toString() + " x")
            } else if (wsp[i] != 0.0) {
                print(wsp[i].toString() + "x^$i + ")
            }
            i--
        }
        println()
    }

    /**
     * Funkcja oblicza wartość wielomianu dla podanego argumentu
     * @param x argument, dla którego ma być policzony wielomian
     * @return wartość wielomianu
     */
    fun Wartosc ( x: Double): Double
    {
        var sum: Double = 0.0
        var i = 0

        wsp.forEach{
            sum += it * x.pow(i)
            i++
        }

        return sum
    }

    /**
     * Przeciążenie operatora + dla wielomiany
     * @param wiel2 drugi wielomian, który ma być dodany do tego wielomianu
     * @return obiekt klasy Wielomian, będący sumą dwóch wielomianów
     */
    operator fun plus (wiel2: Wielomian): Wielomian
    {
        val list = mutableListOf<Double>()
        val n: Int
        if(this.wsp.size>=wiel2.wsp.size)
        {
         n=wiel2.wsp.size
        }
        else { n = this.wsp.size}

        for(i:Int in 0..n-1)
        {
            list.add(i, this.wsp[i]+wiel2.wsp[i])
        }
        if(n<this.wsp.size)
        {
            var i: Int = n
            do
            {
                list.add(i, this.wsp[i])
                i++
            }while(i<=this.wsp.size)

        }
        else if(n<wiel2.wsp.size)
        {

            var i: Int = n

            do {
                list.add(i, wiel2.wsp[i])
                i++
            } while(i<wiel2.wsp.size)

        }
        val wiel3 = Wielomian(list)
        return wiel3

    }

    /**
     * Przeciążenie operatora - dla wielomiany
     * @param wiel2 drugi wielomian, który ma być odjęty od tego wielomianu
     * @return obiekt klasy Wielomian, będący różnicą dwóch wielomianów
     */
    operator fun minus (wiel2: Wielomian): Wielomian
    {
        val list = mutableListOf<Double>()
        val n: Int
        if(this.wsp.size>=wiel2.wsp.size)
        {
            n=wiel2.wsp.size
        }
        else { n = this.wsp.size}

        for(i:Int in 0..n-1)
        {
            list.add(i, this.wsp[i]-wiel2.wsp[i])
        }
        if(n<this.wsp.size)
        {
            var i: Int = n

            do{
                list.add(i, this.wsp[i])
                i++
            }while(i<this.wsp.size)
        }
        else if(n<wiel2.wsp.size)
        {
            var i: Int = n
            do
            {
                list.add(i, (-1)*wiel2.wsp[i])
                i++
            }while(i<wiel2.wsp.size)
        }
        val wiel3 = Wielomian(list)
        return wiel3
    }

    /**
     * Przeciążenie operatora * dla wielomiany
     * @param wiel2 drugi wielomian, przez który ma być pomnożony ten wielomian
     * @return obiekt klasy Wielomian, będący iloczynem dwóch wielomianów
     */
    operator fun times (wiel2: Wielomian): Wielomian
    {
        val list = mutableListOf<Double>()
        val n: Int =this.wsp.size
        val m: Int = wiel2.wsp.size

        var p: Int
        var w: Double
        for(i:Int in 0..<n)
        {
            for(j:Int in 0..<m)
            {
                p=i+j
                w=this.wsp[i]*wiel2.wsp[j]
                if(list.size>p)
                {
                    list[p]+=w
                }
                else
                {
                    list.add(p,w)
                }

            }

        }

        val wiel3 = Wielomian(list)
        return wiel3

    }

}

/**
 * Funkcja main() zawierająca kod, testujący funkcjonalność napisanej klasy
 * @author Hanna Górecka
 */
fun main()
{
    val list = mutableListOf<Double>(1.0,2.0,3.0)
    val list2 = mutableListOf<Double>(1.0,1.0,1.0,1.0)
    var wiel = Wielomian(list)
    val wiel2 = Wielomian(list2)
    var wiel3 = wiel+wiel2
    wiel.Wypisz()
    wiel3 = wiel-wiel2
    wiel3.Wypisz()
    wiel3 = wiel2-wiel
    wiel3.Wypisz()
    wiel3 = wiel*wiel2
    wiel3.Wypisz()


}