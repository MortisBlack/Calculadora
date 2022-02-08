package urrego.jesus.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // 0->nada  1-suma  2->resta  3->mult  4->div
    var oper: Int = 0

    var numero1: Double = 0.0
    var numero2: Double = 0.0

    var flag_igual: Boolean = false

    lateinit var tv_num1: TextView
    lateinit var tv_num2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_num1 = findViewById(R.id.tv_num1)
        tv_num2 = findViewById(R.id.tv_num2)

        val btnBorrar: Button = findViewById(R.id.button_erase)
        val btnIgual: Button = findViewById(R.id.button_equals)

        btnIgual.setOnClickListener{

            if(tv_num1.text.equals("Error")) {
                tv_num1.setText("")
            }

            if(!flag_igual){
                numero2 = tv_num2.text.toString().toDouble()
                flag_igual = true
            }

            var resp: Double = 0.0

            when (oper) {
                1 -> resp = numero1 + numero2
                2 -> resp = numero1 - numero2
                3 -> resp = numero1 * numero2
                4 -> {
                    if (numero2 == 0.0) {
                        tv_num1.setText("Error")
                        tv_num2.setText("")
                        numero1 = 0.0
                        numero2 = 0.0
                        oper = 0
                    } else {
                        resp = numero1 / numero2
                    }
                }
            }

            if (resp == 0.0) {
                tv_num2.setText("")
            } else {
                tv_num2.setText(resp.toString())
            }

            if(!tv_num1.text.equals("Error")) {
                tv_num1.setText("")
            }
            numero1 = resp
        }

        btnBorrar.setOnClickListener{
            tv_num1.setText("")
            tv_num2.setText("")
            numero1 = 0.0
            numero2 = 0.0
            oper = 0
        }
    }

    fun huboError(view: View){
        if(tv_num1.text.equals("Error")) {
            tv_num1.setText("")
        }
    }
    fun presionarDigito(view: View){

        huboError(view)

        flag_igual = false

        var num2: String = tv_num2.text.toString()

        when(view.id){
            R.id.button0 -> tv_num2.setText(num2 + "0")
            R.id.button1 -> tv_num2.setText(num2 + "1")
            R.id.button2 -> tv_num2.setText(num2 + "2")
            R.id.button3 -> tv_num2.setText(num2 + "3")
            R.id.button4 -> tv_num2.setText(num2 + "4")
            R.id.button5 -> tv_num2.setText(num2 + "5")
            R.id.button6 -> tv_num2.setText(num2 + "6")
            R.id.button7 -> tv_num2.setText(num2 + "7")
            R.id.button8 -> tv_num2.setText(num2 + "8")
            R.id.button9 -> tv_num2.setText(num2 + "9")
            R.id.button_point -> if(num2.contains('.')) tv_num2.setText(num2) else tv_num2.setText(num2 + '.')
        }
    }

    fun clicOperacion(view: View){

        huboError(view)

        if(!tv_num2.text.equals("")){
            flag_igual = false
            numero1 = tv_num2.text.toString().toDouble()
            var num2_text: String = tv_num2.text.toString()
            tv_num2.setText("")
            when(view.id) {
                R.id.button_plus -> {
                    tv_num1.setText(num2_text + " +")
                    oper = 1
                }
                R.id.button_less -> {
                    tv_num1.setText(num2_text + " -")
                    oper = 2
                }
                R.id.button_multiply -> {
                    tv_num1.setText(num2_text + " x")
                    oper = 3
                }
                R.id.button_divide -> {
                    tv_num1.setText(num2_text + " /")
                    oper = 4
                }
            }
        }
    }
}