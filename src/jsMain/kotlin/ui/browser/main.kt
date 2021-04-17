package ui.browser

import chords.*
import chords.synth.defaultSynth
import chords.synth.muted
import chords.synth.play
import kotlinx.browser.document
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import kotlin.time.seconds

private val currentSelected = mutableSetOf(ChordPattern.Basic)
private var currentPool = createChordTypePool(currentSelected)
private var lastChord: Chord? = null

fun showChord() {
    val (text, detailText) = if (currentPool.isEmpty()) {
        lastChord = null
        "No chord pattern was chosen, select some checkboxes!" to ""
    } else {
        val chord = Chord.random(currentPool)
        println("${chord.symbols} was chosen")
        lastChord = chord
        defaultSynth.play(chord.toMidiNotes(), 1.5.seconds)
        chord.symbols to "(${chord.name})"
    }
    document.getElementById("chord-label")!!.innerHTML = text
    document.getElementById("chord-label-detailed")!!.innerHTML = detailText
}

fun replayChord() {
    val chord = lastChord
    if (chord != null) defaultSynth.play(chord.toMidiNotes(), 1.5.seconds)
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
    println("New chord type pool is ${currentPool.map { it.name }}")
}

@JsName("toggleMute")
fun toggleMute(event: Event) {
    with(defaultSynth) {
        muted = !muted
        val button = event.target as HTMLButtonElement
        button.innerHTML = if (muted) "\uD83D\uDD08" else "\uD83D\uDD0A"
    }
}

fun main() {
    listOf("checkbox-basic", "checkbox-suspended", "checkbox-seventh", "checkbox-altered", "checkbox-extended")
        .map { document.getElementById(it)!! }
        .forEach { it.addEventListener("input", ::updateChordPool) }
    document.getElementById("button-next")!!.addEventListener("click", { showChord() })
    document.getElementById("button-mute")!!.addEventListener("click", ::toggleMute)
    document.getElementById("button-repeat")!!.addEventListener("click", { replayChord() })
}

