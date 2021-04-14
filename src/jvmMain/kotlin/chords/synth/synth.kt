package chords.synth

import javax.sound.midi.MidiSystem
import kotlin.time.Duration
import javax.sound.midi.Synthesizer as JavaxSynthesizer

typealias Synthesizer = JavaxSynthesizer

inline fun withSynth(
    synthSupplier: () -> Synthesizer = { MidiSystem.getSynthesizer() },
    action: Synthesizer.() -> Unit
) = with(synthSupplier()) {
    open()
    action()
    close()
}

fun Synthesizer.play(notes: List<Int>, duration: Duration) {
    notes.forEach {
        channels.first().noteOn(it, 80)
    }
    Thread.sleep(duration.toLongMilliseconds())
    notes.forEach { channels.first().noteOff(it) }
}

fun Synthesizer.play(note: Int, duration: Duration) =
    play(listOf(note), duration)