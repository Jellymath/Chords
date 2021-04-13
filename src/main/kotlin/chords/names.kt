package chords

fun Note.name() = when (this) {
    Note.C -> "C"
    Note.CSharp -> "C sharp"
    Note.DFlat -> "D flat"
    Note.D -> "D"
    Note.DSharp -> "D sharp"
    Note.EFlat -> "E flat"
    Note.E -> "E"
    Note.F -> "F"
    Note.FSharp -> "F sharp"
    Note.GFlat -> "G flat"
    Note.G -> "G"
    Note.GSharp -> "G sharp"
    Note.AFlat -> "A flat"
    Note.A -> "A"
    Note.ASharp -> "A sharp"
    Note.BFlat -> "B flat"
    Note.B -> "B"
}

val Note.symbols
    get() = when (this) {
        Note.C -> "C"
        Note.CSharp -> "C♯"
        Note.DFlat -> "D♭"
        Note.D -> "D"
        Note.DSharp -> "D♯"
        Note.EFlat -> "E♭"
        Note.E -> "E"
        Note.F -> "F"
        Note.FSharp -> "F♯"
        Note.GFlat -> "G♭"
        Note.G -> "G"
        Note.GSharp -> "G♯"
        Note.AFlat -> "A♭"
        Note.A -> "A"
        Note.ASharp -> "A♯"
        Note.BFlat -> "B♭"
        Note.B -> "B"
    }

val Chord.technicalString get() = "$baseNote ${chordType.technicalString}}${if (baseNote != bassNote) " over $bassNote" else ""}"

val Chord.name get() = "${baseNote.name()} ${chordType.name}${if (baseNote != bassNote) " over ${bassNote.name()}" else ""}"

val Chord.symbols get() = "${baseNote.symbols}${chordType.longSymbols}${if (baseNote != bassNote) "/${bassNote.symbols}" else ""}"

val ChordType.technicalString get() = intervals.sortedBy { it.type }.toString()

val ChordType.name
    get() =
        when (this) {
            minor -> "Minor"
            major -> "Major"

            diminished -> "Diminished"
            augmented -> "Augmented"

            suspendedSecond -> "Suspended Second"
            suspendedFourth -> "Suspended Fourth"

            dominantSeventh -> "Dominant Seventh"
            majorSeventh -> "Major Seventh"
            minorSeventh -> "Minor Seventh"
            minorMajorSeventh -> "Minor Major Seventh"

            dominantNinth -> "Dominant Ninth"
            majorNinth -> "Major Ninth"
            minorNinth -> "Minor Ninth"
            minorMajorNinth -> "Minor Major Ninth"

            eleventh -> "Eleventh"
            majorEleventh -> "Major Eleventh"
            minorEleventh -> "Minor Eleventh"
            minorMajorEleventh -> "Minor Major Eleventh"

            thirteenth -> "Thirteenth"
            majorThirteenth -> "Major Thirteenth"
            else -> technicalString
        }


val ChordType.longSymbols
    get() =
        when (this) {
            minor -> "min"
            major -> "maj"

            diminished -> "dim"
            augmented -> "aug"

            suspendedSecond -> "sus2"
            suspendedFourth -> "sus4"

            dominantSeventh -> "7"
            majorSeventh -> "maj7"
            minorSeventh -> "min7"
            minorMajorSeventh -> "min maj7"

            dominantNinth -> "9"
            majorNinth -> "maj9"
            minorNinth -> "min9"
            minorMajorNinth -> "min maj9"

            eleventh -> "11"
            majorEleventh -> "maj11"
            minorEleventh -> "min11"
            minorMajorEleventh -> "min maj11"

            thirteenth -> "13"
            majorThirteenth -> "maj13"
            else -> technicalString
        }