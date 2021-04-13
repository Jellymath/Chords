package console.predefined

import chords.*
import chords.Interval.*
import chords.Note.*
import kotlin.time.Duration
import kotlin.time.seconds

fun main() {
    val preJazzHarmony = listOf(
        D + minor to 2.seconds,
        EFlat + major over F to 1.seconds,
        F + major to 1.seconds,
        BFlat + major to 2.seconds,
        BFlat + major to 1.seconds,
        C + major to 1.seconds,
        D + minor to 2.seconds
    )
    play(preJazzHarmony)

    val firstLevelJazzHarmony = listOf(
        D + minorSeventh to 2.seconds,
        C + minorSeventh to 1.seconds,
        F + dominantSeventh to 1.seconds,
        BFlat + major + MajorSixth to 2.seconds,
        E + halfDiminishedSeventh to 1.seconds,
        A + dominantSeventh to 1.seconds,
        D + minorSeventh to 2.seconds
    )
    play(firstLevelJazzHarmony)
}

private fun play(chordsInTime: List<Pair<Chord, Duration>>) {
    chordsInTime.forEach { (chord, time) ->
        println("Current chord: ${chord.name} (${chord.symbols})")
        withSynth {
            val notes = chord.toMidiNotes()
            println(notes)
            play(notes, time)
        }
    }
}