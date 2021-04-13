package chords

inline fun <reified T : Enum<T>> random() = enumValues<T>().random()

val basicChordTypes = listOf(major, minor, suspendedFourth, dominantSeventh, majorSeventh, minorSeventh)
fun Chord.Companion.random(chordTypes: Collection<ChordType> = basicChordTypes) = random<Note>() + chordTypes.random()