package chords

import chords.Interval.*
import chords.IntervalQuality.*
import chords.IntervalType.*

enum class Interval {
    PerfectUnison,
    AugmentedUnison,

    DiminishedSecond,
    MinorSecond,
    MajorSecond,
    AugmentedSecond,

    DiminishedThird,
    MinorThird,
    MajorThird,
    AugmentedThird,

    DiminishedFourth,
    PerfectFourth,
    AugmentedFourth,

    DiminishedFifth,
    PerfectFifth,
    AugmentedFifth,

    DiminishedSixth,
    MinorSixth,
    MajorSixth,
    AugmentedSixth,

    DiminishedSeventh,
    MinorSeventh,
    MajorSeventh,
    AugmentedSeventh,

    DiminishedOctave,
    PerfectOctave,
    AugmentedOctave,

    DiminishedNinth,
    MinorNinth,
    MajorNinth,
    AugmentedNinth,

    DiminishedTenth,
    MinorTenth,
    MajorTenth,
    AugmentedTenth,

    DiminishedEleventh,
    PerfectEleventh,
    AugmentedEleventh,

    DiminishedTwelfth,
    PerfectTwelfth,
    AugmentedTwelfth,

    DiminishedThirteenth,
    MinorThirteenth,
    MajorThirteenth,
    AugmentedThirteenth,

    DiminishedFourteenth,
    MinorFourteenth,
    MajorFourteenth,
    AugmentedFourteenth,

    DiminishedFifteenth,
    PerfectFifteenth,
    AugmentedFifteenth
}

enum class IntervalType {
    Unison,
    Second,
    Third,
    Fourth,
    Fifth,
    Sixth,
    Seventh,
    Octave,

    Ninth,
    Tenth,
    Eleventh,
    Twelfth,
    Thirteenth,
    Fourteenth,
    Fifteenth
}

enum class IntervalQuality {
    Perfect,
    Major,
    Minor,
    Augmented,
    Diminished
}

val Interval.type
    get() = when (this) {
        PerfectUnison, AugmentedUnison -> Unison
        DiminishedSecond, MinorSecond, MajorSecond, AugmentedSecond -> Second
        DiminishedThird, MinorThird, MajorThird, AugmentedThird -> Third
        DiminishedFourth, PerfectFourth, AugmentedFourth -> Fourth
        DiminishedFifth, PerfectFifth, AugmentedFifth -> Fifth
        DiminishedSixth, MinorSixth, MajorSixth, AugmentedSixth -> Sixth
        DiminishedSeventh, MinorSeventh, MajorSeventh, AugmentedSeventh -> Seventh
        DiminishedOctave, PerfectOctave, AugmentedOctave -> Octave
        DiminishedNinth, MinorNinth, MajorNinth, AugmentedNinth -> Ninth
        DiminishedTenth, MinorTenth, MajorTenth, AugmentedTenth -> Tenth
        DiminishedEleventh, PerfectEleventh, AugmentedEleventh -> Eleventh
        DiminishedTwelfth, PerfectTwelfth, AugmentedTwelfth -> Twelfth
        DiminishedThirteenth, MinorThirteenth, MajorThirteenth, AugmentedThirteenth -> Thirteenth
        DiminishedFourteenth, MinorFourteenth, MajorFourteenth, AugmentedFourteenth -> Fourteenth
        DiminishedFifteenth, PerfectFifteenth, AugmentedFifteenth -> Fifteenth
    }

val Interval.quality
    get() = when (this) {
        PerfectUnison, PerfectFourth, PerfectFifth, PerfectOctave, PerfectEleventh, PerfectTwelfth, PerfectFifteenth -> Perfect

        MinorSecond, MinorThird, MinorSixth, MinorSeventh, MinorNinth, MinorTenth, MinorThirteenth, MinorFourteenth -> Minor

        MajorSecond, MajorThird, MajorSixth, MajorSeventh, MajorNinth, MajorTenth, MajorThirteenth, MajorFourteenth -> Major

        AugmentedUnison, AugmentedSecond, AugmentedThird, AugmentedFourth,
        AugmentedFifth, AugmentedSixth, AugmentedSeventh, AugmentedOctave,
        AugmentedNinth, AugmentedTenth, AugmentedEleventh, AugmentedTwelfth,
        AugmentedThirteenth, AugmentedFourteenth, AugmentedFifteenth -> Augmented

        DiminishedSecond, DiminishedThird, DiminishedFourth,
        DiminishedFifth, DiminishedSixth, DiminishedSeventh, DiminishedOctave,
        DiminishedNinth, DiminishedTenth, DiminishedEleventh, DiminishedTwelfth,
        DiminishedThirteenth, DiminishedFourteenth, DiminishedFifteenth -> Diminished
    }

val Interval.semitonesNumber get() = when(this) {
    PerfectUnison, DiminishedSecond -> 0
    MinorSecond, AugmentedUnison -> 1
    MajorSecond, DiminishedThird -> 2
    MinorThird, AugmentedSecond -> 3
    MajorThird, DiminishedFourth -> 4
    PerfectFourth, AugmentedThird -> 5
    DiminishedFifth, AugmentedFourth -> 6
    PerfectFifth, DiminishedSixth -> 7
    MinorSixth, AugmentedFifth -> 8
    MajorSixth, DiminishedSeventh -> 9
    MinorSeventh, AugmentedSixth -> 10
    MajorSeventh, DiminishedOctave -> 11
    PerfectOctave, AugmentedSeventh, DiminishedNinth -> 12
    MinorNinth, AugmentedOctave -> 13
    MajorNinth, DiminishedTenth -> 14
    MinorTenth, AugmentedNinth -> 15
    MajorTenth, DiminishedEleventh -> 16
    PerfectEleventh, AugmentedTenth -> 17
    DiminishedTwelfth, AugmentedEleventh -> 18
    PerfectTwelfth, DiminishedThirteenth -> 19
    MinorThirteenth, AugmentedTwelfth -> 20
    MajorThirteenth, DiminishedFourteenth -> 21
    MinorFourteenth, AugmentedThirteenth -> 22
    MajorFourteenth, DiminishedFifteenth -> 23
    PerfectFifteenth, AugmentedFourteenth -> 24
    AugmentedFifteenth -> 25
}

val Interval.compound get() = type.compound

val IntervalType.compound get() = when(this) {
    Unison, Second, Third, Fourth, Fifth, Sixth, Seventh, Octave -> false
    else -> true
}
