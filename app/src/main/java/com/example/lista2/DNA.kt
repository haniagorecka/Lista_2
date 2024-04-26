package com.example.lista2

class DNASequence (var identifier: String, var data: String, var length: Int)
{
val VALID_CHARS: List<Char> = listOf('A', 'C', 'T', 'G')


    fun mutate(position: Int, value: Char)
    {
        check(this.data.isNotBlank()){"Nić kodująca jest pusta"}
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
        check(this.data.isNotBlank()){"Nić matrycowa jest pusta"}
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
        var RNA = RNASequence(this.identifier, transcibed.reversed(), this.length)
        return RNA
    }
}

class RNASequence (var identifier: String, var data: String, var length: Int)
{
    val VALID_CHARS: List<Char> = listOf('A', 'C', 'U', 'G')


}


fun main()
{

}