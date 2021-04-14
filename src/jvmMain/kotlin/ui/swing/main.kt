package ui.swing

import chords.*
import chords.synth.*
import java.awt.*
import java.awt.event.*
import javax.swing.*
import kotlin.concurrent.thread
import kotlin.time.seconds


fun main() = SwingUtilities.invokeLater {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    JFrame("Chords Generator").run {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        contentPane = ChordsPanel().apply { isOpaque = true }

        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }
}

// vaguely based on https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/CheckBoxDemoProject/src/components/CheckBoxDemo.java
class ChordsPanel : JPanel(BorderLayout()) {
    private val updateChordPool: (ItemEvent) -> Unit = {
        currentPool = createChordTypePool(chordOptions())
    }

    private val showAndPlayChord: (ActionEvent) -> Unit = {
        val chord = Chord.random(currentPool)
        label.text = "${chord.name} (${chord.symbols})"
        thread {
            withSynth {
                play(chord.toMidiNotes(), 2.seconds)
            }
        }
    }

    private val basic = JCheckBox("Basic", true).apply { addItemListener(updateChordPool) }
    private val suspended = JCheckBox("Suspended").apply { addItemListener(updateChordPool) }
    private val seventh = JCheckBox("Seventh").apply { addItemListener(updateChordPool) }
    private val altered = JCheckBox("Altered").apply { addItemListener(updateChordPool) }
    private val extended = JCheckBox("Extended").apply { addItemListener(updateChordPool) }

    private val button = JButton("Next").apply {
        addActionListener(showAndPlayChord)
        font = font.deriveFont(30f)
    }

    private val label = JLabel(
        """Press "Next" to play new chord. Select/deselect to add/remove variations.""",
        SwingConstants.CENTER
    ).apply {
        font = font.deriveFont(30f)
    }

    private var currentPool = createChordTypePool(chordOptions())

    init {
        add(JPanel(GridLayout(0, 1)).apply {
            add(basic)
            add(suspended)
            add(seventh)
            add(altered)
            add(extended)
        }, BorderLayout.LINE_START)

        add(JPanel(GridLayout(0, 1)).apply {
            add(label)
            add(button)
        }, BorderLayout.CENTER)


        border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
    }

    private fun chordOptions() = buildSet {
        if (basic.isSelected) add(ChordPattern.Basic)
        if (suspended.isSelected) add(ChordPattern.Suspended)
        if (seventh.isSelected) add(ChordPattern.Seventh)
        if (altered.isSelected) add(ChordPattern.Altered)
        if (extended.isSelected) add(ChordPattern.Extended)
    }
}