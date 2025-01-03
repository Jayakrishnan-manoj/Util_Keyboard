package com.jayk.utilkeyboard

import android.inputmethodservice.InputMethodService
import android.view.HapticFeedbackConstants
import android.view.KeyEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.jayk.utilkeyboard.databinding.KeyboardLayoutBinding

class KeyboardService : InputMethodService() {

    private lateinit var binding: KeyboardLayoutBinding
    private var isCapitalized = false
    private var isSymbolsMode = false

    override fun onCreateInputView(): View {
        binding = KeyboardLayoutBinding.inflate(layoutInflater)

        println(isCapitalized)

        fun performHapticFeedback(view: View) {
            view.performHapticFeedback(
                HapticFeedbackConstants.KEYBOARD_TAP,

                )
        }

        val letterButtons = mapOf(
            binding.btnA to Pair("a", "A"),
            binding.btnB to Pair("b", "B"),
            binding.btnC to Pair("c", "C"),
            binding.btnD to Pair("d", "D"),
            binding.btnE to Pair("e", "E"),
            binding.btnF to Pair("f", "F"),
            binding.btnG to Pair("g", "G"),
            binding.btnH to Pair("h", "H"),
            binding.btnI to Pair("i", "I"),
            binding.btnJ to Pair("j", "J"),
            binding.btnK to Pair("k", "K"),
            binding.btnL to Pair("l", "L"),
            binding.btnM to Pair("m", "M"),
            binding.btnN to Pair("n", "N"),
            binding.btnO to Pair("o", "O"),
            binding.btnP to Pair("p", "P"),
            binding.btnQ to Pair("q", "Q"),
            binding.btnR to Pair("r", "R"),
            binding.btnS to Pair("s", "S"),
            binding.btnT to Pair("t", "T"),
            binding.btnU to Pair("u", "U"),
            binding.btnV to Pair("v", "V"),
            binding.btnW to Pair("w", "W"),
            binding.btnX to Pair("x", "X"),
            binding.btnY to Pair("y", "Y"),
            binding.btnZ to Pair("z", "Z")
        )

        val numberButtons = mapOf(
            binding.btn0 to "0",
            binding.btn1 to "1",
            binding.btn2 to "2",
            binding.btn3 to "3",
            binding.btn4 to "4",
            binding.btn5 to "5",
            binding.btn6 to "6",
            binding.btn7 to "7",
            binding.btn8 to "8",
            binding.btn9 to "9"
        )

        val punctuationButtons = mapOf(
            binding.btnDot to ".",
            binding.btnComma to ","
        )

        val symbolButtons = mapOf(
            binding.btnStar to "*",
            binding.btnDQ to "\"",
            binding.btnSQ to "'",
            binding.btnColon to ":",
            binding.btnSemiColon to ";",
            binding.btnExclamation to "!",
            binding.btnQn to "?",
            binding.btnAt to "@",
            binding.btnHash to "#",
            binding.btnAnd to "&",
            binding.btnUnderscore to "_",
            binding.btnMinus to "-",
            binding.btnDollar to "$",
            binding.btnLeftBrace to "(",
            binding.btnRightBrace to ")",
            binding.btnPlus to "+",
            binding.btnSlash to "/"
        )


        fun updateButtonTexts() {
            if (isSymbolsMode) {
                symbolButtons.forEach { (button, symbol) ->
                    button.text = symbol
                }
            } else {
                letterButtons.forEach { (button, textPair) ->
                    button.text = if (isCapitalized) textPair.second else textPair.first
                }
            }
        }

        fun toggleSymbolsMode() {

            isSymbolsMode = !isSymbolsMode
            println(isSymbolsMode)
            if (isSymbolsMode){
                binding.btnSymbols.setText("ABC")
            }else{
                binding.btnSymbols.setText("123")
            }
            binding.asdf.visibility = if(isSymbolsMode) View.GONE else View.VISIBLE
            binding.qwerty.visibility = if (isSymbolsMode) View.GONE else View.VISIBLE
            binding.SymbolsFirst.visibility = if (isSymbolsMode) View.VISIBLE else View.GONE
            binding.numbers.visibility = if (isSymbolsMode) View.VISIBLE else View.GONE
            binding.symbolsLast.visibility = if (isSymbolsMode) View.VISIBLE else View.GONE
            binding.zxcv.visibility = if(isSymbolsMode) View.GONE else View.VISIBLE
            updateButtonTexts()
        }

        symbolButtons.forEach{(button,text)->
            button.setOnClickListener { view->
                performHapticFeedback(view)
                currentInputConnection?.commitText(text,1)

            }
        }

        letterButtons.forEach { (button, textPair) ->
            button.setOnClickListener { view ->
                view.performHapticFeedback(
                    HapticFeedbackConstants.KEYBOARD_TAP,
                )
                val textToCommit = if (isCapitalized) textPair.second else textPair.first
                currentInputConnection?.commitText(textToCommit, 1)
            }
        }

        numberButtons.forEach { (button, text) ->
            button.setOnClickListener { view ->
                performHapticFeedback(view)
                currentInputConnection?.commitText(text, 1)
            }
        }

        punctuationButtons.forEach { (button, text) ->
            button.setOnClickListener { view ->
                performHapticFeedback(view)
                currentInputConnection?.commitText(text, 1)
            }
        }

        binding.btnSymbols.setOnClickListener { view ->
            performHapticFeedback(view)
            toggleSymbolsMode()
        }

        binding.btnBackSpace.setOnClickListener { view ->
            performHapticFeedback(view)
            currentInputConnection?.sendKeyEvent(
                KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)
            )
        }

        binding.btnBackSpace2.setOnClickListener { view ->
            performHapticFeedback(view)
            currentInputConnection?.sendKeyEvent(
                KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)
            )
        }

        binding.btnCapitalize.setOnClickListener { view ->
            performHapticFeedback(view)
            isCapitalized = !isCapitalized
            updateButtonTexts()
            binding.btnCapitalize.backgroundTintList = ContextCompat.getColorStateList(
                baseContext,
                if (isCapitalized) R.color.primaryColor else R.color.greyColor
            )
        }

        binding.btnCapitalize2.setOnClickListener { view ->
            performHapticFeedback(view)
            isCapitalized = !isCapitalized
            updateButtonTexts()
            binding.btnCapitalize.backgroundTintList = ContextCompat.getColorStateList(
                baseContext,
                if (isCapitalized) R.color.primaryColor else R.color.greyColor
            )
        }

        //updateButtonTexts()

        binding.btnSpace.setOnClickListener { view ->
            performHapticFeedback(view)
            currentInputConnection?.sendKeyEvent(
                KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE)
            )
        }

        binding.btnEnter.setOnClickListener { view ->
            performHapticFeedback(view)
            currentInputConnection?.sendKeyEvent(
                KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER)
            )
        }

        return binding.root
    }
}