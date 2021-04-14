package console.interactive

import chords.*
import chords.synth.*
import kotlin.time.seconds

fun main() = withSynth {
    println("Type \"next\" to play next random chord")
    val possibleChordsString = ChordPattern.values().contentToString()
    println("Type \"add X\" to add chord pattern. Possible chords patterns: $possibleChordsString")
    println("Type \"remove X\" to remove chord pattern. Possible chords patterns: $possibleChordsString")
    println("Type \"exit\" to exit")
    val currentOptions = mutableSetOf(ChordPattern.Basic)
    var chordPool = createChordTypePool(currentOptions)
    while (true) {
        val line = readLine().takeUnless { it.isNullOrBlank() }?.trim() ?: return
        when {
            line == "exit" -> return
            line == "next" -> {
                val chord = Chord.random(chordPool)
                println("Current chord: ${chord.name} (${chord.symbols})")
                play(chord.toMidiNotes(), 1.5.seconds)
            }
            line.startsWith("add ") -> {
                val chordPattern = ChordPattern.valueOf(line.substringAfter("add ").capitalize())
                currentOptions += chordPattern
                chordPool = createChordTypePool(currentOptions)
                println("$chordPattern added, current chord options: $currentOptions")
            }
            line.startsWith("remove ") -> {
                val chordPattern = ChordPattern.valueOf(line.substringAfter("remove ").capitalize())
                currentOptions -= chordPattern
                chordPool = createChordTypePool(currentOptions)
                println("$chordPattern removed, current chord options: $currentOptions")
            }
            else -> throw IllegalArgumentException("Unexpected input")
        }
    }
}

