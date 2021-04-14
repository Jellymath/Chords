package ui.browser

import chords.*
import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

private val currentSelected = mutableSetOf(ChordPattern.Basic)
private var currentPool = createChordTypePool(currentSelected)

fun showChord() {
    val text = if (currentPool.isEmpty()) {
        "No chord pattern was chosen, select some checkboxes!"
    } else {
        val chord = Chord.random(currentPool)
        val chordText = "${chord.name} (${chord.symbols})"
        println("$chordText was chosen")
        chordText
    }
    document.getElementById("chord-label")!!.innerHTML = text
}

@JsName("updateChordPool")
fun updateChordPool(event: Event) {
    val input = event.target as HTMLInputElement
    val checked = input.checked
    val pattern = ChordPattern.valueOf(input.value)
    if (checked) {
        currentSelected += pattern
    } else {
        currentSelected -= pattern
    }
    currentPool = createChordTypePool(currentSelected)
    println("${if (checked) "Selected" else "Deselected"} $pattern")
    println("New pattern list is $currentSelected")
    println("New chord type pool is ${currentPool.map {it.name }}")
}

fun main() {
    listOf("checkbox-basic", "checkbox-suspended", "checkbox-seventh", "checkbox-altered", "checkbox-extended")
        .map { document.getElementById(it)!! }
        .forEach { it.addEventListener("input", ::updateChordPool) }
    document.getElementById("button-next")!!.addEventListener("click", { showChord() })
}

