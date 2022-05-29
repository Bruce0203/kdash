import com.soywiz.klock.DateTime

fun timing(block: () -> Unit) {
    val before = DateTime.now()
    block()
    val after = DateTime.now()
    println(after - before)
}