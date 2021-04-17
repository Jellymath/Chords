package chords.synth

import kotlin.math.pow
import kotlin.time.Duration

const val defaultGainValue = 0.5

class Synthesizer {
    val audioContext = js("new AudioContext()")
    val gain = audioContext.createGain()

    init {
        gain.gain.setValueAtTime(defaultGainValue, audioContext.currentTime)
        gain.connect(audioContext.destination)
    }
}

val defaultSynth = Synthesizer()

fun Synthesizer.play(notes: List<Int>, duration: Duration) {
    println("Used frequencies: ${notes.map(::midiToHertz)}")
    val oscillators = notes.map { note ->
        val oscillator = audioContext.createOscillator()
        oscillator.type = "sawtooth"
        oscillator.frequency.setValueAtTime(midiToHertz(note), audioContext.currentTime)
        oscillator.connect(gain)
        oscillator
    }
    oscillators.forEach { it.start() }
    val stop = { oscillators.forEach { it.stop() } }
    val durationMillis = duration.toLongMilliseconds()
    js("setTimeout(stop, durationMillis)")
}

fun Synthesizer.play(note: Int, duration: Duration) =
    play(listOf(note), duration)

fun Synthesizer.mute() {
    gain.gain.value = 0
}

fun Synthesizer.unmute() {
    gain.gain.value = defaultGainValue
}

var Synthesizer.muted: Boolean
    get() = gain.gain.value == 0
    set(value) = if(value) mute() else unmute()

private fun midiToHertz(midiNumber: Int): Double = 2.0.pow((midiNumber - 69) / 12.0) * 440