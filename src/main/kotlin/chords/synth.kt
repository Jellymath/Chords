package chords

import javax.sound.midi.MidiSystem
import javax.sound.midi.Synthesizer
import kotlin.time.Duration

fun withSynth(
    synthSupplier: () -> Synthesizer = { MidiSystem.getSynthesizer() },
    synthProgram: Int = 0,
    action: Synthesizer.() -> Unit
) = with(synthSupplier()) {
    open()
    channels.first().programChange(synthProgram)
    action()
    close()
}

fun Synthesizer.play(notes: List<Int>, duration: Duration, velocity: Int = 80) {
    notes.forEach {
        channels.first().noteOn(it, velocity)
    }
    Thread.sleep(duration.toLongMilliseconds())
    notes.forEach { channels.first().noteOff(it) }
}

fun Synthesizer.play(note: Int, duration: Duration, velocity: Int = 80) =
    play(listOf(note), duration, velocity)