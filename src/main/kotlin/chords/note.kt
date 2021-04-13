package chords

enum class Note {
    C,
    CSharp, DFlat,
    D,
    DSharp, EFlat,
    E,
    F,
    FSharp, GFlat,
    G,
    GSharp, AFlat,
    A,
    ASharp, BFlat,
    B;

    companion object
}

enum class DistinctNote(val notes: List<Note>) {
    C(listOf(Note.C)),
    CSharp(listOf(Note.CSharp, Note.DFlat)),
    D(listOf(Note.D)),
    DSharp(listOf(Note.DSharp, Note.EFlat)),
    E(listOf(Note.E)),
    F(listOf(Note.F)),
    FSharp(listOf(Note.FSharp, Note.GFlat)),
    G(listOf(Note.G)),
    GSharp(listOf(Note.GSharp, Note.AFlat)),
    A(listOf(Note.A)),
    ASharp(listOf(Note.ASharp, Note.BFlat)),
    B(listOf(Note.B));

    companion object
}


fun Note.toDistinct() = DistinctNote.values().first { this in it.notes }

