package chords.synth

import kotlin.math.pow
import kotlin.time.Duration

class Synthesizer {
    val audioContext = js("new AudioContext()")
}

inline fun withSynth(
    synthSupplier: () -> Synthesizer = { Synthesizer() },
    action: Synthesizer.() -> Unit
) = with(synthSupplier()) {
    action()
}

fun Synthesizer.play(notes: List<Int>, duration: Duration) {
    println("Used frequencies: ${notes.map(::midiToHertz)}")
    val oscillators = notes.map { note ->
        val oscillator = audioContext.createOscillator()
        oscillator.type = "sawtooth"
        oscillator.frequency.setValueAtTime(midiToHertz(note), audioContext.currentTime)
        oscillator.connect(audioContext.destination)
        oscillator
    }
    oscillators.forEach { it.start() }
    val stop = { oscillators.forEach { it.stop() } }
    val durationMillis = duration.toLongMilliseconds()
    js("setTimeout(stop, durationMillis)")
}

fun Synthesizer.play(note: Int, duration: Duration) =
    play(listOf(note), duration)

private fun midiToHertz(midiNumber: Int): Double = 2.0.pow((midiNumber - 69) / 12.0) * 440