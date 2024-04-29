package com.example.lista2

/**
 * Klasa zawierająca sekwencję DNA oraz dotyczące jej funkcje
 * @param data sekwencja zasad DNA
 * @property VALID_CHARS Lista doswolonych znaków (zasad)
 * @property identifier String identyfikujący sekwencję DNA, jest tworzony w init jako "DNASequence" oraz 6 pierwszych zasad
 * @property length Długość sekwencji zasad
 * @author Hanna Górecka
*/
class DNASequence (var data: String)
{
    val VALID_CHARS: List<Char> = listOf('A', 'C', 'T', 'G')
    var identifier: String
    var length: Int
    init {
        this.length=this.data.length
        var str = "DNASequence"
        if(this.data.length>=6)
        {
            str+=this.data.lowercase().subSequence(0, 6)
        }
        else
        {
            str+=this.data.lowercase()
        }
        this.identifier = str
        this.data = this.data.uppercase()
        for(el in this.data)
        {
            check(VALID_CHARS.contains(el)){"Zly element w nici"}
        }
    }

    /**
     * Funkcja zwraca String zawierający opis sekwencji DNA w formacie FASTA
     * jako
     * >identyfier
     * data
     * @return String opisujący sekwencję DNA
     *
     */
    override fun toString(): String {
        check(this.data.isNotBlank()) { "Nić jest pusta" }
        return ">" + identifier + "\n" + data
    }

    /**
     * Funkcja zmienia element o podanym indeksie na podany nowy element
     * Sprawdza czy nić nie jest pusta, czy istnieje pozycja o danym indeksie i czy podana wartość jest dozwolona
     * @param position indeks, na którym ma być zmieniona zasada
     * @param value element, na który ma być zmieniony element na indeksie o pozycji position
     *
     */
    fun mutate(position: Int, value: Char)
    {
        check(this.data.isNotBlank()) {"Nić jest pusta"}
        check(this.length>position){"Nie istnieje taka pozycja"}
        check(VALID_CHARS.contains(value.uppercaseChar())){"Niedozwolona wartosc"}

        val str =  StringBuilder(this.data)
        str.setCharAt(position, value.uppercaseChar())
        this.data=str.toString()

    }

    /**
     * Funkcja zwraca obiekt DNASequence, gdzie data to sekwencja komplementarna do sekwencji DNA w data
     * Najpierw sprawdza czy nić nie jest pusta
     * @return obiekt DNASequence nici komplementarnej DNA
     *
     */
    fun complement(): DNASequence
    {
        check(this.data.isNotBlank()){"Nić kodująca jest pusta"}
        var complementary= ""
        for(el in this.data.uppercase())
        {
            complementary += when(el) {
                'A' -> 'T'
                'C' -> 'G'
                'T' -> 'A'
                'G' -> 'C'
                else -> throw IllegalArgumentException(" Niewłaściwy symbol w nici kodującej")
            }

        }
        val DNAComp = DNASequence(complementary.reversed())
        return DNAComp
    }

    /**
     * Funkcja zwraca obiekt klasy RNASequence, będący wynikiem transkrypcji DNA
     * Najpierw sprawdza czy nić nie jest pusta
     * @return obiekt klasy RNASequence, gdzie data to wynik transkrybcji DNA
     *
     */
    fun transcribe (): RNASequence
    {
        check(this.data.isNotBlank()){"Nić jest pusta"}
        var transcibed= ""
        for(el in this.data.uppercase())
        {
            transcibed += when(el) {
                'A' -> 'U'
                'C' -> 'G'
                'T' -> 'A'
                'G' -> 'C'
                else -> throw IllegalArgumentException(" Niewłaściwy symbol w nici matrycowej")

            }

        }
        var RNA = RNASequence(transcibed)
        return RNA
    }

    /**
     * Funckja zwraca indeks, na którym znajduje się podany jako argument motyw (sekwencja)
     * Jeśli takiego motywu nie ma, wyrzucony jest błąd
     * @param motif sekwencja zasad, która ma się znajdować w sekwencji DNA
     * @throws Exception kiedy nie ma takiego motywu w nici
     * @return indeks, na którym znajduje się motyw
     *
     */
    fun findMotif(motif: String): Int
    {
       check(this.data.isNotBlank()){"Nić jest pusta"}
       val i = this.data.indexOf(motif.uppercase())
        if (i==-1)
        {
            throw Exception("Brak motywu w nici")
        }
        else
        {
            return i
        }
    }
}

/**
 * Klasa zawierająca sekwencję RNA oraz dotyczące jej funkcje
 * @param data sekwencja zasad RNA
 * @property VALID_CHARS Lista dozwolonych znaków (zasad)
 * @property identifier String identyfikujący sekwencję RNA, jest tworzony w init jako "RNASequence" oraz 6 pierwszych zasad
 * @property length Długość sekwencji zasad
 * @author Hanna Górecka
 */
class RNASequence (var data: String)
{
    val VALID_CHARS: List<Char> = listOf('A', 'C', 'U', 'G')
    var identifier: String
    var length: Int

