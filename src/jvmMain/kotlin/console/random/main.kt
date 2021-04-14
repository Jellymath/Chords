package console.random

import chords.*
import chords.synth.*
import kotlin.time.seconds

fun main() = withSynth {
    val chords = generateSequence { Chord.random() }.take(10)
    chords.forEach { chord ->
        println("Current chord: ${chord.name} (${chord.symbols})")
        play(chord.toMidiNotes(), 1.seconds)
    }
}