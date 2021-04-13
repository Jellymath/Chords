package chords

data class Chord(val baseNote: Note, val chordType: ChordType, val bassNote: Note = baseNote) {
    companion object
}
operator fun Note.plus(chordType: ChordType) = Chord(this, chordType)
operator fun Note.plus(interval: Interval) = Chord(this, ChordType(setOf(Interval.PerfectUnison, interval)))
operator fun Chord.plus(interval: Interval) = copy(chordType = ChordType(chordType.intervals + interval))
infix fun Chord.over(bassNote: Note) = copy(bassNote = bassNote)