    init {
        this.length=this.data.length
        var str = "RNASequence"
        if(this.data.length>=6)
        {
            str+=this.data.lowercase().subSequence(0, 6)
        }
        else
        {
            str+=this.data.uppercase()
        }
        this.identifier = str
        this.data = this.data.uppercase()
        for(el in this.data)
        {
            check(VALID_CHARS.contains(el)){"Zly element w nici"}
        }
    }

    /**
     * Funkcja zwraca String zawierający opis sekwencji RNA w formacie LASTA
     * jako
     * >identyfier
     * data
     *@return String opisujący sekwencję RNA
     *
     */
    override fun toString(): String
    {
        check(this.data.isNotBlank()){"Nić jest pusta"}
        return ">"+this.identifier+"\n"+this.data
    }

    /**
     * Funkcja zmienia element o podanym indeksie na podany nowy element
     * Sprawdza czy nić nie jest pusta, czy istnieje pozycja o danym indeksie i czy podana wartość jest dozwolona
     * @param position indeks, na którym ma być zmieniona zasada
     * @param value element, na który ma być zmieniony element na indeksie o pozycji position
     *
     */
    fun mutate(position: Int, value: Char)
    {
        check(this.data.isNotBlank()){"Nić jest pusta"}
        check(this.length>position){"Nie istnieje taka pozycja"}
        check(VALID_CHARS.contains(value.uppercaseChar())){"Niedozwolona wartosc"}

        val str =  StringBuilder(this.data)
        str.setCharAt(position, value.uppercaseChar())
        this.data=str.toString()
    }

    /**
     * Funckja zwraca indeks, na którym znajduje się podany jako argument motyw (sekwencja)
     * Jeśli takiego motywu nie ma, wyrzucony jest błąd
     * @param motif sekwencja zasad, która ma się znajdować w sekwencji RNA
     * @throws Exception kiedy nie ma takiego motywu w nici
     * @return indeks, na którym znajduje się motyw
     *
     */
    fun findMotif(motif: String): Int
    {
        check(this.data.isNotBlank()){"Nić jest pusta"}
        val i = this.data.indexOf(motif)
        if (i==-1)
        {
            throw Exception("Brak motywu w nici")
        }
        else
        {
            return i
        }
    }

    /**
     * Funkcja zwraca obiekt klasy ProteinSequence, będący wynikiem translacji RNA
     * Najpierw sprawdza czy nić nie jest pusta
     * @return obiekt klasy ProteinSequence, gdzie data to wynik translacji RNA
     *
     */
    fun translate (): ProteinSequence
    {
        check(this.data.isNotBlank()){"Nić jest pusta"}

        val mapaAminokwasow= mapOf<String, String>(
            "UUU" to "Phe", "UUC" to "Phe", "UUA" to "Leu", "UUG" to "Leu",
            "CUU" to "Leu", "CUC" to "Leu", "CUA" to "Leu", "CUG" to "Leu",
            "AUU" to "Ile", "AUC" to "Ile", "AUA" to "Ile", "AUG" to "Met",
            "GUU" to "Val", "GUC" to "Val", "GUA" to "Val", "GUG" to "Val",
            "UCU" to "Ser", "UCC" to "Ser", "UCA" to "Ser", "UCG" to "Ser",
            "CCU" to "Pro", "CCC" to "Pro", "CCA" to "Pro", "CCG" to "Pro",
            "ACU" to "Thr", "ACC" to "Thr", "ACA" to "Thr", "ACG" to "Thr",
            "GCU" to "Ala", "GCC" to "Ala", "GCA" to "Ala", "GCG" to "Ala",
            "UAU" to "Tyr", "UAC" to "Tyr", "UAA" to "Stop", "UAG" to "Stop",
            "CAU" to "His", "CAC" to "His", "CAA" to "Gln", "CAG" to "Gln",
            "AAU" to "Asn", "AAC" to "Asn", "AAA" to "Lys", "AAG" to "Lys",
            "GAU" to "Asp", "GAC" to "Asp", "GAA" to "Glu", "GAG" to "Glu",
            "UGU" to "Cys", "UGC" to "Cys", "UGA" to "Stop", "UGG" to "Trp",
            "CGU" to "Arg", "CGC" to "Arg", "CGA" to "Arg", "CGG" to "Arg",
            "AGU" to "Ser", "AGC" to "Ser", "AGA" to "Arg", "AGG" to "Arg",
            "GGU" to "Gly", "GGC" to "Gly", "GGA" to "Gly", "GGG" to "Gly"
        )

        var i: Int = 0
        var aminokwasy = mutableListOf<String>()

        while (i+3<=this.data.length)
        {
            val temp = this.data.uppercase().subSequence(i, i+3)

            if(mapaAminokwasow.containsKey(temp.toString()))
            {
                if(mapaAminokwasow.getValue(temp.toString())=="Stop"){break}
                else{
                    aminokwasy+=mapaAminokwasow.getValue(temp.toString())
                    i+=3
                }
            }
            else throw IllegalArgumentException("Niewłaściwy kodon w nici RNA")
        }

        val Prot = ProteinSequence(aminokwasy)
        return Prot
    }
}

