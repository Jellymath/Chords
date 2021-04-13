package chords

import chords.Interval.*

data class ChordType(val intervals: Set<Interval>)

operator fun Interval.plus(other: Interval) = ChordType(setOf(this, other))
operator fun ChordType.plus(other: Interval) = ChordType(intervals + other)


//triad chords
val major = PerfectUnison + MajorThird + PerfectFifth
val minor = PerfectUnison + MinorThird + PerfectFifth

//altered triad chords
val diminished = PerfectUnison + MinorThird + DiminishedFifth
val augmented = PerfectUnison + MajorThird + AugmentedFifth

//suspended chords
val suspendedFourth = PerfectUnison + PerfectFourth + PerfectFifth
val suspendedSecond = PerfectUnison + MajorSecond + PerfectFifth

//seventh chords
val dominantSeventh = major + MinorSeventh
val majorSeventh = major + MajorSeventh
val minorSeventh = minor + MinorSeventh
val minorMajorSeventh = minor + MajorSeventh

// seventh + altered chord
val augmentedMajorSeventh = augmented + MajorSeventh
val augmentedSeventh = augmented + MinorSeventh
val halfDiminishedSeventh = diminished + MinorSeventh
val diminishedSeventh = diminished + DiminishedSeventh
val seventhFlatFive = PerfectUnison + MajorThird + DiminishedFifth + MinorSeventh


//extended chords

//ninth chords
val majorNinth = majorSeventh + MajorNinth
val dominantNinth = dominantSeventh + MajorNinth
val minorMajorNinth = minorMajorSeventh + MajorNinth
val minorNinth = minorSeventh + MajorNinth

//ninth + altered chords
val augmentedMajorNinth = augmentedMajorSeventh + MajorNinth
val augmentedDominantNinth = PerfectUnison + MajorThird + AugmentedFifth + MinorSeventh + MajorNinth
val halfDiminishedNinth = halfDiminishedSeventh + MajorNinth
val diminishedNinth = diminishedSeventh + MajorNinth

//minor ninth chords
val dominantMinorNinth = dominantNinth + MinorNinth
val halfDiminishedMinorNinth = halfDiminishedSeventh + MinorNinth
val diminishedMinorNinth = diminishedSeventh + MinorNinth

//eleventh chords
val eleventh = dominantNinth + PerfectEleventh
val majorEleventh = majorNinth + PerfectEleventh
val minorMajorEleventh = minorMajorNinth + PerfectEleventh
val minorEleventh = minorNinth + PerfectEleventh

//eleventh altered chords
val augmentedMajorEleventh = augmentedMajorNinth + PerfectEleventh
val augmentedEleventh = augmentedDominantNinth + PerfectEleventh
val halfDiminishedEleventh = halfDiminishedNinth + PerfectEleventh
val diminishedEleventh = diminishedNinth + PerfectEleventh

//thirteenth chords
val thirteenth = eleventh + MajorThirteenth
val majorThirteenth = majorEleventh + MajorThirteenth

enum class ChordPattern {
    Basic,
    Suspended,
    Seventh,
    Altered,
    Extended,
//    Added
}

// Note - there are some intersections that can be included (like augmentedMajorEleventh as Altered + Extended)
// However for now these options are considered as a bit more complex than needed (maybe provide additional flag for that?)
fun createChordTypePool(options: Set<ChordPattern> = setOf(ChordPattern.Basic)) = buildSet {
    if (ChordPattern.Basic in options) {
        add(minor)
        add(major)
    }
    if (ChordPattern.Suspended in options) {
        add(suspendedFourth)
        add(suspendedSecond)
    }
    if (ChordPattern.Seventh in options) {
        add(dominantSeventh)
        add(majorSeventh)
        add(minorSeventh)
        add(minorMajorSeventh)
    }
    if (ChordPattern.Altered in options) {
        add(diminished)
        add(augmented)
    }
    if (ChordPattern.Extended in options) {
        add(dominantNinth)
        add(majorNinth)
        add(minorNinth)
        add(minorMajorNinth)

        add(eleventh)
        add(majorEleventh)
        add(minorEleventh)
        add(minorMajorEleventh)

        add(thirteenth)
        add(majorThirteenth)
    }
}




