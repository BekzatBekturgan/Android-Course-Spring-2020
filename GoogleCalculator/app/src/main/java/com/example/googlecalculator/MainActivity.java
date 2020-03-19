package com.example.googlecalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.res.Configuration;

import java.math.BigInteger;
import java.text.Bidi;

public class MainActivity extends AppCompatActivity {
    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDiv,
            buttonMul, buttonClr, buttonEqual, buttonDel, buttonPercent, buttonSqrt, buttonDegree,
            buttonDot, buttonSin, buttonCos, buttonTan, buttonLn, buttonLog, buttonSquare,
            buttonNRoot, buttonFactorial, buttonTenDegree;
    TextView edt1;
    boolean isCos = false;
    boolean isSin = false;
    boolean isTan = false;
    boolean isLog = false;
    boolean isLn = false;
    boolean isFactorial = false;
    boolean isTenDegree = false;
    boolean isSquare = false;
    boolean isNRoot = false;


    // tools for me
    String operations = "+-√%÷×^";
    // checker for the clear after the wrong operation
    boolean checker(String text){
        if(text.length()> 0 && (text.charAt(0)=='d' || text.charAt(0)=='m')){
            return true;
        }
        return false;
    }
    // check if the last will operation, then return true;
    boolean checkForReturn(String text){
        if(text.length()>0){
            char current = text.charAt(text.length()-1);
            if(current == 'n' || current == 'g' || current == 's' || operations.indexOf(current)!=-1){
                return true;
            }
        }
        return false;
    }
    String changeTrigionimicFunctions(String text, String trigio) {
        int indexOfString = text.indexOf(trigio);
        String cur = "";
        if (text.charAt(indexOfString + trigio.length()) == '-') {
            cur += '-';
            indexOfString++;
        }
        for (int i = indexOfString + trigio.length(); i < text.length(); i++) {
            char s = text.charAt(i);
            if (s >= '0' && s <= '9') {
                cur += s;
            } else if (s == '.') {
                cur += s;
            } else {
                break;
            }
        }
        Double t = Double.parseDouble(cur);

        if (trigio.equals("tan")) t = Math.tan(t);
        else if (trigio.equals("sin")) t = Math.sin(t);
        else if (trigio.equals("cos")) t = Math.cos(t);
        else if (trigio.equals("10^")) t = Math.pow(10, t);
        else if (trigio.equals("log")) t = Math.log10(t);
        else if (trigio.equals("ln")) t = Math.log(t);
        else if (trigio.equals("^1/")) return cur;
        indexOfString = text.indexOf(trigio);
        String newly = text.substring(0, indexOfString);
        newly += Double.toString(t);
        newly += text.substring(indexOfString + trigio.length() + cur.length());
        return newly;
    }
    BigInteger factorial(double t){
        int c;

        BigInteger i = new BigInteger("1");
        BigInteger f = new BigInteger("1");
        BigInteger m = new BigInteger("-1");
        for (c = 1; c <= t; c++) {
            f = f.multiply(i);
            i = i.add(BigInteger.ONE);
        }
        return f;
    }
    // reverse strings
    String getReverseString(String text){
        String ans = "";
        for(int i = text.length()-1; i>=0; i--){
            ans+=text.charAt(i);
        }
        return ans;
    }
    String changeBeforeOperation(String text, String operation){
        int indexOfString = text.indexOf(operation);
        String cur = "";
        int start=-1;
        for(int i = indexOfString-1; i >= 0; i--){
            if(text.charAt(i) >= '0' && text.charAt(i) <= '9') cur+=text.charAt(i);
            else if(text.charAt(i) == '.'){
                if(operation.equals("!")){
                    return "dangeroues";
                }
                else{
                    cur+=text.charAt(i);
                }
            }
            else{
                start = i;
                break;
            }
        }
        if(start==-1) start = 0;
        else start++;
        String cur2 = getReverseString(cur);
        if(operation.equals("!") && (cur2.length()>=3)){
            return "dangeroues";
        }
        if(operation.equals("^1/")){
            return cur2;
        }
        Double t = Double.parseDouble(cur2);
        double ans=-1;
        BigInteger ansFac = new BigInteger("-1");
        if(operation.equals("!")) ansFac = factorial(t);
        else if(operation.equals("²")) ans = Math.pow(t, 2);
        String newly = text.substring(0, start);
        if(ans!=-1) newly+=Double.toString(ans);
        else if(!ansFac.equals(-1)) newly+=ansFac.toString(10);
        if(indexOfString+operation.length()<text.length())
            newly+=text.substring(indexOfString+operation.length());
        return newly;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSub = (Button) findViewById(R.id.buttonSub);
        buttonMul = (Button) findViewById(R.id.buttonMul);
        buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonEqual = (Button) findViewById(R.id.buttonEqual);
        buttonDel = (Button) findViewById(R.id.buttonDel);
        buttonClr = (Button) findViewById(R.id.buttonClr);
        buttonPercent = (Button) findViewById(R.id.buttonPercent);
        buttonDegree = (Button) findViewById(R.id.buttonDegree);
        buttonSqrt = (Button) findViewById(R.id.buttonSqrt);
        buttonDot = (Button) findViewById(R.id.buttonDot);
        edt1 = (TextView) findViewById(R.id.edt1);
        // second part of the calculator
        buttonSin = (Button) findViewById(R.id.buttonSin);
        buttonCos = (Button) findViewById(R.id.buttonCos);
        buttonTan = (Button) findViewById(R.id.buttonTan);
        buttonTenDegree = (Button) findViewById(R.id.buttonTenDegree);
        buttonNRoot = (Button) findViewById(R.id.buttonNRoot);
        buttonFactorial = (Button) findViewById(R.id.buttonFactorial);
        buttonSquare = (Button) findViewById(R.id.buttonSquare);
        buttonLn = (Button) findViewById(R.id.buttonLn);
        buttonLog = (Button) findViewById(R.id.buttonLog);

        if(savedInstanceState != null){
            edt1.setText(savedInstanceState.getString("numberInput"));
            isCos = savedInstanceState.getBoolean("isCos");
            isSin = savedInstanceState.getBoolean("isSin");
            isTan = savedInstanceState.getBoolean("isTan");
            isLog = savedInstanceState.getBoolean("isLog");
            isLn = savedInstanceState.getBoolean("isLn");
            isFactorial = savedInstanceState.getBoolean("isFactorial");
            isTenDegree = savedInstanceState.getBoolean("isTenDegree");
            isSquare = savedInstanceState.getBoolean("isSquare");
            isNRoot = savedInstanceState.getBoolean("isNRoot");

        }
        // all digits except zero
        View.OnClickListener buttonDigit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt1.getText().toString();
                Button btn = (Button)v;
                if(checker(text)){
                    edt1.setText("");
                    return;
                }
                if(text.length() != 0 && (text.charAt(text.length()-1)=='!' || text.charAt(text.length()-1)=='²')) return;
                if(text.length() != 0){
                    if(text.length() == 1 && text.charAt(text.length() - 1) == '0'){
                        text = btn.getText().toString();
                    }
                    else{
                        if(text.charAt(text.length() - 1) == '0' && (text.charAt(text.length() -  2) < '0' ||
                                text.charAt(text.length() - 2) > '9') && text.charAt(text.length() - 2) != '.'){
                            text = text.substring(0, text.length() - 1) + btn.getText().toString();
                        }
                        else{
                            text += btn.getText().toString();
                        }
                    }
                }
                else{
                    text += btn.getText().toString();
                }
                edt1.setText(text);
            }
        };

        // clear
        View.OnClickListener oclButtonClr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText("");
            }
        };

        // delete
        View.OnClickListener oclButtonDel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt1.getText().toString();
                if(checker(text)){
                    edt1.setText("");
                    return;
                }
                if(text.length() != 0) {
                    char cur = text.charAt(text.length()-1);
                    if(cur == 'n' && text.charAt(text.length()-2) == 'l'){
                        isLn = false;
                        edt1.setText(text.substring(0, text.length()-2));
                    }
                    else if(cur == 'n'){
                        String t = text.substring(text.length()-3);
                        if(t.equals("tan")){
                            isTan = false;
                            edt1.setText(text.substring(0, text.length()-3));
                        }
                        else if(t.equals("sin")){
                            isSin = false;
                            edt1.setText(text.substring(0, text.length()-3));
                        }
                    }
                    else if(cur == 'g'){
                        String t = text.substring(text.length()-3);
                        if(t.equals("log")){
                            isLog = false;
                            edt1.setText(text.substring(0, text.length()-3));
                        }
                    }
                    else if(cur == 's'){
                        String t = text.substring(text.length()-3);
                        if(t.equals("cos")){
                            isCos = false;
                            edt1.setText(text.substring(0, text.length()-3));
                        }
                    }
                    else
                        edt1.setText(text.substring(0, text.length() - 1));
                }
            }
        };
        // operations
        View.OnClickListener oclButtonOperation = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt1.getText().toString();
                Button btn = (Button)v;
                if(checker(text)){
                    edt1.setText("");
                    return;
                }
                if(text.length()==1 && text.charAt(0)=='-' && btn.getText().toString().equals("+")){
                    text = "";
                }
                else if(text.length()==1 && text.charAt(0)=='-'){
                    return;
                }
                if(text.length() != 0 && text.charAt(text.length() - 1) != '.'){
                    char current = text.charAt(text.length() - 1);
                    if(current == 'n' || current == 's' || current == 'g') {
                        return;
                    }
                    if(current == '+' || current == '÷' || current == '×' || current == '%' || current == '^'
                    ){
                        text = text.substring(0, text.length() - 1) + btn.getText().toString();
                    }
                    else if(current=='-'){
                        if(text.length()>1 && (text.charAt(text.length()-2) == '+' || text.charAt(text.length()-2) == '+' ||
                                text.charAt(text.length()-2) == '%' || text.charAt(text.length()-2) == '×' || text.charAt(text.length()-2) == '^' ||
                                text.charAt(text.length()-2) == '÷')) return;
                        text = text.substring(0, text.length()-1) + btn.getText().toString();
                    }
                    else if(current != '√'){
                        text += btn.getText().toString();
                    }
                }
                edt1.setText(text);
            }
        };
        View.OnClickListener oclButtonMinus = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt1.getText().toString();
                if(checker(text)){
                    edt1.setText("");
                    return;
                }
                if(text.length()>0 && (text.charAt(text.length()-1) == 'g' || text.charAt(text.length()-1) == 'n')) return;
                Button btn = (Button)v;
                if(text.length()==0){
                    edt1.setText("-");
                }
                else if(text.charAt(text.length()-1)=='-'){
                    return;
                }
                else{
                    if(text.length() != 0 && text.charAt(text.length() - 1) != '.'){
                        char current = text.charAt(text.length() - 1);
                        if(current == '+' || current == '%'
                        ){
                            text = text.substring(0, text.length() - 1) + btn.getText().toString();
                        }
                        else if(current == '÷' || current == '×' || current == '^'){
                            text+='-';
                        }
                        else if(current == '√'){
                            text = "minus number under square root prohibited";
                        }
                        else{
                            text+='-';
                        }
                    }
                    edt1.setText(text);
                }
            }
        };
        // dot button
        View.OnClickListener oclButtonDot = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasDot = false;
                String text = edt1.getText().toString();
                if(checker(text)){
                    edt1.setText("");
                    return;
                }
                if(text.length() != 0){
                    if(text.charAt(text.length() - 1) >= '0' && text.charAt(text.length() - 1) <= '9'){
                        for(int i = text.length() - 1; i >= 0; --i){
                            if(text.charAt(i) == '.') hasDot = true;
                            if(text.charAt(i) < '0' || text.charAt(i) > '9') break;
                        }
                        if(!hasDot){
                            text += '.';
                        }
                    }
                }
                edt1.setText(text);
            }
        };
        View.OnClickListener oclButtonSqrt = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt1.getText().toString();
                if(checker(text)){
                    edt1.setText("");
                    return;
                }
                if(text.length()>0){
                    char current = text.charAt(text.length()-1);
                    if(current == 'n' || current == 'g' || current == 's' || current=='²'){
                        return;
                    }
                }
                if(text.length()!= 0 && text.charAt(text.length()-1)=='-'){
                    text+="√";
                }
                else if(text.length()!=0 && text.charAt(text.length()-1)=='!') return;
                else if(text.length() != 0 && text.charAt(text.length() - 1) != '.' &&
                        (text.charAt(text.length() - 1) < '0' || text.charAt(text.length() - 1) > '9')){
                    char current = text.charAt(text.length() - 1);
                    if(current != '√' && current != '.')
                        text += '√';
                }
                else if(text.length() == 0) {
                    text += '√';
                }
                edt1.setText(text);
            }
        };
        View.OnClickListener oclButtonTrigionometric = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt1.getText().toString();
                Button btn = (Button)v;
                if(checker(text)){
                    edt1.setText("");
                    return;
                }
                if(text.length()!= 0 && (text.charAt(text.length()-1)=='!' || text.charAt(text.length()-1) == '²')) return;
                else if(text.length()!= 0 && ( text.charAt(text.length()-1)=='-' || text.charAt(text.length()-1)=='+'
                        || text.charAt(text.length()-1)=='÷' || text.charAt(text.length()-1) == '×' || text.charAt(text.length()-1)=='^')) {
                    text += btn.getText().toString();
                }
                else if(text.length() == 0) {
                    text += btn.getText().toString();
                }
                edt1.setText(text);
            }
        };
        View.OnClickListener oclButtonFactorial = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt1.getText().toString();
                if(text.length()!=0){
                    for(int i = text.length()-1; i >= 0; i--){
                        if(operations.indexOf(text.charAt(i))!=-1) break;
                        else if(text.charAt(i) == '.') return;
                    }
                    if(text.charAt(text.length()-1)>='0' && text.charAt(text.length()-1)<='9'){
                        text+='!';
                    }
                }
                edt1.setText(text);
            }
        };
        View.OnClickListener oclButtonTenDegree = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt1.getText().toString();
                if(checker(text)){
                    edt1.setText("");
                    return;
                }
                if(text.length()!=0) {
                    char current = text.charAt(text.length()-1);
                    if (current >= '0' && current <= '9') return;
                    if (current == '.') return;
                    if (current == 'n' || current == 'g' || current == 's') return;
                    if (current == '!') return;
                    if(current == '²') return;
                    text += "10^";
                }
                else text = "10^";
                edt1.setText(text);
            }
        };
        View.OnClickListener oclButtonSquare = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt1.getText().toString();
                Button btn = (Button)v;
                if(checker(text)){
                    edt1.setText("");
                    return;
                }
                if(text.length()!=0){
                    char cur = text.charAt(text.length()-1);
                    if(cur>='0' && cur<='9')
                        text+="²";
                }
                edt1.setText(text);
            }
        };
        View.OnClickListener oclButtonNRoot = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt1.getText().toString();
                Button btn = (Button)v;
                if(checker(text)){
                    edt1.setText("");
                    return;
                }
                if(text.length()!=0){
                    char cur = text.charAt(text.length()-1);
                    if(cur=='²') return;
                    if(cur>='0' && cur<='9'){
                        text+="^1/";
                    }
                }
                edt1.setText(text);
            }
        };
        // the main calculation
        View.OnClickListener oclButtonEqual = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt1.getText().toString();
                if(checker(text)){
                    edt1.setText("");
                    return;
                }
                if(checkForReturn(text)) {return;}

                isLog = text.indexOf("log")!=-1?true:false;
                isLn = text.indexOf("ln")!=-1?true:false;
                isTan = text.indexOf("tan")!=-1?true:false;
                isSin = text.indexOf("sin")!=-1?true:false;
                isCos = text.indexOf("cos")!=-1?true:false;
                isFactorial = text.indexOf("!")!=-1?true:false;
                isTenDegree = text.indexOf("10^")!=-1?true:false;
                isSquare = text.indexOf("²")!=-1?true:false;
                isNRoot = text.indexOf("^1/")!=-1?true:false;

                if(isLog){
                    while(text.indexOf("log")!=-1)
                        text = changeTrigionimicFunctions(text, "log");
                }
                if(isLn){
                    while(text.indexOf("ln")!=-1)
                        text = changeTrigionimicFunctions(text, "ln");
                }
                if(isTan){
                    while(text.indexOf("tan")!=-1)
                        text = changeTrigionimicFunctions(text, "tan");
                }
                if(isSin){
                    while(text.indexOf("sin")!=-1)
                        text = changeTrigionimicFunctions(text, "sin");
                }
                if(isCos){
                    while(text.indexOf("cos")!=-1)
                        text = changeTrigionimicFunctions(text, "cos");
                }
                if(isTenDegree){
                    while(text.indexOf("10^")!=-1)
                        text = changeTrigionimicFunctions(text, "10^");
                }
                if(isFactorial){
                    while(text.indexOf("!")!=-1) {
                        text = changeBeforeOperation(text, "!");
                        if (text.equals("dangeroues")) {
                            edt1.setText(text);
                            return;
                        }
                    }
                }
                if(isSquare){
                    while(text.indexOf("²")!=-1) {
                        text = changeBeforeOperation(text, "²");
                    }
                }
                if(isNRoot){
                    int a = text.indexOf("^1/");
                    String cur2 = changeBeforeOperation(text, "^1/");
                    Double t = Double.parseDouble(cur2);
                    String cur = changeTrigionimicFunctions(text, "^1/");
                    Double w = Double.parseDouble(cur);
                    w = 1/w;
                    t = Math.pow(t, w);
                    String newly = "";
                    if(a != cur2.length())
                        newly = text.substring(0, a-cur2.length());
                    newly+=Double.toString(t);
                    newly+=text.substring(cur2.length()+3+cur.length());
                    text = newly;
                }
                double last = 0;
                String current = "";
                char lastOperation = '+';
                boolean hasSqrt = false;
                boolean divideByZero = false;
                if (text.length() != 0) {
                    if (text.charAt(text.length() - 1) == '.') {
                        text = text.substring(0, text.length() - 1);
                    }
                    text += '+';
                    String newText;
                    int a = 0;
                    if(text.charAt(a)=='-'){
                        current = "-";
                        a++;
                    }
                    boolean enter = false;
                    for (int i = a; i < text.length(); ++i) {
                        char cur = text.charAt(i);
                        if (cur == '√') {
                            hasSqrt = true;
                        } else if (cur == '.' || (cur >= '0' && cur <= '9')) {
                            current += cur;
                            enter = false;
                        } else if(cur=='-' && enter){
                            current+=cur;
                        } else {
                            enter = true;
                            double have = Double.parseDouble(current);
                            if (hasSqrt) {
                                boolean ok = false;
                                if(have<0){
                                    have = (-1*have);
                                    ok = true;
                                }
                                have = Math.sqrt(have);
                                if(ok) have = (-1*have);
                            }
                            if (lastOperation == '+') {
                                last += have;
                            }
                            else if (lastOperation == '-') {
                                last -= have;
                            }
                            else if (lastOperation == '×') {
                                last *= have;
                            } else if (lastOperation == '^') {
                                last = Math.pow(last, have);
                            } else if (lastOperation == '%') {
                                last = last * have / 100.0;
                            } else if (lastOperation == '÷') {
                                boolean haveit = false;
                                for (int j = 0; j < current.length(); ++j) {
                                    if (current.charAt(j) >= '1' && current.charAt(j) <= '9') {
                                        haveit = true;
                                        break;
                                    }
                                }
                                if (haveit == false) {
                                    divideByZero = true;
                                    break;
                                } else {
                                    last /= have;
                                }
                            }
                            current = "";
                            lastOperation = cur;
                        }
                    }
                    if (divideByZero) {
                        edt1.setText("divide by Zero prohibited");
                    } else {
                        newText = Double.toString(last);
                        boolean hasDots = false;
                        for (int j = newText.length() - 1; j >= 0; --j) {
                            if (newText.charAt(j) == '.') hasDots = true;
                        }
                        while (hasDots && newText.length() > 1 && (newText.charAt(newText.length() - 1) == '.' ||
                                newText.charAt(newText.length() - 1) == '0' || newText.charAt(newText.length() - 1) == '√')) {
                            if (newText.charAt(newText.length() - 1) == '.') {
                                newText = newText.substring(0, newText.length() - 1);
                                break;
                            }
                            newText = newText.substring(0, newText.length() - 1);
                        }
                        if(newText.equals("-0")) newText = "0";
                        edt1.setText(newText);
                    }
                }
            }
        };

        // buttons of write digits
        button0.setOnClickListener(buttonDigit);
        button1.setOnClickListener(buttonDigit);
        button2.setOnClickListener(buttonDigit);
        button3.setOnClickListener(buttonDigit);
        button4.setOnClickListener(buttonDigit);
        button5.setOnClickListener(buttonDigit);
        button6.setOnClickListener(buttonDigit);
        button7.setOnClickListener(buttonDigit);
        button8.setOnClickListener(buttonDigit);
        button9.setOnClickListener(buttonDigit);
        // buttons of delete and clear
        buttonClr.setOnClickListener(oclButtonClr);
        buttonDel.setOnClickListener(oclButtonDel);
        // buttons of write operation
        buttonAdd.setOnClickListener(oclButtonOperation);
        buttonSub.setOnClickListener(oclButtonMinus);
        buttonMul.setOnClickListener(oclButtonOperation);
        buttonDiv.setOnClickListener(oclButtonOperation);
        buttonPercent.setOnClickListener(oclButtonOperation);
        buttonDegree.setOnClickListener(oclButtonOperation);
        // buttons of write float
        buttonDot.setOnClickListener(oclButtonDot);
        // buttons of write sqrt
        buttonSqrt.setOnClickListener(oclButtonSqrt);
        // the main calculation
        buttonEqual.setOnClickListener(oclButtonEqual);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            buttonFactorial.setOnClickListener(oclButtonFactorial);
            buttonCos.setOnClickListener(oclButtonTrigionometric);
            buttonSin.setOnClickListener(oclButtonTrigionometric);
            buttonLog.setOnClickListener(oclButtonTrigionometric);
            buttonLn.setOnClickListener(oclButtonTrigionometric);
            buttonTan.setOnClickListener(oclButtonTrigionometric);
            buttonTenDegree.setOnClickListener(oclButtonTenDegree);
            buttonSquare.setOnClickListener(oclButtonSquare);
            buttonNRoot.setOnClickListener(oclButtonNRoot);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("numberInput", edt1.getText().toString());
        outState.putBoolean("isCos" , isCos);
        outState.putBoolean("isSin" , isSin);
        outState.putBoolean("isTan" , isTan);
        outState.putBoolean("isLog" , isLog);
        outState.putBoolean("isLn" , isLn);
        outState.putBoolean("isFactorial", isFactorial);
        outState.putBoolean("isTenDegree", isTenDegree);
        outState.putBoolean("isSquare", isSquare);
        outState.putBoolean("isNRoot", isNRoot);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("numberInput");
        savedInstanceState.getString("isCos");
        savedInstanceState.getString("isSin");
        savedInstanceState.getString("isTan");
        savedInstanceState.getString("isLog");
        savedInstanceState.getString("isLn");
        savedInstanceState.getString("isFactorial");
        savedInstanceState.getString("isTenDegree");
        savedInstanceState.getString("isSquare");
        savedInstanceState.getString("isNRoot");
    }

}