/**
 * Klasa zawierająca sekwencję aminokwasów oraz dotyczące jej funkcje
 * @param data sekwencja aminokwasów
 * @property VALID_CHARS Lista doswolonych elementów (aminokwasów)
 * @property identifier String identyfikujący sekwencję aminokwasów,
 * jest tworzony w init jako "ProteinSequence" oraz pierwsze litery aminokwasów
 * @property length Długość sekwencji aminokwasów
 * @author Hanna Górecka
 */
class ProteinSequence(var data: MutableList<String>)
{
    val VALID_CHARS: List<String> = listOf("Phe", "Leu", "Ile", "Met",
        "Val", "Ser", "Pro", "Thr", "Ala", "Tyr", "His",
        "Gln", "Asn", "Lys", "Asp", "Glu", "Cys", "Trp",
       "Arg", "Gly")
    var identifier: String
    var length: Int
    init {
        this.length=this.data.size
        var str = "ProteinSequence "
        data.forEach {
            str+=it.subSequence(0,1)
        }
        this.identifier = str
        for(el in this.data)
        {
            check(VALID_CHARS.contains(el)){"Zly element ciagu aminokwasow"}
        }
    }

    /**
     * Funkcja zmienia element o podanym indeksie na podany nowy element
     * Sprawdza czy nić nie jest pusta, czy istnieje pozycja o danym indeksie i czy podana wartość jest dozwolona
     * @param position indeks, na którym ma być zmieniona zasada
     * @param value element, na który ma być zmieniony element na indeksie o pozycji position
     *
     */
    fun mutate(position: Int, value: String)
    {
        check(this.data.isNotEmpty()){"Nić jest pusta"}
        check(this.length>position){"Nie istnieje taka pozycja"}
        check(VALID_CHARS.contains(value)){"Niedozwolona wartosc"}

       this.data[position]=value
    }

    /**
     * Funckja zwraca indeks, na którym znajduje się pierwszy element
     * podanego jako argument motywu (sekwencji)
     * Jeśli takiego motywu nie ma, wyrzucony jest błąd
     * @param motif lista aminokwasów, która ma się znajdować w sekwencji aminokwasów
     * @throws Exception kiedy nie ma takiego motywu w sekwencji
     * @return indeks, na którym znajduje się pierwszy element motywu
     *
     */
    fun findMotif(motif: MutableList<String>): Int
    {
       check(this.data.isNotEmpty()){"Nić jest pusta"}
       check(this.data.containsAll(motif)) {"Nie ma takiego motywu"}

        var positions = mutableListOf<Int>()
        for (j in this.data.indices) {
            if (this.data[j] == motif[0]) {
               positions.add(j)
            }
        }
        var index: Int = -1
        for (j in positions)
        {
            for(i in j..<j+motif.size)
            {
                if(this.data[i]!=motif[i-j])
                {
                    break
                }
                else
                {
                    index = i
                }
            }

            if(index == j+motif.size-1)
            {
                break
            }
            else
            {
                index = -1
            }
        }
        if (index == -1)
        {
            throw Exception  ("Nie znaleziono pozycji motywu")
        }
    return index
    }

    /**
     * Funkcja zwraca String zawierający opis sekwencji aminokwasów w formacie LASTA
     * jako
     * >identyfier
     * Znaki odpowiadające aminokwasom w sekwencji
     * @return String opisujący sekwencję aminokwasów
     *
     */
    override fun toString(): String {

        val mapaAminokwasow= mapOf<String, Char>(
            "Phe" to 'F' , "Leu" to 'L', "Ile" to 'I', "Met" to 'M',
            "Val" to 'V', "Ser" to 'S', "Pro" to 'P', "Thr" to 'T', "Ala" to 'A', "Tyr" to 'Y', "His" to 'H',
            "Gln" to 'Q', "Asn" to 'N', "Lys" to 'K', "Asp" to 'D', "Glu" to 'E', "Cys" to 'C', "Trp" to 'W',
            "Arg" to 'R', "Gly" to 'G')

        check(this.data.isNotEmpty()){"Nić jest pusta"}
        var str: String
        str=">"+this.identifier+"\n"

        for(el in this.data)
        {

        if(mapaAminokwasow.contains(el))
        {
                str+=mapaAminokwasow.getValue(el)

        }

        else throw IllegalArgumentException("Niewłaściwy kodon w nici RNA")
        }
        return str
    }
}

/**
 * Funkcja main() zawierająca kod, testujący funkcjonalność napisanych klas
 * @author Hanna Górecka
 */
fun main()
{
var DNA1 = DNASequence("agggtaaattga")
    println("Sekwencja DNA: $DNA1")
    println("Sekwencja nici komplementarnej DNA: ${DNA1.complement()}")
    DNA1.mutate(3, 'a')
    println("Sekwencja DNA po mutacji na 3 pozycji: $DNA1")
    println("Indeks, na którym znajduje się motyw aaa: ${DNA1.findMotif("AAA")}")
}