package com.example.lista2

class DNASequence (var data: String)
{
val VALID_CHARS: List<Char> = listOf('A', 'C', 'T', 'G')
    var identifier: String
    var length: Int
    init {
        this.length=this.data.length
        var str = "RNASequence"
        if(this.data.length>=3)
        {
            str+=this.data.uppercase().subSequence(0, 3)
        }
        else
        {
            str+=this.data.uppercase()
        }
        this.identifier = str
    }
    override fun toString(): String
    {
        check(this.data.isNotBlank()){"Nić jest pusta"}
        var str: String
        str=">"+this.identifier+"\n"+this.data
        return str
    }

    fun mutate(position: Int, value: Char)
    {
        check(this.data.isNotBlank()){"Nić jest pusta"}
        check(this.length>position){"Nie istnieje taka pozycja"}
        check(VALID_CHARS.contains(value.uppercaseChar())){"Niedozwolona wartosc"}

        val str =  StringBuilder(this.data)
        str.setCharAt(position, value.uppercaseChar())
        this.data=str.toString()

    }
    fun complement(): String
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
        return complementary.reversed()
    }

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
        var RNA = RNASequence(transcibed.reversed())
        return RNA
    }

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
}

class RNASequence (var data: String)
{
    val VALID_CHARS: List<Char> = listOf('A', 'C', 'U', 'G')
    var identifier: String
    var length: Int

    init {
        this.length=this.data.length
        var str = "RNASequence"
        if(this.data.length>=3)
        {
            str+=this.data.uppercase().subSequence(0, 3)
        }
        else
        {
            str+=this.data.uppercase()
        }
        this.identifier = str
    }
    override fun toString(): String
    {
        check(this.data.isNotBlank()){"Nić jest pusta"}
        var str: String
        str=">"+this.identifier+"\n"+this.data
        return str
    }
    fun mutate(position: Int, value: Char)
    {
        check(this.data.isNotBlank()){"Nić jest pusta"}
        check(this.length>position){"Nie istnieje taka pozycja"}
        check(VALID_CHARS.contains(value.uppercaseChar())){"Niedozwolona wartosc"}

        val str =  StringBuilder(this.data)
        str.setCharAt(position, value.uppercaseChar())
        this.data=str.toString()
    }

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

    fun transluj (): ProteinSequence
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

        var Prot = ProteinSequence(aminokwasy)
        return Prot
    }
}

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
    }

    fun mutate(position: Int, value: String)
    {
        check(this.data.isNotEmpty()){"Nić jest pusta"}
        check(this.length>position){"Nie istnieje taka pozycja"}
        check(VALID_CHARS.contains(value)){"Niedozwolona wartosc"}

       this.data[position]=value
    }

    fun findMotif(motif: List<String>): Int
    {
        check(this.data.isNotEmpty()){"Nić jest pusta"}
       check(this.data.containsAll(motif)) {"Nie ma takiego motywu"}





    }

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

        if(mapaAminokwasow.contains(el)
        {s
            else{
                aminokwasy+=mapaAminokwasow.getValue(temp.toString())
                i+=3
            }
        }
        else throw IllegalArgumentException("Niewłaściwy kodon w nici RNA")
    }
        return str

    }
}

fun main()
{

}