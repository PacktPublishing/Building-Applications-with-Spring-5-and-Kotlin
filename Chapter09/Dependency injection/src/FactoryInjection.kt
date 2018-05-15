
class MyExampleClass2 {

    private val dependency = Factory().create()

}

class Factory {

    fun create(): Any {
        // To some instantiation work
        return Any()
    }

}