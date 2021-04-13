package console.random

import chords.*
import kotlin.time.seconds

fun main() {
    val chords = generateSequence { Chord.random() }.take(10)
    chords.forEach { chord ->
        println("Current chord: ${chord.name} (${chord.symbols})")
        withSynth {
            play(chord.toMidiNotes(), 1.5.seconds)
        }
    }
}