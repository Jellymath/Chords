package chords

val DistinctNote.midiNoteNumber
    get() = when (this) {
        DistinctNote.C -> 60
        DistinctNote.CSharp -> 61
        DistinctNote.D -> 62
        DistinctNote.DSharp -> 63
        DistinctNote.E -> 64
        DistinctNote.F -> 65
        DistinctNote.FSharp -> 66
        DistinctNote.G -> 67
        DistinctNote.GSharp -> 68
        DistinctNote.A -> 69
        DistinctNote.ASharp -> 70
        DistinctNote.B -> 71
    }

val Note.midiNoteNumber get() = toDistinct().midiNoteNumber

fun Chord.toMidiNotes() = run {
    val baseNoteNumber = baseNote.midiNoteNumber
    val mainChordNumbers = chordType.intervals.map { baseNoteNumber + it.semitonesNumber }.sorted()
    if (bassNote != baseNote) {
        val bassNoteNumber = bassNote.midiNoteNumber.let { if (it < baseNoteNumber) it else it - 12 }
        listOf(bassNoteNumber) + mainChordNumbers
    } else mainChordNumbers
}